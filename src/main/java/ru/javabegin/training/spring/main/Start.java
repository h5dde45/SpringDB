package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javabegin.training.spring.dao.impls.SQLiteDAO;
import ru.javabegin.training.spring.dao.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        List<MP3> mp3List = new ArrayList<MP3>();
        MP3 mp3 = new MP3();
        mp3.setName("Song name5");
        mp3.setAuthor("Song author5");
        MP3 mp32 = new MP3();
        mp32.setName("Song name6");
        mp32.setAuthor("Song author6");
        mp3List.add(mp3);
        mp3List.add(mp32);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("sqliteDAO");

        sqLiteDAO.insert(mp3List);
    }
}
