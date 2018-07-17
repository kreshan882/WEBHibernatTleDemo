package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EpicTleModule generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_MODULE"
//    ,catalog="epictle_v5ch"
)
public class EpicTleModule  implements java.io.Serializable {


     private String code;
     private String description;
     private Set<EpicTleProfilePrivilage> epicTleProfilePrivilages = new HashSet<EpicTleProfilePrivilage>(0);
     private Set<EpicTleSection> epicTleSections = new HashSet<EpicTleSection>(0);

    public EpicTleModule() {
    }

    public EpicTleModule(String code) {
        this.code = code;
    }

	
    public EpicTleModule(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public EpicTleModule(String code, String description, Set<EpicTleProfilePrivilage> epicTleProfilePrivilages, Set<EpicTleSection> epicTleSections) {
       this.code = code;
       this.description = description;
       this.epicTleProfilePrivilages = epicTleProfilePrivilages;
       this.epicTleSections = epicTleSections;
    }
   
     @Id 

    
    @Column(name="CODE", unique=true, nullable=false, length=2)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="DESCRIPTION", nullable=false, length=50)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="epicTleModule")
    public Set<EpicTleProfilePrivilage> getEpicTleProfilePrivilages() {
        return this.epicTleProfilePrivilages;
    }
    
    public void setEpicTleProfilePrivilages(Set<EpicTleProfilePrivilage> epicTleProfilePrivilages) {
        this.epicTleProfilePrivilages = epicTleProfilePrivilages;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="epicTleModule")
    public Set<EpicTleSection> getEpicTleSections() {
        return this.epicTleSections;
    }
    
    public void setEpicTleSections(Set<EpicTleSection> epicTleSections) {
        this.epicTleSections = epicTleSections;
    }

    @Override
    public String toString() {
        return "EpicTleModule{" + "code=" + code + ", description=" + description + ", epicTleProfilePrivilages=" + epicTleProfilePrivilages + ", epicTleSections=" + epicTleSections + '}';
    }




}


