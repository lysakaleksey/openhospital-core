package org.isf.tempunit.test;

import org.isf.tempunit.model.TempUnit;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

import java.util.List;

public class TestTempUnitContext {
	private static List<TempUnit> savedTempUnit;

	@SuppressWarnings("unchecked")
	public void saveAll(DbJpaUtil jpa) throws OHException {
		jpa.beginTransaction();
		jpa.createQuery("SELECT * FROM TEMPUNIT", TempUnit.class, false);
		savedTempUnit = (List<TempUnit>) jpa.getList();
		jpa.commitTransaction();
	}

	public List<TempUnit> getAllSaved() throws OHException {
		return savedTempUnit;
	}

	@SuppressWarnings("unchecked")
	public void deleteNews(DbJpaUtil jpa) throws OHException {
		jpa.beginTransaction();
		jpa.createQuery("SELECT * FROM TEMPUNIT", TempUnit.class, false);
		List<TempUnit> tempUnits = (List<TempUnit>) jpa.getList();
		for (TempUnit tempUnit : tempUnits) {
			int index = savedTempUnit.indexOf(tempUnit);

			if (index == -1) {
				jpa.remove(tempUnit);
			}
		}
		jpa.commitTransaction();
	}
}
