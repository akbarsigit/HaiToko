package com.haitoko.share.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="category")
public class categoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kategori_id;
    
    @Column(name = "kategori", nullable = false)
    private String kategori;

    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;


    public Integer getKategori_id() {
        return this.kategori_id;
    }

    public void setKategori_id(Integer kategori_id) {
        this.kategori_id = kategori_id;
    }

    
    public String getKategori() {
        return this.kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return this.deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    @Override
    public String toString() {
        return "{" +
            " kategori_id='" + getKategori_id() + "'" +
            ", kategori='" + getKategori() + "'" +
            ", deskripsi='" + getDeskripsi() + "'" +
            "}";
    }

}
