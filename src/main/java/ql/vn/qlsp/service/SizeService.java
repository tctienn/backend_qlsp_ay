package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.entity.BrandsEntity;
import ql.vn.qlsp.entity.SizeEntity;
import ql.vn.qlsp.repository.ProductRepository;
import ql.vn.qlsp.repository.ProductSizeRepository;
import ql.vn.qlsp.repository.SizeRepository;

import javax.transaction.Transactional;

@Service
public class SizeService {

    @Autowired
    SizeRepository sizeRepository;

    public SizeEntity addSize(SizeEntity sizeEntityParam) throws Exception {

        if (sizeEntityParam.getId() != null){
            throw new Exception("dữ liệu size add không được có id");
        }

        if(sizeRepository.existsByName(sizeEntityParam.getName())){
            throw new Exception("tên siz đã tồn ");
        }

        return sizeRepository.save(sizeEntityParam);

    }



    @Transactional
    public SizeEntity updateSize(SizeEntity sizeEntityParam) throws Exception {

        if (sizeEntityParam.getId() == null){
            throw new Exception("không có id size");
        }
        if(sizeRepository.existsByName(sizeEntityParam.getName())){
            throw new Exception("tên siz đã tồn ");
        }

        SizeEntity sizeEntity = sizeRepository.findById(sizeEntityParam.getId()).orElse(null);
        if (sizeEntity ==null){
            throw new Exception("không tìm thấy size item");
        }
        sizeEntity.setName(sizeEntityParam.getName());
        return sizeEntity;

    }


    @Autowired
    ProductSizeRepository productSizeRepository;

    public void deleteSize(Integer idSize) throws Exception {
        if(productSizeRepository.existsBySizeId(idSize)){
            throw new Exception(" size đang được dùng ");
        }
        sizeRepository.deleteById(idSize);
    }

}
