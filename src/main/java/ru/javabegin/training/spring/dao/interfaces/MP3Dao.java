package ru.javabegin.training.spring.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.spring.dao.objects.Author;
import ru.javabegin.training.spring.dao.objects.MP3;

import java.util.List;
import java.util.Map;

public interface MP3Dao {

    @Transactional
    int insertMP3(MP3 mp3);

    @Transactional
    int insertAuthor(Author author);

    int insertList(List<MP3> listMP3);

    Map<String, Integer> getStat();

    void delete(int id);

    void delete(MP3 mp3);

    MP3 getMP3ByID(int id);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    int getMP3Count();
}
