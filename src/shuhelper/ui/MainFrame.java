package shuhelper.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.soap.SOAPBinding.Style;

import org.apache.http.client.ClientProtocolException;

import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent; 
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import shuhelper.web.*;

public class MainFrame extends Controller{
	
	@FXML
	private TabPane ChosePane;
	
	@FXML
	private GridPane XK_ClassTable;
	//Tab
	@FXML
	private Tab CJ_IdentTab, XK_IdentTab;
	
	@FXML
	private Button CJ_Button,XK_Button;
	//待选课程、搜索课程、已选课程窗口
	@FXML
	private GridPane Wait_Pane,Search_Pane,Had_Pane;
	
	//课表颜色
	public String[] Color = {"#E9CBFA","#D1CBFA","#CBDCFA","#CBF4FA","#CBFAE9","#CBFAD1","#F4FACB","#FAE9CB","#FAD1CB","#FACBF4"};
	//当前颜色
	public int colornum = 1;
	
	//登录状态
	private boolean CJ_status = false;
	private boolean XK_status = false;
	
	//是否已经选择过学期
	private boolean term = false;
	//是否已经加载过排名表
	private boolean isrank = false;
	//第一次登录
	private boolean FirstLogin = true;
	
	
	// 已选课程
	public static ArrayList<String[]> courseTable = new ArrayList<String[]>();
			
	// 选课排名
	public static ArrayList<String[]> enrollRank = new ArrayList<String[]>();
	
	//待选课程
	public static ArrayList<String[]>waitcourse = new ArrayList<String[]>();
	
	//查询课程
	public static ArrayList<String[]> queryCourse = new ArrayList<String[]>();
	
	//数据集合：已选课程
	ObservableList<HadClass> Haddata = FXCollections.observableArrayList();
	//课程表格：已选课程
	@FXML
	private TableView<HadClass> RankTable;
	@FXML
	private TableColumn<HadClass,String> ClassNum,ClassName,TeacherNum,TeacherName,Time,PeopleNum,Rank;
	@FXML
	private TableColumn<HadClass,Boolean>ExitClass;
	
	//数据集合：查询课程
	ObservableList<SearchClass> Searchdata = FXCollections.observableArrayList();
	//查询内容
	@FXML
	private TextField S_ClassNum,S_TeacherName,S_Score,S_Time;
	@FXML
	private ChoiceBox S_School;
	@FXML
	private CheckBox S_Morning,S_noon,S_Friday; 
	
	//课程表格:查询课程
	@FXML
	private TableView<SearchClass> SearchTable;
	@FXML
	private TableColumn<SearchClass,String> Search_ClassNum,Search_ClassName,Search_Score,
			Search_TeacherNum,Search_TeacherName,Search_Time,Search_School,Search_Limit,
			Search_PeopleNum;
	@FXML
	private TableColumn<SearchClass,Boolean>Search_Choice;
	
	//数据集合：待选课程
	ObservableList<WaitClass> Waitdata = FXCollections.observableArrayList();
	//课程表格：待选课程
	@FXML
	private TableView<WaitClass> WaitTable;
	@FXML
	private TableColumn<WaitClass,String> Wait_ClassNum,Wait_ClassName,Wait_Score,Wait_TeacherNum,
			Wait_TeacherName,Wait_Time,Wait_PeopleNum;
	@FXML
	private TableColumn<WaitClass,Boolean>Wait_Choice;

	// 解析时间的正则模式串
		private static Pattern pattern = Pattern.compile("\\b(?<day>[一二三四五])(?<from>\\d+)-(?<to>\\d+)\\b");

		/**
		* 字符串解析为时间
		* 如"二7~9 三1~2" = (2, 7, 9), (3, 1, 2)
		* @param time 原始的时间字符串
		* @return ArrayList of Tuple
		*/
		static public ArrayList<Tuple> parseTime(String time) {
			Matcher matcher = pattern.matcher(time);
			ArrayList<Tuple> result = new ArrayList<Tuple>();
			while (matcher.find()) {
				int day = "一二三四五".indexOf(matcher.group("day")) + 1;
				int from = Integer.valueOf(matcher.group("from"));
				int to = Integer.valueOf(matcher.group("to"));
				result.add(new Tuple(day, from, to));
			}
			return result;
		}
	//CJ界面操作
	public void CJ_Offline() throws Exception
	{
//		try {
//			CJ_status = shuhelpapp.CJ.isLogin();
//		} catch (Exception e) {
//			// Do nothing
//		}
		if(!shuhelpapp.CJ_Islogin)
		{
			shuhelpapp.XK_IdentFrame.stage.close();
			shuhelpapp.CJ_IdentFrame.stage.show();
//			CJ_IdentFrame.Show();
//			CJ_IdentFrame.stage.setAlwaysOnTop(true);
			shuhelpapp.CJ_IdentFrame.stage.setResizable(false);
		}
	}
//	XK界面操作
	public void XK_Offline() throws Exception
	{
		
		if(!term )
		{
			WindowFrame chose = new WindowFrame("ChoseFrame",188,40,true);
			try {
				chose.Start();
			} catch (Exception e) {
				// Do nothing
			}
			chose.scene.setFill(null);
			chose.stage.initOwner(stage);
			chose.Show();
			term = true;
		}
		else
		{
			//如果未登录则显示验证码界面
			if(!shuhelpapp.XK_Islogin)
			{
				shuhelpapp.CJ_IdentFrame.stage.close();
				shuhelpapp.XK_IdentFrame.stage.show();
				shuhelpapp.XK_IdentFrame.stage.setResizable(false);
			}
		}
//		else
//		{
//			
//			try {
//				XK_status = shuhelpapp.XK.isLogin();
//			} catch (Exception e) { 
//				//Do nothing
//			}
//			if(XK_status == false)
//			{
//				System.out.println("222");
//				shuhelpapp.CJ_IdentFrame.stage.close();
//				shuhelpapp.XK_IdentFrame.stage.show();
//				shuhelpapp.XK_IdentFrame.stage.setResizable(false);
//			}
//		}
	}
	@FXML
	public void Tabaction() throws Exception
	{

	}
	@FXML
	public void XK_Tabaction() throws Exception
	{
		if(XK_IdentTab.isSelected())
		{
			
			XK_Offline();
		}
		else if(CJ_IdentTab.isSelected())
		{
			CJ_Offline();
		}
		
	}
	@FXML
	public void CJ_Tabaction() throws Exception
	{
		if(XK_IdentTab.isSelected())
		{
			XK_Offline();
		}
		else if(CJ_IdentTab.isSelected())
		{
			CJ_Offline();
		}
	}
	@FXML
	public void Wait_Buttonaction(ActionEvent e)
	{
		Wait_Pane.setDisable(false);
		Wait_Pane.setVisible(true);
		Search_Pane.setDisable(true);
		Search_Pane.setVisible(false);
		Had_Pane.setDisable(true);
		Had_Pane.setVisible(false);
	}
	@FXML
	public void Search_Buttonaction(ActionEvent e)
	{
		Wait_Pane.setDisable(true);
		Wait_Pane.setVisible(false);
		Search_Pane.setDisable(false);
		Search_Pane.setVisible(true);
		Had_Pane.setDisable(true);
		Had_Pane.setVisible(false);
	}
	@FXML
	public void Had_Buttonaction(ActionEvent e) throws Exception
	{
		Wait_Pane.setDisable(true);
		Wait_Pane.setVisible(false);
		Search_Pane.setDisable(true);
		Search_Pane.setVisible(false);
		Had_Pane.setDisable(false);
		Had_Pane.setVisible(true);

		if(!isrank )
		{
			
			Had_SetTable();
			isrank = true;
		}
	}
	
	//待选课程界面：确认选课
	@FXML
	public void ChoseCourseaction() throws Exception
	{
		for(WaitClass h:Waitdata)
		{
			if(h.getWait_Choice().isSelected())
			{
				String x = shuhelpapp.XK.enrollCourse(h.getWait_ClassNum(),h.getWait_TeacherNum());
				if(!x.equals("OK"))
				{
					shuhelpapp.PromtFrame.controller.label.setText(x);
					shuhelpapp.PromtFrame.stage.show();
					wait(5000);
					System.out.println("123");
					shuhelpapp.PromtFrame.stage.close();
				}
			}
		}
	}
	//待选课程界面：刷新课表
	@FXML
	public void GetCourseaction()
	{
		//画课表
		for (String[] h:courseTable)
		{	
		
			ArrayList<Tuple> i = parseTime(h[5]);
			for (Tuple k:i)
			{
				Label x = new Label(h[1]);
				x.setStyle("-fx-background-color:" + Color[(colornum+10)%10] + ";");
				x.setAlignment(Pos.CENTER);
				x.setPrefWidth(100);
				x.setPrefHeight(40 * (k.to - k.from + 1 ));
				XK_ClassTable.add(x,k.day,k.from);
				GridPane.setRowSpan(x, k.to - k.from+1);
			}
			colornum ++ ;
		}
	}
	
	@FXML
	//查询课程
	public void Seach_Buttonaction(ActionEvent e) throws Exception
	{
		String s_classnum = S_ClassNum.getText();
		if(s_classnum == null)
		{
			s_classnum = "0";
		}
		String s_teachername = S_TeacherName.getText();
		String s_score = S_Score.getText();
		String s_time = S_Time.getText();
		String s_school = (String) S_School.getValue();
		Boolean s_morning = S_Morning.isSelected();
		Boolean s_noon = S_noon.isSelected();
		Boolean s_friday = S_Friday.isSelected();
		
		SearchTask task = new SearchTask(s_classnum,s_time,s_teachername,s_score,s_school,s_morning,s_noon,s_friday);
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			public void handle(WorkerStateEvent event) {
				queryCourse = task.GetPath();
				Search_SetTable();
				wait.cancelProgressBar();
			}
        });
	}
	
	@FXML
	//加入待选课程
	public void Search_Choice_Buttonaction(ActionEvent e) throws InterruptedException
	{
		int i =0;
		for (SearchClass h : Searchdata)
		{
			if(h.getSearch_Choice().isSelected() )
			{
				waitcourse = shuhelpapp.test.saveEleCourse(queryCourse, i, waitcourse);
				System.out.println("1:" + shuhelpapp.test.flagClassTime);
				System.out.println("1:" + shuhelpapp.test.flagCourse);
				if(shuhelpapp.test.flagClassTime.equals("课时冲突！"))
				{
					shuhelpapp.PromtFrame.controller.label.setText("课时冲突，请选择其他时间");
					shuhelpapp.PromtFrame.stage.show();
				   
				}
				if(!shuhelpapp.test.flagCourse.equals(""))
				{
					shuhelpapp.PromtFrame.controller.label.setText("重复选择该课程");
					shuhelpapp.PromtFrame.stage.show();
				}
				
				if((!shuhelpapp.test.flagClassTime.equals("课时冲突！") )&& shuhelpapp.test.flagCourse.equals(""))
				{
					//把课程画入课表
					ArrayList<Tuple> ii = parseTime(queryCourse.get(i)[5]);
					for (Tuple k:ii)
					{
						Label x = new Label(queryCourse.get(i)[1]);
						x.setStyle("-fx-background-color:" + Color[(colornum+10)%10] + ";");
						x.setAlignment(Pos.CENTER);
						x.setPrefWidth(100);
						x.setPrefHeight(40 * (k.to - k.from + 1 ));
						XK_ClassTable.add(x,k.day,k.from);
						GridPane.setRowSpan(x, k.to - k.from+1);
					}
					colornum ++ ;
				}
				
			}
			i++;
		}
		Wait_SetTable();
		
	}
	//退课
	@FXML
	public void ExitButtonaction(ActionEvent e) throws Exception
	{
		String TheStatus = shuhelpapp.XK.getEnrollStatus();

		if(TheStatus.equals("OK"))
		{
			String Exit_status;//退课状态
			for (HadClass h : Haddata)
			{
				if(h.getExitClass().isSelected())
				{
					Exit_status = shuhelpapp.XK.returnCourse(h.getClassNum(), h.getTeacherNum());
				}
			}
			enrollRank = shuhelpapp.XK.getEnrollRankArrayList();
			courseTable = shuhelpapp.XK.getCourseTableArrayList();
			Had_SetTable();
		}
		else
		{
			shuhelpapp.PromtFrame.controller.label.setText(TheStatus);
			shuhelpapp.PromtFrame.stage.show();
		}
	}
	//绘制已选课程表
	public void Had_SetTable()
	{
		Haddata.clear();
		ClassNum.setCellValueFactory(new PropertyValueFactory<>("ClassNum"));
		ClassName.setCellValueFactory(new PropertyValueFactory<>("ClassName"));
		TeacherNum.setCellValueFactory(new PropertyValueFactory<>("TeacherNum"));
		TeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
		Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
		PeopleNum.setCellValueFactory(new PropertyValueFactory<HadClass,String>("PeopleNum"));
		Rank.setCellValueFactory(new PropertyValueFactory<HadClass,String>("Rank"));
		ExitClass.setCellValueFactory(new PropertyValueFactory<HadClass,Boolean>("ExitClass"));
				
		for (int i = 0;i<enrollRank.size();i++) 
		{
			Haddata.add(new HadClass(enrollRank.get(i)[0],enrollRank.get(i)[1],enrollRank.get(i)[2],enrollRank.get(i)[3],courseTable.get(i)[5],enrollRank.get(i)[5],enrollRank.get(i)[6]));
		}
		RankTable.setItems(Haddata);
	}
	//绘制搜索表格
	public void Search_SetTable()
	{
		Searchdata.clear();
		Search_ClassNum.setCellValueFactory(new PropertyValueFactory<>("Search_ClassNum"));
		Search_ClassName.setCellValueFactory(new PropertyValueFactory<>("Search_ClassName"));
		Search_Score.setCellValueFactory(new PropertyValueFactory<>("Search_Score"));
		Search_TeacherNum.setCellValueFactory(new PropertyValueFactory<>("Search_TeacherNum"));
		Search_TeacherName.setCellValueFactory(new PropertyValueFactory<>("Search_TeacherName"));
		Search_Time.setCellValueFactory(new PropertyValueFactory<>("Search_Time"));
		Search_School.setCellValueFactory(new PropertyValueFactory<>("Search_school"));
		Search_PeopleNum.setCellValueFactory(new PropertyValueFactory<>("Search_PeopleNum"));
		Search_Limit.setCellValueFactory(new PropertyValueFactory<>("Search_Limit"));
		Search_Choice.setCellValueFactory(new PropertyValueFactory<SearchClass,Boolean>("Search_Choice"));
		
		for(String[] row :queryCourse)
		{
			String People = row[7] + "/" + row[8];
			Searchdata.add(new SearchClass(row[0],row[1],row[2],row[3],row[4],row[5],row[9],row[10],People));
		}
		SearchTable.setItems(Searchdata);
	}
	//绘制待课程表
	public void Wait_SetTable()
	{
		Waitdata.clear();
		Wait_ClassNum.setCellValueFactory(new PropertyValueFactory<>("Wait_ClassNum"));
		Wait_ClassName.setCellValueFactory(new PropertyValueFactory<>("Wait_ClassName"));
		Wait_Score.setCellValueFactory(new PropertyValueFactory<>("Wait_Score"));
		Wait_TeacherNum.setCellValueFactory(new PropertyValueFactory<>("Wait_TeacherNum"));
		Wait_TeacherName.setCellValueFactory(new PropertyValueFactory<>("Wait_TeacherName"));
		Wait_Time.setCellValueFactory(new PropertyValueFactory<>("Wait_Time"));
		Wait_PeopleNum.setCellValueFactory(new PropertyValueFactory<>("Wait_PeopleNum"));
		Wait_Choice.setCellValueFactory(new PropertyValueFactory<WaitClass,Boolean>("Wait_Choice"));
		
		for(String[] row :waitcourse)
		{
			String People = row[7] + "/" + row[8];
			Waitdata.add(new WaitClass(row[0],row[1],row[2],row[3],row[4],row[5],People));
		}
		WaitTable.setItems(Waitdata);
	}
	//绘制课表
	public void DrawCourseTable()
	{
		String[] c = {"#E8E8E8","#F9F6F7"};
		for(int j=1;j<14;j++)
		{
			for(int i=1;i<6;i++)
			{
				Label x = new Label("");
				x.setPrefWidth(100);
				x.setPrefHeight(40);
				x.setStyle("-fx-background-color:" + c[(j&1)^(i&1)] + ";");
				XK_ClassTable.add(x,i,j);
				
			}
		}
	}
}
