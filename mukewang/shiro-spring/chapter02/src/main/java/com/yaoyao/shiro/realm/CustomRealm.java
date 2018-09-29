package com.yaoyao.shiro.realm;

import com.yaoyao.dao.UserDao;
import com.yaoyao.web.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by yaoyao on 2018-09-24.
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String) principals.getPrimaryPrincipal();

        //从数据库或者缓存中获取角色、权限数据
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionByUserName(roles);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(Set<String> roles) {
        StringBuffer roleNames = new StringBuffer();
        for (Iterator it = roles.iterator(); it.hasNext(); ) {
            roleNames.append(it.next() + ",");
        }
        if (roleNames.length() > 0) {
            roleNames.deleteCharAt(roleNames.length() - 1);
        }
        List<String> list = userDao.queryPermissionByRoleNames(roleNames.toString());
        Set<String> sets = new HashSet<String>();
        if (list != null && !list.isEmpty()) {
            sets.addAll(list);
        }
        return sets;
    }

    private Set<String> getRolesByUserName(String userName) {
        List<String> list = userDao.queryRolesByUserName(userName);
        Set<String> sets = new HashSet<String>(list);
        return sets;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1、从主体传过来的认证信息中，获得用户名
        String userName = (String) token.getPrincipal();

        //2、通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, "customRealm");

        //加盐
//        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("abc"));

        return authenticationInfo;

    }

    private String getPasswordByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        if (user != null) {
            return user.getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
//        Md5Hash md5Hash = new Md5Hash("123456");//e10adc3949ba59abbe56e057f20f883e
        Md5Hash md5Hash = new Md5Hash("123456", "abc");//0659c7992e268962384eb17fafe88364
        System.out.println(md5Hash.toString());
    }

}
