package ql.vn.qlsp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "blog")
@Data
public class BlogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String title;
    private String conten;

    @Column(name = "namecreateuser")
    private String nameCreateUser;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "imgmain")
    private String imgMain;
    @Column(name = "imgbackground")
    private String imgBackGround;

    @JsonIgnore
    @OneToMany(mappedBy = "blog",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<BlogRenderEntity> blogRenderEntities;
}
