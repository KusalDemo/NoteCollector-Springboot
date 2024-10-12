package lk.ijse.NoteCollector_Springboot.dao;


import lk.ijse.NoteCollector_Springboot.entity.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    /* JpaRepository<T, ID> --> T - Entity, ID - Data type of the Primary Key */
    /* CrudRepository<T, ID> super --> JpaRepository<T, ID> */
    /* JpaRepository<T, ID> *Abstracted *Generics */

    Optional<User> findByEmail(String email); // <Optional<User>>
}
