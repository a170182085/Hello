package com.worktile.testcase.web;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.autoframe.lib.DataStore;
import com.autoframe.lib.WebDriverLib;
import com.worktilecommonlib.ObjectStore;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCase {
	public static M1BussinessLib bl = new M1BussinessLib();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bl.newSetup("测试登陆");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bl.newTeardown();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1_login() throws Exception {
		//登陆
		String myCase1="验证登录";
		bl.login(DataStore.D_Username,DataStore.D_Password);
		bl.newAssertEquals(myCase1,true,bl.newIsElementPresent(ObjectStore.CreateTask_Button));	
	}
	@Test
	public void test2_setUserInfo() throws Exception {
		//登陆
		String myCase2="设置个人资料";
		bl.setUserInfo();
		bl.newAssertEquals(myCase2,true,bl.newIsElementPresent("xpath=//span[@class='ng-binding'][contains(text(),'"+DataStore.D_User_autograph+"')]"));	
	}
	@Test
	public void test3_logout() throws Exception {
		//登陆
		String myCase3="退出";
		bl.logout();
		bl.newAssertEquals(myCase3,true,bl.newIsElementPresent(ObjectStore.Login_LoginFreeLink));	
	}
}
