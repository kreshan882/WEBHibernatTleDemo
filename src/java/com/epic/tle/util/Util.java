/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

//import com.epic.tle.FieldEngineerManagement.smartcard.HSMConnector;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.mapping.EpicTleUserPasswordHistory;
import com.epic.tle.util.alerts.BMLAlertSender;
import com.epic.tle.util.alerts.EpicAlertSender;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.constant.DbConfiguraitonBean;
import com.epic.tle.util.constant.SwitchConfBean;
import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
//import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jpos.iso.ISOUtil;
import org.w3c.dom.Document;

/**
 *
 * @author kreshan
 */
public class Util {

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[]{'E', 'p', 'i', 'c', 'L', 'a', 'n', 'k', 'a', 'p', 'v', 't', 'l', 't', 'd', 'z'};

    public static boolean validateString(String text) throws Exception { //  VF0
//        return text.matches("^[a-zA-Z0-9\\s_-]+$");
        return text.matches("^[\\p{L} .'-]+$");
    }

    public static boolean validateMoblieNo(String numericString) throws Exception {
        return numericString.matches("^[+ 0-9]*$") && numericString.length() > 5;
    }

    public static boolean validateNameWithSpcCharactors(String text) throws Exception {
        return text.matches(".*[a-zA-Z0-9&%$#@!~]+.*");
    }

    public static boolean validateVF0(String text) throws Exception { //  VF0
        return text.matches("^[a-zA-Z0-9\\s_-]+$");
    }

    public static boolean validateNAME(String text) throws Exception {
//        return text.matches("^[a-zA-Z0-9_]+$") && text.length() <= 50;
        return text.matches("[a-zA-Z_. ]*") && text.length() <= 50;
    }
 public static boolean validateFieldEngNAME(String text) throws Exception {
//        return text.matches("^[a-zA-Z0-9_]+$") && text.length() <= 50;
        return text.matches("[a-zA-Z0-9_. ]*") && text.length() <= 50;
    }
    public static boolean validateUSERNAME(String text) throws Exception {
        return text.matches("^[a-zA-Z0-9_]+$") && text.length() <= 50;
    }

    public static boolean validateNUMBER(String numericString) throws Exception {
        return numericString.matches("^[0-9]*$");
    }

    public static boolean validateEMAIL(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        boolean matches = pattern.matcher(email).matches();
        return matches;
    }

    public static boolean validatePHONENO(String numericString) throws Exception {
        return numericString.matches("^[0-9]*$") && numericString.length() <= 15;
    }

    public static boolean validateNIC(String nic) {
        return nic.matches("^[0-9]+[VX]?$") && nic.length() == 10;
    }

    public static boolean validateSPECIALCHAR(String specialChars) throws Exception {
        return specialChars.matches("[~@#$&!~]+");
    }

    public static boolean validateDESCRIPTION(String text) {
        return text.matches("^(.*/)?(?:$|(.+?)(?:(\\.[^.]*$)|$))") && text.length() <= 150;
    }

    public static boolean validateHEXA(String text) {
        return text.matches("[\\dA-Fa-f]+");
    }

    public static String validatePW_POLICY(String text) {
        PasswordValidator passwordValidator = new PasswordValidator();
        String result = passwordValidator.validatePassword(text);
        return result;
    }

    public static String getRandomVal(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$&abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();

    }

    public static boolean validateURL(String text) {

        return text.matches("\\b(https?|ftp|file|ldap)://"
                + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
                + "*[-A-Za-z0-9+&@#/%=~_|]") && text.length() <= 150;
    }

    public static String generateHash(String password1, String password2) throws Exception {
        String password = "";
        if (password1.length() < 7) {
            password = password1 + password2;
        } else {
            password = password1.substring(0, password1.length() - 5)
                    + password2.substring(4, 10)
                    + password1.substring(password1.length() - 2, password1.length())
                    + password2.substring(2, 4)
                    + password1.substring(password1.length() - 5, password1.length() - 2)
                    + password2.substring(0, 2);
        }

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] sha1hash = new byte[40];
        md.update(password.getBytes("iso-8859-1"), 0, password.length());
        sha1hash = md.digest();
        String hashtext = convertToHex(sha1hash);
        return hashtext;
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static java.sql.Date getLocalDate() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        java.sql.Date date2 = new java.sql.Date(d.getTime());
        return date2;
    }

//    public static java.sql.Date getqlLocalDate() throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date d = new Date();
//        java.sql.Date date2 = new java.sql.Date(d.getTime());
//        return date2;
//    }
    /* Added by danushka_r */
    public static Date getOrclLocalDate() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        java.util.Date date2 = new java.util.Date(d.getTime());
        return date2;
    }

    public static Map<Integer, String> getUserTypes() throws Exception {

        Map<Integer, String> usertypesmap = new HashMap<Integer, String>();
        List<com.epic.tle.mapping.EpicTleUserProfile> tleMUserTypes = null;
        //List<com.epic.tle.orcel.mapping.EpicTleUserProfile> tleOUserTypes = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleUserProfile ";
            Query query = session.createQuery(sql);
            tleMUserTypes = query.list();

            for (int i = 0; i < tleMUserTypes.size(); i++) {
//                usertypesmap.put(tleUserTypes.get(i).getCode(), tleUserTypes.get(i).getDescription());
                usertypesmap.put(tleMUserTypes.get(i).getCode(), tleMUserTypes.get(i).getDescription());
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
        return usertypesmap;
    }

    public static String getOSLogPath(String logpath) throws Exception {
        String path = null;
        try {
            String linuxPath = logpath + "/";
            String conForwordToBack = linuxPath.replace("/", "\\");
            if (System.getProperty("os.name").startsWith("Windows")) {
                path = conForwordToBack;
            } else if (System.getProperty("os.name").startsWith("Linux")) {
                path = linuxPath;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return path;
    }

    public static Map<Integer, String> getBasicStatus() {
        Map<Integer, String> basicStatus = new HashMap<Integer, String>();
        basicStatus.put(Status.ACTIVE, "Active");
        basicStatus.put(Status.INACTIVE, "Inactive");
        return basicStatus;
    }

    /*public static void insertHistoryRecord(int logType, int userLevel, String modules, String operation, String remark, String tid, String mid, String setserialno, int webuser) throws Exception {
     //public static void insertHistoryRecord(int logType, int userLevel, String modules, String operation, String remark, String tid, String mid, String setserialno, int webuser, String section, StringBuffer oldValue, StringBuffer newValue) throws Exception {
     if (HibernateInit.dbConfig.equals(Configurations.ORCEL_CONF)) {
     HttpServletRequest request = ServletActionContext.getRequest();
     String ip = request.getRemoteAddr();
     com.epic.tle.orcel.mapping.EpicTleHistory tleHistory = null;
     Session session = null;
     try {
     session = HibernateInit.sessionFactory.openSession();
     session.beginTransaction();
     tleHistory = new com.epic.tle.orcel.mapping.EpicTleHistory();
     com.epic.tle.orcel.mapping.EpicTleLogtypes lt = new com.epic.tle.orcel.mapping.EpicTleLogtypes();
     //            lt.setCode(logType);
     lt.setCode(new BigDecimal(logType));
     tleHistory.setEpicTleLogtypes(lt);

     com.epic.tle.orcel.mapping.EpicTleUserProfile tleuser = new com.epic.tle.orcel.mapping.EpicTleUserProfile();
     //    tleuser.setCode(userLevel);
     tleuser.setCode(new BigDecimal(userLevel));
     tleHistory.setEpicTleUserProfile(tleuser);

     tleHistory.setModule(modules);

     //            EpicTleOperations opr = new EpicTleOperations();
     //    opr.setCode(operation);
     //     tleHistory.setEpicTleOperations(opr);
     com.epic.tle.orcel.mapping.EpicTleTask opr = new com.epic.tle.orcel.mapping.EpicTleTask();
     opr.setTaskId(operation);
     tleHistory.setEpicTleTask(opr);

     tleHistory.setRemark(remark);//add message
     tleHistory.setDatetime(Util.getLocalDate());
     tleHistory.setLocation(ip);
     tleHistory.setTid(tid);
     tleHistory.setMid(mid);
     tleHistory.setSerialno(setserialno);
     //                tleHistory.setWebuser(webuser);

     session.save(tleHistory);
     session.getTransaction().commit();
     } catch (Exception e) {
     e.printStackTrace();
     if (session != null) {
     session.flush();
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
     } else {
     HttpServletRequest request = ServletActionContext.getRequest();
     String ip = request.getRemoteAddr();
     com.epic.tle.mapping.EpicTleHistory tleHistory = new com.epic.tle.mapping.EpicTleHistory();
     Session session = null;
     Transaction tx = null;
     try {
     session = HibernateInit.sessionFactory.openSession();
     tx = session.beginTransaction();
     com.epic.tle.mapping.EpicTleUserProfile tleuser = (com.epic.tle.mapping.EpicTleUserProfile) session.get(com.epic.tle.mapping.EpicTleUserProfile.class, userLevel);
     tleHistory.setEpicTleUserProfile(tleuser);

     tleHistory.setModule(modules);

     com.epic.tle.mapping.EpicTleTask opr = new com.epic.tle.mapping.EpicTleTask();
     opr.setTaskId(operation);
     tleHistory.setEpicTleTask(opr);

     tleHistory.setRemark(remark);//add message
     tleHistory.setDatetime(Util.getLocalDate());
     tleHistory.setLocation(ip);
     tleHistory.setTid(tid);
     tleHistory.setMid(mid);
     tleHistory.setSerialno(setserialno);
     com.epic.tle.mapping.EpicTleUser user = (com.epic.tle.mapping.EpicTleUser) session.load(com.epic.tle.mapping.EpicTleUser.class, webuser);
     tleHistory.setEpicTleUser(user);

     com.epic.tle.mapping.EpicTleNodetype nodeType = (com.epic.tle.mapping.EpicTleNodetype) session.get(com.epic.tle.mapping.EpicTleNodetype.class, Configurations.SERVER_NODE);
     tleHistory.setEpicTleNodetype(nodeType);
     session.save(tleHistory);
     tx.commit();
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

     }*/
    public static void insertHistoryRecord(int logType, int userLevel, String modules, String operation, String remark, String tid, String mid, String setserialno, int webuser, String section, String oldValue, String newValue) throws Exception {
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            String ip = request.getRemoteAddr();
            com.epic.tle.mapping.EpicTleHistory tleHistory = new com.epic.tle.mapping.EpicTleHistory();
            Session session = null;
            Transaction tx = null;
            try {
                session = HibernateInit.sessionFactory.openSession();
                tx = session.beginTransaction();
                com.epic.tle.mapping.EpicTleUserProfile tleuser = (com.epic.tle.mapping.EpicTleUserProfile) session.get(com.epic.tle.mapping.EpicTleUserProfile.class, userLevel);
                tleHistory.setEpicTleUserProfile(tleuser);

                tleHistory.setModule(modules);

                com.epic.tle.mapping.EpicTleTask opr = new com.epic.tle.mapping.EpicTleTask();
                opr.setTaskId(operation);
                tleHistory.setEpicTleTask(opr);

                tleHistory.setRemark(remark);//add message
                tleHistory.setDatetime(Util.getLocalDate());
                tleHistory.setLocation(ip);
                tleHistory.setTid(tid);
                tleHistory.setMid(mid);
                tleHistory.setSerialno(setserialno);
                com.epic.tle.mapping.EpicTleUser user = (com.epic.tle.mapping.EpicTleUser) session.load(com.epic.tle.mapping.EpicTleUser.class, webuser);
                tleHistory.setEpicTleUser(user);

                com.epic.tle.mapping.EpicTleNodetype nodeType = (com.epic.tle.mapping.EpicTleNodetype) session.get(com.epic.tle.mapping.EpicTleNodetype.class, Configurations.SERVER_NODE);
                tleHistory.setEpicTleNodetype(nodeType);
                tleHistory.setSection(section);
                tleHistory.setOldValue(oldValue);
                tleHistory.setNewValue(newValue);
                session.save(tleHistory);
                tx.commit();
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

    }

    public static String getDateFE() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        return sdf.format(date1);
    }

    //**********get files path in a directory****************
    public static List getPopulateFilesList(File dir) throws IOException {
        List<String> filesListInDir = new ArrayList<String>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                filesListInDir.add(file.getAbsolutePath());
            } else {
                getPopulateFilesList(file);
            }
        }
        return filesListInDir;
    }

    public static String getTimestamoToDate(String datea) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date d = df.parse(datea);
        SimpleDateFormat fin = new SimpleDateFormat("yyyy-MM-dd");
        return fin.format(d);
    }

    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static boolean validateIP(final String ip) {
        return PATTERN.matcher(ip).matches();
    }

    public static String getDatabaseConfig() {
        ResourceBundle rb = ResourceBundle.getBundle("context");

        return rb.getString("epic.tle.database.config");
        //  return "mysql";
    }

    public static Map<Integer, String> getStatusValues(int from, int to) {
        Session session = null;
        List result = new ArrayList();
        Map<Integer, String> status = new HashMap<Integer, String>();
        try {
            session = HibernateInit.sessionFactory.openSession();
            Query query = session.createQuery("from EpicTleStatus o order by o.code asc");
            query.setFirstResult(from);
            query.setMaxResults(to);
            result = query.list();
            for (com.epic.tle.mapping.EpicTleStatus stat : (List<com.epic.tle.mapping.EpicTleStatus>) result) {
                status.put(stat.getCode(), stat.getDescription());
            }
        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return status;
    }

    public static List getMasterValues(int from, int to, String table) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = HibernateInit.sessionFactory.openSession();
            Query query = session.createQuery("from " + table + " o order by o.code asc");
            query.setFirstResult(from);
            query.setMaxResults(to);
            result = query.list();

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return result;
    }

    public static String tomorrowDate() {
        DateFormat yeartomorrw = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();

        return yeartomorrw.format(tomorrow);
    }

    public static String currentDate() {
        DateFormat yeartodate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return yeartodate.format(date);
    }

    public static DbConfiguraitonBean xmlConfiguraion(String path) throws Exception {

        DbConfiguraitonBean config = new DbConfiguraitonBean();
        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            config.setDialect(doc.getElementsByTagName("dbdialect").item(0).getTextContent());
            config.setDriverClass(doc.getElementsByTagName("dbpooldriver").item(0).getTextContent());
            config.setShowSql("false");
            config.setUrl(doc.getElementsByTagName("dbpoolurl").item(0).getTextContent());
            config.setUsername(doc.getElementsByTagName("dbpoolusername").item(0).getTextContent());
            config.setPassword(removeASC(doc.getElementsByTagName("dbpoolpassword").item(0).getTextContent()));
            config.setZeroDateTimeBehavior("convertToNull");
            String pooltype = (doc.getElementsByTagName("dbpooltype").
                    item(0).getTextContent().equals("1")) ? "oracle" : "mysql";
            Configurations.DB_CONF = pooltype;
            config.setDbpooltype(pooltype);

            config.setPoolSize(doc.getElementsByTagName("dbpoolmaxcon").item(0).getTextContent());
            config.setDbpoolmax(doc.getElementsByTagName("dbpoolmax").item(0).getTextContent());
            config.setDbpoolmin(doc.getElementsByTagName("dbpoolmin").item(0).getTextContent());
            config.setDbpooltimeout(doc.getElementsByTagName("dbpoolcontimeout").item(0).getTextContent());
            config.setDbpoolsource(doc.getElementsByTagName("dbpoolsource").item(0).getTextContent());
            Configurations.SERVER_NODE = Integer.parseInt(doc.getElementsByTagName("servernode").item(0).getTextContent());
            Configurations.HSM_SLOT = Integer.parseInt(doc.getElementsByTagName("hsmslot").item(0).getTextContent());
            Configurations.HSM_PASSWORD = doc.getElementsByTagName("hsmpassword").item(0).getTextContent();
//            Configurations.NODE_STATUS = doc.getElementsByTagName("servernode").item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return config;
    }

    public static String removeASC(String asci) throws Exception {
        return new String(ISOUtil.hex2byte(new String(ISOUtil.hex2byte(asci)))).trim();
    }

    public static SwitchConfBean getSwitchConfiguration() {
        Session session = null;
        List result = new ArrayList();
        SwitchConfBean bean = new SwitchConfBean();

        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig");
            result = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) result) {
                bean.setPort(config.getServicePort());
                bean.setTimeout(config.getServiceClientTimeout());
                bean.setIp(Configurations.SWITCH_IP);
                //bean.setHsmSlot(config.getHsmSlot());
                //bean.setVersionNo(config.getVersionNo());
                Configurations.LOG_PATH = config.getLogbackuppath();

                System.out.println("service port: " + config.getServicePort() + "|  timeout: " + config.getServiceClientTimeout() + ""
                        + "| ip: " + Configurations.SWITCH_IP + "" + "| logpath: " + config.getLogbackuppath());
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return bean;

    }

    public static String getWebLogPath(String require) {
        return Configurations.PATH_ROOT + Configurations.WebLogPath + require;
    }

    public static boolean runConsoleCommand(String command) throws Exception {
        boolean ok = false;

        Runtime rt = null;
        Process pr = null;
        BufferedReader input = null;
        InputStreamReader inst = null;
        try {
            rt = Runtime.getRuntime();
            pr = rt.exec(command);
            inst = new InputStreamReader(pr.getInputStream());
            input = new BufferedReader(inst);
            while ((input.readLine()) != null) {

            }

            int exitVal = pr.waitFor();
            if (exitVal == 0) {
                ok = true;
            }

        } catch (Exception e) {
            throw e;

        } finally {
            if (input != null) {
                input.close();
            }
            if (inst != null) {
                inst.close();
            }
            if (pr != null) {
                pr.destroy();
            }

            input = null;
            inst = null;
            pr = null;
            rt = null;
        }
        return ok;

    }

    public static String getPasswordExpirationTimePeriod(int node) {
        Session session = null;
        List list = null;
        String password_expiration_time_period = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                password_expiration_time_period = config.getPasswordExpirationTimePeriod();
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return password_expiration_time_period;
    }

    public static Integer getUserAccountLockOutTime(int node) {
        Session session = null;
        List list = null;
        int lock_out_time = 0;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                lock_out_time = config.getUserAccountLockTime();
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return lock_out_time;
    }

    public static Integer getTempPasswordExpirationTimePeriod(int node) {
        Session session = null;
        List list = null;
        int temp_pass = 0;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                temp_pass = config.getTempPasswordExpirationTimePeriod();
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return temp_pass;
    }

    public static Integer getNumberOfPasswordAttempts(int node) {
        Session session = null;
        List list = null;
        int password_attempts = 0;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                password_attempts = config.getNumberOfPasswordAttempts();
            }
        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return password_attempts;
    }

    public static String getSenderEmail(int node) {
        Session session = null;
        List list = null;
        String senderEmail = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                senderEmail = config.getSenderEmail();
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return senderEmail;
    }

    public static String getPasswordRestMailTemplate(int node) {
        Session session = null;
        List list = null;
        String email_temp = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                email_temp = config.getPasswordResetMailTemplate();
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return email_temp;
    }

    public static List<EpicTleUserPasswordHistory> getUserPreviousPasswords(String username) {
        Session session = null;
        ArrayList<EpicTleUserPasswordHistory> list = new ArrayList<>();
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            Criteria add = session.createCriteria(EpicTleUserPasswordHistory.class, "ph")
                    .createAlias("ph.userId", "user")
                    .add(Restrictions.eq("user.username", username));
            List list1 = add.list();
            list = (ArrayList<EpicTleUserPasswordHistory>) list1;
        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return list;
    }

    public static String getUserRandVal(String username) throws Exception {
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            return Util.dataEncrypter(0, session.createCriteria(EpicTleUser.class, "ph")
                    .add(Restrictions.eq("ph.username", username))
                    .setProjection(
                            Projections.
                            projectionList().
                            add(Projections.property("ph.randVal"))).
                    uniqueResult().toString());

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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

    public static ArrayList<EpicTleUserPasswordHistory> getOldPasswords(String username, int check_prv_pw) {
        Session session = null;
        Transaction txn = null;
        ArrayList<EpicTleUserPasswordHistory> passwordHistorys = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            passwordHistorys = (ArrayList<EpicTleUserPasswordHistory>) session
                    .createCriteria(EpicTleUserPasswordHistory.class, "pw_his")
                    .createAlias("pw_his.userId", "user")
                    .add(Restrictions.eq("user.username", username))
                    .addOrder(Order.asc("pw_his.createdDate"))
                    .setMaxResults(check_prv_pw)
                    .list();
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }

        }
        return passwordHistorys;
    }

    public static boolean updatePasswordHistory(String password, String username, int userId) throws Exception {
        return updatePasswordHistory(password, username, userId, null);
    }

    public static boolean updatePasswordHistory(String password, String username, int userId, String randVal) throws Exception {
        int check_prv_pw = 4;
        Session session = null;
        Transaction txn = null;
        boolean val = false, isSetrandVal = null != randVal;
        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            ArrayList<EpicTleUserPasswordHistory> passwordHistorys = getOldPasswords(username, check_prv_pw);
            if (check_prv_pw == passwordHistorys.size()) {
                EpicTleUserPasswordHistory oldest_password = passwordHistorys.iterator().next();
                Query createQuery = session.createQuery("update EpicTleUserPasswordHistory set privousPassword = :privousPassword,createdDate=:createdDate where userId=:userId AND passwordHistoryId=:passwordHistoryId");
                createQuery.setParameter("privousPassword", generateHash(password, isSetrandVal ? randVal : Util.getUserRandVal(username)));
                createQuery.setParameter("createdDate", new Date());
                createQuery.setParameter("userId", new EpicTleUser(userId));
                createQuery.setParameter("passwordHistoryId", oldest_password.getPasswordHistoryId());
                int executeUpdate = createQuery.executeUpdate();
//                oldest_password.setPrivousPassword(generateHash(password));
//                oldest_password.setUserId(new EpicTleUser(userId));
//                oldest_password.setCreatedDate(new Date());
//                session.update(oldest_password);
                txn.commit();
                val = true;
            } else {
                EpicTleUserPasswordHistory epicTleUserPasswordHistory = new EpicTleUserPasswordHistory();
                EpicTleUser epicTleUser = (EpicTleUser) session.get(EpicTleUser.class, userId);
                epicTleUserPasswordHistory.setUserId(epicTleUser);
                epicTleUserPasswordHistory.setPrivousPassword(generateHash(password, isSetrandVal ? randVal : Util.getUserRandVal(username)));
                epicTleUserPasswordHistory.setCreatedDate(new Date());
                session.save(epicTleUserPasswordHistory);
                txn.commit();
                val = true;
            }
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return val;

    }

    public static String genarateTempPassword() {
        String ALPHA_NUMERIC_STRING = "abcdefghijklmnopABCDEFGH@#IJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int length = 10;
        while (length-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static boolean sendMail(MailBucket bucket) throws MessagingException, GeneralSecurityException, Exception {
        boolean res = false;
        EmailServerConf mailconf = getMailServerConfigurations(Configurations.SERVER_NODE);

        if (mailconf.getBank_code() == 0) {
            res = EpicAlertSender.sendMail(
                    bucket.getFrom_mail_address(),
                    bucket.getTo_mail_address(),
                    bucket.getSubject(),
                    bucket.getMessage(),
                    mailconf.getHost(),
                    mailconf.getUser_name(),
                    mailconf.getPassword(),
                    mailconf.getPort()
            );
        } else if (mailconf.getBank_code() == 7) {
            res = BMLAlertSender.sendMail(
                    bucket.getFrom_mail_address(),
                    bucket.getTo_mail_address(),
                    bucket.getSubject(),
                    bucket.getMessage(),
                    mailconf.getHost(),
                    mailconf.getUser_name(),
                    mailconf.getPassword(),
                    mailconf.getPort()
            );
        } else {
            res = false;
        }
        return res;
    }

    public static EmailServerConf getMailServerConfigurations(int node) throws Exception {
        Session session = null;
        EmailServerConf serverConf = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            List list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                serverConf = new EmailServerConf();
                serverConf.setHost(config.getEmailGwUrl());
                serverConf.setPort(config.getEmailGwPort());
                serverConf.setPassword(Util.dataEncrypter(2, config.getEmailPassword()));
                serverConf.setUser_name(config.getEmailUsername());
                serverConf.setBank_code(config.getBank_code());
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return serverConf;
    }

//    public static String encryptWithAES(String data) throws Exception {
//        Key key = new SecretKeySpec(keyValue, ALGO);
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encVal = c.doFinal(data.getBytes());
//        return new BASE64Encoder().encode(encVal);
//    }
//
//    /**
//     * Decrypt a string with AES algorithm.
//     *
//     * @param encryptedData is a string
//     * @return the decrypted string
//     */
//    public static String decryptWithAES(String encryptedData) throws Exception {
//        Key key = new SecretKeySpec(keyValue, ALGO);
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.DECRYPT_MODE, key);
//        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
//        byte[] decValue = c.doFinal(decordedValue);
//        return new String(decValue);
//    }
    public static String getOldorNewVal(Object o, String str) {
        StringBuilder result = new StringBuilder();
        String resultStr = null;
        Session session = null;
        Query query = null;
        try {
            Class<?> c = o.getClass();
            session = HibernateInit.sessionFactory.openSession();

            String sql1 = "from " + o.getClass().getSimpleName() + " wu where " + str;
            query = session.createQuery(sql1);

            ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
            while (results.next()) {
                result.append(", ");
                result.append(results.get(0).toString());
            }
            resultStr = result.toString().substring(1);
            results.close();

        } catch (Exception e) {
            resultStr = "error while fetching data.";
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
        return resultStr;
    }

    public static String encrypt(String str) throws Exception {
        byte data[] = str.getBytes();
        Provider p = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        Security.addProvider(p);
        byte KEY[] = ISOUtil.hex2byte("8FA623E44821B10299F2701809F3303EA28143E17712B3C8");
        org.jpos.security.Util.adjustDESParity(KEY);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", p.getName());
//        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY, HSMConnector.getTDES_ALGORITHEM()), new IvParameterSpec(new byte[8]));
        return ISOUtil.hexString(cipher.doFinal(data));
    }

    public static int getTempPasswordExpirayionTime(int node) {
        Session session = null;
        List list = null;
        int temp_pass_exp_time = 0;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setParameter("node", node);
            list = query.list();
            for (com.epic.tle.mapping.EpicTleInitconfig config : (List<com.epic.tle.mapping.EpicTleInitconfig>) list) {
                temp_pass_exp_time = config.getTempPasswordExpirationTimePeriod();
            }
        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return temp_pass_exp_time;
    }

    public static String dataEncrypter(int mode, String str) throws Exception {
        Provider p = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        Security.addProvider(p);
        byte KEY[] = ISOUtil.hex2byte("8FA623E44821B10299F2701809F3303EA28143E17712B3C8");
        org.jpos.security.Util.adjustDESParity(KEY);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", p.getName());

        if (mode == 1) {
//            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY, HSMConnector.getTDES_ALGORITHEM()), new IvParameterSpec(new byte[8]));
            return ISOUtil.hexString(cipher.doFinal(str.getBytes()));
        } else {
//            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY, HSMConnector.getTDES_ALGORITHEM()), new IvParameterSpec(new byte[8]));
            return new String(cipher.doFinal(ISOUtil.hex2byte(str)));
        }
    }
}
