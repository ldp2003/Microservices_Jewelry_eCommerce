package iuh.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "productimage")
public class Productimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productid")
    private Product productid;

    @Column(name = "imageurl", nullable = false, length = Integer.MAX_VALUE)
    private String imageurl;

    @Column(name = "isthumbnail")
    private Boolean isthumbnail;

}