package iuh.backend.resrouces;

import iuh.backend.dto.ProductCreateDto;
import iuh.backend.dto.ProductUpdateDto;
import iuh.backend.entities.Category;
import iuh.backend.entities.Collection;
import iuh.backend.entities.Product;
import iuh.backend.entities.Productimage;
import iuh.backend.services.CategoryService;
import iuh.backend.services.CollectionService;
import iuh.backend.services.ProductService;
import iuh.backend.services.ProductimageService;
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
    @Autowired
    private ProductimageService productimageService;

    @GetMapping("/listProduct")
    public List<Product> showProductList() {
        return productService.getAllProducts();
    }

    @GetMapping("/detailProduct/{id}")
    public Product showProductDetail(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductCreateDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());
        product.setGender(productDto.getGender());
        product.setMaterial(productDto.getMaterial());
        product.setGoldKarat(productDto.getGoldKarat());
        product.setColor(productDto.getColor());
        product.setBrand(productDto.getBrand());
        product.setSize(productDto.getSize());

        if (productDto.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productDto.getCategoryId());
            product.setCategoryId(category);
        }

        if (productDto.getCollectionId() != null) {
            Collection collection = collectionService.getCollectionById(productDto.getCollectionId());
            product.setCollectionId(collection);
        }

        //Khong test them hinh anh, can hoan thien productimage api truoc
        if (productDto.getProductImageIds() != null) {
            List<Productimage> images = productimageService.getAllById(productDto.getProductImageIds());
            product.setProductImages(images);
        }

        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductUpdateDto productUpdateDTO) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null || !id.equals(productUpdateDTO.getId())) {
            return ResponseEntity.notFound().build();
        }
        if(productUpdateDTO.getName() != null)
            existingProduct.setName(productUpdateDTO.getName());
        if(productUpdateDTO.getDescription() != null)
            existingProduct.setDescription(productUpdateDTO.getDescription());
        if(productUpdateDTO.getStock() != null)
            existingProduct.setStock(productUpdateDTO.getStock());
        if(productUpdateDTO.getPrice() != null)
            existingProduct.setPrice(productUpdateDTO.getPrice());
        if(productUpdateDTO.getGender() != null)
            existingProduct.setGender(productUpdateDTO.getGender());
        if(productUpdateDTO.getSize() != null)
            existingProduct.setSize(productUpdateDTO.getSize());
        if(productUpdateDTO.getMaterial() != null)
            existingProduct.setMaterial(productUpdateDTO.getMaterial());
        if(productUpdateDTO.getColor() != null)
            existingProduct.setColor(productUpdateDTO.getColor());
        if(productUpdateDTO.getBrand() != null)
            existingProduct.setBrand(productUpdateDTO.getBrand());
        if(productUpdateDTO.getGoldKarat() != null)
            existingProduct.setGoldKarat(productUpdateDTO.getGoldKarat());

        if(productUpdateDTO.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productUpdateDTO.getCategoryId());
            existingProduct.setCategoryId(category);
        }

        if(productUpdateDTO.getCollectionId() != null) {
            Collection collection = collectionService.getCollectionById(productUpdateDTO.getCollectionId());
            existingProduct.setCollectionId(collection);
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
