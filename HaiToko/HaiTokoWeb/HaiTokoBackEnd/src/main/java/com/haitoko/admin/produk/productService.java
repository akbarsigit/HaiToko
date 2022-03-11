package com.haitoko.admin.produk;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haitoko.share.entity.productModel;
import com.haitoko.admin.produk.productRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class productService {
    @Autowired
    private productRepository productRepo;

    public List<productModel> listAll(){
        return productRepo.findAll();
    }

    public void save(productModel product) {
        productRepo.save(product);
    }

    public productModel get(Integer id) {
        Optional<productModel> product = productRepo.findById(id);

        if (!product.isPresent()) {
            return null;
        }
        return product.get();
    }

    public void delete(Integer id){
        productRepo.deleteById(id);
    }
}

