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
        //1. �� AuthenticationToken ת��Ϊ UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //2. �� UsernamePasswordToken ������ȡ username
        //3. �������ݿ�ķ���, �����ݿ��в�ѯ username ��Ӧ���û���¼
        try {
            UserLogin userLogin= userInfoService.getUserLogin(upToken.getUsername());
            //        System.out.println("�����ݿ��л�ȡ username: " + username + " ����Ӧ���û���Ϣ.");
            //4. ���û�������, ������׳� UnknownAccountException �쳣
//        if ("unknown".equals(username)) {
//            throw new UnknownAccountException("�û�������");
//        }
//        //5. �����û���Ϣ�����, �����Ƿ���Ҫ�׳������� AuthenticationException �쳣.
//        if ("monster".equals(username)) {
//            throw new LockedAccountException("�û�������");
//        }
            //6. �����û������, ������ AuthenticationInfo ���󲢷���. ͨ��ʹ�õ�ʵ����Ϊ: SimpleAuthenticationInfo
            //������Ϣ�Ǵ����ݿ��л�ȡ��.
            //1). principal: ��֤��ʵ����Ϣ. ������ username, Ҳ���������ݱ��Ӧ���û���ʵ�������.
            Object pricipal=userLogin.getEmail();
            //2). credentials: ����.
            Object credentials=userLogin.getPassword();
            //3). realmName: ��ǰ realm ����� name. ���ø���� getName() ��������
            String realmName=getName();
            //4). ��ֵ.
//            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(pricipal,credentials,realmName);
//            info=new SimpleAuthenticationInfo(pricipal,credentials,credentialsSalt,realmName);
            return info;
        } catch (ServiceException e) {
            throw new UnknownAccountException("----�û�������----");
        }
    }
}
