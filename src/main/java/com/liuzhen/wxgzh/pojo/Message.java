package com.liuzhen.wxgzh.pojo;

import java.io.Serializable;

public class Message  implements Serializable {
    private String mainKey;
    private String message;

    public String getMainKey() {
        return mainKey;
    }

    public void setMainKey(String mainKey) {
        this.mainKey = mainKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mainKey='" + mainKey + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
