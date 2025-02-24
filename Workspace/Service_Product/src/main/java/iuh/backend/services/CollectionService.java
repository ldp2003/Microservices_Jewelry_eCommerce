package iuh.backend.services;

import iuh.backend.entities.Collection;
import iuh.backend.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    public void addCollection(Collection collection) {
        collectionRepository.save(collection);
    }

    public void updateCollection(Collection collection) {
        collectionRepository.save(collection);
    }

    public void deleteCollection(Collection collection) {
        collectionRepository.delete(collection);
    }

    public Collection getCollectionById(Integer id) {
        return collectionRepository.findById(id).orElse(null);
    }

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

}
