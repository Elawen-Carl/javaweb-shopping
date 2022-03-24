package com.czklps.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LayuiData {
    private JSONArray obj = new JSONArray();
    private JSONObject data = new JSONObject();
    private Integer code = 0;
    private String msg = "";
    private Integer count = 0;


    public void setField(JSONObject field){
        count = field.size();
        obj.add(field);
    }

    public JSONObject getData() {
        data.put("data",obj);
        data.put("code",code);
        data.put("msg",msg);
        data.put("count",count);
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
