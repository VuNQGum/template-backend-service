package com.evnit.ttpm.stdv.repository;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("commonDao")
public class CommonDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long autoID(Class<?> persistentClass, Integer DonviID, String idName) {
		Long id = null;
		try {
			String queryString = "SELECT MAX(model." + idName + ")" + " FROM " + persistentClass.getSimpleName()
					+ " model " + " where model.donviId=" + DonviID
					// + " and model." + idName + " like '"
					// + Integer.toString(DonviID) + "%'";
					+ " and model." + idName + "/1000000000000 " + " >= " + DonviID + " and model." + idName
					+ "/1000000000000 " + " < " + (DonviID + 1);

			// hadh update
			if (persistentClass.getSimpleName().equals("NsHsgiaytogocFile")) {
				queryString = queryString.replace("model.donviId", "model.idDonVi");
			} // end update

			// id = jdbcTemplate.queryForObject(queryString, Long.class); // NEW CODE

			id = (Long) entityManager.createQuery(queryString).getSingleResult(); // OLD
			// HRMS
		} catch (NoResultException ex) {
			id = 0L;
		}

		if (id != null) {
			if (id == 0) {
				// id = DonviID * (long)10^12 + 1;
				id = DonviID * Long.parseLong("1000000000000") + 1;
			} else {
				id = id + 1;
			}
		} else {
			id = DonviID * Long.parseLong("1000000000000") + 1;
		}
		id = checkID(persistentClass, id, idName, DonviID);
		return id;
	}

	public Long autoID(Class<?> persistentClass, Integer DonviID, String propertyName, String idName) {
		Long id;
		try {
			String queryString = "SELECT MAX(model." + idName + ")" + " FROM " + persistentClass.getSimpleName()
					+ " model " + " where model." + propertyName + "=" + DonviID + " and model."
					+ idName + "/1000000000000 " + " >= " + DonviID + " and model." + idName + "/1000000000000 " + " < "
					+ (DonviID + 1);

			id = (Long) entityManager.createQuery(queryString).getSingleResult();
		} catch (NoResultException ex) {
			id = 0L;
		}
		if (id != null) {
			if (id == 0) {
				id = DonviID * (long) Math.pow(10D, 12D) + 1;
			} else {
				id = id + 1;
			}
		} else {
			id = DonviID * (long) Math.pow(10D, 12D) + 1;
		}

		id = checkID(persistentClass, id, idName, DonviID);
		return id;
	}

	/**
	 * Sinh ID tự động cho các bảng dữ liệu theo đơn vị thao tác với các bảng ko sử
	 * dụng trường DONVI_ID
	 * 
	 * @param DonviID
	 *            - ma don vi thao tac
	 * @param tableName
	 *            - ten bang
	 * @param idName
	 *            - ten truong id cua bang
	 * @return
	 */
	public Long createID(Integer DonviID, String idName, String tableName) {
		Long id;
		BigDecimal id1 = null;
		// String id1;
		String queryString = "";
		String Donvi_ID = DonviID.toString();
		try {
			queryString = "SELECT MAX(" + idName + ")" + " FROM " + tableName + " where left(" + idName + ",len("
					+ idName + ")-12) = '" + Donvi_ID + "'";

			id1 = (BigDecimal) entityManager.createNativeQuery(queryString).getSingleResult();
			// id = Long.parseLong(id1.toString());
		} catch (NoResultException ex) {
			id = 0L;
		}
		if (id1 != null) {
			id = Long.parseLong(id1.toString());
			if (id == 0) {
				// id = DonviID * (long)10^12 + 1;
				id = DonviID * Long.parseLong("1000000000000") + 1;
			} else {
				id = id + 1;
			}
		} else {
			id = DonviID * Long.parseLong("1000000000000") + 1;
		}
		return id;
	}

	private Long checkID(Class<?> persistentClass, Long id, String idName, Integer DonviID) {
		Long value = null;
		try {
			String queryString = "SELECT model." + idName + " FROM " + persistentClass.getSimpleName()
					+ " model WHERE model." + idName + "=" + id;
			try {
				value = (Long) entityManager.createQuery(queryString).getSingleResult();// OLD
				// HRMS
				// id = jdbcTemplate.queryForObject(queryString, Long.class); // NEW CODE

				queryString = "SELECT MAX(model." + idName + ")" + " FROM " + persistentClass.getSimpleName()
						+ " model " + " where model." + idName + "/1000000000000 " + " >= " + DonviID + " and model."
						+ idName + "/1000000000000 " + " < " + (DonviID + 1);

				id = (Long) entityManager.createQuery(queryString).getSingleResult();// OLD HRMS
				// id = jdbcTemplate.queryForObject(queryString, Long.class); // NEW CODE

				value = (Long) entityManager.createQuery(queryString).getSingleResult();// OLD
				// HRMS
				// value = jdbcTemplate.queryForObject(queryString, Long.class); // NEW CODE
				id = value + 1;
			} catch (NoResultException ex) {
				return id;
			}
			return id;
		} catch (NoResultException ex) {
			return id;
		} catch (Exception e) {
			return id;
		}
	}
}
