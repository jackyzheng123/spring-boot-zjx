package com.zjx.cache.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description 分页公共属性DTO
 * @Author Carson Cheng
 * @Date 2020/1/17 15:42
 * @Version V1.0
 **/
@Data
@ToString
public class PageQueryDTO implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private Order order;

    @Data
    public static class Order {
        private String orderBy;
        private Boolean isDesc;
    }
}
