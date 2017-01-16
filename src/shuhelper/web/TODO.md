# shuhelper.web
> 实现`http://xk.shu.edu.cn`和`http://cj.shu.eud.cn`的API。


## Utils

> 工具类
### 成员变量
|变量名|修饰符|类型|功能|
|:--------:|:------------:|:-----------------:|:--------:|
|properties|private static|Map<String, String>|属性文件缓存|

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
以`String[][]`返回`Document`中的表格先后按照`selectorRow`和`selectorCol`进行选择的结果。

- [x] `public String[] getLoginFields(Document doc)`
从登录页面的文档中获取需提交的字段名。

- [x] `public static String getProperty(String key)`
获取当前类目录下属性文件中，键`key`的值。

## WebAPI

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

> 继承于`WebAPI`，实现`http://cj.shu.edu.cn`的部分功能。

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

> 继承于`WebAPI`，实现`http://xk.autoisp.shu.edu.cn[:8080]`的部分功能。

### 成员变量
|变量名|修饰符|类型|功能|
|:------------:|:-----:|:----:|:-------------------:|
|port          |private|int   |网页端口               |
|allPorts      |private|int[] |所有可选的端口号        |

- [ ] 考虑`port`和`allPorts`也从属性文件中读取

### 成员方法
- [x] `public String[] getTermInfo()`
返回字符串数组，依次记录可选端口的学期。

- [x] `public void setTerm(int idx)`
设置api的学期为`getTermInfo()`方法返回数组的第`idx`个。

- [x] `private Document getCourseTableDocument()`
返回已选课程页面的文档。

- [x] `private Document getEnrollRankDocument()`
返回选课排名页面的文档。

- [x] `private Document getAllCourseDocument(String courseNo)`
返回查询课程，且参数为`courseNo`时__所有结果__的文档。

- [x] `public String[][] getCourseTableArray()`
返回已选课程`String[][]`。

- [x] `public String[][] getEnrollRankArray()`
返回选课排名`String[][]`。

- [x] `public String[][] getAllCourseArray(String courseNo)`
返回查询课程，且参数为`courseNo`时__所有结果__的文档`String[][]`。

- [ ] `public void getCourseTable()`
将已选课程存储在数据库中（会删除之前的记录）。

- [ ] `public void getEnrollRank()`
将选课排名存储在数据库中（会删除之前的记录）。

- [ ] `public void getAllCourse(String courseNo)`
将所有查询结果存储在数据库中。
