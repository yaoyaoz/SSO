package utils;

/**
 * Created by yaoyao on 2018-06-18.
 */
public class TokenUtil {

    /**
     * 问题：
     * 既然有delToken这个方法，为什么还要设置存活期为60秒呢？
     */

    //存储临时令牌到redis中，存活期60秒
    public static void setToken(String token, TokenInfo tokenInfo) {
//        。。。这里未写
    }

    //根据token读取TokenInfo
    public static TokenInfo getToken(String tokenId) {
        TokenInfo tokenInfo = new TokenInfo();
//        。。。这里未写
        return tokenInfo;
    }

    //删除某个token键值
    public static void delToken() {
//        。。。这里未写
    }

}
