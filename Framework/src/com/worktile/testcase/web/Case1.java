package com.worktile.testcase.web;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.autoframe.lib.DataStore;
import com.worktilecommonlib.ObjectStore;
import junit.framework.TestCase;




public class Case1 {

	M1BussinessLib bl = new M1BussinessLib();

	@Before
	public void setUp() throws Exception {

		bl.newSetup("测试登陆");

	}
   @Test
	public void test1_login() throws Exception {
        
		//登陆
		String myCase1="验证登录";
		bl.login(DataStore.D_Username,DataStore.D_Password);
		bl.newAssertEquals(myCase1,true,bl.newIsElementPresent(ObjectStore.CreateTask_Button));	
	};
	@Ignore
	public void test2_addSchedule() throws Exception {
		//添加日程
		String myCase2="添加日程";
	};
	
	@After
	public void tearDown() throws Exception {
		
		bl.newTeardown();
	}

}
