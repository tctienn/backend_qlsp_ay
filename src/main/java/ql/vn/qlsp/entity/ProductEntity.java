package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ql.vn.qlsp.dto.ProductEntityDto;

import javax.persistence.*;
//import java.util.Date;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="product")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String alias;
    private String name;
    private String mota;

    @Column(name="mota_ngan")
    private String motaNgan;

    @ManyToOne
    @JoinColumn(name = "brand")
    private BrandsEntity brand;


    @ManyToOne
    @JoinColumn(name = "tag")
    private TagEntity tag;


    @ManyToMany
    @JoinTable(
            name = "product_catergory",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_catergory")
    )
    private List<CatergoryEntity> catergorys;

    // orphanRemoval để sử lý các đối tương con khi không còn được tham chiếu tới cha
    @OneToMany(mappedBy = "productss",cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<ImagesEntity> images;

    @ManyToMany
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_size"))
    List<SizeEntity> sizes;

    private float cost;
    private float gia;
    @Column(name="main_img")
    private String mainImg;
    @Column(name="create_at")
    private Date createAt;
    private int start;
    @Column(name="update_at")
    private Date updateAt;
    private int enable;
    @Column(name="in_stock")
    private int inStock;


//    @OneToMany(mappedBy = "product")
//    @JsonIgnore
//    private List<CartItemEntity> cartItems;

    public ProductEntity() {
    }

    // khởi tạo product vói productentitydto


    public ProductEntity(ProductEntityDto productEntityDto) {
        this.id = productEntityDto.getId();
        this.alias = productEntityDto.getAlias();
        this.name = productEntityDto.getName();
        this.mota = productEntityDto.getMota();
        this.motaNgan = productEntityDto.getMotaNgan();
        this.brand = productEntityDto.getBrand();
        this.tag = productEntityDto.getTag();
        this.catergorys = productEntityDto.getCatergorys();
        this.images = productEntityDto.getImages();
        this.sizes = productEntityDto.getSizes();
        this.cost = productEntityDto.getCost();
        this.gia = productEntityDto.getGia();
        this.mainImg = productEntityDto.getMainImg();
        this.createAt = productEntityDto.getCreateAt();
        this.start = productEntityDto.getStart();
        this.updateAt = productEntityDto.getUpdateAt();
        this.enable = productEntityDto.getEnable();
        this.inStock = productEntityDto.getInStock();

    }
}
