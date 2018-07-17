/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thilina_t
 */
public class NIIBean {
    
    private Integer NiiId;
    private String niiName;
    private String Nii;
    private String mapNii;
    private int group;
    private String status;
    private Map<Integer,String> ChannelMap=new HashMap<Integer, String>();

    private boolean add;
    private boolean delete;
    private boolean view;
    private boolean update;

    private long fullCount;

    //table
    private List<NIIBean> gridModel;
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

    //****************Search**********************
    private String searchName = "";

    private String delsuccess;
    private String message;
    private boolean success;

    //****************Edit**********************
    private Integer upid;
    private String upniiConfName;
    private String upniiConfDes;
    private String upChannelName;
    private int upChannel;
    private Date updatetime;
    private String upstatus;

    //****************delete**********************
    private String DbinId;
    private boolean isDeleted;
    private String dmessage;

    /**
     * @return the niiConfName
     */
    public String getNiiConfName() {
        return getNiiName();
    }

    /**
     * @param niiConfName the niiConfName to set
     */
    public void setNiiConfName(String niiName) {
        this.setNiiName(niiName);
    }

    /**
     * @return the niiConfDes
     */
    public String getNiiConfDes() {
        return getMapNii();
    }

    /**
     * @param niiConfDes the niiConfDes to set
     */
    public void setNiiConfDes(String mapNii) {
        this.setMapNii(mapNii);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the ChannelMap
     */
    public Map<Integer,String> getChannelMap() {
        return ChannelMap;
    }

    /**
     * @param ChannelMap the ChannelMap to set
     */
    public void setChannelMap(Map<Integer,String> ChannelMap) {
        this.ChannelMap = ChannelMap;
    }

    /**
     * @return the add
     */
    public boolean isAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(boolean add) {
        this.add = add;
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
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

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * @return the fullCount
     */
    public long getFullCount() {
        return fullCount;
    }

    /**
     * @param fullCount the fullCount to set
     */
    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    /**
     * @return the searchName
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName the searchName to set
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * @return the delsuccess
     */
    public String getDelsuccess() {
        return delsuccess;
    }

    /**
     * @param delsuccess the delsuccess to set
     */
    public void setDelsuccess(String delsuccess) {
        this.delsuccess = delsuccess;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the DbinId
     */
    public String getDbinId() {
        return DbinId;
    }

    /**
     * @param DbinId the DbinId to set
     */
    public void setDbinId(String DbinId) {
        this.DbinId = DbinId;
    }

    /**
     * @return the isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the dmessage
     */
    public String getDmessage() {
        return dmessage;
    }

    /**
     * @param dmessage the dmessage to set
     */
    public void setDmessage(String dmessage) {
        this.dmessage = dmessage;
    }

    /**
     * @return the gridModel
     */
    public List<NIIBean> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel the gridModel to set
     */
    public void setGridModel(List<NIIBean> gridModel) {
        this.gridModel = gridModel;
    }

    /**
     * @return the rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the records
     */
    public Long getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Long records) {
        this.records = records;
    }

    /**
     * @return the sord
     */
    public String getSord() {
        return sord;
    }

    /**
     * @param sord the sord to set
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return the sidx
     */
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx the sidx to set
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    /**
     * @return the searchField
     */
    public String getSearchField() {
        return searchField;
    }

    /**
     * @param searchField the searchField to set
     */
    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    /**
     * @return the searchString
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * @param searchString the searchString to set
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * @return the searchOper
     */
    public String getSearchOper() {
        return searchOper;
    }

    /**
     * @param searchOper the searchOper to set
     */
    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    /**
     * @return the loadonce
     */
    public boolean isLoadonce() {
        return loadonce;
    }

    /**
     * @param loadonce the loadonce to set
     */
    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    /**
     * @return the upid
     */
    public Integer getUpid() {
        return upid;
    }

    /**
     * @param upid the upid to set
     */
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    /**
     * @return the upniiConfName
     */
    public String getUpniiConfName() {
        return upniiConfName;
    }

    /**
     * @param upniiConfName the upniiConfName to set
     */
    public void setUpniiConfName(String upniiConfName) {
        this.upniiConfName = upniiConfName;
    }

    /**
     * @return the upniiConfDes
     */
    public String getUpniiConfDes() {
        return upniiConfDes;
    }

    /**
     * @param upniiConfDes the upniiConfDes to set
     */
    public void setUpniiConfDes(String upniiConfDes) {
        this.upniiConfDes = upniiConfDes;
    }

    /**
     * @return the upChannelName
     */
    public String getUpChannelName() {
        return upChannelName;
    }

    /**
     * @param upChannelName the upChannelName to set
     */
    public void setUpChannelName(String upChannelName) {
        this.upChannelName = upChannelName;
    }

    /**
     * @return the upChannel
     */
    public int getUpChannel() {
        return upChannel;
    }

    /**
     * @param upChannel the upChannel to set
     */
    public void setUpChannel(int upChannel) {
        this.upChannel = upChannel;
    }

    /**
     * @return the updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime the updatetime to set
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return the upstatus
     */
    public String getUpstatus() {
        return upstatus;
    }

    /**
     * @param upstatus the upstatus to set
     */
    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }

    /**
     * @return the group
     */
    public int getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(int group) {
        this.group = group;
    }

    /**
     * @return the Nii
     */
    public String getNii() {
        return Nii;
    }

    /**
     * @param Nii the Nii to set
     */
    public void setNii(String Nii) {
        this.Nii = Nii;
    }

    /**
     * @return the NiiId
     */
    public Integer getNiiId() {
        return NiiId;
    }

    /**
     * @param NiiId the NiiId to set
     */
    public void setNiiId(Integer NiiId) {
        this.NiiId = NiiId;
    }

    /**
     * @return the niiName
     */
    public String getNiiName() {
        return niiName;
    }

    /**
     * @param niiName the niiName to set
     */
    public void setNiiName(String niiName) {
        this.niiName = niiName;
    }

    /**
     * @return the mapNii
     */
    public String getMapNii() {
        return mapNii;
    }

    /**
     * @param mapNii the mapNii to set
     */
    public void setMapNii(String mapNii) {
        this.mapNii = mapNii;
    }

}
