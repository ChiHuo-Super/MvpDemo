package com.demo.mvpdemo.exception;


public class ServerException extends Exception {
    public ServerException(String message) {
        super(message);
    }

    public ServerException() {
        super("服务器地址异常");
    }
}
