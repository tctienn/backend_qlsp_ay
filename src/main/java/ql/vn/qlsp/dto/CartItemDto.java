package ql.vn.qlsp.dto;

import lombok.Data;
import ql.vn.qlsp.entity.ProductEntity;

@Data
public class CartItemDto {

    private Integer id;
    private Integer idCart;
    ProductEntity product;
    private int quantity;
    private float price;
}
