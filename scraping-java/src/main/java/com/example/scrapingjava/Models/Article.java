package com.example.scrapingjava.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "art")
public class Article {

    @Id
    private String id;
    private String title;
    private String othors;
    private String abstraText;
    private String publichedIn;
    private String date;
    private String DOI;
    private String location;

    public Article(String title, String othors, String abstraText, String publichedIn, String date, String DOI, String location) {
        this.title = title;
        this.othors = othors;
        this.abstraText = abstraText;
        this.publichedIn = publichedIn;
        this.date = date;
        this.DOI = DOI;
        this.location = location;
    }

    public Article() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOthors() {
        return othors;
    }

    public void setOthors(String othors) {
        this.othors = othors;
    }

    public String getAbstraText() {
        return abstraText;
    }

    public void setAbstraText(String abstraText) {
        this.abstraText = abstraText;
    }

    public String getPublichedIn() {
        return publichedIn;
    }

    public void setPublichedIn(String publichedIn) {
        this.publichedIn = publichedIn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
