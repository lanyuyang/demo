package com.iflytek.demo.string;

import com.iflytek.demo.dto.SemAuxiliary;
import com.iflytek.demo.dto.SemAuxiliaryItem;
import com.iflytek.demo.dto.SemEsse;
import com.iflytek.demo.dto.SemEsseItem;
import net.sf.json.JSONArray;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 10:08 2019/5/10
 */
public class tiquguize {
    public static void main(String[] args) {
        String str = "我想要[<book,yuding,预定:<预订,定,订>>]({datetime,jintian,今天:{后天:{后天上午,后天中午};明天:{明天上午,明天中午}}})(<city,beijing,北京:<首都,京城,北平>>)的({house,jiudian,酒店:{酒店1:{香格里拉,香格里拉1}}})";
        String strBak =str;
        //saveAuxi(str);

//        text[]()
//        []({slotkey,datetime,时间:{时间1:{时间11,时间12};时间2:{时间21,时间22}}})

        // 1、解析出可选择的
        List<String> optionalList = extractMessageByRegular(str);
        for (String string : optionalList){
            System.out.println(string);
        }

        // 2、解析出必选的
        List<String> requiredList = extractMessageByRegular2(str);
        for (String string : requiredList){
            System.out.println(string);
        }

        List<String> corpusList = new ArrayList<String>();
        List<String> keywordList = new ArrayList<String>();
        // 3、合并可选必选列表 遍历，分析实体或辅助词
        corpusList.addAll(optionalList);
        corpusList.addAll(requiredList);

        str = str.replaceAll("\\{","").replaceAll("\\}","").replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\(","").replaceAll("\\)","");
        for (String string : corpusList){
            // 是辅助词
            if (string.contains("<")){
                String[] auxiArr = saveAuxi(string);
                keywordList.add(auxiArr[2]);
                // TODO 保存辅助词 槽位 数据库中
                str = str.replaceAll(string,"|");
            }else {
                // 是实体
                String[] esseArr = saveEsse(string);
                keywordList.add(esseArr[2]);
                // TODO 保存实体  槽位 数据库中
                string = string.replaceAll("\\{","").replaceAll("\\}","");
                str = str.replaceAll(string,"|");
            }
        }
        // 生成map开始 *****************************
        Map<Integer, String> keywordMap = new HashMap<Integer, String>();
        // 将实体或辅助词的 name加进去， 因为不会重复
        for (String string : keywordList){
            Matcher matcherKeyword = Pattern.compile(string).matcher(strBak);
            if (matcherKeyword.find()){
                keywordMap.put(matcherKeyword.start(), string);
            }
        }
        //keywordList.addAll(Arrays.asList(str.split("\\|")));
        // 处理基本词汇， 比如： 我想要、的
        List<String> baseList = Arrays.asList(str.split("\\|"));
        // 移除字符串中""
            List<String> signRemove = new ArrayList<String>();
            signRemove.add("");
            baseList = new ArrayList<String>(baseList); // 需要组装一下，不然removeAll报错
            baseList.removeAll(signRemove);
        for (String string : baseList){
            Matcher matcherKeyword = Pattern.compile(string).matcher(strBak);
            while (matcherKeyword.find()){
                keywordMap.put(matcherKeyword.start(),string);
            }
        }
        Object[] key = keywordMap.keySet().toArray();
        Arrays.sort(key);

        String text = "";
        for (int i=0; i< key.length; i++){
            text += keywordMap.get(key[i]);
        }
        // TODO text保存到语料数据库中
        System.out.println("文本为： "+ text);

    }

    //按值进行 升序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueAscending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static String[] saveAuxi(String str){
        String[] auxiArr = new String[3];
        SemAuxiliary semAuxiliary = new SemAuxiliary();
        List<SemAuxiliaryItem> auxiItemList = new ArrayList<SemAuxiliaryItem>();
        String auxiMsg = getAuxiString(str);
        System.out.println(auxiMsg);
        List<String> auxiBody = strSplit(auxiMsg,",");
        List<String> auxiTemp = strSplit(auxiBody.get(1),",");
        auxiBody.set(1,auxiTemp.get(0));
        auxiBody.add(2,auxiTemp.get(1));

        semAuxiliary.setAuxiKey(auxiBody.get(1));
        List<String> auxiBodyDown = strSplit(auxiBody.get(2),":");
        semAuxiliary.setAuxiName(auxiBodyDown.get(0));

        String auxiAliasMsg = getAuxiString(auxiMsg);
        List<String> auxiAliasList = Arrays.asList(auxiAliasMsg.split(","));
        for (int i=0; i< auxiAliasList.size(); i++){
            SemAuxiliaryItem semAuxiliaryItem = new SemAuxiliaryItem();
            semAuxiliaryItem.setAuxiItemName(auxiAliasList.get(i));
            auxiItemList.add(semAuxiliaryItem);
        }
        System.out.println(auxiAliasList.size());
        System.out.println(auxiAliasList.size());
        auxiArr[0] = auxiBody.get(0);
        auxiArr[1] = auxiTemp.get(0);
        auxiArr[2] = auxiBodyDown.get(0);
        return auxiArr;
    }

    public static String[] saveEsse(String str){
        String[] esseArr = new String[3];
        SemEsse semEsse = new SemEsse();
        List<SemEsseItem> esseItemList = new ArrayList<SemEsseItem>();
        String esseMsg = getEsseString(str);
        System.out.println(esseMsg);

        List<String> esseBody = strSplit(esseMsg,",");
        List<String> esseTemp = strSplit(esseBody.get(1),",");
        esseBody.set(1,esseTemp.get(0));
        esseBody.add(2,esseTemp.get(1));

        semEsse.setEsseKey(esseBody.get(1));
        List<String> esseBodyDown = strSplit(esseBody.get(2),":");
        semEsse.setEsseName(esseBodyDown.get(0));

        String esseAliasMsg = getEsseString(esseMsg);
        List<String> esseAliasList = Arrays.asList(esseAliasMsg.split(";"));
        for (int i=0; i< esseAliasList.size(); i++){
            SemEsseItem semEsseItem = new SemEsseItem();
            List<String> esseAlistBodyCell = strSplit(esseAliasList.get(i), ":");
            semEsseItem.setEsseItemName(esseAlistBodyCell.get(0));

            String esseAliasBodyDown = getEsseString(esseAlistBodyCell.get(1));
            List<String> esseAliasListJson = Arrays.asList(esseAliasBodyDown.split(","));
            JSONArray json = JSONArray.fromObject(esseAliasListJson);
            semEsseItem.setEsseItemAlias(json.toString());
            esseItemList.add(semEsseItem);
        }

        System.out.println(semEsse);
        System.out.println(esseItemList.size());
        esseArr[0] = esseBody.get(0);
        esseArr[1] = esseTemp.get(0);
        esseArr[2] = esseBodyDown.get(0);
        return esseArr;
    }
    /**
     * 使用正则表达式提取中括号中的内容
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular(String msg){

        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
    }

    /**
     * 使用正则表达式提取大括号中的内容
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular1(String msg){
        String regex="start\\d+:?\\s*\\{(.+?)\\}(?=(,start\\d+:?)|$)";
        List<String> list=new ArrayList<String>();
        // 提取大括号中内容
        //Pattern p = Pattern.compile("(?=\\{)(.+?)(?>\\})");
        // 提取最外层大括号内容
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
    }

    /**
     * 使用正则表达式提取小括号中的内容
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular2(String msg){

        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(?=\\()(.+?)(?>\\))");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
    }

    /**
     * 使用正则表达式提取尖括号中的内容
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular3(String msg){

        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(?=\\<(.+?)(?>\\>)");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
    }

    /**
     * 获取实体{} 内容字符串
     * @param msg
     * @return
     */
    public static String getEsseString (String msg){
        int begin = 0;
        int end = 0;
        for (int i=0; i< msg.length(); i++){
            if ("{".equals(String.valueOf(msg.charAt(i)))){
                begin = i;
                break;
            }
        }
        for (int i= msg.length()-1; i>0; i--){
            if ("}".equals(String.valueOf(msg.charAt(i)))){
                end = i;
                break;
            }
        }
        return msg.substring(begin+1,end);
    }

    /**
     * 字符串按指定字符 sign分割 分固定两段
     * @param msg
     * @param sign
     * @return
     */
    public static List<String> strSplit(String msg, String sign){
        List<String> result = new ArrayList<String>();
        for (int i=0; i< msg.length(); i++){
            if (sign.equals(String.valueOf(msg.charAt(i)))){
                result.add(msg.substring(0, i));
                result.add(msg.substring(i+1,msg.length()));
                break;
            }
        }
        return result;
    }

    /**
     * 获取辅助词<> 中内容字符串
     * @param msg
     * @return
     */
    public static String getAuxiString (String msg){
        int begin = 0;
        int end = 0;
        for (int i=0; i< msg.length(); i++){
            if ("<".equals(String.valueOf(msg.charAt(i)))){
                begin = i;
                break;
            }
        }
        for (int i= msg.length()-1; i>0; i--){
            if (">".equals(String.valueOf(msg.charAt(i)))){
                end = i;
                break;
            }
        }
        return msg.substring(begin+1,end);
    }

}
