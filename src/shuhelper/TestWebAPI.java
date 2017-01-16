package shuhelper;

import java.util.Scanner;

import shuhelper.web.CJWebAPI;
import shuhelper.web.WebAPI;
import shuhelper.web.XKWebAPI;

public class TestWebAPI {
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		CJWebAPI web = new CJWebAPI();
		testLogin(web);
		displayMatrix(web.getScoreSummaryArray());
	}
	
	private static void testLogin(WebAPI webAPI) throws Exception {
		String filePath = webAPI.getCaptcha();
		System.out.println("Cpatcha saved to " + filePath);
		String strUserNo = inputString("学号: ");
		String strPasswd = inputString("密码: ");
		String strCaptcha = inputString("验证码: ");
		String strLoginResult = webAPI.login(strUserNo, strPasswd, strCaptcha);
		System.out.println("登录结果: " + strLoginResult);
	}
	
	/**
	 * @Title: displayMatrix
	 * @Description: 显示String二维数组mat
	 * @param: @param mat
	 * @return: void
	 */
	@SuppressWarnings("unused")
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
	
	private static String inputString(String txtHint) {
		System.out.print(txtHint);
		String strInput = in.next();
		return strInput;
	}
}
