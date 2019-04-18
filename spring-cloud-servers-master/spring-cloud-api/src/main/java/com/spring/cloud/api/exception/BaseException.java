package com.spring.cloud.api.exception;

/**
 * 所有异常父类
 *
 * @author wangmj
 * @since 2018/11/2
 */
public class BaseException extends RuntimeException {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
