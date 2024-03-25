package com.onlinestore.onlinestore.Models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue( generator = "Generic")
    @GenericGenerator(name = "Generic", strategy = "uuid2")
    @Column(name = "Id1",columnDefinition = "binary(16)" ,nullable = false, updatable = false)
    private UUID id;
}
