package lk.ijse.NoteCollector_Springboot.customStatusCodes;


import lk.ijse.NoteCollector_Springboot.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedUserStatus implements UserStatus {
    private int statusCode;
    private String message;
}
