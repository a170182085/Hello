package com.worktile.testcase.mobile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.autoframe.lib.DataStore;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCase {
	public static MBussinessLib bl = new MBussinessLib();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bl.newSetUp("测试启动");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bl.newtearDown();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1_login() throws Exception {
		String myCase1="验证登录";
		bl.newAssertEquals(myCase1,true,bl.login(DataStore.D_Username,DataStore.D_Password));	
	}
	@Test
	public void test1_logout() throws Exception {
		String myCase1="验证登录";
		bl.logout();	
	}
}
