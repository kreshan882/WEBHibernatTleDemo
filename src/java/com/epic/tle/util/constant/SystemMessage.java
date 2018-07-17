/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util.constant;

/**
 * @author kreshan
 */
public class SystemMessage {

    /////////////////////////////////////////////////////////////////////////
    // init Messages
    ////////////////////////////////////////////////////////////////////////
    public static final String INITIAL_ERROR = "Environmental variable not found";
    public static final String CONFIGURAITON_ERROR = "Configuraiton file read error";

    /////////////////////////////////////////////////////////////////////////
    // operation managment
    ////////////////////////////////////////////////////////////////////////
    public static final String OPERATION_SEND_SUCCESS = "Operation send sucessfully";
    public static final String SERVERSTATUS_FAIL = "Server is not responding";
    public static final String OPERATION_SELECT_FAIL = "Please select an operation";
    public static final String OPERATION_SEND_FAIL = "Operation send fail";
    /////////////////////////////////////////////////////////////////////////
    // User managment
    ////////////////////////////////////////////////////////////////////////
    public static final String USER_LOGIN_SUCCESS = "Login sucessfully";
    public static final String USER_LOGOUT_SUCCESS = "Logout sucessfully";
    public static final String USER_LOGIN_STATUSE_NOT_ACTIVATE = "Status not active";
    public static final String USER_LOGIN_STATUSE_LOCKED = "Your account has been locked.!";
    public static final String USER_LOGIN_STATUSE_UNLOCKED = "User account successfully unlocked..!";
    public static final String USER_LOGIN_FAIL = "Invalid login..!";
    public static final String USER_PASSWORD_EXPIRED = "Your password has been expired..! Please change your password.!";
    public static final String FIRST_LOGIN = "This is your first login..! Please change the default password..!";
    public static final String USER_LOGIN_ERROR = "Error contact administrator";
    public static final String USER_LOGIN_EMPTY_NAME = "Enter user name";
    public static final String USER_LOGIN_EMPTY_PWD = "Enter password";
    public static final String USER_ROLE_STATUS_NOT_ACTIVATE = "User role status not active";

    public static final String USER_EMPTY_NAME = "Empty name";
    public static final String USER_INVALID_NAME = "Invaild name";
    public static final String USER_EMPTY_USERNAME = "Empty user name";
    public static final String USER_INVALID_USERNAME = "Invaild user name or password";
    public static final String USER_INVALIDUSERNAME = "Invaild user name";
    public static final String USER_INVALID_EMAIL = "Invaild Email Address";
    public static final String USER_USERNAME_ALREADY = "User name already exist";
    public static final String USER_PW_EMPTY = "Empty user password";
    public static final String USER_PW_INVALID = "Invaild user password";
    public static final String USER_CONF_PW_EMPTY = "Empty user confirm password";
    public static final String USER_PASS_POLICY_CONFIRM = "Does not match with password Policy";
    public static final String USER_CONF_PW_INVALID = "Invaild user confirm password";
    public static final String USER_CONF_PW_MISMATCH = "Password not matched";
    public static final String USER_EMPTY_USERTYPE = "Empty user type";
    public static final String USER_EMPTY_USER_PROFILE = "Empty user profile";
    public static final String USER_ADD_SUCESS = "Web user registration is successfully done";
    public static final String USER_ADD_FAIL = "User registation is fail ";

    public static final String USER_COUNT_EXCEEDED = "Maximum user's exists";
    public static final String USER_UPDATED = "User update is successfully done ";
    public static final String USER_UPDATED_ERROR = "User update error";
    public static final String USER_UPDATED_ERROR_EMAIL_DUPLICATE = "Email address already registered in database.";
    public static final String USER_DELETED = "User delete is successfully done ";
    public static final String USER_DELETED_ERROR = "User delete error";

    public static final String USER_DELETE_CANNOT_DELETE_CURRENT_USER = "Deleting current login user not allowed";
    public static final String USER_DELETE_CANNOT_DELETE_ADMIN = "Deleting administrators not allowed";

    public static final String USER_CONFIRM_CONFIRMATION_ERROR = "user activation error";
    public static final String USER_CONFIRM_CONFIRMATION_SUCCESS = "User activation success";

    public static final String USER_PW_POLICY_UPDATED = "Password policy  update is successfully done ";
    public static final String USER_PW_POLICY_UPDATE_FAIL = "Password policy update is fail";

    public static final String USER_PW_POlICY_INVALID_MAX_LENGTH = "Invalid maximum length";
    public static final String USER_PW_POlICY_INVALID_MIN_LENGTH = " Invalid minimum length..! must contain at least seven or above";
    public static final String USER_PW_POlICY_INVALID_SPECIAL_CHARACTER_SET = "Invalid special character set : Valid special characters are ~!@#$&";
    public static final String USER_PW_POlICY_INVALID_MIN_SPCL_CHARACTER = "Invalid minimum special characters";
    public static final String USER_PW_POlICY_INVALID_SPCL_CHARACTER_LENGTH = "Invalid length..! must contain at least one special character";
    public static final String USER_PW_POlICY_INVALID_MAX_SPCL_CHARACTER = "Invalid maximum special characters";
    public static final String USER_PW_POlICY_INVALID_MIN_UPPERCASE = "Invalid minimum uppercase";
    public static final String USER_PW_POlICY_INVALID_UPPERCASE_LENGTH = "Invalid length..! must contain at least one uppercase character";
    public static final String USER_PW_POlICY_INVALID_MIN_LOWERCASE = "Invalid minimum lowercase";
    public static final String USER_PW_POlICY_INVALID_LOWERCASE_LENGTH = "Invalid length..! must contain at least one lowercase character";
    public static final String USER_PW_POlICY_INVALID_MIN_NUMERIC_CHARACTER = "Invalid minimum numeric characters";
    public static final String USER_PW_POlICY_INVALID_NUMERIC_CHARACTER_LENGTH = "Invalid length..! must contain at least one numeric character";
    public static final String USER_PW_POLICY_INVALID_MIN_MAX_LENGTH = "Minimum length is higher than maximum length";
    public static final String USER_PW_POLICY_INVALID_SPCL_CHARACTER_LENGTH = "Minumum special character length is higher than maximum special character length";
    public static final String USER_PW_POLICY_MIN_LENGTH = "Minimum length must be equal or higher than sum of other minimum characters";
    public static final String USER_PW_POLICY_INVALID_MAX_LENGTH_WITH_SPC_CHAR = "Maximum password length must be equal or higher than sum of maximum special characters, minimum uppercase, minimum lowercase and minimum numeric characters";

    public static final String USER_CHANGE_PASSWORD_SUCCESS = "Password successfully changed";
    public static final String USER_CHANGE_PASSWORD_ERROR = "Password change error";
    public static final String USER_CHANGE_PREVIOUS_PASSWORD = "You cant use your old passwords..! your new password must be different from four previous passwords.!";
    public static final String USER_CHANGE_PASSWORD_OLD_PW_EMPTY = "Old password is empty";
    public static final String USER_CHANGE_PASSWORD_NEW_PW_EMPTY = "New password is empty";
    public static final String USER_CHANGE_PASSWORD_REPEAT_PW_EMPTY = "Repeat password is empty";
    public static final String USER_CHANGE_PASSWORD_OLD_PW_INCORRECT = "Old password is incorrect";
    public static final String USER_CHANGE_PASSWORD_CANNOT_INSERT_OLD_PW = "Can not insert existing password";

    public static final String FIELD_ADD_SUCESS = "Field engineer added successfully";
    public static final String FIELD_ADD_FAIL = "Field engineer add fail";
    public static final String FIELD_ADD_PDF_FAIL = "Field engineer pdf creating error";
    public static final String FIELD_SELECT_DEVICE = "Select device type";
    public static final String FIELD_EMPTY_SERIAL = "Empty serial number";
    public static final String FIELD_INVAID_SERIAL = "Invalid serial number";
    public static final String FIELD_SERIALNO_ALREADY = "Serial number already exeists";
    public static final String FIELD_EMPTY_NAME = "Empty field engineer name";
    public static final String FIELD_INVAID_NAME = "Invalid feile engineer name";
    public static final String FIELD_EMPTY_BANKNAME = "Empty bank name";
    public static final String FIELD_INVAID_BANKNAME = "Invalid bank name";
    public static final String FIELD_EMPTY_LOCATION = "Empty location";
    public static final String FIELD_INVAID_LOCATION = "Invalid location";
    public static final String FIELD_EMPTY_MAXKEYDOWN = "Empty maximum key download";
    public static final String FIELD_INVAID_MAXKEYDOWN = "Invalid maximum key download";
    public static final String FIELD_SELECT_ALGO = "Please select algoritham type";
    public static final String FIELD_SELECT_PINVERTYPE = "Please select PIN verfication type";
    public static final String FIELD_SELECT_BDK = "Please select BDk Index";
    public static final String FIELD_SELECT_STATUS = "Please select status";

    public static final String FIELD_DELETE_SUCESS = "Field engineer deleted successfully";
    public static final String FIELD_DELETE_FAIL = "Field engineer delete error";
    public static final String FIELD_UPDATE_SUCESS = "Field engineer updated successfully";
    public static final String FIELD_UPDATE_FAIL = "Field engineer update error";

    public static final String FIELD_PIN_RESET = "PIN reset sucessfully";
    public static final String FIELD_PIN_RESET_FAIL = "PIN reset fail";

    public static final String FIELD_KEY_INJE_EMPTY = "Empty key injection terminal id";
    public static final String FIELD_KEY_INJE_SUCCESS = "Key injection success";
    public static final String FIELD_KEY_INJE_INVALID = "Invalid key injection terminal id";

    //Terminal Management Messages
    public static final String TERMINAL_UPLOAD_FAIL = "Terminal upload fail";
    public static final String TERMINAL_ADD_SUCCESS = "Terminal registered successfully";
    public static final String TERMINAL_LIST_ADD_SUCCESS = "Terminal list added successfully";
    public static final String TERMINAL_REGISTRATION_FAIL = "Terminal registration fail";
    public static final String TERMINAL_ID_INVALID = "Invalid terminal id";
    public static final String TERMINAL_ID_TOO_LONG = "Terminal ID must be 8 digit";
    public static final String TERMINAL_INVALID_MERCHANT_ID = "Invalid merchant id";
    public static final String TERMINAL_LENGTH = "Merchant ID must be 8 to 15 digit";
    public static final String TERMINAL_INVALID_SERIAL_NO = "Invalid serial number";
    public static final String TERMINAL_INVALID_NAME = "Invalid name";
    public static final String TERMINAL_EMPTY_NAME = "Empty name";
    public static final String TERMINAL_INVALID_BANK_NAME = "Invalid bank name";
    public static final String TERMINAL_EMPTY_BANK_NAME = "Empty bank name";
    public static final String TERMINAL_INVALID_LOCATION_NAME = "Invalid location";
    public static final String TERMINAL_EMPTY_LOCATION_NAME = "Empty location name";
    public static final String TERMINAL_INVALID_TERBRAND_NAME = "Invalid terminal brand";
    public static final String TERMINAL_EMPTY_TERBRAND_NAME = "Empty terminal brand name";
    public static final String TERMINAL_STATUS_SELECTED = "Status must be selected";
    public static final String TERMINAL_ENCTYPE_NOT_SELECTED = "Encryption type must be selected";
    public static final String TERMINAL_NONENCTNX_NOT_SELECTED = "Non encryption transaction must be selected";
    public static final String TERMINAL_BINPROFILE_CHECK_EMPTY = "BIN profile check must be selected";
    public static final String TERMINAL_BIN_PROFILE_NOT_SELECTED = "Bin profile must be selected";
    public static final String TERMINAL_BIN_PROFILE_STATUS_NOT_SELECTED = "Bin profile Status must be selected";
    public static final String TERMINAL_UPDATE_SUCCESS = "Terminal updated successfully";
    public static final String TERMINAL_UPDATE_FAIL = "Terminal update fail";
    public static final String TERMINAL_DELETE_SUCCESS = "Terminal deleted successfully";
    public static final String TERMINAL_DELETE_FAIL = "Terminal delete fail";

    public static final String TERMINAL_SELECT_FILE = "Please select the file";
    public static final String TERMINAL_LISTADD_FAIL = "Terminal list upload fail";
    public static final String TERMINAL_SELECT_FORMAT = "Please select correct file format";
    public static final String TERMINAL_FILE_SIZE = "File size is higher";

    //************Log File Management***************************
    public static final String LOG_FILE_DELETED = "Log file deleted successfully";
    public static final String LOG_FILE_DELETE_ERROR = "ERROR";
    public static final String LOG_FILE_DOWNLOADED = "Log file downloaded successfully";
    public static final String LOG_FILE_DOWNLOAD_FAIL = "Log file download fail";
    public static final String LOG_FILE_ALL_LOG_CLEAR_SUCCESSFULY = "All selected logs clear successfully";
    public static final String LOG_FILE_ALL_LOG_CLEAR_ERROR = "ERORR";

    /////////////////////////////////////////////////
    //////Server Management
    /////////////////////////////////////////////////
    public static final String SESSION_CONF_EMPTY_MAX_PIN_COUNTER_VALUE = " Max PIN counter value can't be empty";
    public static final String SESSION_CONF_INVALID_MAX_PIN_COUNTER_VALUE = " Max PIN counter value should a  numeric value between 1 and 100";
    public static final String SESSION_CONF_EMPTY_LOGLEVEL = " Log level value can't be empty";
    public static final String SESSION_CONF_EMPTY_LOGFILENAME = " Log file Name value can't be empty";
    public static final String SESSION_CONF_EMPTY_LOGPATH = " Log backup Path value can't be empty";
    public static final String SESSION_CONF_EMPTY_IV = " IV value can't be empty";
    public static final String SESSION_CONF_EMPTY_HOSTFAIL_STATUS = " Host fail alert status value can't be empty";
    public static final String SESSION_CONF_EMPTY_REPLAYATTACK_LEVEL = " Replay attack level value can't be empty";
    public static final String SESSION_CONF_EMPTY_NUMOF_LOGS = " No Of Log Backup files can't be empty";

    public static final String SESSION_CONF_EMPTY_HOST_TIMEOUT_VALUE = " Host timeout value can't be empty";
    public static final String SESSION_CONF_INVALID_HOST_TIMEOUT_VALUE = " Host timeout value should a  numeric value between 100 and 100000";
    public static final String SESSION_CONF_EMPTY_LOG_FILE_NAME = " Log file name can't be empty";
    public static final String SESSION_CONF_EMPTY_CHANNALE_PKT_SIZE = " Channel packet size can't be empty";
    public static final String SESSION_CONF_INVALID_CHANNALE_PKT_SIZE = " Channel packet size should a  numeric value between 100 and 5000";
    public static final String SESSION_CONF_EMPTY_LISTENER_PKT_SIZE = " Listener packet size can't be empty";
    public static final String SESSION_CONF_INVALID_LISTENER_PKT_SIZE = " Listener packet size should a  numeric value between 100 and 5000";
    public static final String SESSION_CONF_EMPTY_MONITOR_IP = " Moniter ip can't be empty";
    public static final String SESSION_CONF_INVALID_MONITOR_IP = " Invalid moniter ip";
    public static final String SESSION_CONF_EMPTY_MONITOR_PORT = " Moniter port can't be empty";
    public static final String SESSION_CONF_INVALID_MONITOR_PORT = " Moniter port should a  numeric value between 3000 and 50000";
    public static final String SESSION_CONF_EMPTY_MONITOR_TIME_OUT = " Moniter time out can't be empty";
    public static final String SESSION_CONF_INVALID_MONITOR_TIME_OUT = " Moniter time out should a  numeric value between 1000 and 10000";
    public static final String SESSION_CONF_EMPTY_HOST_TEST_TIME = " Host test time can't be empty";
    public static final String SESSION_CONF_INVALID_HOST_TEST_TIME = " Host test time should a  numeric value between 100 and 50000";
    public static final String SESSION_CONF_EMPTY_CHANNEL_FIND_TIME_OUT = " Channel time out can't be empty";
    public static final String SESSION_CONF_INVALID_CHANNEL_FIND_TIME_OUT = " Channel time out should a numeric value";

    public static final String SESSION_CONF_UPDATE_FAIL = " Session configuration update is fail";
    public static final String SESSION_CONF_UPDATE_SUCCESS = " Session configuration update is successfully done";

    ////**************************Port Configuration***************
    public static final String PORT_CONF_INVALID_DATARATE = " Data rate must be selected";
    public static final String PORT_CONF_INVALID_DATABIT = " Data bit must be selected";
    public static final String PORT_CONF_INVALID_PARITY = " Parity must be selected";
    public static final String PORT_CONF_INVALID_STOPBITS = " Stop bits must be selected";
    public static final String PORT_CONF_INVALID_PORT = " Port must be selected";
    public static final String PORT_CONF_INVALID_STATUS = " Status must be selected";

    public static final String PORT_CONF_UPDATED = " Port configuration updated successfully";
    public static final String PORT_CONF_UPDATE_ERROR = " Port configuration updated fail";

    ///************************Server Configuration*******************
    public static final String SERVER_CONF_INVALID_MAX_POOL = " Thread MAX pool must be numeric";
    public static final String SERVER_CONF_EMPTY_MAX_POOL = " Thread MAX pool can't be empty";
    public static final String SERVER_CONF_EMPTY_MIN_POOL = " Thread MIN pool can't be empty";
    public static final String SERVER_CONF_INVALID_MIN_POOL = " Thread MIN pool must be numeric";
    public static final String SERVER_CONF_EMPTY_MAX_QUEUE_SIZE = " MAX queue can't be empty";
    public static final String SERVER_CONF_INVALID_MAX_QUEUE_SIZE = " MAX queue size must be numeric";
    public static final String SERVER_CONF_INVALID_BACKLOG_SIZE = " Backlog size must be numeric";
    public static final String SERVER_CONF_EMPTY_BACKLOG_SIZE = " Backlog size can't be empty";
    public static final String SERVER_CONF_INVALID_VIP = " Invalid VIP";
    public static final String SERVER_CONF_EMPTY_VIP = "VIP can't be empty";
    public static final String SERVER_CONF_INVALID_MONITOR_IP = " Invalid IP";
    public static final String SERVER_CONF_INVALID_NUMOF_LOGFILES = " No Of Log Backup files must be numeric";
    public static final String SERVER_CONF_INVALID_MONITOR_PORT = " Port must be numeric";
    public static final String SERVER_CONF_INVALID_MONITOR_TIMEOUT = "Timeout must be numeric";
    public static final String SERVER_CONF_EMPTY_HOSTFAIL_ALERTS = "Host fail alert status can't be empty";
    public static final String SERVER_CONF_EMPTY_ATTACKLEVEL = "Replay attack level can't be empty";
    public static final String SERVER_CONF_EMPTY_AUTOREG = "Auto registry status can't be empty";
    public static final String SERVER_CONF_LOGLEVEL = "Log level can't be empty";
    public static final String SERVER_CONF_EMPTY_LOGBACKUP = "Log backup status can't be empty";
    public static final String SERVER_CONF_EMPTY_NUMOFHISTORY = "Number of history records can't be empty";
    public static final String SERVER_CONF_INVALID_NUMOFHISTORY = "Number of history records must be numeric";
    public static final String SERVER_CONF_EMPTY_COREDBUGSTATUS = "Core debug status can't be empty";
    public static final String SERVER_CONF_EMPTY_HSMSLOT = "HSM Slot can't be empty";
    public static final String SERVER_CONF_EMPTY_ESMSTATUS = "ESM status can't be empty";
    public static final String SERVER_CONF_EMPTY_MONITOR = "Monitor status can't be empty";
    public static final String SERVER_CONF_EMPTY_SMSURL = "SMS service URL can't be empty";
    public static final String SERVER_CONF_INVALID_SMSURL = "Invalid SMS service URL";
    public static final String SERVER_CONF_EMPTY_SMSTIMEOUT = "SMS service timeout can't be empty";
    public static final String SERVER_CONF_INVALID_SMSTIMEOUT = "Invalid SMS service timeout";
    public static final String SERVER_CONF_INVALID_IV = " Invalid IV";
    public static final String SERVER_CONF_EMPTY_IV = "IV can't be empty";
    public static final String SERVER_CONF_EMPTY_BUFFER = "Maximum buffer size can't be empty";
    public static final String SERVER_CONF_INVALID_BUFFER = "Maximum buffer size must be numeric and greater than 249";
    public static final String SERVER_CONF_EMPTY_LOGFILENAME = "Log file name can't be empty";
    public static final String SERVER_CONF_EMPTY_NUMLOGBACKFILES = "No Of Log Backup files can't be empty";
    public static final String SERVER_CONF_INVALID_NUMLOGBACKFILES = "No Of Log Backup files must be numeric";
    public static final String SERVER_CONF_EMPTY_HSMPIN = "HSM PIN can't be empty";
    public static final String SERVER_CONF_EMPTY_MONIP = "IP can't be empty";
    public static final String SERVER_CONF_EMPTY_PORT = "Port can't be empty";
    public static final String SERVER_CONF_EMPTY_TIMEOUT = "Timeout can't be empty";
    public static final String SERVER_CONF_EMPTY_SMSUSERNAME = "SMS username can't be empty";
    public static final String SERVER_CONF_EMPTY_SMSPASSWORD = "SMS password can't be empty";
    public static final String SERVER_CONF_MISMATCH_EMAIL_PASS = "Email gateway password mismatch.!";
    public static final String SERVER_CONF_EMPTY_EMAIL_PASS = "Email gateway password can't be empty.!";
    public static final String SERVER_CONF_EMPTY_ADURL = "AD URL can't be empty";
    public static final String SERVER_CONF_EMPTY_ADUSERNAME = "AD username can't be empty";
    public static final String SERVER_CONF_EMPTY_ADPASSWORD = "AD password can't be empty";
    public static final String SERVER_CONF_EMPTY_PINCOUNTER = "Maximum PIN counter can't be empty";
    public static final String SERVER_CONF_EMPTY_SERVICEPORT = "Service port can't be empty";
    public static final String SERVER_CONF_INVALID_PINCOUNTER = "Maximum PIN counter must be numeric";
    public static final String SERVER_CONF_EMPTY_VIPSTATUS = "VIP status can't be empty";
    public static final String SERVER_CONF_EMPTY_ADVERIFYSTATUS = "AD verify status can't be empty";
    public static final String SERVER_CONF_INVALID_EMPTY_TIMEOUT = " Service client timeout can't be empty";
    public static final String SERVER_CONF_INVALID_CLIENT_TIMEOUT = " Service client timeout must be numeric";
    public static final String SERVER_CONFIG_INVALID_SMS_PORT = "SMS port must be numeric";

    public static final String SERVER_CONF_UPDATED = "Successfully completed and modifications are get effected after session/server restart";
    public static final String SERVER_CONF_UPDATED_ERROR = " Server configuration update is fail";

    ///********************Response Configuration********************************
    public static final String RESPONSE_CONF_UPDATED = "Response configuration updated successfully";
    public static final String RESPONSE_CONF_UPDATED_ERROR = "Response configuration updated fail";

    //**********************SMS Configuration**************************************
    public static final String SMS_PROFILE_ADD_SUCCESS = "Alert group profile add successfully";
    public static final String SMS_PROFILE_REGISTRATION_FAIL = "Alert group profile registration fail";
    public static final String SMS_PROFILE_UPDATE_SUCCESS = "Alert group profile updated successfully";
    public static final String SMS_PROFILE_UPDATED_ERROR = "Alert group profile update error";
    public static final String SMS_PROFILE_DELETE_SUCESS = "Alert group profile deleted successfully";
    public static final String SMS_PROFILE_DELETE_FAIL = "Alert group profile delete error";

    public static final String SMS_PROFILE_ALREADY_NAME = "Alert group name already defined";
    public static final String SMS_PROFILE_INVALID_PROFILE_NAME = "Alert group name can't be empty";
    public static final String SMS_PROFILE_INVALID_NAME = "Invalid group name";

    public static final String SMS_GROUP_INFO_ADD_SUCCESS = "Alert group information registered successfully";
    public static final String SMS_GROUP_INFO_REGISTRATION_FAIL = "Alert group information registration fail";

    public static final String MOBILE_EMPTY = " Mobile number can not be empty";
    public static final String INVALID_EMAIL = " Invalid email";
    public static final String MOBILE_ALREDY_DEFINED = " Mobile number already defined";
     public static final String INVALID_MOBILE= " Invalid mobile number";

    public static final String INFORMATION_DELETE_SUCESS = "Information delete successfull";
    public static final String INFORMATION_DELETE_FAIL = "Information delete fail";

    public static final String SMS_ADD_SUCCESS = "SMS filter message registered successfully";
    public static final String SMS_REGISTRATION_FAIL = "SMS filter message registration fail";
    public static final String SMS_DELETE_SUCESS = "SMS filter message delete successfull";
    public static final String SMS_DELETE_FAIL = "SMS filter message delete fail";

    public static final String SMS_EMPTY = " Message can not be empty";
    public static final String SMS_ALREADY_DEFINED = " Message already defined";

    ////////////////////////////////////////
    //////User Profile Management///////
    ///////////////////////////////////////
    public static final String USRP_PROFILE_ADD_SUCCESS = "User profile add successfully";
    public static final String USRP_PROFILE_ADD_FAIL = "User profile add fail";
    public static final String USRP_PROFILE_NOT_SELECT_PAGES = "Please select sections,modules and task";
    public static final String USRP_PROFILE_ALREADY = "User profile name already inserted";
    public static final String USRP_PROFILE_NAME_EMPTY = "Empty user profile name";
    public static final String USRP_PROFILE_NAME_INVALID = "Invalid user profile name";
    public static final String USRP_PROFILE_STATUS = "Select user profile status";

    public static final String USRP_ROLE_UPDATED = "User profile update successfully";
    public static final String USRP_ROLE_UPDATED_FAIL = "User profile update fail";
    public static final String USRP_PROF_DELETED = "User profile delete successfully";
    public static final String USRP_PROF_DELETED_FAIL = "User profile delete fail";
    public static final String USRP_PROF_ALREADY_USED = "User profile already used";
    public static final String USRP_ROLE_INVALID_SEARCH = "User name invalid fail";

    /////////////////////////////////////////////////////////////////////////
    // Host managment
    ////////////////////////////////////////////////////////////////////////
    public static final String HL_EMPTY_NII = "Empty nii";
    public static final String HL_EMPTY_HOST_NAME = "Empty hostname";
    public static final String HL_EMPTY_IP = "Empty IP";
    public static final String HL_EMPTY_PORT = "Empty port";
    public static final String HL_EMPTY_TIMEOUT = "Empty timeout";
    public static final String HL_INVALID_NII = "Invalid nii";
    public static final String HL_INVALID_HOST_NAME = "Invalid hostname";
    public static final String HL_INVALID_IP = "Invalid IP";
    public static final String HL_INVALID_PORT = "Invalid port";
    public static final String HL_INVALID_SEC_IP = "Invalid secondary IP";
    public static final String HL_INVALID_SEC_PORT = "Invalid secondary port";
    public static final String HL_CON_EMPTY_TIMEOUT = "Empty connection timeout";
    public static final String HL_READ_EMPTY_TIMEOUT = "Empty read timeout";
    public static final String HL_CON_INVALID_TIMEOUT = "Invalid timeout";
    public static final String HL_READ_INVALID_TIMEOUT = "Invalid timeout";
    public static final String HL_INVALID_STATUS = "Invalid status";
    public static final String HL_INVALID_OPERATION = "Invalid operation method";
    public static final String HL_INVALID_TPDU_STATUS = "Invalid TPDU status";
    public static final String HL_INVALID_CON_TYPE = "Invalid connection type";
    public static final String HL_INVALID_LOAD_TYPE = "Invalid load balance status";
    public static final String HL_INVALID_FORWARD_METHOD = "Invalid forward method";
    public static final String HL_EMPTY_MAPNII = "Empty mapped nii";

    public static final String HL_CHANNEL_ADD_SUCCESS = "Channel added successfully";
    public static final String HL_CHANNEL_ADD_FAIL = "Channel add fail";
    public static final String HL_CHANNEL_UPDATE_FAIL = "Channel update fail";
    public static final String HL_CHANNEL_UPDATE_SUCCESS = "Channel updated successfully";
    public static final String HL_CHANNEL_NAME_DEFINE = "Channel name already define";
    public static final String HL_CHANNEL_PORT_DEFINE = "Channel IP/Port already define";
    public static final String HL_CHANNEL_SECOND_PORT_DEFINE = "Channel secondary IP/Port already define";
    public static final String HL_CHANNEL_DELETE = "Channel deleted successfully";
    public static final String HL_CHANNEL_DELETE_FAIL = "Channel delete fail";

    public static final String BLOCK_BIN_ADD_SUCCESS = "Block BIN registered successfully";
    public static final String BLOCK_BIN_REGISTRATION_FAIL = "Block BIN registration fail";
    public static final String BLOCK_BIN_INVALID_NAME = "Invalid name";
    public static final String BLOCK_BIN_ALREADY_NAME = "Block BIN already defined";
    public static final String BLOCK_BIN_INVALID_PROFILE_NAME = "Block BIN profile can't be empty";
    public static final String BLOCK_BIN_STATUS_SELECTED = "Status must be selected";
    public static final String BLOCK_BIN_UPDATE_SUCCESS = "Block BIN updated successfully";
    public static final String BLOCK_BIN_UPDATE_FAIL = "Block BIN update fail";
    public static final String BLOCK_BIN_DELETE_SUCESS = "Block BIN deleted successfully";
    public static final String BLOCK_BIN_DELETE_FAIL = "Block BIN delete error";
    public static final String BLOCK_BIN_UPDATED = "Block BIN updated successfully";
    public static final String BLOCK_BIN_UPDATED_ERROR = "Block BIN update error";

    public static final String REF_TERMINAL_NAME_ALREADY = "Terminal risk profile name already defined";
    public static final String REF_TERMINAL_NAME_EMPTY = "Terminal risk profile name can't be empty";
    public static final String REF_TERMINAL_MIN_EMPTY = "Minimum amount per transaction can't be empty";
    public static final String REF_TERMINAL_MIN_INVALID = "Minimum amount per transaction must be numeric";
    public static final String REF_TERMINAL_MAX_EMPTY = "Maximum amount per transaction can't be empty";
    public static final String REF_TERMINAL_MAX_INVALID = "Maximum amount per transaction must be numeric";
    public static final String REF_TERMINAL_OPRH_FROM = "Day operation hours From can't be empty";
    public static final String REF_TERMINAL_OPRH_TO = "Day operation hours to can't be empty";
    public static final String REF_TERMINAL_OPRH_INVALID = "Day operation hours to value should be '0' or grater than from value";
    public static final String REF_TERMINAL_OPRH_CANNOT_SAME = "Day operation hours to value and from value can not be same";
    public static final String REF_TERMINAL_SWIPE_EMPTY = "Perform non-EMV transaction can't be empty";
    public static final String REF_TERMINAL_FALLBACK_EMPTY = "Perform fall-back transaction can't be empty";
    public static final String REF_TERMINAL_NFC_EMPTY = "Perform NFC based transaction can't be empty";
    public static final String REF_TERMINAL_PIN_EMPTY = "Request PIN to perform transaction can't be empty";
    public static final String REF_TERMINAL_ADD_SUCCESS = "Terminal risk profile registered successfully";
    public static final String REF_TERMINAL_ADD_FAIL = "Terminal risk profile registration fail";
    public static final String REF_TERMINAL_DELETE_SUCCESS = "Terminal risk profile deleted successfully";
    public static final String REF_TERMINAL_DELETE_FAIL = "Terminal risk profile deleted fail";
    public static final String REF_TERMINAL_UPDATE_SUCCESS = "Terminal risk profile updated successfully";
    public static final String REF_TERMINAL_UPDATE_FAIL = "Terminal risk profile updated fail";
    public static final String REF_TERMINAL_MIN_MAX_LENGTH_INVALID = "Minimum amount per transaction higher than maximum amount per transaction";

    public static final String BIN_ADD_SUCCESS = "BIN range is registered successfully";
    public static final String BIN_REGISTRATION_FAIL = "BIN registration fail";
    public static final String BIN_INVALID_NAME = "BIN must be numeric with 6 digits";
    public static final String BIN_INVALID_BLANK_NAME = "BIN can't be empty";
    public static final String BIN_ALREADY_NAME = "BIN already define : ";
    public static final String BIN_DELETE_SUCESS = "BIN delete successfull";
    public static final String BIN_DELETE_FAIL = "BIN delete error";

    public static final String NII_GROUP_ADD_SUCCESS = "NII registered successfully";
    public static final String NII_GROUP_REGISTRATION_FAIL = "NII registration fail";
    public static final String NII_ALREADY_DEFINE = "NII or MAP NII already define to";
    public static final String NII_GROUP_INVALID_NAME = "Invalid name";
    public static final String NII_GROUP_NUMBER = "Invalid NII. NII must be the number";
    public static final String NII_GROUP_MAP_NII_NUMBER = "Invalid MAP NII. MAP NII must be the number";
    public static final String NII_GROUP_INVALID_CHANNEL = "Invalid channel name";
    public static final String NII_GROUP_INVALID_BLANK_NAME = "Empty NII";
    public static final String NII_GROUP_INVALID_BLANK_DESCRIPTION = "Empty map NII";

    public static final String NII_GROUP_DELETE_SUCESS = "NII group deleted successfully";
    public static final String NII_GROUP_DELETE_FAIL = "NII group delete error";

    public static final String NII_INVALID_NAME = "NII should be is numeric";
    public static final String NII_INVALID_MAP_NII = "MAP NII should be is numeric";
    public static final String NII_ADD_SUCCESS = "NII is registered successfully";
    public static final String NII_REGISTRATION_FAIL = "NII registration fail";
    public static final String NII_DELETE_SUCESS = "NII deleted successfully";
    public static final String NII_DELETE_FAIL = "NII delete error";

    public static final String HL_LISTENER_DELETE_FAIL = "Listener delete fail";

    public static final String HL_LISTENER_UPDATE_SUCCESS = "Listener Updated successfully";
    public static final String HL_LISTENER_UPDATE_FAIL = "Listener Update Fail";
    public static final String HL_LISTENER_ADD_SUCCESS = "Listener added successfully";
    public static final String HL_LISTENER_ALREADY = "Listener Name Already define";
    public static final String HL_LISTENER_PORT_ALREADY = "Listener Port Already define";
    public static final String HL_LISTENER_ADD_FAIL = "Listener add fail";

    public static String HL_EMPTY_CHANNEL_NAME = "Empty channel name";
    public static String HL_EMPTY_ISO_NAME = "Empty ISO file name";
    public static String HL_EMPTY_ENCRYPT_ZPK_SIZE = "Empty encrypt ZPK";
    public static String HL_INVALID_CHANNEL_NAME = "Invalid channel name";
    public static String HL_EMPTY_LISTENER_NAME = "Empty listener name";
    public static String HL_LISTENER_DELETE_SUCCESS = "Listener deleted successfully";
    public static String HL_INVALID_LISTENER_NAME = "Invalid listener name";
    public static String HL_INVALID_LISTENER_PORT = "Invalid listener port";
    public static String HL_EMPTY_CONNECTION_TYPE = "Empty connection type";
    public static String HL_EMPTY_LISTINER_TIMEOUT = "Empty listener timeout";
    public static String HL_INVALID_LISTINER_TIMEOUT = "Invalid listener timeout";
    public static String HL_EMPTY_KEEP_ALIVE = "Empty keep alive";
    public static String HL_EMPTY_HEADER_FORMAT = "Empty header format";
    public static String HL_EMPTY_HEADER_SIZE = "Empty header size";
    public static String HL_EMPTY_STATUS = "Empty status";
    public static String HL_EMPTY_ENCRYPT_ZPK_VALUE = "Encrypt ZPK must be hexadecimal value";
    public static String ZPK_LENGTH_TOO_HIGH = "Encrypt ZPK must be 32 charactors";

    public static String PROCESS_TIME = "Numeric values only";

    public static final String LOCAL_BIN_INVALID_PROFILE_NAME = "Bank BIN can't be empty";
    public static final String SYSTEM_BIN_INVALID_PROFILE_NAME = "System Block BIN can't be empty";
    public static final String LOCAL_BIN_INVALID_NAME = "Invalid BIN";
    public static final String LOCAL_BIN_EXIST= "Bank BIN is already exist";
    public static final String LOCAL_BIN_ADD_SUCCESS = "Bank BIN is registered successfully";
    public static final String SYSTEM_BLOCK_BIN_ADD_SUCCESS = "System Block BIN is registered successfully";
    public static final String LOCAL_BIN_REGISTRATION_FAIL = "Bank BIN registration fail";
    public static final String LOCAL_BIN_DELETE_SUCESS = "Bank BIN delete successful";
    public static final String SYSTEM_BLOCK_BIN_DELETE_SUCESS = "System Block BIN delete successful";
    public static final String LOCAL_BIN_DELETE_FAIL = "Bank BIN delete error";

    public static final String SEARCH_SUCCESS = "Success search";
    public static final String SUCCESS_EXPORT = " Successfully created excel report";
    public static final String SUCCESS_DOWNLOAD_PDF = " Successfully created PDF";
}
