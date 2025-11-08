package com.assessment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
public class Doctor extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
}
