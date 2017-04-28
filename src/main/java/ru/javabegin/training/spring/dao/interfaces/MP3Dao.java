package ru.javabegin.training.spring.dao.interfaces;

import ru.javabegin.training.spring.dao.objects.MP3;

import java.util.List;
import java.util.Map;

public interface MP3Dao {

	int insert(MP3 mp3);

    void insert(List<MP3> mp3List);

    Map<String, Integer> getStat();

    void delete(int id);

	void delete(MP3 mp3);

	MP3 getMP3ByID(int id);

	List<MP3> getMP3ListByName(String name);

	List<MP3> getMP3ListByAuthor(String author);

    int getMP3Count();
}
