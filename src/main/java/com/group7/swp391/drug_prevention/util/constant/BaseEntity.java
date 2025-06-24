package com.group7.swp391.drug_prevention.util.constant;
import com.group7.swp391.drug_prevention.util.SecurityUtil;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass // Specifies that this is a superclass and its mappings are applied to subclasses.
@EntityListeners(AuditingEntityListener.class) // Enables auditing for entities that extend this class.
@Getter
@Setter
public abstract class BaseEntity {


    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

    @PrePersist
    public void handleBeforeCreate(){
        this.createdAt = Instant.now();
        this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : null;
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = Instant.now();
        this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
    }
}