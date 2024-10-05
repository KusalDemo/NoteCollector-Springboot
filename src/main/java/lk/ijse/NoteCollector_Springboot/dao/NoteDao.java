package lk.ijse.NoteCollector_Springboot.dao;


import lk.ijse.NoteCollector_Springboot.entity.impl.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDao extends JpaRepository<Note, String> {


}
