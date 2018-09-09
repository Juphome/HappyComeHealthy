package com.happycomehealthy.xlog;

import android.content.Context;
import android.os.Environment;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class XlogHelper {
    private static XlogHelper mInstance;

    public static XlogHelper getInstance(){
        if (mInstance == null){
            synchronized (XlogHelper.class){
                mInstance = new XlogHelper();
            }
        }
        return mInstance;
    }

    public void initXlog(Context context) {
        String packageName = context.getPackageName();

        LogConfiguration config = new LogConfiguration.Builder()
//				.logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
//						: LogLevel.NONE)
                .tag(packageName)                                         // Specify TAG, default: "X-LOG"
//				.st(2)                                                 // Enable stack trace info with depth 2, disabled by default
//				.b()                                                   // Enable border, disabled by default
                .build();

        Printer androidPrinter = new AndroidPrinter();             // Printer that print the log using android.util.Log
        Printer consolePrinter = new ConsolePrinter();             // Printer that print the log to console using System.out
        Printer filePrinter = new FilePrinter                      // Printer that print the log to the file system
                .Builder(Environment.getExternalStorageDirectory().getPath()+"/"+"Android"+"/"+packageName+"/xlog")                              // Specify the path to save log file
                .fileNameGenerator(new SmartLogNameGenerator())        // Default: ChangelessFileNameGenerator("log")
//				.backupStrategy(new NeverBackupStrategy())             // Default: FileSizeBackupStrategy(1024 * 1024)
                .logFlattener(new TimeStampFlattener())                       // Default: DefaultFlattener
                .build();
        XLog.init(                                                 // Initialize XLog
                config,                                                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
                androidPrinter,                                        // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
                consolePrinter,
                filePrinter);
    }

}
