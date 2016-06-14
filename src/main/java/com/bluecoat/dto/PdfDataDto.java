package com.bluecoat.dto;

/**
 * Created by nathan.fife on 4/22/2016.
 */
public class PdfDataDto {
    private String name;
    private Integer age;

    public PdfDataDto() {}

    public PdfDataDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
