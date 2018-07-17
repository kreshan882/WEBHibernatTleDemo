package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * EpicTleUser generated by hbm2java
 */
@Entity
@Table(name = "EPIC_TLE_USER" //    ,catalog="epictle_v5ch"
        , uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME","EMAIL"})
)
public class EpicTleUser implements java.io.Serializable {

    private Integer userid;
    private EpicTleStatus epicTleStatus;
    private EpicTleUserProfile epicTleUserProfile;
    private String username;
    private String password;
    private String name;
    private String email;
    private String createusername;
    private Date createdate;
    private int failureLoginAttempts;
    private Date lastRawUpdateDateTime;
    private Date lastPasswordChangeDate;
    private Date lastPasswordResetDate;
    private Set<EpicTleHistory> epicTleHistories = new HashSet<EpicTleHistory>(0);
    private Set<EpicTleUserPasswordHistory> epicTleUserPasswordHistories = new HashSet<EpicTleUserPasswordHistory>(0);
    private String randVal;
    
    public EpicTleUser() {
    }

    public EpicTleUser(String username) {
        this.username = username;
    }

    public EpicTleUser(Integer userid) {
        this.userid = userid;
    }

    public EpicTleUser(Integer userid, EpicTleStatus epicTleStatus, EpicTleUserProfile epicTleUserProfile, String username, String password, String name, String email, String createusername, Date createdate, int failureLoginAttempts, Date lastRawUpdateDateTime, Date lastPasswordChangeDate) {
        this.userid = userid;
        this.epicTleStatus = epicTleStatus;
        this.epicTleUserProfile = epicTleUserProfile;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createusername = createusername;
        this.createdate = createdate;
        this.failureLoginAttempts = failureLoginAttempts;
        this.lastRawUpdateDateTime = lastRawUpdateDateTime;
        this.lastPasswordChangeDate = lastPasswordChangeDate;
    }

    @Id
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters= {@Parameter(name="columnPram", value = "userid")})
    @GeneratedValue(generator = "id_generator")
    @Column(name = "USERID", unique = true, nullable = false)
    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
    @JoinColumn(name = "APCODE")
    public EpicTleUserProfile getEpicTleUserProfile() {
        return this.epicTleUserProfile;
    }

    public void setEpicTleUserProfile(EpicTleUserProfile epicTleUserProfile) {
        this.epicTleUserProfile = epicTleUserProfile;
    }

    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "NAME", length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CREATEUSERNAME", length = 100)
    public String getCreateusername() {
        return this.createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE", length = 19)
    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "epicTleUser")
    public Set<EpicTleHistory> getEpicTleHistories() {
        return this.epicTleHistories;
    }

    public void setEpicTleHistories(Set<EpicTleHistory> epicTleHistories) {
        this.epicTleHistories = epicTleHistories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
    public Set<EpicTleUserPasswordHistory> getEpicTleUserPasswordHistories() {
        return epicTleUserPasswordHistories;
    }

    public void setEpicTleUserPasswordHistories(Set<EpicTleUserPasswordHistory> epicTleUserPasswordHistories) {
        this.epicTleUserPasswordHistories = epicTleUserPasswordHistories;
    }

    @Column(name = "FAILURE_LOGIN_COUNT")
    public int getFailureLoginAttempts() {
        return failureLoginAttempts;
    }

    public void setFailureLoginAttempts(int failureLoginAttempts) {
        this.failureLoginAttempts = failureLoginAttempts;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LR_UPDATED_TIMESTAMP", length = 19)
    public Date getLastRawUpdateDateTime() {
        return lastRawUpdateDateTime;
    }

    public void setLastRawUpdateDateTime(Date lastRawUpdateDateTime) {
        this.lastRawUpdateDateTime = lastRawUpdateDateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "L_PASS_CHANGE_DATE", length = 19)
    public Date getLastPasswordChangeDate() {
        return lastPasswordChangeDate;
    }

    public void setLastPasswordChangeDate(Date lastPasswordChangeDate) {
        this.lastPasswordChangeDate = lastPasswordChangeDate;
    }

    @Column(name = "EMAIL", unique = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "L_PASS_RESET_DATE", length = 19)
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Column(name = "RAND_VAL", length = 50)
    public String getRandVal() {
        return randVal;
    }

    public void setRandVal(String randVal) {
        this.randVal = randVal;
    }

    @Override
    public String toString() {
        try {
            return "{" + "user id : " + userid + ", status : " + epicTleStatus.getCode() + ", user profile id : " + epicTleUserProfile.getCode() + ", username: " + username + ", password: <Not Shown> , name: " + name + ", email: " + email + ", create user name: " + createusername + ", create date: " + createdate + ", Failure Login Attempts: " + failureLoginAttempts + ", Last Raw Update DateTime:" + lastRawUpdateDateTime + ", Last Password Change Date: " + lastPasswordChangeDate + ",Last Password Reset Date : " + lastPasswordResetDate + '}';
        } catch (Exception e) {
            return "{" + "user id :" + userid + ", username: " + username + ", password: <Not Shown> , name: " + name + ", email: " + email + ", create user name: " + createusername + ", createdate: " + createdate + ", Failure Login Attempts: " + failureLoginAttempts + ", Last Raw Update DateTime: " + lastRawUpdateDateTime + ", Last Password Change Date: " + lastPasswordChangeDate + '}';
        }

    }
    public String forHistory() {
        try {
            return "{" + "user id : " + userid + ", status : " + epicTleStatus.getCode() + ", user profile id : " + epicTleUserProfile.getCode() + ", username: " + username + ", password: <Not Shown> , name: " + name + ", email: " + email + ", create user name: " + createusername + ", create date: " + createdate + ", Failure Login Attempts: " + failureLoginAttempts + ", Last Raw Update DateTime:" + lastRawUpdateDateTime + ", Last Password Change Date: " + lastPasswordChangeDate + ",Last Password Reset Date : " + lastPasswordResetDate + '}';
        } catch (Exception e) {
            return "{" + "user id :" + userid + ", username: " + username + ", password: <Not Shown> , name: " + name + ", email: " + email + ", create user name: " + createusername + ", createdate: " + createdate + ", Failure Login Attempts: " + failureLoginAttempts + ", Last Raw Update DateTime: " + lastRawUpdateDateTime + ", Last Password Change Date: " + lastPasswordChangeDate + '}';
        }
    }

}
