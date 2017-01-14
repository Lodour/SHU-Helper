/**
 * @Title: Test.java
 * @Package team.zgb3.shuhelper.web
 * @Description: 测试team.zgb3.shuhelper.web
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:29:07
 * @version V1.0
 */
package team.zgb3.shuhelper.web;

import java.io.File;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @ClassName: Test
 * @Description: 测试team.zgb3.shuhelper.web
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:29:07
 *
 */
public class Test {
	/**
	 * @Fields in : Scanner(System.in)
	 */
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * @Title: main
	 * @Description: 测试主方法
	 * @param @param args
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		CJWebAPI CJ = new CJWebAPI();
	}
	
	/**
	 * @Title: testLogin
	 * @Description: 测试webAPI的登录功能
	 * @param @param webAPI
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void testLogin(BaseWebAPI webAPI) throws Exception {
		String filePath = webAPI.getCaptcha();
		System.out.println("Cpatcha saved to " + filePath);
		String strUserNo = inputString("学号: ");
		String strPasswd = inputString("密码: ");
		String strCaptcha = inputString("验证码: ");
		String strLoginResult = webAPI.login(strUserNo, strPasswd, strCaptcha);
		System.out.println("登录结果: " + strLoginResult);
	}
	
	/**
	 * @Title: inputString
	 * @Description: 带提示信息的字符串输入
	 * @param @param txtHint
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String inputString(String txtHint) {
		System.out.print(txtHint);
		String strInput = in.next();
		return strInput;
	}

	/**
	 * @Title: loadHtmlFromFile
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
}
