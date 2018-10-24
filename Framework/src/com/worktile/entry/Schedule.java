package com.worktile.entry;

public class Schedule {
	public String name;//日程名称
	public String location;//位置
	public String project;//项目
	public String[] people;//参与人
	public int startDay;//开始时间
	public int endDay;//结束时间
	public String repeat;//重复选项
	public String remind;//提醒
	Schedule(String p_name,String p_location,String p_project,String[] p_people,int p_examDay,String p_repeat,String p_remind){
		this.name  = p_name;
		this.location = p_location;
		this.project = p_project;
		this.people = p_people;
		this.startDay = p_examDay-1;
		this.endDay = p_examDay;
		this.repeat = p_repeat;
		this.remind = p_remind;
	}
}
