package shuhelper.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class SearchClass {
	private SimpleStringProperty Search_ClassNum;
	private SimpleStringProperty Search_ClassName;
	private SimpleStringProperty Search_Score;
	private SimpleStringProperty Search_TeacherNum;
	private SimpleStringProperty Search_TeacherName;
	private SimpleStringProperty Search_Time;
	private SimpleStringProperty Search_school;
	private SimpleStringProperty Search_Limit;
	private SimpleStringProperty Search_PeopleNum;
	private CheckBox Search_Choice;
	
	public SearchClass(String Search_ClassNum,String Search_ClassName,String Search_Score,
			String Search_TeacherNum,String Search_TeacherName,String Search_Time,
			String Search_school,String Search_Limit,String Search_PeopleNum)
	{
		this.Search_ClassNum = new SimpleStringProperty(Search_ClassNum);
		this.Search_ClassName = new SimpleStringProperty(Search_ClassName);
		this.Search_Score =  new SimpleStringProperty(Search_Score);
		this.Search_TeacherNum = new SimpleStringProperty(Search_TeacherNum);
		this.Search_TeacherName = new SimpleStringProperty(Search_TeacherName);
		this.Search_Time = new SimpleStringProperty(Search_Time);
		this.Search_school = new SimpleStringProperty(Search_school);
		this.Search_Limit = new SimpleStringProperty(Search_Limit);
		this.Search_PeopleNum = new SimpleStringProperty(Search_PeopleNum);
		this.Search_Choice = new CheckBox("º”»Î¥˝—°");
	}

	public String getSearch_ClassNum() {
		return Search_ClassNum.get();
	}

	public void setSearch_ClassNum(SimpleStringProperty search_ClassNum) {
		Search_ClassNum = search_ClassNum;
	}

	public String getSearch_ClassName() {
		return Search_ClassName.get();
	}

	public void setSearch_ClassName(SimpleStringProperty search_ClassName) {
		Search_ClassName = search_ClassName;
	}

	public String getSearch_Score() {
		return Search_Score.get();
	}

	public void setSearch_Score(SimpleStringProperty search_Score) {
		Search_Score = search_Score;
	}

	public String getSearch_TeacherNum() {
		return Search_TeacherNum.get();
	}

	public void setSearch_TeacherNum(SimpleStringProperty search_TeacherNum) {
		Search_TeacherNum = search_TeacherNum;
	}

	public String getSearch_TeacherName() {
		return Search_TeacherName.get();
	}

	public void setSearch_TeacherName(SimpleStringProperty search_TeacherName) {
		Search_TeacherName = search_TeacherName;
	}

	public String getSearch_Time() {
		return Search_Time.get();
	}

	public void setSearch_Time(SimpleStringProperty search_Time) {
		Search_Time = search_Time;
	}

	public String getSearch_school() {
		return Search_school.get();
	}

	public void setSearch_school(SimpleStringProperty search_school) {
		Search_school = search_school;
	}

	public String getSearch_Limit() {
		return Search_Limit.get();
	}

	public void setSearch_Limit(SimpleStringProperty search_Limit) {
		Search_Limit = search_Limit;
	}

	public String getSearch_PeopleNum() {
		return Search_PeopleNum.get();
	}

	public void setSearch_PeopleNum(SimpleStringProperty search_PeopleNum) {
		Search_PeopleNum = search_PeopleNum;
	}

	public CheckBox getSearch_Choice() {
		return Search_Choice;
	}

	public void setSearch_Choice(CheckBox search_Choice) {
		Search_Choice = search_Choice;
	}

}
