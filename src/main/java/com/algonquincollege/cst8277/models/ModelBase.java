/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: ModelBase.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 * @date 2019 10
 * 
 * @author Patty Mosher, Jack Tan, Chris Fortin-Cherryholme
 * @modified Nov 2019
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Abstract class that is base for all com.algonquincollege.cst8277.assignment3 @Entity classes
 */
@MappedSuperclass
public abstract class ModelBase {

    protected int id;
    protected int version;

    protected LocalDateTime createdDate, updatedDate;

    public ModelBase() {
    }
    
    @Transient
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     *
     * @return createdDate - the date the object was created
     */
    @Column(name="CREATED_DATE")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    /**
     * 
     * @param createdDate - the date the object was created
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * 
     * @return updatedDate - the date the object was updated
     */
    @Column(name="UPDATED_DATE")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    /**
     * 
     * @param updatedDate - the date the object was updated
     */
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    /**
     * Listener that sets the createdDate when the object is persisted to database
     */
    @PrePersist
    public void onPersist() {
        setCreatedDate(LocalDateTime.now());
    }
    /**
     * Listener that sets the updateDate when the object is updated to database
     */
    @PreUpdate
    public void onUpdate() {
        setUpdatedDate(LocalDateTime.now());
    }
    
}