/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.util.Assert;


public abstract class AbstractRepositoryImpl<T> implements RepositoryInterface<T> {
	protected JdbcTemplate jdbcTemplate = null ;
	protected SimpleJdbcInsert simpleJdbcInsert = null ;

	protected String findAll ;
	protected String findByIdQuery ;
	protected String insertRow ;
	protected RowMapper<T> mapper ;
	
//	public abstract void init() throws Exception ;
	public abstract T addRow(T t) ;

//	public AbstractRepositoryImpl() throws Exception {		
//		init() ;
//		afterInit() ;
//	} ;
	
	public AbstractRepositoryImpl(JdbcTemplate jdbcTemplate, String findById, String insertRow, RowMapper<T> mapper) throws Exception 
	{
		super();
		this.jdbcTemplate = jdbcTemplate ;
		//this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
		
		this.findByIdQuery = findById ;
		this.insertRow = insertRow ;
		this.mapper = mapper ;

		afterInit() ;
	}
 	
	public void afterInit() throws Exception {
		Assert.notNull(this.jdbcTemplate, "No Jdbc Template set!");
		//Assert.notNull(this.simpleJdbcInsert, "No SimpleJdbcInsert set!");
		Assert.notNull(this.findByIdQuery, "No query set!");
		Assert.notNull(this.insertRow, "No insert set!");
		Assert.notNull(this.mapper, "No mapper!");
	}
	
	@SuppressWarnings("unchecked")
	public T getRowById(long id) throws DataAccessException {
		return jdbcTemplate.queryForObject(findByIdQuery, getMapper(), id) ;
	}
	
	public List<T> getAllRows() {
		return jdbcTemplate.query(findAll, getMapper()) ;
	}
	
	public Number executeAndReturnKey(Map<String, Object> args) {
		return simpleJdbcInsert.executeAndReturnKey(args) ; 
	}
	
	public String getFindByIdQuery() {
		return findByIdQuery;
	}

	public void setFindByIdQuery(String findById) {
		this.findByIdQuery = findById;
	}

	public String getInsertRow() {
		return insertRow;
	}

	public void setInsertRow(String insertRow) {
		this.insertRow = insertRow;
	}
	
	public String getFindAll() {
		return findAll;
	}

	public void setFindAll(String findAll) {
		this.findAll = findAll;
	}

	public RowMapper<T> getMapper() {
		return mapper;
	}

	public void setMapper(RowMapper<T> mapper) {
		this.mapper = mapper;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}