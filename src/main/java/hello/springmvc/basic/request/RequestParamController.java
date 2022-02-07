package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //HttpServletRequest, HttpServletResponse로 request, respose 사용.
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    //@RequestParam 이용.
    @ResponseBody   //return에서 "ok"를 그대로 http 응답 메세지에 넣어서 보낸다. (RestController처럼) 뷰가 아니라 브라우저에 문자자체를 반환시킬 수 있음
    @RequestMapping("/request-param-v2")
    public String RequestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    //HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String RequestParamV3(@RequestParam String username, @RequestParam int age){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    //객체가 아닌 String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String RequestParamV4(String username,int age){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    //@RequestParam - required 설정
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,     //true이면 http url에 꼭 username을 필수로 설정해야하고(   @RequestParam의 required의 디폴트값은 true이다.)
            @RequestParam(required = false) Integer age){           //false이면 입력 안해도 된다. 입력 안하면 null값 들어감.
                                                                   // 참고: int는 null값을 담을 수 없으므로 null값을 담을 경우에는 Integer로 받아야한다.

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    //아무 것도 입력을 안하면(파라미터에 값이 없는 경우) 값이 defaultValue로 들어감
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age){


        log.info("username={}, age={}", username, age);

        return "ok";
    }

    //파라미터 값을 Map으로 받을 수도 있다.
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }
}
