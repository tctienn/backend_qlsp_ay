package ql.vn.qlsp.entity.product_size;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductSizeKey implements Serializable {
    @Column(name = "id_product")
    private Integer productId;

    @Column(name = "id_size")
    private Integer sizeId;


    public ProductSizeKey() {
    }

    public ProductSizeKey(Integer productId, Integer sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }
}
