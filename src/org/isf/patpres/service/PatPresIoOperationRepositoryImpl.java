package org.isf.patpres.service;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


@Transactional
public class PatPresIoOperationRepositoryImpl implements PatPresIoOperationRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> findAllByFilter(int patId, String patName, GregorianCalendar dateFrom, GregorianCalendar dateTo,
										 String referredFrom, String specificSymptoms, String prescribed, String referredTo) {
		String clause = " WHERE";
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT PPR_ID FROM PATIENTPRESENTATION JOIN PATIENT ON PPR_PAT_ID = PAT_ID");
		if (patId > -1) {
			parameters.add(patId);
			query.append(clause).append(" PAT_ID = ?");
			clause = " AND";
		}

		if (patName != null && patName.length() > 0) {
			parameters.add("%" + patName.toUpperCase() + "%");
			query.append(clause).append(" UPPER(PAT_NAME) like ?");
			clause = " AND";
		}

		if (dateFrom != null) {
			query.append(clause).append(" DATE_FORMAT(PPR_PRES_DATE,'%Y-%m-%d') >= \"").append(_convertToSQLDateLimited(dateFrom)).append("\"");
			clause = " AND";
		}

		if (dateTo != null) {
			query.append(clause).append(" DATE_FORMAT(PPR_PRES_DATE,'%Y-%m-%d') <= \"").append(_convertToSQLDateLimited(dateTo)).append("\"");
			clause = " AND";
		}

		if (referredFrom != null && referredFrom.length() > 0) {
			parameters.add("%" + referredFrom.toUpperCase() + "%");
			query.append(clause).append(" UPPER(PPR_REFERRED_FROM) like ?");
			clause = " AND";
		}

		if (specificSymptoms != null && specificSymptoms.length() > 0) {
			parameters.add("%" + specificSymptoms.toUpperCase() + "%");
			query.append(clause).append(" UPPER(PPR_SPEC_SYMPTOMS) like ?");
			clause = " AND";
		}

		if (prescribed != null && prescribed.length() > 0) {
			parameters.add("%" + prescribed.toUpperCase() + "%");
			query.append(clause).append(" UPPER(PPR_PRESCRIBED) like ?");
			clause = " AND";
		}

		if (referredTo != null && referredTo.length() > 0) {
			parameters.add("%" + referredTo.toUpperCase() + "%");
			query.append(clause).append(" UPPER(PPR_REFERRED_TO) like ?");
		}
		query.append(" ORDER BY PPR_PRES_DATE DESC, PPR_ID DESC");

		Query nativeQuery = this.entityManager.createNativeQuery(query.toString());
		for (int i = 0; i < parameters.size(); i++) {
			nativeQuery.setParameter(i+1, parameters.get(i));
		}
		return nativeQuery.getResultList();
	}

	/**
	 * return a String representing the date in format <code>yyyy-MM-dd</code>
	 *
	 * @param date
	 * @return the date in format <code>yyyy-MM-dd</code>
	 */
	private String _convertToSQLDateLimited(GregorianCalendar date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date.getTime());
	}
}