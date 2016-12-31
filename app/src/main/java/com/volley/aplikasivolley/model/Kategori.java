package com.volley.aplikasivolley.model;

/**
 * Created by ahmad on 31/12/2015.
 */
public class Kategori {

    private String kId;
    private String kName;
    private String kImage;
    private String author;
    private String status;

    public Kategori() {
    }

    public Kategori(String kId, String kName, String kImage, String author, String status) {
        this.kId = kId;
        this.kName = kName;
        this.kImage = kImage;
        this.author = author;
        this.status = status;
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public String getkImage() {
        return kImage;
    }

    public void setkImage(String kImage) {
        this.kImage = kImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    "cid": "6",
//            "category_name": "Pengampunan Pajak",
//            "category_image": "4052-2016-12-30.jpg",
//            "author": "Sasmito",
//            "status": "1"
}
