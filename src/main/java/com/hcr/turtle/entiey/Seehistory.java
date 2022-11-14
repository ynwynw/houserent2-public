package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Seehistory implements Serializable {
    private Integer id;
    private Integer renter;
    private Integer houseid;
    private Object seetime;
    private Integer state;
    private String sstate;
    private String cusdesc;
    private String cname;
    private String cphone;
    private String ccard;
    private Integer csex;
    private Integer hrent;
}
