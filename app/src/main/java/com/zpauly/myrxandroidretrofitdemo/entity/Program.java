package com.zpauly.myrxandroidretrofitdemo.entity;

import java.util.List;

/**
 * Created by root on 16-5-5.
 */
public class Program {
    /**
     * error_code : 0
     * reason : Success!
     * result : [{"cName":"CCTV-5 体育","pName":"午夜体育报道","pUrl":"url","time":"2015-02-27 00:00"}]
     */

    private int error_code;
    private String reason;
    /**
     * cName : CCTV-5 体育
     * pName : 午夜体育报道
     * pUrl : url
     * time : 2015-02-27 00:00
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
        private String cName;
        private String pName;
        private String pUrl;
        private String time;

        public String getCName() {
            return cName;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getPUrl() {
            return pUrl;
        }

        public void setPUrl(String pUrl) {
            this.pUrl = pUrl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
