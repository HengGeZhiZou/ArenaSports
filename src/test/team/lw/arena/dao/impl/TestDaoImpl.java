package team.lw.arena.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.TestDao;
import team.lw.arena.entity.UserLogin;


import javax.annotation.Resource;
import java.io.Serializable;

@Repository("lii")
@Scope("prototype")
public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public UserLogin findUser(Serializable id) {
        return this.getHibernateTemplate().get(UserLogin.class,id);
    }
}
