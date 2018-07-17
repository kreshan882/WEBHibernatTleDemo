/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.mapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author ridmi_g
 */
@Entity
@Table(name = "EPIC_TLE_SMS_PROFILE", schema = "")
public class EpicTleSmsProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters= {@Parameter(name="columnPram", value = "smsProfileId")})
    @GeneratedValue(generator = "id_generator")
    @Basic(optional = false)
    @Column(name = "SMS_PROFILE_ID")
    private Integer smsProfileId;
    @Size(max = 50)
    @Column(name = "PROFILE_NAME")
    private String profileName;
    @Column(name = "CERATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ceratedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private Collection<EpicTleSmsFilter> epicTleSmsFilterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private Collection<EpicTleSmsProfileInfo> epicTleSmsProfileInfoCollection;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS")
    private EpicTleStatus epicTleStatus;

    public EpicTleSmsProfile() {
    }

    public EpicTleSmsProfile(Integer smsProfileId) {
        this.smsProfileId = smsProfileId;
    }

    public Integer getSmsProfileId() {
        return smsProfileId;
    }

    public void setSmsProfileId(Integer smsProfileId) {
        this.smsProfileId = smsProfileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Date getCeratedDate() {
        return ceratedDate;
    }

    public void setCeratedDate(Date ceratedDate) {
        this.ceratedDate = ceratedDate;
    }

    public Collection<EpicTleSmsFilter> getEpicTleSmsFilterCollection() {
        return epicTleSmsFilterCollection;
    }

    public void setEpicTleSmsFilterCollection(Collection<EpicTleSmsFilter> epicTleSmsFilterCollection) {
        this.epicTleSmsFilterCollection = epicTleSmsFilterCollection;
    }

    public Collection<EpicTleSmsProfileInfo> getEpicTleSmsProfileInfoCollection() {
        return epicTleSmsProfileInfoCollection;
    }

    public void setEpicTleSmsProfileInfoCollection(Collection<EpicTleSmsProfileInfo> epicTleSmsProfileInfoCollection) {
        this.epicTleSmsProfileInfoCollection = epicTleSmsProfileInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smsProfileId != null ? smsProfileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpicTleSmsProfile)) {
            return false;
        }
        EpicTleSmsProfile other = (EpicTleSmsProfile) object;
        if ((this.smsProfileId == null && other.smsProfileId != null) || (this.smsProfileId != null && !this.smsProfileId.equals(other.smsProfileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return "{" + "smsProfileId : " + smsProfileId + ", profileName : " + profileName + ", ceratedDate : " + ceratedDate +   ", epicTleStatus : " + epicTleStatus.getDescription() + '}';
        } catch (Exception e) {
            return "{" + "smsProfileId : " + smsProfileId + ", profileName : " + profileName + ", ceratedDate : " + ceratedDate +  ", epicTleStatus : " + epicTleStatus.getDescription() + '}';
        }
    }

    public EpicTleStatus getEpicTleStatus() {
        return epicTleStatus;
    }

    public void setEpicTleStatus(EpicTleStatus epicTleStatus) {
        this.epicTleStatus = epicTleStatus;
    }

}
