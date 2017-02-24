package shuhelper.scheduing;


import java.util.ArrayList;
import java.util.Scanner;

import shuhelper.scheduing.Sframe;
import shuhelper.web.WebAPI;
import shuhelper.web.XKWebAPI;

public class Schedule {
	
	static Sframe f=new Sframe();
	static XKWebAPI XK = null;
	static Object [][]data = null;
	static ArrayList<String[]> courseTable=new ArrayList<String[]>();
	private static Scanner in = new Scanner(System.in);
	
	public static void constraints(ArrayList<String[]> arrayList) throws Exception{
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(f.noMorningClass.isSelected()==true){
		      	if(ss[5].contains("1-2")==true){
		      		arrayList.remove(j);
		      		v=v-1;
		      		j--;
		      	}
		    }  
		    if(f.noNoonClass.isSelected()==true){  
		      	if(ss[5].contains("5-6")==true){
		      		arrayList.remove(j);
		      		v=v-1;
		      		j--;
		      	}
		    }  
		    if(f.noFridayClass.isSelected()==true){  
		      	if(ss[5].contains("��")==true){
		      		arrayList.remove(j);
		      		v=v-1;
		      		j--;
		      	}
		    }
		    
		}
	}
	/**
	 * @Title: schedule
	 * @Description: ���ݿγ̺ź�����������ѯ
	 * @param: courseNo
	 * @return: arrayList
	 * @throws Exception
	 */
	public static ArrayList<String[]> schedule(String courseNo) throws Exception{
		ArrayList<String[]> arrayList=null;
		f.getCourseNo();
		arrayList = new ArrayList<String[]>();
		arrayList = getCourse(courseNo);
		constraints(arrayList);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
		}
		return courseTable;
	}
	/**
	 * @Title: schedule
	 * @Description: ���ݿγ������Ͽ�ʱ���Ƽ��γ�
	 * @param: no,courseName
	 * @return: ArrayList<String[]> �γ̺�, �γ���, ѧ��, ��ʦ��, ��ʦ��, �Ͽ�ʱ��, �Ͽεص�, ����, ����, У��, ѡ������, ����ʱ��, ���ɵص�
	 * @throws Exception 
	 */
	/*public ArrayList<String[]> scheName(String courseName) throws Exception{
		ArrayList<String[]> arrayList = null;
		f.getClassTime();
		arrayList = new ArrayList<String[]>();
		arrayList = getCourse("0830");
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[1].contains(courseName)==false){
				arrayList.remove(j);
				v--;
				j--;
			}
		}
		constraints(arrayList);
		//int s=0;
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			//s++;
			courseTable.add(arrayList.get(j));
		}
		//data=new Object[s][13];
		//data=getData(s, arrayList);
		return courseTable;
	}*/
	/**
	 * @Title: search
	 * @Description: �����Ͽ�ʱ���ѯ
	 * @param: classTime
	 * @return: arrayList
	 * @throws Exception
	 */
	public ArrayList<String[]> scheTime(String courseNo,String classTime) throws Exception{
		ArrayList<String[]> arrayList=new ArrayList<String[]>();
		arrayList=getCourse(courseNo);
		int v=arrayList.size();
		for(int i=0;i<v;i++){
			String [] ss=arrayList.get(i);
			if(ss[5].contains(classTime)==false){
				arrayList.remove(i);
				i--;v--;
			}
		}
		constraints(arrayList);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			//s++;
			courseTable.add(arrayList.get(j));
		}
		
		return courseTable;
	}
	/**
	 * @Title: schedule
	 * @Description: ���ݽ�ʦ���Ƽ��γ�
	 * @param: no1,no2,teacherName
	 * @return: courseTable
	 * @return: ArrayList<String[]> �γ̺�, �γ���, ѧ��, ��ʦ��, ��ʦ��, �Ͽ�ʱ��, �Ͽεص�, ����, ����, У��, ѡ������, ����ʱ��, ���ɵص�
	 * @throws Exception 
	 */
	public ArrayList<String[]> schedTeacher(String courseNo,String teacherName) throws Exception{
		
		ArrayList<String[]> arrayList = null;
		//f.getCourseNo();
		arrayList = new ArrayList<String[]>();
		arrayList=getCourse(courseNo);
		
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[4].contains(teacherName)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		constraints(arrayList);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
			
		}
		return courseTable;
	}
	
	public static Object [][] getData(int s,ArrayList<String[]> courseTable){
		data=new Object[s][13];
		for(int i=0;i<s;i++){
			data[i]=courseTable.get(i);
		}
		return data;
	}
	/**
	 * @Title: schedule
	 * @Description: ���ݿγ������Ͽ�ʱ���Ƽ��γ�
	 * @param: courseName,classTime
	 * @return: ArrayList<String[]> �γ̺�, �γ���, ѧ��, ��ʦ��, ��ʦ��, �Ͽ�ʱ��, �Ͽεص�, ����, ����, У��, ѡ������, ����ʱ��, ���ɵص�
	 * @throws Exception 
	 */
	/*public ArrayList<String[]> schedule(String courseName,String classTime) throws Exception{
		ArrayList<String[]> arrayList = null;
		arrayList = new ArrayList<String[]>();
		arrayList=scheName(1, courseName);
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[5].contains(classTime)==false){
				arrayList.remove(j);
				v--;
				j--;
			}
		}
		
		constraints(arrayList);
		for(int j=0;j<arrayList.size();j++){
			//s++;
			courseTable.add(arrayList.get(j));
		}
		
		return courseTable;
	}*/

	/**
	 * @Title: schedule
	 * @Description: ���ݿγ̺š��Ͽ�ʱ��ͽ�ʦ���Ƽ��γ�
	 * @param: null
	 * @return: void
	 * @return: ArrayList<String[]> �γ̺�, �γ���, ѧ��, ��ʦ��, ��ʦ��, �Ͽ�ʱ��, �Ͽεص�, ����, ����, У��, ѡ������, ����ʱ��, ���ɵص�
	 * @throws Exception 
	 */
	/*public ArrayList<String[]> schedule(int no,String classTime,String teacherName) throws Exception{
		
		ArrayList<String[]> arrayList = null;
		arrayList = new ArrayList<String[]>();
		arrayList = scheTeacher(teacherName);
		int v=arrayList.size();
		for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[5].contains(classTime)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}
		constraints(arrayList);
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
		}
		return courseTable;
	}*/
	public ArrayList<String[]> schedule(String courseNo,String classTime,String teacherName,String credit,String campus) throws Exception{
		
		ArrayList<String[]> arrayList = null;
		arrayList = new ArrayList<String[]>();
		arrayList = getCourse(courseNo);
		int v=arrayList.size();
		/*for(int j=0;j<v;j++){
			String [] ss=arrayList.get(j);
			if(ss[1].contains(courseName)==false){
				arrayList.remove(j);
				j--;v--;
			}
		}*/
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
		constraints(arrayList);
		courseTable=new ArrayList<String[]>();
		for(int j=0;j<arrayList.size();j++){
			courseTable.add(arrayList.get(j));
		}
		
		return courseTable;
	}
	//�ύѡ�ν����ѡ��ϵͳ
	public static void elective(ArrayList<String[]> arrayList,int s) throws Exception{
		String data=new String();
		for(int i=0;i<s;i++){
			XK.getEnrollStatus();
			System.out.println(arrayList.get(i)[0]+"   "+arrayList.get(i)[1]);
			data=XK.enrollCourse((String)arrayList.get(i)[0], (String)arrayList.get(i)[1]);
			System.out.println(data);
		}		
	}
	//�˿�
	public static void DropCourse(ArrayList<String[]> arrayList,int s) throws Exception{
		String data=new String();
		for(int i=0;i<s;i++){
			XK.getEnrollStatus();
			System.out.println(arrayList.get(i)[0]+"   "+arrayList.get(i)[1]);
			data=XK.returnCourse((String)arrayList.get(i)[0], (String)arrayList.get(i)[1]);
			System.out.println(data);
		}
	}
	public static ArrayList<String[]> getCourse(String courseNo) throws Exception{
		return XK.getAllCourseArray(courseNo);
	}
	public String getClassTime(){
	
		return f.text[1].getText();
	}
	public static void output(ArrayList<String[]> arrayList, String title) {
		System.out.println("================================");
		System.out.println(title);
		System.out.println("================================");
		for (String[] row : arrayList) {
			for (String col : row)
				System.out.print(col + "\t");
			System.out.println();
		}
		System.out.printf("-------- Total %d row(s) --------\n", arrayList.size());
	}
	private static boolean login(WebAPI web) throws Exception {
		// ��ȡ��֤��ͼƬ
		String validatePath = web.getCaptcha();
		System.out.println("��֤��ͼƬ�洢��: " + validatePath);
		
		// ��¼����
		System.out.print("ѧ��: ");
		String username = in.next();;
		System.out.print("����: ");
		String password = in.next();;
		System.out.print("��֤��: ");
		String validate = in.next();
		
		// ���Ե�¼
		String res = web.login(username, password, validate);
		System.out.println("��¼���: " + res);
		
		// ���ص�¼״̬
		return web.isLogin();
	}
	public void testXKWebAPI() throws Exception {
		// ʵ���� XKWebAPI
		XK=new XKWebAPI();
	
		// �鿴ѧ��
		String[] termInfo = XK.getTermInfo();
		for (int i = 0; i < termInfo.length; i++) {
			System.out.printf("[%d] %s\n", i, termInfo[i]);
		}
	
		// ѡ��
		System.out.print("��ѡ��ѧ�ڱ��: ");
		int termNo = in.nextInt();
		XK.setTerm(termNo);
	
		// ��¼
		if (!login(XK)) return;
	
		//ArrayList<String[]> courseTable = XK.getCourseTableArrayList();					
		//output(courseTable, "��ѡ�γ�");
	
		// ѡ������
		//ArrayList<String[]> enrollRank = XK.getEnrollRankArrayList();
		//output(enrollRank, "ѡ������");
	
		// ��ѯ����"0830"�γ�
		//ArrayList<String[]> queryCourse = XK.getAllCourseArray("0830");
		//output(queryCourse, "0830�γ�");
		
	}
	public static int courseSize(){
		return courseTable.size();
	}
	public static Object [][] testSchedule() throws Exception {
		
		ArrayList<String[]> coursedata=schedule(f.getCourseNo());
		data=getData(coursedata.size(), coursedata);
		output(coursedata, f.getCourseNo());
		return data;
	}
	public void sfmain() throws Exception {
		f.Init();
		f.addLabel();
		f.addCheckBox();
		f.addButton();
		f.addComboBox();
		f.addText();

		try {
			testXKWebAPI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//testSchedule();
		f.myEvent();
	}
	public static void main(String[] args) throws Exception {
		Schedule schedule=new Schedule();
		schedule.sfmain();
	}
}
