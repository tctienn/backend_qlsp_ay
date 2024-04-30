package ql.vn.qlsp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Data
public class TokenUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Long userId;
    private String token;
    @Column(name="end_time")
    private LocalDateTime endTime = LocalDateTime.now().plusMinutes(60);

}
