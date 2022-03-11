package com.haitoko.admin.produk;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haitoko.share.entity.categoryModel;
import com.haitoko.admin.produk.categoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class categoryService{

    @Autowired
    private categoryRepository cRepository;

    public List<categoryModel> listAll(){
        return (List<categoryModel>) cRepository.findAll();
    }

    public void delete(Integer id){
        cRepository.deleteById(id);
    }

    public void save(categoryModel cModel){
        cRepository.save(cModel);
    }

    public void update(categoryModel cModel){
        cRepository.save(cModel);
    }

    public Optional<categoryModel> findById(Integer id){
        return cRepository.findById(id);
    }
    
}
