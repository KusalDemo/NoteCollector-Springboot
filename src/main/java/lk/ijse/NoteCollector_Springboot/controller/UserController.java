package lk.ijse.NoteCollector_Springboot.controller;

import lk.ijse.NoteCollector_Springboot.Service_BO.UserService;
import lk.ijse.NoteCollector_Springboot.customStatusCodes.SelectedUserStatus;
import lk.ijse.NoteCollector_Springboot.dto.UserStatus;
import lk.ijse.NoteCollector_Springboot.dto.impl.UserDto;
import lk.ijse.NoteCollector_Springboot.exception.DataPersistException;
import lk.ijse.NoteCollector_Springboot.exception.UserNotFoundException;
import lk.ijse.NoteCollector_Springboot.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Handle Multipart Form Data. Think we have to save Profile Pic.
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@RequestPart("firstName") String firstName,
                                   @RequestPart("lastName") String lastName,
                                   @RequestPart("email") String email,
                                   @RequestPart("password") String password,
                                   @RequestPart("profilePic") MultipartFile profilePic) {
        // Build the Object
        String userId = AppUtil.generateUserId();
        String profilePicBase64="";

        try{
            byte[] profilePicBytes = profilePic.getBytes();
            profilePicBase64 = AppUtil.generateProfilePicToBase64(profilePicBytes);

            UserDto userDto = new UserDto();
            userDto.setUserId(userId);
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setEmail(email);
            userDto.setPassword(password);
            userDto.setProfilePic(profilePicBase64);
            userService.saveUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 500 - Internal Server Error
        // Wada 2 k karanna oone me 500 error eka nathi karanna..
        //  1. AppInit
        //  2. WebAppConfig
    }


    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") String userId) {
        String regex= "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        if(!userId.matches(regex)){
            return new SelectedUserStatus(1,"User Id is Empty or invalid");
        }
        return userService.getSelectedUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        String regex= "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        try{
            if(!userId.matches(regex)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return userService.deleteUser(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUser(@PathVariable("userId") String userId,
                           @RequestPart("firstName") String firstName,
                           @RequestPart("lastName") String lastName,
                           @RequestPart("email") String email,
                           @RequestPart("password") String password,
                           @RequestPart("profilePic") MultipartFile profilePic) {

        String profilePicBase64="";

        try{
            byte[] profilePicBytes = profilePic.getBytes();
            profilePicBase64 = AppUtil.generateProfilePicToBase64(profilePicBytes);
        }catch (Exception e){
            e.printStackTrace();
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setProfilePic(profilePicBase64);

        userService.updateUser(userId,userDto);
    }
}
