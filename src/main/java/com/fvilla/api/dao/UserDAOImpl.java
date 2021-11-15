package com.fvilla.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fvilla.api.beans.UserInfo;
import com.fvilla.api.dao.mappers.UserInfoMapper;
import com.fvilla.api.exceptions.DAOException;


@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public UserInfo getUserByName(String name) throws DAOException {
				
		try {
			String query = "select * from user where nombre = :name";
			
			MapSqlParameterSource mapParameters = new MapSqlParameterSource();
			mapParameters.addValue("name", name);
			
			return jdbcTemplate.queryForObject(query, mapParameters, new UserInfoMapper());
			
		} catch (Throwable e) {
			throw new DAOException(e);
		}
	}

}
