package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/27-18:46
 */
public class TestExcel {
    static String fileUrl = "C:\\Users\\a\\Desktop\\class list\\student\\write.xlsx";
    @Test
    public void write(){
        File file = new File(fileUrl);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EasyExcel.write(file,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    private  List<DemoData> getData(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("heqing"+i);
            list.add(demoData);
        }
        return list;
    }
    @Test
    public void read(){
        EasyExcel.read(fileUrl,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
