package ql.vn.qlsp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class CatergoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String img;



}
