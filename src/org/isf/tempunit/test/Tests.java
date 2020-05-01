package org.isf.tempunit.test;


import org.isf.tempunit.model.TempUnit;
import org.isf.tempunit.service.TempUnitIoOperation;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Tests {
	private static DbJpaUtil jpa;
	private static TestTempUnit testTempUnit;
	private static TestTempUnitContext testTempUnitContext;

	@Autowired
	TempUnitIoOperation tempUnitIoOperation;

	@BeforeClass
	public static void setUpClass() {
		jpa = new DbJpaUtil();
		testTempUnit = new TestTempUnit();
		testTempUnitContext = new TestTempUnitContext();
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

	@AfterClass
	public static void tearDownClass() throws OHException {
		testTempUnit = null;
		testTempUnitContext = null;
	}

	@Test
	public void testTempUnitGets() {
		String code = "";
		try {
			code = _setupTestTempUnit(false);
			_checkTempUnitIntoDb(code);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testTempUnitSets() {
		String code = "";
		try {
			code = _setupTestTempUnit(true);
			_checkTempUnitIntoDb(code);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoGetTempUnit() {
		String code = "";
		try {
			code = _setupTestTempUnit(false);
			TempUnit foundTempUnit = (TempUnit) jpa.find(TempUnit.class, code);
			ArrayList<TempUnit> tempUnits = tempUnitIoOperation.getTempUnits();

			assertEquals(foundTempUnit.getDescription(), tempUnits.get(tempUnits.size() - 1).getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoUpdateTempUnit() {
		String code = "";
		boolean result = false;
		try {
			code = _setupTestTempUnit(false);
			TempUnit foundTempUnit = (TempUnit) jpa.find(TempUnit.class, code);
			jpa.flush();
			foundTempUnit.setDescription("Update");
			result = tempUnitIoOperation.updateTempUnit(foundTempUnit);
			TempUnit updateTempUnit = (TempUnit) jpa.find(TempUnit.class, code);

			assertTrue(result);
			assertEquals("Update", updateTempUnit.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoNewTempUnit() {
		boolean result = false;
		try {
			TempUnit tempUnit = testTempUnit.setup(true);
			result = tempUnitIoOperation.newTempUnit(tempUnit);

			assertTrue(result);
			_checkTempUnitIntoDb(tempUnit.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoIsCodePresent() {
		String code = "";
		boolean result = false;
		try {
			code = _setupTestTempUnit(false);
			result = tempUnitIoOperation.isCodePresent(code);
			assertTrue(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoDeleteTempUnit() {
		String code = "";
		boolean result = false;
		try {
			code = _setupTestTempUnit(false);
			TempUnit foundTempUnit = (TempUnit) jpa.find(TempUnit.class, code);
			result = tempUnitIoOperation.deleteTempUnit(foundTempUnit);

			result = tempUnitIoOperation.isCodePresent(code);
			assertFalse(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}


	private void _saveContext() throws OHException {
		testTempUnitContext.saveAll(jpa);
	}

	private void _restoreContext() throws OHException {
		testTempUnitContext.deleteNews(jpa);
	}

	private String _setupTestTempUnit(boolean usingSet) throws OHException {
		TempUnit tempUnit;

		jpa.beginTransaction();
		tempUnit = testTempUnit.setup(usingSet);
		jpa.persist(tempUnit);
		jpa.commitTransaction();

		return tempUnit.getCode();
	}

	private void _checkTempUnitIntoDb(String code) throws OHException {
		TempUnit foundTempUnit;

		foundTempUnit = (TempUnit) jpa.find(TempUnit.class, code);
		testTempUnit.check(foundTempUnit);
	}
}