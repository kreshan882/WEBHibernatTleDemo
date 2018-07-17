/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chalaka_n
 */
public class ResponseConfigurationBean {

    private String f_02;
    private String f_03;
    private String f_04;
    private String f_11;
    private String f_12;
    private String f_13;
    private String f_14;
    private String f_22;
    private String f_23;
    private String f_24;
    private String f_25;
    private String f_35;
    private String f_37;
    private String f_38;
    private String f_39;
    private String f_41;
    private String f_42;
    private String f_45;
    private String f_47;
    private String f_48;
    private String f_52;
    private String f_54;
    private String f_55;
    private String f_60;
    private String f_61;
    private String f_62;
    private String f_63;
    private String f_49;
    

    private Map<String, String> f_02_Map = new HashMap<String, String>();
    private Map<String, String> f_03_Map = new HashMap<String, String>();
    private Map<String, String> f_04_Map = new HashMap<String, String>();
    private Map<String, String> f_11_Map = new HashMap<String, String>();
    private Map<String, String> f_12_Map = new HashMap<String, String>();
    private Map<String, String> f_13_Map = new HashMap<String, String>();
    private Map<String, String> f_14_Map = new HashMap<String, String>();
    private Map<String, String> f_22_Map = new HashMap<String, String>();
    private Map<String, String> f_23_Map = new HashMap<String, String>();
    private Map<String, String> f_24_Map = new HashMap<String, String>();
    private Map<String, String> f_25_Map = new HashMap<String, String>();
    private Map<String, String> f_35_Map = new HashMap<String, String>();
    private Map<String, String> f_37_Map = new HashMap<String, String>();
    private Map<String, String> f_38_Map = new HashMap<String, String>();
    private Map<String, String> f_39_Map = new HashMap<String, String>();
    private Map<String, String> f_41_Map = new HashMap<String, String>();
    private Map<String, String> f_42_Map = new HashMap<String, String>();
    private Map<String, String> f_45_Map = new HashMap<String, String>();    
    private Map<String, String> f_47_Map = new HashMap<String, String>();
    private Map<String, String> f_48_Map = new HashMap<String, String>();
    private Map<String, String> f_52_Map = new HashMap<String, String>();
    private Map<String, String> f_54_Map = new HashMap<String, String>();
    private Map<String, String> f_55_Map = new HashMap<String, String>();
    private Map<String, String> f_60_Map = new HashMap<String, String>();
    private Map<String, String> f_61_Map = new HashMap<String, String>();
    private Map<String, String> f_62_Map = new HashMap<String, String>();
    private Map<String, String> f_63_Map = new HashMap<String, String>();
    private Map<String, String> f_49_Map = new HashMap<String, String>();
    
    //load table data into map
    
    private Map<String,String> tlestatusresponseMap = new HashMap<String, String>();

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean view;
    private String PageCode="";
    
         //***************Working Path*************
    private String Module="";
    private String Section="";
    
    //***************Token************************
    private String Token;


    public String getF_49() {
        return f_49;
    }

    public void setF_49(String f_49) {
        this.f_49 = f_49;
    }

    public Map<String, String> getF_49_Map() {
        return f_49_Map;
    }

    public void setF_49_Map(Map<String, String> f_49_Map) {
        this.f_49_Map = f_49_Map;
    }

    
    public boolean isVadd() {
        return vadd;
    }

    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    public boolean isVupdate() {
        return vupdate;
    }

    public void setVupdate(boolean vupdate) {
        this.vupdate = vupdate;
    }

    public boolean isVdelete() {
        return vdelete;
    }

    public void setVdelete(boolean vdelete) {
        this.vdelete = vdelete;
    }
    
    
    
    public Map<String, String> getTlestatusresponseMap() {
        return tlestatusresponseMap;
    }

    public void setTlestatusresponseMap(Map<String, String> tlestatusresponseMap) {
        this.tlestatusresponseMap = tlestatusresponseMap;
    }

    public String getF_02() {
        return f_02;
    }

    public void setF_02(String f_02) {
        this.f_02 = f_02;
    }

    public String getF_03() {
        return f_03;
    }

    public void setF_03(String f_03) {
        this.f_03 = f_03;
    }

    public String getF_04() {
        return f_04;
    }

    public void setF_04(String f_04) {
        this.f_04 = f_04;
    }

    public String getF_11() {
        return f_11;
    }

    public void setF_11(String f_11) {
        this.f_11 = f_11;
    }

    public String getF_12() {
        return f_12;
    }

    public void setF_12(String f_12) {
        this.f_12 = f_12;
    }

    public String getF_13() {
        return f_13;
    }

    public void setF_13(String f_13) {
        this.f_13 = f_13;
    }

    public String getF_14() {
        return f_14;
    }

    public void setF_14(String f_14) {
        this.f_14 = f_14;
    }

    public String getF_22() {
        return f_22;
    }

    public void setF_22(String f_22) {
        this.f_22 = f_22;
    }

    public String getF_23() {
        return f_23;
    }

    public void setF_23(String f_23) {
        this.f_23 = f_23;
    }

    public String getF_24() {
        return f_24;
    }

    public void setF_24(String f_24) {
        this.f_24 = f_24;
    }

    public String getF_25() {
        return f_25;
    }

    public void setF_25(String f_25) {
        this.f_25 = f_25;
    }

    public String getF_35() {
        return f_35;
    }

    public void setF_35(String f_35) {
        this.f_35 = f_35;
    }

    public String getF_37() {
        return f_37;
    }

    public void setF_37(String f_37) {
        this.f_37 = f_37;
    }

    public String getF_38() {
        return f_38;
    }

    public void setF_38(String f_38) {
        this.f_38 = f_38;
    }

    public String getF_39() {
        return f_39;
    }

    public void setF_39(String f_39) {
        this.f_39 = f_39;
    }

    public String getF_41() {
        return f_41;
    }

    public void setF_41(String f_41) {
        this.f_41 = f_41;
    }

    public String getF_42() {
        return f_42;
    }

    public void setF_42(String f_42) {
        this.f_42 = f_42;
    }

    public String getF_45() {
        return f_45;
    }

    public void setF_45(String f_45) {
        this.f_45 = f_45;
    }

    public String getF_47() {
        return f_47;
    }

    public void setF_47(String f_47) {
        this.f_47 = f_47;
    }

    public String getF_48() {
        return f_48;
    }

    public void setF_48(String f_48) {
        this.f_48 = f_48;
    }

    public String getF_52() {
        return f_52;
    }

    public void setF_52(String f_52) {
        this.f_52 = f_52;
    }

    public String getF_54() {
        return f_54;
    }

    public void setF_54(String f_54) {
        this.f_54 = f_54;
    }

    public String getF_55() {
        return f_55;
    }

    public void setF_55(String f_55) {
        this.f_55 = f_55;
    }

    public String getF_60() {
        return f_60;
    }

    public void setF_60(String f_60) {
        this.f_60 = f_60;
    }

    public String getF_61() {
        return f_61;
    }

    public void setF_61(String f_61) {
        this.f_61 = f_61;
    }

    public String getF_62() {
        return f_62;
    }

    public void setF_62(String f_62) {
        this.f_62 = f_62;
    }

    public String getF_63() {
        return f_63;
    }

    public void setF_63(String f_63) {
        this.f_63 = f_63;
    }

    public Map<String, String> getF_02_Map() {
        return f_02_Map;
    }

    public void setF_02_Map(Map<String, String> f_02_Map) {
        this.f_02_Map = f_02_Map;
    }

    public Map<String, String> getF_03_Map() {
        return f_03_Map;
    }

    public void setF_03_Map(Map<String, String> f_03_Map) {
        this.f_03_Map = f_03_Map;
    }

    public Map<String, String> getF_04_Map() {
        return f_04_Map;
    }

    public void setF_04_Map(Map<String, String> f_04_Map) {
        this.f_04_Map = f_04_Map;
    }

    public Map<String, String> getF_11_Map() {
        return f_11_Map;
    }

    public void setF_11_Map(Map<String, String> f_11_Map) {
        this.f_11_Map = f_11_Map;
    }

    public Map<String, String> getF_12_Map() {
        return f_12_Map;
    }

    public void setF_12_Map(Map<String, String> f_12_Map) {
        this.f_12_Map = f_12_Map;
    }

    public Map<String, String> getF_13_Map() {
        return f_13_Map;
    }

    public void setF_13_Map(Map<String, String> f_13_Map) {
        this.f_13_Map = f_13_Map;
    }

    public Map<String, String> getF_14_Map() {
        return f_14_Map;
    }

    public void setF_14_Map(Map<String, String> f_14_Map) {
        this.f_14_Map = f_14_Map;
    }

    public Map<String, String> getF_22_Map() {
        return f_22_Map;
    }

    public void setF_22_Map(Map<String, String> f_22_Map) {
        this.f_22_Map = f_22_Map;
    }

    public Map<String, String> getF_23_Map() {
        return f_23_Map;
    }

    public void setF_23_Map(Map<String, String> f_23_Map) {
        this.f_23_Map = f_23_Map;
    }

    public Map<String, String> getF_24_Map() {
        return f_24_Map;
    }

    public void setF_24_Map(Map<String, String> f_24_Map) {
        this.f_24_Map = f_24_Map;
    }

    public Map<String, String> getF_25_Map() {
        return f_25_Map;
    }

    public void setF_25_Map(Map<String, String> f_25_Map) {
        this.f_25_Map = f_25_Map;
    }

    public Map<String, String> getF_35_Map() {
        return f_35_Map;
    }

    public void setF_35_Map(Map<String, String> f_35_Map) {
        this.f_35_Map = f_35_Map;
    }

    public Map<String, String> getF_37_Map() {
        return f_37_Map;
    }

    public void setF_37_Map(Map<String, String> f_37_Map) {
        this.f_37_Map = f_37_Map;
    }

    public Map<String, String> getF_38_Map() {
        return f_38_Map;
    }

    public void setF_38_Map(Map<String, String> f_38_Map) {
        this.f_38_Map = f_38_Map;
    }

    public Map<String, String> getF_39_Map() {
        return f_39_Map;
    }

    public void setF_39_Map(Map<String, String> f_39_Map) {
        this.f_39_Map = f_39_Map;
    }

    public Map<String, String> getF_41_Map() {
        return f_41_Map;
    }

    public void setF_41_Map(Map<String, String> f_41_Map) {
        this.f_41_Map = f_41_Map;
    }

    public Map<String, String> getF_42_Map() {
        return f_42_Map;
    }

    public void setF_42_Map(Map<String, String> f_42_Map) {
        this.f_42_Map = f_42_Map;
    }

    public Map<String, String> getF_45_Map() {
        return f_45_Map;
    }

    public void setF_45_Map(Map<String, String> f_45_Map) {
        this.f_45_Map = f_45_Map;
    }

    public Map<String, String> getF_47_Map() {
        return f_47_Map;
    }

    public void setF_47_Map(Map<String, String> f_47_Map) {
        this.f_47_Map = f_47_Map;
    }

    public Map<String, String> getF_48_Map() {
        return f_48_Map;
    }

    public void setF_48_Map(Map<String, String> f_48_Map) {
        this.f_48_Map = f_48_Map;
    }

    public Map<String, String> getF_52_Map() {
        return f_52_Map;
    }

    public void setF_52_Map(Map<String, String> f_52_Map) {
        this.f_52_Map = f_52_Map;
    }

    public Map<String, String> getF_54_Map() {
        return f_54_Map;
    }

    public void setF_54_Map(Map<String, String> f_54_Map) {
        this.f_54_Map = f_54_Map;
    }

    public Map<String, String> getF_55_Map() {
        return f_55_Map;
    }

    public void setF_55_Map(Map<String, String> f_55_Map) {
        this.f_55_Map = f_55_Map;
    }

    public Map<String, String> getF_60_Map() {
        return f_60_Map;
    }

    public void setF_60_Map(Map<String, String> f_60_Map) {
        this.f_60_Map = f_60_Map;
    }

    public Map<String, String> getF_61_Map() {
        return f_61_Map;
    }

    public void setF_61_Map(Map<String, String> f_61_Map) {
        this.f_61_Map = f_61_Map;
    }

    public Map<String, String> getF_62_Map() {
        return f_62_Map;
    }

    public void setF_62_Map(Map<String, String> f_62_Map) {
        this.f_62_Map = f_62_Map;
    }

    public Map<String, String> getF_63_Map() {
        return f_63_Map;
    }

    public void setF_63_Map(Map<String, String> f_63_Map) {
        this.f_63_Map = f_63_Map;
    }

    /**
     * @return the PageCode
     */
    public String getPageCode() {
        return PageCode;
    }

    /**
     * @param PageCode the PageCode to set
     */
    public void setPageCode(String PageCode) {
        this.PageCode = PageCode;
    }

    /**
     * @return the Module
     */
    public String getModule() {
        return Module;
    }

    /**
     * @param Module the Module to set
     */
    public void setModule(String Module) {
        this.Module = Module;
    }

    /**
     * @return the Section
     */
    public String getSection() {
        return Section;
    }

    /**
     * @param Section the Section to set
     */
    public void setSection(String Section) {
        this.Section = Section;
    }

    /**
     * @return the view
     */
    public boolean isView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(boolean view) {
        this.view = view;
    }
    
    public String getToken() {
         return Token;
    }
    /**
     * 
     * @param Token the token for CSRF
     */
    public void setToken(String Token) {
        this.Token = Token;
    }

}
