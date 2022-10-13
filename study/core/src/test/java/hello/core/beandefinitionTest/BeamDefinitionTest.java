package hello.core.beandefinitionTest;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeamDefinitionTest {
    //AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionname : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionname);
            
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinition = " + beanDefinition + "     beanDefinitionName : " + beanDefinitionname);
            }
        }
    }

    @Test
    @DisplayName("optional Test")
    void optionalTest(){
        String test = Optional.of("ABCD").filter(v -> v.startsWith("AB")).orElse("Not AB");
        System.out.println("test = " + test);
    }
}
