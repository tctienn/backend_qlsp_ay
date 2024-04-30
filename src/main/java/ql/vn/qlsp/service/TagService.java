package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ql.vn.qlsp.entity.TagEntity;
import ql.vn.qlsp.repository.ProductRepository;
import ql.vn.qlsp.repository.TagRepository;

import javax.transaction.Transactional;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public TagEntity addTag(TagEntity tagEntityParam) throws Exception {
        if(tagEntityParam.getId() != null){
            throw new Exception("dữ liệu tạo tag không được có id");
        }
        if(tagRepository.findByName(tagEntityParam.getName())!= null){
            throw new Exception("tiêu đề tag đã tồn tại");
        }
        return tagRepository.save(tagEntityParam);

    }


    @Transactional
    public TagEntity updateTag(TagEntity tagEntityParam) throws Exception {
        if(tagEntityParam.getId() == null){
            throw new Exception("dữ liệu tạo tag không  có id");
        }
        if(tagRepository.findByName(tagEntityParam.getName())!= null){
            throw new Exception("tiêu đề tag đã tồn tại");
        }
        TagEntity tagEntity = tagRepository.findById(tagEntityParam.getId()).orElse(null);

        if(tagEntity == null){
            throw new Exception("không timf thấy tag cần cập nhật");
        }
        tagEntity.setName(tagEntityParam.getName());
        return tagEntity;

    }

    @Autowired
    ProductRepository productRepository;
    public void deleteTag(Integer idTag) throws Exception {


        TagEntity tagEntity = tagRepository.findById(idTag).orElse(null);

        if(tagEntity == null){
            throw new Exception("không timf thấy tag cần ");
        }

        if(productRepository.existsByTagId(idTag)){
            throw new Exception("Tag đang được sử dụng");
        }

        tagRepository.deleteById(idTag);


    }

}
