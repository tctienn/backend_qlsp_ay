package ql.vn.qlsp.entity.product_catergory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductCatergoryKey implements Serializable {
    @Column(name = "id_product")
    private Integer productId;

    @Column(name = "id_catergory")
    private Integer sizeId;

    public ProductCatergoryKey() {
    }

    public ProductCatergoryKey(Integer productId, Integer sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }
}
