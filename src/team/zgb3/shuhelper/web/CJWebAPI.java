/**
 * @Title: CJWebAPI.java
 * @Package team.zgb3.shuhelper.web
 * @Description: 实现http://cj.shu.edu.cn的网络API
 * @author Roll (roll@busyliving.me)
 * @date 2017年1月13日 下午11:01:32
 * @version V1.0
 */
package team.zgb3.shuhelper.web;

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
}
