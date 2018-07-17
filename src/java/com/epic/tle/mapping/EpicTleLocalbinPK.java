/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.mapping;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gayan_s
 */
@Embeddable
public class EpicTleLocalbinPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "BIN")
    private String bin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATEGORY_CODE")
    private int categoryCode;

    public EpicTleLocalbinPK() {
    }

    public EpicTleLocalbinPK(String bin, int categoryCode) {
        this.bin = bin;
        this.categoryCode = categoryCode;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bin != null ? bin.hashCode() : 0);
        hash += (int) categoryCode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpicTleLocalbinPK)) {
            return false;
        }
        EpicTleLocalbinPK other = (EpicTleLocalbinPK) object;
        if ((this.bin == null && other.bin != null) || (this.bin != null && !this.bin.equals(other.bin))) {
            return false;
        }
        if (this.categoryCode != other.categoryCode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epic.tle.mapping.EpicTleLocalbinPK[ bin=" + bin + ", categoryCode=" + categoryCode + " ]";
    }
    
}
