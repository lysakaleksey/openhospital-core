package org.isf.patpres.test;

import org.isf.bsunit.model.BsUnit;
import org.isf.bsunit.test.TestBsUnit;
import org.isf.bsunit.test.TestBsUnitContext;
import org.isf.patient.model.Patient;
import org.isf.patient.test.TestPatient;
import org.isf.patient.test.TestPatientContext;
import org.isf.patpres.model.Bp;
import org.isf.patpres.model.PatientPresentation;
import org.isf.patpres.model.Vitals;
import org.isf.patpres.service.PatPresIoOperations;
import org.isf.tempunit.model.TempUnit;
import org.isf.tempunit.test.TestTempUnit;
import org.isf.tempunit.test.TestTempUnitContext;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Tests {
	private static DbJpaUtil jpa;
	private static TestBsUnit testBsUnit;
	private static TestTempUnit testTempUnit;
	private static TestPatient testPatient;
	private static TestPatientContext testPatientContext;
	private static TestBsUnitContext testBsUnitContext;
	private static TestTempUnitContext testTempUnitContext;
	private static TestPatientPresentation testPatientPresentation;
	private static TestPatientPresentationContext testPatientPresentationContext;

	@Autowired
	PatPresIoOperations patPresIoOperations;

	@BeforeClass
	public static void setUpClass() {
		jpa = new DbJpaUtil();
		testBsUnit = new TestBsUnit();
		testTempUnit = new TestTempUnit();
		testBsUnitContext = new TestBsUnitContext();
		testTempUnitContext = new TestTempUnitContext();
		testPatient = new TestPatient();
		testPatientContext = new TestPatientContext();
		testPatientPresentation = new TestPatientPresentation();
		testPatientPresentationContext = new TestPatientPresentationContext();
	}

	@AfterClass
	public static void tearDownClass() throws OHException {
		testBsUnit = null;
		testTempUnit = null;
		testPatient = null;
		testBsUnitContext = null;
		testTempUnitContext = null;
		testPatientContext = null;
		testPatientPresentation = null;
		testPatientPresentationContext = null;
	}

	@Before
	public void setUp() throws OHException {
		jpa.open();
		_saveContext();
	}

	@After
	public void tearDown() throws Exception {
		_restoreContext();
		jpa.flush();
		jpa.close();
	}

	private void _saveContext() throws OHException {
		testBsUnitContext.saveAll(jpa);
		testTempUnitContext.saveAll(jpa);
		testPatientContext.saveAll(jpa);
		testPatientPresentationContext.saveAll(jpa);
	}

	private void _restoreContext() throws OHException {
		testPatientPresentationContext.deleteNews(jpa);
		testPatientContext.deleteNews(jpa);
		testTempUnitContext.deleteNews(jpa);
		testBsUnitContext.deleteNews(jpa);
	}

	@Test
	public void testPatientPresentationSets1() {
		try {
			int code = _setupTestPatientPresentation(true);
			_checkPatientPresentationIntoDb(code);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testPatientPresentationSets2() {
		try {
			int code = _setupTestPatientPresentation(false);
			_checkPatientPresentationIntoDb(code);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private int _setupTestPatientPresentation(boolean usingSet) throws OHException {
		Patient patient = testPatient.setup(false);
		Vitals vitals = _createVitals();

		jpa.beginTransaction();
		PatientPresentation patientPresentation = testPatientPresentation.setup(patient, vitals, usingSet);
		jpa.persist(patient);
		jpa.persist(patientPresentation);
		jpa.commitTransaction();

		return patientPresentation.getCode();
	}

	private void _checkPatientPresentationIntoDb(int code) throws OHException {
		PatientPresentation foundPatientVaccine;

		foundPatientVaccine = (PatientPresentation) jpa.find(PatientPresentation.class, code);
		testPatientPresentation.check(foundPatientVaccine);
	}

	private Vitals _createVitals() throws OHException {
		BsUnit bsUnit = testBsUnit.setup(true);
		TempUnit tempUnit = testTempUnit.setup(true);

		Vitals vitals = new Vitals();
		vitals.setWeight(0.1f);
		vitals.setHeight(0.2f);
		vitals.setBloodSugar(0.3f);
		vitals.setTemperature(0.4f);
		vitals.setBsUnit(bsUnit.getCode());
		vitals.setTempUnit(tempUnit.getCode());
		vitals.setBp(new Bp(5, 6));

		return vitals;
	}

}
