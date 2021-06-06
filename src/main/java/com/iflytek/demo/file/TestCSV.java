package com.iflytek.demo.file;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 19:03 2019/5/8
 */
public class TestCSV {
    public static void main(String[] args) {
        try {
            exportCsv();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
        /**
         * CSV导出   乱码未成功
         *
         * @throws Exception
         */
        public static void exportCsv() throws IOException {
            String srcCSV = "F:\\iflytek\\semcmd\\ceshi\\实体词条.csv";
            String targetFile = "F:/test.csv";
            CsvReader reader = new CsvReader(srcCSV, ',', Charset.forName("UTF-8"));
            CsvWriter write =new CsvWriter(targetFile,',',Charset.forName("UTF-8"));
//各字段以引号标记
            write.setForceQualifier(true);
//路过表头
//r.readHeaders();
//逐条读取记录，直至读完
            String[] header = {};
            while (reader.readRecord()) {
//把头保存起来
                if (reader.getCurrentRecord()==0){
                    header = reader.getValues();
                }
//获取当前记录位置
                System.out.print(reader.getCurrentRecord() + ".");
//读取一条记录
                System.out.println(reader.getRawRecord());
                String[] tmp = {reader.getValues()[0],reader.getValues()[1]};
//修改记录，并只写入第一个字段和第二字段
                if (!header[1].equals(tmp[1]) && ("".equals(tmp[1])||tmp==null)){
                    tmp[1]="空";
                    write.writeRecord(tmp);
                }else{
                    write.writeRecord(new String[]{reader.getValues()[0],reader.getValues()[1]});
                }
            }
            reader.close();
            write.close();
        }
}
