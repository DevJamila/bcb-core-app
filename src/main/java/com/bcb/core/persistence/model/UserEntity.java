package com.bcb.core.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bcb_user")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "bcb_user_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bcb_user_user_id_seq")
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(name = "bcb_username")
    private String username;

    @Column(name = "bcb_password")
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerEntity customer;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
