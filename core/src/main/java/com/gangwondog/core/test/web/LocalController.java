package com.gangwondog.core.test.web;

import com.gangwondog.core.test.Entity.CommonCodeEntity;
import com.gangwondog.core.test.Entity.PlaceEntity;
import com.gangwondog.core.test.common.Common;
import com.gangwondog.core.test.repository.LocalDetailRepository;
import com.gangwondog.core.test.repository.LocalRepository;
import com.gangwondog.core.test.repository.CommonCodeRepository;
import com.gangwondog.core.test.repository.PlaceRepository;
import com.gangwondog.core.test.vo.LocalDetailEntity;
import com.gangwondog.core.test.vo.LocalEntity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocalController {

  private final LocalRepository localRepository;

  private final LocalDetailRepository localDetailRepository;

  private final CommonCodeRepository commonCodeRepository;

  private final PlaceRepository placeRepository;
  @PostMapping(path = "/insertAll")
  public void insertAll()throws Exception{
    Common common  = new Common();
    List<Long> getSeq = new ArrayList<>();
    String str = "AC";
    for (int i = 1; i<19;i++){
      String local;
      if((str+i).length() == 3){
        local = str+0+i;
      }else{
        local = str + i;
      }
      common.setLocation(local);
      List<Long> list = common.getLocation(); // 지역별 시퀀스값
      for(int j=0; j<list.size(); j++) {
        Long seq = list.get(j);
        Common common1 = new Common();
        common1.setContentSeq(seq);
        common1.setLocation(local);
        JSONObject localDetail = common1.localDetail();
        if (localDetail != null) {
          Optional<CommonCodeEntity> areaCode = commonCodeRepository.findByComCodeDescription(
              (String) localDetail.get("areaName"));
          Optional<CommonCodeEntity> partCode = commonCodeRepository.findByComCodeDescription(
              (String) localDetail.get("partName"));
          PlaceEntity localDetail1 = new PlaceEntity();
          localDetail1.setContentSeq(Long.parseLong(String.valueOf(localDetail.get("contentSeq"))));
          localDetail1.setAreaCode(areaCode.get());
          localDetail1.setPartCode(partCode.get());
          localDetail1.setTitle((String) localDetail.get("title"));
          localDetail1.setAddress((String) localDetail.get("address"));
          localDetail1.setLatitude((String) localDetail.get("latitude"));
          localDetail1.setLongitude((String) localDetail.get("longitude"));
          localDetail1.setTel((String) localDetail.get("tel"));
          localDetail1.setKeyword((String) localDetail.get("keyword"));
          localDetail1.setUsedTime((String) localDetail.get("usedTime"));
          localDetail1.setHomePage((String) localDetail.get("homePage"));
          localDetail1.setContent((String) localDetail.get("content"));
          localDetail1.setProvisionSupply((String) localDetail.get("provisionSupply"));
          localDetail1.setPetFacility((String) localDetail.get("petFacility"));
          localDetail1.setRestaurant((String) localDetail.get("restaurant"));
          localDetail1.setParkingLog((String) localDetail.get("parkingLog"));
          localDetail1.setMainFacility((String) localDetail.get("mainFacility"));
          localDetail1.setUsedCost((String) localDetail.get("usedCost"));
          localDetail1.setPolicyCautions((String) localDetail.get("policyCautions"));
          localDetail1.setEmergencyResponse((String) localDetail.get("emergencyResponse"));
          localDetail1.setMemo((String) localDetail.get("memo"));
          localDetail1.setBathFlag((String) localDetail.get("bathFlag"));
          localDetail1.setProvisionFlag((String) localDetail.get("provisionFlag"));
          localDetail1.setPetFlag((String) localDetail.get("petFlag"));
          if(!localDetail.get("petWeight").equals("")){
          localDetail1.setPetWeight(Integer.parseInt(String.valueOf(localDetail.get("petWeight"))));
          }else {
            localDetail1.setPetWeight(null);
          }
          localDetail1.setDogBreed((String) localDetail.get("dogBreed"));
          localDetail1.setEmergencyFlag((String) localDetail.get("emergencyFlag"));
          localDetail1.setEntranceFlag((String) localDetail.get("entranceFlag"));
          localDetail1.setParkingFlag((String) localDetail.get("parkingFlag"));
          localDetail1.setInOutFlag((String) localDetail.get("inOutFlag"));
          localDetail1.setMessage((String) localDetail.get("message"));
          localDetail1.setReadCount(0);
          localDetail1.setOwnAt("N");
          Instant current = Instant.now();
          localDetail1.setCreateDate(current);

          placeRepository.save(localDetail1);
        }
      }

/*      for(var t:list){
*//*        final LocalEntity localEntity = t;
        LocalEntity.builder().build();
        localRepository.save(localEntity);*//*
        getSeq.add(t.getContentSeq());
      }*/
    }

    /*String test1 = "AC";
    for (int j= 1; j<19; j++){
        String localCode;
        if((test1+j).length() == 3){
          localCode = test1 + 0 + j;
        }else {
          localCode = test1 + j;
        }
          for (int i=0; i<getSeq.size();i++) {
            Long test = getSeq.get(i); //전체사용자 리스트 시퀀스 가져와서 넣음
            Common common1 = new Common();
            common1.setContentSeq(test);
            common1.setLocation(localCode);
            JSONObject localDetail = common1.localDetail();

            Optional<CommonCodeEntity> commonCodeEntity = placeRepository.findByComCodeDescription((String) localDetail.get("areaName"));
            System.out.println("commonCodeEntity.get().getComCode() = " + commonCodeEntity.get().getComCode());
            *//*
            if (localDetail != null) {
              //final LocalDetailEntity localDetail1 = localDetail;
              LocalDetailEntity.builder().build();
              localDetailRepository.save(localDetail1);
            }
*//*
        }
    }*/


  }

 /* @PostMapping(path = "/insertLocal")
  public LocalEntity insertLocal()throws Exception{
    Common common = new Common();
    String str = "AC";
    for (int i = 1; i<19;i++){
      String local;
      if((str+i).length() == 3){
        local = str+0+i;
      }else{
        local = str + i;
      }
      common.setLocation(local);
      List<LocalEntity> list = common.getLocation();
      for(var t:list){
        final LocalEntity localEntity = t;
            LocalEntity.builder().build();
            localRepository.save(localEntity);

      }
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
         *//* LocalDetailEntity localDetail = common.localDetail();
          if (localDetail != null) {
            final LocalDetailEntity localDetail1 = localDetail;
            LocalDetailEntity.builder().build();
            localDetailRepository.save(localDetail1);
          }*//*
        }
      }
    return getSeq.size();
  }*/

  @GetMapping("/getLocal")
  public List<LocalEntity> localEntities(){
    return localRepository.findAll();
  }

  @GetMapping("/getLocalDetail")
  public List<LocalDetailEntity> localDetailRepositories(){
    return localDetailRepository.findAll();
  }



}
