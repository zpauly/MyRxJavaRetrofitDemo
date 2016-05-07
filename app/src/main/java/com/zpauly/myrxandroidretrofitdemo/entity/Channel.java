package com.zpauly.myrxandroidretrofitdemo.entity;

import java.util.List;

/**
 * Created by root on 16-5-5.
 */
public class Channel {
    /**
     * error_code : 0
     * reason : Success!
     * result : [{"channelName":"CCTV-1 综合","pId":1,"rel":"cctv1","url":"url"},"..."]
     */

    private int error_code;
    private String reason;
    /**
     * channelName : CCTV-1 综合
     * pId : 1
     * rel : cctv1
     * url : url
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String channelName;
        private int pId;
        private String rel;
        private String url;

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
