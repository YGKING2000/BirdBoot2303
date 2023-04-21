package com.birdboot.http;

/**
 * @Description 空请求异常
 * @ClassName EmptyRequestException
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/19 14:10
 * @Version 1.0
 */
public class EmptyRequestException extends Exception {
    public EmptyRequestException() {
    }

    public EmptyRequestException(String message) {
        super(message);
    }

    public EmptyRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequestException(Throwable cause) {
        super(cause);
    }

    public EmptyRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
