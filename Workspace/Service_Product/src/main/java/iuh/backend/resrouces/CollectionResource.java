package iuh.backend.resrouces;

import iuh.backend.entities.Collection;
import iuh.backend.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/collection")
public class CollectionResource {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/listCollection")
    public List<Collection> showCollectionList() {
        return collectionService.getAllCollections();
    }
}
