package com.example.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Person")

public class Person {

    @Id
    @Column (name="id")
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "address")
    private String address;
    @Column (name="postcode")
    private String postcode;
    @Column (name="age")
    private String age;
    @Column (name = "job")
    private String job;
    @Column (name = "email")
    private String email;
    @Column (name = "phoneno")
    private String phoneno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phoneno;
    }

    public void setPhone(String phone) {
        this.phoneno = phone;
    }

}
