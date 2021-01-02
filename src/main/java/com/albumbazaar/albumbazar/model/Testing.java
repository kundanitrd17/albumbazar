package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import lombok.Data;

@Data
@Entity
public class Testing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "json")
    private String data1;

}
