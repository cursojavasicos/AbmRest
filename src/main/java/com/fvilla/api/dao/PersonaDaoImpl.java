package com.fvilla.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fvilla.api.beans.Persona;
import com.fvilla.api.exceptions.DniDuplicadoException;
import com.fvilla.api.exceptions.PersonaNotFoundException;

@Repository
public class PersonaDaoImpl implements PersonaDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Persona> getPersonas() {
		String sql = "SELECT * FROM personas;";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Persona.class));
	}

	@Override
	public Persona getPersonaById(int id) throws PersonaNotFoundException {
		try {
			String sql = "SELECT * FROM personas WHERE id=?";
			Persona persona = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Persona.class), id);
			return persona;
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new PersonaNotFoundException();
		}
	}

	@Override
	public int savePersona(Persona persona) throws DniDuplicadoException {
		String sql = "INSERT INTO personas (apellido, fecha_nacimiento, dni, nombre, profesion) VALUES (?,?,?,?,?)";
		
		int res = jdbcTemplate.update(sql, new Object[] { persona.getApellido(), persona.getFechaNacimiento(),
				persona.getDni(), persona.getNombre(), persona.getProfesion() });
		
		if (res != 1) {
			throw new DniDuplicadoException();
		}

		return res;
	}

	@Override
	public int deletePersonaById(int id) throws PersonaNotFoundException {
		String sql = " DELETE FROM personas WHERE id=?";
		
		int res = jdbcTemplate.update(sql, id);
		
		if (res != 1) {
			throw new PersonaNotFoundException();
		}
		
		return res;
	}

	@Override
	public int editPersona(Persona persona) throws PersonaNotFoundException, DniDuplicadoException {
		// getPersonaById verifica en casi de haber exception si es de tipo PersonaNotFound
		this.getPersonaById(persona.getId());
		
		String sql = "UPDATE personas SET apellido=?, fecha_nacimiento=?, dni=?, nombre=?, profesion=? WHERE id=?";
		int res = jdbcTemplate.update(sql, new Object[] { persona.getApellido(), persona.getFechaNacimiento(),
				persona.getDni(), persona.getNombre(), persona.getProfesion(), persona.getId() });
		
		if (res != 1) {
			throw new DniDuplicadoException();
		}
		
		return res;
	}

}
