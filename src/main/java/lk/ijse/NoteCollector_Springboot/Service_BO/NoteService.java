package lk.ijse.NoteCollector_Springboot.Service_BO;



import lk.ijse.NoteCollector_Springboot.dto.NoteStatus;
import lk.ijse.NoteCollector_Springboot.dto.impl.NoteDto;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDto noteDto);
    void updateNote(NoteDto noteDto);
    void deleteNote(String noteId);
    NoteStatus getSelectedNote(String noteId);
    List<NoteDto> getAllNotes();
}
