package com.happycomehealthy.xlog;

import com.elvishew.xlog.printer.file.naming.FileNameGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class SmartLogNameGenerator implements FileNameGenerator {

    ThreadLocal<SimpleDateFormat> mLocalDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        }
    };


    @Override
    public boolean isFileNameChangeable() {
        return true;
    }

    @Override
    public String generateFileName(int logLevel, long timestamp) {
        SimpleDateFormat sdf = mLocalDateFormat.get();
        sdf.setTimeZone(TimeZone.getDefault());
        String timeStampStr = sdf.format(new Date(timestamp));
        return timeStampStr+".txt";
    }
}
