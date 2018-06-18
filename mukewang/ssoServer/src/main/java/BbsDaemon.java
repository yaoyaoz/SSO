import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyao on 2018-06-18.
 */
@RestController
@EnableAutoConfiguration
public class BbsDaemon {

    @RequestMapping("/eee")
    public String index() {
        return "欢迎光临瑶瑶论坛";
    }

    public static void main(String[] args) {
        SpringApplication.run(BbsDaemon.class, args);
    }

}
