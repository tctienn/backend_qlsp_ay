package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ql.vn.qlsp.dto.RequsetLogin;
import ql.vn.qlsp.dto.ResponseLogin;
import ql.vn.qlsp.dto.UserDto;
import ql.vn.qlsp.entity.TokenUserEntity;
import ql.vn.qlsp.entity.UserEntity;
import ql.vn.qlsp.repository.TokenUserRepository;
import ql.vn.qlsp.repository.UserRepository;
import ql.vn.qlsp.security.CustomUserDetails;
import ql.vn.qlsp.security.JwtTokenProvider;
import ql.vn.qlsp.service.UserService;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    TokenUserRepository tokenUserRepository;

    @Autowired
    UserRepository userRepository;



    @PostMapping("login")
    public ResponseEntity<?> login( @RequestBody RequsetLogin loginRequest){
        if (userRepository.findByUserName(loginRequest.getUserName())==null){
            return new ResponseEntity<>("tài khoản không tồn tại ", HttpStatus.BAD_REQUEST);
        }

//        // Xác thực từ username và password.
//        System.out.println("ayyy"+userRepository.findByUsername(loginRequest.getUserName()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassWord()
                )
        );
        if (userRepository.findByUserName(loginRequest.getUserName()).getRole()==null){
            return new ResponseEntity<>("tài khoản chưa xác thực", HttpStatus.FORBIDDEN);
        }
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println(authentication);


        ////// lấy id người dùng
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity user = userDetails.getUserEntity();

        /////

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//        System.out.println("user " + ResponseLogin.builder().user(user).stringToken(jwt).build());


        return ResponseEntity.ok(ResponseLogin.builder().user(user).stringToken(jwt).build());
    }
    @PostMapping("signup")
    public ResponseEntity<?> singUp(@RequestBody UserDto userDto){

        try{
            userDto.setPassWord(passwordEncoder.encode(userDto.getPassWord()));

            return ResponseEntity.ok(userService.singUp(userDto));
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }


    }
    @GetMapping("public/confirm")
    public void confirm(@RequestParam(name = "token") String token ){

        if(tokenUserRepository.findByToken(token)==null){
            return;
        }
        TokenUserEntity tokenUserEntity = tokenUserRepository.findByToken(token);
        UserEntity userEntity = userRepository.findById(tokenUserEntity.getUserId()).orElse(null);
        if(userEntity==null)
            return;
        userEntity.setRole("user");
        userRepository.save(userEntity);

    }

    @PostMapping("public/user/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String userName , @RequestParam String gmail, @RequestParam String password){

        try{
            userService.rememberPassword(userName,gmail,password);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("public/confirm-changepassword")
    public ResponseEntity<?> confirmChangePassword(@RequestParam(name = "token") String token, @RequestParam(name = "password") String password ){

        if(tokenUserRepository.findByToken(token)==null){
            return new ResponseEntity<>("không tìm thấy token", HttpStatus.BAD_REQUEST);
        }
        TokenUserEntity tokenUserEntity = tokenUserRepository.findByToken(token);
        UserEntity userEntity = userRepository.findById(tokenUserEntity.getUserId()).orElse(null);
        if(userEntity==null)
            return new ResponseEntity<>("không tìm tháy user với token tương ứng", HttpStatus.BAD_REQUEST);
        userEntity.setPassWord(passwordEncoder.encode(password));
        userRepository.save(userEntity);
        return ResponseEntity.ok("ok");
    }
}
