package lk.ijse.NoteCollector_Springboot.entity.impl;

import jakarta.persistence.*;
import lk.ijse.NoteCollector_Springboot.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "note")
public class Note implements SuperEntity {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createdDate;
    private String priorityLevel;
    @ManyToOne
    @JoinColumn(name = "userid",nullable = false)
    private User user;
}
