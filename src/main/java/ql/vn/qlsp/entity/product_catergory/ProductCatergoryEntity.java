package ql.vn.qlsp.entity.product_catergory;

import lombok.Data;
import ql.vn.qlsp.entity.CatergoryEntity;
import ql.vn.qlsp.entity.ProductEntity;


import javax.persistence.*;

@Entity
@Table(name = "product_catergory")
@Data
public class ProductCatergoryEntity {
    @EmbeddedId
    private ProductCatergoryKey id;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_catergory", insertable = false, updatable = false)
    private CatergoryEntity catergory;

}
