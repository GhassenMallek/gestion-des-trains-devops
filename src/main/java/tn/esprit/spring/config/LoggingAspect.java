package tn.esprit.spring.config;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
@Before("execution(* tn.esprit.spring.service.*.set*(..))")
public void logMethodEntry(JoinPoint joinPoint) {
logger.info("In method %1$s " , joinPoint.getSignature().getName());
}
@After("execution(* tn.esprit.spring.service.*.set*(..))")
public void logMethodExit(JoinPoint joinPoint) {
	 joinPoint.getSignature().getName();
}}