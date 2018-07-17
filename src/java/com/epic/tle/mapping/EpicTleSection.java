package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EpicTleSection generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_SECTION"
//    ,catalog="epictle_v5ch"
)
public class EpicTleSection  implements java.io.Serializable {


     private String sectionId;
     private EpicTleModule epicTleModule;
     private String sectionName;
     private String sectionUrl;
     private Set<EpicTleProfilePrivilage> epicTleProfilePrivilages = new HashSet<EpicTleProfilePrivilage>(0);
     private Set<EpicTleTask> epicTleTasks = new HashSet<EpicTleTask>(0);

    public EpicTleSection() {
    }

	
    public EpicTleSection(String sectionId) {
        this.sectionId = sectionId;
    }
    public EpicTleSection(String sectionId, EpicTleModule epicTleModule, String sectionName, String sectionUrl, Set<EpicTleProfilePrivilage> epicTleProfilePrivilages, Set<EpicTleTask> epicTleTasks) {
       this.sectionId = sectionId;
       this.epicTleModule = epicTleModule;
       this.sectionName = sectionName;
       this.sectionUrl = sectionUrl;
       this.epicTleProfilePrivilages = epicTleProfilePrivilages;
       this.epicTleTasks = epicTleTasks;
    }
   
     @Id 

    
    @Column(name="SECTION_ID", unique=true, nullable=false, length=4)
    public String getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID")
    public EpicTleModule getEpicTleModule() {
        return this.epicTleModule;
    }
    
    public void setEpicTleModule(EpicTleModule epicTleModule) {
        this.epicTleModule = epicTleModule;
    }

    
    @Column(name="SECTION_NAME", length=45)
    public String getSectionName() {
        return this.sectionName;
    }
    
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    
    @Column(name="SECTION_URL", length=45)
    public String getSectionUrl() {
        return this.sectionUrl;
    }
    
    public void setSectionUrl(String sectionUrl) {
        this.sectionUrl = sectionUrl;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="epicTleSection")
    public Set<EpicTleProfilePrivilage> getEpicTleProfilePrivilages() {
        return this.epicTleProfilePrivilages;
    }
    
    public void setEpicTleProfilePrivilages(Set<EpicTleProfilePrivilage> epicTleProfilePrivilages) {
        this.epicTleProfilePrivilages = epicTleProfilePrivilages;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="EPIC_TLE_SECTION_TASK", joinColumns = { 
        @JoinColumn(name="SECTION_ID", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="TASK_ID", nullable=false, updatable=false) })
    public Set<EpicTleTask> getEpicTleTasks() {
        return this.epicTleTasks;
    }
    
    public void setEpicTleTasks(Set<EpicTleTask> epicTleTasks) {
        this.epicTleTasks = epicTleTasks;
    }

    @Override
    public String toString() {
        return "EpicTleSection{" + "sectionId=" + sectionId + ", epicTleModule=" + epicTleModule.getCode() + ", sectionName=" + sectionName + ", sectionUrl=" + sectionUrl + ", epicTleProfilePrivilages=" + epicTleProfilePrivilages + ", epicTleTasks=" + epicTleTasks + '}';
    }




}

