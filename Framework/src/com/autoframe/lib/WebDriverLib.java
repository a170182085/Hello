package com.autoframe.lib;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import com.debug.log.DebugLogFile;
import com.mail.MailUtils;
import com.report.entry.ReportEntry;
import com.report.html.HtmlFileGlobal;

/**
 * web对selenium中的方法再次封装后的方法
 * newSetup根据获取到的参数启动不同的浏览器
 * parseObject解析各种不同的元素定位
 * newClick对原有的click方法添加了等待时间和日志的输出
 * 其他类似
 * newVerifyEquals断言失败继续执行
 * newAssertEquals断言失败停止执行
 * newIsTextPresent判断是否存在包含传参的文字信息
 */
public class WebDriverLib {
//	public static Logger logger = Logger.getLogger(DataStore.D_DebugLogger);
	public static WebDriver driver=null;
	ReportEntry re=new ReportEntry();
	private FileHandler fileHandler=null;
	private String browser=DataStore.D_Browser;
	private String baseUrl=DataStore.D_URL;	
		
		/**
	     * Constructor that takes in the instrumentation.
	     *
	     * @param instrumentation the {@link Instrumentation} instance
		 * @throws IOException 
	     *
	     */
	  
	 public void newSetup(String p_Name) throws IOException
	   
		
	 	{
		 if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver","C:\\Users\\zhanglei\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
				
				 driver = new ChromeDriver();

		}else if (browser.equalsIgnoreCase("ie")) {

				
					driver = new InternetExplorerDriver();
			
		}else if (browser.equalsIgnoreCase("safari")) {
				// System.setProperty("webdriver.safari.noinstall","C:\\Program Files\\Safari\\Safari.exe");
				Platform current = Platform.getCurrent();
				if (Platform.WINDOWS.is(current))
					driver = new SafariDriver();


		}else{
				   ProfilesIni pi = new ProfilesIni();
				   FirefoxProfile profile = pi.getProfile("default");
				   driver = new FirefoxDriver(profile);

			}
		     
            if (driver!=null)
            {	
        		File file = new File("D:\\report\\");
        		if(!file.exists()) {
        			file.mkdir();
        		}
        		String path = System.getProperty("user.dir");
        		File source = new File(path + "\\css\\demo_report_style.css");
        		FileUtils.copyFileToDirectory(source,file,true);
            	re.crateLog("D:\\report\\"+p_Name+".html");
            	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    			driver.get(baseUrl);
    			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    			driver.manage().window().maximize();
    			DebugLogFile.type(TextStore.T_Init + TextStore.T_Pass);
//    			logger.info(TextStore.T_Init + TextStore.T_Pass);
            }
            
			
	 	}
	  
	  public void newTeardown(){
		  DebugLogFile.type("newTeardown");
//		  logger.info("newTeardown");
		  re.closeLog();	 
		  driver.quit();	
		  MailUtils.sendmail("./Log/loggingResults/Debug.log");

	  }
	    
	    public static By parseObject(String p_object) {
			String newObjecyt = null;

			if (p_object.startsWith(".//") || p_object.startsWith("//")) {
				return By.xpath(p_object);
			} else if (p_object.startsWith("link=") || p_object.startsWith("Link=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.linkText(newObjecyt);
			} else if (p_object.startsWith("xpath=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.xpath(newObjecyt);
			} else if (p_object.startsWith("id=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.id(newObjecyt);
			} else if (p_object.startsWith("css=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.cssSelector(newObjecyt);
			} else if (p_object.startsWith("class=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.className(newObjecyt);
			} else if (p_object.startsWith("tagName=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.tagName(newObjecyt);
			} else if (p_object.startsWith("name=")) {
				newObjecyt = p_object.substring(p_object.indexOf("=") + 1);
				return By.name(newObjecyt);
			} else
				return null;

		}
		
		
		public void newClick(String p_id){
			
			CommonLib.sleep(DataStore.D_Wait_ShortTime);
			
			try {
				
				driver.findElement(parseObject(p_id)).click();
				DebugLogFile.type(TextStore.T_ClickObject + p_id + TextStore.T_Pass);
//				logger.info(TextStore.T_ClickObject + p_id + TextStore.T_Pass);

			}

			catch (Exception e) {
				DebugLogFile.type(TextStore.T_Exception + "newClick(String p_id)"
						+ TextStore.T_DetailInfo + e.toString());
//				logger.severe(TextStore.T_Exception + "newClick(String p_id)"
//						+ TextStore.T_DetailInfo + e.toString());

			}
			
			    
		}
	    
		public void newType(String p_id,String p_text){
			CommonLib.sleep(DataStore.D_Wait_ShortTime);
			
			try {
				driver.findElement(parseObject(p_id)).clear(); // 输入文字前，清除文本框中的文字
				driver.findElement(parseObject(p_id)).sendKeys(p_text);
				DebugLogFile.type(TextStore.T_Input + p_text + TextStore.T_To + p_id
						+ TextStore.T_Pass);
//				logger.info(TextStore.T_Input + p_text + TextStore.T_To + p_id + TextStore.T_Pass);

			} catch (Exception e) {
				DebugLogFile.type(TextStore.T_Exception + "newType" + TextStore.T_DetailInfo + e.toString());
//				logger.severe(TextStore.T_Exception + "newType" + TextStore.T_DetailInfo + e.toString());
			}
		}
		
		public void newSelect(String p_id,String p_text){
			CommonLib.sleep(DataStore.D_Wait_ShortTime);
			
			try {
				Select select = new Select(driver.findElement(parseObject(p_id)));
				select.selectByVisibleText(p_text);
				DebugLogFile.type(TextStore.T_SelectListValue + p_id+"内容是"+p_id
						+ TextStore.T_Pass);
//				logger.info(TextStore.T_SelectListValue + p_id+"内容是"+p_id + TextStore.T_Pass);

			} catch (Exception e) {
				DebugLogFile.type(TextStore.T_Exception + "newSelect"
						+ TextStore.T_DetailInfo + e.toString());
//				logger.severe(TextStore.T_Exception + "newSelect" + TextStore.T_DetailInfo + e.toString());
			}
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
	   
	   
	   public void swithchToWindow(String p_windowName){
		   CommonLib.sleep(DataStore.D_Wait_ShortTime);
		   for (String s : driver.getWindowHandles()) {
				driver.switchTo().window(s);
				if (driver.getTitle().equals(p_windowName)) {
					{
						DebugLogFile.type("切换到窗口：" + p_windowName + TextStore.T_Pass);
						break;
					}
					
				}
		    }
	   }
	   
	   
	   public void newRunScript(String p_script) {
		   
		   CommonLib.sleep(DataStore.D_Wait_ShortTime);

			try {
				((JavascriptExecutor) driver).executeScript(p_script);
//				logger.info("执行jS代码：" + p_script + TextStore.T_Pass);
				DebugLogFile.type("执行jS代码：" + p_script + TextStore.T_Pass);

			} catch (Exception e) {
//				logger.severe(TextStore.T_Exception	+ "newRunScript(String p_script)" + TextStore.T_DetailInfo+ e.toString());
				DebugLogFile.type(TextStore.T_Exception	+ "newRunScript(String p_script)" + TextStore.T_DetailInfo+ e.toString());
			}
	   }
			
			public boolean newIsElementPresent(String p_id) {
			    try {
			      driver.findElement(parseObject(p_id));
			      return true;
			    } catch (NoSuchElementException e) {
			      return false;
			    }
			  }
			public boolean newIsTextPresent(String p_id) {
				String str = "xpath=//*[contains(text(),'"+p_id+"')]";
			    try {
			      driver.findElement(parseObject(str));
			      return true;
			    } catch (NoSuchElementException e) {
			      return false;
			    }
			  }

	   
}
