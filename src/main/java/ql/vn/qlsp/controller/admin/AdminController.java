package ql.vn.qlsp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.dto.TopUser;
import ql.vn.qlsp.entity.*;
import ql.vn.qlsp.repository.CatergoryRepository;
import ql.vn.qlsp.repository.InvoiceRepository;
import ql.vn.qlsp.repository.UserRepository;
import ql.vn.qlsp.service.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping("invoice/get-invoices-by-week")
    public List<InvoiceEntity> getCurrentUserName() {
        return invoiceService.getInvoiceByweek();
    }

    @GetMapping("invoice/get-invoices")
    public Page<InvoiceEntity> getInvoices(Pageable pageable){
        return invoiceService.getInvoices(pageable);
    }

    @GetMapping("invoice/findter-name/{sohd}")
    public InvoiceEntity getInvoiceByName(@PathVariable("sohd") String sohd ){
        return invoiceRepository.findBySohd(sohd);
    }
    @GetMapping("invoice/get-top-user")
    public List<TopUser> getTopUsers(){
        return invoiceService.getTopUserPay();
    }

    @GetMapping("invoice/cout-invoice-wait")
    public Integer countInvoiceWait(){
        return invoiceService.countInvoiceWait();
    }
    @GetMapping("invoice/get-invoice-wait")
    public Page<InvoiceEntity> getInvoiceWait(Pageable pageable){
        return invoiceRepository.getInvoiceWait(pageable);
    }
    @PostMapping("invoice/update-giaohang")
    public ResponseEntity<?> getInvoiceWait(@RequestParam String sohd,@RequestParam String giaohang){
        try{
            if(!giaohang.equals("succes"))
            {
                invoiceService.succesGiaoHang(sohd,"false");
                return ResponseEntity.ok("ok là false hoặt succes không hợ lệ");
            }
            invoiceService.succesGiaoHang(sohd,"succes");
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/cout-admin")
    public Integer countAdmin(){
        return  userRepository.coutAdmin();
    }
    @GetMapping("user/cout-user")
    public Integer countUser(){
        return userRepository.coutUser();
    }

    @GetMapping("user/cout-userwait")
    public Integer countUserWait(){
        return userRepository.coutUserWait();
    }

    @GetMapping("user/get-users")
    public Page<UserEntity> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Autowired
    CatergoryService catergoryService;
    @PostMapping("catergory/add-catergory")
    public ResponseEntity<?> addCatergory(@RequestBody CatergoryEntity catergoryEntityParam){

        try{
            CatergoryEntity catergoryEntity = catergoryService.addCatergory(catergoryEntityParam);
            return ResponseEntity.ok(catergoryEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("catergory/update-catergory")
    public ResponseEntity<?> updateCatergory(@RequestBody CatergoryEntity catergoryEntityParam){

        try{
            CatergoryEntity catergoryEntity = catergoryService.updateCatergory(catergoryEntityParam);
            return ResponseEntity.ok(catergoryEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("catergory/delete-catergory")
    public ResponseEntity<?> deleteCatergory(@RequestParam Integer idCatergory){

        try{
            catergoryService.deleteCatergory(idCatergory);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @Autowired
    TagService tagService;

    @PostMapping("tag/add-tag")
    public ResponseEntity<?> addTag(@RequestBody TagEntity tagEntityParam){

        try{
            TagEntity tagEntity = tagService.addTag(tagEntityParam);
            return ResponseEntity.ok(tagEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("tag/update-tag")
    public ResponseEntity<?> updateTag(@RequestBody TagEntity tagEntityParam){

        try{
            TagEntity tagEntity = tagService.updateTag(tagEntityParam);
            return ResponseEntity.ok(tagEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("tag/delete-tag")
    public ResponseEntity<?> deleteTag(@RequestParam Integer idTag){

        try{
            tagService.deleteTag(idTag);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Autowired
    BrandService brandService;

    @PostMapping("brand/add-brand")
    public ResponseEntity<?> addBrand(@RequestBody BrandsEntity brandsEntityParam){

        try{
            BrandsEntity brandsEntity = brandService.addBrand(brandsEntityParam);
            return ResponseEntity.ok(brandsEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("brand/update-brand")
    public ResponseEntity<?> updateBrand(@RequestBody BrandsEntity brandsEntityParam){

        try{
            BrandsEntity brandsEntity = brandService.updateBrand(brandsEntityParam);
            return ResponseEntity.ok(brandsEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("brand/update-imgBrand")
    public ResponseEntity<?> updateBrand(@RequestParam("idBrand") Integer idBrand , @RequestParam(value="fileImg" , required = false)MultipartFile imgFile){

        try{
            brandService.updateImge(idBrand, imgFile);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("brand/delete-brand")
    public ResponseEntity<?> deleteBrand(@RequestParam Integer idBrand){

        try{
            brandService.deleteBrand(idBrand);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Autowired
    SizeService sizeService;

    @PostMapping("size/add-size")
    public ResponseEntity<?> addSize(@RequestBody SizeEntity sizeEntityPram){

        try{
            SizeEntity sizeEntity = sizeService.addSize(sizeEntityPram);
            return ResponseEntity.ok(sizeEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("size/update-size")
    public ResponseEntity<?> updateSize(@RequestBody SizeEntity sizeEntityParam){

        try{
            SizeEntity sizeEntity = sizeService.updateSize(sizeEntityParam);
            return ResponseEntity.ok(sizeEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("size/delete-size")
    public ResponseEntity<?> deleteSize(@RequestParam Integer idSize){

        try{
            sizeService.deleteSize(idSize);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
