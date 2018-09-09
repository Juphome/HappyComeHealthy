package com.happycomehealthy;

import android.app.Application;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.happycomehealthy.utils.CrashHandler;
import com.happycomehealthy.utils.PreferenceUtils;
import com.happycomehealthy.utils.ToastUtils;
import com.happycomehealthy.xlog.XlogHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class MyApplication extends Application {
    private static final String DB_NAME = "news-db";

    //    private static ApplicationComponent sAppComponent;
    private static Context sContext;

    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
//    private RxBus mRxBus = new RxBus();
//    private AppComponent appComponent;
    private SQLiteDatabase liteDatabase;

    public static MyApplication get(Context context){
        return (MyApplication)context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        initXlog();
        initDatabase();
//        initInjector();
        initConfig();
        initSharedPreferences();
        initLogger();


    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
//                .logStrategy(customLog) //（可选）更改要打印的日志策略。 默认LogCat
                .tag("POWER")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


    private void initSharedPreferences() {
        PreferenceUtils.getInstance(this.getApplicationContext(),"SharedPreferences");
    }

    private void initXlog() {
        XlogHelper.getInstance().initXlog(this);
    }


    public static Context getContext() {
        return sContext;
    }

    /**
     * 初始化注射器
     */
    private void initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
//        sAppComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this, mDaoSession, mRxBus))
//                .build();
//        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    /**
     * 初始化数据库
     */
    private void initDatabase() {
        LitePal.initialize(this);
//        liteDatabase = LitePal.getDatabase();
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        if (BuildConfig.DEBUG) {

        }
        CrashHandler.getInstance().init(getApplicationContext());
        ToastUtils.init(getApplicationContext());

    }


    //重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }

    //Android 4.0会调用此方法获取数据库。
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }

    @Override
    public File getDatabasePath(String name) {
        File parentFile = new File(Environment.getExternalStorageDirectory() + File.separator +
                "smartDB" + File.separator+getPackageName() +File.separator);
        if(!parentFile.exists()){
            boolean mkParentRes = parentFile.mkdirs();
        }

        File realDBFile = new File(parentFile,name);
        if(!realDBFile.exists()){
            try {
                realDBFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return realDBFile;
    }

}
