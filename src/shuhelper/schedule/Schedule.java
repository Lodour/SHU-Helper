package shuhelper.schedule;

import java.util.ArrayList;


import shuhelper.web.XKWebAPI;

/**
 * @ClassName:Schedule
 * @Description:自动排课模块
 * @author yy
 * @date 2017/2/26
 */

public class Schedule {
	
	//static XKWebAPI XK = null;
	//保存当前查询到的课程
	public static ArrayList<String[]> courseTable=new ArrayList<String[]>();
	//存储课表排课状态，并检测冲突的类
	public static CourseTable conflict = new CourseTable();
	public static ArrayList<String[]> courseData=new ArrayList<String[]>();
	//是否课时冲突
	public String flagClassTime="";
	//是否重复加入
	public String flagCourse="";
	/**
	 * @Title: constraints
	 * @Description: 限制条件
	 * @param: ArrayList<String[]> arrayList,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass
	 * @return: void
	 * @throws Exception
	 */
	public void constraints(ArrayList<String[]> arrayList,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass) throws Exception{
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(noMorningClass==true){
		      	if(ss[5].contains("1-2")==true){
		      		arrayList.remove(j);
		      		v=v-1;
		      		j--;
		      	}
		    }  
		    if(noNoonClass==true){  
		      	if(ss[5].contains("5-6")==true){
		      		arrayList.remove(j);
		      		v=v-1;
		      		j--;
		      	}
		    }  
		    if(noFridayClass==true){  
		      	if(ss[5].contains("五")==true){
		      		arrayList.remove(j);
		      		v=v-1;
		      		j--;
		      	}
		    }
		    
		}
	}
	/**
	 * @Title: schedule
	 * @Description: 根据课程号和限制条件查询
	 * @param: String courseNo,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass
	 * @return: courseTable
	 * @throws Exception
	 */
	public ArrayList<String[]> schedule(XKWebAPI XK,String courseNo,String campus,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass) throws Exception{
		ArrayList<String[]> arrayList=null;
		arrayList = new ArrayList<String[]>();
		arrayList = XK.getAllCourseArray(courseNo);
		//TestSchedule.output(arrayList, "课程");
		int v=arrayList.size();
		for(int i=0;i<v;i++){
			String [] ss=arrayList.get(i);
			if(ss[9].contains(campus)==false){
				arrayList.remove(i);
				i--;v--;
			}
		}
		constraints(arrayList,noMorningClass,noNoonClass,noFridayClass);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
		}
		return courseTable;
	}
	/**
	 * @Title: scheTime
	 * @Description: 根据上课时间和限制条件查询
	 * @param: String courseNo,String classTime,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass
	 * @return: courseTable
	 * @throws Exception
	 */
	public ArrayList<String[]> scheTime(XKWebAPI XK,String courseNo,String classTime,String campus,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass) throws Exception{
		ArrayList<String[]> arrayList=new ArrayList<String[]>();
		arrayList=XK.getAllCourseArray(courseNo);
		
		int v=arrayList.size();
		for(int i=0;i<v;i++){
			String [] ss=arrayList.get(i);
			if(ss[5].contains(classTime)==false){
				arrayList.remove(i);
				i--;v--;
			}
		}
		for(int i=0;i<v;i++){
			String [] ss=arrayList.get(i);
			if(ss[9].contains(campus)==false){
				arrayList.remove(i);
				i--;v--;
			}
		}
		constraints(arrayList,noMorningClass,noNoonClass,noFridayClass);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			//s++;
			courseTable.add(arrayList.get(j));
		}
		
		return courseTable;
	}
	/**
	 * @Title: schedTeacher
	 * @Description: 根据教师名和限制条件查询
	 * @param: String courseNo,String teacherName,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass
	 * @return: courseTable 课程号, 课程名, 学分, 教师号, 教师名, 上课时间, 上课地点, 容量, 人数, 校区, 选课限制, 答疑时间, 答疑地点
	 * @throws Exception 
	 */
	public ArrayList<String[]> schedTeacher(XKWebAPI XK,String courseNo,String teacherName,String campus,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass) throws Exception{
		
		ArrayList<String[]> arrayList = null;
		//f.getCourseNo();
		arrayList = new ArrayList<String[]>();
		arrayList=XK.getAllCourseArray(courseNo);
		
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[4].contains(teacherName)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[9].contains(campus)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		constraints(arrayList,noMorningClass,noNoonClass,noFridayClass);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
			
		}
		return courseTable;
	}
	
	/**
	 * @Title: schedTeacher
	 * @Description: 根据上课时间、教师名和限制条件查询
	 * @param: String courseNo,String teacherName,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass
	 * @return: courseTable 课程号, 课程名, 学分, 教师号, 教师名, 上课时间, 上课地点, 容量, 人数, 校区, 选课限制, 答疑时间, 答疑地点
	 * @throws Exception 
	 */
	public ArrayList<String[]> schedule(XKWebAPI XK,String courseNo,String classTime,String teacherName,String campus,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass) throws Exception{
		
		ArrayList<String[]> arrayList = null;
		//f.getCourseNo();
		arrayList = new ArrayList<String[]>();
		arrayList=XK.getAllCourseArray(courseNo);
		
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[4].contains(teacherName)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[5].contains(classTime)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[9].contains(campus)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		constraints(arrayList,noMorningClass,noNoonClass,noFridayClass);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
			
		}
		return courseTable;
	}
	/**
	 * @Title: schedule
	 * @Description: 全部条件精准查询
	 * @param: String courseNo,String classTime,String teacherName,String credit,String campus,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass
	 * @return: courseTable
	 * @throws Exception 
	 */
	public ArrayList<String[]> schedule(XKWebAPI XK,String courseNo,String classTime,String teacherName,String credit,String campus,Boolean noMorningClass,Boolean noNoonClass,Boolean noFridayClass) throws Exception{
		
		ArrayList<String[]> arrayList = null;
		arrayList = new ArrayList<String[]>();
		arrayList = XK.getAllCourseArray(courseNo);
		int v=arrayList.size();
	
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[2].contains(credit)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[4].contains(teacherName)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[5].contains(classTime)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[9].contains(campus)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		constraints(arrayList,noMorningClass,noNoonClass,noFridayClass);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
		}
		return courseTable;
	}
	/**
	 * @Title: schedule
	 * @Description: 提交选课结果到选课系统
	 * @param: arrayList,int s
	 * @return: void
	 * @throws Exception 
	 */
	public static void elective(XKWebAPI XK,ArrayList<String[]> arrayList,int s) throws Exception{
		
		for(int i=0;i<s;i++){
			XK.getEnrollStatus();
			XK.enrollCourse((String)arrayList.get(i)[0], (String)arrayList.get(i)[3]);
		}	
		
	}
	/**
	 * @Title: schedule
	 * @Description: 退课
	 * @param: arrayList,int s
	 * @return: void
	 * @throws Exception 
	 */
	public static void DropCourse(XKWebAPI XK,ArrayList<String[]> arrayList,int s) throws Exception{
		String data=new String();
		for(int i=0;i<s;i++){
			XK.getEnrollStatus();
			System.out.println(arrayList.get(i)[0]+"   "+arrayList.get(i)[1]);
			data=XK.returnCourse((String)arrayList.get(i)[0], (String)arrayList.get(i)[1]);
			System.out.println(data);
		}
	}
	/**
	 * @Title: saveEleCourse
	 * @Description: 保存待选课程信息
	 * @param: ArrayList<String[]> arrayList,int row,ArrayList<String[]> courses
	 * @return: ArrayList<String[]> 课程号, 课程名, 学分, 教师号, 教师名, 上课时间, 上课地点, 容量, 人数, 校区, 选课限制
	 * @throws Exception 
	 */
	public ArrayList<String[]> saveEleCourse(ArrayList<String[]> arrayList,int row,ArrayList<String[]> courses){
		// TODO Auto-generated method stub
		String [] str = new String[13];
		String [] cour = new String[11];
		flagClassTime="";
		flagCourse="";
		//获取选中行的课程信息
		str = arrayList.get(row);
		String []source = new String[11];
		for(int i=0;i<courses.size();i++){
			source=courses.get(i);
			if(str[0].equals(source[0])&&str[3].equals(source[3])){
				flagCourse="重复加入同一门课";
				return courses;
			}
		}
		flagClassTime=conflict.pickClassTime(str[5]);
		if(flagClassTime.equals("课时冲突！")){
			return courses;
		}
		conflict.reClassTime(str[5]);
		for(int i=0;i<11;i++){
			cour[i]=str[i];
		}
		
		courses.add(cour); 
		return courses;
	}

	public ArrayList<String[]> getCourseTable(){
		return courseTable;
	}
	
	public int courseSize(){
		return courseTable.size();
	}
	/**
	 * @Title: selectedCourse
	 * @Description: 已选课程信息
	 * @param: null
	 * @return: ArrayList<String[]> 课程号, 课程名, 教师号, 教师名, 学分, 上课时间, 上课地点, 校区
	 * @throws Exception 
	 */
	public ArrayList<String[]> selectedCourse(XKWebAPI XK){
		ArrayList<String[]> dropCourses = new ArrayList<String[]>();
		ArrayList<String[]> Courses = new ArrayList<String[]>();
		try {
			dropCourses=XK.getCourseTableArrayList();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String [][] str = new String[dropCourses.size()][8];
		String [][] cour = new String[dropCourses.size()][8];
		//获取选中行的课程信息
		for(int i=0;i<dropCourses.size();i++){
			str[i] = dropCourses.get(i);
			for(int j=0;j<8;j++){
				cour[i][j]=str[i][j];
			}
			Courses.add(cour[i]);
		}
		
		Object [][]data=new Object[Courses.size()][8];
		for(int i=0;i<Courses.size();i++){
			data[i]=Courses.get(i);
		}
		return Courses;
	}
	/**
	 * @Title: dropCourse
	 * @Description: 待退退课
	 * @param: ArrayList<String[]> arrayList已选课程 int row,ArrayList<String[]> dropCourse
	 * @return: ArrayList<String[] 课程号, 课程名, 教师号, 教师名, 学分, 上课时间,
	 * 课程号, 课程名, 教师号, 教师名, 学分, 上课时间,
	 * @throws Exception 
	 */
	public ArrayList<String[]> dropCourse(ArrayList<String[]> arrayList,int row,ArrayList<String[]> dropCourse){
		
		String [] string = new String[10];
		String [] strings = new String[2];
		
		string=arrayList.get(row);
		
		for(int i=0;i<6;i++){
			strings[i]=string[i];
		}
		
		dropCourse.add(string);
		return dropCourse;
	}
	/**
	 * @Title: initCourseStatus
	 * @Description: 初始化课表排课状态
	 * @param: ArrayList<String[]> selectedCourses
	 * @return: void
	 * @throws Exception 
	 */
	public static void initCourseStatus(ArrayList<String[]> selectedCourses) throws Exception{
		String [][]str=new String[selectedCourses.size()][8];
		String string = "";
		for(int i=0;i<selectedCourses.size();i++){
			str[i]=selectedCourses.get(i);
			string=string+str[i][5];
		}
		conflict.pickClassTime(string);
	}
	
	public ArrayList<String[]> testSchedule(XKWebAPI XK,String courseNo,String classTime,String teacherName,String credit,String campus,boolean noMorningClass,boolean noNoonClass,boolean noFridayClass) throws Exception {
		//若用户只输入课程号
		if(!courseNo.equals("")&&classTime.equals("")&&teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=schedule(XK,courseNo,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			TestSchedule.data=TestSchedule.getData(courseData.size(), courseData);
		}
		//如用户输入课程号和课程时间
		if(!courseNo.equals("")&&!classTime.equals("")&&teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=scheTime(XK,courseNo,classTime,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			TestSchedule.data=TestSchedule.getData(courseData.size(), courseData);
		}
		//若输入课程号和教师号
		if(!courseNo.equals("")&&classTime.equals("")&&!teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=schedTeacher(XK,courseNo,teacherName,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			TestSchedule.data=TestSchedule.getData(courseData.size(), courseData);
		}
		if(!courseNo.equals("")&&!classTime.equals("")&&!teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=schedule(XK,courseNo,classTime,teacherName,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			TestSchedule.data=TestSchedule.getData(courseData.size(), courseData);
		}
		//输入全部查询条件
		if(!courseNo.equals("")&&!classTime.equals("")&&!teacherName.equals("")&&!credit.equals("")){
			//courseData――查询到的课程
			courseData=schedule(XK,courseNo,classTime,teacherName,credit,campus,noMorningClass,noNoonClass,noFridayClass);
			//表格数据
			//data=new Object[courseData.size()][13];
			TestSchedule.data=TestSchedule.getData(courseData.size(), courseData);
		}
		//output(courseData, "课程");
		return courseData;
	}
	public static void main(String[] args) throws Exception {
		XKWebAPI xk=new XKWebAPI();
		//创建测试对象
		TestSchedule schedule=new TestSchedule(xk);
		//窗口初始化，登录选课系统
		schedule.sfmain();
		//查询课程，对应按钮‘查询’
		schedule.search();
		//保存待选课程，对应按钮‘保存’
		schedule.saveCourseData();
		//查看选课情况，对应按钮‘选课情况’
		schedule.selectedCourses();
		//选课，对应按钮‘选课’
		schedule.selectCourse();
		//退课，对应按钮‘退课’
		schedule.returnCourse();
	}
}
