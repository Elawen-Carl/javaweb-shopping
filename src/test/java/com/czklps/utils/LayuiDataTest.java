package com.czklps.utils;


import org.junit.jupiter.api.Test;

public class LayuiDataTest {

    @Test
    public void putField() {
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(0);
        layuiData.setMsg("删除成功");
        layuiData.setCode(0);
        System.out.println(layuiData.getData());
    }
}