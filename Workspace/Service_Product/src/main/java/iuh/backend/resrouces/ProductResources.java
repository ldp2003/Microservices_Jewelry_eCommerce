package iuh.backend.resrouces;

import iuh.backend.entities.Product;
import iuh.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductResources {
    @Autowired
    private ProductService productService;

    @GetMapping("/listProduct")
    public List<Product> showProductList() {
        System.out.println("showProductList" + productService.getAllProducts());
        return productService.getAllProducts();
    }


}
