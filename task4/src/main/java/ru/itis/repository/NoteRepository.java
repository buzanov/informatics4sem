package ru.itis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.model.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}
