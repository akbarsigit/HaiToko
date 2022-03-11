package com.haitoko.admin.produk;

import com.haitoko.share.entity.categoryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<categoryModel, Integer> {

    public Long countById(Integer id);	
    
}
