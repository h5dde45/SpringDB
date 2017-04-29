package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javabegin.training.spring.dao.impls.SQLiteDAO;
import ru.javabegin.training.spring.dao.objects.Author;
import ru.javabegin.training.spring.dao.objects.MP3;

public class Start {

    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("name3");

        Author author=new Author();
        author.setName("author3");

        mp3.setAuthor(author);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("sqliteDAO");

        System.out.println(sqLiteDAO.insert(mp3));

    }
}
