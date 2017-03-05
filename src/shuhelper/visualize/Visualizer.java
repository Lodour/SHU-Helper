package shuhelper.visualize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.TableOrder;

import shuhelper.analyse.Analyse;
import shuhelper.web.CJWebAPI;

public class Visualizer {
	 /**
	 * 生成GPA分布比例的饼图
	 * @param ana
	 * @return String 生成图片的文件名
	 * @throws IOException
	 */
	public static String getGPAChart(Analyse ana) throws IOException {
		String chartTitle = "课程GPA比例示意图";
		int[] types = {Analyse.All, Analyse.Major, Analyse.NotMajor};
		String[] captions = {"全部", "专业课", "非专业课"};
		
		// 创建数据集
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// 构建数据
		for (int i = 0; i < 3; i++) 
			for (double[] data : ana.getGPARatio(types[i]))
				dataset.addValue(data[1], String.valueOf(data[0]), captions[i]);
		
		// 获取JFreeChart对象
		JFreeChart chart = ChartFactory.createMultiplePieChart(chartTitle, dataset, TableOrder.BY_COLUMN, false, true, false);
		
		// 获取PiePlot对象
		MultiplePiePlot multiPlot = (MultiplePiePlot) chart.getPlot();
		JFreeChart obj = multiPlot.getPieChart();
		PiePlot plot = (PiePlot) obj.getPlot();

		// 设置标签格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
		
		// 保存为jpg文件
		String filename = "GPA-Pie.jpg";
		FileOutputStream pic = new FileOutputStream(filename);
		ChartUtilities.writeChartAsJPEG(pic, 1.0f, chart, 800, 600, null);
		pic.close();
		
		return filename;
	}

	public static String getScoreChart(CJWebAPI CJ, String field) throws Exception {
		// 获取数据
		String[] terms = Analyse.strTERM;
		ArrayList<double[]> scores = Analyse.getTermData(terms, CJ, field);
		
		// 创建数据集
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		for (int i = 0; i < terms.length; i++) {
			double[] score = scores.get(i);
			dataset.addValue(score[0], "专业课", terms[i]);
			dataset.addValue(score[1], "非专业课", terms[i]);
			dataset.addValue(score[2], "全部", terms[i]);
		}
		
		// 创建图表
		JFreeChart chart = ChartFactory.createBarChart3D("各学期课程平均成绩统计图", "学期", "平均成绩", dataset, PlotOrientation.VERTICAL, true, false, false); 
		
		// 保存为jpg文件
		String filename = field + "-Bar.jpg";
		FileOutputStream pic = new FileOutputStream(filename);
		ChartUtilities.writeChartAsJPEG(pic, 1.0f, chart, 800, 600, null);
		pic.close();
		
		return filename;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		CJWebAPI CJ = new CJWebAPI();
		shuhelper.web.Utils.login(CJ, in);
		
		// 使用方法
		ArrayList<String[]> scoreSummary = CJ.getScoreSummaryArrayList();
		Analyse ana = new Analyse(scoreSummary);
		Visualizer.getGPAChart(ana);
		Visualizer.getScoreChart(CJ, "GPA");
		Visualizer.getScoreChart(CJ, "Score");
	}
}
