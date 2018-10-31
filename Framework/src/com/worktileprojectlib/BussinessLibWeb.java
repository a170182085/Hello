package com.worktileprojectlib;

import org.openqa.selenium.By;

import com.autoframe.lib.CommonLib;
import com.autoframe.lib.DataStore;




public class BussinessLibWeb extends WebdriverLibExtension{
	
	//删除Log目录中的文件，为了满足report打包发送功能
	


	public void login() {

		
			/*
			driver.findElement(parseObject(ObjectStore_Web.Login_LoginLink)).click();
			 driver.findElement(parseObject(ObjectStore_Web.Login_Name)).clear();
			 driver.findElement(parseObject
					 (ObjectStore_Web.Login_Name)).sendKeys(p_user);
			 driver.findElement(parseObject(ObjectStore_Web.Login_Password)).clear();
			 driver.findElement(parseObject(ObjectStore_Web.Login_Password)).sendKeys(p_pwd);
			 driver.findElement(parseObject(ObjectStore_Web.Login_Button)).click();
			*/
			super.newClick(ObjectStore.Login_LoginEntry);
			super.newClick(ObjectStore.Login_LoginFreeLink);
			//super.swithchToWindow(ObjectStore.Login_LoginTab_Windowname);
			super.newType(ObjectStore.Login_LoginTab_Username,
					DataStore.D_Username);
			super.newType(ObjectStore.Login_LoginTab_Password,
					DataStore.D_Password);
			super.newClick(ObjectStore.Login_LoginTab_LoginButton);
			
			CommonLib.sleep(DataStore.D_Wait_MediumTime);
			     
			//判断是否有广告出现
			if (newIsElementPresent(ObjectStore.Login_LoginTab_AD)) //xpath元素是广告右上角的*
				super.newClick(ObjectStore.Login_LoginTab_AD);	                          
			
		
	}
	
	// 登录，需要登录的用户名和密码
		public void login(String p_name,String p_password) {
				//super.newClick(ObjectStore.Login_LoginLink);
				super.newClick(ObjectStore.Login_LoginFreeLink);
				//super.swithchToWindow(ObjectStore.Login_LoginTab_Windowname);
				super.newType(ObjectStore.Login_LoginTab_Username,
						p_name);
				super.newType(ObjectStore.Login_LoginTab_Password,
						p_password);
				super.newClick(ObjectStore.Login_LoginTab_LoginButton);
				//判断是否有广告出现
				if (newIsElementPresent(ObjectStore.Login_LoginTab_AD)) //xpath元素是广告右上角的*
					super.newClick(ObjectStore.Login_LoginTab_AD);
		}
	
	// 退出，不需要参数
	public void logout() {
		super.newClick(ObjectStore.Index_user);
		super.newClick(ObjectStore.User_exit);
	}

	public void createTask(String p_taskname,String p_assignto){
		super.newClick("");
		super.newType("", "");
	}
	public void createSchedule() {
		
	}
	
	
	

			
	
}

