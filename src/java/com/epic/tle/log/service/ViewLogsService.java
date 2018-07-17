package com.epic.tle.log.service;

import com.epic.tle.log.bean.ViewLogsDataBean;
import com.epic.tle.log.bean.ViewLogsInputBean;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author dimuthu_t
 */
public class ViewLogsService {

    public List<ViewLogsDataBean> loadLogFileTable(ViewLogsInputBean bean, String orderBy, int max, int first) throws Exception {

        List<ViewLogsDataBean> dataList = new ArrayList<ViewLogsDataBean>();
        long totalCount = 0;

        try {
//            if (null == bean.getLogTypes() || bean.getLogTypes().equals("-1")) {
//                dataList = new ArrayList<ViewLogsDataBean>();
//            } else {
//                String logType = bean.getLogTypes();
            String logType = "log1";
//                String logArchivePath = Configurations.LOG_PATH + logType;
//                File f = new File(Util.getOSLogPath(logArchivePath));          
            String path = Util.getOSLogPath(Configurations.LOG_PATH);
//            System.out.println(path);
//            String path = Util.getOSLogPath("C:\\opt\\tlelog");
            System.out.println("path : " + path);

            File f = new File(path);
            File[] files = f.listFiles();
            List listFile = Arrays.asList(files);
            Collections.sort(listFile, Collections.reverseOrder());
            totalCount = files.length;
            dataList = new ArrayList<ViewLogsDataBean>();
            files = (File[]) listFile.toArray();
            if (max > files.length) {
                max = files.length;
            }
            for (int i = first; i < max; i++) {
                ViewLogsDataBean dataBean = new ViewLogsDataBean();
                dataBean.setLogFileName(files[i].getName());
                dataBean.setSize(files[i].length() / 1024 + "kB");
                dataBean.setPath(files[i].getAbsolutePath());
                dataBean.setLogFileType(logType);
                dataBean.setDate(getLastModified(files[i]));

                dataBean.setFullCount(totalCount);
                dataList.add(dataBean);
            }
//            }
        } catch (Exception e) {
            throw e;
        }
        return dataList;
    }

    private String getLastModified(File f) throws Exception {
        String df = "";
        if (f.exists()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            df = sdf.format(f.lastModified());
        }
        return df;
    }

    public void zipDirectory(File dir, String zipDirName) throws IOException {
        List<String> filesListInDir = new ArrayList<String>();
        try {
            filesListInDir = Util.getPopulateFilesList(dir);
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String filePath : filesListInDir) {
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
                zos.putNextEntry(ze);
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
