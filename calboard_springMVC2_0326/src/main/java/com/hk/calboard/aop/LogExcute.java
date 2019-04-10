package com.hk.calboard.aop;




import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;
import  org.slf4j.Logger;


public class LogExcute {
			
		
		/*getArgs(): 메서드의 아규먼트를 반환
		getTarget(): 대상객체를 반환
		getSignature(): 호출되는 메서드에 대한 정보
			-->getName():메서드의 이름을 구함
			-->toLongName():메서드의 풀네임(메서드의 리턴타입, 파라미터 타입 모두 표시)
			-->toShortName(): 메서드를 축약해서 표현(메서드 이름만)
		getThis():프록시 객체를 반환
	*/
	
		public void before(JoinPoint join) {
			Logger log = LoggerFactory.getLogger(join.getTarget()+"");
			log.debug("before-debug:시작");
			log.info("before-info:시작");
		}
		
		public void afterReturning(JoinPoint join) {
			Logger log = LoggerFactory.getLogger(join.getTarget()+"");
			log.debug("afterReturning-debug:끝");
			log.info("afterReturning-info:끝");
			
		}
		
		public void daoError(JoinPoint join) {
			Logger log = LoggerFactory.getLogger(join.getTarget()+"");
			log.debug("daoError-debug:끝"+join.getArgs());
			log.info("daoError-info:끝"+join.toLongString());
			
		}
}
