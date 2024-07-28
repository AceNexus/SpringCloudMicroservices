package org.ecommerce.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account", nullable = false, length = 255)
    private String account;

    @Column(name = "password")
    @JsonIgnore
    private String password; // @JsonIgnore：隱藏密碼值，避免回傳給前端

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_modified_date", insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

}
