package lk.ijse.NoteCollector_Springboot.dao;


import lk.ijse.NoteCollector_Springboot.entity.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    /* JpaRepository<T, ID> --> T - Entity, ID - Data type of the Primary Key */
    /* CrudRepository<T, ID> super --> JpaRepository<T, ID> */
    /* JpaRepository<T, ID> *Abstracted *Generics */

}
