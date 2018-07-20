package com.liuzhen.wxgzh.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoiceOutMessage extends OutMessage {
    // 语音格式，如amr，speex等
    private String Format;
    // 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
    @XmlElementWrapper(name="Voice")
    private String[] MediaId;

    public String getFormat() {
        return Format;
    }

    public void setFormat(String picUrl) {
        Format = picUrl;
    }

    public String[] getMediaId() {
        return MediaId;
    }

    public void setMediaId(String[] mediaId) {
        MediaId = mediaId;
    }
}
