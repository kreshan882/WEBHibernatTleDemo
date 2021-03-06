package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * EpicTleProfilePrivilage generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_PROFILE_PRIVILAGE"
//    ,catalog="epictle_v5ch"
)
public class EpicTleProfilePrivilage  implements java.io.Serializable {


     private Integer id;
     private EpicTleModule epicTleModule;
     private EpicTleSection epicTleSection;
     private EpicTleTask epicTleTask;
     private EpicTleUserProfile epicTleUserProfile;

    public EpicTleProfilePrivilage() {
    }

    public EpicTleProfilePrivilage(EpicTleModule epicTleModule, EpicTleSection epicTleSection, EpicTleTask epicTleTask, EpicTleUserProfile epicTleUserProfile) {
       this.epicTleModule = epicTleModule;
       this.epicTleSection = epicTleSection;
       this.epicTleTask = epicTleTask;
       this.epicTleUserProfile = epicTleUserProfile;
    }
   
    @Id
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters= {@Parameter(name="columnPram", value = "id")})
    @GeneratedValue(generator = "id_generator") 
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID")
    public EpicTleModule getEpicTleModule() {
        return this.epicTleModule;
    }
    
    public void setEpicTleModule(EpicTleModule epicTleModule) {
        this.epicTleModule = epicTleModule;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECTION_ID")
    public EpicTleSection getEpicTleSection() {
        return this.epicTleSection;
    }
    
    public void setEpicTleSection(EpicTleSection epicTleSection) {
        this.epicTleSection = epicTleSection;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TASK_ID")
    public EpicTleTask getEpicTleTask() {
        return this.epicTleTask;
    }
    
    public void setEpicTleTask(EpicTleTask epicTleTask) {
        this.epicTleTask = epicTleTask;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROFILE_ID")
    public EpicTleUserProfile getEpicTleUserProfile() {
        return this.epicTleUserProfile;
    }
    
    public void setEpicTleUserProfile(EpicTleUserProfile epicTleUserProfile) {
        this.epicTleUserProfile = epicTleUserProfile;
    }

    @Override
    public String toString() {
        return "EpicTleProfilePrivilage{" + "id=" + id + ", epicTleModule=" + epicTleModule.getCode() + ", epicTleSection=" + epicTleSection.getSectionId() + ", epicTleTask=" + epicTleTask.getTaskId() + ", epicTleUserProfile=" + epicTleUserProfile.getCode() + '}';
    }




}


