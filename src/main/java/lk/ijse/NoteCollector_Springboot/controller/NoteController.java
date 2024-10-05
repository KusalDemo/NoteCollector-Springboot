package lk.ijse.NoteCollector_Springboot.controller;


import lk.ijse.NoteCollector_Springboot.Service_BO.NoteService;
import lk.ijse.NoteCollector_Springboot.customStatusCodes.SelectedNoteErrorStatus;
import lk.ijse.NoteCollector_Springboot.dto.NoteStatus;
import lk.ijse.NoteCollector_Springboot.dto.impl.NoteDto;
import lk.ijse.NoteCollector_Springboot.exception.DataPersistException;
import lk.ijse.NoteCollector_Springboot.exception.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @Component - Not needed...
@RequestMapping("api/v1/notes")
public class NoteController {

    @Autowired
    // Dependency Injection.
    // If @Autowired generates an error that's mean spring Application context can't find any bean related to it..
    // Check Service class.
    private NoteService noteService;

    /*
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE
            ,produces = MediaType.APPLICATION_JSON_VALUE)

    // ✅JSON object ekk enne *Serialize* karanna oone dhan (JSON -> NoteDto)

    public NoteDto saveNote(@RequestBody NoteDto noteDto) {
        return noteService.saveNote(noteDto);
        */

    /*JsonbBuilder.create().toJson(noteDto, System.out);

     *  Yasson is not that much advanced than jackson or Gson..
     *  No Other codes required to convert NoteDto to JSON or JSON to NoteDto
     *

    }*/

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNote(@RequestBody NoteDto noteDto) {
        try {
            noteService.saveNote(noteDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteStatus getSelectedNote(@PathVariable("noteId") String noteId) {
        String regex = "^NOTE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        if (!noteId.matches(regex)) {
            return new SelectedNoteErrorStatus(1, "Invalid Note Id");
        }
        return noteService.getSelectedNote(noteId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDto> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PutMapping(value = "/{noteId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable("noteId") String noteId,@RequestBody NoteDto noteDto) {
        String regex = "^NOTE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        try{
            if (noteId.matches(regex)) {
                noteDto.setNoteId(noteId);
                noteService.updateNote(noteDto);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable("noteId") String noteId) {
        String regex = "^NOTE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        try{
            if (!noteId.matches(regex)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                 noteService.deleteNote(noteId);
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
