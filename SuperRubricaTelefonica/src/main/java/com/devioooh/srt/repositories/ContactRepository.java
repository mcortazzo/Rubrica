package com.devioooh.srt.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.devioooh.srt.domain.Contact;

@Repository
public class ContactRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Contact> findAll(){
		return jdbcTemplate.query("Select * from contacts", new ContactRowMapper());
	}
	
	public Contact findById(long id) {
		String sql ="Select * from contacts where id= ?";
		return jdbcTemplate.queryForObject(sql, new ContactRowMapper(), id);
	}
	
	public Contact create(Contact contact) {
		String sql = "insert into contacts (first_name, last_name, phone, email) value (?, ?, ?, ?)";
		KeyHolder keyHolder =new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, contact.getFirstName());
				preparedStatement.setString(2, contact.getLastName());
				preparedStatement.setString(3, contact.getEmail());
				preparedStatement.setString(4, contact.getPhone());
				return preparedStatement;
			}
		},keyHolder);
		contact.setId(keyHolder.getKey().longValue());
		return contact;
	}
	
	static class ContactRowMapper implements RowMapper<Contact>{

		@Override
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setPhone(rs.getString("phone"));
			contact.setEmail(rs.getString("email"));
			return contact;
		}
	}
}
