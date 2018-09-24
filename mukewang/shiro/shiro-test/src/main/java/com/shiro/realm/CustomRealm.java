package com.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yaoyao on 2018-09-24.
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<String, String>();

    {
        userMap.put("yaoyao", "123456");
//        super.setName("customRealm");
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String) principals.getPrimaryPrincipal();

        //从数据库或者缓存中获取角色、权限数据
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(String userName) {

        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;

    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1、从主体传过来的认证信息中，获得用户名
        String userName = (String) token.getPrincipal();

        //2、通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if(password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, "customRealm");

        return authenticationInfo;

    }

    private String getPasswordByUserName(String userName) {

        return userMap.get(userName);

    }
}
