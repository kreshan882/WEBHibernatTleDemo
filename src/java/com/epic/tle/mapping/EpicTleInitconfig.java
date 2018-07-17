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
 * EpicTleInitconfig generated by hbm2java
 */
@Entity
@Table(name = "EPIC_TLE_INITCONFIG"
//        , catalog = "epictle_v5ch"
)
public class EpicTleInitconfig implements java.io.Serializable {

    private Integer id;
    private Integer bank_code;
    private EpicTleLoglevel epicTleLoglevel;
    private EpicTleReplayAttacklevel epicTleReplayAttacklevel;
    private EpicTleStatus epicTleStatusByLogbackupstatus;
    private EpicTleStatus epicTleStatusByEsmstatus;
    private EpicTleStatus epicTleStatusByMonitorstatus;
    private EpicTleStatus epicTleStatusByHostfailalertstatus;
    private EpicTleStatus epicTleStatusByVipstatus;
    private EpicTleStatus epicTleStatusByCoreDebugStatus;
    private EpicTleStatus epicTleStatusByAdVerifyStatus;
    private EpicTleStatus epicTleStatusByAutoRegistaryStatus;
    private EpicTleStatus epicTleStatusBySmsNotifyStatus;
    private EpicTleStatus epicTleStatusByUkptStatus;
    private Integer maxpincountor;
    private String monitorip;
    private Integer monitorport;
    private String emailUsername;
    private String emailPassword;
    private String smsUsername;
    private String smsPassword;
    private String logfilename;
    private String iv;
    private Integer monitortimeout;
    //private String psgpassword;
    private Integer poolmaxthread;
    private Integer poolminthread;
    private Integer poolmaxqueque;
    private Integer poolbacklog;
//    private String smsGwUrl;
    private Integer emailGwPort;
    private String emailGwUrl;
    private String logbackuppath;
    private Integer servicePort;
    private Integer bufferSize;
    private String vip;
    //private Integer hsmSlot;
    private Integer noflogbackupfile;
    private Integer serviceClientTimeout;
    private String adUrl;
    private String adUsername;
    private String adPassword;
    private Integer numHistoryRecords;
    //private Integer versionNo;
    private String keyMailTemplate;
    private String pinMailTemplate;
    private String passwordResetMailTemplate;
    private String smsServiceUrl;
    private Integer smsServiceTimeout;
    private Integer node;
    private Integer numberOfPasswordAttempts;
    private String passwordExpirationTimePeriod;
    private Integer tempPasswordExpirationTimePeriod;
    private Integer userAccountLockTime;

    private Integer smsPort;

    private String senderEmail;

    public EpicTleInitconfig() {
    }

    public EpicTleInitconfig(EpicTleStatus epicTleStatusByAdVerifyStatus, EpicTleStatus epicTleStatusByAutoRegistaryStatus, String adUrl, String adUsername, String adPassword) {
        this.epicTleStatusByAdVerifyStatus = epicTleStatusByAdVerifyStatus;
        this.epicTleStatusByAutoRegistaryStatus = epicTleStatusByAutoRegistaryStatus;
        this.adUrl = adUrl;
        this.adUsername = adUsername;
        this.adPassword = adPassword;
    }

    public EpicTleInitconfig(Integer id, EpicTleLoglevel epicTleLoglevel, EpicTleReplayAttacklevel epicTleReplayAttacklevel, EpicTleStatus epicTleStatusByLogbackupstatus, EpicTleStatus epicTleStatusByEsmstatus, EpicTleStatus epicTleStatusByMonitorstatus, EpicTleStatus epicTleStatusByHostfailalertstatus, EpicTleStatus epicTleStatusByVipstatus, EpicTleStatus epicTleStatusByCoreDebugStatus, EpicTleStatus epicTleStatusByAdVerifyStatus, EpicTleStatus epicTleStatusByAutoRegistaryStatus, EpicTleStatus epicTleStatusBySmsNotifyStatus, EpicTleStatus epicTleStatusByUkptStatus, Integer maxpincountor, String monitorip, Integer monitorport, String emailUsername, String emailPassword, String smsUsername, String smsPassword, String logfilename, String iv, Integer monitortimeout, String psgpassword, Integer poolmaxthread, Integer poolminthread, Integer poolmaxqueque, Integer poolbacklog, String emailGwUrl, String logbackuppath, Integer servicePort, Integer bufferSize, String vip, Integer hsmSlot, Integer noflogbackupfile, Integer serviceClientTimeout, String adUrl, String adUsername, String adPassword, Integer numHistoryRecords, Integer versionNo, String keyMailTemplate, String pinMailTemplate, String smsServiceUrl, Integer smsServiceTimeout, Integer node, Integer numberOfPasswordAttempts, String passwordExpirationTimePeriod, Integer userAccountLockTime, Integer smsPort) {
        this.id = id;
        this.epicTleLoglevel = epicTleLoglevel;
        this.epicTleReplayAttacklevel = epicTleReplayAttacklevel;
        this.epicTleStatusByLogbackupstatus = epicTleStatusByLogbackupstatus;
        this.epicTleStatusByEsmstatus = epicTleStatusByEsmstatus;
        this.epicTleStatusByMonitorstatus = epicTleStatusByMonitorstatus;
        this.epicTleStatusByHostfailalertstatus = epicTleStatusByHostfailalertstatus;
        this.epicTleStatusByVipstatus = epicTleStatusByVipstatus;
        this.epicTleStatusByCoreDebugStatus = epicTleStatusByCoreDebugStatus;
        this.epicTleStatusByAdVerifyStatus = epicTleStatusByAdVerifyStatus;
        this.epicTleStatusByAutoRegistaryStatus = epicTleStatusByAutoRegistaryStatus;
        this.epicTleStatusBySmsNotifyStatus = epicTleStatusBySmsNotifyStatus;
        this.epicTleStatusByUkptStatus = epicTleStatusByUkptStatus;
        this.maxpincountor = maxpincountor;
        this.monitorip = monitorip;
        this.monitorport = monitorport;
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
        this.smsUsername = smsUsername;
        this.smsPassword = smsPassword;
        this.logfilename = logfilename;
        this.iv = iv;
        this.monitortimeout = monitortimeout;
        //this.psgpassword = psgpassword;
        this.poolmaxthread = poolmaxthread;
        this.poolminthread = poolminthread;
        this.poolmaxqueque = poolmaxqueque;
        this.poolbacklog = poolbacklog;
//        this.smsGwUrl = smsGwUrl;
        this.emailGwUrl = emailGwUrl;
        this.logbackuppath = logbackuppath;
        this.servicePort = servicePort;
        this.bufferSize = bufferSize;
        this.vip = vip;
        //this.hsmSlot = hsmSlot;
        this.noflogbackupfile = noflogbackupfile;
        this.serviceClientTimeout = serviceClientTimeout;
        this.adUrl = adUrl;
        this.adUsername = adUsername;
        this.adPassword = adPassword;
        this.numHistoryRecords = numHistoryRecords;
        //this.versionNo = versionNo;
        this.keyMailTemplate = keyMailTemplate;
        this.pinMailTemplate = pinMailTemplate;
        this.smsServiceUrl = smsServiceUrl;
        this.smsServiceTimeout = smsServiceTimeout;
        this.node = node;
        this.numberOfPasswordAttempts = numberOfPasswordAttempts;
        this.passwordExpirationTimePeriod = passwordExpirationTimePeriod;
        this.userAccountLockTime = userAccountLockTime;

        this.smsPort = smsPort;
    }

    @Column(name = "BANK_CODE")
    public Integer getBank_code() {
        return bank_code;
    }

    public void setBank_code(Integer bank_code) {
        this.bank_code = bank_code;
    }

    @Id
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters = {
        @Parameter(name = "columnPram", value = "id")})
    @GeneratedValue(generator = "id_generator")
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NODE")
    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    @Column(name = "SMS_PORT")
    public Integer getSmsPort() {
        return smsPort;
    }

    public void setSmsPort(Integer smsPort) {
        this.smsPort = smsPort;
    }

//    @Column(name = "HSM_KEY_UTILITY_VERSION")
//    public Integer getVersionNo() {
//        return this.versionNo;
//    }
//
//    public void setVersionNo(Integer versionNo) {
//        this.versionNo = versionNo;
//    }
    @Column(name = "SMS_SERVICE_URL")
    public String getSmsServiceUrl() {
        return smsServiceUrl;
    }

    public void setSmsServiceUrl(String smsServiceUrl) {
        this.smsServiceUrl = smsServiceUrl;
    }

    @Column(name = "SMS_SERVICE_TIMEOUT")
    public Integer getSmsServiceTimeout() {
        return smsServiceTimeout;
    }

    public void setSmsServiceTimeout(Integer smsServiceTimeout) {
        this.smsServiceTimeout = smsServiceTimeout;
    }

    @Column(name = "BUFFER_SIZE")
    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGLEVEL")
    public EpicTleLoglevel getEpicTleLoglevel() {
        return this.epicTleLoglevel;
    }

    public void setEpicTleLoglevel(EpicTleLoglevel epicTleLoglevel) {
        this.epicTleLoglevel = epicTleLoglevel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLAYLEVEL")
    public EpicTleReplayAttacklevel getEpicTleReplayAttacklevel() {
        return this.epicTleReplayAttacklevel;
    }

    public void setEpicTleReplayAttacklevel(EpicTleReplayAttacklevel epicTleReplayAttacklevel) {
        this.epicTleReplayAttacklevel = epicTleReplayAttacklevel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGBACKUPSTATUS")
    public EpicTleStatus getEpicTleStatusByLogbackupstatus() {
        return this.epicTleStatusByLogbackupstatus;
    }

    public void setEpicTleStatusByLogbackupstatus(EpicTleStatus epicTleStatusByLogbackupstatus) {
        this.epicTleStatusByLogbackupstatus = epicTleStatusByLogbackupstatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESMSTATUS")
    public EpicTleStatus getEpicTleStatusByEsmstatus() {
        return this.epicTleStatusByEsmstatus;
    }

    public void setEpicTleStatusByEsmstatus(EpicTleStatus epicTleStatusByEsmstatus) {
        this.epicTleStatusByEsmstatus = epicTleStatusByEsmstatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MONITORSTATUS")
    public EpicTleStatus getEpicTleStatusByMonitorstatus() {
        return this.epicTleStatusByMonitorstatus;
    }

    public void setEpicTleStatusByMonitorstatus(EpicTleStatus epicTleStatusByMonitorstatus) {
        this.epicTleStatusByMonitorstatus = epicTleStatusByMonitorstatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSTFAILALERTSTATUS")
    public EpicTleStatus getEpicTleStatusByHostfailalertstatus() {
        return this.epicTleStatusByHostfailalertstatus;
    }

    public void setEpicTleStatusByHostfailalertstatus(EpicTleStatus epicTleStatusByHostfailalertstatus) {
        this.epicTleStatusByHostfailalertstatus = epicTleStatusByHostfailalertstatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIPSTATUS")
    public EpicTleStatus getEpicTleStatusByVipstatus() {
        return this.epicTleStatusByVipstatus;
    }

    public void setEpicTleStatusByVipstatus(EpicTleStatus epicTleStatusByVipstatus) {
        this.epicTleStatusByVipstatus = epicTleStatusByVipstatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CORE_DEBUG_STATUS")
    public EpicTleStatus getEpicTleStatusByCoreDebugStatus() {
        return this.epicTleStatusByCoreDebugStatus;
    }

    public void setEpicTleStatusByCoreDebugStatus(EpicTleStatus epicTleStatusByCoreDebugStatus) {
        this.epicTleStatusByCoreDebugStatus = epicTleStatusByCoreDebugStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AD_VERIFY_STATUS", nullable = false)
    public EpicTleStatus getEpicTleStatusByAdVerifyStatus() {
        return this.epicTleStatusByAdVerifyStatus;
    }

    public void setEpicTleStatusByAdVerifyStatus(EpicTleStatus epicTleStatusByAdVerifyStatus) {
        this.epicTleStatusByAdVerifyStatus = epicTleStatusByAdVerifyStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTO_REGISTARY_STATUS", nullable = false)
    public EpicTleStatus getEpicTleStatusByAutoRegistaryStatus() {
        return this.epicTleStatusByAutoRegistaryStatus;
    }

    public void setEpicTleStatusByAutoRegistaryStatus(EpicTleStatus epicTleStatusByAutoRegistaryStatus) {
        this.epicTleStatusByAutoRegistaryStatus = epicTleStatusByAutoRegistaryStatus;
    }

    @Column(name = "MAXPINCOUNTOR")
    public Integer getMaxpincountor() {
        return this.maxpincountor;
    }

    public void setMaxpincountor(Integer maxpincountor) {
        this.maxpincountor = maxpincountor;
    }

    @Column(name = "MONITORIP", length = 16)
    public String getMonitorip() {
        return this.monitorip;
    }

    public void setMonitorip(String monitorip) {
        this.monitorip = monitorip;
    }

    @Column(name = "MONITORPORT")
    public Integer getMonitorport() {
        return this.monitorport;
    }

    public void setMonitorport(Integer monitorport) {
        this.monitorport = monitorport;
    }

    @Column(name = "EMAIL_USERNAME", length = 50)
    public String getEmailUsername() {
        return this.emailUsername;
    }

    public void setEmailUsername(String emailUsername) {
        this.emailUsername = emailUsername;
    }

    @Column(name = "EMAIL_PASSWORD", length = 50)
    public String getEmailPassword() {
        return this.emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    @Column(name = "SMS_USERNAME", length = 50)
    public String getSmsUsername() {
        return this.smsUsername;
    }

    public void setSmsUsername(String smsUsername) {
        this.smsUsername = smsUsername;
    }

    @Column(name = "SMS_PASSWORD", length = 50)
    public String getSmsPassword() {
        return this.smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }

    @Column(name = "LOGFILENAME", length = 20)
    public String getLogfilename() {
        return this.logfilename;
    }

    public void setLogfilename(String logfilename) {
        this.logfilename = logfilename;
    }

    @Column(name = "IV", length = 16)
    public String getIv() {
        return this.iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    @Column(name = "MONITORTIMEOUT")
    public Integer getMonitortimeout() {
        return this.monitortimeout;
    }

    public void setMonitortimeout(Integer monitortimeout) {
        this.monitortimeout = monitortimeout;
    }

//    @Column(name = "PSGPASSWORD", length = 20)
//    public String getPsgpassword() {
//        return this.psgpassword;
//    }
//
//    public void setPsgpassword(String psgpassword) {
//        this.psgpassword = psgpassword;
//    }
    @Column(name = "POOLMAXTHREAD")
    public Integer getPoolmaxthread() {
        return this.poolmaxthread;
    }

    public void setPoolmaxthread(Integer poolmaxthread) {
        this.poolmaxthread = poolmaxthread;
    }

    @Column(name = "POOLMINTHREAD")
    public Integer getPoolminthread() {
        return this.poolminthread;
    }

    public void setPoolminthread(Integer poolminthread) {
        this.poolminthread = poolminthread;
    }

    @Column(name = "POOLMAXQUEQUE")
    public Integer getPoolmaxqueque() {
        return this.poolmaxqueque;
    }

    public void setPoolmaxqueque(Integer poolmaxqueque) {
        this.poolmaxqueque = poolmaxqueque;
    }

    @Column(name = "POOLBACKLOG")
    public Integer getPoolbacklog() {
        return this.poolbacklog;
    }

    public void setPoolbacklog(Integer poolbacklog) {
        this.poolbacklog = poolbacklog;
    }

//    @Column(name = "SMS_GW_URL", length = 200)
//    public String getSmsGwUrl() {
//        return this.smsGwUrl;
//    }
//    public void setSmsGwUrl(String smsGwUrl) {
//        this.smsGwUrl = smsGwUrl;
//    }
    @Column(name = "EMAIL_GW_URL", length = 200)
    public String getEmailGwUrl() {
        return this.emailGwUrl;
    }

    public void setEmailGwUrl(String emailGwUrl) {
        this.emailGwUrl = emailGwUrl;
    }

    @Column(name = "LOGBACKUPPATH", length = 200)
    public String getLogbackuppath() {
        return this.logbackuppath;
    }

    public void setLogbackuppath(String logbackuppath) {
        this.logbackuppath = logbackuppath;
    }

    @Column(name = "SERVICE_PORT")
    public Integer getServicePort() {
        return this.servicePort;
    }

    public void setServicePort(Integer servicePort) {
        this.servicePort = servicePort;
    }

    @Column(name = "VIP", length = 45)
    public String getVip() {
        return this.vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

//    @Column(name = "HSM_SLOT")
//    public Integer getHsmSlot() {
//        return this.hsmSlot;
//    }
//
//    public void setHsmSlot(Integer hsmSlot) {
//        this.hsmSlot = hsmSlot;
//    }
    @Column(name = "NOFLOGBACKUPFILE")
    public Integer getNoflogbackupfile() {
        return this.noflogbackupfile;
    }

    public void setNoflogbackupfile(Integer noflogbackupfile) {
        this.noflogbackupfile = noflogbackupfile;
    }

    @Column(name = "SERVICE_CLIENT_TIMEOUT")
    public Integer getServiceClientTimeout() {
        return this.serviceClientTimeout;
    }

    public void setServiceClientTimeout(Integer serviceClientTimeout) {
        this.serviceClientTimeout = serviceClientTimeout;
    }

    @Column(name = "AD_URL", nullable = false, length = 200)
    public String getAdUrl() {
        return this.adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    @Column(name = "AD_USERNAME", nullable = false, length = 50)
    public String getAdUsername() {
        return this.adUsername;
    }

    public void setAdUsername(String adUsername) {
        this.adUsername = adUsername;
    }

    @Column(name = "AD_PASSWORD", nullable = false, length = 50)
    public String getAdPassword() {
        return this.adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    @Column(name = "NUM_HISTORY_RECORDS")
    public Integer getNumHistoryRecords() {
        return this.numHistoryRecords;
    }

    public void setNumHistoryRecords(Integer numHistoryRecords) {
        this.numHistoryRecords = numHistoryRecords;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SMS_NOTIFY_STATUS")
    public EpicTleStatus getEpicTleStatusBySmsNotifyStatus() {
        return epicTleStatusBySmsNotifyStatus;
    }

    public void setEpicTleStatusBySmsNotifyStatus(EpicTleStatus epicTleStatusBySmsNotifyStatus) {
        this.epicTleStatusBySmsNotifyStatus = epicTleStatusBySmsNotifyStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UKPT_STATUS")
    public EpicTleStatus getEpicTleStatusByUkptStatus() {
        return epicTleStatusByUkptStatus;
    }

    public void setEpicTleStatusByUkptStatus(EpicTleStatus epicTleStatusByUkptStatus) {
        this.epicTleStatusByUkptStatus = epicTleStatusByUkptStatus;
    }

    @Column(name = "KEY_MAIL_TEMPLATE", length = 800)
    public String getKeyMailTemplate() {
        return keyMailTemplate;
    }

    public void setKeyMailTemplate(String keyMailTemplate) {
        this.keyMailTemplate = keyMailTemplate;
    }

    @Column(name = "PIN_MAIL_TEMPLATE", length = 800)
    public String getPinMailTemplate() {
        return pinMailTemplate;
    }

    public void setPinMailTemplate(String pinMailTemplate) {
        this.pinMailTemplate = pinMailTemplate;
    }

    @Column(name = "NUM_OF_PASS_ATTEMPTS")
    public Integer getNumberOfPasswordAttempts() {
        return numberOfPasswordAttempts;
    }

    public void setNumberOfPasswordAttempts(Integer numberOfPasswordAttempts) {
        this.numberOfPasswordAttempts = numberOfPasswordAttempts;
    }

    @Override
    public String toString() {
        try {
            return "{" + "Id:" + id + ",  Log level:" + epicTleLoglevel.getCode() + ", Replay Attack level:" + epicTleReplayAttacklevel.getCode() + ", Logbackup status:" + epicTleStatusByLogbackupstatus.getCode() + ", Esm status:" + epicTleStatusByEsmstatus.getCode() + ", Monitor status:" + epicTleStatusByMonitorstatus.getCode() + ", Host failalert status:" + epicTleStatusByHostfailalertstatus.getCode() + ", Vip status:" + epicTleStatusByVipstatus.getCode() + ", Core Debug Status:" + epicTleStatusByCoreDebugStatus.getCode() + ", Ad Verify Status:" + epicTleStatusByAdVerifyStatus.getCode() + ", Auto Registary Status:" + epicTleStatusByAutoRegistaryStatus.getCode() + ", Sms Notify Status:" + epicTleStatusBySmsNotifyStatus.getCode() + ", Ukpt Status:" + epicTleStatusByUkptStatus.getCode() + ", Max Pin Countor:" + maxpincountor + ", Monitor Ip:" + monitorip + ", Monitor Port:" + monitorport + ", Email Username:" + emailUsername + ", Email Password: <Not Shown>, SMS Username:" + smsUsername + ", SMS Password: <Not Shown>, Log File Name:" + logfilename + ", iv:" + iv + ", Monitor timeout:" + monitortimeout + ", Max Pool Thread:" + poolmaxthread + ", Min Pool Thread:" + poolminthread + ", Max Pool Queque:" + poolmaxqueque + ", Pool Back Log:" + poolbacklog + ", Email Gateway Url:" + emailGwUrl + ", Log Backup Path:" + logbackuppath + ", Service Port:" + servicePort + ", Buffer Size:" + bufferSize + ", vip:" + vip + ", Number of Logbackup File:" + noflogbackupfile + ", Service Client Timeout:" + serviceClientTimeout + ", ad Url:" + adUrl + ", ad Username:" + adUsername + ", ad Password:" + adPassword + ", No of History Records:" + numHistoryRecords + ", SMS Service Url:" + smsServiceUrl + ", SMS Service Timeout:" + smsServiceTimeout + ", Node:" + node + ", Number Of Password Attempts:" + numberOfPasswordAttempts + '}';
        } catch (Exception e) {
            return "{" + "Id:" + id + ", Max Pin Countor:" + maxpincountor + ", Monitor Ip:" + monitorip + ", Monitor port:" + monitorport + ", Email Username:" + emailUsername + ", emailPassword: <Not Shown>, SMS Username:" + smsUsername + ", SMS Port:" + smsPort + ", smsPassword: <Not Shown>, Log file name:" + logfilename + ", iv:" + iv + ", Monitor timeout:" + monitortimeout + ", Max Pool Thread:" + poolmaxthread + ", Min Pool Thread:" + poolminthread + ", Max pool queque:" + poolmaxqueque + ", pool back log:" + poolbacklog + ", Email gateway Url:" + emailGwUrl + ", Log backup path:" + logbackuppath + ", Service Port:" + servicePort + ", Buffer Size:" + bufferSize + ", vip:" + vip + ", No of log backup files:" + noflogbackupfile + ", Service Client Timeout:" + serviceClientTimeout + ", ad Url:" + adUrl + ", ad Username:" + adUsername + ", ad Password:" + adPassword + ", No of History Records:" + numHistoryRecords + ", SMS Service Url:" + smsServiceUrl + ", SMS Service Timeout:" + smsServiceTimeout + ", Node:" + node + ", Number Of Password Attempts:" + numberOfPasswordAttempts + '}';
        }
    }

    public String forHistory() {
        try {
            return "{" + "Id:" + id + ",  Log level:" + epicTleLoglevel.getCode() + ", Replay Attack level:" + epicTleReplayAttacklevel.getCode() + ", Logbackup status:" + epicTleStatusByLogbackupstatus.getCode() + ", Esm status:" + epicTleStatusByEsmstatus.getCode() + ", Monitor status:" + epicTleStatusByMonitorstatus.getCode() + ", Host failalert status:" + epicTleStatusByHostfailalertstatus.getCode() + ", Vip status:" + epicTleStatusByVipstatus.getCode() + ", Core Debug Status:" + epicTleStatusByCoreDebugStatus.getCode() + ", Ad Verify Status:" + epicTleStatusByAdVerifyStatus.getCode() + ", Auto Registary Status:" + epicTleStatusByAutoRegistaryStatus.getCode() + ", Sms Notify Status:" + epicTleStatusBySmsNotifyStatus.getCode() + ", Ukpt Status:" + epicTleStatusByUkptStatus.getCode() + ", Max Pin Countor:" + maxpincountor + ", Monitor Ip:" + monitorip + ", Monitor Port:" + monitorport + ", Email Username:" + emailUsername + ", Email Password: <Not Shown>, SMS Username:" + smsUsername + ", SMS Password: <Not Shown>, Log File Name:" + logfilename + ", iv:" + iv + ", Monitor timeout:" + monitortimeout + ", Max Pool Thread:" + poolmaxthread + ", Min Pool Thread:" + poolminthread + ", Max Pool Queque:" + poolmaxqueque + ", Pool Back Log:" + poolbacklog + ", Email Gateway Url:" + emailGwUrl + ", Log Backup Path:" + logbackuppath + ", Service Port:" + servicePort + ", Buffer Size:" + bufferSize + ", vip:" + vip + ", Number of Logbackup File:" + noflogbackupfile + ", Service Client Timeout:" + serviceClientTimeout + ", ad Url:" + adUrl + ", ad Username:" + adUsername + ", ad Password:" + adPassword + ", No of History Records:" + numHistoryRecords + ", SMS Service Url:" + smsServiceUrl + ", SMS Service Timeout:" + smsServiceTimeout + ", Node:" + node + ", Number Of Password Attempts:" + numberOfPasswordAttempts + '}';
        } catch (Exception e) {
            return "{" + "Id:" + id + ", Max Pin Countor:" + maxpincountor + ", Monitor Ip:" + monitorip + ", Monitor port:" + monitorport + ", Email Username:" + emailUsername + ", emailPassword: <Not Shown>, SMS Username:" + smsUsername + ", smsPassword: <Not Shown>, Log file name:" + logfilename + ", iv:" + iv + ", Monitor timeout:" + monitortimeout + ", Max Pool Thread:" + poolmaxthread + ", Min Pool Thread:" + poolminthread + ", Max pool queque:" + poolmaxqueque + ", pool back log:" + poolbacklog + ", Email gateway Url:" + emailGwUrl + ", Log backup path:" + logbackuppath + ", Service Port:" + servicePort + ", Buffer Size:" + bufferSize + ", vip:" + vip + ", No of log backup files:" + noflogbackupfile + ", Service Client Timeout:" + serviceClientTimeout + ", ad Url:" + adUrl + ", ad Username:" + adUsername + ", ad Password:" + adPassword + ", No of History Records:" + numHistoryRecords + ", SMS Service Url:" + smsServiceUrl + ", SMS Service Timeout:" + smsServiceTimeout + ", Node:" + node + ", Number Of Password Attempts:" + numberOfPasswordAttempts + '}';
        }
    }

    @Column(name = "USR_PASS_EXP_TIME")
    public String getPasswordExpirationTimePeriod() {
        return passwordExpirationTimePeriod;
    }

    public void setPasswordExpirationTimePeriod(String passwordExpirationTimePeriod) {
        this.passwordExpirationTimePeriod = passwordExpirationTimePeriod;
    }

    @Column(name = "USER_ACCOUNT_LOCK_TIME")
    public Integer getUserAccountLockTime() {
        return userAccountLockTime;
    }

    public void setUserAccountLockTime(Integer userAccountLockTime) {
        this.userAccountLockTime = userAccountLockTime;
    }

    @Column(name = "USR_TMP_PASS_EXP_TIME")
    public Integer getTempPasswordExpirationTimePeriod() {
        return tempPasswordExpirationTimePeriod;
    }

    public void setTempPasswordExpirationTimePeriod(Integer tempPasswordExpirationTimePeriod) {
        this.tempPasswordExpirationTimePeriod = tempPasswordExpirationTimePeriod;
    }

    @Column(name = "PASS_RESET_MAIL_TEMPLATE")
    public String getPasswordResetMailTemplate() {
        return passwordResetMailTemplate;
    }

    public void setPasswordResetMailTemplate(String passwordResetMailTemplate) {
        this.passwordResetMailTemplate = passwordResetMailTemplate;
    }

    @Column(name = "EMAIL_GW_PORT")
    public Integer getEmailGwPort() {
        return emailGwPort;
    }

    public void setEmailGwPort(Integer emailGwPort) {
        this.emailGwPort = emailGwPort;
    }

    @Column(name = "SENDER_EMAIL")
    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

}
