package com.fin.bancs.audit;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter 
public abstract class AuditableEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    @LastModifiedBy
    @Column(insertable = false, updatable = false)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(insertable = false, updatable = false)
    private Instant lastModifiedDate;
   }