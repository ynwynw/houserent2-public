package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FollowHouse implements Serializable {
    private Integer id;
    private Integer cid;
    private Integer houseid;
}
