package lk.ijse.NoteCollector_Springboot.customStatusCodes;


import lk.ijse.NoteCollector_Springboot.dto.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedNoteErrorStatus implements NoteStatus {
    private int statusCode;
    private String message;
}
