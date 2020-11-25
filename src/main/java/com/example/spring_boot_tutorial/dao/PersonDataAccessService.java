package com.example.spring_boot_tutorial.dao;

import com.example.spring_boot_tutorial.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO Person (id, name) VALUES (?, ?) RETURNING *;";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id, person.getName()}, new int[]{Types.OTHER, Types.VARCHAR}, ((resultSet, i) -> {
                    UUID userId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Person(userId, name);
                }
                ));

    }

    @Override
    public List<Person> getPeople() {
        final String sql = "SELECT * FROM Person;";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person( id,name);
        }));

    }

    @Override
    public Optional<Person> deletePersonById(UUID id) {
        final String sql = "DELETE FROM Person WHERE id = ? RETURNING *;";
        try {
            Person deletedPerson = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id}, new int[]{Types.OTHER}, ((resultSet, i) -> {
                        UUID userId = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        return new Person(userId, name);
                    }
                    ));
            return Optional.of(deletedPerson);
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> updatePerson(UUID id, Person person) {
        final String sql = "UPDATE Person SET name = ? WHERE id = ? RETURNING *;";
        try {
            Person updatedPerson = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{person.getName(), id}, new int[]{Types.VARCHAR, Types.OTHER}, ((resultSet, i) -> {
                        UUID userId = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        return new Person(userId, name);
                    }
                    ));

            return Optional.of(updatedPerson);
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {

        final String sql = "SELECT * FROM Person WHERE id = ?;";
        try {
            Person result = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.OTHER}, (resultSet, i) -> {
                UUID userId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                return new Person(userId, name);
            });
            return Optional.of(result);
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }
}
