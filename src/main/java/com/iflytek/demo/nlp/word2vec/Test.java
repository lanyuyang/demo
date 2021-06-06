//package com.iflytek.demo.nlp.word2vec;
//
//import com.iflytek.demo.dto.Term;
//
//import java.io.*;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @ Author     ：yylan
// * @ Date       ：Created in 16:41 2019/4/11
// */
//public class Test {
//    public static final String TAG_START_CONTENT = "<content>";
//    public static final String TAG_END_CONTENT = "</content>";
//
//    public static void main(String[] args) {
//        String temp = null ;
//
//        BufferedReader reader = null;
//        PrintWriter pw = null;
//        System.out.println("开始分词...");
//        try {
//            //分词之前的文件路径
//            File file = new File("C:/users/xxx/Desktop/xxx");
//            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
//            reader = new BufferedReader(read);
//            //分词之后的文件路径
//            pw = new PrintWriter("C:/users/xxx/Desktop/xxx");
//            long start = System.currentTimeMillis()  ;
//            int allCount =0 ;
//            int termcnt = 0;
//            Set<String> set = new HashSet<String>();
//            while((temp=reader.readLine())!=null){
//                temp = temp.trim();
//                if (temp.startsWith(TAG_START_CONTENT)) {
//                    int end = temp.indexOf(TAG_END_CONTENT);
//                    String content = temp.substring(TAG_START_CONTENT.length(), end);
//                    if (content.length() > 0) {
//                        allCount += content.length() ;
//                        List<Term> result = ToAnalysis.parse(content);
//                        for (Term term: result) {
//                            String item = term.getName().trim();
//                            if (item.length() > 0) {
//                                termcnt++;
//                                pw.print(item.trim() + " ");
//                                set.add(item);
//                            }
//                        }
//                        pw.println();
//                    }
//                }
//            }
//            long end = System.currentTimeMillis() ;
//            System.out.println("已完成！");
//            System.out.println("共" + termcnt + "个term，" + set.size() + "个不同的词，共 "
//                    +allCount+" 个字符，每秒处理了:"+(allCount*1000.0/(end-start)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != reader) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (null != pw) {
//                pw.close();
//            }
//        }
//    }
//}