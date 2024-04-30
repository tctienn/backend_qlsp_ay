package ql.vn.qlsp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="blog_render")
@Data
public class BlogRenderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_blog")
    private BlogEntity blog;
    private int type;
    private int top;




}
