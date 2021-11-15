package com.fvilla.api.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import com.fvilla.api.beans.UserInfo;


public class UserInfoMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfo u = new UserInfo();
		
		u.setUser(rs.getString("nombre"));
		
		u.setPassword(new String(Base64.getDecoder().decode(rs.getString("password"))));
		
		return u;
	}

}
