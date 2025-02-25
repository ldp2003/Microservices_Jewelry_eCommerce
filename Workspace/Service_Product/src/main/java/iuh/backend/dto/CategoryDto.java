package iuh.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
    private List<Integer> productIds;
}
