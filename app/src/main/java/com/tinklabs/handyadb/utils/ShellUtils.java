package com.tinklabs.handyadb.utils;

import java.net.URL;

/**
 * Helper class containing Shell unility
 */
public class ShellUtils {
    protected static IShellUtils impl;

    public static void setImplementation(IShellUtils impl) {
        ShellUtils.impl = impl;
    }

    public static String catFile(String path) {
        return impl == null ? null : impl.catFile(path);
    }

    public static String runCommandGetResponseStringRoot(String command) {
        return impl == null ? null : impl.runCommandGetResponseStringRoot(command);
    }

    public static boolean chmod(String path, String value) {
        return impl != null && impl.chmod(path, value);
    }

    /**
     * Copies a fild from source to destination
     *
     * @param src source path
     * @param dst destionation path
     * @return true on success
     */
    public static boolean copyFile(String src, String dst) {
        return impl != null && impl.copyFile(src, dst);
    }

    /**
     * Copies a fild from source to destination
     *
     * @param src   source path
     * @param dst   destionation path
     * @param chmod to apply
     * @return true on success
     */
    public static boolean copyFile(String src, String dst, String chmod) {
        return impl != null && impl.copyFile(src, dst, chmod);
    }

    /**
     * Returns if given path exist
     *
     * @param path to validate
     * @return true if exist
     */
    public static boolean doesExists(String path) {
        return impl != null && impl.doesExists(path);
    }

    /**
     * echo string to file
     *
     * @param echoString
     * @param filePath
     * @return true on success
     */


    public static boolean echoFile(String echoString, String filePath) {
        return impl != null && impl.echoFile(echoString, filePath);
    }

    /**
     * Returns md5 of the given file
     *
     * @param src of the file
     * @return md5 hash if available, otherwise null
     */
    public static String getMD5(String src) {
        return impl == null ? null : impl.getMD5(src);
    }

    /**
     * Deletes a file
     *
     * @return true if success
     */
    public static boolean removeFile(String src) {
        return impl != null && impl.removeFile(src);
    }

    public static boolean removeFile2(String src) {
        return impl != null && impl.removeFile2(src);
    }

    public static boolean renameFile(String source, String destination) {
        return impl != null && impl.renameFile(source, destination);
    }

    /**
     * Executes a command and returns an exit code
     *
     * @param command to execute
     * @return an exit code
     */
    public static int runCommand(String command) {
        return impl == null ? -1 : impl.runCommand(command);
    }

    /**
     * Executes a command and returns a response
     *
     * @param command to execute
     * @return response if any
     */
    public static String runCommandGetResponseString(String command) {
        return impl == null ? "" : impl.runCommandGetResponseString(command);
    }

    /**
     * Executes a command and returns an exit code. This will also mount /system with rw flag when the command is being executed
     *
     * @param command to execute
     * @return an exit code
     */
    public static int runCommandRW(String command) {
        return impl == null ? -1 : impl.runCommandRW(command);
    }

    public static boolean mkdirs(String backupFolder) {
        return impl != null && impl.mkdirs(backupFolder);
    }

    public static void reboot() {
        if (impl != null) {
            impl.reboot();
        }
    }

    public static long getFileSize(String path) {
        return impl == null ? 0 : impl.getFileSize(path);
    }

    public static int curl(URL url, String dest) {
        return impl == null ? -1 : impl.curl(url, dest);
    }

    public static boolean isCurlAvailable() {
        return impl != null && impl.isCurlAvailable();
    }
}