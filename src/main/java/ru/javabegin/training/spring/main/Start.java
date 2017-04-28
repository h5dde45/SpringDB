package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javabegin.training.spring.dao.impls.SQLiteDAO;
import ru.javabegin.training.spring.dao.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("Song name8");
        mp3.setAuthor("Song author8");
        MP3 mp32 = new MP3();
        mp32.setName("Song name82");
        mp32.setAuthor("Song author82");
        List<MP3> mp3List=new ArrayList<>();
        mp3List.add(mp3);
        mp3List.add(mp32);


        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("sqliteDAO");

        System.out.println(sqLiteDAO.insertList(mp3List));


    }
}
