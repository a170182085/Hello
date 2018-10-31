package com.mail;

import java.io.File;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.autoframe.lib.DataStore;
import com.debug.log.DebugLogFile;


public class MailUtils {
	
	public static void  sendmail(String attachmentpath){
		
		  EmailAttachment attachment = new EmailAttachment();
		  File f=new File(attachmentpath);
		  if (!f.exists())
		  {
			  DebugLogFile.type("上传的附件不存在！");
			  return;
		  }
		  
		  attachment.setPath(attachmentpath);
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setName(f.getName());
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName(MailDataStore.host);
		  email.setSmtpPort(Integer.parseInt(MailDataStore.port));
		  email.setSSLOnConnect(true);
	      email.setAuthenticator(new DefaultAuthenticator(MailDataStore.user, MailDataStore.pwd));
		  try {
			email.addTo(MailDataStore.users);
			email.setFrom(MailDataStore.from, MailDataStore.fromname);
			email.setSubject(MailDataStore.subject);
			email.setMsg(MailDataStore.message);
			email.attach(attachment);
			email.send();
			DebugLogFile.type("发送邮件成功!");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			DebugLogFile.type("发送邮件异常！"+e.toString());
	
		}
		
		
	}
	public static void sendmail(String[] users,String attachmentpath){
		
		  EmailAttachment attachment = new EmailAttachment();
		  File f=new File(attachmentpath);
		  if (!f.exists())
		  {
			  DebugLogFile.type("上传的附件不存在！");
			  return;
		  }
		  
		  attachment.setPath(attachmentpath);
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setName(f.getName());
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName(MailDataStore.host);
		  email.setSmtpPort(Integer.parseInt(MailDataStore.port));
		  email.setSSLOnConnect(true);
	      email.setAuthenticator(new DefaultAuthenticator(MailDataStore.user, MailDataStore.pwd));
		  try {
			email.addTo(users);
			email.setFrom(MailDataStore.from, MailDataStore.fromname);
			email.setSubject(MailDataStore.subject);
			email.setMsg(MailDataStore.message);
			email.attach(attachment);
			email.send();
			DebugLogFile.type("发送邮件成功!");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			DebugLogFile.type("发送邮件异常！"+e.toString());
	
		}
		
		
	}

}
