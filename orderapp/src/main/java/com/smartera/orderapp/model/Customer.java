package com.smartera.orderapp.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="customers")
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

    public Customer() {
    }

    public Customer(int id, String name, boolean authority) {
        this.id = id;
        this.name = name;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authority=" + authority +
                '}';
    }
}
