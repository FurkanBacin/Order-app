package com.smartera.customer.service.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "This is auto generated customer id")
    @Column(name = "customer_id")
    private int id;

    @ApiModelProperty(value = "This is customer name")
    @Column(name = "customer_name")
    private String name;

    @ApiModelProperty(value = "This is customer authority")
    @Column(name = "customer_authority")
    private boolean authority;
}
