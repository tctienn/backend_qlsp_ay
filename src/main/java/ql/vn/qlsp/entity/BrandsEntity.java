package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
@Data
public class BrandsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;
    private String img;

//    @OneToMany(mappedBy = "brand")
//    @JsonIgnore
//    List<ProductEntity> products;
}
