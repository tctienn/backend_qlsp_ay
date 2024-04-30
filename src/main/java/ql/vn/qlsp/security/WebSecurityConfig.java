package ql.vn.qlsp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ql.vn.qlsp.service.UserService;

import java.util.Arrays;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;



    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }


    //// cấu hình cors có thể dùng 2 cách 1 thêm .cros và cấu hình lớp bean, cách 2 là dùng trực tiếp trong anotation CrossOrigin trong contronler
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
   ///////////////////////


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors() // Ngăn chặn request từ một domain khác
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
                .antMatchers("/signup").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
                .antMatchers("/public/**").permitAll()
                .antMatchers("/actuator/**").permitAll() // giám sát hệ thống
                .antMatchers("/pay").permitAll()
                .antMatchers("/submitOrder").permitAll()
                .antMatchers("/vnpay-payment").permitAll()
                .antMatchers("/public/confirm").permitAll()
                .antMatchers("/backend/**").hasAnyRole("admin")
                .antMatchers("/user/**").hasAnyRole("user", "admin")
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập
        // Thêm một lớp Filter kiểm tra jwt
        JwtAuthenticationFilter jwtAuthenticationFilter = jwtAuthenticationFilter();
        jwtAuthenticationFilter.setExcludedUrls(Arrays.asList("/login", "/signup","/public/**","/pay","/actuator/**","/vnpay-payment","/submitOrder","/public/confirm"));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}