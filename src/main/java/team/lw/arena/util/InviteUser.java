package team.lw.arena.util;


import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class InviteUser {
    public static SixPeopleRoom getRoom(String inviteeID, SixPeopleRoom sixPeopleRoom) throws ServiceException {

        if (sixPeopleRoom.getCharacter03() == null) {

            if (sixPeopleRoom.getCharacter02() == null) {
                sixPeopleRoom.setCharacter02(inviteeID);
            } else {
                sixPeopleRoom.setCharacter03(inviteeID);
            }
        } else {

            if (sixPeopleRoom.getCharacter05() == null) {
                if (sixPeopleRoom.getCharacter04() == null) {
                    sixPeopleRoom.setCharacter04(inviteeID);
                } else {
                    sixPeopleRoom.setCharacter05(inviteeID);
                }
            } else {
                if (sixPeopleRoom.getCharacter06() == null) {
                    sixPeopleRoom.setCharacter06(inviteeID);
                } else {
                    throw new ServiceException("房间已满，请不要再次邀请");
                }
            }
        }
        return sixPeopleRoom;
    }

    public static List<UserInfo> getAllUser(SixPeopleRoom sixPeopleRoom) {
        List<UserInfo> lists = new ArrayList<UserInfo>();
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0:
                    lists.add(0, new UserInfo(sixPeopleRoom.getCharacter01()));
                    break;
                case 1:
                    lists.add(1, new UserInfo(sixPeopleRoom.getCharacter02()));
                    break;
                case 2:
                    if (sixPeopleRoom.getCharacter03() != null) {
                        lists.add(2, new UserInfo(sixPeopleRoom.getCharacter03()));
                    }
                    break;
                case 3:
                    if (sixPeopleRoom.getCharacter04() != null) {
                        lists.add(3, new UserInfo(sixPeopleRoom.getCharacter04()));
                    }
                    break;
                case 4:
                    if (sixPeopleRoom.getCharacter05() != null) {
                        lists.add(4, new UserInfo(sixPeopleRoom.getCharacter05()));
                    }
                    break;
                case 5:
                    if (sixPeopleRoom.getCharacter06() != null) {
                        lists.add(5, new UserInfo(sixPeopleRoom.getCharacter06()));
                    }
                    break;
            }
        }
        return lists;
    }
}
