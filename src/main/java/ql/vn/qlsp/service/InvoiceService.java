package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ql.vn.qlsp.dto.TopUser;
import ql.vn.qlsp.entity.CartItemEntity;
import ql.vn.qlsp.entity.InvoiceEntity;
import ql.vn.qlsp.entity.UserEntity;
import ql.vn.qlsp.repository.CartRepository;
import ql.vn.qlsp.repository.InvoiceRepository;
import ql.vn.qlsp.repository.UserRepository;
import sun.rmi.runtime.Log;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    ////////////succes
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CartService cartService;

    @Autowired
    UserRepository userRepository;

    public Page<InvoiceEntity> getallInvoice(Pageable pageable){
        return invoiceRepository.findAll(pageable);
    }

    @Transactional
    public Double createInvoice( String SoHD,Long idUser,String diaChi) throws Exception {
        UserEntity userEntity = userRepository.findById(idUser).orElse(null);
        if(userEntity==null){
            throw new Exception("không tìm thấy id user để tạo invoice");
        }
        List<CartItemEntity> cartItems = cartService.getCartItems(userEntity.getId());
        if(cartItems.size()<=0){
            throw new Exception("trong giỏ hảng không có sản phẩm");
        }
        String listProduct="";
        for(CartItemEntity e : cartItems){
            listProduct += "name:"+e.getProduct().getName()+",quantity:"+e.getQuantity()+",price:"+e.getPrice()+"\n";
        }
        double sum = cartItems.stream().mapToDouble(e->e.getQuantity()*e.getPrice()).sum();
        System.out.println("dia chi" + diaChi);
        InvoiceEntity invoiceEntity = new InvoiceEntity(SoHD, sum, "NCB", "Giao dịch mua hàng", "wait", listProduct, "wait", diaChi, userEntity.getGmail(), userEntity.getSdt(), userEntity.getUserName());

        invoiceRepository.save(invoiceEntity);
        cartService.deleteAllCartItemByIdCart(idUser);
        return sum;
    }
    @Transactional
    public void succesPay( String soHD,String maGD, String createTime) throws Exception {
       InvoiceEntity invoiceEntity = invoiceRepository.findBySohd(soHD);
       invoiceEntity.setMagd(maGD);
       invoiceEntity.setTrangthai("succes");
       invoiceEntity.setNgaytao(createTime);

    }
    @Transactional
    public void falsePay( String soHD, String createTime) throws Exception {
        InvoiceEntity invoiceEntity = invoiceRepository.findBySohd(soHD);
        invoiceEntity.setTrangthai("false");
        invoiceEntity.setNgaytao(createTime);

    }

    ///// admin
    public Page<InvoiceEntity> getInvoices(Pageable pageable){

        return invoiceRepository.findAll(pageable);
    }
    public List<InvoiceEntity> getInvoiceByweek(){
        return invoiceRepository.getInvoicesByweek();
    }
    public List<TopUser> getTopUserPay(){
        return invoiceRepository.getTopPay();
    }

    public Integer countInvoiceWait(){
        return invoiceRepository.coutInvoiceWait();
    }

    @Transactional
    public void succesGiaoHang(String soHD, String giaohang) throws Exception {
        InvoiceEntity invoiceEntity = invoiceRepository.findBySohd(soHD);
        if(invoiceEntity== null){
            throw new Exception("không tìm thấy sohd của hóa đơn cần thay đổi giao hàng");
        }
        invoiceEntity.setGiaohang(giaohang);


    }

}
