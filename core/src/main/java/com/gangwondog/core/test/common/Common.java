package com.gangwondog.core.test.common;

import com.gangwondog.core.test.Entity.PlaceEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Common {

  private String location;

  private Long contentSeq;

  public void setContentSeq(Long contentSeq) {
    this.contentSeq = contentSeq;
  }

  public Long getContentSeq() {
    return contentSeq;
  }
//public static void  bringLocation()

//  public static void main(String[] args)throws Exception {
//    StringBuilder urlBuilder = new StringBuilder(
//        "https://www.pettravel.kr/api/listArea.do"); /*URL*/
//    urlBuilder.append(
//        "?" + URLEncoder.encode("page", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1",
//            StandardCharsets.UTF_8)); /*페이지번호*/
//    urlBuilder.append("&" + URLEncoder.encode("pageBlock", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("100",
//        StandardCharsets.UTF_8)); /*한 페이지 결과 수*/
//    urlBuilder.append(
//        "&" + URLEncoder.encode("areaCode", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("AC01",
//            StandardCharsets.UTF_8)); /*지역 코드*/
//    URL url = new URL(urlBuilder.toString());
//    System.out.println("url = " + url);
//    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//    conn.setRequestMethod("GET");
//    conn.setRequestProperty("Content-type", "application/json");
//    conn.setRequestProperty("Accept", "application/json");
//    conn.setDoOutput(true);
//    System.out.println(conn.getHeaderFields());
//    System.out.println("Response code: " + conn.getResponseCode());
//    BufferedReader rd;
//    if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//      rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
//    } else {
//      rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
//    }
//    StringBuilder sb = new StringBuilder();
//    //String sb = "";
//    String line;
//    while ((line = rd.readLine()) != null) {
//      sb.append(line);
//    }
//    sb.deleteCharAt(0);
//    sb.deleteCharAt(sb.length() - 1);
//    rd.close();
//    conn.disconnect();
//    System.out.println("api 값 : " + sb);
//    List<LocalEntity> localList = new ArrayList<>();
//    JSONParser jsonParser = new JSONParser();
//    JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
//    JSONArray resultList = (JSONArray) jsonObject.get("resultList");
//    Iterator iterator = resultList.iterator();
//    while (iterator.hasNext()) {
//      var next = iterator.next();
//      JSONObject jsonObject1 = (JSONObject) next;
//      //LocalVo localVo = new LocalVo();
//      LocalEntity localVo = new LocalEntity();
//      localVo.setContentSeq(Long.parseLong(jsonObject1.get("contentSeq").toString()));
//      localVo.setAreaName((String) jsonObject1.get("areaName"));
//      localVo.setPartName((String) jsonObject1.get("partName"));
//      localVo.setTitle((String) jsonObject1.get("title"));
//      localVo.setAddress((String) jsonObject1.get("address"));
//      localVo.setLatitude((String) jsonObject1.get("latitude"));
//      localVo.setLongitude((String) jsonObject1.get("longitude"));
//      localVo.setTel((String) jsonObject1.get("tel"));
//      localList.add(localVo);
//    }
//  }
  public List<Long> getLocation() throws IOException, ParseException {
    StringBuilder urlBuilder = new StringBuilder(
        "https://www.pettravel.kr/api/listArea.do"); /*URL*/
    urlBuilder.append(
        "?" + URLEncoder.encode("page", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1",
            StandardCharsets.UTF_8)); /*페이지번호*/
    urlBuilder.append("&" + URLEncoder.encode("pageBlock", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("100",
        StandardCharsets.UTF_8)); /*한 페이지 결과 수*/
    urlBuilder.append(
        "&" + URLEncoder.encode("areaCode", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(location,
            StandardCharsets.UTF_8)); /*지역 코드*/
    URL url = new URL(urlBuilder.toString());
    //System.out.println("url = " + url);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    conn.setRequestProperty("Accept", "application/json");
    conn.setDoOutput(true);
    //System.out.println(conn.getHeaderFields());
    //System.out.println("Response code: " + conn.getResponseCode());
    BufferedReader rd;
    if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
      rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
    } else {
      rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
    }
    StringBuilder sb = new StringBuilder();
    //String sb = "";
    String line;
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }
    sb.deleteCharAt(0);
    sb.deleteCharAt(sb.length() - 1);
    rd.close();
    conn.disconnect();
    //System.out.println("api 값 : " + sb);
    List<Long> localList = new ArrayList<>();
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
    JSONArray resultList = (JSONArray) jsonObject.get("resultList");
    Iterator iterator = resultList.iterator();
    while (iterator.hasNext()) {
      var next = iterator.next();
      JSONObject jsonObject1 = (JSONObject) next;
      //LocalVo localVo = new LocalVo();
      //LocalEntity localVo = new LocalEntity();
      localList.add(Long.parseLong(jsonObject1.get("contentSeq").toString()));
      /*localVo.setAreaName((String) jsonObject1.get("areaName"));
      localVo.setPartName((String) jsonObject1.get("partName"));
      localVo.setTitle((String) jsonObject1.get("title"));
      localVo.setAddress((String) jsonObject1.get("address"));
      localVo.setLatitude((String) jsonObject1.get("latitude"));
      localVo.setLongitude((String) jsonObject1.get("longitude"));
      localVo.setTel((String) jsonObject1.get("tel"));*/
      //localList.add(localVo);
    }
    return localList;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public JSONObject localDetail() throws IOException, ParseException{
    StringBuilder urlBuilder = new StringBuilder("https://www.pettravel.kr/api/detailSeqArea.do"); /*URL*/
    urlBuilder.append("?" + URLEncoder.encode("areaCode", StandardCharsets.UTF_8) + "="+ URLEncoder.encode(location, StandardCharsets.UTF_8));/*지역 코드*/
    urlBuilder.append("&" + URLEncoder.encode("contentNum", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(
        String.valueOf(contentSeq),
        StandardCharsets.UTF_8)); /*콘텐츠 번호*/
    URL url = new URL(urlBuilder.toString());
    System.out.println("url = " + url);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    System.out.println("Response code: " + conn.getResponseCode());
    BufferedReader rd;
    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
      rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
    } else {
      rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    }
    System.out.println("rd = " + rd);
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }
    sb.deleteCharAt(0);
    sb.deleteCharAt(sb.length() - 1);
    rd.close();
    conn.disconnect();
    //System.out.println("api 값 =" +sb);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
    //System.out.println("jsonObject = " + jsonObject);
    JSONObject testt = (JSONObject) jsonObject.get("resultList");
    //System.out.println("testt@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ = " + testt);
    
    if(testt != null) {
      return testt;
      //LocalDetailEntity localDetail1 = new LocalDetailEntity();
//      PlaceEntity localDetail1 = new PlaceEntity();
//      //System.out.println("testt = " + testt.get("contentSeq"));
//      //localDetail1.setContentSeq(Long.parseLong(jsonObject1.get("contentSeq").toString()));
/*      localDetail1.setContentSeq(Long.parseLong(String.valueOf(testt.get("contentSeq"))));
      localDetail1.setKeyword((String) testt.get("keyword"));
      localDetail1.setUsedTime((String) testt.get("usedTime"));
      localDetail1.setHomePage((String) testt.get("homePage"));
      localDetail1.setContent((String) testt.get("content"));
      localDetail1.setProvisionSupply((String) testt.get("provisionSupply"));
      localDetail1.setPetFacility((String) testt.get("petFacility"));
      localDetail1.setRestaurant((String) testt.get("restaurant"));
      localDetail1.setParkingLog((String) testt.get("parkingLog"));
      localDetail1.setMainFacility((String) testt.get("mainFacility"));
      localDetail1.setUsedCost((String) testt.get("usedCost"));
      localDetail1.setPolicyCautions((String) testt.get("policyCautions"));
      localDetail1.setEmergencyResponse((String) testt.get("emergencyResponse"));
      localDetail1.setMemo((String) testt.get("memo"));
      localDetail1.setBathFlag((String) testt.get("bathFlag"));
      localDetail1.setProvisionFlag((String) testt.get("provisionFlag"));
      localDetail1.setPetFlag((String) testt.get("petFlag"));
      localDetail1.setPetWeight(Integer.parseInt(String.valueOf(testt.get("petWeight"))));
      localDetail1.setDogBreed((String) testt.get("dogBreed"));
      localDetail1.setEmergencyFlag((String) testt.get("emergencyFlag"));
      localDetail1.setEntranceFlag((String) testt.get("entranceFlag"));
      localDetail1.setParkingFlag((String) testt.get("parkingFlag"));
      localDetail1.setInOutFlag((String) testt.get("inOutFlag"));
      localDetail1.setMessage((String) testt.get("message"));*/
//      System.out.println("localDetail1 = " + localDetail1);
//      return localDetail1;
    }
    return null;
  }
}
