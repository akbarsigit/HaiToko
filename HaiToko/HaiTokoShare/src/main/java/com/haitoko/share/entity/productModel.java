package com.haitoko.share.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="products")
public class productModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "namaProduk", nullable = false)
    private String  namaProduk;

    @Column(name = "nomorProduk", nullable = false)
    private String nomorProduk;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private categoryModel kategori;

    @Column(name = "stok", nullable = false)
    private double stok;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public categoryModel getKategori() {
        return this.kategori;
    }

    public void setKategori(categoryModel kategori) {
        this.kategori = kategori;
    }

    public String getNamaProduk() {
        return this.namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getNomorProduk() {
        return this.nomorProduk;
    }

    public void setNomorProduk(String nomorProduk) {
        this.nomorProduk = nomorProduk;
    }

    public double getStok() {
        return this.stok;
    }

    public void setStok(double stok) {
        this.stok = stok;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", namaProduk='" + getNamaProduk() + "'" +
            ", nomorProduk='" + getNomorProduk() + "'" +
            ", kategori='" + getKategori() + "'" +
            ", stok='" + getStok() + "'" +
            "}";
    }
    

}