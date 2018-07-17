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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gayan_s
 */
@Entity
@Table(name = "EPIC_TLE_LOCALBIN", schema = "")
public class EpicTleLocalbin implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EpicTleLocalbinPK epicTleLocalbinPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public EpicTleLocalbin() {
    }

    public EpicTleLocalbin(EpicTleLocalbinPK epicTleLocalbinPK) {
        this.epicTleLocalbinPK = epicTleLocalbinPK;
    }

    public EpicTleLocalbin(EpicTleLocalbinPK epicTleLocalbinPK, Date timestamp) {
        this.epicTleLocalbinPK = epicTleLocalbinPK;
        this.timestamp = timestamp;
    }

    public EpicTleLocalbin(String bin, int categoryCode) {
        this.epicTleLocalbinPK = new EpicTleLocalbinPK(bin, categoryCode);
    }

    public EpicTleLocalbinPK getEpicTleLocalbinPK() {
        return epicTleLocalbinPK;
    }

    public void setEpicTleLocalbinPK(EpicTleLocalbinPK epicTleLocalbinPK) {
        this.epicTleLocalbinPK = epicTleLocalbinPK;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epicTleLocalbinPK != null ? epicTleLocalbinPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpicTleLocalbin)) {
            return false;
        }
        EpicTleLocalbin other = (EpicTleLocalbin) object;
        if ((this.epicTleLocalbinPK == null && other.epicTleLocalbinPK != null) || (this.epicTleLocalbinPK != null && !this.epicTleLocalbinPK.equals(other.epicTleLocalbinPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String cat = epicTleLocalbinPK.getCategoryCode() == 1 ? "Local Bin" : "System Block Bin";
        try {
            return "{" + "category code : " + epicTleLocalbinPK.getBin() + " , category : " + cat + ", timestamp=" + timestamp + '}';
        } catch (Exception e) {
            return "{" + "category code: " + epicTleLocalbinPK.getBin() + " , category : " + cat + ", timestamp=" + timestamp + '}';
        }
    }

}
