package shuhelper.schedule;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class CourseTable extends Matrix{
	Matrix courseTable=new Matrix(13,5);
	public Matrix getCourseTable(){
		return courseTable;
	}
	
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
	/**
	 * @Title: pickClassTime
	 * @Description: 挑选出上课时间，判断排课状态
	 * @param: String str 
	 * @return: String 
	 * @throws Exception 
	 */
	public String pickClassTime(String str){
		//String regEx = "[\u2E80-\u9FFF]";
		String regEx = "[一][0-9]{1,2}.[0-9]{1,2}|[二][0-9]{1,2}.[0-9]{1,2}|[三][0-9]{1,2}.[0-9]{1,2}|[四][0-9]{1,2}.[0-9]{1,2}|[五][0-9]{1,2}.[0-9]{1,2}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		String string="";
		String week="";
		String time="";
		while(matcher.find()){
			//周几
			week=matcher.group();
			//System.out.println(week);
			String regEx1 = "[一]|[二]|[三]|[四]|[五]";
			Pattern pattern1 = Pattern.compile(regEx1);
			Matcher matcher1 = pattern1.matcher(week);
			while(matcher1.find()){	
				time=matcher1.group();
				//上课时间
				
				if(time.equals("一")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						if(courseTable.setToSpecifiedValue(i, 0, 1).equals("课时冲突！")){
							//System.out.println("课时冲突！");
							return "课时冲突！";
						}
						else{
							courseTable.setToSpecifiedValue(i, 0, 1);
						}
					}
				}
				if(time.equals("二")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						if(courseTable.setToSpecifiedValue(i, 1, 1).equals("课时冲突！")){
							//System.out.println("课时冲突！");
							return "课时冲突！";
						}
						else{
							courseTable.setToSpecifiedValue(i, 1, 1);
						}
					}
				}
				if(time.equals("三")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						if(courseTable.setToSpecifiedValue(i, 2, 1).equals("课时冲突！")){
							//System.out.println("课时冲突！");
							return "课时冲突！";
						}
						else{
							courseTable.setToSpecifiedValue(i, 2, 1);
						}
					}
				}
				if(time.equals("四")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						if(courseTable.setToSpecifiedValue(i, 3, 1).equals("课时冲突！")){
							//System.out.println("课时冲突！");
							return "课时冲突！";
						}
						else{
							courseTable.setToSpecifiedValue(i, 3, 1);
						}
					}
				}
				if(time.equals("五")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						if(courseTable.setToSpecifiedValue(i, 4, 1).equals("课时冲突！")){
							//System.out.println("课时冲突！");
							return "课时冲突！";
						}
						else{
							courseTable.setToSpecifiedValue(i, 4, 1);
						}
					}
				}
			}
			string=string+matcher.group();
		}
		//System.out.println(string);
		return string;
	}
	/**
	 * @Title: reClassTime
	 * @Description: 挑选出退课课程时间，并清除其排课状态
	 * @param: String str 
	 * @return: String 
	 * @throws Exception 
	 */
	public String reClassTime(String str){
		//String regEx = "[\u2E80-\u9FFF]";
		String regEx = "[一][0-9]{1,2}.[0-9]{1,2}|[二][0-9]{1,2}.[0-9]{1,2}|[三][0-9]{1,2}.[0-9]{1,2}|[四][0-9]{1,2}.[0-9]{1,2}|[五][0-9]{1,2}.[0-9]{1,2}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		String string="";
		String week="";
		String time="";
		while(matcher.find()){
			//周几
			week=matcher.group();
			//System.out.println(week);
			String regEx1 = "[一]|[二]|[三]|[四]|[五]";
			Pattern pattern1 = Pattern.compile(regEx1);
			Matcher matcher1 = pattern1.matcher(week);
			while(matcher1.find()){	
				time=matcher1.group();
				//上课时间
				if(time.equals("一")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						courseTable.setValue(i, 0, 0);
					}
				}
				if(time.equals("二")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						courseTable.setValue(i, 1, 0);
					}
				}
				if(time.equals("三")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						courseTable.setValue(i, 2, 0);
					}
				}
				if(time.equals("四")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						courseTable.setValue(i, 3, 0);
					}
				}
				if(time.equals("五")){	
					String regEx2 = "[0-9]{1,2}";
					Pattern pattern2 = Pattern.compile(regEx2);
					Matcher matcher2 = pattern2.matcher(week);
					int []stime={-1,-1};
					int k=0;
					Integer it;
					while(matcher2.find()){
						it = new Integer(matcher2.group(0));
						stime[k++]=it.intValue();
					}
					//System.out.println(stime[0]+"   "+stime[1]);
					for(int i=stime[0]-1;i<stime[1];i++){
						courseTable.setValue(i, 4, 0);
					}
				}
			}
			string=string+matcher.group();
		}
		//System.out.println(string);
		return string;
	}
	/**
	 * @Title: getClassTime
	 * @Description: 挑选出退课课程时间用于画课表
	 * @param: String str 
	 * @return: String 
	 * @throws Exception 
	 */
	public ArrayList<Tuple> getClassTime(String str){
		return parseTime(str);
	}
	public static void main(String[] args) throws Exception {
		CourseTable courseTable=new CourseTable();
		String string="一1-2 三3-4 学院机房上机 五3-4 研讨 ";
		courseTable.pickClassTime(string);
//		TestSchedule.output(courseTable.getClassTime(string), "选课");
		courseTable.courseTable.showMatrix();
		System.out.println();
		String string1="一1-2 三3-4 学院机房上机 五3-4 研讨 一1-2 一3-4 学院机房上机 三7-8 研讨一11-13 四3-4 学院机房上机一7-9 三5-6 学院机房上机二1-2 四1-2 三11-12 (2,7周)";
		courseTable.reClassTime(string1);	
//		TestSchedule.output(courseTable.getClassTime(string1), "选课");
		courseTable.courseTable.showMatrix();
		
	}
}
