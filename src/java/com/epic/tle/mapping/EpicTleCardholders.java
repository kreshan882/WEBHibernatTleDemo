package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * EpicTleCardholders generated by hbm2java
 */
@Entity
@Table(name = "EPIC_TLE_CARDHOLDERS" //    ,catalog="epictle_v5ch"
        ,
         uniqueConstraints = @UniqueConstraint(columnNames = "SID")
)
public class EpicTleCardholders implements java.io.Serializable {

    private String serialno;
    private EpicTleAlgorithem epicTleAlgorithem;
    private EpicTlePinverficationmethod epicTlePinverficationmethod;
    private EpicTleSelecteddevice epicTleSelecteddevice;
    private EpicTleStatus epicTleStatus;
    private int sid;
    private String officername;
    private String bankname;
    private String location;
    private Date regdate;
    private Integer maxtmkdownlod = 0;
    private Integer maxcountor = 0;
    private String pinblock = "1010101010101010";
    private String modules = "10101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010";
    private String efwk = "10101010101010101010101010101010";
    private Integer maxpincountor = 0;
    private Date lastupdatedate;
    private String exponent = "101010";
    private String countor = "000000";
    private String kvc = "000000";
    private int bdkid;

    public EpicTleCardholders() {
    }

    public EpicTleCardholders(int sid, Date lastupdatedate) {
        this.sid = sid;
        this.lastupdatedate = lastupdatedate;
    }

    public EpicTleCardholders(EpicTleAlgorithem epicTleAlgorithem, EpicTlePinverficationmethod epicTlePinverficationmethod, EpicTleSelecteddevice epicTleSelecteddevice, EpicTleStatus epicTleStatus, int sid, String officername, String bankname, String location, Date regdate, Integer maxtmkdownlod, Integer maxcountor, String pinblock, String modules, String efwk, Integer maxpincountor, Date lastupdatedate, String exponent, String countor, String kvc) {
        this.epicTleAlgorithem = epicTleAlgorithem;
        this.epicTlePinverficationmethod = epicTlePinverficationmethod;
        this.epicTleSelecteddevice = epicTleSelecteddevice;
        this.epicTleStatus = epicTleStatus;
        this.sid = sid;
        this.officername = officername;
        this.bankname = bankname;
        this.location = location;
        this.regdate = regdate;
        this.maxtmkdownlod = maxtmkdownlod;
        this.maxcountor = maxcountor;
        this.pinblock = pinblock;
        this.modules = modules;
        this.efwk = efwk;
        this.maxpincountor = maxpincountor;
        this.lastupdatedate = lastupdatedate;
        this.exponent = exponent;
        this.countor = countor;
        this.kvc = kvc;
    }

    @Id
    @Column(name = "SERIALNO", unique = true, nullable = false, length = 16)
    public String getSerialno() {
        return this.serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALGORITHEM")
    public EpicTleAlgorithem getEpicTleAlgorithem() {
        return this.epicTleAlgorithem;
    }

    public void setEpicTleAlgorithem(EpicTleAlgorithem epicTleAlgorithem) {
        this.epicTleAlgorithem = epicTleAlgorithem;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PINVERFICATION")
    public EpicTlePinverficationmethod getEpicTlePinverficationmethod() {
        return this.epicTlePinverficationmethod;
    }

    public void setEpicTlePinverficationmethod(EpicTlePinverficationmethod epicTlePinverficationmethod) {
        this.epicTlePinverficationmethod = epicTlePinverficationmethod;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELECTEDDEVICE")
    public EpicTleSelecteddevice getEpicTleSelecteddevice() {
        return this.epicTleSelecteddevice;
    }

    public void setEpicTleSelecteddevice(EpicTleSelecteddevice epicTleSelecteddevice) {
        this.epicTleSelecteddevice = epicTleSelecteddevice;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS")
    public EpicTleStatus getEpicTleStatus() {
        return this.epicTleStatus;
    }

    public void setEpicTleStatus(EpicTleStatus epicTleStatus) {
        this.epicTleStatus = epicTleStatus;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SID", unique = true, nullable = false)
    public int getSid() {
        return this.sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Column(name = "OFFICERNAME", length = 50)
    public String getOfficername() {
        return this.officername;
    }

    public void setOfficername(String officername) {
        this.officername = officername;
    }

    @Column(name = "BANKNAME", length = 50)
    public String getBankname() {
        return this.bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    @Column(name = "LOCATION", length = 80)
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REGDATE", length = 10)
    public Date getRegdate() {
        return this.regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Column(name = "MAXTMKDOWNLOD", columnDefinition = "int default 0")
    public Integer getMaxtmkdownlod() {
        return this.maxtmkdownlod;
    }

    public void setMaxtmkdownlod(Integer maxtmkdownlod) {
        this.maxtmkdownlod = maxtmkdownlod;
    }

    @Column(name = "MAXCOUNTOR", columnDefinition = "int default 0")
    public Integer getMaxcountor() {
        return this.maxcountor;
    }

    public void setMaxcountor(Integer maxcountor) {
        this.maxcountor = maxcountor;
    }

    @Column(name = "PINBLOCK", length = 16)
    public String getPinblock() {
        return this.pinblock;
    }

    public void setPinblock(String pinblock) {
        this.pinblock = pinblock;
    }

    @Column(name = "MODULES", length = 128)
    public String getModules() {
        return this.modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    @Column(name = "EFWK", length = 32)
    public String getEfwk() {
        return this.efwk;
    }

    public void setEfwk(String efwk) {
        this.efwk = efwk;
    }

    @Column(name = "MAXPINCOUNTOR", columnDefinition = "int default 0")
    public Integer getMaxpincountor() {
        return this.maxpincountor;
    }

    public void setMaxpincountor(Integer maxpincountor) {
        this.maxpincountor = maxpincountor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATEDATE", nullable = false, length = 19)
    public Date getLastupdatedate() {
        return this.lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    @Column(name = "EXPONENT", length = 6)
    public String getExponent() {
        return this.exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }

    @Column(name = "COUNTOR", length = 6)
    public String getCountor() {
        return this.countor;
    }

    public void setCountor(String countor) {
        this.countor = countor;
    }

    @Column(name = "KVC", length = 6)
    public String getKvc() {
        return this.kvc;
    }

    public void setKvc(String kvc) {
        this.kvc = kvc;
    }

    @Override
    public String toString() {
        try {
            return "{" + "Serial No:" + serialno + ", Algorithem:" + epicTleAlgorithem.getCode() + ", Pin Verfication Method:" + epicTlePinverficationmethod.getCode() + ", Selected Device:" + epicTleSelecteddevice.getCode() + ", Status:" + epicTleStatus.getCode() + ", Sid:" + sid + ", Officer Name:" + officername + ", Bank Name:" + bankname + ", Location:" + location + ", Register date:" + regdate + ", Max Terminal Key Downlod:" + maxtmkdownlod + ", Max Countor:" + maxcountor + ", Pin Block:" + pinblock + ", Modules:" + modules + ", efwk:" + efwk + ", Max Pin countor:" + maxpincountor + ", Exponent:" + exponent + ", Countor:" + countor + ", KVC :" + kvc +", bdk id:" + bdkid+ '}';
        } catch (Exception e) {
            return "{" + "Serial No:" + serialno + ", Sid:" + sid + ", Officer Name:" + officername + ", Bank Name:" + bankname + ", Location:" + location + ", Register date:" + regdate + ", Max Terminal Key Downlod::" + maxtmkdownlod + ", Max Countor:" + maxcountor + ", Pin Block:" + pinblock + ", Modules:" + modules + ", efwk:" + efwk + ", Max Pin Countor:" + maxpincountor + ", Exponent:" + exponent + ", Countor:" + countor + ", KVC:" + kvc + ", bdk id:" + bdkid+'}';
        }
    }

    public String forHistory() {
        try {
            return "{" + "Serial No:" + serialno + ", Algorithem:" + epicTleAlgorithem.getCode() + ", Pin Verfication Method:" + epicTlePinverficationmethod.getCode() + ", Selected Device:" + epicTleSelecteddevice.getCode() + ", Status:" + epicTleStatus.getCode() + ", Sid:" + sid + ", Officer Name:" + officername + ", Bank Name:" + bankname + ", Location:" + location + ", Register date:" + regdate + ", Max Terminal Key Downlod:" + maxtmkdownlod + ", Max Countor:" + maxcountor + ", Pin Block:" + pinblock + ", Modules:" + modules + ", efwk:" + efwk + ", Max Pin countor:" + maxpincountor + ", Exponent:" + exponent + ", Countor:" + countor + ", KVC :" + kvc +", bdk id:" + bdkid+ '}';
        } catch (Exception e) {
            return "{" + "Serial No:" + serialno + ", Sid:" + sid + ", Officer Name:" + officername + ", Bank Name:" + bankname + ", Location:" + location + ", Register date:" + regdate + ", Max Terminal Key Downlod::" + maxtmkdownlod + ", Max Countor:" + maxcountor + ", Pin Block:" + pinblock + ", Modules:" + modules + ", efwk:" + efwk + ", Max Pin Countor:" + maxpincountor + ", Exponent:" + exponent + ", Countor:" + countor + ", KVC:" + kvc +", bdk id:" + bdkid+ '}';
        }
    }

    @Column(name = "BDKID", length = 11)
    public int getBdkid() {
        return bdkid;
    }

    public void setBdkid(int bdkid) {
        this.bdkid = bdkid;
    }

}