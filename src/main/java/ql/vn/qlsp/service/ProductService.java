package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.dto.ProductEntityDto;
import ql.vn.qlsp.entity.ImagesEntity;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.repository.ImagesRepository;
import ql.vn.qlsp.repository.ProductRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private CheckDateNow checkDateNow;


    @Transactional // tạo 1 giao dịch nếu trường hợp hàm chạy có vấn đề rollback về trạng thái chưa chạy (
    public ProductEntity addProduct(ProductEntityDto productEntityDto) throws Exception {
        if(productEntityDto.getId()!= null)
        {
            return new ProductEntity();
        }
        productEntityDto.setCreateAt(checkDateNow.getDateNow());
        productEntityDto.setUpdateAt(checkDateNow.getDateNow());
        ProductEntity productEntity = new ProductEntity(productEntityDto);
//        System.out.println("datadto"+productEntityDto.toString());
        if(productRepository.save(productEntity)!=null){ // lưu product thành công

            return productRepository.save(productEntity);
        }

        throw new Exception("thêm sản phẩm không thành công");


    }

    public Page<ProductEntity> getProductByPageNumber(Pageable pageable){

        return productRepository.findAll(pageable);

    }

    public Page<ProductEntity> getProductPageByBrandId(int id, Pageable pageable){
        return productRepository.findAllByBrand_Id(id,pageable);
    }

    public Page<ProductEntity> getProductPageBytagId(int id, Pageable pageable){
        return productRepository.findAllByTag_Id(id,pageable);
    }

    public Page<ProductEntity> getProductByName(String name, Pageable pageable){
        return productRepository.findAllByNameContaining(name, pageable);
    }

    @Transactional
    public ProductEntity updateProduct(ProductEntityDto productEntityDto) throws Exception {
        ProductEntity productEntity = productRepository.findById(productEntityDto.getId()).orElse(null);
        if(productEntity==null)
        {
            throw new Exception("không tìm thấy product id");
        }

        productEntityDto.setUpdateAt(checkDateNow.getDateNow());
        return productRepository.save(new ProductEntity(productEntityDto));


    }

    @Autowired
    UploadFile uploadFile;

    @Transactional
    public ProductEntity updateMainImg(Integer idProduct , MultipartFile multipartFile) throws Exception {
        ProductEntity productEntity = productRepository.findById(idProduct).orElse(null);
        if(productEntity==null)
        {
            throw new Exception("không tìm thấy product id dể update img");
        }
        try{
            uploadFile.removeFile(productEntity.getMainImg());
        }catch (Exception ex){
            System.out.println("xóa ảnh trên cloud thất bại:"+ productEntity.getMainImg() );
        }
         productEntity.setMainImg(uploadFile.uploadFile(multipartFile));
        return productEntity;

    }

    @Transactional
    public ProductEntity addImages(Integer idProduct , MultipartFile multipartFile) throws Exception {
        ProductEntity productEntity = productRepository.findById(idProduct).orElse(null);
        if(productEntity==null)
        {
            throw new Exception("không tìm thấy product id dể update img");
        }
        ImagesEntity imagesEntity = new ImagesEntity();
        imagesEntity.setIdProduct(idProduct);
        String url = uploadFile.uploadFile(multipartFile);
        imagesEntity.setUrl(url);
        imagesRepository.save(imagesEntity);
        return productRepository.findById(idProduct).orElse(productEntity);

    }
    @Transactional
    public ProductEntity deleteImgs(Integer idProduct , String url) throws Exception {
        ProductEntity productEntity = productRepository.findById(idProduct).orElse(null);
        if(productEntity==null)
        {
            throw new Exception("không tìm thấy product id dể delete img");
        }
        ImagesEntity imagesEntity = imagesRepository.findByIdProductAndUrl(idProduct,url);
        if(imagesEntity==null){
            throw new Exception("không tìm thấy ảnh cần xóa");
        }

        imagesRepository.delete(imagesEntity);
        return productRepository.findById(idProduct).orElse(productEntity);
    }


}
