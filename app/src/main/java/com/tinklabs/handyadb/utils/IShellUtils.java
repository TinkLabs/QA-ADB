package com.tinklabs.handyadb.utils;

import java.net.URL;

/**
 * Created by Kriz on 22/3/2016.
 */
public interface IShellUtils {
    String catFile(String path);

    boolean chmod(String path, String value);

    boolean copyFile(String src, String dst);

    boolean copyFile(String src, String dst, String chmod);

    boolean doesExists(String path);

    boolean echoFile(String echoString, String filePath);

    String getMD5(String src);

    boolean removeFile(String src);

    boolean removeFile2(String src);

    boolean renameFile(String source, String destination);

    int runCommand(String command);

    String runCommandGetResponseString(String command);

    String runCommandGetResponseStringRoot(String command);

    int runCommandRW(String command);

    boolean mkdirs(String backupFolder);

    void reboot();

    long getFileSize(String remove);

    int curl(URL url, String dEST_PATH);

    boolean isCurlAvailable();
}
