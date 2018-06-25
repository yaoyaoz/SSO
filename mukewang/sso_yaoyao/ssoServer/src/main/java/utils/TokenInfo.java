package utils;

/**
 * Created by yaoyao on 2018-06-17.
 */
public class TokenInfo {

    /**
     * 用户唯一标识ID
     */
    private int userId;

    /**
     * 用户登录名
     */
    private String userName;

    /**
     * 来自登录请求的某应用系统标识
     */
    private String ssoClient;

    /**
     * 本次登录成功的全局会话sessionId
     */
    private String globalId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSsoClient() {
        return ssoClient;
    }

    public void setSsoClient(String ssoClient) {
        this.ssoClient = ssoClient;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }
}
