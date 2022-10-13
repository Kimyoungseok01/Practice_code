package com.gangwondog.core.test.common;

import com.gangwondog.core.test.vo.LocalDetailEntity;
import com.gangwondog.core.test.vo.LocalEntity;
import com.gangwondog.core.test.vo.LocalVo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class CommonTest {


  @Test
  @DisplayName("데이터 가져오기")
  void getApi() throws Exception {
    Common common = new Common();
    common.setLocation("AC01");
//    List<LocalVo> test = common.getLocation();

//        Assertions.assertThat(rs.size()).isSameAs(62);
  }

  @Test
  @DisplayName("데이터 넣기")
  void setAip() throws Exception {
    Common common = new Common();
    common.setLocation("AC01");
    var test = common.getLocation();
    Iterator iterator = test.iterator();
    System.out.println("test.size() = " + test.size());
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      LocalVo vo = (LocalVo) iterator.next();
      System.out.println("vo.getContentSeq() = " + vo.getContentSeq());
      System.out.println("vo.getTitle() = " + vo.getTitle());
    }
    Map<String, Object> map = new HashMap<>();
    for (var o : test) {
      map.put("contentSeq", o.getContentSeq());
      map.put("areaName", o.getAreaName());
      map.put("title", o.getTitle());
      System.out.println("map = " + map);
    }
  }

  @Test
  @DisplayName("stream test")
  void testStream() {
   String test = "AC";
    for (int i=1; i<=18; i++){
      String s;
      if((test + i).length() == 3){
        s = test + 0 + i;
      }else {
        s = test + i;
      }
        System.out.println("test = " + s);
    }
  }

  @Test
  @DisplayName("File")
  void fileTest(){
    File file = new File("c:\\fileTest\\file.txt");
    try {
      if(!file.exists()){
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file);
      BufferedWriter writer = new BufferedWriter(fw);

      writer.write("test");
    }catch (IOException o){
      o.printStackTrace();
    }
  }
}


