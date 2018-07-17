/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerBean;
import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerInputBean;
import com.epic.tle.FieldEngineerManagement.bean.FieldEngineer;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyInectingConfig;
import com.epic.tle.FieldEngineerManagement.smartcard.PrinterConfig;
import com.epic.tle.mapping.EpicTleAlgorithem;
import com.epic.tle.mapping.EpicTleCardholders;
import com.epic.tle.mapping.EpicTlePinverficationmethod;
import com.epic.tle.mapping.EpicTlePortconfig;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleSelecteddevice;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.PortConfig;
import com.epic.tle.util.constant.Status;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author kasun_k
 */
public class RegisterFieldEngineerService implements RegisterFieldEngineerServiceInf {

    public void getdevivetype(RegisterFieldEngineerInputBean inputBean) throws Exception {
        List<EpicTleSelecteddevice> tleDeviceTypes = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.setCacheMode(CacheMode.IGNORE);
            session.beginTransaction();
            String sql = "from EpicTleSelecteddevice ";
            Query query = session.createQuery(sql);
            tleDeviceTypes = query.list();

            for (int i = 0; i < tleDeviceTypes.size(); i++) {
                inputBean.getDeviceTypeMap().put(tleDeviceTypes.get(i).getCode(), tleDeviceTypes.get(i).getDescription());
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
    }

    public void getAlgoMap(RegisterFieldEngineerInputBean inputBean) throws Exception {

        List<EpicTleAlgorithem> tlealgoTypes = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleAlgorithem ";
            Query query = session.createQuery(sql);
            tlealgoTypes = query.list();

            for (int i = 0; i < tlealgoTypes.size(); i++) {
                inputBean.getAlgoMap().put(tlealgoTypes.get(i).getCode(), tlealgoTypes.get(i).getDescription());
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    public void getPinVerTypeMap(RegisterFieldEngineerInputBean inputBean) throws Exception {

        List<EpicTlePinverficationmethod> tlepinVerifi = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTlePinverficationmethod ";
            Query query = session.createQuery(sql);
            tlepinVerifi = query.list();

            for (int i = 0; i < tlepinVerifi.size(); i++) {
                inputBean.getPinVerTypeMap().put(tlepinVerifi.get(i).getCode(), tlepinVerifi.get(i).getDescription());
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    public void getPinMailerParems() throws Exception { //pORTcONFIGURATION
        List<EpicTlePortconfig> portConflist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTlePortconfig pc where pc.sid =:sid";
            query = session.createQuery(sql);
            query.setInteger("sid", PortConfig.PIN_MAIL); //Pin Mailer Port
            portConflist = query.list();
            System.out.println("PortConfig.PIN_MAIL-------- " + PortConfig.PIN_MAIL);

            if (0 < portConflist.size()) {
                PrinterConfig.DATABITS = portConflist.get(0).getDatabits();
                PrinterConfig.DATA_RATE = portConflist.get(0).getDatarate();
                PrinterConfig.PARITY = Integer.parseInt(portConflist.get(0).getParity());
                PrinterConfig.STOPBITS = portConflist.get(0).getStopbits();
                PrinterConfig.PORT = portConflist.get(0).getPort();
                PrinterConfig.STATUS = portConflist.get(0).getStatus();

                System.out.println("DATABITS-------- " + PrinterConfig.DATABITS);
                System.out.println("DATA_RATE-------- " + PrinterConfig.DATA_RATE);
                System.out.println("PARITY-------- " + PrinterConfig.PARITY);
                System.out.println("STOPBITS-------- " + PrinterConfig.STOPBITS);
                System.out.println("PORT-------- " + PrinterConfig.PORT);
                System.out.println("STATUS-------- " + PrinterConfig.STATUS);

            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    public boolean isSerialNumExist(String sERIALNO) throws Exception {
        List<EpicTleCardholders> cardHolderlist = null;
        Session session = null;
        Query query = null;
        boolean isExists = false;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleCardholders ch where ch.serialno =:serialno";
            query = session.createQuery(sql);
            query.setString("serialno", sERIALNO);
            cardHolderlist = query.list();
            if (0 < cardHolderlist.size()) {
                isExists = true;
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return isExists;
    }

    public boolean insertFieldEngineer(FieldEngineer fieldEng) throws Exception {
        boolean isAddUser = false;
        EpicTleCardholders cardHolder = null;
        Session session = null;

        try {
            System.out.println("field ingineer service ----------- " + fieldEng.getSERIALNO());
            session = HibernateInit.sessionFactory.openSession();
            cardHolder = new EpicTleCardholders();
            cardHolder.setSerialno(fieldEng.getSERIALNO());
            cardHolder.setOfficername(fieldEng.getOFFICERNAME());
            cardHolder.setBankname(fieldEng.getBANKNAME());
            cardHolder.setLocation(fieldEng.getLOCATION());
            cardHolder.setMaxtmkdownlod(fieldEng.getMAXTMKDOWNLOD());
            cardHolder.setPinblock(fieldEng.getPINBLOCK());
            cardHolder.setRegdate(Util.getLocalDate());
            cardHolder.setBdkid(fieldEng.getBDKINDEX());
            EpicTleSelecteddevice seldev = new EpicTleSelecteddevice();
            seldev.setCode(fieldEng.getSELECTEDDEVICE());
            cardHolder.setEpicTleSelecteddevice(seldev);

            EpicTleAlgorithem algo = new EpicTleAlgorithem();
            algo.setCode(fieldEng.getALGORITHEM());
            cardHolder.setEpicTleAlgorithem(algo);

            EpicTleStatus sts = new EpicTleStatus();
            sts.setCode(Status.ACTIVE);
            cardHolder.setEpicTleStatus(sts);

            EpicTlePinverficationmethod pinVerM = new EpicTlePinverficationmethod();
            pinVerM.setCode(fieldEng.getPINVERFICATION());
            cardHolder.setEpicTlePinverficationmethod(pinVerM);

//                        cardHolder.setRegdate(new java.sql.Date(fieldEng.getREGDATE().getTime()));
            if (fieldEng.getPINBLOCK() != null) {
                cardHolder.setPinblock(fieldEng.getPINBLOCK());
            } else {
                cardHolder.setPinblock("1010101010101010");
            }
            if (fieldEng.getPINBLOCK() != null) {
                cardHolder.setPinblock(fieldEng.getPINBLOCK());
            } else {
                cardHolder.setPinblock("1010101010101010");
            }
            if (fieldEng.getMODULES() != null) {
                cardHolder.setModules(fieldEng.getMODULES());
            } else {
                cardHolder.setModules("10101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
            }
            if (fieldEng.getEXPONENT() != null) {
                cardHolder.setExponent(fieldEng.getEXPONENT());
            } else {
                cardHolder.setExponent("101010");
            }
            cardHolder.setKvc(fieldEng.getKVC());
            cardHolder.setEfwk(fieldEng.getEFWK());

            session.beginTransaction();
            session.save(cardHolder);
            session.getTransaction().commit();
            isAddUser = true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.flush();
                session.close();
                session = null;
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return isAddUser;
    }

    public void getKeyInjectParems() throws Exception {
        List<EpicTlePortconfig> portConflist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTlePortconfig pc where pc.sid =:sid";
            query = session.createQuery(sql);
            query.setInteger("sid", PortConfig.KEY_INJECTION);
            portConflist = query.list();

            if (0 < portConflist.size()) {
                KeyInectingConfig.DATABITS = portConflist.get(0).getDatabits();
                KeyInectingConfig.DATA_RATE = portConflist.get(0).getDatarate();
                KeyInectingConfig.PARITY = Integer.parseInt(portConflist.get(0).getParity());
                KeyInectingConfig.STOPBITS = portConflist.get(0).getStopbits();
                KeyInectingConfig.PORT = portConflist.get(0).getPort();
                KeyInectingConfig.STATUS = portConflist.get(0).getStatus();

            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    public List<RegisterFieldEngineerBean> loadFldEngCardHolderDetail(RegisterFieldEngineerInputBean inputBean, int max, int first, String orderBy) throws Exception {
        List<RegisterFieldEngineerBean> dataList = new ArrayList<RegisterFieldEngineerBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by ch.sid desc ";

            }

            if (inputBean.getAlgorithm().equals("-1")) {
                inputBean.setAlgorithm("");
            }
            if (inputBean.getSelectedDevice().equals("-1")) {
                inputBean.setSelectedDevice("");
            }
            if (inputBean.getPinVerification().equals("-1")) {
                inputBean.setPinVerification("");
            }
            if (inputBean.getBdkindex().equals("-1")) {
                inputBean.setBdkindex("");
            }

            long count = 0;
            Query queryCount;
            Query querySearch;

            session = HibernateInit.sessionFactory.openSession();

            String q = "";
            if (!inputBean.getBdkindex().equals("")) {
                q = "and ch.bdkid =:bdkid ";
            }
            String q1 = "";
            if (!inputBean.getSelectedDevice().equals("")) {
                q1 = "and ch.epicTleSelecteddevice.code =:code ";
            }

            String sqlCount = "select count(sid) from EpicTleCardholders ch where   ch.serialno LIKE :serialno and  ch.officername LIKE :officerName and ch.location LIKE :locations and ch.epicTleAlgorithem.code LIKE :algorithm and ch.epicTlePinverficationmethod.code LIKE :pinVerification " + q + q1 + orderBy;
            queryCount = session.createQuery(sqlCount);

//            queryCount.setParameter("statuscode", Status.ACTIVE);
            queryCount.setParameter("serialno", "%" + inputBean.getSearchSerial() + "%");
//            queryCount.setParameter("selectedDevice", "%" + inputBean.getSelectedDevice() + "%");
            queryCount.setParameter("officerName", "%" + inputBean.getOfficerName() + "%");
            queryCount.setParameter("locations", "%" + inputBean.getLocations() + "%");
            queryCount.setString("algorithm", "%" + inputBean.getAlgorithm() + "%");
            queryCount.setString("pinVerification", "%" + inputBean.getPinVerification() + "%");
            if (!inputBean.getBdkindex().equals("")) {
                queryCount.setInteger("bdkid", Integer.parseInt(inputBean.getBdkindex()));
            }
            if (!inputBean.getSelectedDevice().equals("")) {
                queryCount.setInteger("code", Integer.parseInt(inputBean.getSelectedDevice()));
            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            System.out.println("count " + count);

            if (count > 0) {

                String sqlSearch = " from EpicTleCardholders ch where  ch.serialno LIKE :serialno and  ch.officername LIKE :officerName and ch.location LIKE :locations and ch.epicTleAlgorithem.code LIKE :algorithm and ch.epicTlePinverficationmethod.code LIKE :pinVerification " + q + orderBy;
                querySearch = session.createQuery(sqlSearch);
//                querySearch.setInteger("statuscode", Status.ACTIVE);
                querySearch.setParameter("serialno", "%" + inputBean.getSearchSerial() + "%");
//                querySearch.setParameter("selectedDevice", "%" + inputBean.getSelectedDevice() + "%");
                querySearch.setParameter("officerName", "%" + inputBean.getOfficerName() + "%");
                querySearch.setParameter("locations", "%" + inputBean.getLocations() + "%");
                querySearch.setString("algorithm", "%" + inputBean.getAlgorithm() + "%");
                querySearch.setString("pinVerification", "%" + inputBean.getPinVerification() + "%");
                if (!inputBean.getBdkindex().equals("")) {
                    querySearch.setInteger("bdkid", Integer.parseInt(inputBean.getBdkindex()));
                }
                if (!inputBean.getSelectedDevice().equals("")) {
                    queryCount.setInteger("code", Integer.parseInt(inputBean.getSelectedDevice()));
                }
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    RegisterFieldEngineerBean databean = new RegisterFieldEngineerBean();

                    EpicTleCardholders objBean = (EpicTleCardholders) it.next();

                    try {
                        databean.setBdkindex("" + objBean.getBdkid());

                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                        databean.setBdkindex("--");
                    }

                    try {
                        databean.setSid("" + objBean.getSid());

                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                        databean.setSid("--");
                    }
                    try {
                        databean.setSerialno(objBean.getSerialno());
                    } catch (NullPointerException npe) {
                        databean.setSerialno("--");
                    }
                    try {
                        databean.setSelecteddevice(objBean.getEpicTleSelecteddevice().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setSelecteddevice("--");
                    }
                    try {
                        databean.setOfficername(objBean.getOfficername());
                    } catch (NullPointerException npe) {
                        databean.setOfficername("--");
                    }
                    try {
                        databean.setBankname(objBean.getBankname());
                    } catch (NullPointerException npe) {
                        databean.setBankname("--");
                    }
                    try {
                        databean.setLocation(objBean.getLocation());
                    } catch (NullPointerException npe) {
                        databean.setLocation("--");
                    }
                    try {
                        if (objBean.getRegdate() == null) {
                            databean.setRegdate("0000-00-00");
                        } else {
                            databean.setRegdate(objBean.getRegdate().toString());
                        }
                    } catch (NullPointerException npe) {
                        databean.setRegdate("--");
                    }
                    try {
                        databean.setMaxtmkdownlod("" + objBean.getMaxtmkdownlod());
                    } catch (NullPointerException npe) {
                        databean.setMaxtmkdownlod("--");
                    }
                    try {
                        databean.setAvaliabletmkdownlod("" + (objBean.getMaxtmkdownlod() - objBean.getMaxcountor()));
                    } catch (NullPointerException npe) {
                        databean.setAvaliabletmkdownlod("--");
                    }
                    try {
                        databean.setStatus("" + objBean.getEpicTleStatus().getCode());
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setAlgorithem(objBean.getEpicTleAlgorithem().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setPinVerfi(objBean.getEpicTlePinverficationmethod().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    databean.setFullCount(count);

                    dataList.add(databean);
                }
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            e.printStackTrace();
            throw e;

        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return dataList;
    }

    public boolean deleteFE(RegisterFieldEngineerInputBean inputBean) throws Exception {

        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleCardholders ch"
                    + "  where ch.serialno =:serialno";
            query = session.createQuery(sql);

            query.setString("serialno", inputBean.getDserialNumber());
            int result = query.executeUpdate();
            if (1 == result) {
                isFeDeleted = true;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return isFeDeleted;
    }

    public void findFieldEngineer(RegisterFieldEngineerInputBean inputBean) throws Exception {
        List<EpicTleCardholders> findfieldengineer = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleCardholders ch where ch.serialno =:serialno";
            query = session.createQuery(sql);
            query.setString("serialno", inputBean.getUpserialNumber());
            findfieldengineer = query.list();

            if (0 < findfieldengineer.size()) {
                inputBean.setUpserialNumber(findfieldengineer.get(0).getSerialno());
                inputBean.setUpfldEngName(findfieldengineer.get(0).getOfficername());
                inputBean.setUpbankName(findfieldengineer.get(0).getBankname());
                inputBean.setUplocation(findfieldengineer.get(0).getLocation());
                inputBean.setUpmaxKeyDown("" + findfieldengineer.get(0).getMaxtmkdownlod());
                inputBean.setUpstatus("" + findfieldengineer.get(0).getEpicTleStatus().getCode());
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }

    }

    public boolean updateFieldEng(RegisterFieldEngineerInputBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleCardholders> tlefieldeng = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleCardholders ch where ch.serialno =:serialno";
            query = session.createQuery(sql);
            query.setString("serialno", inputBean.getUpserialNumber());
            tlefieldeng = query.list();
            if (tlefieldeng.size() > 0) {
                tlefieldeng.get(0).setOfficername(inputBean.getUpfldEngName());
                tlefieldeng.get(0).setBankname(inputBean.getUpbankName());
                tlefieldeng.get(0).setLocation(inputBean.getUplocation());
                tlefieldeng.get(0).setMaxtmkdownlod(Integer.parseInt(inputBean.getUpmaxKeyDown()));
                EpicTleStatus st = new EpicTleStatus();
                st.setCode(Integer.parseInt(inputBean.getUpstatus()));
                tlefieldeng.get(0).setEpicTleStatus(st);

                session.save(tlefieldeng.get(0));
                session.getTransaction().commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return isUpdated;
    }

    public void getfieldEngData(String upserialNumber, FieldEngineer fieldEng) throws Exception {

        List<EpicTleCardholders> findfieldengineer = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleCardholders ch where ch.serialno =:serialno";
            query = session.createQuery(sql);
            query.setString("serialno", upserialNumber);
            findfieldengineer = query.list();

            if (0 < findfieldengineer.size()) {
                fieldEng.setSERIALNO(findfieldengineer.get(0).getSerialno());
                fieldEng.setALGORITHEM(findfieldengineer.get(0).getEpicTleAlgorithem().getCode());
                fieldEng.setSELECTEDDEVICE(findfieldengineer.get(0).getEpicTleSelecteddevice().getCode());
                fieldEng.setPINVERFICATION(findfieldengineer.get(0).getEpicTlePinverficationmethod().getCode());
                fieldEng.setOFFICERNAME(findfieldengineer.get(0).getOfficername());
                fieldEng.setBANKNAME(findfieldengineer.get(0).getBankname());

            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }

    }

    public boolean updateFieldEngineer(FieldEngineer fieldEng) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleCardholders> tlefieldeng = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleCardholders ch where ch.serialno =:serialno";
            query = session.createQuery(sql);
            query.setString("serialno", fieldEng.getSERIALNO());
            tlefieldeng = query.list();
            if (tlefieldeng.size() > 0) {
                tlefieldeng.get(0).setPinblock(fieldEng.getPINBLOCK());
                tlefieldeng.get(0).setMaxpincountor(fieldEng.getMAXPINCOUNTOR());

                session.save(tlefieldeng.get(0));
                session.getTransaction().commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return isUpdated;
    }

    public RegisterFieldEngineerInputBean getPagePath(String page, RegisterFieldEngineerInputBean inputBean) throws Exception {
        if (page != null && page != "") {
            String module = page.substring(0, 2);
            Session session = null;
            String pagePath = "";

            try {

                session = HibernateInit.sessionFactory.openSession();
                session.beginTransaction();
                EpicTleSection epicTleSection = (EpicTleSection) session.get(EpicTleSection.class, page);
                String mod = epicTleSection.getEpicTleModule().getDescription();
                String sect = epicTleSection.getSectionName();

                inputBean.setModule(mod);
                inputBean.setSection(sect);

            } catch (Exception e) {
                if (session != null) {
                    session.flush();
                    session.close();
                    session = null;
                }
                throw e;
            } finally {
                if (session != null) {
                    session.flush();
                    session.close();
                    session = null;
                }
            }
        }
        return inputBean;

    }
}
