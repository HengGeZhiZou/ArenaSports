package team.lw.arena.dao.impl;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.UserInfoDao;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope("prototype")
public class UserInfoDaoImpl extends BaseDaoImpl<UserLogin> implements UserInfoDao {

    @Override
    @SuppressWarnings("unchecked")
    public boolean checkEmailExist(String email) {
        String hql = "from UserLogin where email=?";
        List<UserLogin> list = (List<UserLogin>) this.getHibernateTemplate().find(hql, email);
        return list.get(0).getId() != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String userCheckLogin(UserLogin userLogin) {
        String hql = "from UserLogin where email=? and password=?";
        List<UserLogin> list = (List<UserLogin>) this.getHibernateTemplate().find(hql, userLogin.getEmail(), userLogin.getPassword());
        if (list != null) return list.get(0).getId();
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserLogin getUserLogin(String email) {
        String hql="from UserLogin where email=?";
        List<UserLogin> list = (List<UserLogin>) this.getHibernateTemplate().find(hql, email);
        if (list != null) return list.get(0);
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getMaxId() {
        String hql = "select max(id) from UserLogin ";
        List<String> list = (List<String>) this.getHibernateTemplate().find(hql);
        if (list != null) return list.get(0);
        return null;
    }


    @Override
    public void updateTime(Serializable id) {
        UserLogin userLogin = findObjectById(id);
        userLogin.setLastTime(new java.sql.Timestamp(System.currentTimeMillis()));
        update(userLogin);
    }


    @Override
    @SuppressWarnings("unchecked")
    public UserInfo findUserInfo(Serializable  id) {
        return this.getHibernateTemplate().get(UserInfo.class,id);
    }


    @Override
    public String addUserInfo(UserInfo userInfo) {
        this.getHibernateTemplate().save(userInfo);
        return userInfo.getId();
    }

    @Override
    public String updateUserInfo(UserInfo userInfo) {
        this.getHibernateTemplate().update(userInfo);
        return userInfo.getId();
    }

    @Override
    public void addToken(UserLogin userLogin) {
        UserLogin user=this.getHibernateTemplate().get(UserLogin.class,userLogin.getId());
        user.setToken(userLogin.getToken());
        this.getHibernateTemplate().update(user);
    }
}
