package com.zpauly.myrxandroidretrofitdemo.entity;

import java.util.List;

/**
 * Created by root on 16-5-5.
 */
public class Category {
    /**
     * error_code : 0
     * reason : 请求成功！
     * result : [{"id":1,"name":"央视"},{"id":2,"name":"卫视"},{"id":3,"name":"数字"}]
     */

    private int error_code;
    private String reason;
    /**
     * id : 1
     * name : 央视
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
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
