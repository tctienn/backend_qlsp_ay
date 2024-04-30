package ql.vn.qlsp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ql.vn.qlsp.entity.*;

import javax.persistence.*;
//import java.util.Date;
import java.sql.Date;
import java.util.List;

@Data
public class ProductEntityDto {
    private Integer id;
    private String alias;
    private String name;
    private String mota;
    private String motaNgan;
    private BrandsEntity brand;
    private TagEntity tag;
    private List<CatergoryEntity> catergorys;
    private List<ImagesEntity> images;
    private List<SizeEntity> sizes;
    private float cost;
    private float gia;
    private String mainImg;
    private Date createAt;
    private int start;
    private Date updateAt;
    private int enable;
    private int inStock;

    @Override
    public String toString() {
        return "ProductEntityDto{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                ", mota='" + mota + '\'' +
                ", motaNgan='" + motaNgan + '\'' +
                ", brand=" + brand +
                ", tag=" + tag +
                ", catergorys=" + catergorys +
                ", images=" + images +
                ", sizes=" + sizes +
                ", cost=" + cost +
                ", gia=" + gia +
                ", mainImg='" + mainImg + '\'' +
                ", createAt=" + createAt +
                ", start=" + start +
                ", updateAt=" + updateAt +
                ", enable=" + enable +
                ", inStock=" + inStock +
                '}';
    }
}
