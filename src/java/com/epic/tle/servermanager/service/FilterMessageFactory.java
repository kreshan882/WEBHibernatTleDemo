/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

/**
 *
 * @author ridmi_g
 */
public class FilterMessageFactory {

    private final FilterMessageInf filterMessageInf;

    public FilterMessageFactory() {
       
        this.filterMessageInf = new FilterMessageService();
    }

    public FilterMessageInf getFilterMessageInf() {
        return filterMessageInf;
    }

}
