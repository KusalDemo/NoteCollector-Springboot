package lk.ijse.NoteCollector_Springboot.Service_BO.impl;


import lk.ijse.NoteCollector_Springboot.Service_BO.UserService;
import lk.ijse.NoteCollector_Springboot.customStatusCodes.SelectedUserStatus;
import lk.ijse.NoteCollector_Springboot.dao.UserDao;
import lk.ijse.NoteCollector_Springboot.dto.UserStatus;
import lk.ijse.NoteCollector_Springboot.dto.impl.UserDto;
import lk.ijse.NoteCollector_Springboot.entity.impl.User;
import lk.ijse.NoteCollector_Springboot.exception.DataPersistException;
import lk.ijse.NoteCollector_Springboot.exception.UserNotFoundException;
import lk.ijse.NoteCollector_Springboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapper;

    @Override
    public void saveUser(UserDto userDto) {
        /*
            userDao.save() -> JpaRepository walin implement karala thiyenne methods..
        */

        User savedUser = userDao.save(mapper.toUserEntity(userDto));
        if(savedUser == null){
            // Custom Exception that we build 👇😎
            throw new DataPersistException("Failed to save user");
        }
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {
        Optional<User> fetchedUser = userDao.findById(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().setFirstName(userDto.getFirstName());
            fetchedUser.get().setLastName(userDto.getLastName());
            fetchedUser.get().setEmail(userDto.getEmail());
            fetchedUser.get().setPassword(userDto.getPassword());
            fetchedUser.get().setProfilePic(userDto.getProfilePic());
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> fetchedUser = userDao.findById(userId);
        if(!fetchedUser.isPresent()){
            throw new UserNotFoundException("User With : "+userId+" Not Found");
        }
        userDao.deleteById(userId);
    }

    @Override
    public UserStatus getSelectedUser(String userId) {
        if(userDao.existsById(userId)){
            User searchedUser = userDao.getReferenceById(userId);
            return mapper.toUserDto(searchedUser);
        }else{
            return new SelectedUserStatus(2,"User Not Found");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapper.toUserDtoList(userDao.findAll());
    }
}
