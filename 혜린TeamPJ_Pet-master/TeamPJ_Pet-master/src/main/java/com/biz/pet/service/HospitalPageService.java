package com.biz.pet.service;

import org.springframework.stereotype.Service;

import com.biz.pet.domain.HospitalPageDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HospitalPageService {
   
   /*
    * pagination을 시작할 떄
    * 한페이지에 보여질 기본 값ㅇ르 설정
    */
   private int listPerPage = 20;
   private int pageCount = 5;
   
   /*
    * pagination의 기본값을 변경하고자 할때
    * 사용하는 setter method
    */
   public void setListPerPage(int listPerPage) {
      this.listPerPage = listPerPage;
   }
   
   public void setPageCount(int pageCount) {
      this.pageCount = pageCount;
   }
   
   public HospitalPageDTO getPagination(long totalCount, int currentPageNo) {
      
      if(totalCount < 1) {
         return null;
      }
      
      int finalPageNo = ((int)totalCount + (listPerPage - 1)) / listPerPage;
      
      if(currentPageNo > finalPageNo) currentPageNo = finalPageNo;
      if(currentPageNo < 1) currentPageNo = 1;
      
//      boolean isNowFirst = currentPageNo == 1;
//      boolean isNowFinal = currentPageNo == finalPageNo;
      
      int startPageNo = ((currentPageNo - 1) / pageCount) * pageCount + 1;
      
      int endPageNo = startPageNo + pageCount -1;
      if(endPageNo > finalPageNo) endPageNo = finalPageNo;
      
      int prePageNo = 1;
      if(currentPageNo > 1) prePageNo = currentPageNo - 1;
      
      int nextPageNo = finalPageNo;
      if(currentPageNo < finalPageNo) nextPageNo = currentPageNo + 1; 
      
      // DB에서 데이터 가져오기 값 설정
      // 1, 2, 3, 페이지를 선택했을 때
      // 1, 11, 21로 설정
      int offset = (currentPageNo -1) * listPerPage + 1;
      
      // 10, 20, 30으로 설정하기
      int limit = offset + listPerPage - 1;
      
      HospitalPageDTO pageDTO = HospitalPageDTO.builder()
            .totalCount(totalCount)
            .pageCount(pageCount)
            
            .listPerPage(listPerPage)
            .offset(offset)
            .limit(limit)
            
            .firstPageNo(1)
            .finalPageNo(finalPageNo)
            
            .startPageNo(startPageNo)
            .endPageNo(endPageNo)
            
            .prePageNo(prePageNo)
            .nextPageNo(nextPageNo)
            
            .currentPageNo(currentPageNo)
            .build();
            
      return pageDTO;
      
   }

}