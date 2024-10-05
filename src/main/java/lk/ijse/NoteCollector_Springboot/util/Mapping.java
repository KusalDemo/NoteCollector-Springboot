package lk.ijse.NoteCollector_Springboot.util;

import lk.ijse.NoteCollector_Springboot.dto.impl.NoteDto;
import lk.ijse.NoteCollector_Springboot.dto.impl.UserDto;
import lk.ijse.NoteCollector_Springboot.entity.impl.Note;
import lk.ijse.NoteCollector_Springboot.entity.impl.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    // For user Mapping
    public User toUserEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
    public UserDto toUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
    public List<UserDto> toUserDtoList(List<User> userList){
        return modelMapper.map(userList, List.class);
    }

    // For note Mapping
    public Note toNoteEntity(NoteDto noteDto){
        return modelMapper.map(noteDto, Note.class);
    }
    public NoteDto toNoteDto(Note note){
        return modelMapper.map(note, NoteDto.class);
    }
    public List<NoteDto> toNoteDtoList(List<Note> noteList){
        return modelMapper.map(noteList, List.class);
    }
}
