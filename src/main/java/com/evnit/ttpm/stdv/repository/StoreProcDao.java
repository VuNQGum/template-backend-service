package com.evnit.ttpm.stdv.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("storeProcDao")
public class StoreProcDao {

	@Autowired
	protected JdbcTemplate utilitiesJdbcTemplate;
}
