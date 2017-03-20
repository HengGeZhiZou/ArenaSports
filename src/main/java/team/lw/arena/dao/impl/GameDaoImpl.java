package team.lw.arena.dao.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.GameDao;
import team.lw.arena.entity.SixPeopleRoom;

import java.sql.Connection;

@Repository
@Scope("prototype")
public class GameDaoImpl extends BaseDaoImpl<SixPeopleRoom> implements GameDao{

    @Override
    public void startGame(String ownerID,int isFull) {
            String hql="UPDATE SixPeopleRoom set isFull=:bool where " +
                    "character01=:id and " +
                    "character02 != null and " +
                    "character03 != null and " +
                    "character04 != null and " +
                    "character05 != null and " +
                    "character06 != null";
            Session session= this.getHibernateTemplate().getSessionFactory().openSession();
            session.beginTransaction();
            Query query=session.createQuery(hql);
            System.out.println(isFull+ownerID);
            query.setParameter("bool",isFull);
            query.setParameter("id",ownerID);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
    }
}
