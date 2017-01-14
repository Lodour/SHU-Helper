/**
 * @Title: Utils.java
 * @Package team.zgb3.shuhelper.web
 * @Description: TODO
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月14日 下午4:27:39
 * @version V1.0
 */
package team.zgb3.shuhelper.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @ClassName: Utils
 * @Description: TODO
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月14日 下午4:27:39
 *
 */
public class Utils {
	/**
	 * @Title: parseFile
	 * @Description: 从文件中加载HTML文档
	 * @param @param filePath
	 * @param @return
	 * @param @throws Exception
	 * @return Document
	 * @throws
	 */
	public static Document parseFile(String filePath) throws Exception {
		File inputFile = new File(filePath);
		return Jsoup.parse(inputFile, "UTF-8");
	}

	/**
	 * @Title: getString
	 * @Description: TODO
	 * @param: @param 返回GET页面的结果String
	 * @param: @param url
	 * @param: @return
	 * @param: @throws ClientProtocolException
	 * @param: @throws IOException
	 * @return: String
	 */
	public static String getString(CloseableHttpClient client, String url)
	throws ClientProtocolException, IOException {
		HttpGet getPage = new HttpGet(url);
		String htmlContent = null;
		CloseableHttpResponse response = client.execute(getPage);
		try {
			HttpEntity entity = response.getEntity();
			htmlContent = EntityUtils.toString(entity);
		} finally {
			response.close();
		}
		return htmlContent;
	}

	/**
	 * @Title: getDocument
	 * @Description: 返回GET页面的结果Document
	 * @param: @param client
	 * @param: @param url
	 * @param: @return
	 * @param: @throws ClientProtocolException
	 * @param: @throws IOException
	 * @return: Document
	 */
	public static Document getDocument(CloseableHttpClient client, String url)
	throws ClientProtocolException, IOException {
		return Jsoup.parse(getString(client, url));
	}

	/**
	 * @Title: postString
	 * @Description: 返回POST页面的结果String
	 * @param: @param client
	 * @param: @param url
	 * @param: @param data
	 * @param: @return
	 * @param: @throws ClientProtocolException
	 * @param: @throws IOException
	 * @return: String
	 */
	public static String postString(CloseableHttpClient client, String url, List<NameValuePair> data)
	throws ClientProtocolException, IOException {
		HttpPost postPage = new HttpPost(url);
		String htmlContent = null;
		postPage.setEntity(new UrlEncodedFormEntity(data));
		CloseableHttpResponse response = client.execute(postPage);
		try {
			HttpEntity entity = response.getEntity();
			htmlContent = EntityUtils.toString(entity);
		} finally {
			response.close();
		}
		return htmlContent;
	}

	/**
	 * @Title: postDocument
	 * @Description: 返回POST页面的结果Document
	 * @param: @param client
	 * @param: @param url
	 * @param: @param data
	 * @param: @return
	 * @param: @throws ClientProtocolException
	 * @param: @throws IOException
	 * @return: Document
	 */
	public static Document postDocument(CloseableHttpClient client, String url, List<NameValuePair> data)
	throws ClientProtocolException, IOException {
		return Jsoup.parse(postString(client, url, data));
	}
}
