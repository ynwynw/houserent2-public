package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class HouseImgs implements Serializable {
    private Integer id;
    private String houseid;//房屋id
    private String houseimg;//房屋组图路径
}
