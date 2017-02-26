package team.lw.arena.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import team.lw.arena.dao.RecordDao;
import team.lw.arena.entity.Record;

import java.util.List;

@Repository
@Scope("prototype")
public class RecordDaoImpl extends BaseDaoImpl<Record> implements RecordDao {

    @Override
    @SuppressWarnings("unchecked")
    public int findCount() {
        String hql="select count(*) from Record where id=? and type=?";
        List<Long> list= (List<Long>) this.getHibernateTemplate().find(hql,"1701000001","basketball");
        if (list.size()>0) return list.get(0).intValue();
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Record> findByPage(int begin, int pageSize) {
        DetachedCriteria detachedCriteria= DetachedCriteria.forClass(Record.class);
        detachedCriteria.add(Restrictions.eq("id","1701000001"));
        detachedCriteria.add(Restrictions.eq("type","basketball"));
        return (List<Record>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }
}
