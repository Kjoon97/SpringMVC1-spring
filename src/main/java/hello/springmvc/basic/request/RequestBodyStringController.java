package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);  //항상 스트림은 바이트 코드인데 바이트 코드를 문자로 받을 때는, 어떤 인코딩으로 받을지 지정해줘야한다.

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    //스프링은 파라미터가 InputStream, OutputStream을 지원한다. -inputStream( HTTP 요청 메시지 바디의 내용을 직접 조회)
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException{
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);  //항상 스트림은 바이트 코드인데 바이트 코드를 문자로 받을 때는, 어떤 인코딩으로 받을지 지정해줘야한다.
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    //HttpEntity - Http Body에 있는 것을 문자로 받아올 수 있음.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException{

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    //실무에서는 이 방법을 주로 씀
    //HttpEnitiy말고 @RequestBody 애노테이션을 사용해서 HttpBody 메세지를 받아 올 수 있음.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){

        log.info("messageBody={}", messageBody);

        return "ok";   //  @ResponseBody가 "ok"를 Http응답코드에 담아서 넣어서 반환한다.
    }
    //요청오는 것은 @RequestBody가, 응답하는 것은 @ResponseBody가 처리해줌.
}
