import org.junit.Test;

import java.util.UUID;

/**
 * Token生成测试类
 * Created by yaoyao on 2018-06-17.
 */
public class TokenTest {

    /**
     * 利用java util包工具直接生成一个32位唯一字符串
     */
    @Test
    public void testCreateToken() {
        String token = UUID.randomUUID().toString();
        System.out.println(token);
    }

}
