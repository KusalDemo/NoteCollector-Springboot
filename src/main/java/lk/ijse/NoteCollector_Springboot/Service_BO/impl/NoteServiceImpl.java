package lk.ijse.NoteCollector_Springboot.Service_BO.impl;


import lk.ijse.NoteCollector_Springboot.Service_BO.NoteService;
import lk.ijse.NoteCollector_Springboot.customStatusCodes.SelectedNoteErrorStatus;
import lk.ijse.NoteCollector_Springboot.dao.NoteDao;
import lk.ijse.NoteCollector_Springboot.dto.NoteStatus;
import lk.ijse.NoteCollector_Springboot.dto.impl.NoteDto;
import lk.ijse.NoteCollector_Springboot.entity.impl.Note;
import lk.ijse.NoteCollector_Springboot.exception.DataPersistException;
import lk.ijse.NoteCollector_Springboot.exception.NoteNotFoundException;
import lk.ijse.NoteCollector_Springboot.util.AppUtil;
import lk.ijse.NoteCollector_Springboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

// @Component - Not needed...
@Service
// Meta annotated - @Component
@Transactional
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveNote(NoteDto noteDto) {
        noteDto.setNoteId(AppUtil.generateNoteId());
        Note savedNote = noteDao.save(mapper.toNoteEntity(noteDto));
        if(savedNote == null){
            throw new DataPersistException("Failed to save Note");
        }
    }

    @Override
    public void updateNote(NoteDto noteDto) {
        Optional<Note> fetchedNote = noteDao.findById(noteDto.getNoteId());
        if(fetchedNote.isPresent()){
            fetchedNote.get().setNoteTitle(noteDto.getNoteTitle());
            fetchedNote.get().setNoteDesc(noteDto.getNoteDesc());
            fetchedNote.get().setCreatedDate(noteDto.getCreatedDate());
            fetchedNote.get().setPriorityLevel(noteDto.getPriorityLevel());
        }else {
            throw new NoteNotFoundException("Note With : "+noteDto.getNoteId()+" Not Found");
        }
    }

    @Override
    public void deleteNote(String noteId) {
        if(noteDao.existsById(noteId)){
            noteDao.deleteById(noteId);
        }else {
            throw new DataPersistException("Note With : "+noteId+" Not Found");
        }
    }

    @Override
    public NoteStatus getSelectedNote(String noteId) {
        if(noteDao.existsById(noteId)){
            Note fetchedNote = noteDao.getReferenceById(noteId);
            return mapper.toNoteDto(fetchedNote);
        }else{
            return new SelectedNoteErrorStatus(2,"Note With : "+noteId+" Not Found");
        }
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return mapper.toNoteDtoList(noteDao.findAll());
    }
}
