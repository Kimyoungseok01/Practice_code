package com.gangwondog.core.test.common;

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
  void fileTestT(){
    String image = "http://www.pettravel.kr/upload/mapdata/C0070/thumb/list/C0070_F20210828164621010.jpg";
    String fileName = image.substring(image.lastIndexOf("list/")+5);
    System.out.println("fileName = " + fileName);
    int num = image.indexOf("/list/");
    System.out.println("num = " + num);
    String url = image.substring(0,num+6);
    System.out.println("url = " + url);
  }
}


