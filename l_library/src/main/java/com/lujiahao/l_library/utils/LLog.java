package com.lujiahao.l_library.utils;

import android.util.Log;

/**
 * 自定义日志处理器
 * 1. 日志等级管理
 * 2. 日志格式输出调用类名，方法名，行数信息
 * 3.通过一个TAG打印所有的日志信息，方便过滤
 * Created by lujiahao
 * Created at 2016/5/16 15:07
 */
public class LLog {
    /**
     * TAG的名称
     */
    private static String TAG = "LLOG";

    private static boolean IS_FULL_CLASSNAME;

    /**
     * Log的级别
     */
    private static int LOG_LEVEL = Log.VERBOSE;

    /**
     * 设置是否打印全类名
     * @param isFullClassName 是否打印全类名
     */
    public static void setFullClassName(boolean isFullClassName){
        LLog.IS_FULL_CLASSNAME = isFullClassName;
    }

    /**
     * 设置log的打印级别
     * @param level log的打印级别，默认是Log.VERBOSE
     */
    public static void setLogLevel(int level){
        LLog.LOG_LEVEL = level;
    }

    /**
     * 设置TAG标签
     * @param tag
     */
    public static void setAppTAG(String tag){
        LLog.TAG = tag;
    }

    public static void v(String msg) {
        if (LOG_LEVEL <= Log.VERBOSE) {
            Log.v(TAG, getLogTitle() + msg);
        }
    }


    public static void d(String msg) {
        if (LOG_LEVEL <= Log.DEBUG) {
            Log.d(TAG, getLogTitle() + msg);
        }
    }

    public static void i(String msg) {
        if (LOG_LEVEL <= Log.INFO) {
            Log.i(TAG, getLogTitle() + msg);
        }
    }

    public static void w(String msg) {
        if (LOG_LEVEL <= Log.WARN) {
            Log.w(TAG, getLogTitle() + msg);
        }
    }

    public static void e(String msg) {
        if (LOG_LEVEL <= Log.ERROR) {
            Log.e(TAG, getLogTitle() + msg);
        }
    }

    private static String getLogTitle(){
        StackTraceElement elm = Thread.currentThread().getStackTrace()[4];
        String className = elm.getClassName();
        if (!IS_FULL_CLASSNAME){
            int dot = className.lastIndexOf('.');
            if (dot != -1){
                className = className.substring(dot+1);
            }
        }
        return className + "." + elm.getMethodName() + "(" + elm.getLineNumber() + ")" + ": ";
    }
}
