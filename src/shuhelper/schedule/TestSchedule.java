package shuhelper.schedule;


import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import shuhelper.web.WebAPI;
import shuhelper.web.XKWebAPI;

@SuppressWarnings("serial")
public class TestSchedule extends JFrame{
	
	//保存待选课程
	ArrayList<String[]> courses = new ArrayList<String[]>();
	//保存待退课程
	ArrayList<String[]> dropCourses = new ArrayList<String[]>();
	//表格数据
	static Object [][]data=null;
	
	//下面数据都是窗口变量，可以不看
	private static Scanner in = new Scanner(System.in);
	public Schedule sframe = new Schedule();
	static JFrame frame;
	static JTextField []text=new JTextField[5];
	mTable table = new mTable();
	JTable table1 = new JTable();
	JLabel []labels = new JLabel[9];
	JScrollPane scrollPane = new JScrollPane();
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	static Checkbox noMorningClass=new Checkbox("不排早课");
	static Checkbox noNoonClass=new Checkbox("不排午课");
	static Checkbox noFridayClass=new Checkbox("不排周五课");
	int tableWidth=0;
	int tableHeigth=0;
	
	public ArrayList<String[]> courseData = new ArrayList<String[]>();
	String [] courseNo= new String[4];
	String [] classTime=new String[4];
	String [] teacherName=new String[4];
	@SuppressWarnings("rawtypes")
	static
	JComboBox campus = null;
	public static XKWebAPI XK;
	
	public TestSchedule(XKWebAPI xk) throws Exception{
		//XK=new XKWebAPI();
		XK=xk;
	}
	/**
	 * @Title: search
	 * @Description: 查询课程
	 * @param: null
	 * @return: void 
	 */
	public void search(){
		data=new Object[sframe.courseSize()][13];
		button1.addActionListener(new ActionListener()
	    {
	          public void actionPerformed(ActionEvent e)
	          { 	   	
	       	   try {
       			data=new Object[sframe.courseSize()][13];
	              	//scrollPane.remove(table);
       			//testSchedule()――查询课程
	            courseData=testSchedule(getCourseNo(),getClassTime(),getTeacherName(),getCredit(),(String)campus.getSelectedItem(),noMorningClass.getState(),noNoonClass.getState(),noFridayClass.getState());
	            //courseData――查询到的课程(arrayLiat)
	       		drawTable(data,sframe.courseSize());
	       		//Schedule.courseSize()――当前查询到的课程数量
	       	} catch (Exception e1) {
	       		// TODO Auto-generated catch block
	      		e1.printStackTrace();
	       	}
	          } 
		});
	}
	/**
	 * @Title: clearCourseData
	 * @Description: 清除待选课程
	 * @param: null
	 * @return: void 
	 */
	public void clearCourseData(){
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<4;i++){
					text[i].setText(null);
				}
				//慎用
				courses.clear();
			}
		});
	}
	/**
	 * @Title: saveCourseData
	 * @Description: 添加到待选课程
	 * @param: null
	 * @return: void 
	 */
	public void saveCourseData(){
		 button3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//保存待选课程
					for(int i=0;i<courseData.size();i++){
						if((boolean) table.getValueAt(i, 13)){
							//添加待选课程 保存在courses里面（注意course不是局部参数）
							courses=sframe.saveEleCourse(courseData, i, courses);
							//courseData――查询到的课程 ， i――选中行行号
						}
					}
					
					output(courses, "待选课程");
				}
			});
	}
	/**
	 * @Title: selectedCourses
	 * @Description: 查看已选课程
	 * @param: null
	 * @return: void 
	 */
	public void selectedCourses(){
		//查看已选课程的表格
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//选课情况
				ArrayList<String[]> arrayList=new ArrayList<String[]>();
				//selectedCourse() ―― 获取已选课程信息
				arrayList=sframe.selectedCourse(XK);
				//移除前一次查询的表格
				scrollPane.remove(table);
				scrollPane.remove(table1);
				//将arrayList转化为Object类，以便将数据加入table,若不是用JTable,可用自己的方式处理数据
				Object [][]data=new Object[arrayList.size()][8];
				for(int i=0;i<arrayList.size();i++){
					data[i]=arrayList.get(i);
				}
				//列名
				String []cloName={"课程号", "课程名", "教师号", "教师名", "学分", "上课时间", 
						"上课地点","校区","选择"};
				
				DefaultTableModel tabDemo = (DefaultTableModel) table1.getModel();
				tabDemo.setRowCount(0);
				tabDemo.setColumnIdentifiers(cloName);
				JCheckBox jc = new JCheckBox();
				//在最后一列加入复选框组件
				table1.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(jc));
				//将数据加入表格
				for(int i=0;i<arrayList.size();i++){
					Object[] objdata = { data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],
					 data[i][5],data[i][6],data[i][7],false};
					tabDemo.addRow(objdata);
				}
				//设置表格行数
				tabDemo.setRowCount(arrayList.size());
				table1.setRowHeight(25);
				//字体居中
				DefaultTableCellRenderer font = new DefaultTableCellRenderer();// 设置table内容居中
				font.setHorizontalAlignment(JLabel.CENTER);
				table1.setDefaultRenderer(Object.class, font);
				//调整表格列宽
				fitTableColumns(table1);
				scrollPane.setViewportView(table1);
				scrollPane.repaint();
				scrollPane.setBounds(20, 280, tableWidth+20, tableHeigth+30);
				frame.add(scrollPane);
			}
		});
	}
	/**
	 * @Title: selectCourse
	 * @Description: 选课
	 * @param: null
	 * @return: void 
	 */
	public void selectCourse(){
		button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//选课
				//courses――保存待选课程的arraylist
				String []str=new String[11];
				for(int i=0;i<courses.size();i++){
					str=courses.get(i);
					try {
						//选课
						XK.getEnrollStatus();
						String flag=XK.enrollCourse(str[0], str[3]);
						System.out.println(flag);
						if(flag=="OK"){
							//更新排课状态――pickClassTime(上课时间)
							Schedule.conflict.pickClassTime(str[5]);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				//output(sframe.eleCourse(courses),"选课");
			}
		});
	}
	/**
	 * @Title: returnCourse
	 * @Description: 退课
	 * @param: null
	 * @return: void 
	 */
	public void returnCourse(){
	 	 
	 button6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//退课
				ArrayList<String[]> arrayList = new ArrayList<String[]>();
				String []str=new String[8];
				
				//获取已选课程
				arrayList=sframe.selectedCourse(XK);
				
				for(int i=0;i<arrayList.size();i++){
					if((boolean) table1.getValueAt(i, 8)){
						str=arrayList.get(i);
						try {
							//获取选课状态
							XK.getEnrollStatus();
							//退课――returnCourse(课程号，教师号)
							String flag = XK.returnCourse(str[0], str[2]);
							System.out.println(flag);
							if(flag=="OK"){
								//清除退课课程时间排课的状态――reClassTime(上课时间)
								Schedule.conflict.reClassTime(str[5]);
							}
						} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}
				}
			}
		});
	}
	
	/**
	 * @Title: testSchedule
	 * @Description: 查询课程
	 * @param: String courseNo,String teacherName,String classTime,boolean noMorningClass,boolean noNoonClass,boolean noFridayClass
	 * @return: 表格数据 :课程号, 课程名, 学分, 教师号, 教师名, 上课时间, 上课地点, 容量, 人数, 校区, 选课限制, 答疑时间, 答疑地点
	 * @throws Exception 
	 */
	//测试查询课程功能
	//ArrayList<String[]> arrayList = new ArrayList<String[]>();
	public ArrayList<String[]> testSchedule(String courseNo,String classTime,String teacherName,String credit,String campus,boolean noMorningClass,boolean noNoonClass,boolean noFridayClass) throws Exception {
		//若用户只输入课程号
		//System.out.println(XK.isLogin());
		if(!courseNo.equals("")&&classTime.equals("")&&teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=sframe.schedule(XK,courseNo,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			data=getData(courseData.size(), courseData);
		}
		//如用户输入课程号和课程时间
		if(!courseNo.equals("")&&!classTime.equals("")&&teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=sframe.scheTime(XK,courseNo,classTime,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			data=getData(courseData.size(), courseData);
		}
		//若输入课程号和教师号
		if(!courseNo.equals("")&&classTime.equals("")&&!teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=sframe.schedTeacher(XK,courseNo,teacherName,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			data=getData(courseData.size(), courseData);
		}
		if(!courseNo.equals("")&&!classTime.equals("")&&!teacherName.equals("")&&credit.equals("")){
			//courseData――查询到的课程
			courseData=sframe.schedule(XK,courseNo,classTime,teacherName,campus,noMorningClass,noNoonClass,noFridayClass);
			//data=new Object[courseData.size()][13];
			data=getData(courseData.size(), courseData);
		}
		//输入全部查询条件
		if(!courseNo.equals("")&&!classTime.equals("")&&!teacherName.equals("")&&!credit.equals("")){
			//courseData――查询到的课程
			courseData=sframe.schedule(XK,courseNo,classTime,teacherName,credit,campus,noMorningClass,noNoonClass,noFridayClass);
			//表格数据
			//data=new Object[courseData.size()][13];
			data=getData(courseData.size(), courseData);
		}
		System.out.println(XK.isLogin());
		output(courseData, "课程");
		return courseData;
	}
	public void Init() throws Exception{
		frame = new JFrame("排课模块");
		frame.setLayout(null);
		frame.setSize(1280, 960);
		frame.setBounds(100,200,960,700);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addButton(){
		button1 = new JButton("查询");
		button2 = new JButton("清除");
		button3 = new JButton("保存");
		button4 = new JButton("选课情况");
		button5 = new JButton("选课");
		button6 = new JButton("退课");
		button1.setBounds(40, 160, 60, 30);
		button2.setBounds(130, 160, 60, 30);
		button3.setBounds(220, 160, 60, 30);
		button4.setBounds(310, 160, 90, 30);
		button5.setBounds(430, 160, 60, 30);
		button6.setBounds(520, 160, 60, 30);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.add(button5);
		frame.add(button6);
	}
	public void addText(){
		for(int i=0;i<4;i++){
			text[i]=new JTextField(null);
			frame.add(text[i]);	
			text[i].setVisible(true);
			text[i].setLayout(null);
		}
		text[0].setBounds(90, 20, 120, 30);//课程号
		text[1].setBounds(300, 20, 120, 30);//上课时间
		text[2].setBounds(90, 60, 120, 30);//教师名
		text[3].setBounds(300, 60, 120, 30);//学分
	}
	public void addLabel(){
		labels[0]=new JLabel("课程号");
		frame.add(labels[0]);
		labels[0].setBounds(30, 20, 50 , 20);
		labels[1]=new JLabel("上课时间");
		frame.add(labels[1]);
		labels[1].setBounds(225, 20, 60 , 20);
		labels[2]=new JLabel("教师名");
		frame.add(labels[2]);
		labels[2].setBounds(30, 65, 50 , 20);
		labels[4]=new JLabel("学分");
		frame.add(labels[4]);
		labels[4].setBounds(225, 65, 50 , 20);
		labels[5]=new JLabel("校区");
		frame.add(labels[5]);
		labels[5].setBounds(30, 105, 50 , 20);
		labels[3]=new JLabel("限制条件");
		frame.add(labels[3]);
		labels[3].setBounds(450, 10, 100 , 20);
	}
	//下拉框――校区选择
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addComboBox(){
		String str1[]={"本部","嘉定","延长","全部"};
		campus = new JComboBox(str1);
		frame.add(campus);
		campus.setVisible(true);
		campus.setBounds(90, 100, 120, 30);
		
	}
	public static String getCourseNo(){
		return text[0].getText();
	}
	public static String getClassTime(){
	
		return text[1].getText();
	}
	
	public static String getTeacherName(){
		
		return text[2].getText();
	}
	public static String getCredit(){
		
		return text[3].getText();
	}
	public static String getCampus(){
		
		return (String) campus.getSelectedItem();
	}
	//选课限制条件复选框
	public void addCheckBox(){
		
		noMorningClass.setBounds(500,40,120,30);
		noNoonClass.setBounds(500,80,120,30);
		noFridayClass.setBounds(500,120,120,30);
		
		frame.add(noMorningClass);
		frame.add(noNoonClass);
		frame.add(noFridayClass);
	}
	/**
	 * @Title: drawTable
	 * @Description: 画查询课程的表
	 * @param: Object [][]data(数据),int s(行数)
	 * @return: void
	 * @throws Exception 
	 */
	public void drawTable(Object [][]data,int s) throws Exception{
				
		String []cloName={"课程号", "课程名", "学分", "教师号", "教师名", "上课时间", 
						"上课地点", "容量", "人数", "校区", "选课限制", "答疑时间", "答疑地点","选择"};
		//scrollPane.removeAll();
		DefaultTableModel tabDemo = (DefaultTableModel) table.getModel();
		tabDemo.setRowCount(0);
		tabDemo.setColumnIdentifiers(cloName);
		JCheckBox jc = new JCheckBox();
        table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jc));
		for(int i=0;i<s;i++){
			
			 Object[] objdata = { data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],
					 data[i][5],data[i][6],data[i][7],data[i][8],data[i][9],data[i][10],
					 data[i][11],data[i][12],false};
			 tabDemo.addRow(objdata);
			}
		tabDemo.setRowCount(s);
		table.updateUI();
		table.setRowHeight(25);
		DefaultTableCellRenderer font = new DefaultTableCellRenderer();// 设置table内容居中
		font.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, font);
		fitTableColumns(table);
		scrollPane.setViewportView(table);
		scrollPane.repaint();
		scrollPane.setBounds(20, 280, tableWidth, tableHeigth);
		frame.validate();
		frame.add(scrollPane);
	}
	/**
	 * @Title: fitTableColumns
	 * @Description: 调节列宽
	 * @param: JTable myTable
	 * @return: void
	 * @throws Exception 
	 */
	public void fitTableColumns(JTable myTable) {
		tableWidth=0;
         myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         JTableHeader header = myTable.getTableHeader();
         int rowCount = myTable.getRowCount();
         
         Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
         while(columns.hasMoreElements()) {
        	 
            TableColumn column = (TableColumn)columns.nextElement();
            
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int)header.getDefaultRenderer().getTableCellRendererComponent
            (myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for(int row = 0; row < rowCount; row++) {
                int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent
                (myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
		header.setResizingColumn(column); 
		column.setWidth(width+myTable.getIntercellSpacing().width);
		tableWidth+=width+myTable.getIntercellSpacing().width;
		}
        tableWidth+=10;
        tableHeigth=table.getRowCount()*table.getRowHeight()+25;
	}
	public static Object [][] getData(int s,ArrayList<String[]> courseTable){
		data=new Object[s][13];
		for(int i=0;i<s;i++){
			data[i]=courseTable.get(i);
		}
		return data;
	}
	/**
	 * @Title: output
	 * @Description: 输出arraylist
	 * @param: null
	 * @return: void
	 * @throws Exception 
	 */
	public static void output(ArrayList<String[]> courses2, String title) {
		System.out.println("================================");
		System.out.println(title);
		System.out.println("================================");
		for (String[] row : courses2) {
			for (String col : row)
				System.out.print(col + "\t");
			System.out.println();
		}
		System.out.printf("-------- Total %d row(s) --------\n", courses2.size());
	}
	private static boolean login(WebAPI web) throws Exception {
		// 获取验证码图片
		String validatePath = web.getCaptcha();
		System.out.println("验证码图片存储在: " + validatePath);
		
		// 登录参数
		System.out.print("学号: ");
		String username = in.next();;
		System.out.print("密码: ");
		String password = in.next();;
		System.out.print("验证码: ");
		String validate = in.next();
		
		// 尝试登录
		String res = web.login(username, password, validate);
		System.out.println("登录结果: " + res);
		
		// 返回登录状态
		return web.isLogin();
	}
	/**
	 * @Title: testXKWebAPI
	 * @Description: 登录选课系统
	 * @param: null
	 * @return: void
	 * @throws Exception 
	 */
	public void testXKWebAPI() throws Exception {
		// 实例化 XKWebAPI
		//Schedule.XK=new XKWebAPI();
		
		// 查看学期
		String[] termInfo = XK.getTermInfo();
		for (int i = 0; i < termInfo.length; i++) {
			System.out.printf("[%d] %s\n", i, termInfo[i]);
		}
	
		// 选择
		System.out.print("请选择学期编号: ");
		int termNo = in.nextInt();
		XK.setTerm(termNo);
	
		// 登录
		if (!login(XK)) return;
		
	}
	/**
	 * @Title: sfmain
	 * @Description: 初始化窗口,登录选课系统,初始化排课状态
	 * @param: null
	 * @return: void
	 * @throws Exception 
	 */
	public void sfmain() throws Exception {
		Init();
		addLabel();
		addCheckBox();
		addButton();
		addComboBox();
		addText();
		
		try {
			//登录选课系统
			testXKWebAPI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//初始化排课状态
			Schedule.initCourseStatus(sframe.selectedCourse(XK));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * @Title: main
	 * @Description: 主函数
	 * @param: String[] args
	 * @return: void
	 * @throws Exception 
	 */
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
	/**
	 * @JTable的子类，用于创建表格 
	 */
	class mTable extends JTable{
		@Override 
		public boolean isCellEditable(int rowIndex, int columnIndex) {  
		        if(columnIndex == 13) return true;  
		        return false;  
		}
		 @Override
		public Class<?> getColumnClass(int columnIndex) {
		        return getValueAt(0, columnIndex).getClass();
		}	 
	}
	
}
