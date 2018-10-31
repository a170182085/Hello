package com.worktile.testcase.web;

import com.worktileprojectlib.ObjectStore;

public class ObjectStoreMoudule1 extends ObjectStore{
	//编辑个人资料
	public static final String Account_userinfo = "xpath=//div[@class='ng-scope']/button[contains(text(),'编辑个人资料')]";
	//个人资料相关
	public static final String User_name = "name=display_name";//姓名
	public static final String User_title = "name=user_title";//职位
	public static final String User_department = "name=user_department";//部门
	public static final String User_autograph = "xpath=//textarea[@placeholder='个性签名']";
	public static final String User_save = "xpath=//button[@class='btn btn-success mr-14 mt_13 ng-binding']";
}
