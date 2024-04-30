package ql.vn.qlsp.entity;

import lombok.Builder;
import lombok.Data;
import ql.vn.qlsp.dto.UserDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@Builder
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;
    private String sdt;
    private String role;
    @Column(name = "create_time")
    private Date createTime;
    private String gmail;


    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
        this.id = userDto.getId();
        this.userName = userDto.getUserName();
        this.passWord = userDto.getPassWord();
        this.sdt = userDto.getSdt();
        this.role = userDto.getRole();
        this.createTime = userDto.getCreateTime();
        this.gmail = userDto.getGmail();
    }

    public UserEntity(Long id, String userName, String passWord, String sdt, String role, Date createTime, String gmail) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.sdt = sdt;
        this.role = role;
        this.createTime = createTime;
        this.gmail = gmail;
    }
}
