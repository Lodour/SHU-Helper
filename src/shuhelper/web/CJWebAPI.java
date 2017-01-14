/**
 * @Title: CJWebAPI.java
 * @Package shuhelper.web
 * @Description: 实现http://cj.shu.edu.cn的网络API
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:01:32
 * @version V1.0
 */
package shuhelper.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @ClassName: CJWebAPI
 * @Description: 实现http://cj.shu.edu.cn的网络API
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:01:32
 *
 */
public class CJWebAPI extends BaseWebAPI {
	/**
	 * <p>Title: CJWebAPI</p>
	 * <p>Description: 构造函数</p>
	 */
	public CJWebAPI() {
		super();
		urlLogin = "http://cj.shu.edu.cn/";
		urlIndex = "http://cj.shu.edu.cn/Home/StudentIndex";
		urlCaptcha = "http://cj.shu.edu.cn/User/GetValidateCode?%20%20+%20GetTimestamp()";
	}

	/**
	 * @Title: getScheduleDocument
	 * @Description: 根据TermID返回课表页面的文档
	 * @param @param strTermID
	 * @param @return
	 * @param @throws ParseException
	 * @param @throws IOException
	 * @return Document
	 * @throws
	 */
	private Document getScheduleDocument(String strTermID)
	throws ParseException, IOException {
		String urlGetSchedule = "http://cj.shu.edu.cn/StudentPortal/CtrlStudentSchedule";
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("academicTermID", strTermID));
		return Utils.postDocument(httpClient, urlGetSchedule, postData);
	}

	/**
	 * @Title: getScoreTermDocument
	 * @Description: 返回某学期成绩的文档
	 * @param: @param strTermID
	 * @param: @return
	 * @param: @throws IOException
	 * @return: Document
	 */
	private Document getScoreTermDocument(String strTermID)
	throws IOException {
		String urlGetTermScore = "http://cj.shu.edu.cn/StudentPortal/CtrlScoreQuery";
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("academicTermID", strTermID));
		return Utils.postDocument(httpClient, urlGetTermScore, postData);
	}

	/**
	 * @Title: getScoreSummaryDocument
	 * @Description: 返回成绩大表的文档
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws IOException
	 * @return: Document
	 */
	private Document getScoreSummaryDocument()
	throws ParseException, IOException {
		String urlGetScoreSummary = "http://cj.shu.edu.cn/StudentPortal/ScoreSummary";
		return Utils.getDocument(httpClient, urlGetScoreSummary);
	}

	/**
	 * @Title: getScheduleArray
	 * @Description: 以二维数组返回课程安排
	 * @param: @param strTermID
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[][] {{课程号, 课程名, 教师号, 教师名, 上课时间, 上课地点, 答疑时间, 答疑地点}, ...}
	 */
	public String[][] getScheduleArray(String strTermID) throws Exception {
		Document doc = getScheduleDocument(strTermID);
		String selectorRow = "tr:has(td:eq(7))";
		String selectorCol = "td:lt(8)";
		return Utils.parseTable2Array(doc, selectorRow, selectorCol);
	}
	
	/**
	 * @Title: getScoreTermArray
	 * @Description: 以二维数组返回学期成绩
	 * @param: @param strTermID
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[][] {{课程号, 课程名, 学分, 成绩, 绩点}, ...}
	 */
	public String[][] getScoreTermArray(String strTermID) throws Exception {
		Document doc = getScoreTermDocument(strTermID);
		String selectorRow = "tr:has(td:eq(5))";
		String selectorCol = "td:lt(6):gt(0)";
		return Utils.parseTable2Array(doc, selectorRow, selectorCol);
	}
	
	/**
	 * @Title: getScoreSummaryArray
	 * @Description: 以二维数组返回成绩大表
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[][] {{课程号, 课程名, 学分, 成绩, 绩点, 学期}, ...}
	 */
	public String[][] getScoreSummaryArray() throws Exception {
		Document doc = getScoreSummaryDocument();
		int colCount = 6;
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		// select rows
		Elements rows = doc.select("tr:has(td:eq(11)):not(tr:has(td:gt(11)))");		
		for (Element row : rows) {
			// select cols twice
			Elements cols = null;
			String[] selectorCol = {"td:lt(" + colCount + ")", "td:gt(" + (colCount - 1) + ")"};
			for (String selector : selectorCol) {
				cols = row.select(selector);
				if (cols.get(0).html().compareTo("&nbsp;") != 0) {
					String[] items = new String[colCount];
					for (int j = 0; j < colCount; j++) {
						items[j] = cols.get(j).html();
					}
					arrayList.add(items);
				}
			}
		}
		// convert to String[][]
		String[][] array = new String[arrayList.size()][];
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
}
