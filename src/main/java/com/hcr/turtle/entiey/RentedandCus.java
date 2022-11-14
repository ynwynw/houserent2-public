package com.hcr.turtle.entiey;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RentedandCus implements Serializable {
    private String cname;
    private String cphone;
    private String ccard;
    private Object begintime;
    private Object endtime;
    private Double charge;

}
