# shuhelper.web
> 实现`http://xk.shu.edu.cn`和`http://cj.shu.eud.cn`的API。

## Test

> 测试用

### 成员方法
- [x] `public static void testLogin(BaseWebAPI webAPI)`
对`webAPI`进行登录测试。

- [x] `private static String inputString(String txtHint)`
带提示信息的字符串输入


## Utils

> 工具类

### 成员方法
- [x] `public static Document parseFile(String filePath)`
返回由文件`filePath`解析的`Document`。

- [x] `public static String getString(CloseableHttpClient client, String url)`
以`String`返回`GET`某个页面的结果。

- [x] `public static Document getDocument(CloseableHttpClient client, String url)`
以`Jsoup.Document`返回`GET`某个页面的结果。

- [x] `public static String postString(CloseableHttpClient client, String url, ArrayList<NameValuePair> data)`
以`String`返回`POST`某个页面的结果。

- [x] `public static Document postDocument(CloseableHttpClient client, String url, ArrayList<NameValuePair> data)`
以`Jsoup.Document`返回`POST`某个页面的结果。

- [x] `public static String[][] parseTable2Array(Document doc, String selectorRow, String selectorCol)`
以`String[][]`返回`Docuemnt`中的表格先后按照`selectorRow`和`selectorCol`进行选择的结果。


## BaseWebAPI

> 基本的网络API抽象类。

### 成员变量
|变量名|修饰符|类型|功能|
|:--------:|:-------:|:-----------------:|:-----------:|
|urlLogin  |protected|String             |登录页面的URL  |
|urlCaptcha|protected|String             |验证码图片的URL|
|urlIndex  |protected|String             |登陆后的主页URL|
|httpClient|protected|CloseableHttpClient|http客户端    |
|strUserNo |protected|String             |学号          |

### 成员方法
- [x] `public String getCaptcha()`
保存验证码图片到本地文件，并返回文件绝对路径。

- [x] `public String login(String strUserNo, String strPasswd, String strCaptcha)`
指定学号、密码、验证码登录，登录成功返回`OK`，否则返回错误信息。

- [x] `public boolean isLogin()`
判断客户端是否处于登录状态。

- [ ] `public void initDataBase()`
初始化数据库。


## CJWebAPI

> 继承于`BaseWebAPI`，实现`http://cj.shu.edu.cn`的部分功能。

### 成员方法
- [x] `private Document getScheduleDocument(String strTermID)`
返回`strTermID`学期课程安排页面的文档。

- [x] `private Document getScoreTermDocument(String strTermID)`
返回`strTermID`学期成绩页面的文档。

- [x] `private Document getScoreSummaryDocument()`
返回成绩大表页面的文档。

- [x] `public String[][] getScheduleArray(String strTermID)`
返回`strTermID`学期课程安排页面的文档`String[][]`。

- [x] `public String[][] getScoreTermArray(String strTermID)`
返回`strTermID`学期成绩页面的文档`String[][]`。

- [x] `public String[][] getScoreSummaryArray()`
返回成绩大表页面的文档`String[][]`。

- [ ] `public void getSchedule(String strTermID)`
将`strTermID`学期的课程安排存储在数据库中。

- [ ] `public void getScoreTerm(String strTermID)`
将`strTermID`学期的成绩存储在数据库中。

- [ ] `public void getScoreSummary()`
将成绩大表存储在数据库中。


## XKWebAPI

> 继承于`BaseWebAPI`，实现`http://xk.autoisp.shu.edu.cn[:8080]`的部分功能。
