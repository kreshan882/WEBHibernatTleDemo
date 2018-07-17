package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * EpicTleHistory generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_HISTORY"
//    ,catalog="epictle_v5ch"
)
public class EpicTleHistory  implements java.io.Serializable {


     private Integer sid;
     private EpicTleTask epicTleTask;
     private EpicTleUser epicTleUser;   
     private EpicTleUserProfile epicTleUserProfile;
     private String module;
     private String remark;
     private Date datetime;
     private String location;
     private String tid;
     private String mid;
     private String serialno;
     private EpicTleNodetype epicTleNodetype;
     private String section;
     private String oldValue;
     private String newValue;


    public EpicTleHistory() {
    }

	
//    public EpicTleHistory(EpicTleTask epicTleTask, EpicTleUserProfile epicTleUserProfile, String module, String remark) {
//        this.epicTleTask = epicTleTask;
//        this.epicTleUserProfile = epicTleUserProfile;
//        this.module = module;
//        this.remark = remark;
//    }
//    
     public EpicTleHistory(EpicTleTask epicTleTask, EpicTleUserProfile epicTleUserProfile, String module, String remark,String section, String oldValue, String newValue) {
        this.epicTleTask = epicTleTask;
        this.epicTleUserProfile = epicTleUserProfile;
        this.module = module;
        this.remark = remark;
        this.section = section;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
//    public EpicTleHistory(EpicTleTask epicTleTask, EpicTleUser epicTleUser, EpicTleUserProfile epicTleUserProfile, String module, String remark, Date datetime, String location, String tid, String mid, String serialno) {
//       this.epicTleTask = epicTleTask;
//       this.epicTleUser = epicTleUser;
//       this.epicTleUserProfile = epicTleUserProfile;
//       this.module = module;
//       this.remark = remark;
//       this.datetime = datetime;
//       this.location = location;
//       this.tid = tid;
//       this.mid = mid;
//       this.serialno = serialno;
//    }
    
     public EpicTleHistory(EpicTleTask epicTleTask, EpicTleUser epicTleUser, EpicTleUserProfile epicTleUserProfile, String module, String remark, Date datetime, String location, String tid, String mid, String serialno,String section, String oldValue, String newValue) {
       this.epicTleTask = epicTleTask;
       this.epicTleUser = epicTleUser;
       this.epicTleUserProfile = epicTleUserProfile;
       this.module = module;
       this.remark = remark;
       this.datetime = datetime;
       this.location = location;
       this.tid = tid;
       this.mid = mid;
       this.serialno = serialno;
       this.section = section;
       this.oldValue = oldValue;
       this.newValue = newValue;
    }
    
     @TableGenerator(
            name="histGen")

   
    @Id 
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters= {@Parameter(name="columnPram", value = "sid")})
    @GeneratedValue(generator = "id_generator")   
    @Column(name="SID", unique=true, nullable=false)
    public Integer getSid() {
        return this.sid;
    }
    
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OPERATION", nullable=false)
    public EpicTleTask getEpicTleTask() {
        return this.epicTleTask;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NODE", nullable=false)
    public EpicTleNodetype getEpicTleNodetype() {
        return this.epicTleNodetype;
    }
    
    public void setEpicTleNodetype(EpicTleNodetype epicTleNodetype) {
        this.epicTleNodetype = epicTleNodetype;
    }
    
    public void setEpicTleTask(EpicTleTask epicTleTask) {
        this.epicTleTask = epicTleTask;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WEBUSER")
    public EpicTleUser getEpicTleUser() {
        return this.epicTleUser;
    }
    
    public void setEpicTleUser(EpicTleUser epicTleUser) {
        this.epicTleUser = epicTleUser;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USERCODE", nullable=true)
    public EpicTleUserProfile getEpicTleUserProfile() {
        return this.epicTleUserProfile;
    }
    
    public void setEpicTleUserProfile(EpicTleUserProfile epicTleUserProfile) {
        this.epicTleUserProfile = epicTleUserProfile;
    }

    
    @Column(name="MODULE", nullable=false, length=2)
    public String getModule() {
        return this.module;
    }
    
    public void setModule(String module) {
        this.module = module;
    }

    
    @Column(name="REMARK", nullable=false, length=150)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATETIME", length=19)
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    
    @Column(name="LOCATION", length=50)
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    
    @Column(name="TID", length=8)
    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }

    
    @Column(name="MID", length=15)
    public String getMid() {
        return this.mid;
    }
    
    public void setMid(String mid) {
        this.mid = mid;
    }

    
    @Column(name="SERIALNO", length=16)
    public String getSerialno() {
        return this.serialno;
    }
    
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    @Override
    public String toString() {
        return "EpicTleHistory{" + "sid=" + sid + ", epicTleTask=" + epicTleTask.getTaskId() + ", epicTleUser=" + epicTleUser.getUserid() + ", epicTleUserProfile=" + epicTleUserProfile.getCode() + ", module=" + module + ", remark=" + remark + ", datetime=" + datetime + ", location=" + location + ", tid=" + tid + ", mid=" + mid + ", serialno=" + serialno + ", epicTleNodetype=" + epicTleNodetype.getCode() + '}';
    }

    @Column(name="SECTION" , length=4)
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Column(name="OLD_VALUE")
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Column(name="NEW_VALUE")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}

