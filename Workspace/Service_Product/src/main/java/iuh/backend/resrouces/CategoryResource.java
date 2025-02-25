package iuh.backend.resrouces;

import iuh.backend.dto.CategoryDto;
import iuh.backend.entities.Category;
import iuh.backend.entities.Product;
import iuh.backend.services.CategoryService;
import iuh.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/listCategory")
    public List<Category> showCategoryList() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/detailCategory/{id}")
    public Category showCategoryDetail(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        if(categoryDto.getProductIds() != null) {
            category.setProducts(productService.getAllByIds(categoryDto.getProductIds()));
        }
        Category savedCategory = categoryService.addCategory(category);

        for(Integer productId : categoryDto.getProductIds()) {
            Product product = productService.getProductById(productId);
            product.setCategoryId(savedCategory);
            productService.updateProduct(product);
        }
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory == null || !id.equals(categoryDto.getId())) {
            return ResponseEntity.notFound().build();
        }

        if (categoryDto.getName() != null) {
            existingCategory.setName(categoryDto.getName());
        }
        if (categoryDto.getDescription() != null) {
            existingCategory.setDescription(categoryDto.getDescription());
        }
        if (categoryDto.getProductIds() != null) {
            existingCategory.setProducts(productService.getAllByIds(categoryDto.getProductIds()));
        }

        return ResponseEntity.ok(categoryService.updateCategory(existingCategory));
    }

}
