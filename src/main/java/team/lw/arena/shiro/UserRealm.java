package team.lw.arena.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.UserInfoService;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //2. 从 UsernamePasswordToken 中来获取 username
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        try {
            UserLogin userLogin= userInfoService.getUserLogin(upToken.getUsername());
            //        System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");
            //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
//        if ("unknown".equals(username)) {
//            throw new UnknownAccountException("用户不存在");
//        }
//        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
//        if ("monster".equals(username)) {
//            throw new LockedAccountException("用户被锁定");
//        }
            //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
            //以下信息是从数据库中获取的.
            //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
            Object pricipal=userLogin.getEmail();
            //2). credentials: 密码.
            Object credentials=userLogin.getPassword();
            //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
            String realmName=getName();
            //4). 盐值.
//            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(pricipal,credentials,realmName);
//            info=new SimpleAuthenticationInfo(pricipal,credentials,credentialsSalt,realmName);
            return info;
        } catch (ServiceException e) {
            throw new UnknownAccountException("----用户不存在----");
        }
    }
}
