package org.isf.patient.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.isf.patient.model.Patient;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class PatientIoOperationRepositoryImpl implements PatientIoOperationRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@SuppressWarnings("unchecked")	
	@Override
	public List<Integer> findAllByHeightAndWeight(String regex) {
		return this.entityManager.
				createNativeQuery(_getPatientsWithHeightAndWeightQueryByRegex(regex)).
					getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Patient> findAllByFilter(String firstName, String secondName, Date birthDate, char sex) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT * FROM PATIENT WHERE (PAT_DELETED='N' OR PAT_DELETED IS NULL)");

		if (firstName != null && firstName.length() > 0) {
			parameters.add("%" + firstName.toUpperCase() + "%");
			query.append(" AND UPPER(PAT_FNAME) like ?");
		}

		if (secondName != null && secondName.length() > 0) {
			parameters.add("%" + secondName.toUpperCase() + "%");
			query.append(" AND UPPER(PAT_SNAME) like ?");
		}

		if (birthDate != null) {
			query.append(" AND DATE_FORMAT(PAT_BDATE,'%Y-%m-%d') = \"").append(_convertToSQLDateLimited(birthDate)).append("\"");
		}

		if (sex != ' ' && sex != 0) {
			parameters.add(sex);
			query.append(" AND PAT_SEX = ?");
		}

		query.append(" ORDER BY PAT_ID DESC");
		Query nativeQuery = this.entityManager.createNativeQuery(query.toString());
		for (int i = 0; i < parameters.size(); i++) {
			nativeQuery.setParameter(i+1, parameters.get(i));
		}
		return nativeQuery.getResultList();
	}

	private String _convertToSQLDateLimited(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	private String _getPatientsWithHeightAndWeightQueryByRegex(
			String regex) 
	{
		String[] words = _getPatientsWithHeightAndWeightRegex(regex);
		String query = _getPatientsWithHeightAndWeightQuery(words);
		
		
		return query;
	}
	
	private String[] _getPatientsWithHeightAndWeightRegex(
			String regex) 
	{
		String string = null;
		String[] words = new String[0];
		
		
		if ((regex != null) 
			&& (!regex.equals(""))) 
		{
			string = regex.trim().toLowerCase();
			words = string.split(" ");
		}

		return words;
	}
		
	private String _getPatientsWithHeightAndWeightQuery(
			String[] words)
	{
		StringBuilder queryBld = new StringBuilder(
				"SELECT PAT_ID FROM PATIENT LEFT JOIN (SELECT PEX_PAT_ID, PEX_HEIGHT AS PAT_HEIGHT, "
				+ "PEX_WEIGHT AS PAT_WEIGHT FROM PATIENTEXAMINATION GROUP BY PEX_PAT_ID ORDER BY PEX_DATE DESC) "
				+ "AS HW ON PAT_ID = HW.PEX_PAT_ID WHERE (PAT_DELETED='N' or PAT_DELETED is null) ");


        for (String word : words) {
            queryBld.append("AND CONCAT_WS(PAT_ID, LOWER(PAT_SNAME), LOWER(PAT_FNAME), LOWER(PAT_NOTE), LOWER(PAT_TAXCODE)) ");
            queryBld.append("LIKE CONCAT('%', \"" + word + "\" , '%') ");
        }
		queryBld.append(" ORDER BY PAT_ID DESC");

		return queryBld.toString();
	}
}