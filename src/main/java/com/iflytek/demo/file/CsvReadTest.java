package com.iflytek.demo.file;

//import com.csvreader.CsvReader;
//import com.csvreader.CsvWriter;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.iflytek.demo.dto.SemEsseItem;
import com.iflytek.demo.dto.TestBean;
import com.opencsv.bean.CsvToBeanBuilder;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 18:45 2019/5/8
 */
public class CsvReadTest {
    //private static String tempPath = System.getProperty("java.io.tmpdir")+File.separator+"semcmd";
    private static String tempPath = "F:\\export"+File.separator+"semcmd";
    public static void main(String[] args)  {
//        List<SemEsseItem> semEsseItems = readeCsv();
//        for (SemEsseItem semEsseItem : semEsseItems){
//            System.out.println(semEsseItem.getEsseItemName());
//            System.out.println(semEsseItem.getEsseItemAlias());
//            System.out.println("**********");
//        }
        String path = tempPath + File.separator ;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        writeCsv();

    }
    // 未成功
    private static void test1() throws IllegalStateException, IOException{
        String path = "F:\\iflytek\\semcmd\\ceshi\\实体词条.csv";
        String charset = "utf-8";
        FileInputStream fileInputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(fileInputStream, charset);
        List<TestBean> beans = new CsvToBeanBuilder<TestBean>(reader)
                .withType(TestBean.class).build().parse();
        for (TestBean bean : beans){
            System.out.println(bean.getName());
        }
    }
    public static List<SemEsseItem> readeCsv() {
        List<SemEsseItem> esseItemList = new ArrayList<SemEsseItem>();
        try {

            ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
            //注明：或者可以通过前端上传的文件，用一个方法获取上传文件名uploadName(String)
            String csvFilePath = "F:\\iflytek\\semcmd\\ceshi\\实体词条.csv";
            FileInputStream fileInputStream = new FileInputStream(csvFilePath);
            CsvReader reader = new CsvReader(fileInputStream, ',', Charset.forName("GBK"));    //解决中文编码

            //reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
            System.out.println(reader.getColumnCount());
            while (reader.readRecord()) { //逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();
            for (int row = 0; row < csvList.size(); row++) {
                SemEsseItem semEsseItem = new SemEsseItem();
                String cell = csvList.get(row)[0]; //取得第row行第0列的数据，可以指定获取内容
                semEsseItem.setEsseItemName(cell);

                List<String> aliasList = new ArrayList<String>();
                String[] raw = csvList.get(row);
                for (int i=1; i< raw.length; i++){
                    aliasList.add(raw[i]);
                }
                // 移除空值
                for (int index =0; index < aliasList.size(); index++){
                    if ( "" .equals(aliasList.get(index))){
                        aliasList.remove(index);
                    }
                }
                JSONArray json = JSONArray.fromObject(aliasList);
                semEsseItem.setEsseItemAlias(json.toString());
                esseItemList.add(semEsseItem);
//              System.out.println("姓名：" + cell);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return esseItemList;
    }
    public void Test(){

    }

    public static void writeCsv(){
        try {
            String csvFilePath = tempPath +  File.separator +"abc.csv";
            CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("GBK"));

            String[] contents = {"aaaaa","bbbbb","北京","ddddddddd"};
            String[] contents1 = {"aaaaa","bbbbb","北京","ddddddddd"};
            wr.writeRecord(contents);
            wr.writeRecord(contents1);
            wr.close();


            Workbook wb = new XSSFWorkbook();
            List<SemEsseItem> semEsseItemList = new ArrayList<SemEsseItem>();

            //设置sheet名称，并创建新的sheet对象
            String sheetName = "实体词条";

            SemEsseItem semEsseItem = new SemEsseItem();
            semEsseItem.setEsseItemName("蚌埠");
            semEsseItem.setEsseItemAlias("珠城，蚌城");
            semEsseItemList.add(semEsseItem);

            SemEsseItem semEsseItem1 = new SemEsseItem();
            semEsseItem1.setEsseItemName("北京");
            semEsseItem1.setEsseItemAlias("首都，京城，北平");
            semEsseItemList.add(semEsseItem1);
            String saveParth = saveEsseXlsxFile(wb, semEsseItemList, sheetName);
            System.out.println("***********"+saveParth);

            ZipUtil zipUtil = new ZipUtil();
            zipUtil.zipFile(tempPath, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String saveEsseXlsxFile(Workbook wb, List<SemEsseItem> semEsseItemList, String sheetName){
        Sheet stuSheet = wb.createSheet(sheetName);
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i=0; i<semEsseItemList.size(); i++){
            SemEsseItem semEsseItem = semEsseItemList.get(i);
            row = stuSheet.createRow(i);
            row.createCell(0).setCellValue(semEsseItem.getEsseItemName());
            String[] esseItemAliasArr = semEsseItem.getEsseItemAlias().split("，");
            for (int j=0; j< esseItemAliasArr.length ; j++){
                row.createCell(j+1).setCellValue(esseItemAliasArr[j]);
            }
        }

        // 使用说明
        String sheetName1 = "使用说明";
        Sheet helpSheet = wb.createSheet(sheetName1);
        String helpContext = "使用说明：1.内容词条导入文件支持.csv，.xlsx格式；" +
                "\r\n          2.文件中的一行文字作为一条内容输入；" +
                "\r\n          3.文件的第一列文字为内容名称，第二列及其以后为该内容词条的同义词；";

        //创建上传文件目录
        File folder = new File(tempPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String savePath = "";
        try {
            //设置文件名
            String fileName =sheetName + ".xlsx";
            savePath = tempPath + File.separator + fileName;
            System.out.println("文件临时保存路径 : {}"+savePath);
            OutputStream fileOut = new FileOutputStream(savePath);
            wb.write(fileOut);
            fileOut.close();
            //返回文件保存全路径
        }catch (Exception e){
            e.printStackTrace();
        }

        return savePath;
    }
}
