package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ql.vn.qlsp.dto.CartItemDto;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Data
public class CartItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "id_cart")
    private Integer idCart;
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "id_cart")
//    CartEntity cart;


//    @Column(name = "id_product")
//    private Integer idProduct;
    @ManyToOne
    @JoinColumn(name = "id_product")
    ProductEntity product;

    private int quantity;
    private float price;


}
