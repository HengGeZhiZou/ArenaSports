package team.lw.arena.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.PositionDao;
import team.lw.arena.dao.UserInfoDao;
import team.lw.arena.entity.MobileUser;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.UserInfoService;
import team.lw.arena.util.CreateNewUserId;
import team.lw.arena.util.Token;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;


@Service
@Transactional
@Scope("prototype")
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDao userInfoDao;
    private PositionDao positionDao;

    @Resource(name = "userInfoDaoImpl")
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Resource(name = "positionDaoImpl")
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public UserLogin getUserLogin(String email) throws ServiceException {
        try {
            return userInfoDao.getUserLogin(email);
        } catch (Exception e) {
            throw new ServiceException("账号不存在");
        }
    }

    @Override
    public String loginService(UserLogin userLogin) throws ServiceException {
        try {
            String id = userInfoDao.userCheckLogin(userLogin);
            userInfoDao.updateTime(id);
            return id;
        } catch (Exception e) {
            throw new ServiceException("账号密码错误...");
        }
    }

    @Override
    public boolean checkEmailExistService(String email) throws ServiceException {
        try {
            return userInfoDao.checkEmailExist(email);
        } catch (Exception e) {
            return  false;
        }
    }

    @Override
    public String registerService(UserLogin userLogin) {
        userLogin.setId(CreateNewUserId.getNewUserId(userInfoDao.getMaxId()));
        userLogin.setLastTime(new java.sql.Timestamp(System.currentTimeMillis()));
        userLogin.setAddTime(new java.sql.Timestamp(System.currentTimeMillis()));
        userInfoDao.save(userLogin);
        positionDao.save(new MobileUser(userLogin.getId(), new BigDecimal(0), new BigDecimal(0)));
        return userLogin.getId();
    }

    @Override
    public String updatePasswordService(UserLogin userLogin) throws ServiceException {
        try {
            UserLogin user = userInfoDao.findObjectById(userLogin.getId());
            user.setPassword(userLogin.getPassword());
            userInfoDao.update(user);
            return user.getId();
        } catch (Exception e) {
            throw new ServiceException("传入id不存在");
        }
    }

    @Override
    public UserInfo findUserInfo(Serializable id) throws ServiceException {
        try {
            return userInfoDao.findUserInfo(id);
        } catch (Exception e) {
            throw new ServiceException("传入id不存在");
        }
    }

    @Override
    public String addUserInfoService(UserInfo userInfo) {
        userInfo.setAddTime(new java.sql.Timestamp(System.currentTimeMillis()));
        userInfoDao.addUserInfo(userInfo);
        return userInfo.getId();
    }

    @Override
    public String updateUserInfoService(UserInfo userInfo) {
        userInfo.setAddTime(new java.sql.Timestamp(System.currentTimeMillis()));
        userInfoDao.updateUserInfo(userInfo);
        return userInfo.getId();
    }

    @Override
    public String addToken(String email) {
        UserLogin userLogin = userInfoDao.getUserLogin(email);
        userLogin.setToken(Token.getToken(userLogin.getId()));
        userInfoDao.addToken(userLogin);
        return userLogin.getToken();
    }

    @Override
    public void deleteToken(String email) {
        UserLogin userLogin = userInfoDao.getUserLogin(email);
        userLogin.setToken("");
        userInfoDao.addToken(userLogin);
    }

}
