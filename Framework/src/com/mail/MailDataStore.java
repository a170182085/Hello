package com.mail;

import com.autoframe.lib.CommonLib;
/**
 * 
 * @author 张蕾
 *读取邮件配置文件中的相关配置参数
 */
public class MailDataStore {
	
	public static final String mailconfigfilepaht="./mailconfig.ini";
	public static final String host=CommonLib.readINIFile(mailconfigfilepaht, "server", "host");
	public static final String port=CommonLib.readINIFile(mailconfigfilepaht, "server", "port");
	public static final String user=CommonLib.readINIFile(mailconfigfilepaht, "Authenticator", "user");
	public static final String pwd=CommonLib.readINIFile(mailconfigfilepaht, "Authenticator", "pwd");
	public static final String from=CommonLib.readINIFile(mailconfigfilepaht, "MailContent", "from");
	public static final String fromname=CommonLib.readINIFile(mailconfigfilepaht, "MailContent", "fromname");
	public static final String[] users = CommonLib.readINFFile(mailconfigfilepaht, "To", "to");
	public static final String subject="附件测试";
	public static final String message="自动化测试已经结束请查看测试邮件";
	public static final String attachmentpath="";
	public static final String attachmentname="";



}
