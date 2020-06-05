package com.demo.mvpdemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.demo.mvpdemo.exception.CrashHandler;
import com.demo.mvpdemo.greenDao.DaoMaster;
import com.demo.mvpdemo.greenDao.DaoSession;
import com.demo.mvpdemo.utils.SpUtil;

public class App extends Application {
    private static App mContext;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        SpUtil.init(mContext);
        initGreenDao();
        initData();
    }

    private void initData() {
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "DemoManage.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static App getInstance() {
        return mContext;
    }
}
