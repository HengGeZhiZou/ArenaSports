package team.lw.arena.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import team.lw.arena.dao.TestDao;
import team.lw.arena.service.TestService;


@Service("testService")
@Scope("prototype")
public class TestServiceImpl implements TestService {

    public TestDao testDao ;

   @Autowired
    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    public TestDao getTestDao() {
        return testDao;
    }

    @Override
    public void say() {
        System.out.println(testDao.hashCode());
       System.out.println("hello service");
    }
}
