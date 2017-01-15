# shuhelper.web Manaual
> 实现`http://xk.shu.edu.cn`和`http://cj.shu.eud.cn`的API。

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
`String[][] getScheduleArray(String strTermID)`

该方法返回String型二维数组的引用，表示这个学期的课程安排，每行一门课。

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
String[][] arraySchedule = CJ.getScheduleArray(strTermID);
```

### 学期成绩
`String[][] getScoreTermArray(String strTermID)`

该方法返回String型二维数组的引用，表示这个学期的成绩情况，每行一门课。

> 课程号, 课程名, 学分, 成绩, 绩点

学期编号格式同上。

```java
String strTermID = "20161";
String[][] arrayScoreTerm = CJ.getScoreTermArray(strTermID);
```

### 成绩大表
`String[][] getScoreSummaryArray()`

该方法返回String型二维数组的引用，表示成绩大表的情况，每行一门课。

> 课程号, 课程名, 学分, 成绩, 绩点, 学期

```java
String[][] arrayScoreSummary = CJ.getScoreSummaryArray();
```
