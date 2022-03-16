package com.haitoko.admin.produk;

import com.haitoko.share.entity.productModel;
import com.haitoko.admin.produk.productService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class productController {

    @Autowired
    private productService pService;
    
    @Autowired
    private categoryService cService;

    @GetMapping("/products")
    public String index(Model model){
        productModel pModel = new productModel();
        model.addAttribute("produkList", pService.listAll());
        
        return "produk_list";
    }

    @GetMapping("/updateProduk")
    public String perbarui(Model model){
        productModel pModel = new productModel();
        model.addAttribute("product", pModel);
        model.addAttribute("kategori", cService.listAll());
        
        return "form_perbarui_produk";
    }

    // controller untuk mengatur form tambah produk
    @GetMapping("/addProduk")
    public String formTmabahProduk(Model model){
        productModel pModel = new productModel();
        model.addAttribute("product", pModel);

        model.addAttribute("kategori", cService.listAll());

        return "form_tambah_produk";
    }

    @GetMapping("/view")
    public String viewPage(Integer id, Model model){
        productModel pModel = new productModel();
        model.addAttribute("product", pModel);
        model.addAttribute("product", pService.get(id));

        return "view_produk";
    }
    
    // tombol kembali
    @PostMapping("/back")
    public String batal(Model model){
        productModel pModel = new productModel();
        model.addAttribute("product", pModel);
        return "redirect:/products";
    }

    // tombol kirim form tambah produk
    @PostMapping("/addProduct")
    public String doAdd(productModel pModel){
        pService.save(pModel);
        return "redirect:/products";
    }

    // tombol kirim form perbarui produk
    @PostMapping("/updateProduct")
    public String doUpdate(productModel pModel, Model model){
        model.addAttribute("product", pModel);
        model.addAttribute("product", pService.listAll());

        pService.save(pModel);
        return "produk_list";
    }

    @GetMapping("/delete")
    public String delete(Integer id, productModel pModel){
        pService.delete(id);

        return "redirect:/products";
    }

}
