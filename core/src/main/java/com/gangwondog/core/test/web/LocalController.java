package com.gangwondog.core.test.web;

import com.gangwondog.core.test.common.Common;
import com.gangwondog.core.test.repository.LocalDetailRepository;
import com.gangwondog.core.test.repository.LocalRepository;
import com.gangwondog.core.test.vo.LocalDetailEntity;
import com.gangwondog.core.test.vo.LocalEntity;
import com.gangwondog.core.test.vo.MemberEntity;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocalController {

  private final LocalRepository localRepository;

  private final LocalDetailRepository localDetailRepository;

  @PostMapping(path = "/insertLocal/{localCode}")
  public LocalEntity insertLocal(@PathVariable String localCode)throws Exception{
    Common common = new Common();
    common.setLocation(localCode);
    List<LocalEntity> list = common.getLocation();
    for(var t:list){
      final LocalEntity localEntity = t;
          LocalEntity.builder().build();
          localRepository.save(localEntity);

    }
    return null;
  }

  @PostMapping("/insertLocalDetail")
  public int insertLocalDetail()throws Exception{
    List<LocalEntity> getSeq = localRepository.findAll();
    String test1 = "AC";
    for (int j= 1; j<19; j++){
        String localCode;
        if((test1+j).length() == 3){
          localCode = test1 + 0 + j;
        }else {
          localCode = test1 + j;
        }
        for (int i=0; i<getSeq.size();i++) {
          Long test = getSeq.get(i).getContentSeq(); //전체사용자 리스트 시퀀스 가져와서 넣음
          Common common = new Common();
          common.setContentSeq(test);
          common.setLocation(localCode);
          LocalDetailEntity localDetail = common.localDetail();
          if (localDetail != null) {
            final LocalDetailEntity localDetail1 = localDetail;
            LocalDetailEntity.builder().build();
            localDetailRepository.save(localDetail1);
          }
        }
      }
    return getSeq.size();
  }

  @GetMapping("/getLocal")
  public List<LocalEntity> localEntities(){
    return localRepository.findAll();
  }

  @GetMapping("/getLocalDetail")
  public List<LocalDetailEntity> localDetailRepositories(){
    return localDetailRepository.findAll();
  }



}
