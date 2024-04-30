package ql.vn.qlsp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.dto.ProductEntityDto;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.repository.ProductRepository;
import ql.vn.qlsp.service.ProductService;
import ql.vn.qlsp.service.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("admin/product")
public class ProductAdmin {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UploadFile uploadFile;

    @PostMapping("add-product")
    public ResponseEntity<?> getProducts(@RequestBody ProductEntityDto productEntityDto){

        ProductEntity productEntity = new ProductEntity();
        try{
            productEntity = productService.addProduct(productEntityDto);

        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(productEntity);

    }
    @PostMapping("update-mainImg")
    public ResponseEntity<?> updateMainImg(@RequestParam("idProduct") Integer id,@RequestParam("multipartFile") MultipartFile multipartFile){
        try{
            ProductEntity productEntity = productService.updateMainImg(id,multipartFile);
            return ResponseEntity.ok(productEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("update-product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntityDto productEntityDto){
        try{
            ProductEntity productEntity = productService.updateProduct(productEntityDto);
            return ResponseEntity.ok(productEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("add-images")
    public ResponseEntity<?> addImages(@RequestParam("id") Integer idProduct,@RequestParam("multipartFile") MultipartFile multipartFile){


        try{
            ProductEntity productEntity = productService.addImages(idProduct,multipartFile);
            return ResponseEntity.ok(productEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("delete-images")
    public ResponseEntity<?> deleteImages( @RequestParam("idProduct") int id,@RequestParam("url") String url){


        try{
            ProductEntity productEntity = productService.deleteImgs(id, url);
            return ResponseEntity.ok(productEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("upload-file-image")
    public String uploadFileImage( @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {

        return uploadFile.uploadFile(multipartFile);

    }
    @PostMapping("remove-file-image-by-name")
    public String removeFIleImage( @RequestParam String fileName) throws IOException {
        return uploadFile.removeFile(fileName);
    }
}
