/**
 * @Title: XKWebAPI.java
 * @Package shuhelper.web
 * @Description: TODO
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月14日 下午5:18:19
 * @version V1.0
 */
package shuhelper.web;

import org.jsoup.nodes.Document;

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
	public Document getCourseTableDocument() throws Exception {
		String url = urlIndex;
		Document doc = Utils.getDocument(httpClient, url);
		System.out.println(doc.getElementsMatchingText("14123090").size());
		return doc;
	}
}
