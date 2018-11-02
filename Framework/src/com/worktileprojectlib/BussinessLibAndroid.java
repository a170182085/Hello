package com.worktileprojectlib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.autoframe.lib.DataStore;
import com.worktile.entry.Schedule;
import com.worktile.testcase.mobile.ObjectStoreMoudule1;

import io.appium.java_client.android.AndroidDriver;

public class BussinessLibAndroid extends AndroidDriverLibExtension {

public boolean login(String P_UserName,String P_UserPass){
		
		try {	// 若提示更新进行稍后更新操作
			super.newisDisplayed(ObjectStoreMoudule1.Index_update);
			super.newClick(ObjectStoreMoudule1.Index_update);
		} catch (Exception e) {	}

		try {	// 加code 对应是否需要评论
			super.newisDisplayed(ObjectStoreMoudule1.Index_rest);
			super.newClick(ObjectStoreMoudule1.Index_rest);
		} catch (Exception e) {	}

		try {	// 判断是否已登陆成功
			super.newisDisplayed(ObjectStoreMoudule1.Schedule_new);			
			return super.newisDisplayed(ObjectStoreMoudule1.Schedule_new);
		} catch (Exception e) {	}
		super.newClick(ObjectStoreMoudule1.Index_login);
		super.newType(ObjectStoreMoudule1.username, DataStore.D_Username);
		super.newType(ObjectStoreMoudule1.password, DataStore.D_Password);
		super.newClick(ObjectStoreMoudule1.Login_button);
		try {	// 加code 对应是否需要评论
			driver.findElementById("android:id/button2").isDisplayed();
			driver.findElementById("android:id/button2").click();
		} catch (Exception e) {	}

		try {	// 判断是否已登陆成功
			super.newisDisplayed(ObjectStoreMoudule1.Schedule_new);	
			return super.newisDisplayed(ObjectStoreMoudule1.Schedule_new);	
		} catch (Exception e) {}
		return false;
	}
	 public void setSchedule(AndroidDriver driver,Schedule p_Schedule) {
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  //设置为10 ，不想等待太久
	    	driver.findElement(parseObject(ObjectStoreMoudule1.Schedule_new)).click();
	    	driver.findElement(parseObject(ObjectStoreMoudule1.Schedule_Schedule)).click();
	    	driver.findElement(parseObject(ObjectStoreMoudule1.Schedule_name)).sendKeys(p_Schedule.name);
	    	driver.findElement(parseObject(ObjectStoreMoudule1.Schedule_location)).sendKeys(p_Schedule.location);
	    	driver.findElement(parseObject(ObjectStoreMoudule1.Schedule_pro)).click();
	    	driver.findElement(By.name(p_Schedule.project)).click();
	    	driver.findElement(parseObject(ObjectStoreMoudule1.Schedule_people)).click();
	    	for(int i =0;i<p_Schedule.people.length;i++) {
	    		if(!driver.findElementByAndroidUIAutomator(scrollToByText(p_Schedule.people[i])).isSelected()) {
	    			driver.findElementByAndroidUIAutomator(scrollToByText(p_Schedule.people[i])).click();
	    		}
	        	
	    	}
	    	driver.findElement(parseObject(ObjectStore.Schedule_submit)).click();
	    	driver.findElement(parseObject(ObjectStore.Schedule_startDate)).click();
	    	driver.findElement(By.xpath("//android.view.View[contains(@index,"+(p_Schedule.startDay-1)+")]")).click();
	    	driver.findElement(parseObject(ObjectStore.Schedule_submit)).click();
	    	driver.findElement(parseObject(ObjectStore.Schedule_endDate)).click();
	    	driver.findElement(By.xpath("//android.view.View[contains(@index,"+(p_Schedule.endDay-1)+")]")).click();
	    	driver.findElement(parseObject(ObjectStore.Schedule_submit)).click();
	    	driver.findElement(parseObject(ObjectStore.Schedule_repeat)).click();
	    	if(driver.findElement(By.name(p_Schedule.repeat)).isSelected()) {
	    		driver.findElement(By.name(p_Schedule.repeat)).click();
	    	}else {
	    		driver.findElement(parseObject(ObjectStore.Schedule_repeat)).click();
	    	}
	    	if(!p_Schedule.remind.equals("否")) {
	    		driver.findElement(parseObject(ObjectStore.Schedule_remind)).click();
	    		driver.findElement(By.xpath("//android.view.View[contains(@index,"+(p_Schedule.startDay-1)+")]")).click();
	    		driver.findElement(parseObject(ObjectStore.Schedule_remindok)).click();
	    	}
	    	driver.findElement(parseObject(ObjectStore.Schedule_save)).click();
	    	toHome(driver);//返回工作台
	    	
	    }
   
    public boolean checkScheduleComment(AndroidDriver driver,String p_name,int p_day,String p_comment) {
    	driver.findElement(By.name("查看全部日程")).click();
    	driver.findElement(By.name(String.valueOf(p_day-1))).click();
    	driver.scrollTo(p_name).click();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	return driver.findElement(By.name(p_comment)).isDisplayed();
    }
    public boolean checkSchedule(AndroidDriver driver,String p_name,int p_day) {
    	driver.findElement(By.name("查看全部日程")).click();
    	driver.findElement(By.name(String.valueOf(p_day-1))).click();
    	return driver.findElement(By.name(p_name)).isDisplayed();
    }
    public void toHome(AndroidDriver driver) {
    	driver.findElement(By.className("android.widget.ImageButton")).click();
    }

    public void logout() {
    	super.newClick(ObjectStoreMoudule1.back);
    	super.newClick(ObjectStoreMoudule1.Schedule_save);
    	super.newClick(ObjectStoreMoudule1.setting);
    	super.newscrollTo(ObjectStoreMoudule1.quitname);
    	super.newClick(ObjectStoreMoudule1.quit);
    	
    }
}
