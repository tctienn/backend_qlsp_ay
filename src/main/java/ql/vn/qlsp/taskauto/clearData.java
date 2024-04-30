package ql.vn.qlsp.taskauto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ql.vn.qlsp.repository.TokenUserRepository;

@Component
public class clearData {

    @Autowired
    TokenUserRepository tokenUserRepository;
    // Thực hiện tác vụ hàng ngày vào lúc 12:00 AM
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanup() {
        System.out.println("_______________clear data auto _________________");
    }
}
