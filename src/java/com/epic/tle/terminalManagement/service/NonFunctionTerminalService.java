package com.epic.tle.terminalManagement.service;

import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalDataBean;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalInputBean;
import com.epic.tle.terminalManagement.bean.RegisterTerminalBean;
import com.epic.tle.util.HibernateInit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nipun_t
 */
public class NonFunctionTerminalService implements RegisterTerminalServiceInf{

    public List<NonFunctionTerminalDataBean> loadTerminalUsers(NonFunctionTerminalInputBean inputBean, int max, int first, String orderBy) throws Exception {

        EpicTleTerminal epicTleTerminal = new EpicTleTerminal();
        List<NonFunctionTerminalDataBean> dataList = new ArrayList<NonFunctionTerminalDataBean>();
        Session session = null;
        try {
            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.tid desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            Query queryCount;
            Query querySearch;
            if (inputBean.getFromdate() == null || inputBean.getTodate() == null) {
                String sqlCount = "select count(tid) from EpicTleTerminal wu" + orderBy;
                queryCount = session.createQuery(sqlCount);
            } else {
                String sqlCount = "select count(tid) from EpicTleTerminal wu where wu.lasttxndate >= :beginDate and wu.lasttxndate <= :endDate " + orderBy;
                queryCount = session.createQuery(sqlCount);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());

                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                queryCount.setParameter("endDate", endDate);
            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

                if (inputBean.getFromdate() == null || inputBean.getTodate() == null) {
                    String sqlSearch = "from EpicTleTerminal wu " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                } else {
                    String sqlSearch = "from EpicTleTerminal wu where wu.lasttxndate >= :beginDate and wu.lasttxndate <= :endDate " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    querySearch.setParameter("endDate", endDate);
                }

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();
                while (it.hasNext()) {
                    NonFunctionTerminalDataBean databean = new NonFunctionTerminalDataBean();
                    EpicTleTerminal objBean = (EpicTleTerminal) it.next();

                    try {
                        databean.setTid(objBean.getTid().toString());
                    } catch (NullPointerException npe) {
                        databean.setTid("--");
                    }
                    try {
                        databean.setMid(objBean.getMid());
                    } catch (Exception npe) {
                        databean.setMid("--");
                    }
                    try {
                        databean.setSerialNo(objBean.getSerialNo());
                    } catch (NullPointerException npe) {
                        databean.setSerialNo("--");
                    }
                    try {
                        databean.setTerminalBrand(objBean.getTerminalbrand());
                    } catch (NullPointerException npe) {
                        databean.setTerminalBrand("--");
                    }
                    try {
                        databean.setBank(objBean.getBank());
                    } catch (NullPointerException npe) {
                        databean.setBank("--");
                    }
                    try {
                        databean.setName(objBean.getName());
                    } catch (NullPointerException npe) {
                        databean.setName("--");
                    }
                    try {
                        databean.setLocation(objBean.getLocation());
                    } catch (NullPointerException npe) {
                        databean.setLocation("--");
                    }
                    try {
                        databean.setRegisterDate(objBean.getRegdate());
                    } catch (NullPointerException npe) {
                        databean.setRegisterDate("--");
                    }
                    try {
                        String date = objBean.getLasttxndate().toString();
                        databean.setLastTransDate(date);
                    } catch (NullPointerException npe) {
                        databean.setEncryptionStatus("--");
                    }

                    databean.setFullCount(count);

                    dataList.add(databean);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }

        return dataList;
    }

    @Override
    public void setETypeDropDown(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addTerminal(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getEncryptionStatus() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTerminalUser(String dtid) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findUser(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initiateValuesToMap(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTerminal(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RegisterTerminalBean> loadTerminalUsers(RegisterTerminalBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object generateExcelReport() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void BinPrfDropDown(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegisterTerminalBean getPagePath(String page, RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RepPrfDropDown(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String GetResult(RegisterTerminalBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
