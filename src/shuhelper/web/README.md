# Document of shuhelper.web
> 实现`http://xk.shu.edu.cn`和`http://cj.shu.eud.cn`的API。

## 依赖
|Tools|Version|
|:-:|:-:|
|httpcomponents-cilent|4.5.2|
|jsoup|1.10.2|

## `WebAPI`抽象类
> 基本的Web接口，实现了两个网站公用的功能，如获取验证码、登陆等。

以下所有样例代码段都是基于这一个框架实现的。

当然，有时也会需要用到其它的包。

```java
import shuhelper.web.WebAPI;
import shuhelper.web.CJWebAPI;
import shuhelper.web.XKWebAPI;

public class TestWebAPI {
	public static void main(String[] args) throws Exception {
		// codes
	}
}
```

实际使用时，应分别对其子类`CJWebAPI`或`XKWebAPI`进行实例化，并调用相关方法，如：

```java
CJWebAPI CJ = new CJWebAPI();
XKWebAPI XK = new XKWebAPI();
```

但是如果使用如下这种方式进行实例化：

```java
WebAPI web = new CJWebAPI();
```

由于是使用**父类**的引用，因此只能访问父类声明的成员变量或方法。

但无论使用**父类**还是**子类**的引用，调用方式是相同的。

> 在下面的样例代码中，对于`web.method()`
>
> 应根据情况使用`CJ.method()`或`XK.method()`
>
> 而不是`web.method()`

### 验证码
通过`getCaptcha`方法，可以保存验证码为`Captcha.jpg`文件，并返回文件的**绝对路径**。

每次调用该方法，都会下载一个新的验证码图片，登录应以最新的图片为准。

```java
String filePath = web.getCaptcha();
System.out.println("验证码保存在: " + filePath);
```

### 登录
通过`login`方法，可以指定学号、姓名、验证码进行登录，并返回登录结果。

登录成功则返回`OK`，否则返回说明性文字（密码错误、验证码错误等等）。

```java
String strLoginResult = web.login(strUserNo, strPasswd, strCaptcha);
System.out.println("登录结果: " + strLoginResult);
```

### 检测登录状态
可以通过`isLogin`方法随时检测登录状态`true`或`false`。

```java
boolean state = web.isLogin();
```

## `CJWebAPI`类
> 继承自`WebAPI`，实现`http://cj.shu.eud.cn`的网络接口。

### 课程安排
`ArrayList<String[]> getScheduleArrayList(String strTermID)`

该方法返回`String[]`列表的引用，表示这个学期的课程安排，每项一门课。

> 课程号, 课程名, 教师号, 教师名, 上课时间, 上课地点, 答疑时间, 答疑地点

学期编号格式：

> 年份 + 学期
>
> 年份: 2014、2015、2016等
>
> 学期: 1秋季、2冬季、3春季、5夏季
>
> 2016年冬季学期: 20162

```java
String strTermID = "20161";
ArrayList<String[]> schedule = CJ.getScheduleArrayList(strTermID);
```

### 学期成绩
`ArrayList<String[]> getScoreTermArrayList(String strTermID)`

该方法返回`String[]`列表的引用，表示这个学期的成绩情况，每项一门课。

> 课程号, 课程名, 学分, 成绩, 绩点

学期编号格式同上。

```java
String strTermID = "20161";
ArrayList<String[]> scoreTerm = CJ.getScoreTermArrayList(strTermID);
```

### 成绩大表
`ArrayList<String[]> getScoreSummaryArrayList()`

该方法返回`String[]`列表的引用，表示成绩大表的情况，每项一门课。

> 课程号, 课程名, 学分, 成绩, 绩点, 学期

```java
ArrayList<String[]> scoreSummary = CJ.getScoreSummaryArrayList();
```


## `XKWebAPI`类
> 继承自`WebAPI`，实现`http://xk.autoisp.shu.eud.cn`的网络接口。

### 学期信息
一般情况下，选课网站分为80端口和8080端口两个页面，分别提供相邻两个学期的选课。

> http://xk.autoisp.shu.eud.cn
>
> http://xk.autoisp.shu.eud.cn:8080

`String[] getTermInfo()`方法返回`String`数组的引用，显示当前可选学期的信息。

`void setTerm(int idx)`则指定使用数组中第`idx`个学期。

需要注意的是，`XKWebAPI`在确定学期之后需要重新获取验证码。

```java
String[] termInfo = XK.getTermInfo();
for(String term : termInfo) {
	System.out.println(term);
}
// 选择第0个学期
XK.setTerm(0);
```

### 已选课程
`ArrayList<String[]> getCourseTableArrayList()`方法返回`String[]`列表的引用，显示已选课程，每项一门课。

> 课程号, 课程名, 教师号, 教师名, 学分, 上课时间, 上课地点, 校区, 答疑时间, 答疑地点

```java
ArrayList<String[]> courseTable = web.getCourseTableArrayList();
for(String[] course : courseTable) {
	for(String field : course)
		System.out.print(field + " ");
	System.out.println();
}
```

### 选课排名
`ArrayList<String[]> getEnrollRankArrayList()`方法返回`String[]`列表的引用，显示选课排名，每项一门课。

> 课程号, 课程名, 教师号, 教师名, 选课人数, 额定人数, 排名

```java
ArrayList<String[]> enrollRank = web.getEnrollRankArrayList();
for(String[] course : enrollRank) {
	for(String field : course)
		System.out.print(field + " ");
	System.out.println();
}
```

### 查询课程
`ArrayList<String[]> getAllCourseArrayList(String courseNo)`方法返回`String[]`列表的引用，显示所有查询结果，每项一节课。

> 课程号, 课程名, 学分, 教师号, 教师名, 上课时间, 上课地点, 容量, 人数, 校区, 选课限制, 答疑时间, 答疑地点

```java
// 查询所有计算机学院的课
// 时间可能会比较长
ArrayList<String[]> allCourseQuery = web.getAllCourseArrayList("0830");
for(String[] course : allCourseQuery) {
	for(String field : course)
		System.out.print(field + " ");
	System.out.println();
}
```
