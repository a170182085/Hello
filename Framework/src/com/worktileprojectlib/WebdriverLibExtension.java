package com.worktileprojectlib;

import com.autoframe.lib.CommonLib;
import com.autoframe.lib.DataStore;
import com.autoframe.lib.TextStore;
import com.autoframe.lib.WebDriverLib;

public class WebdriverLibExtension extends WebDriverLib {
	//1.重写方法
	//2.扩展方法
	public WebdriverLibExtension() {
		super();
	}
	public void newClick(String p_id){
		
		CommonLib.sleep(DataStore.D_Wait_ShortTime);
		logger.info("重写的方法");
		
		try {
			
			driver.findElement(parseObject(p_id)).click();
			logger.info(TextStore.T_ClickObject + p_id + TextStore.T_Pass);

		}

		catch (Exception e) {
			logger.severe(TextStore.T_Exception + "newClick(String p_id)"
					+ TextStore.T_DetailInfo + e.toString());

		}
		
		    
	}
}
