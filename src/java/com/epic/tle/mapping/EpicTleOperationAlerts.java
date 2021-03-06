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
 * EpicTleOperationAlerts generated by hbm2java
 */
@Entity
@Table(name = "EPIC_TLE_OPERATION_ALERTS"
//        , catalog = "epictle_v5ch"
)
public class EpicTleOperationAlerts implements java.io.Serializable {

    private int operationId;
    private EpicTleStatus epicTleStatus;
    private EpicTleSysOperation epicTleSysOperation;
    private EpicTleNodetype epicTleNodetype;
    private String username;
    private String ip;
    private Date datetime;
    private String message;

    public EpicTleOperationAlerts() {
    }

    public EpicTleOperationAlerts(int operationId) {
        this.operationId = operationId;
    }

    public EpicTleOperationAlerts(int operationId, EpicTleStatus epicTleStatus, EpicTleSysOperation epicTleSysOperation, String username, String ip, Date datetime, String message) {
        this.operationId = operationId;
        this.epicTleStatus = epicTleStatus;
        this.epicTleSysOperation = epicTleSysOperation;
        this.username = username;
        this.ip = ip;
        this.datetime = datetime;
        this.message = message;
    }

    @Id
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters= {@Parameter(name="columnPram", value = "operationId")})
    @GeneratedValue(generator = "id_generator") 
    @Column(name = "OPERATION_ID", unique = true, nullable = false)
    public int getOperationId() {
        return this.operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NODE", nullable=false)
    public EpicTleNodetype getEpicTleNodetype() {
        return this.epicTleNodetype;
    }
    
    public void setEpicTleNodetype(EpicTleNodetype epicTleNodetype) {
        this.epicTleNodetype = epicTleNodetype;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS")
    public EpicTleStatus getEpicTleStatus() {
        return this.epicTleStatus;
    }

    public void setEpicTleStatus(EpicTleStatus epicTleStatus) {
        this.epicTleStatus = epicTleStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATION")
    public EpicTleSysOperation getEpicTleSysOperation() {
        return this.epicTleSysOperation;
    }

    public void setEpicTleSysOperation(EpicTleSysOperation epicTleSysOperation) {
        this.epicTleSysOperation = epicTleSysOperation;
    }

    @Column(name = "USERNAME", length = 50)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "IP", length = 20)
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETIME", length = 19)
    public Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Column(name = "MESSAGE", length = 200)
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EpicTleOperationAlerts{" + "operationId=" + operationId + ", epicTleStatus=" + epicTleStatus.getCode() + ", epicTleSysOperation=" + epicTleSysOperation.getCode() + ", epicTleNodetype=" + epicTleNodetype.getCode() + ", username=" + username + ", ip=" + ip + ", datetime=" + datetime + ", message=" + message + '}';
    }

    
}
