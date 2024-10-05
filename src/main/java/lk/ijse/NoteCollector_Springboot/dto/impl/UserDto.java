package lk.ijse.NoteCollector_Springboot.dto.impl;


import lk.ijse.NoteCollector_Springboot.dto.SuperDto;
import lk.ijse.NoteCollector_Springboot.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements UserStatus, SuperDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private List<NoteDto> notes;
}
