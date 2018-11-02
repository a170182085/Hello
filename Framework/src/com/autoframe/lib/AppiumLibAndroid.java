package com.autoframe.lib;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.debug.log.DebugLogFile;
import com.report.entry.ReportEntry;
import com.report.html.HtmlFileGlobal;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * @author 张蕾
 * 安卓段的共用方法
 *
 */
public class AppiumLibAndroid {
	 public static Logger logger = Logger.getLogger(DataStore.D_DebugLogger);
	 public static AndroidDriver driver;
	 ReportEntry re=new ReportEntry();
	 private FileHandler fileHandler=null;
	 
	 public void newSetUp(String p_Name) throws Exception {

	        // set up appium
	    	DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("platformName", DataStore.D_Adroid_platformName);
	        capabilities.setCapability("deviceName", DataStore.D_Adroid_deviceName);
	        capabilities.setCapability("noReset", DataStore.D_Adroid_noReset); //是否需要再次安装apk
	        capabilities.setCapability("appPackage", DataStore.D_Adroid_appPackage); //worktile的包
	        capabilities.setCapability("appActivity", DataStore.D_Adroid_appActivity); //启动的activity  .ui.external.WelcomeActivity
	      //设置支持中文输入
	        capabilities.setCapability("unicodeKeyboard", DataStore.D_Adroid_chinainput);
	        capabilities.setCapability("resetKeyboard", DataStore.D_Adroid_chinainput);
	        try {
				driver = new AndroidDriver<MobileElement>(new URL(DataStore.D_Adroid_hubURL), capabilities);//远程调用
				//driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				//driver = new AndroidDriver<MobileElement>(new URL("http://192.168.1.104:4723/wd/hub"), capabilities);
//				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				driver.quit();
				DebugLogFile.type("launch Android driver fail！"+e.toString());
			}
	        if(driver!=null) {
	   		 File file = new File("D:\\report\\");
	  		if(!file.exists()) {
	  			file.mkdir();
	  		}
	  		String path = System.getProperty("user.dir");
	  		File source = new File(path + "\\css\\demo_report_style.css");
	  		FileUtils.copyFileToDirectory(source,file,true);
	      	re.crateLog("D:\\report\\"+p_Name+".html");
	        }
	    }

	    public void newtearDown() throws Exception {
	        driver.quit();
	        re.closeLog();
	    }
    private String myUiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
      }

    public String scrollToByText(String text) {
        String uiScrollables =myUiScrollable("new UiSelector().descriptionContains(\"" + text + "\")") +
        					  myUiScrollable("new UiSelector().textContains(\"" + text + "\")");
        return uiScrollables;
       
      }
    public By parseObject(String p_object) {
		String newObjecyt = null;

		if (p_object.startsWith(".//") || p_object.startsWith("//")) {
			return By.xpath(p_object);
		} else if (p_object.startsWith("xpath=")) {
			newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
			return By.xpath(newObjecyt);
		} else if (p_object.startsWith("id=")) {
			newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
			return By.id(newObjecyt);
		} else if (p_object.startsWith("class=")) {
			newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
			return By.className(newObjecyt);
		} else if (p_object.startsWith("name=")) {
			newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
			return By.name(newObjecyt);
		} else
			return null;

	}
    public void newClick(String p_id){
		
//    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		try {
			
			driver.findElement(parseObject(p_id)).click();
			DebugLogFile.type(TextStore.T_ClickObject + p_id + TextStore.T_Pass);
//			logger.info(TextStore.T_ClickObject + p_id + TextStore.T_Pass);

		}

		catch (Exception e) {
			DebugLogFile.type(TextStore.T_Exception + "newClick(String p_id)"
					+ TextStore.T_DetailInfo + e.toString());
//			logger.severe(TextStore.T_Exception + "newClick(String p_id)"
//					+ TextStore.T_DetailInfo + e.toString());

		}
		
		    
	}
    
	public void newType(String p_id,String p_text){
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		try {
			driver.findElement(parseObject(p_id)).clear(); // 输入文字前，清除文本框中的文字
			driver.findElement(parseObject(p_id)).sendKeys(p_text);
			DebugLogFile.type(TextStore.T_Input + p_text + TextStore.T_To + p_id
					+ TextStore.T_Pass);
//			logger.info(TextStore.T_Input + p_text + TextStore.T_To + p_id + TextStore.T_Pass);

		} catch (Exception e) {
			DebugLogFile.type(TextStore.T_Exception + "newType" + TextStore.T_DetailInfo + e.toString());
//			logger.severe(TextStore.T_Exception + "newType" + TextStore.T_DetailInfo + e.toString());
		}
	}
	
	public void newSelect(String p_id,String p_text){
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		try {
			Select select = new Select(driver.findElement(parseObject(p_id)));
			select.selectByVisibleText(p_text);
			DebugLogFile.type(TextStore.T_SelectListValue + p_id+"内容是"+p_id
					+ TextStore.T_Pass);
//			logger.info(TextStore.T_SelectListValue + p_id+"内容是"+p_id + TextStore.T_Pass);

		} catch (Exception e) {
			DebugLogFile.type(TextStore.T_Exception + "newSelect"
					+ TextStore.T_DetailInfo + e.toString());
//			logger.severe(TextStore.T_Exception + "newSelect" + TextStore.T_DetailInfo + e.toString());
		}
	}
		
	public boolean newisDisplayed(String p_id) {
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver.findElement(parseObject(p_id)).isDisplayed();
	}
	public boolean newisSelected(String p_id) {
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver.findElementByAndroidUIAutomator(scrollToByText(p_id)).isSelected();
	}
	public void newscrollTo(String p_id) {
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.scrollTo(p_id);
	}
   
   public void newVerifyEquals(String p_message, Object p_expected,
			Object p_actual) throws Exception {

		if (p_expected.equals(p_actual)) {
		    re.write(p_message, p_expected.toString(), p_actual.toString());//写入html report or debug report
		    DebugLogFile.type(TextStore.T_Verify +"newVerifyEquals" + "通过");
		
		} else {

			re.write(p_message, p_expected.toString(), p_actual.toString());//写入html report or debug report
			//screenshot();
			DebugLogFile.type(TextStore.T_Verify +"newVerifyEquals" + "不通过，继续执行");


			
		}
	}
   
   public void newAssertEquals(String p_message, Object p_expected,
			Object p_actual) throws Exception {
      
		if (p_expected.equals(p_actual)) {
		    re.write(p_message, p_expected.toString(), p_actual.toString());//写入html report or debug report
		    DebugLogFile.type(TextStore.T_Verify +"newAssertEquals" + "通过");
		
		} else {
			re.write(p_message, p_expected.toString(), p_actual.toString());//写入html report or debug report
			re.closeLog();
			//screenshot();
		    driver.quit();
		    DebugLogFile.type(TextStore.T_Verify +"newAssertEquals" + "不通过，退出执行");
			
		}
	}
   
}
