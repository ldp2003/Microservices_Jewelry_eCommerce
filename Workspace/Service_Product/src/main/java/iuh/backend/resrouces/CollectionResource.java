package iuh.backend.resrouces;

import iuh.backend.dto.CollectionDto;
import iuh.backend.entities.Collection;
import iuh.backend.entities.Product;
import iuh.backend.services.CollectionService;
import iuh.backend.services.CollectionimageService;
import iuh.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection")
public class CollectionResource {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CollectionimageService collectionimageService;
    @Autowired
    private ProductService productService;

    @GetMapping("/listCollection")
    public List<Collection> showCollectionList() {
        return collectionService.getAllCollections();
    }

    @GetMapping("/detailCollection/{id}")
    public Collection showCollectionDetail(@PathVariable Integer id) {
        return collectionService.getCollectionById(id);
    }

    @PostMapping("/addCollection")
    public ResponseEntity<Collection> addCollection(@RequestBody CollectionDto collectionDto) {
        Collection collection = new Collection();
        collection.setName(collectionDto.getName());
        collection.setDescription(collectionDto.getDescription());

        if(collectionDto.getCollectionImageIds() != null) {
            collection.setCollectionImages(collectionimageService.getAllById(collectionDto.getCollectionImageIds()));
        }

        if(collectionDto.getProductIds() != null) {
            collection.setProducts(productService.getAllByIds(collectionDto.getProductIds()));
        }
        Collection savedCollection = collectionService.addCollection(collection);

        for (Integer productId : collectionDto.getProductIds()) {
            Product product = productService.getProductById(productId);
            product.setCollectionId(savedCollection);
            productService.updateProduct(product);
        }

        return ResponseEntity.ok(savedCollection);
    }

    @PutMapping("/updateCollection/{id}")
    public ResponseEntity<Collection> updateCollection(@RequestBody CollectionDto collectionDto, @PathVariable Integer id) {
        Collection existingCollection = collectionService.getCollectionById(id);
        if(existingCollection == null || !id.equals(collectionDto.getId())) {
            return ResponseEntity.notFound().build();
        }

        if(collectionDto.getName() != null) {
            existingCollection.setName(collectionDto.getName());
        }
        if(collectionDto.getDescription() != null) {
            existingCollection.setDescription(collectionDto.getDescription());
        }
        if(collectionDto.getCollectionImageIds() != null) {
            existingCollection.setCollectionImages(collectionimageService.getAllById(collectionDto.getCollectionImageIds()));
        }
        return ResponseEntity.ok(collectionService.updateCollection(existingCollection));
    }
}
