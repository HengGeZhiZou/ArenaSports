package team.lw.arena.util;


import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;

public class InviteUser {
    public static SixPeopleRoom getRoom(String inviteeID,SixPeopleRoom sixPeopleRoom) throws ServiceException {

        if(sixPeopleRoom.getCharacter03()==null){

            if (sixPeopleRoom.getCharacter02()==null){
                sixPeopleRoom.setCharacter02(inviteeID);
            }
            else {
                sixPeopleRoom.setCharacter03(inviteeID);
            }
        }else {

            if (sixPeopleRoom.getCharacter05()==null){
                if (sixPeopleRoom.getCharacter04()==null){
                    sixPeopleRoom.setCharacter04(inviteeID);
                }
                else {
                    sixPeopleRoom.setCharacter05(inviteeID);
                }
            }
            else {
                if (sixPeopleRoom.getCharacter06()==null){
                    sixPeopleRoom.setCharacter06(inviteeID);
                }
                else {
                    throw new ServiceException("房间已满，请不要再次邀请");
                }
            }
        }
        return sixPeopleRoom;
    }
}
