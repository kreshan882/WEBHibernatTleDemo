/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import com.epic.tle.userManagement.bean.PasswordPolicyBean;
import com.epic.tle.userManagement.service.PasswordPolicyFactory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nipun_t
 */
public class PasswordValidator {

    public synchronized String validatePassword(String password) {
//        System.out.println("id " + id);
        char characters[] = password.toCharArray();
        PasswordPolicyBean passPolcyBean = getPasswordPolicys();

        if (null != passPolcyBean) {

            if (Integer.parseInt(passPolcyBean.getMin_len()) <= characters.length && Integer.parseInt(passPolcyBean.getMax_len()) >= characters.length) {

//                if (isValidSpecialChars(characters, passPolcyBean)) {
                if (validSpecialChars(password, passPolcyBean.getAllowspecialcharacters())) {

                    if (Integer.parseInt(passPolcyBean.getMin_upercase()) <= numOfUpperCases(characters) && Integer.parseInt(passPolcyBean.getMin_lowcase()) <= numOflowerCases(characters)) {

                        if (Integer.parseInt(passPolcyBean.getMin_numerics().trim()) <= numOfNumerics(characters)) {

                            if (Integer.parseInt(passPolcyBean.getMin_spcl_chars()) <= numOfSpecialChars(characters, passPolcyBean) && Integer.parseInt(passPolcyBean.getMax_spcl_chars()) >= numOfSpecialChars(characters, passPolcyBean)) {
                                return "success";
                            } else {
                                return "Invalid number of special characters. Min is " + passPolcyBean.getMin_numerics() + " Max is " + passPolcyBean.getMax_spcl_chars() + " values.";
                            }
                        } else {
                            return "Invalid password, your password should have  " + passPolcyBean.getMin_numerics() + " or more numeric values.";
                        }

                    } else {
                        return "Invalid password, your password should have " + passPolcyBean.getMin_upercase() + " or more uppercase letters and " + passPolcyBean.getMin_lowcase() + " or more lowercase letters.";
                    }

                } else {
                    return "Invalid password, use no of special charactors in range ( " + passPolcyBean.getMin_spcl_chars() + " , " + passPolcyBean.getMax_spcl_chars() + " ) : Allowed special characters ( " + passPolcyBean.getAllowspecialcharacters() + " ) ";
                }

            } else {
                return "Invalid password legnth. Please insert " + passPolcyBean.getMin_len() + " to " + passPolcyBean.getMax_len() + " values.";
            }
        } else {
            return "Error please create password policy first";
        }
    }

    private PasswordPolicyBean getPasswordPolicys() {
        PasswordPolicyBean pass = null;
        try {

            pass = this.viewPasswordPolicyData();

        } catch (Exception e) {

            try {
                LogFileCreator.writeErrorToLog(e);
            } catch (Exception ex) {
                Logger.getLogger(PasswordValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pass;
    }

    public ArrayList<String> getPreviousPasswords(int id) {

        return null;
    }

    public PasswordPolicyBean viewPasswordPolicyData() throws Exception {
        HibernateInit hibernateInit = new HibernateInit();
        hibernateInit.initialize();
        PasswordPolicyBean passwordPolicyBean = new PasswordPolicyBean();
        PasswordPolicyFactory passwordPolicyFactory = new PasswordPolicyFactory();
        return passwordPolicyFactory.getPasswordPolicyServiceInf().viewPasswordPolicyDetails(passwordPolicyBean);
    }

    private int numOfUpperCases(char[] charArray) {
        int numbers = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isUpperCase(charArray[i])) {
                numbers++;
            }
        }
        return numbers;
    }

    private int numOflowerCases(char[] charArray) {
        int numbers = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLowerCase(charArray[i])) {
                numbers++;
            }
        }
        return numbers;
    }

    private int numOfNumerics(char[] charArray) {
        int numbers = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (String.valueOf(charArray[i]).matches("[0-9]")) {
                numbers++;
            }
        }
        return numbers;
    }

    private boolean isValidSpecialChars(char[] charArray, PasswordPolicyBean passpolBean) {

        int numbers = 0, numOfDoler = 0, counter = 0;
        String validVal = "", secondValidator = "$";
        char chars[] = passpolBean.getAllowspecialcharacters().toCharArray();
        numOfDoler = passpolBean.getAllowspecialcharacters().compareTo("$");

        for (int i = 0; i < chars.length; i++) {
            // if (!"$".equals(String.valueOf(chars[i]))) {

            if ((counter + 1) != (chars.length - numOfDoler)) {
                validVal += String.valueOf(chars[i]) + "|";

            } else {
                validVal += String.valueOf(chars[i]);

            }
            counter++;

            //  }
        }

        for (int i = 0; i < charArray.length; i++) {
            if (String.valueOf(charArray[i]).matches(validVal)) {
                numbers++;
            } else if (String.valueOf(charArray[i]).matches("[" + secondValidator + "]")) {
                numbers++;
            }
        }

        if (numbers >= Integer.parseInt(passpolBean.getMin_spcl_chars()) && numbers <= Integer.parseInt(passpolBean.getMax_spcl_chars())) {
            return true;
        } else {
            return false;
        }
    }

    private int numOfSpecialChars(char[] charArray, PasswordPolicyBean passpolBean) {

        ArrayList<Character> characters = new ArrayList<>();

        for (char d : charArray) {
            if (!Character.isLetterOrDigit(d)) {
                characters.add(d);
            }

        }
//        System.out.println( passpolBean.getAllowspecialcharacters());
//        System.out.println( passpolBean.getAllowspecialcharacters().trim().length());
//        System.out.println(characters.size());
//        String spclChars = passpolBean.getAllowspecialcharacters();
//        int numbers = 0;
//        for (int i = 0; i < charArray.length; i++) {
//            if (String.valueOf(charArray[i]).matches("[" + spclChars + "]")) {
//                numbers++;
//            }
//        }
        return characters.size();
    }

    private boolean validSpecialChars(String password, String special_chars) {
        boolean isok = false;
        char[] toCharArray = password.toCharArray();
        char[] spa = special_chars.toCharArray();
        for (char u : toCharArray) {
            if (!Character.isLetterOrDigit(u)) {
                boolean b = false;
                for (char t : spa) {
                    if (Character.toString(t).equals(Character.toString(u))) {
                        isok = true;
                        b = true;
                        break;
                    } else {
                        b = false;
                        isok = false;
                    }
                }
                if (!b) {
                    break;
                }

            }
        }
        return isok;
    }
}
