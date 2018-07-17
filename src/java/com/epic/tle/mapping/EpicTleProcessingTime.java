package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * EpicTleProcessingTime generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_PROCESSING_TIME"
//    ,catalog="epictle_v5ch"
)
public class EpicTleProcessingTime  implements java.io.Serializable {


    private Integer id;
     private EpicTleStatus epicTleStatus;
     private EpicTleTxntypes epicTleTxntypes;
     private EpicTleNodetype epicTleNodetype;
     private int totalTime;
     private int hostTime;
     private Date datetime;
     private Integer tleTime;
     private String tid;
     private String responseCode;
     private String bin;
     private String traceNo;

    public EpicTleProcessingTime() {
    }

	
    public EpicTleProcessingTime(EpicTleStatus epicTleStatus, int totalTime, int hostTime, Date datetime, String responseCode) {
        this.epicTleStatus = epicTleStatus;
        this.totalTime = totalTime;
        this.hostTime = hostTime;
        this.datetime = datetime;
        this.responseCode = responseCode;
    }
    public EpicTleProcessingTime(EpicTleStatus epicTleStatus,EpicTleTxntypes epicTleTxntypes, int totalTime, int hostTime, Date datetime, Integer tleTime, String tid, String responseCode, String bin, String traceNo) {
       this.epicTleStatus = epicTleStatus;
       this.epicTleTxntypes = epicTleTxntypes;
       this.totalTime = totalTime;
       this.hostTime = hostTime;
       this.datetime = datetime;
       this.tleTime = tleTime;
       this.tid = tid;
       this.responseCode = responseCode;
       this.bin = bin;
       this.traceNo = traceNo;
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
    @JoinColumn(name="NODE", nullable=false)
    public EpicTleNodetype getEpicTleNodetype() {
        return this.epicTleNodetype;
    }
    
    public void setEpicTleNodetype(EpicTleNodetype epicTleNodetype) {
        this.epicTleNodetype = epicTleNodetype;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TLE_STATUS", nullable=false)
    public EpicTleStatus getEpicTleStatus() {
        return this.epicTleStatus;
    }
    
    public void setEpicTleStatus(EpicTleStatus epicTleStatus) {
        this.epicTleStatus = epicTleStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TXNTYPE")
    public EpicTleTxntypes getEpicTleTxntypes() {
        return this.epicTleTxntypes;
    }
    
    public void setEpicTleTxntypes(EpicTleTxntypes epicTleTxntypes) {
        this.epicTleTxntypes = epicTleTxntypes;
    }

    
    @Column(name="TOTAL_TIME", nullable=false)
    public int getTotalTime() {
        return this.totalTime;
    }
    
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    
    @Column(name="HOST_TIME", nullable=false)
    public int getHostTime() {
        return this.hostTime;
    }
    
    public void setHostTime(int hostTime) {
        this.hostTime = hostTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATETIME", nullable=false, length=19)
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    
    @Column(name="TLE_TIME")
    public Integer getTleTime() {
        return this.tleTime;
    }
    
    public void setTleTime(Integer tleTime) {
        this.tleTime = tleTime;
    }

    
    @Column(name="TID", length=8)
    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }

    
    @Column(name="RESPONSE_CODE", nullable=false, length=2)
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    
    @Column(name="BIN", length=8)
    public String getBin() {
        return this.bin;
    }
    
    public void setBin(String bin) {
        this.bin = bin;
    }

    
    @Column(name="TRACE_NO", length=6)
    public String getTraceNo() {
        return this.traceNo;
    }
    
    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    @Override
    public String toString() {
        return "EpicTleProcessingTime{" + "id=" + id + ", epicTleStatus=" + epicTleStatus.getCode() + ", epicTleTxntypes=" + epicTleTxntypes.getCode() + ", epicTleNodetype=" + epicTleNodetype.getCode() + ", totalTime=" + totalTime + ", hostTime=" + hostTime + ", datetime=" + datetime + ", tleTime=" + tleTime + ", tid=" + tid + ", responseCode=" + responseCode + ", bin=" + bin + ", traceNo=" + traceNo + '}';
    }


    
}


