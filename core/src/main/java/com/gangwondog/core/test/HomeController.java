//package com.gangwondog.core.test;
//
//import com.gangwondog.core.test.common.Common;
//import com.gangwondog.core.test.vo.LocalVo;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HomeController {
//
//  private static List<LocalVo> localList = new ArrayList<>();
//
//  public static List<LocalVo> getLocalList() {
//    return localList;
//  }
//
//  public static void setLocalList(List<LocalVo> localList) {
//    HomeController.localList = localList;
//  }
//
//  @GetMapping(path = "/")
//  public String start() {
//    return "그거보다 한시간보다 더 넘게 남았어요 밖에 비 엄청 와요 힘들어요 피곤해요 어쩌죠?";
//  }
//
//  @GetMapping(path = "/{location}")
//  public List<LocalVo> getLocation(@PathVariable String location) throws Exception {
//    Common common = new Common();
//    common.setLocation(location);
//    setLocalList(common.getLocation());
////        System.out.println("localList = " + localList.get(3));
////        System.out.println("localList = " + localList.get(1));
//    return localList;
//  }
//
//  @GetMapping(path = "/location/{pageIndex}/{pagePerCount}")
//  public List<LocalVo> aboutLocation(@PathVariable int pageIndex, @PathVariable int pagePerCount) {
//    System.out.println("localList ======= " + localList.get(0));
//    List<LocalVo> pageList = new ArrayList<>();
//    int start = pageIndex * 10;
//    if (pageIndex > 1 && start + pagePerCount < localList.size()) {
//      for (int i = start; i < start + pagePerCount; i++) {
//        pageList.add(localList.get(i));
//      }
//      return pageList;
//    } else if (pageIndex == 1 && pagePerCount < localList.size()) {
//      for (int i = 0; i < pagePerCount; i++) {
//        pageList.add(localList.get(i));
//      }
//      return pageList;
//    }
//    return null;
//  }
//
//  @PostMapping(path = "/location/insert")
//  public String insertLocation(@RequestBody LocalVo localVo) {
//    int seq = localVo.getContentSeq();
//    System.out.println("seq = " + seq);
//    LocalVo insertsVo = localList.stream()
//        .filter(localVo1 -> localVo1.getContentSeq() == seq)
//        .findAny()
//        .orElse(null);
//    if (insertsVo != null) {
//      return "overlap Data";
//    } else {
//      localList.add(0, localVo);
//      return "success";
//    }
//  }
//
//  @PutMapping(path = "/location/update/{seq}")
//  public String updateLocation(@RequestBody LocalVo localVo, @PathVariable int seq) {
//    LocalVo updateVo = localList.stream()
//        .filter(localVo1 -> localVo1.getContentSeq() == seq)
//        .findAny()
//        .orElse(null);
//    if (updateVo != null) {
//      updateVo.setContentSeq(localVo.getContentSeq());
//      updateVo.setAreaName(localVo.getAreaName());
//      updateVo.setPartName(localVo.getPartName());
//      updateVo.setTitle(localVo.getTitle());
//      updateVo.setAddress(localVo.getAddress());
//      updateVo.setLatitude(localVo.getLatitude());
//      updateVo.setLongitude(localVo.getLongitude());
//      updateVo.setTel(localVo.getTel());
//      return "success";
//    }
//    return "NoData";
//  }
//
//  @DeleteMapping(path = "/location/delete/{seq}")
//  public String deleteLocation(@PathVariable int seq) {
//    LocalVo deleteVo = localList.stream()
//        .filter(localVo -> localVo.getContentSeq() == seq)
//        .findAny()
//        .orElse(null);
//    if (deleteVo != null) {
//      localList.remove(deleteVo);
//      return "success";
//    }
//    return "Not Valid";
//  }
//
//  @GetMapping(path = "/location/test")
//  public ResponseEntity<List<LocalVo>> test11() {
//    return new ResponseEntity<List<LocalVo>>(HttpStatus.OK);
//  }
//}
////        StringBuilder urlBuilder = new StringBuilder("https://www.pettravel.kr/api/listArea.do"); /*URL*/
////        urlBuilder.append("?" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(pageIndex, "UTF-8")); /*페이지번호*/
////        urlBuilder.append("&" + URLEncoder.encode("pageBlock","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
////        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("AC01", "UTF-8")); /*지역 코드*/
////        URL url = new URL(urlBuilder.toString());
////        System.out.println("url = " + url);
////        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////        conn.setRequestMethod("GET");
////        conn.setRequestProperty("Content-type", "application/json");
////        conn.setRequestProperty("Accept", "application/json");
////        conn.setDoOutput(true);
////        System.out.println(conn.getHeaderFields());
////        System.out.println("Response code: " + conn.getResponseCode());
////        BufferedReader rd;
////        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
////            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
////        } else {
////            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
////        }
////        StringBuilder sb = new StringBuilder();
////        String line;
////        while ((line = rd.readLine()) != null) {
////            sb.append(line);
////        }
////        rd.close();
////        conn.disconnect();
////        return sb.toString();
//
//
///*    public static void main(String[] args) throws ParseException , IOException{
//            Common common = new Common();
//            String ex = common.bringLocation();
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) jsonParser.parse(ex);
//            JSONArray resultList = (JSONArray) jsonObject.get("resultList");
//            for (int i =0; i<resultList.size(); i++){
//                JSONObject resultListObject = (JSONObject) resultList.get(i);
//                LocalVo localVo = new LocalVo();
//                localVo.setContentSeq((Integer) resultListObject.get("contentSeq").toString());
//                localVo.setAreaName((String) resultListObject.get("areaName"));
//                localVo.setPartName((String) resultListObject.get("partName"));
//                localVo.setTitle((String) resultListObject.get("title"));
//                localVo.setAddress((String) resultListObject.get("address"));
//                localVo.setLatitude((String) resultListObject.get("latitude"));
//                localVo.setLongitude((String) resultListObject.get("longitude"));
//                localVo.setTel((String) resultListObject.get("tel"));
//                localList.add(localVo);
//            }
//    }*/
//
////    public static void main(String[] args) throws IOException{
////        StringBuilder urlBuilder = new StringBuilder("https://www.pettravel.kr/api/listArea.do"); /*URL*/
////        urlBuilder.append("?" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
////        urlBuilder.append("&" + URLEncoder.encode("pageBlock","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
////        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("AC01", "UTF-8")); /*지역 코드*/
////        URL url = new URL(urlBuilder.toString());
////        System.out.println("url = " + url);
////        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////        conn.setRequestMethod("GET");
////        conn.setRequestProperty("Content-type", "application/json");
////        conn.setRequestProperty("Accept", "application/json");
////        conn.setDoOutput(true);
////        System.out.println(conn.getHeaderFields());
////        System.out.println("Response code: " + conn.getResponseCode());
////        BufferedReader rd;
////        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
////            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
////        } else {
////            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
////        }
////        StringBuilder sb = new StringBuilder();
////        String line;
////        while ((line = rd.readLine()) != null) {
////            sb.append(line);
////        }
////        rd.close();
////        conn.disconnect();
////        System.out.println(sb.toString());
////    }
//
//
