package com.happycomehealthy.xlog;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.flattener.Flattener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class TimeStampFlattener implements Flattener {
    public static final String TIME_STAMP_PATTEN = "yyyy-MM-dd HH:mm:ss.SSS";
    @Override
    public CharSequence flatten(int logLevel, String tag, String message) {
        String timeStamp = new SimpleDateFormat(TIME_STAMP_PATTEN).format(new Date());
        return timeStamp
                + '|' + LogLevel.getShortLevelName(logLevel)
                + '|' + tag
                + '|' + message;
    }
}
