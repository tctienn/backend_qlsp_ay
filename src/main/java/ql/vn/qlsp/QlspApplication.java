package ql.vn.qlsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QlspApplication {

    public static void main(String[] args) {
        SpringApplication.run(QlspApplication.class, args);
    }

}
