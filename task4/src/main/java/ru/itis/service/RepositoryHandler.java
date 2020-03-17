package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.model.Note;
import ru.itis.repository.NoteRepository;

import java.util.List;

@Component
public class RepositoryHandler {
    @Autowired
    NoteRepository noteRepository;

    public void add(Note note) {
        noteRepository.save(note);
    }

    public List<Note> out() {
        return (List<Note>) noteRepository.findAll();
    }
}
