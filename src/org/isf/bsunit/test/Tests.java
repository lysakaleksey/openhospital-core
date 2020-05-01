package org.isf.bsunit.test;


import org.isf.bsunit.model.BsUnit;
import org.isf.bsunit.service.BsUnitIoOperation;
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
	private static TestBsUnit testBsUnit;
	private static TestBsUnitContext testBsUnitContext;

	@Autowired
	BsUnitIoOperation bsUnitIoOperation;

	@BeforeClass
	public static void setUpClass() {
		jpa = new DbJpaUtil();
		testBsUnit = new TestBsUnit();
		testBsUnitContext = new TestBsUnitContext();
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
		testBsUnit = null;
		testBsUnitContext = null;
	}
	
	@Test
	public void testBsUnitGets() {
		String code = "";
		try {
			code = _setupTestBsUnit(false);
			_checkBsUnitIntoDb(code);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testBsUnitSets() {
		String code = "";
		try {
			code = _setupTestBsUnit(true);
			_checkBsUnitIntoDb(code);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoGetBsUnit() {
		String code = "";
		try {
			code = _setupTestBsUnit(false);
			BsUnit foundBsUnit = (BsUnit) jpa.find(BsUnit.class, code);
			ArrayList<BsUnit> bsUnits = bsUnitIoOperation.getBsUnits();

			assertEquals(foundBsUnit.getDescription(), bsUnits.get(bsUnits.size() - 1).getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoUpdateBsUnit() {
		String code = "";
		boolean result = false;
		try {
			code = _setupTestBsUnit(false);
			BsUnit foundBsUnit = (BsUnit) jpa.find(BsUnit.class, code);
			jpa.flush();
			foundBsUnit.setDescription("Update");
			result = bsUnitIoOperation.updateBsUnit(foundBsUnit);
			BsUnit updateBsUnit = (BsUnit) jpa.find(BsUnit.class, code);

			assertTrue(result);
			assertEquals("Update", updateBsUnit.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoNewBsUnit() {
		boolean result = false;
		try {
			BsUnit bsUnit = testBsUnit.setup(true);
			result = bsUnitIoOperation.newBsUnit(bsUnit);

			assertTrue(result);
			_checkBsUnitIntoDb(bsUnit.getCode());
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
			code = _setupTestBsUnit(false);
			result = bsUnitIoOperation.isCodePresent(code);
			assertTrue(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIoDeleteBsUnit() {
		String code = "";
		boolean result = false;
		try {
			code = _setupTestBsUnit(false);
			BsUnit foundBsUnit = (BsUnit) jpa.find(BsUnit.class, code);
			result = bsUnitIoOperation.deleteBsUnit(foundBsUnit);

			result = bsUnitIoOperation.isCodePresent(code);
			assertFalse(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}


	private void _saveContext() throws OHException {
		testBsUnitContext.saveAll(jpa);
	}

	private void _restoreContext() throws OHException {
		testBsUnitContext.deleteNews(jpa);
	}

	private String _setupTestBsUnit(boolean usingSet) throws OHException {
		BsUnit bsUnit;

		jpa.beginTransaction();
		bsUnit = testBsUnit.setup(usingSet);
		jpa.persist(bsUnit);
		jpa.commitTransaction();

		return bsUnit.getCode();
	}

	private void _checkBsUnitIntoDb(String code) throws OHException {
		BsUnit foundBsUnit;

		foundBsUnit = (BsUnit) jpa.find(BsUnit.class, code);
		testBsUnit.check(foundBsUnit);
	}
}