/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.mapping;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "EPIC_TLE_SMS_FILTER", schema = "")

public class EpicTleSmsFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "id_generator", strategy = "com.epic.tle.util.IdGenerator", parameters = {
        @Parameter(name = "columnPram", value = "id")})
    @GeneratedValue(generator = "id_generator")
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 250)
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "CERATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ceratedDate;
    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "SMS_PROFILE_ID")
    @ManyToOne(optional = false)
    private EpicTleSmsProfile profileId;

    public EpicTleSmsFilter() {
    }

    public EpicTleSmsFilter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCeratedDate() {
        return ceratedDate;
    }

    public void setCeratedDate(Date ceratedDate) {
        this.ceratedDate = ceratedDate;
    }

    public EpicTleSmsProfile getProfileId() {
        return profileId;
    }

    public void setProfileId(EpicTleSmsProfile profileId) {
        this.profileId = profileId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpicTleSmsFilter)) {
            return false;
        }
        EpicTleSmsFilter other = (EpicTleSmsFilter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return "{" + "id : " + id + ", message : " + message + ", ceratedDate : " + ceratedDate + ", profile id : " + profileId.getSmsProfileId() + '}';

        } catch (Exception e) {
            return "{" + "id : " + id + ", message : " + message + ", ceratedDate : " + ceratedDate + ", profile id : " + profileId.getSmsProfileId() + '}';
        }

    }

}
