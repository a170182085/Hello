package com.autoframe.lib;

import java.io.File;

//与外部文件的一个接口
public class DataStore {	
	public static String D_Username=CommonLib.readINIFile("./config.ini", "base", "userName");
	public static String D_Password=CommonLib.readINIFile("./config.ini", "base", "password");
	public static String D_URL=CommonLib.readINIFile("./config.ini", "base", "URL");
	public static String D_Browser=CommonLib.readINIFile("./config.ini", "browser", "useBrowser");
	public static int D_Wait_ShortTime=Integer.parseInt(CommonLib.readINIFile("./config.ini", "waitTime", "shortTime"));
	public static int D_Wait_MediumTime=Integer.parseInt(CommonLib.readINIFile("./config.ini", "waitTime", "mediumTime"));
	public static int D_Wait_LongTime=Integer.parseInt(CommonLib.readINIFile("./config.ini", "waitTime", "longTime"));
	public static String D_Schedule_Name=CommonLib.readINIFile("./config.ini", "Schedule", "name");//日程名称
	public static String D_Schedule_location=CommonLib.readINIFile("./config.ini", "Schedule", "location");//位置
	public static String D_Schedule_project=CommonLib.readINIFile("./config.ini", "Schedule", "project");//项目
	public static String[] D_Schedule_peoples=CommonLib.readINFFile("./config.ini", "Schedule", "peoples");//参与人
	public static int D_Schedule_startDay=Integer.parseInt(CommonLib.readINIFile("./config.ini", "Schedule", "startDay"));//开始时间
	public static int D_Schedule_endDay=Integer.parseInt(CommonLib.readINIFile("./config.ini", "Schedule", "endDay"));//结束时间
	public static String D_Schedule_repeat=CommonLib.readINIFile("./config.ini", "Schedule", "repeat");//重复选项
	public static String D_Schedule_remind=CommonLib.readINIFile("./config.ini", "Schedule", "remind");//提醒
	public static String D_User_name=CommonLib.readINIFile("./config.ini", "userInfo", "name");//展示名
	public static String D_User_title=CommonLib.readINIFile("./config.ini", "userInfo", "title");
	public static String D_User_department=CommonLib.readINIFile("./config.ini", "userInfo", "department");
	public static String D_User_autograph=CommonLib.readINIFile("./config.ini", "userInfo", "autograph");
	public static String D_LogPath="./Log/loggingResults";
	public static String D_ScreenShotPath=System.getProperty("user.dir")+File.separator+"Log"+File.separator+"loggingResults"+File.separator+"screenshots";
	public static String D_DebugLogger="DebugLogger";
}
