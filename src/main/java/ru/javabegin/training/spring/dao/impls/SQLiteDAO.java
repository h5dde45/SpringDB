package ru.javabegin.training.spring.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.javabegin.training.spring.dao.interfaces.MP3Dao;
import ru.javabegin.training.spring.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insert(MP3 mp3) {
        String sql = "insert into mp3 (name, author) VALUES (:name, :author)";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",mp3.getName());
        mapSqlParameterSource.addValue("author",mp3.getAuthor());
        jdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void insert(List<MP3> mp3List) {
        for (MP3 mp3 : mp3List) {
            insert(mp3);
        }
    }


    @Override
    public void delete(int id) {
       String sql="delete from mp3 where id=:id";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
       jdbcTemplate.update(sql,mapSqlParameterSource);

    }

    @Override
    public void delete(MP3 mp3) {
        delete(mp3.getId());

    }

    @Override
    public MP3 getMP3ByID(int id) {
        String sql="SELECT  * from mp3 where id=:id";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);

        return jdbcTemplate.queryForObject(sql,mapSqlParameterSource, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql="SELECT  * from mp3 where UPPER (name) LIKE :name";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", "%"+name.toUpperCase()+"%");

        return jdbcTemplate.query(sql,mapSqlParameterSource, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql="SELECT  * from mp3 where UPPER (author) LIKE :author";
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("author", "%"+author.toUpperCase()+"%");

        return jdbcTemplate.query(sql,mapSqlParameterSource, new MP3RowMapper());
    }

    @Override
    public  int getMP3Count(){
        String sql="select count(*) from mp3 ";
        return jdbcTemplate.getJdbcOperations().queryForObject(sql,Integer.class);
    }

    private static final class MP3RowMapper implements RowMapper<MP3>{

        @Override
        public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
            MP3 mp3=new MP3();
            mp3.setId(rs.getInt("id"));
            mp3.setName(rs.getString("name"));
            mp3.setAuthor(rs.getString("author"));
            return mp3;
        }
    }

}
