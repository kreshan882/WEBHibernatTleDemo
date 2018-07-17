/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.profile.bean;

import com.epic.tle.profile.bean.ModulePagesBean;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chalaka_n
 */
public class UserProfileInputBean {
    /************** For search **********************/
    private String searchName="";
    
    private String PagePath="";
    private String PageCode="";

    /*------------------------list data table  ------------------------------*/
    private List<UserProfileBean> gridModel=new ArrayList<UserProfileBean>();
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private boolean loadonce = false;
    
    /************** For update **********************/
    private String name;
    private int upProfileID;
    private String upName;
    private int upStatus;
    private Map<String,String> modulesMap = new HashMap<String, String>();
    private Map<Integer, String> upStatusMap = Util.getBasicStatus();
    
    private Map<String,String> alreadyAcsessPageMap = new HashMap<String, String>();
    private Map<String,String> allPageMap = new HashMap<String, String>();
    private Map<String,String> unSelectedTaskMap = new HashMap<String, String>();
    private Map<String,String> SelectedTaskMap = new HashMap<String, String>();

    private List<String> selectedpages = null;
    private String selectModule;
    
    
            /************** For delete **********************/
    private int profileID;
    private  String delsuccess;
    private  String message;
    private boolean success;
    
       //***************Working Path*************
    private String Module="";
    private String Section="";
    
    /******************* Add User ***************************/
    
     private String profilename;
//    private String description;
    private String modules;
    private String pages;
    private String taskString;
    private static Map<Integer, List<ModulePagesBean>> modulesPagesMap = new HashMap<Integer, List<ModulePagesBean>>();

    private List<String> selecterpages;
    private List<String> unselecterpages;

    private Map<String, String> pagesMap = new HashMap<String, String>();
    private Map<String, String> pagesFinalMap = new HashMap<String, String>();

    private List<String> newBox2;
    private Map<String, String> newList2 = new HashMap<String, String>();
    
    private String flag;
    
    /************* view User ****************/
    
    private boolean add;
    private boolean edit;
    private boolean update;
    private boolean delete;
    private boolean view;
    
    //***************Token************************
    private String Token;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTaskString() {
        return taskString;
    }

    public void setTaskString(String taskString) {
        this.taskString = taskString;
    }
    
    public Map<String, String> getUnSelectedTaskMap() {
        return unSelectedTaskMap;
    }

    public void setUnSelectedTaskMap(Map<String, String> unSelectedTaskMap) {
        this.unSelectedTaskMap = unSelectedTaskMap;
    }

    public Map<String, String> getSelectedTaskMap() {
        return SelectedTaskMap;
    }

    public void setSelectedTaskMap(Map<String, String> SelectedTaskMap) {
        this.SelectedTaskMap = SelectedTaskMap;
    }

    
    public Map<String, String> getModulesMap() {
        return modulesMap;
    }

    public void setModulesMap(Map<String, String> modulesMap) {
        this.modulesMap = modulesMap;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
    
    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<UserProfileBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<UserProfileBean> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpProfileID() {
        return upProfileID;
    }

    public void setUpProfileID(int upProfileID) {
        this.upProfileID = upProfileID;
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public int getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(int upStatus) {
        this.upStatus = upStatus;
    }

    public Map<Integer, String> getUpStatusMap() {
        return upStatusMap;
    }

    public void setUpStatusMap(Map<Integer, String> upStatusMap) {
        this.upStatusMap = upStatusMap;
    }

    public Map<String, String> getAlreadyAcsessPageMap() {
        return alreadyAcsessPageMap;
    }

    public void setAlreadyAcsessPageMap(Map<String, String> alreadyAcsessPageMap) {
        this.alreadyAcsessPageMap = alreadyAcsessPageMap;
    }

    public Map<String, String> getAllPageMap() {
        return allPageMap;
    }

    public void setAllPageMap(Map<String, String> allPageMap) {
        this.allPageMap = allPageMap;
    }

    public List<String> getSelectedpages() {
        return selectedpages;
    }

    public void setSelectedpages(List<String> selectedpages) {
        this.selectedpages = selectedpages;
    }

    public String getSelectModule() {
        return selectModule;
    }

    public void setSelectModule(String selectModule) {
        this.selectModule = selectModule;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getDelsuccess() {
        return delsuccess;
    }

    public void setDelsuccess(String delsuccess) {
        this.delsuccess = delsuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public static Map<Integer, List<ModulePagesBean>> getModulesPagesMap() {
        return modulesPagesMap;
    }

    public static void setModulesPagesMap(Map<Integer, List<ModulePagesBean>> modulesPagesMap) {
        UserProfileInputBean.modulesPagesMap = modulesPagesMap;
    }

    public List<String> getSelecterpages() {
        return selecterpages;
    }

    public void setSelecterpages(List<String> selecterpages) {
        this.selecterpages = selecterpages;
    }

    public List<String> getUnselecterpages() {
        return unselecterpages;
    }

    public void setUnselecterpages(List<String> unselecterpages) {
        this.unselecterpages = unselecterpages;
    }

    public Map<String, String> getPagesMap() {
        return pagesMap;
    }

    public void setPagesMap(Map<String, String> pagesMap) {
        this.pagesMap = pagesMap;
    }

    public Map<String, String> getPagesFinalMap() {
        return pagesFinalMap;
    }

    public void setPagesFinalMap(Map<String, String> pagesFinalMap) {
        this.pagesFinalMap = pagesFinalMap;
    }

    public List<String> getNewBox2() {
        return newBox2;
    }

    public void setNewBox2(List<String> newBox2) {
        this.newBox2 = newBox2;
    }

    public Map<String, String> getNewList2() {
        return newList2;
    }

    public void setNewList2(Map<String, String> newList2) {
        this.newList2 = newList2;
    }

    /**
     * @return the PagePath
     */
    public String getPagePath() {
        return PagePath;
    }

    /**
     * @param PagePath the PagePath to set
     */
    public void setPagePath(String PagePath) {
        this.PagePath = PagePath;
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
