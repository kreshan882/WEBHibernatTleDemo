/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.bean;

/**
 *
 * @author nipun_t
 */
public class ChangePasswordBean {

    private String oldPassword;
    private String newPassword;
    private String repeatPassword;
    
    private boolean update;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordBean{" + "oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", repeatPassword=" + repeatPassword + ", update=" + update + '}';
    }

}
