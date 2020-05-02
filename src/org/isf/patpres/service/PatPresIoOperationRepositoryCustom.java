package org.isf.patpres.service;


import java.util.GregorianCalendar;
import java.util.List;


public interface PatPresIoOperationRepositoryCustom {
	List<Integer> findAllByFilter(int patId, String patName, GregorianCalendar dateFrom, GregorianCalendar dateTo,
								  String referredFrom, String specificSymptoms, String prescribed, String referredTo);
}
