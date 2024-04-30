package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ql.vn.qlsp.entity.CatergoryEntity;
import ql.vn.qlsp.repository.CatergoryRepository;
import ql.vn.qlsp.repository.ProductCatergoryRepository;

import javax.transaction.Transactional;

@Service
public class CatergoryService {
    @Autowired
    CatergoryRepository catergoryRepository;

    public CatergoryEntity addCatergory(CatergoryEntity catergoryEntityParam) throws Exception {

        if (catergoryEntityParam.getId() != null){
            throw new Exception("dữ liệu catergory add không được có id");
        }

        if(catergoryRepository.findByName(catergoryEntityParam.getName()) !=null){
            throw new Exception("tên catergory đã tônf tại");
        }

        return catergoryRepository.save(catergoryEntityParam);

    }
    @Transactional
    public CatergoryEntity updateCatergory(CatergoryEntity catergoryEntityParam) throws Exception {

        if (catergoryEntityParam.getId() == null){
            throw new Exception("dữ liệu catergory phải có ");
        }
        CatergoryEntity catergoryEntity = catergoryRepository.findById(catergoryEntityParam.getId()).orElse(null);
        if( catergoryRepository.findByName(catergoryEntityParam.getName()) !=null){
            throw new Exception("tên catergory đã tônf tại");
        }
        if (catergoryEntity ==null){
            throw new Exception("id catergory không tìm thấy");
        }
        catergoryEntity.setName(catergoryEntityParam.getName());
        return catergoryEntity;

    }

    @Autowired
    ProductCatergoryRepository productCatergoryRepository;
    public void deleteCatergory(Integer idCatergory) throws Exception {
        if(productCatergoryRepository.existsByCatergoryId(idCatergory)){
            throw new Exception("catergory đang được dùng ");
        }
        catergoryRepository.deleteById(idCatergory);
    }
}
