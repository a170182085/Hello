package com.autoframe.lib;
/**
 * 各端可以共用的方法
 * 获取时间getCurrentTime
 * 休眠sleep
 * 读取文件readINIFile，读取一个字符变量和字符数组
 * 发送邮件等
 */

import java.util.Calendar;

import com.mail.MailUtils;
import com.report.entry.ReportEntry;
public class CommonLib {
	public static  String getCurrentTime() {

	   Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		int minute = ca.get(Calendar.MINUTE);
		int hour = ca.get(Calendar.HOUR);
		int second = ca.get(Calendar.SECOND);
		String currentTime=(String.valueOf(year) + "-" + String.valueOf(month + 1) + "-"
				+ String.valueOf(day) + "-" + String.valueOf(hour) + "-"
				+ String.valueOf(minute) + "-" + String.valueOf(second));
	
		
		return currentTime;
   }
  
   
	public static void sleep(int p_time){
		
		try {
			Thread.sleep(p_time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String readINIFile(String p_file,String p_section,String p_key){
		String iniValue=null;
		try{
			ReportEntry re=new ReportEntry();
			re.crateLog(p_file);
			iniValue=re.read(p_section, p_key);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return iniValue;
	}
	public static String[] readINFFile(String p_file,String p_section,String p_key) {
		String people = null;
		String[] peoples = null;
		try {
			ReportEntry re=new ReportEntry();
			re.crateLog(p_file);
			people = re.read(p_section, p_key);
			peoples = people.split(",");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return peoples;
	}
	public static void sendMail(String attachmentpath){
		
		MailUtils me=new MailUtils();
		me.sendmail(attachmentpath);
		
	}
	public static void sendMail(String[] users,String attachmentpath){
		
		MailUtils me=new MailUtils();
		me.sendmail(users,attachmentpath);
		
	}
}