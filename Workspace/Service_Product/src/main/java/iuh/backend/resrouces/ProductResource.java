package iuh.backend.resrouces;

import iuh.backend.dto.ProductUpdateDto;
import iuh.backend.entities.Category;
import iuh.backend.entities.Collection;
import iuh.backend.entities.Product;
import iuh.backend.services.CategoryService;
import iuh.backend.services.CollectionService;
import iuh.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductResource {
    @Autowired
    private ProductService productService;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listProduct")
    public List<Product> showProductList() {
        return productService.getAllProducts();
    }

    @GetMapping("/detailProduct/{id}")
    public Product showProductDetail(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductUpdateDto productUpdateDTO) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null || !id.equals(productUpdateDTO.getId())) {
            return ResponseEntity.notFound().build();
        }
        //Chua xu ly them hay cap nhat hinh anh product
        return ResponseEntity.ok(productService.updateProduct(existingProduct));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.deleteProduct(product));
    }

}
