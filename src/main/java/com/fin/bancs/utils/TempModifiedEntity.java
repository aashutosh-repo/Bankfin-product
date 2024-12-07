package com.fin.bancs.utils;

import java.sql.Blob;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TempModifiedEntity")
public class TempModifiedEntity {
	@Id
    @Column(name = "modified_key", nullable = false, unique = true)
    private String modifiedKey;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";  // Default value for status

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Lob
    @Column(name = "modified_data", nullable = false)
    private Blob modifiedData;

    @Column(name = "modified_date", nullable = false)
    private LocalDate modifiedDate;
}