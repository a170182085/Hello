package com.worktile.testcase.web;

import com.autoframe.lib.DataStore;
import com.worktileprojectlib.BussinessLibWeb;
import com.worktileprojectlib.ObjectStore;

public class M1BussinessLib extends BussinessLibWeb {
	//设置账户个人资料
	public void setUserInfo() {
		super.newClick(ObjectStore.Index_user);
		super.newClick(ObjectStore.User_accountset);
		super.newClick(ObjectStoreMoudule1.Account_userinfo);
		super.newType(ObjectStoreMoudule1.User_name, DataStore.D_User_name);
		super.newType(ObjectStoreMoudule1.User_title, DataStore.D_User_title);
		super.newType(ObjectStoreMoudule1.User_department, DataStore.D_User_department);
		super.newType(ObjectStoreMoudule1.User_autograph, DataStore.D_User_autograph);
		super.newClick(ObjectStoreMoudule1.User_save);
	}
}
