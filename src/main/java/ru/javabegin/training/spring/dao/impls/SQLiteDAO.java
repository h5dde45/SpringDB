package ru.javabegin.training.spring.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.javabegin.training.spring.dao.interfaces.MP3Dao;
import ru.javabegin.training.spring.dao.objects.MP3;

import javax.sql.DataSource;
import java.util.List;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(MP3 mp3) {
		String sql = "insert into mp3 (name, author) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { mp3.getName(), mp3.getAuthor() });
	}

	@Override
	public void delete(MP3 mp3) {

	}

	@Override
	public MP3 getMP3ByID(int id) {
		return null;
	}

	@Override
	public List<MP3> getMP3ListByName(String name) {
		return null;
	}

	@Override
	public List<MP3> getMP3ListByAuthor(String author) {
		return null;
	}

}
