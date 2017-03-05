package shuhelper.ui;

import java.util.ArrayList;

//查询课程的线程
public class SearchTask  extends NewTask{
	private String s_classnum,s_time,s_teachername,s_score,s_school;
	private Boolean s_morning,s_noon,s_friday;
	private ArrayList<String[]> queryCourse = new ArrayList<String[]>();

	public SearchTask(String a,String b,String c,String d,String e,Boolean f,Boolean g,Boolean h)
	{
		s_classnum = a;
		s_time = b;
		s_teachername = c;
		s_score = d;
		s_school = e;
		s_morning = f;
		s_noon = g;
		s_friday = h;
		
	}
	@Override
	public ArrayList<String[]> GetPath() {
		return queryCourse;
	}

	@Override
	protected Object call() throws Exception {
		queryCourse = shuhelpapp.test.testSchedule(shuhelpapp.XK,s_classnum,s_time,s_teachername,s_score,s_school,s_morning,s_noon,s_friday);
		return null;
	}
	

}
