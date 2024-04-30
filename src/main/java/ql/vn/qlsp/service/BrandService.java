package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.entity.BrandsEntity;
import ql.vn.qlsp.entity.CatergoryEntity;
import ql.vn.qlsp.repository.BrandsRepository;
import ql.vn.qlsp.repository.ProductCatergoryRepository;
import ql.vn.qlsp.repository.ProductRepository;

import javax.transaction.Transactional;

@Service
public class BrandService {
    @Autowired
    BrandsRepository brandsRepository;
    @Autowired
    UploadFile uploadFile;

    public BrandsEntity addBrand(BrandsEntity brandsEntityParam) throws Exception {

        if (brandsEntityParam.getId() != null){
            throw new Exception("dữ liệu brand add không được có id");
        }

        if(brandsRepository.existsByName(brandsEntityParam.getName())){
            throw new Exception("tên brand đã tônf tại");
        }

        return brandsRepository.save(brandsEntityParam);

    }



    @Transactional
    public BrandsEntity updateBrand(BrandsEntity brandsEntityParam) throws Exception {

        if (brandsEntityParam.getId() == null){
            throw new Exception("không có id brand");
        }


        BrandsEntity brandsEntity = brandsRepository.findById(brandsEntityParam.getId()).orElse(null);
        if (brandsEntity ==null){
            throw new Exception("id brand không tìm thấy");
        }
        brandsEntity.setName(brandsEntityParam.getName());
        return brandsEntity;

    }

    @Transactional
    public void updateImge(Integer idBrand, MultipartFile fileImg) throws Exception {
        BrandsEntity brandsEntity = brandsRepository.findById(idBrand).orElse(null);
        if(brandsEntity == null){
            throw new Exception("không tìm thấy brand để update image");
        }
        if (fileImg == null)
        {
            return;
        }
        if(brandsEntity.getImg()!=null){
            uploadFile.removeFile(brandsEntity.getImg());
        }
        brandsEntity.setImg(uploadFile.uploadFile(fileImg));
    }
    @Autowired
    ProductRepository productRepository;
    public void deleteBrand(Integer idBrand) throws Exception {
        if(productRepository.existsByBrandId(idBrand)){
            throw new Exception("brand đang được dùng ");
        }
        brandsRepository.deleteById(idBrand);
    }
}
