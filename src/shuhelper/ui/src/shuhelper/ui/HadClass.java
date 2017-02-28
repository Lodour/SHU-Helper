package shuhelper.ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

//ÒÑÑ¡¿Î³Ì
public class HadClass {
	private  SimpleStringProperty ClassNum;
	private  SimpleStringProperty ClassName;
	private  SimpleStringProperty TeacherNum;
	private  SimpleStringProperty TeacherName;
	private  SimpleStringProperty Time;
	private  SimpleStringProperty PeopleNum;
	private  SimpleStringProperty Rank;
	private  CheckBox ExitClass;
	
	public HadClass(String ClassNum, String ClassName, String TeacherNum,String TeacherName,String Time,String PeopleNum,String Rank) 
	{
		this.ClassNum = new SimpleStringProperty(ClassNum);
		this.ClassName = new SimpleStringProperty(ClassName);
		this.TeacherNum =  new SimpleStringProperty(TeacherNum);
		this.TeacherName = new SimpleStringProperty(TeacherName);
		this.Time = new SimpleStringProperty(Time);
		this.PeopleNum = new SimpleStringProperty(PeopleNum);
		this.Rank = new SimpleStringProperty(Rank);
		this.ExitClass = new CheckBox("ÍË¿Î");
	}

	public String getClassNum() {
		return ClassNum.get();
	}

	public void setClassNum(SimpleStringProperty classNum) {
		ClassNum = classNum;
	}

	public String getClassName() {
		return ClassName.get();
	}

	public void setClassName(SimpleStringProperty className) {
		ClassName = className;
	}

	public String getTeacherName() {
		return TeacherName.get();
	}

	public void setTeacherName(SimpleStringProperty teacherName) {
		TeacherName = teacherName;
	}

	public String getPeopleNum() {
		return PeopleNum.get();
	}

	public void setPeopleNum(SimpleStringProperty peopleNum) {
		PeopleNum = peopleNum;
	}

	public String getRank() {
		return Rank.get();
	}

	public void setRank(SimpleStringProperty rank) {
		Rank = rank;
	}

	public CheckBox getExitClass() {
		return ExitClass;
	}

	public String getTeacherNum() {
		return TeacherNum.get();
	}

	public void setTeacherNum(SimpleStringProperty teacherNum) {
		TeacherNum = teacherNum;
	}

	public String getTime() {
		return Time.get();
	}

	public void setTime(SimpleStringProperty time) {
		Time = time;
	}





	
	
	
	
	

}