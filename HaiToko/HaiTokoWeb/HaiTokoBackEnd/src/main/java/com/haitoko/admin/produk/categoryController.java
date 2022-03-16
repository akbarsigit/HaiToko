package com.haitoko.admin.produk;


import java.util.List;

import java.util.List;

import com.haitoko.share.entity.categoryModel;
import com.haitoko.share.entity.productModel;
import com.haitoko.admin.produk.categoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class categoryController {
    
    @Autowired
	private categoryService cService;

	@GetMapping("/categories")
	public String listKategori(Model model){
		List<categoryModel> cModel = cService.listAll();
		model.addAttribute("kategori", cModel);
		// model.addAttribute

		return "list_kategori";
	}

	// pergi ke form tambah kategori
	@GetMapping("/addKategori")
	public String goAdd(Model model){
		categoryModel cModel = new categoryModel();
		model.addAttribute("kategori", cModel);

		return "form_tambah_kategori";
	}
    
	// pergi ke form perbarui kategori
	@GetMapping("/editKategori/{id}")
	public String goUpdate(@PathVariable("id") Integer id, Model model){
		categoryModel cModel = new categoryModel();
		model.addAttribute("kategori", cModel);
		model.addAttribute("kategori", cService.findById(id));

		return "form_perbarui_kategori";
	}

	// tambahakan kategori
	@PostMapping("/addKategori")
	public String add(categoryModel cModel){
		cService.save(cModel);

		return "redirect:/";
	}

	// perbarui kategori
	@PostMapping("/updateKategori")
	public String update(categoryModel cModel){
		cService.update(cModel);

		return "redirect:/";
	}

	@GetMapping("/viewKategori/{id}")
	public String view(@PathVariable("id") Integer id, Model model){
		categoryModel cModel = new categoryModel();
		model.addAttribute("kategori", cModel);
		model.addAttribute("kategori", cService.findById(id));

		return "view_kategori";
	}

	@GetMapping("/addProduct")
	public String listKategoriAdd(Model model){
		// categoryModel cModel = new categoryModel();
		model.addAttribute("kategori", cService.listAll());

		return "form_tambah_product";
	}

	@GetMapping("/updateProduct")
	public String listKategoriUpdate(Model model){
		// categoryModel cModel = new categoryModel();
		model.addAttribute("kategori", cService.listAll());

		return "form_perbarui_product";
	}
}
