package com.epic.tle.mapping;
// Generated May 31, 2017 9:39:12 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EpicTleBlockBinId generated by hbm2java
 */
@Embeddable
public class EpicTleBlockBinId implements java.io.Serializable {

    private int profileId;
    private int low_value;
    private int upper_value;

    public EpicTleBlockBinId() {
    }

    public EpicTleBlockBinId(int profileId, int low_value, int upper_value) {
        this.profileId = profileId;
        this.low_value = low_value;
        this.upper_value = upper_value;
    }

    @Column(name = "PROFILE_ID", nullable = false)
    public int getProfileId() {
        return this.profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.profileId;
        hash = 37 * hash + this.low_value;
        hash = 37 * hash + this.upper_value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EpicTleBlockBinId other = (EpicTleBlockBinId) obj;
        if (this.profileId != other.profileId) {
            return false;
        }
        if (this.low_value != other.low_value) {
            return false;
        }
        if (this.upper_value != other.upper_value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EpicTleBlockBinId{" + "profileId=" + profileId + ", low_value=" + low_value + ", upper_value=" + upper_value + '}';
    }

    public String forHistory() {
        return "{" + "Profile Id:" + profileId + ", low_value=" + low_value + ", upper_value=" + upper_value + '}';
    }

    @Column(name = "LOW_VALUE")
    public int getLow_value() {
        return low_value;
    }

    public void setLow_value(int low_value) {
        this.low_value = low_value;
    }

    @Column(name = "UPPER_VALUE")
    public int getUpper_value() {
        return upper_value;
    }

    public void setUpper_value(int upper_value) {
        this.upper_value = upper_value;
    }

}
