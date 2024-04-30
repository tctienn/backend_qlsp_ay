package ql.vn.qlsp.dto;

import lombok.Builder;
import lombok.Data;
import ql.vn.qlsp.entity.UserEntity;

@Data
@Builder
public class ResponseLogin {
    private UserEntity user;
    private String stringToken;



}
