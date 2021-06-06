package com.iflytek.demo.string;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.iflytek.demo.dto.ResultTaskDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 10:45 2019/4/3
 */
public class Json {
    private static Gson gson = new GsonBuilder().serializeNulls().create();
    public static void main(String[] args) {
        String json = "[{\"result\":{\"carport\":true},\"polygon\":{\"y1\":353,\"x1\":886,\"y2\":219,\"x2\":928,\"y3\":222,\"x3\":803,\"y4\":359,\"x4\":756},\"name\":\"carport1\"},{\"result\":{\"carport\":true},\"polygon\":{\"y1\":345,\"x1\":1314,\"y2\":223,\"x2\":1284,\"y3\":230,\"x3\":1150,\"y4\":360,\"x4\":1189},\"name\":\"carport2\"}]";
        // 第一种方法  利用对象
//        String str = resultJsonDeal(json);
//        System.out.println(str);

        strToJson1();

        // 第二种方法 利用Map
//        String str = resultJsonDealNew(json);
//        System.out.println(str);
    }

    public static void strToJson1(){
       String str = "as"+"，"+"sd"+"，"+"gfg";

       System.out.println(str);
    }

    private static String resultJsonDeal(String resultJson){
        List<ResultTaskDto> regionList = gson.fromJson(resultJson, new TypeToken<List<ResultTaskDto>>(){}.getType());

        String keyName = "";
        List<ResultTaskDto> resultTaskDtoList = new ArrayList<ResultTaskDto>();
        for (ResultTaskDto resultTaskDto : regionList){
            ResultTaskDto resultTaskDto1 = resultTaskDto;


            JSONObject jsonResult = JSONObject.fromObject(resultTaskDto);
            Map<String, Object> resultMap = jsonResult;
            for (String key : resultMap.keySet()) {
                String tempStr = resultMap.get(key).toString();
                if (key.equals("result")){
                    Map<String, String> tempMap = jsonToMap(tempStr);
                    String[] keylist = (String[]) tempMap.keySet().toArray(new String[0]);
                    keyName = keylist[0];
                    System.out.println(keylist[0]);
                }
            }
            resultTaskDto1.setKey(keyName);
            resultTaskDtoList.add(resultTaskDto1);
        }

        System.out.println(resultTaskDtoList.size());
        System.out.println(resultTaskDtoList.size());
        JSONArray json  =  JSONArray.fromObject(resultTaskDtoList);
        return json.toString();
    }
    public static Map<String, String> jsonToMap(Object jsonObj) {
        JSONObject jsonObject = JSONObject.fromObject(jsonObj);
        Map<String, String> map = (Map)jsonObject;
        return map;
    }

    /**
     * 第二种方法  通过map解析 不使用对象
     * @param resultJson
     * @return
     */
    private static String resultJsonDealNew(String resultJson){
        String keyName = "";
        JSONArray myJsonArray = JSONArray.fromObject(resultJson);
       // System.out.println(myJsonArray);
        List<Map<String,String>> mapListJson = (List)myJsonArray;
        List<Map<String,String>> mapListJsonNew = new ArrayList<Map<String, String>>();
        //System.out.println(mapListJson);
        for (Map map : mapListJson){
            //System.out.println(map.keySet());
            //System.out.println(map.entrySet());
            Map<String,String> mapNew = map;
            Set set = map.entrySet();
            for(Iterator iter = set.iterator(); iter.hasNext();){
                Map.Entry entry = (Map.Entry)iter.next();
                String key = (String)entry.getKey();
                if (key.equals("result")){
                    String tempStr = entry.getValue().toString();
                    Map<String, String> tempMap = jsonToMap(tempStr);
                    String[] keylist = (String[]) tempMap.keySet().toArray(new String[0]);
                    keyName = keylist[0];
                    System.out.println(keyName);
                }
            }
            mapNew.put("key",keyName);
            mapListJsonNew.add(mapNew);
        }
        JSONArray jsonArray2 = JSONArray.fromObject( mapListJsonNew );
        return jsonArray2.toString();
    }


    public void jsonToList(String json){
        String strJson = "{\"ids\":[\"sa\",\"ds\"]}";
        //String strJson = "{\"ids\":\"sa\"}";
        JSONObject jsonObject = JSONObject.fromObject(strJson);
        Map<Object, List<String>> map = (Map)jsonObject;
        List<String>  sid = (List)map.get("ids");
        for (String id : sid){
            System.out.println(id);
        }
    }
    public void jsonToStr(String json){
        //String strJson = "{\"ids\":[\"sa\",\"ds\"]}";
        String strJson = "{\"ids\":\"sa\"}";
        JSONObject jsonObject = JSONObject.fromObject(strJson);
        Map<Object, String> map = (Map)jsonObject;
        String  sid = map.get("ids");

            System.out.println(sid);

    }
}
