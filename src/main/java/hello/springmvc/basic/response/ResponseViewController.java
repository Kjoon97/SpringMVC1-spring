package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 뷰 템플릿으로 반환하기.
 */
@Controller
public class ResponseViewController {

    //ModelAndView로 반환
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello!");

        return mav;
    }

    //String으로 반환
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hiii!");

        return "response/hello";  // 뷰리졸버가 실행되어 "response/hello" 뷰를 찾고 렌더링한다.
    }

    //권장하지 않는 방법. - 뷰 파일 경로이름과 매핑 경로랑 같음.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hiii!");
    }
}
