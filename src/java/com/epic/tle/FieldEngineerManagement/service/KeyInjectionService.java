/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

/**
 *
 * @author kasun_k
 */
import com.epic.tle.FieldEngineerManagement.bean.Terminal;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyInectingConfig;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyMailConfiguration;
import com.epic.tle.FieldEngineerManagement.smartcard.PrinterConfig;
import com.epic.tle.mapping.EpicTlePortconfig;
import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.PortConfig;
import java.util.List;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;

public class KeyInjectionService implements KeyInjectionServiceInf {

    public Terminal getTerminal(String terminalKI) throws Exception {
        Terminal terminal = new Terminal();
        List<EpicTleTerminal> epictleerminal = null;
        Session session = null;
        System.out.println("terminalKI----------"+terminalKI);
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.setCacheMode(CacheMode.IGNORE);
            session.beginTransaction();
            String sql = "from EpicTleTerminal tr where tr.tid=:id";
            Query query = session.createQuery(sql);
            query.setString("id", terminalKI);
            epictleerminal = query.list();

            if (0 < epictleerminal.size()) {
                terminal.setTid(epictleerminal.get(0).getTid());
                terminal.setMid(epictleerminal.get(0).getMid());
                terminal.setSerialNo(epictleerminal.get(0).getSerialNo());
                terminal.setBank(epictleerminal.get(0).getBank());
                terminal.setName(epictleerminal.get(0).getName());
                terminal.setLocation(epictleerminal.get(0).getLocation());
                terminal.setRegDate(epictleerminal.get(0).getRegdate());
                terminal.setEncStatus(epictleerminal.get(0).getEpicTleEncryptionlevles().getCode());
                terminal.setStatus(epictleerminal.get(0).getEpicTleStatusByStatus().getCode());
                terminal.setEncStatusName(epictleerminal.get(0).getEpicTleEncryptionlevles().getDescription());
                terminal.setStatusName(epictleerminal.get(0).getEpicTleStatusByStatus().getDescription());
                terminal.setBrand(epictleerminal.get(0).getTerminalbrand());
                terminal.setCOUNTOR(epictleerminal.get(0).getCountor());
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
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }

        }
        return terminal;
    }

    public void getKeyInjectParems() throws Exception {
        List<EpicTlePortconfig> portConflist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
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
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
    }

    public boolean updateTerminalKeys(Terminal terminal) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleTerminal> selTerminal = null;
        try {
            System.out.println("terminal.getTid()--------------------" + terminal.getTid());
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleTerminal wu where wu.tid =:tid";
            query = session.createQuery(sql);
            query.setString("tid", terminal.getTid());
            selTerminal = query.list();
            if (selTerminal.size() > 0) {
                selTerminal.get(0).setEtmksessionkey(terminal.getETMKSESSIONKEY());
                selTerminal.get(0).setEmkesessionkey(terminal.getEMKESESSIONKEY());
                selTerminal.get(0).setKeyid(terminal.getKEYID());
                selTerminal.get(0).setTmkkvc(terminal.getTMKKVC());
                selTerminal.get(0).setMekkvc(terminal.getMEKKVC());
                selTerminal.get(0).setCountor(terminal.getCOUNTOR());

                session.save(selTerminal.get(0));
                isUpdated = true;
                terminal.setRepbankaname(selTerminal.get(0).getBank());
                terminal.setRepterminalid(selTerminal.get(0).getTid());
                terminal.setRepissuedDate(selTerminal.get(0).getRegdate());
                terminal.setRepkeyid(terminal.getKEYID());
                terminal.setRepmek(terminal.getMEKKVC());
                terminal.setRepmerchantname(selTerminal.get(0).getName());
                terminal.setReptmk(terminal.getTMKKVC());
                

            }
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
                session.flush();
                session.close();
                session = null;
            }
        }
        return isUpdated;
    }

    public void getPinMailerParems() throws Exception { //pORTcONFIGURATION
        List<EpicTlePortconfig> portConflist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTlePortconfig pc where pc.sid =:sid";
            query = session.createQuery(sql);
            query.setInteger("sid", PortConfig.PIN_MAIL); //Pin Mailer Port
            portConflist = query.list();

            if (0 < portConflist.size()) {
                PrinterConfig.DATABITS = portConflist.get(0).getDatabits();
                PrinterConfig.DATA_RATE = portConflist.get(0).getDatarate();
                PrinterConfig.PARITY = Integer.parseInt(portConflist.get(0).getParity());
                PrinterConfig.STOPBITS = portConflist.get(0).getStopbits();
                PrinterConfig.PORT = portConflist.get(0).getPort();
                PrinterConfig.STATUS = portConflist.get(0).getStatus();

                System.out.println("PrinterConfig.STATUS = portConflist.get(0).getStatus()==" + portConflist.get(0).getStatus());
                System.out.println("portConflist.get(0).getDescription()==" + portConflist.get(0).getDescription());

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

    public void getKeyMailerParems() throws Exception { //pORTcONFIGURATION
        List<EpicTlePortconfig> portConflist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTlePortconfig pc where pc.sid =:sid";
            query = session.createQuery(sql);
            query.setInteger("sid", PortConfig.KEY_MAIL); //Pin Mailer Port
            portConflist = query.list();

            if (0 < portConflist.size()) {
                KeyMailConfiguration.DATABITS = portConflist.get(0).getDatabits();
                KeyMailConfiguration.DATA_RATE = portConflist.get(0).getDatarate();
                KeyMailConfiguration.PARITY = Integer.parseInt(portConflist.get(0).getParity());
                KeyMailConfiguration.STOPBITS = portConflist.get(0).getStopbits();
                KeyMailConfiguration.PORT = portConflist.get(0).getPort();
                KeyMailConfiguration.STATUS = portConflist.get(0).getStatus();

                System.out.println("PrinterConfig.STATUS = portConflist.get(0).getStatus()==" + portConflist.get(0).getStatus());
                System.out.println("portConflist.get(0).getDescription()==" + portConflist.get(0).getDescription());

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

}
