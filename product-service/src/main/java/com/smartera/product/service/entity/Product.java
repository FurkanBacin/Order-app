package com.smartera.product.service.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value="This is auto generated product id")
    @Column(name="product_id")
    private int id;

    @ApiModelProperty(value="This is product name")
    @Column(name="product_name")
    private String name;



}
