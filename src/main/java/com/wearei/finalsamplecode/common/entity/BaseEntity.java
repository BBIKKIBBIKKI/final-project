package com.wearei.finalsamplecode.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
}