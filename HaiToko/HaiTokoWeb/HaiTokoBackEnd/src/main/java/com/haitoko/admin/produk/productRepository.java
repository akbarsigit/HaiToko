package com.haitoko.admin.produk;

import com.haitoko.share.entity.productModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<productModel, Integer> {
    
}