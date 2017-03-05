package shuhelper.analyse;

import java.util.ArrayList;

import shuhelper.web.CJWebAPI;
import shuhelper.web.XKWebAPI;

public class Analyse {
	/**
	 * scoreSummary: 成绩大表(课程号, 课程名, 学分, 成绩, 绩点, 学期)
	 */
	ArrayList<String[]> scoreSummary = null;
	
	/**
	 * Major: 专业课
	 * NotMajor: 非专业课
	 * ALL: 所有课
	 * GPA: 绩点取值集合
	 * MajorCode: 专业代码
	 */
	public static final int Major = 1;
	public static final int NotMajor = 2;
	public static final int All = 3;
	public static final double[] GPA = {4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.5, 1.0, 0.0};
	public static final String[] strGPA = {"4.0", "3.7", "3.3", "3.0", "2.7", "2.3", "2.0", "1.5", "1.0", "0.0"};
	public static final String[] strTERM = {"20141", "20142", "20143", "20145",
											"20151", "20152", "20153", "20155",
											"20161"};
	public static String MajorCode = "083";
	
	/**
	 * 利用传入的成绩大表数据 构造数据分析模块
	 * @param 成绩大表
	 */
	public Analyse(ArrayList<String[]> scoreSummary) {
		this.scoreSummary = scoreSummary;
	}
	
	 /**
	 * 判断某课程是否为专业课
	 * @param course String[] 格式同成绩大表内的课程格式
	 * @return true/false
	 */
	private boolean IsMajor(String[] course) {
		return course[0].startsWith(MajorCode);
	}
	
	 /**
	 * 根据特定类别，对课程大表进行筛选，返回特定类别的课程数据
	 * @param type Major/NotMajor/All 表示需要筛选出哪一类的课程数据
	 * @return 筛选后的课程数据
	 */
	private ArrayList<String[]> getTypeCourse(int type) {
		ArrayList<String[]> filter = new ArrayList<String[]>();
		for (String[] course : scoreSummary) {
			boolean chkmj = IsMajor(course);
			if ((chkmj && (type & 1) != 0) || (!chkmj && (type & 2) != 0)) {
				filter.add(course);
			}
		}
		return filter;
	}
	
	 /**
	 * 计算并返回各GPA的学分
	 * @param type Major/NotMajor/All，表示计算哪些课程的GPA
	 * @return ArrayList<double[]>
	 * 	如 { {4.0, 0.3}, {3.7, 0.3}, ..., {1.0, 0.2} }
	 */
	public ArrayList<double[]> getGPARatio(int type) {
		ArrayList<double[]> result = new ArrayList<double[]>(); 
		ArrayList<String[]> courses = getTypeCourse(type);
		double total = 0.0;
		for (double gpa : GPA) {
			double sum = 0.0;
			for (String[] course : courses) {
				if (course[4].equals(String.valueOf(gpa))) {
					sum += Double.parseDouble(course[2]);
				}
			}
			if (sum == 0) continue;
			total += sum;
			result.add(new double[]{gpa, sum});
		}
		return result;
	}
	
	 /**
	 * 计算并返回挂科学分
	 * @param type Major/NotMajor/All，表示计算哪些课程的GPA
	 * @return double 表示在type课程中挂科学分所占比例
	 */
	public double getFailedCredit(int type) {
		double fail = 0.0;
		for (String[] course : getTypeCourse(type)) {
			if (Double.parseDouble(course[4]) < 1.0)
				fail += Double.parseDouble(course[2]);
		}
		return fail;
	}
	
	 /**
	 * 计算并返回各门课程的加权平均分
	 * @param type Major/NotMajor/All，表示计算哪些课程的加权平均分
	 * @return double 表示在type课程中的平均分
	 */
	public double getScoreAverage(int type) {
		double total = 0.0, credit = 0.0;
		for (String[] course : getTypeCourse(type)) {
			try {
				Double.parseDouble(course[3]);
			} catch (Exception e) {
				continue;
			}
			double score = Double.parseDouble(course[3]);
			double cred = Double.parseDouble(course[2]);
			total += score * cred;
			credit += cred;
		}
		return total / credit;
	}
	
	 /**
	 * 筛选并返回挂科科目
	 * @param type Major/NotMajor/All，表示在哪些课程中进行筛选
	 * @return ArrayList<String[]> 在type课程中的挂科科目信息
	 * 	如 { {"课程号1", "课程名1", "学分1"}, {"课程号2", "课程名2", "学分2"}, ... }
	 */
	public ArrayList<String[]> getFailedCourse(int type) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		for (String[] course : getTypeCourse(type)) {
			if (Double.parseDouble(course[4]) < 1.0) {
				result.add(new String[]{course[0], course[1], course[2]});
			}
		}
		return result;
	}
	
	 /**
	 * 判断并返回某门课程选课排名的状态
	 * @param course 某门课程的数据 (课程号, 课程名, 教师号, 教师名, 选课人数, 额定人数, 排名)
	 * @return int 表示选课排名的状态 0~4分别表示：前25% 前50% 前75% 前100% 额定人数之外
	 */
	public static int getRankStatus(String[] course) {
		double total = Double.parseDouble(course[5]);
		double rank = Double.parseDouble(course[4]);
		if (total == 0) return 4;
		double status = rank / total;
		if (status <= 0.25) return 0;
		if (status <= 0.50) return 1;
		if (status <= 0.75) return 2;
		if (status <= 1.00) return 3;
		return 4;
	}
	
	public static ArrayList<double[]> getTermData(String[] terms, CJWebAPI CJ, String field) throws Exception {
		ArrayList<double[]> scoreData = new ArrayList<double[]>();
		int idx = field == "GPA" ? 4 : 3;
		// 依次处理每个学期
		for (String term : terms) {
			double[] scoreTermData = new double[3]; // All Major NotMajor
			double[] creditTotal = new double[3];
			// 获取当前学期的学期成绩 {课程号, 课程名, 学分, 成绩, 绩点}
			System.out.println("获取" + term + "的数据中...");
			ArrayList<String[]> scoreTerm = CJ.getScoreTermArrayList(term);
			// 遍历每门课 判断其类型并统计
			for (String[] scoreInfo : scoreTerm) {
				// 检测是否为专业课
				boolean chkmj = scoreInfo[0].startsWith(MajorCode);
				// 排除分数为字母的情况
				try {
					Double.parseDouble(scoreInfo[idx]);
				} catch (Exception e) {
					continue;
				}
				double score = Double.parseDouble(scoreInfo[idx]);
				double credit = Double.parseDouble(scoreInfo[2]);
				// 统计
				scoreTermData[All - 1] += score * credit;
				creditTotal[All - 1] += credit;
				if (chkmj) {
					scoreTermData[Major - 1] += score * credit;
					creditTotal[Major - 1] += credit;
				} else {
					scoreTermData[NotMajor - 1] += score * credit;
					creditTotal[NotMajor - 1] += credit;
				}
			}
			// 求加权平均
			for (int i = 0; i < 3; i++) {
				if (creditTotal[i] != 0) {
					scoreTermData[i] /= creditTotal[i];
				}
			}
			System.out.println(scoreTermData[0] + ", " + scoreTermData[1] + ", " + scoreTermData[2]);
			scoreData.add(scoreTermData);
		}
		return scoreData;
	}
}
