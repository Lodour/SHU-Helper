package shuhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shuhelper.web.CJWebAPI;
import shuhelper.web.Utils;
import shuhelper.web.WebAPI;
import shuhelper.web.XKWebAPI;

public class TestWebAPI {
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		CJWebAPI web = new CJWebAPI();
		testLogin(web);
		displayList(web.getScoreSummaryArrayList());
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
	 * @Title: displayList
	 * @Description: 显示List<String[]>
	 * @param: @param list
	 * @return: void
	 */
	@SuppressWarnings("unused")
	private static void displayList(List<String[]> list) {
		if (list == null || list.size() == 0) {
			System.out.println("null/none");
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("[%d]", i);
			String[] row = list.get(i);
			if (row == null || row.length == 0) {
				System.out.println("null/none");
				continue;
			}
			for (String item : row) {
				System.out.print(" [" + item + "]");
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
