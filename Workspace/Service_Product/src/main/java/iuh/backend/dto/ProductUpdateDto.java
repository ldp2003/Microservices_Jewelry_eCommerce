package iuh.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link iuh.backend.entities.Product}
 */

@Getter
@Setter
public class ProductUpdateDto implements Serializable {
    Integer id;
    String name;
    String description;
    Integer stock;
    Double price;
    Integer gender;
    String material;
    Integer goldKarat;
    String color;
    String brand;
    Integer categoryId;
    Instant createdAt;
    List<Integer> productImageIds;
    Integer collectionId;
    String size;
}