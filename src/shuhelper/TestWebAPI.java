package shuhelper;

import java.io.IOException;
import java.util.Scanner;

import shuhelper.web.CJWebAPI;
import shuhelper.web.WebAPI;

public class TestWebAPI {	
	public static void main(String[] args) throws IOException {
		// 实例化 CJWebAPI
		CJWebAPI webAPI = new CJWebAPI();
		
		// 获取验证码图片的绝对地址
		String filePath = webAPI.getCaptcha();
		System.out.println("Cpatcha saved to " + filePath);
		
		// 输入参数并登陆
		Scanner in = new Scanner(System.in);
		String strUserNo = "***";
		String strPasswd = "***";
		System.out.print("验证码: ");
		String strCaptcha = in.next();
		in.close();
		String strLoginResult = webAPI.login(strUserNo, strPasswd, strCaptcha);
		System.out.println("登录结果: " + strLoginResult);
		
		// 查询学期成绩
		if (strLoginResult == "OK") {
			try {
				String[][] scoreTerm = webAPI.getScoreTermArray("20161");
				displayMatrix(scoreTerm);
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
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
}
