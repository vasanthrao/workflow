package com.metaverse.workflow.login.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="user")
public class UserEntity {

    @Id
    @Column(name="user_id")
    private Long userId;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="user_role")
    private String userRole;
    @Column(name="attempts")
    private Integer attempts;
    @Column(name="status")
    private String status;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="gender")
    private String gender;
    @Column(name="mobile_no")
    private Long mobileNo;
    @Column(name="address")
    private String address;

}
