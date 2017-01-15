/**
 * @Title: XKWebAPI.java
 * @Package shuhelper.web
 * @Description: TODO
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月14日 下午5:18:19
 * @version V1.0
 */
package shuhelper.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @ClassName: XKWebAPI
 * @Description: 实现http://xk.autoisp.shu.edu.cn[:8080]的网络API
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月14日 下午5:18:19
 *
 */
public class XKWebAPI extends WebAPI {
	/**
	 * @Fields port : 端口号，默认为80
	 */
	private int port = 80;
	
	/**
	 * @Fields allPorts : 所有可选的端口号
	 */
	private int[] allPorts = {80, 8080};
	
	/**
	 * @Fields urlBasic : urlLogin的格式
	 */
	private String urlLoginFormat = "http://xk.autoisp.shu.edu.cn:%d";
	
	public XKWebAPI() {
		super();
		setTerm(0);
	}
	
	/**
	 * @Title: getTermInfo
	 * @Description: 获取可选端口中开放学期的信息
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[]
	 */
	public String[] getTermInfo() throws Exception {
		String[] termInfo = new String[allPorts.length];
		for (int i = 0; i < allPorts.length; i++) {
			String url = urlLogin + ":" + allPorts[i];
			Document doc = Utils.getDocument(httpClient, url);
			termInfo[i] = doc.select("div.log_maindiv > div:first-child").html();
		}
		return termInfo;
	}
	
	/**
	 * @Title: setTerm
	 * @Description: 设置学期为allPorts[idx]，并更新相关URL
	 * @param: @param idx
	 * @return: void
	 */
	public void setTerm(int idx) {
		port = allPorts[idx];
		urlLogin = String.format(urlLoginFormat, port);
		urlIndex = urlLogin + "/Home/StudentIndex";
		urlCaptcha = urlLogin + "/Login/GetValidateCode?%20%20+%20GetTimestamp()";
	}
	
	/**
	 * @Title: getCourseTableDocument
	 * @Description: 返回课表查询页面的文档
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Document
	 */
	private Document getCourseTableDocument() throws Exception {
		String url = urlLogin + "/StudentQuery/CtrlViewQueryCourseTable";
		return Utils.getDocument(httpClient, url);
	}
	
	/**
	 * @Title: getEnrollRankDocument
	 * @Description: 返回选课排名页面的文档
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Document
	 */
	private Document getEnrollRankDocument() throws Exception {
		String url = urlLogin + "/DataQuery/CtrlQueryEnrollRank";
		return Utils.getDocument(httpClient, url);
	}
	
	/**
	 * @Title: getAllCourseDocument
	 * @Description: 返回查询courseNo所有结果的页面文档
	 * @param: @param courseNo
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Document
	 */
	private Document getAllCourseDocument(String courseNo) throws Exception {
		String url = urlLogin + "/StudentQuery/CtrlViewQueryCourse";
		List<NameValuePair> data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("CourseNo", courseNo));
		data.add(new BasicNameValuePair("CourseName", ""));
		data.add(new BasicNameValuePair("TeachNo", ""));
		data.add(new BasicNameValuePair("TeachName", ""));
		data.add(new BasicNameValuePair("CourseTime", ""));
		data.add(new BasicNameValuePair("NotFull", "false"));
		data.add(new BasicNameValuePair("Credit", ""));
		data.add(new BasicNameValuePair("Campus", "0"));
		data.add(new BasicNameValuePair("Enrolls", ""));
		data.add(new BasicNameValuePair("DataCount", "0"));
		data.add(new BasicNameValuePair("MinCapacity", ""));
		data.add(new BasicNameValuePair("MaxCapacity", ""));
		data.add(new BasicNameValuePair("PageIndex", "1"));    // thus need only the 1st page
		data.add(new BasicNameValuePair("PageSize", "10000")); // ensure all results returned
		data.add(new BasicNameValuePair("FunctionString", "InitPage"));
		return Utils.postDocument(httpClient, url, data);
	}
	
	/**
	 * @Title: getCourseTableArray
	 * @Description: 返回已选课程
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[][] 课程号, 课程名, 教师号, 教师名, 学分, 上课时间, 上课地点, 校区, 答疑时间, 答疑地点
	 */
	public String[][] getCourseTableArray() throws Exception {
		Document doc = getCourseTableDocument();
		String selectorRow = "tr:has(td:eq(10)):not(tr:has(td:gt(10)))";
		String selectorCol = "td:gt(0)";
		return Utils.parseTable2Array(doc, selectorRow, selectorCol);
	}
	
	/**
	 * @Title: getEnrollRankArray
	 * @Description: 返回选课排名
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[][] 课程号, 课程名, 教师号, 教师名, 选课人数, 额定人数, 排名
	 */
	public String[][] getEnrollRankArray() throws Exception {
		Document doc = getEnrollRankDocument();
		String selectorRow = "tr:has(td:eq(6)):not(tr:has(td:gt(6)))";
		String selectorCol = "td";
		return Utils.parseTable2Array(doc, selectorRow, selectorCol);
	}
	
	/**
	 * @Title: getAllCourseArray
	 * @Description: 返回查询courseNo时的所有结果
	 * @param: @param courseNo
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String[][] 课程号, 课程名, 学分, 教师号, 教师名, 上课时间, 上课地点, 容量, 人数, 校区, 选课限制, 答疑时间, 答疑地点
	 */
	public String[][] getAllCourseArray(String courseNo) throws Exception {
		Document doc = getAllCourseDocument(courseNo);
		// 选择所有有效行（至少存在10列）
		Elements rows = doc.select("tr:has(td:eq(9))");
		String[][] array = new String[rows.size()][13];
		String[] courseHeadInfo = new String[3];
		for (int i = 0; i < rows.size(); i++) {
			Elements cols = rows.get(i).select("td");
			// 如果该行有13列，则认为包含课程信息头（课程号 课程名 学分），并记录
			if (cols.size() == 13) {
				for(int j = 0; j < 3; j++) {
					courseHeadInfo[j] = cols.get(j).html();
				}
			}
			// 记录该条信息
			String[] info = array[i];
			int k = 0;
			for(k = 0; k < 3; k++) {
				info[k] = courseHeadInfo[k];
			}
			for(int j = cols.size() - 10; j < cols.size(); j++) {
				info[k++] = cols.get(j).html();
			}
		}
		return array;
	}
}
