package com.kyh.iipa.mvc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AppAop {
    
//    private final RootComService rootComService;
//    
//    private final ObjectMapper objectMapper;
//    
//    private final SysIndvdlinfoAcesLogService sysIndvdlinfoAcesLogService;
//    
//    @Around("execution(* root.cloud.com.mvc.controller.RootRestApiController.searchData(..))")
//    public Object main(ProceedingJoinPoint joinPoint) throws Throwable {
//        return processMethod(joinPoint);
//    }
//    
//    @Around("@annotation(root.cloud.com.envrn.annotation.auth.SearchMethod)")
//    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
//        
//        if(ComUtil.isNull(ThreadLocalRepository.get("method"))){
//            ThreadLocalRepository.set("method", joinPoint.getSignature().getName());
//        }
//        
//        log.debug("Around==========================================================Around");
//        return processMethod(joinPoint, "S");
//        
//    }
//    
//    private Object processMethod(ProceedingJoinPoint joinPoint) throws Throwable{
//        return this.processMethod(joinPoint,"E"); 
//    }
//  
//    private Object processMethod(ProceedingJoinPoint joinPoint, String methodType) throws Throwable {
//        
//        log.info(joinPoint.getSignature().getName() + "  --- 기록 시작 ---- "
//                +joinPoint.getSignature().getName() +" ~~~ "+ RootCloudUtil.getRequest().getRequestURI());
//        
//        StopWatch stopWatch = new StopWatch();
//        
//        try {
//            stopWatch.start();
//            // 핵심 로직 실행.
//            Object retValue = joinPoint.proceed();
//            
//            // 조회 시 개인정보접근로그 대상이면 client 로 보내는 empId 값 저장 2022.01.18
//            if("S".equals(methodType))  this.saveIndvdlinfoAcesLog(retValue);
//          
//            return retValue;
//        } catch (Throwable e) {
//            throw e;
//        } finally {
//            
//            // ==== 쪽지 및 TODO Setting ==== 
//            this.setMainMsgTodoCo();
//            
//            // ==== save 개인정보 접근로그 log ====
//            
//            List<SysIndvdlinfoAcesLog> sysIndvdlinfoAcesLogs = (List<SysIndvdlinfoAcesLog>) ThreadLocalRepository.get("RC_INDVDLINFO_MAP");
//            if(!ComUtil.isNull(sysIndvdlinfoAcesLogs)){
//                ThreadLocalRepository.removeKey("RC_INDVDLINFO_MAP");
//                sysIndvdlinfoAcesLogService.saveAll(sysIndvdlinfoAcesLogs);  // 2022.01.13 주석처리
//            }
//            
//            stopWatch.stop();
//            log.info(joinPoint.getSignature().getName() + " --- 메서드 실행 시간 : " + stopWatch.getTotalTimeMillis());
//            log.info(joinPoint.getSignature().getName() + " --- 기록 종료");
//            
//        }
//    }
//    
//    
//    // 개인정보 접근 로그 저장
//    @SuppressWarnings("unchecked")
//    private void saveIndvdlinfoAcesLog(Object retValue){
//        
//        List<SysIndvdlinfoAcesLog> indvdlLogs = (List<SysIndvdlinfoAcesLog>) ThreadLocalRepository.get("RC_INDVDLINFO_MAP");
//        if(!ComUtil.isNull(indvdlLogs)){
//            
//           String empIds= this.getSchTrgEmpIdsJoining( retValue);
//            
//            // 검색대상 EMP ID 저장
//            indvdlLogs.stream()
//            .filter(f-> f.getOperTy().equals("R"))
//            .forEach(entity->{
//                entity.setSchTrg(empIds);
//            })
//            ;
//            
//            ThreadLocalRepository.set("RC_INDVDLINFO_MAP",indvdlLogs);
//        }
//        
//    }
//
//    // empId 를 comma 콤마 , 로 join 
//    private String getSchTrgEmpIdsJoining(Object retValue){
//        List<Long> empIds = new ArrayList<Long>();
//        
//        this.getAcesTrgEmpIds(retValue, empIds);
//       
//        if(empIds.size()==0) return null;
//
//        // 중복제거 및 정렬 후 콤마(,) 로 return       
//        empIds = empIds.stream().distinct().sorted().collect(Collectors.toList());
//        String strEmpIds = empIds.stream().map(empId->ComUtil.castValue(empId, String.class)).collect(Collectors.joining(","));
//        return strEmpIds;
//    }
//    
//    
//    //접근 대상 empId 추출
//    // list 또는 map 에서 추출
//    @SuppressWarnings("unchecked")
//    private void getAcesTrgEmpIds(Object retValue, List<Long> empIds){
//        if( retValue instanceof List ) this.getEmpIdsByList((List<Object>)retValue,empIds);
//        else if(retValue instanceof Map)  this.getEmpIdsByMaps((Map<String,Object> )retValue,empIds);
//    }
//    
//    
//    // list에서 Map 호출 
//    @SuppressWarnings("unchecked")
//    private void getEmpIdsByList(List<Object> retValue,   List<Long> empIds ){
//        // 리스트이면 스트림으로 돌면서 Map 일때 maps 를 호출한다. 
//        retValue.stream()
//        .filter(f->f instanceof Map)            // Map인경우만
//        .forEach(map-> this.getEmpIdsByMaps((Map<String,Object>)map, empIds));
//    }
//
//    // Map에서 데이터추출
//    private void getEmpIdsByMaps(Map<String,Object> map,  List<Long> empIds ){
//        map.keySet()
//        .stream()
//        .filter(key->RootCloudUtil.getFirstUpperCase(key).indexOf("EmpId")>=0 )
//        .forEach(key->
//            empIds.add(     ComUtil.castValue(map.get(key), Long.class)     )   // empid 추가
//        );
//    }
//
//
//    // 2021.12.15 메인화면에서 쪽지,TODO 갯수 set  
//    private void setMainMsgTodoCo() {
//        
//        try {
//            HashMap map = rootComService.searchMainMsgTodoCo();
//            
//            String rMainCo = objectMapper.writeValueAsString(map);
//            
//            HttpServletResponse response = RootCloudUtil.getResponse();
//            response.setHeader("rMainCo", rMainCo);
//            
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//        
//    }

    
    
}
