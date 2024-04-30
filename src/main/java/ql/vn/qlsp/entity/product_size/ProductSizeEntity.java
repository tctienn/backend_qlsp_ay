package ql.vn.qlsp.entity.product_size;

import lombok.Data;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.entity.SizeEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_size")
@Data
public class ProductSizeEntity {

    @EmbeddedId
    private ProductSizeKey id;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_size", insertable = false, updatable = false)
    private SizeEntity size;

}
