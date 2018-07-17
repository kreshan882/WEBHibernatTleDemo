/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util.alerts;

import com.epic.tle.util.constant.Configurations;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author gayan_s
 */
public class BMLAlertSender {

    public static boolean sendMail(String senderEmail, String reciverEmail, String subject, String message, String URL, String username, String password, int port) throws Exception {
        Transport transport = null;
        boolean rc = false;
        try {
            String messageText = message;
            boolean sessionDebug = true;
            Properties props = System.getProperties();
            props.put("mail.host", URL);
            props.put("mail.transport.protocol.", "smtp");
            props.put("mail.smtp.auth", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.timeout", Configurations.MAIL_TIMEOUT);
            props.put("mail.smtp.connectiontimeout", Configurations.MAIL_TIMEOUT);
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(senderEmail));
            InternetAddress[] address = {new InternetAddress(reciverEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = messageText;
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
            transport = mailSession.getTransport("smtp");
            transport.connect();
//            transport.connect(URL, port, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            rc = true;
        } catch (GeneralSecurityException | MessagingException e) {
            throw e;
        } finally {
            transport.close();
            transport = null;
        }
        return rc;
    }

    public static boolean sendSMS(String sentNumber, String message, String URL, String password, String username, int timeout) throws Exception {

        boolean rc = false;
        HttpURLConnection urlConnection = null;
        BufferedWriter bWriter = null;
        BufferedReader bReader = null;

        try {

//		Configurations.VENDER_URL_SINGLE = "http://smsgate.bml.com.mv:13013/cgi-bin/sendsms";  
            String dateLine = "?username=" + username
                    + "&password=" + password
                    + "&to=" + sentNumber
                    + "&text=" + URLEncoder.encode(message);

            URL = URL + dateLine;

            URL url = new URL(URL);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(timeout);
            urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//			bWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
//
//			bWriter.write(dateLine);
//
//			bWriter.flush();

            bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            String serverResponse = "";
            while (null != ((line = bReader.readLine()))) {
                serverResponse += line;
            }

            bReader.close();
            bReader = null;
            if (serverResponse.startsWith("0: Accepted for delivery")) {
                rc = true;
            }
            serverResponse = null;
            line = null;

        } catch (Exception e) {

            throw e;
        } finally {
            try {
                if (bWriter != null) {
                    bWriter.close();
                }
                if (bReader != null) {
                    bReader.close();
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                urlConnection = null;
                bWriter = null;
                bReader = null;
            } catch (Exception e) {

            }
        }

        return rc;
    }
}
