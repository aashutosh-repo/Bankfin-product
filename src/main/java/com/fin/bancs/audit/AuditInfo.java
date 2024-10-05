package com.fin.bancs.audit;

import java.time.Instant;

import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter 
public abstract class AuditInfo {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant lastModifiedDate;

    @Version
    @Column(name = "version")
    private Long version;

   }