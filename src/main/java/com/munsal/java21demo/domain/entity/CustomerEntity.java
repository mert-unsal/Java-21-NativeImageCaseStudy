package com.munsal.java21demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")}
)
@SequenceGenerator(name = "customer_seq_gen", allocationSize = 1, sequenceName = "customer_seq")
public class CustomerEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @Email
    @NotBlank
    @Size(max = 50)
    @Column(name= "email")
    private String email;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @Column(name= "address")
    private String address;

    @NotBlank
    @Column(name= "phone")
    private String phone;
}
