package ql.vn.qlsp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ql.vn.qlsp.entity.CartEntity;
import ql.vn.qlsp.entity.CartItemEntity;
import ql.vn.qlsp.service.CartService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user/cart")
public class CartController {
    @Autowired
    CartService cartService;


    @GetMapping("get-cart-by-user/{id}")
    public ResponseEntity<?> getcart(@PathVariable Long id){
        List<CartItemEntity> cartItems = new ArrayList<>();
        try{
            cartItems = cartService.getCartItems(id);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("add-cartitem")
    public ResponseEntity<?> addCartItem(@RequestParam("idUser") Long idUser, @RequestParam(name = "idProduct") Integer idProduct, @RequestParam(name = "quantity") int quantity){
        CartItemEntity cartItemEntity;
        try{
            cartItemEntity = cartService.addCartItem(idUser,idProduct,quantity);
            return ResponseEntity.ok(cartItemEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
    @PostMapping("delete-cartItem")
    public ResponseEntity<?> deleteCartItem(@RequestParam(name = "idUser") Long idUser, @RequestParam(name = "idProduct") Integer idProduct){
        try{
            cartService.removeCartItem(idUser,idProduct);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("xóa thành công");
    }

    @GetMapping("check-cartItem")
    public ResponseEntity<?> checkCartItem(@RequestParam Long idUser,@RequestParam Integer idProduct){
        try {
            CartItemEntity cartItemEntity = cartService.checkCartItem(idUser,idProduct);
            return ResponseEntity.ok(cartItemEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}
