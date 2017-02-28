package shuhelper.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class WaitClass {
	private SimpleStringProperty Wait_ClassNum;
	private SimpleStringProperty Wait_ClassName;
	private SimpleStringProperty Wait_Score;
	private SimpleStringProperty Wait_TeacherName;
	private SimpleStringProperty Wait_Time;
	private SimpleStringProperty Wait_PeopleNum;
	private CheckBox Wait_Choice;
	
	public WaitClass(String classnum,String classname,String score,String teachername,String time,String peoplenum)
	{
		Wait_ClassNum = new SimpleStringProperty(classnum);
		Wait_ClassName = new SimpleStringProperty(classname);
		Wait_Score = new SimpleStringProperty(score);
		Wait_TeacherName = new SimpleStringProperty(teachername);
		Wait_Time = new SimpleStringProperty(time);
		Wait_PeopleNum = new SimpleStringProperty(peoplenum);
		Wait_Choice = new CheckBox ("Ñ¡Ôñ¿Î³Ì");
	}

	public String getWait_ClassNum() {
		return Wait_ClassNum.get();
	}

	public void setWait_ClassNum(SimpleStringProperty wait_ClassNum) {
		Wait_ClassNum = wait_ClassNum;
	}

	public String getWait_ClassName() {
		return Wait_ClassName.get();
	}

	public void setWait_ClassName(SimpleStringProperty wait_ClassName) {
		Wait_ClassName = wait_ClassName;
	}

	public String getWait_Score() {
		return Wait_Score.get();
	}

	public void setWait_Score(SimpleStringProperty wait_Score) {
		Wait_Score = wait_Score;
	}

	public String getWait_TeacherName() {
		return Wait_TeacherName.get();
	}

	public void setWait_TeacherName(SimpleStringProperty wait_TeacherName) {
		Wait_TeacherName = wait_TeacherName;
	}

	public String getWait_Time() {
		return Wait_Time.get();
	}

	public void setWait_Time(SimpleStringProperty wait_Time) {
		Wait_Time = wait_Time;
	}

	public String getWait_PeopleNum() {
		return Wait_PeopleNum.get();
	}

	public void setWait_PeopleNum(SimpleStringProperty wait_PeopleNum) {
		Wait_PeopleNum = wait_PeopleNum;
	}

	public CheckBox getWait_Choice() {
		return Wait_Choice;
	}

	public void setWait_Choice(CheckBox wait_Choice) {
		Wait_Choice = wait_Choice;
	}

}
