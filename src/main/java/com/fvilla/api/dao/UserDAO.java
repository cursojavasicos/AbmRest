package com.fvilla.api.dao;

import com.fvilla.api.beans.UserInfo;
import com.fvilla.api.exceptions.DAOException;


public interface UserDAO {

	UserInfo getUserByName(String name) throws DAOException;
	
}
