package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="size")
@Data
public class SizeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;

//    @ManyToMany(mappedBy = "sizes")
//    @JsonIgnore
//    List<ProductEntity> products;
}
