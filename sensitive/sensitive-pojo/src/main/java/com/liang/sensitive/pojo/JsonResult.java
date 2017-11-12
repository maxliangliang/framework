package com.liang.sensitive.pojo;


/**
 * restful返回的数据类型，
 * Created by liang on 2017/4/23.
 */
public class JsonResult {

    //返回的状态码
    private int status;

    //返回的记录总数
    private int resultCount;

    //返回的结果
    private Object data;

    //返回的消息
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", resultCount=" + resultCount +
                ", data=" + data +
                '}';
    }
}
