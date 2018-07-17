/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.bean;

/**
 *
 * @author kreshan
 */
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Thilina Saranga Gajanayake
 * @Date Jul 15, 2010
 */
public class FieldEngineer implements Serializable {

    private String SID;
    private String SERIALNO;      // varchar(16) collate latin1_general_ci NOT NULL default '',
    private String OFFICERNAME;   // varchar(50) collate latin1_general_ci default NULL,
    private String BANKNAME;      // varchar(50) collate latin1_general_ci default NULL,
    private String LOCATION;      // varchar(80) collate latin1_general_ci default NULL,
    private int SELECTEDDEVICE;// int(1) default '0',  
    private Date REGDATE;       // datetime default '0000-00-00 00:00:00',
    private int MAXTMKDOWNLOD; // int(5) default '0',
    private int MAXCOUNTOR;    // int(5) default '0',
    private int ALGORITHEM;    // int(1) default '0',
    private String PINBLOCK;      // varchar(16) collate latin1_general_ci default '1010101010101010',
    private String MODULES;       // varchar(128) collate latin1_general_ci default '10101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010',
    private String EFWK;          // varchar(32) collate latin1_general_ci default '10101010101010101010101010101010',
    private int MAXPINCOUNTOR; // int(1) character set latin1 default NULL,
    private int STATUS;        // int(1) default '0',
    private int PINVERFICATION;// int(1) default '0',
    private String EXPONENT;      // varchar(6) collate latin1_general_ci default '0',
    private int COUNTOR;       // varchar(6) collate latin1_general_ci default NULL,
    private String KVC;           // varchar(6) collate latin1_general_ci default NULL,
    private int BDKINDEX;

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public int getALGORITHEM() {
        return ALGORITHEM;
    }

    public void setALGORITHEM(int ALGORITHEM) {
        this.ALGORITHEM = ALGORITHEM;
    }

    public String getBANKNAME() {
        return BANKNAME;
    }

    public void setBANKNAME(String BANKNAME) {
        this.BANKNAME = BANKNAME;
    }

    public int getCOUNTOR() {
        return COUNTOR;
    }

    public void setCOUNTOR(int COUNTOR) {
        this.COUNTOR = COUNTOR;
    }

    public String getEFWK() {
        return EFWK;
    }

    public void setEFWK(String EFWK) {
        this.EFWK = EFWK;
    }

    public String getEXPONENT() {
        return EXPONENT;
    }

    public void setEXPONENT(String EXPONENT) {
        this.EXPONENT = EXPONENT;
    }

    public String getKVC() {
        return KVC;
    }

    public void setKVC(String KVC) {
        this.KVC = KVC;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public int getMAXCOUNTOR() {
        return MAXCOUNTOR;
    }

    public void setMAXCOUNTOR(int MAXCOUNTOR) {
        this.MAXCOUNTOR = MAXCOUNTOR;
    }

    public int getMAXPINCOUNTOR() {
        return MAXPINCOUNTOR;
    }

    public void setMAXPINCOUNTOR(int MAXPINCOUNTOR) {
        this.MAXPINCOUNTOR = MAXPINCOUNTOR;
    }

    public int getMAXTMKDOWNLOD() {
        return MAXTMKDOWNLOD;
    }

    public void setMAXTMKDOWNLOD(int MAXTMKDOWNLOD) {
        this.MAXTMKDOWNLOD = MAXTMKDOWNLOD;
    }

    public String getMODULES() {
        return MODULES;
    }

    public void setMODULES(String MODULES) {
        this.MODULES = MODULES;
    }

    public String getOFFICERNAME() {
        return OFFICERNAME;
    }

    public void setOFFICERNAME(String OFFICERNAME) {
        this.OFFICERNAME = OFFICERNAME;
    }

    public String getPINBLOCK() {
        return PINBLOCK;
    }

    public void setPINBLOCK(String PINBLOCK) {
        this.PINBLOCK = PINBLOCK;
    }

    public int getPINVERFICATION() {
        return PINVERFICATION;
    }

    public void setPINVERFICATION(int PINVERFICATION) {
        this.PINVERFICATION = PINVERFICATION;
    }

    public Date getREGDATE() {
        return REGDATE;
    }

    public void setREGDATE(Date REGDATE) {
        this.REGDATE = REGDATE;
    }

    public int getSELECTEDDEVICE() {
        return SELECTEDDEVICE;
    }

    public void setSELECTEDDEVICE(int SELECTEDDEVICE) {
        this.SELECTEDDEVICE = SELECTEDDEVICE;
    }

    public String getSERIALNO() {
        return SERIALNO;
    }

    public void setSERIALNO(String SERIALNO) {
        this.SERIALNO = SERIALNO;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getBDKINDEX() {
        return BDKINDEX;
    }

    public void setBDKINDEX(int BDKINDEX) {
        this.BDKINDEX = BDKINDEX;
    }
}
