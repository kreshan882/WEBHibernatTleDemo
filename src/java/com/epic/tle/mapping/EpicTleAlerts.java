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
 * EpicTleAlerts generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_ALERTS"
//    ,catalog="epictle_v5ch"
)
public class EpicTleAlerts  implements java.io.Serializable {


     private Integer sid;
     private EpicTleAlertType epicTleAlertType;
     private EpicTleRiskLevel epicTleRiskLevel;
     private EpicTleStatus epicTleStatus;
     private EpicTleTxntypes epicTleTxntypes;
     private EpicTleNodetype epicTleNodetype;
     private String tid;
     private String serialno;
     private String alertinformation;
     private Date datetime;
     private String clientIp;
     private String mid;
     private String responseCode;
     private String mti;
     private Integer tleStatus;
     private String cardBin;

    public EpicTleAlerts() {
    }

    public EpicTleAlerts(EpicTleAlertType epicTleAlertType, EpicTleRiskLevel epicTleRiskLevel, EpicTleStatus epicTleStatus, EpicTleTxntypes epicTleTxntypes, String tid, String serialno, String alertinformation, Date datetime, String clientIp, String mid, String responseCode, String mti, Integer tleStatus, String cardBin) {
       this.epicTleAlertType = epicTleAlertType;
       this.epicTleRiskLevel = epicTleRiskLevel;
       this.epicTleStatus = epicTleStatus;
       this.epicTleTxntypes = epicTleTxntypes;
       this.tid = tid;
       this.serialno = serialno;
       this.alertinformation = alertinformation;
       this.datetime = datetime;
       this.clientIp = clientIp;
       this.mid = mid;
       this.responseCode = responseCode;
       this.mti = mti;
       this.tleStatus = tleStatus;
       this.cardBin = cardBin;
    }
   
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
    @JoinColumn(name="NODE", nullable=false)
    public EpicTleNodetype getEpicTleNodetype() {
        return this.epicTleNodetype;
    }
    
    public void setEpicTleNodetype(EpicTleNodetype epicTleNodetype) {
        this.epicTleNodetype = epicTleNodetype;
    }

    
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ALERT_TYPE")
    public EpicTleAlertType getEpicTleAlertType() {
        return this.epicTleAlertType;
    }
    
    public void setEpicTleAlertType(EpicTleAlertType epicTleAlertType) {
        this.epicTleAlertType = epicTleAlertType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RISK_LEVEL")
    public EpicTleRiskLevel getEpicTleRiskLevel() {
        return this.epicTleRiskLevel;
    }
    
    public void setEpicTleRiskLevel(EpicTleRiskLevel epicTleRiskLevel) {
        this.epicTleRiskLevel = epicTleRiskLevel;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS")
    public EpicTleStatus getEpicTleStatus() {
        return this.epicTleStatus;
    }
    
    public void setEpicTleStatus(EpicTleStatus epicTleStatus) {
        this.epicTleStatus = epicTleStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TXN_TYPE")
    public EpicTleTxntypes getEpicTleTxntypes() {
        return this.epicTleTxntypes;
    }
    
    public void setEpicTleTxntypes(EpicTleTxntypes epicTleTxntypes) {
        this.epicTleTxntypes = epicTleTxntypes;
    }

    
    @Column(name="TID", length=8)
    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }

    
    @Column(name="SERIALNO", length=20)
    public String getSerialno() {
        return this.serialno;
    }
    
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    
    @Column(name="ALERTINFORMATION", length=150)
    public String getAlertinformation() {
        return this.alertinformation;
    }
    
    public void setAlertinformation(String alertinformation) {
        this.alertinformation = alertinformation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATETIME", length=19)
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    
    @Column(name="CLIENT_IP", length=16)
    public String getClientIp() {
        return this.clientIp;
    }
    
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    
    @Column(name="MID", length=16)
    public String getMid() {
        return this.mid;
    }
    
    public void setMid(String mid) {
        this.mid = mid;
    }

    
    @Column(name="RESPONSE_CODE", length=2)
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    
    @Column(name="MTI", length=4)
    public String getMti() {
        return this.mti;
    }
    
    public void setMti(String mti) {
        this.mti = mti;
    }

    
    @Column(name="TLE_STATUS")
    public Integer getTleStatus() {
        return this.tleStatus;
    }
    
    public void setTleStatus(Integer tleStatus) {
        this.tleStatus = tleStatus;
    }

    
    @Column(name="CARD_BIN", length=8)
    public String getCardBin() {
        return this.cardBin;
    }
    
    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    @Override
    public String toString() {
        return "EpicTleAlerts{" + "sid=" + sid + ", epicTleAlertType=" + epicTleAlertType.getCode() + ", epicTleRiskLevel=" + epicTleRiskLevel.getCode() + ", epicTleStatus=" + epicTleStatus.getCode() + ", epicTleTxntypes=" + epicTleTxntypes.getCode() + ", epicTleNodetype=" + epicTleNodetype.getCode() + ", tid=" + tid + ", serialno=" + serialno + ", alertinformation=" + alertinformation + ", datetime=" + datetime + ", clientIp=" + clientIp + ", mid=" + mid + ", responseCode=" + responseCode + ", mti=" + mti + ", tleStatus=" + tleStatus + ", cardBin=" + cardBin + '}';
    }




}


