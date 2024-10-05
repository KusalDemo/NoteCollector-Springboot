package lk.ijse.NoteCollector_Springboot.Service_BO;



import lk.ijse.NoteCollector_Springboot.dto.UserStatus;
import lk.ijse.NoteCollector_Springboot.dto.impl.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(String userId,UserDto userDto);
    void deleteUser(String userId);
    UserStatus getSelectedUser(String userId);
    List<UserDto> getAllUsers();
}
