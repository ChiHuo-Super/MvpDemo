package com.demo.mvpdemo.exception;


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //TODO 进行闪退操作

        //退出程序
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
