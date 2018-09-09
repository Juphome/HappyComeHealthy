package com.happycomehealthy.net;

/**
 * creator: ZZF
 * careate date: 2017/10/25  09:19.
 */

public class ApiException extends Exception {

    private String retCode;
    private String data;

    public static final int UNKNOWN = 1000;
    public static final int PARSE_ERROR = 1001;

    public ApiException(Throwable throwable, String code) {
        super(throwable);
        this.retCode = code;
    }

    public String getCode() {
        return retCode;
    }
    public String getData() {
        return data;
    }
    public void setData(String msg) {
        this.data = msg + "(retCode:" + retCode + ")";
    }
    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return retCode.equals("0");
    }
}
