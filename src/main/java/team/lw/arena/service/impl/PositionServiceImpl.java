package team.lw.arena.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.lw.arena.dao.PositionDao;
import team.lw.arena.dao.UserInfoDao;
import team.lw.arena.entity.MobileUser;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.PositionService;
import team.lw.arena.util.ComparatorDistance;
import team.lw.arena.util.GetPositionUtil;

import javax.annotation.Resource;

import java.util.List;


@Service
@Transactional
@Scope("prototype")
public class PositionServiceImpl implements PositionService {

    private PositionDao positionDao;
    private UserInfoDao userInfoDao;
    private List<UserInfo> userInfos;
    private static final int pageSize = 5;

    @Resource(name = "positionDaoImpl")
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Resource(name = "userInfoDaoImpl")
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public List<UserInfo> getAroundPeople(MobileUser mobileUser) throws ServiceException {
        try {
            double[] around = GetPositionUtil.getAround(
                    mobileUser.getMuLatitude().doubleValue(),
                    mobileUser.getMuLongitud().doubleValue(),
                    3000);
            List<MobileUser> usersM = positionDao.getAroundPeople(
                    (mobileUser.getCurrPage() - 1) * pageSize, pageSize,
                    around[0], around[1], around[2], around[3]);
            for (MobileUser user : usersM) {
                if (user.getMuUId().equals(mobileUser.getMuUId())) continue;
                UserInfo userInfo = userInfoDao.findUserInfo(user.getMuUId());
                userInfo.setDistance((int) GetPositionUtil.distance(
                        mobileUser.getMuLongitud().doubleValue(),
                        mobileUser.getMuLatitude().doubleValue(),
                        user.getMuLongitud().doubleValue(),
                user.getMuLatitude().doubleValue()));
                userInfos.add(userInfo);
            }
            userInfos = ComparatorDistance.getComparatorDistance(userInfos);
            return userInfos;
        } catch (Exception e) {
            throw new ServiceException("GetAroundPeople Fail");
        }
    }

    @Override
    public void updatePosition(MobileUser mobileUser) {
        positionDao.update(mobileUser);
    }
}
