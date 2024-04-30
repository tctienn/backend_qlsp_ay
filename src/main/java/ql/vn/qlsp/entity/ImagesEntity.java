package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ql.vn.qlsp.controller.fontend.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="images")
@Data
public class ImagesEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String url;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_product",updatable = false, insertable = false)
    private ProductEntity productss;

    @Column(name = "id_product")
    private Integer idProduct;

}
