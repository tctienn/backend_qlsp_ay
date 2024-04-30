package ql.vn.qlsp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String passWord;
    private String sdt;
    private String role;
    private Date createTime;
    private String gmail;

}
