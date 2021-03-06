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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * EpicTleListeners generated by hbm2java
 */
@Entity
@Table(name="EPIC_TLE_LISTENERS"
//    ,catalog="epictle_v5ch"
    , uniqueConstraints = @UniqueConstraint(columnNames="LISTENER_PORT") 
)
public class EpicTleListeners  implements java.io.Serializable {


     private Integer listenerId;
     private EpicTleConnectiontypes epicTleConnectiontypes;
     private EpicTleHeaderFormats epicTleHeaderFormats;
     private EpicTleHeaderSize epicTleHeaderSize;
     private EpicTleStatus epicTleStatusByTpduStatus;
     private EpicTleStatus epicTleStatusByBindStatus;
     private EpicTleStatus epicTleStatusByStatus;
     private EpicTleStatus epicTleStatusByListenerKeepAliveStatus;
     private String listenerName;
     private Integer listenerPort=0;
     private Integer listenerTimeout;
     private EpicTleNodetype epicTleNodetype;
     private Date datetime;
     private String isoFile;
     private Integer numOfConnection;

    public EpicTleListeners() {
    }

	
    public EpicTleListeners(EpicTleHeaderFormats epicTleHeaderFormats) {
        this.epicTleHeaderFormats = epicTleHeaderFormats;
    }
    public EpicTleListeners(EpicTleConnectiontypes epicTleConnectiontypes, EpicTleHeaderFormats epicTleHeaderFormats, EpicTleHeaderSize epicTleHeaderSize, EpicTleStatus epicTleStatusByTpduStatus, EpicTleStatus epicTleStatusByBindStatus, EpicTleStatus epicTleStatusByStatus, EpicTleStatus epicTleStatusByListenerKeepAliveStatus, String listenerName, Integer listenerPort, Integer listenerTimeout, Date datetime, String isoFile) {
       this.epicTleConnectiontypes = epicTleConnectiontypes;
       this.epicTleHeaderFormats = epicTleHeaderFormats;
       this.epicTleHeaderSize = epicTleHeaderSize;
       this.epicTleStatusByTpduStatus = epicTleStatusByTpduStatus;
       this.epicTleStatusByBindStatus = epicTleStatusByBindStatus;
       this.epicTleStatusByStatus = epicTleStatusByStatus;
       this.epicTleStatusByListenerKeepAliveStatus = epicTleStatusByListenerKeepAliveStatus;
       this.listenerName = listenerName;
       this.listenerPort = listenerPort;
       this.listenerTimeout = listenerTimeout;
       this.datetime = datetime;
       this.isoFile = isoFile;
    }
   
    @Id
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters= {@Parameter(name="columnPram", value = "listenerId")})
    @GeneratedValue(generator = "id_generator")  
    @Column(name="LISTENER_ID", unique=true, nullable=false)
    public Integer getListenerId() {
        return this.listenerId;
    }
     
             
    public void setListenerId(Integer listenerId) {
        this.listenerId = listenerId;
    }

    @Column(name="NUM_OF_CONNECTIONS")
    public Integer getNumOfConnection() {
        return numOfConnection;
    }

    public void setNumOfConnection(Integer numOfConnection) {
        this.numOfConnection = numOfConnection;
    }
    
    

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LISTENER_CONNECTION_TYPE")
    public EpicTleConnectiontypes getEpicTleConnectiontypes() {
        return this.epicTleConnectiontypes;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NODE", nullable=false)
    public EpicTleNodetype getEpicTleNodetype() {
        return this.epicTleNodetype;
    }
    
    public void setEpicTleNodetype(EpicTleNodetype epicTleNodetype) {
        this.epicTleNodetype = epicTleNodetype;
    }
    
    public void setEpicTleConnectiontypes(EpicTleConnectiontypes epicTleConnectiontypes) {
        this.epicTleConnectiontypes = epicTleConnectiontypes;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LISTENER_HEADER_FORMAT", nullable=false)
    public EpicTleHeaderFormats getEpicTleHeaderFormats() {
        return this.epicTleHeaderFormats;
    }
    
    public void setEpicTleHeaderFormats(EpicTleHeaderFormats epicTleHeaderFormats) {
        this.epicTleHeaderFormats = epicTleHeaderFormats;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LISTENER_HEADER_SIZE")
    public EpicTleHeaderSize getEpicTleHeaderSize() {
        return this.epicTleHeaderSize;
    }
    
    public void setEpicTleHeaderSize(EpicTleHeaderSize epicTleHeaderSize) {
        this.epicTleHeaderSize = epicTleHeaderSize;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TPDU_STATUS")
    public EpicTleStatus getEpicTleStatusByTpduStatus() {
        return this.epicTleStatusByTpduStatus;
    }
    
    public void setEpicTleStatusByTpduStatus(EpicTleStatus epicTleStatusByTpduStatus) {
        this.epicTleStatusByTpduStatus = epicTleStatusByTpduStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BIND_STATUS")
    public EpicTleStatus getEpicTleStatusByBindStatus() {
        return this.epicTleStatusByBindStatus;
    }
    
    public void setEpicTleStatusByBindStatus(EpicTleStatus epicTleStatusByBindStatus) {
        this.epicTleStatusByBindStatus = epicTleStatusByBindStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS")
    public EpicTleStatus getEpicTleStatusByStatus() {
        return this.epicTleStatusByStatus;
    }
    
    public void setEpicTleStatusByStatus(EpicTleStatus epicTleStatusByStatus) {
        this.epicTleStatusByStatus = epicTleStatusByStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LISTENER_KEEP_ALIVE_STATUS")
    public EpicTleStatus getEpicTleStatusByListenerKeepAliveStatus() {
        return this.epicTleStatusByListenerKeepAliveStatus;
    }
    
    public void setEpicTleStatusByListenerKeepAliveStatus(EpicTleStatus epicTleStatusByListenerKeepAliveStatus) {
        this.epicTleStatusByListenerKeepAliveStatus = epicTleStatusByListenerKeepAliveStatus;
    }

    
    @Column(name="LISTENER_NAME", length=20)
    public String getListenerName() {
        return this.listenerName;
    }
    
    public void setListenerName(String listenerName) {
        this.listenerName = listenerName;
    }

    
    @Column(name="LISTENER_PORT")
    public Integer getListenerPort() {
        return this.listenerPort;
    }
    
    public void setListenerPort(Integer listenerPort) {
        this.listenerPort = listenerPort;
    }

    
    @Column(name="LISTENER_TIMEOUT")
    public Integer getListenerTimeout() {
        return this.listenerTimeout;
    }
    
    public void setListenerTimeout(Integer listenerTimeout) {
        this.listenerTimeout = listenerTimeout;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATETIME", length=19)
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    
    @Column(name="ISO_FILE", length=45)
    public String getIsoFile() {
        return this.isoFile;
    }
    
    public void setIsoFile(String isoFile) {
        this.isoFile = isoFile;
    }

    @Override
    public String toString() {
        try{
            return "{" + "ListenerId:" + listenerId + ", Connection types:" + epicTleConnectiontypes.getCode() + ", Header Formats:" + epicTleHeaderFormats.getCode() + ", Header Size:" + epicTleHeaderSize.getCode() + ", Tpdu Status:" + epicTleStatusByTpduStatus.getCode() + ", Bind Status:" + epicTleStatusByBindStatus.getCode() + ", Status:" + epicTleStatusByStatus.getCode() + ", Listener Keep Alive Status:" + epicTleStatusByListenerKeepAliveStatus.getCode() + ", Listener Name:" + listenerName + ", Listener Port:" + listenerPort + ", Listener Timeout:" + listenerTimeout + ", Node type:" + epicTleNodetype.getCode()  + ", ISO File:" + isoFile + ", Num Of Connection:" + numOfConnection + '}';
        }
        catch(Exception e){
            return "{" + "Listener Id:" + listenerId  + ", Listener Name:" + listenerName + ", Listener Port:" + listenerPort + ", Listener Timeout:" + listenerTimeout + ", ISO File:" + isoFile + ", Num Of Connection:" + numOfConnection + '}';
        }
    }
    
    
    public String forHistory() {
        try{
            return "{" + "ListenerId:" + listenerId + ", Connection types:" + epicTleConnectiontypes.getCode() + ", Header Formats:" + epicTleHeaderFormats.getCode() + ", Header Size:" + epicTleHeaderSize.getCode() + ", Tpdu Status:" + epicTleStatusByTpduStatus.getCode() + ", Bind Status:" + epicTleStatusByBindStatus.getCode() + ", Status:" + epicTleStatusByStatus.getCode() + ", Listener Keep Alive Status:" + epicTleStatusByListenerKeepAliveStatus.getCode() + ", Listener Name:" + listenerName + ", Listener Port:" + listenerPort + ", Listener Timeout:" + listenerTimeout + ", Node type:" + epicTleNodetype.getCode()  + ", ISO File:" + isoFile + ", Num Of Connection:" + numOfConnection + '}';
        }
        catch(Exception e){
            return "{" + "Listener Id:" + listenerId  + ", Listener Name:" + listenerName + ", Listener Port:" + listenerPort + ", Listener Timeout:" + listenerTimeout + ", ISO File:" + isoFile + ", Num Of Connection:" + numOfConnection + '}';
        }
    }




}


