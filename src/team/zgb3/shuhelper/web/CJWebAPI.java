/**
 * @Title: CJWebAPI.java
 * @Package team.zgb3.shuhelper.web
 * @Description: 实现http://cj.shu.edu.cn的网络API
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:01:32
 * @version V1.0
 */
package team.zgb3.shuhelper.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Document;

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
	public Document getScheduleDocument(String strTermID)
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
	public Document getScoreTermDocument(String strTermID)
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
	public Document getScoreSummaryDocument()
	throws ParseException, IOException {
		String urlGetScoreSummary = "http://cj.shu.edu.cn/StudentPortal/ScoreSummary";
		return Utils.getDocument(httpClient, urlGetScoreSummary);
	}
}
