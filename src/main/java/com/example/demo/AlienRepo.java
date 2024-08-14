package com.example.demo;

import com.example.demo.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Component
public class AlienRepo {

    private JdbcTemplateAutoConfiguration templateAutoConfiguration;

    public
    JdbcTemplateAutoConfiguration getTemplateAutoConfiguration() {
        return templateAutoConfiguration;
    }

    @Autowired
    public void setTemplateAutoConfiguration(JdbcTemplateAutoConfiguration templateAutoConfiguration) {
        this.templateAutoConfiguration = templateAutoConfiguration;
    }

    public  void save(Alien alien){

        String sql="insert into alien(id,name,tech) values (?,?,?)";

        int rows=templateAutoConfiguration.update(sql,alien.getId(),alien.getName(),alien.getTech());
        System.out.println(rows+"row/s affected");
    }
    public List<Alien> findAll(){

        String sql="select *  from alien";

        RowMapper<Alien> mapper=new RowMapper<Alien>() {
            @Override
           public  Alien mapRow(ResultSet rs,int rowNum)throws SQLException{

                Alien a=new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setTech(rs.getString(3));


                return  a;
            }
        };

        List<Alien> aliens=templateAutoConfiguration.query(sql,mapper);

        return  aliens;
    }
}
