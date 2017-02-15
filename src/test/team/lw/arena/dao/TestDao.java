package team.lw.arena.dao;



import team.lw.arena.entity.UserLogin;

import java.io.Serializable;


public interface TestDao {
    UserLogin findUser(Serializable id);
}
