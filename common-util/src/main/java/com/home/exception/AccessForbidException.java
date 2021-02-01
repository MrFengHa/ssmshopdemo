package com.home.exception;

/**
 * 表示用户没有登录就访问受保护资源时的异常
 * @author 冯根源
 * @date 2021/2/1 22:02
 */
public class AccessForbidException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AccessForbidException() {
        super();
    }

    public AccessForbidException(String message) {
        super(message);
    }

    public AccessForbidException(String message, Throwable cause) {
        super(message, cause);
    }


    public AccessForbidException(Throwable cause) {
        super(cause);
    }


    protected AccessForbidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
