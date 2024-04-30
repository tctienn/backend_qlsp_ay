package ql.vn.qlsp.service;

import org.hibernate.cache.cfg.spi.EntityDataCachingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ql.vn.qlsp.dto.CartItemDto;
import ql.vn.qlsp.dto.ProductEntityDto;
import ql.vn.qlsp.entity.CartEntity;
import ql.vn.qlsp.entity.CartItemEntity;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.entity.UserEntity;
import ql.vn.qlsp.repository.CartItemRepository;
import ql.vn.qlsp.repository.CartRepository;
import ql.vn.qlsp.repository.ProductRepository;
import ql.vn.qlsp.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CheckDateNow checkDateNow;


@Transactional
    public CartEntity getCartByIdUser(Long id) throws Exception{
//        kiểm tra id user có hợp lệ ?
        userRepository.findById(id).orElseThrow(
                () -> new Exception("id user không tồn tại , không thể tra cứu cart của user không tồn tại : " + id)
        );
        // kiểm tra cart đã tồn tại ? nếu chưa tạo một cái
        CartEntity cartEntity = cartRepository.findByIdUser(id);
        if(cartEntity==null){
            cartEntity = new CartEntity();
            cartEntity.setIdUser(id);
            cartRepository.save(cartEntity);
        }
        return cartEntity;
    }

    public List<CartItemEntity> getCartItems(Long id) throws Exception {

        return cartItemRepository.findAllByIdCart(getCartByIdUser(id).getId());
    }

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public CartItemEntity addCartItem(Long idUser, Integer idProduct, int quantity) throws Exception {
            CartEntity cartEntity = getCartByIdUser(idUser);// kiểm tra xem có cart hay không
            CartItemEntity cartItemEntity = cartItemRepository.findByIdCartAndProductId(cartEntity.getId(),idProduct);
            ProductEntity productEntity= productRepository.findById(idProduct).orElse(null);
            if(productEntity==null){
                throw new Exception("không tìm thấy id product trong hàm add cartitem");

            }
            if(cartEntity==null){
                throw new Exception("cart entity null");
            }
            int inStock = 0;
            if (productEntity.getInStock()-quantity<0){
                System.out.println("_______________ss_____________________________");
                throw new Exception("số lượng trong kho không đủ");
            }

        if(cartItemEntity==null){
                cartItemEntity = new CartItemEntity();
                cartItemEntity.setIdCart(cartEntity.getId());
                cartItemEntity.setProduct(productEntity);
                inStock = -quantity;
                cartItemEntity.setQuantity(quantity);
                cartItemEntity.setPrice(productEntity.getGia());
            }else {
                System.out.println("________________1____________________________");
                inStock =  cartItemEntity.getQuantity() -quantity;
                cartItemEntity.setQuantity(quantity);
                System.out.println("__________________22__________________________");
            }

            productEntity.setInStock(productEntity.getInStock()+inStock);
        System.out.println("____________________s________________________"+ quantity + cartItemEntity.getQuantity() +" \n ");
            productRepository.save(productEntity);
        return cartItemRepository.save(cartItemEntity);
//        return new CartItemEntity();
    }


    public void removeCartItem(Long idUser, Integer idProduct) throws Exception {
        CartEntity cartEntity = getCartByIdUser(idUser);
        CartItemEntity cartItemEntity = cartItemRepository.findByIdCartAndProductId(cartEntity.getId(),idProduct);
        if(cartItemEntity==null){
             throw new Exception("không tìm thấy cartitem để xóa");
        }
         cartItemRepository.delete(cartItemEntity);
    }

    @Transactional
    public void deleteAllCartItemByIdCart(Long idUSer) throws Exception {
         CartEntity cartEntity = getCartByIdUser(idUSer);
         cartItemRepository.deleteAllByIdCart(cartEntity.getId());

    }


    public CartItemEntity checkCartItem(Long idUser, Integer idProduct) throws Exception {
        CartEntity cartEntity = getCartByIdUser(idUser);
        CartItemEntity cartItemEntity = cartItemRepository.findByIdCartAndProductId(cartEntity.getId(),idProduct);
        if(cartItemEntity!=null){
            throw new Exception("product đã nằm trong giỏ hảng");

        }
        return cartItemEntity;
    }








}
