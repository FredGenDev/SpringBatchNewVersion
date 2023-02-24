package com.example.SpringBatchNewVersion.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyAppItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String valueStr;
    private Long valueLong1;
    private Long valueLong2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public Long getValueLong1() {
        return valueLong1;
    }

    public void setValueLong1(Long valueLong1) {
        this.valueLong1 = valueLong1;
    }

    public Long getValueLong2() {
        return valueLong2;
    }

    public void setValueLong2(Long valueLong2) {
        this.valueLong2 = valueLong2;
    }
}
