/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util.constant;

/**
 *
 * @author kreshan
 */
public class Configurations {

    //new
    public static String PATH_ROOT = "";
    public static int SERVER_NODE;
    public static String SWITCH_IP = "127.0.0.1";
    public static String WebLogPath = Configurations.PATH_ROOT+"/logs/web/";
    public static String SWITCH_ECHO = "6|System|127.0.0.1";
    public static String ENV_VARIABLE_CONFIG = "TLE_HOME";
    public static String NODE_STATUS = "";
    
    public static String PATH_CONFIG_MYSQL = "/sconfig/tleconfig_mysql.xml";
    public static String PATH_CONFIG_ORCEL = "/sconfig/tleconfig_orcel.xml";
    public static String PATH_CONFIG = "/sconfig/tleconfig.xml";
//    public static String LOG_PATH   = "/opt/epicline/logs/";
//    public static String LOG_PATH   = Configurations.PATH_ROOT+"/logs/testtemp";
    public static String LOG_PATH   = "";
    public static String ORCEL_CONF   = "ORCEL";
    public static String DB_CONF   = "";
    public static boolean SERVER_STATUS   = false;
    public static String HSM_CONF_TEXT_FILE_PATH   = Configurations.PATH_ROOT+"/sconfig/hsm.txt";
   
    //old
    public static String WEB_USER_FILE_PATH = "sconfig/data/users/LESUSER.dat";
    public static String SERVER_CONFIG_FILE_PATH = "sconfig/severconfig/SERVERCONFIG.xml";
    public static String FE_DATA_FILE_PATH = "sconfig/data/cardholder/CARDHOLDERS.dat";
    public static String RT_DATA_FILE_PATH = "sconfig/data/terminals/regfiles/REGISTERTIDS.dat";
    public static String NIIMAPFORNONLES_DATA_FILE_PATH = "sconfig/data/host/NIIMAPFORNONLES.dat";
    public static String NIIMAPFORNAC_DATA_FILE_PATH = "sconfig/data/host/NIIMAP.dat";
    public static String REGISTERNIIGROUPS_DATA_FILE_PATH = "sconfig/data/host/JOBDISTRIBUTOR.dat";
    public static String REGISTERHOST_DATA_FILE_PATH = "sconfig/data/host/HOST.dat";
    public static String SYSTEMFUNCTION_DATA_FILE_PATH = "sconfig/severconfig/sys/SYS.dat";
    public static String HISTORY_FOLDER_PATH = "sconfig/data/history";
    public static String JASPER_SMART_CARD_FILE_PATH = "sconfig/webAppConfig/SmartCardJasper.jasper";
    public static String JASPER_POS_TERMINAL_FILE_PATH = "sconfig/webAppConfig/POSTreminal.jasper";
    public static String DATA_FOLDER_PATH = "sconfig";
    public static String TEMP_FOLDER_PATH = "system/temp";
    public static String FLUSH_FOLDER_PATH = "logs/backup/flush";
    public static String BACKUP_FOLDER_PATH = "logs/backup/les";
//    public static String FE_TEMP_FILE_PATH_WIN = "C:/opt/tmpFE/";
    public static String FE_TEMP_FILE_PATH_LIN = "/tmp/";
    /**
     * Statuse to check whether the main switch is running.
     */
    public static String RESPONSE_MESSAGE_CONFIG_FILE_PATH = "sconfig/severconfig/ENC.xml";
    public static String SYSTEM_UPDATE_FILE_PATH = "sconfig/severconfig/sys/SYSUPDATES.xml";
    public static String LOG_FILE_PATH = "logs/web";
    public static String ERROR_FOLDER_PATH = "logs/errors";
    public static String INFO_FOLDER_PATH = "logs/rawdata";
    public static String DUMP_FOLDER_PATH = "logs/dump";
    public static String ALERT_FOLDER_PATH = "logs/alert";
    public static String DEBUG_FOLDER_PATH = "logs/debug";
    public static String FAIL_FOLDER_PATH = "logs/fail";
    public static String HEXDUMP_FOLDER_PATH = "logs/hexdump";
    public static String DETAILED_FOLDER_PATH = "logs/infors";
    public static String INIT_FOLDER_PATH = "logs/init";
    public static String TIMEOUT_FOLDER_PATH = "logs/timeout";
    public static String TRACE_FOLDER_PATH = "logs/trace";
    public static String LOG_BACKUP_FOLDER_PATH = "/opt/tmp/"; 
    
    public static boolean EESWITCHUP = false;
    public static boolean PDASWITCHUP = false;
    public static boolean HSMCONFIG = false;
    public static boolean EMAILCONFIG = true;
    public static String DB_CONFIG_FILE = "sconfig/tleconfig.xml";
    
    public static int MAX_FETCH_SIZE = 500;
    
    public static String HSM_PASSWORD;
    public static int HSM_SLOT;
    //////smtp configuration///////////////
    public static int MAIL_TIMEOUT = 1000;
    public static int SMS_TIMEOUT = 10;
    static {
        initialization();
    }

    public static void initialization() {

        LOG_FILE_PATH = Configurations.PATH_ROOT + "logs/web";
        WEB_USER_FILE_PATH = Configurations.PATH_ROOT + "sconfig/data/users/LESUSER.dat";
        SERVER_CONFIG_FILE_PATH = Configurations.PATH_ROOT + "sconfig/severconfig/SERVERCONFIG.xml";
        RESPONSE_MESSAGE_CONFIG_FILE_PATH = Configurations.PATH_ROOT + "sconfig/severconfig/ENC.xml";
        SYSTEM_UPDATE_FILE_PATH = Configurations.PATH_ROOT + "sconfig/severconfig/sys/SYSUPDATES.xml";
        FE_DATA_FILE_PATH = Configurations.PATH_ROOT + "sconfig/data/cardholder/CARDHOLDERS.dat";
        RT_DATA_FILE_PATH = Configurations.PATH_ROOT + "sconfig/data/terminals/regfiles/REGISTERTIDS.dat";
        NIIMAPFORNONLES_DATA_FILE_PATH = Configurations.PATH_ROOT + "sconfig/data/host/NIIMAPFORNONLES.dat";
        NIIMAPFORNAC_DATA_FILE_PATH = Configurations.PATH_ROOT + "sconfig/data/host/NIIMAP.dat";
        REGISTERNIIGROUPS_DATA_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/data/host/JOBDISTRIBUTOR.dat";
        REGISTERHOST_DATA_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/data/host/HOST.dat";
        SYSTEMFUNCTION_DATA_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/severconfig/sys/SYS.dat";
        HISTORY_FOLDER_PATH = Configurations.PATH_ROOT+ "sconfig/data/history";

        JASPER_SMART_CARD_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/web/SmartCardJasper.jasper";
        JASPER_POS_TERMINAL_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/web/POSTreminal.jasper";

        DATA_FOLDER_PATH = Configurations.PATH_ROOT+ "sconfig";
        TEMP_FOLDER_PATH = Configurations.PATH_ROOT+ "system/temp";
        FLUSH_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/backup/flush";
        BACKUP_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/backup/les";
        ERROR_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/errors";
        INFO_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/rawdata";
        ALERT_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/alert";
        DEBUG_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/debug";
        FAIL_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/fail";
        HEXDUMP_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/hexdump";
        DETAILED_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/infors";
        INIT_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/init";
        TIMEOUT_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/timeout";
        TRACE_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/trace";

        RESPONSE_MESSAGE_CONFIG_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/severconfig/ENC.xml";
        SYSTEM_UPDATE_FILE_PATH = Configurations.PATH_ROOT+ "sconfig/severconfig/sys/SYSUPDATES.xml";
        LOG_FILE_PATH = Configurations.PATH_ROOT+ "logs/web";
        ERROR_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/errors";
        INFO_FOLDER_PATH = Configurations.PATH_ROOT+ "logs/rawdata";
        DUMP_FOLDER_PATH = Configurations.PATH_ROOT+  "logs/dump";
        DB_CONFIG_FILE = Configurations.PATH_ROOT+ "sconfig/tleconfig.xml";
        LOG_BACKUP_FOLDER_PATH = "/opt/tmp/";
//        KEY_BACKUP_PATH = OS_TYPY() + "sconfig/data/psg/";
    }
}
