/**
 * @Title: Test.java
 * @Package shuhelper.web
 * @Description: 测试shuhelper.web
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:29:07
 * @version V1.0
 */
package shuhelper.web;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @ClassName: Test
 * @Description: 测试shuhelper.web
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
		WebAPI XK = new XKWebAPI();
		
	}

	/**
	 * @Title: testLogin
	 * @Description: 测试webAPI的登录功能
	 * @param @param webAPI
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void testLogin(WebAPI webAPI) throws Exception {
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
	private static String inputString(String txtHint) {
		System.out.print(txtHint);
		String strInput = in.next();
		return strInput;
	}

	/**
	 * @Title: displayMatrix
	 * @Description: 显示String二维数组mat
	 * @param: @param mat
	 * @return: void
	 */
	private static void displayMatrix(String[][] mat) {
		if (mat == null || mat.length == 0) {
			System.out.println("null/none");
			return;
		}
		for (int i = 0; i < mat.length; i++) {
			System.out.printf("[%d]", i);
			String[] row = mat[i];
			if (row == null || row.length == 0) {
				System.out.println("null/none");
				continue;
			}
			for (int j = 0; j < row.length; j++) {
				System.out.print(" [" + row[j] + "]");
			}
			System.out.println();
		}
	}
}
