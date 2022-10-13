package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //스프링 컨테이너 생성

    @Test
    @DisplayName("모든 빈 출력")
    void findBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // Bean 이름 가져오기(설정된 메소드 명이 호출됨)
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName); //빈 이름으로 빈 가져오기
            System.out.println("beanDefinitionName =  " + beanDefinitionName + " Object = " + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicaitonBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //ROLE_APPLICATION : 직접등록한 애플리케이션 빈
            //ROLE_INFRASTRUCTURE : 스프링 내부 애플리케이션 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName =  " + beanDefinitionName + " Object = " + bean);
            }
        }
    }
}
