package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "id_user")
    private Long idUser;


//    @OneToMany(mappedBy = "cart", orphanRemoval = true)
////    @JsonIgnore
//    private List<CartItemEntity> cariItems;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "cart_item", joinColumns = @JoinColumn(name = "id_cart"), inverseJoinColumns = @JoinColumn(name = "id_product"))
//    List<CartItemEntity> cartItems ;


}
