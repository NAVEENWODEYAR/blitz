package com.gowri.blitz.modal;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    private Integer stId;
}
