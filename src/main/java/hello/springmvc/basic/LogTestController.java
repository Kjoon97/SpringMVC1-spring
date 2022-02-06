package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j     //Slf4j로그 라이브러리를 롬복도 제공해준다.
@RestController
public class LogTestController {

   // private final Logger log = LoggerFactory.getLogger(getClass());  //getClass() -현재 내 클래스(LogTestController)로 지정

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name);
        log.trace("trace my log="+name); //이렇게 +연산자랑 같이 쓰면 안된다. 자바 언어 특성상 매서드 호출전 +연산자를 수행해버리기 때문에
                                         // 나중에 이 매서드가 호출 안 되면(application.properties에서 지정한 로그 출력 레벨 때문에) 메모리, cpu낭비가 생기는 것이다.
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}", name);     // 날짜 시간 INFO 프로세스id --- [실행한 쓰레드] 현재 컨트롤러 이름 : 메세지
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";   //@Controller로 설정하면 "ok"라는 이름의 뷰 파일을 반환하지만,
                      // @RestController는 "ok"라는 문자 자체를 웹브라우저에 출력한다. Http 메세지 바디에 그냥 "ok"를 담아 버린다. RestAPI 만들 때 중요한 컨트롤러이다.
    }
}
