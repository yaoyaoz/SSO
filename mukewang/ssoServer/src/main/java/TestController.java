import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyao on 2018-06-18.
 */
@RestController
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/aaa")
    public String aaa() {
        return "欢迎光临瑶瑶论坛2";
    }

}
