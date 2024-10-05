package lk.ijse.NoteCollector_Springboot.dto.impl;


import lk.ijse.NoteCollector_Springboot.dto.NoteStatus;
import lk.ijse.NoteCollector_Springboot.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDto implements NoteStatus, SuperDto {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createdDate;
    private String priorityLevel;
    private String userId;
}
