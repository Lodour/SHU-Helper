# team.zgb3.shuhelper.web
> 实现`http://xk.shu.edu.cn`和`http://cj.shu.eud.cn`的API。

## Test

> 测试用

### 成员方法
* `public static void testLogin(BaseWebAPI webAPI)`
对`webAPI`进行登录测试。

* `private static String inputString(String txtHint)`
带提示信息的字符串输入

* `private static Document parseFile(String filePath)`
从文件中加载HTML文档

## BaseWebAPI

> 基本的网络API抽象类。

### 成员变量
|变量名|修饰符|类型|功能|
|:--------:|:-------:|:-----------------:|:-----------:|
|urlLogin  |protected|String             |登录页面的URL  |
|urlCaptcha|protected|String             |验证码图片的URL|
|urlIndex  |protected|String             |登陆后的主页URL|
|httpClient|protected|CloseableHttpClient|http客户端    |

### 成员方法
* `public boolean isLogin()`
判断客户端是否处于登录状态。

* `public String getCaptcha()`
保存验证码图片到本地文件，并返回文件绝对路径

* `public String login(String strUserNo, String strPasswd, String strCaptcha)`
指定学号、密码、验证码登录，登录成功返回`OK`，否则返回错误信息

* `protected String getHTML(String url)`
以`String`返回某个URL的HTML

* `protected Document getDocument(String url)`
返回某个URL被`Jsoup`解析后的文档

## CJWebAPI

> 继承于`BaseWebAPI`，实现`http://cj.shu.edu.cn`的部分功能。

