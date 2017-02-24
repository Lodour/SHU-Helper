package shuhelper.scheduing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * @ClassName:Sframe
 * @Description:自动排课模块用户界面
 * @author yy
 * @date
 */

@SuppressWarnings("serial")
public class Sframe extends JFrame{

	JFrame frame;
	JTextField []text=new JTextField[5];
	mTable table = new mTable();
	JLabel []labels = new JLabel[9];
	JScrollPane scrollPane = new JScrollPane();
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JCheckBox noMorningClass=new JCheckBox("不排早课");
	JCheckBox noNoonClass=new JCheckBox("不排午课");
	JCheckBox noFridayClass=new JCheckBox("不排周五课");
	int tableWidth=0;
	int tableHeigth=0;
	ArrayList<String[]> courses = new ArrayList<String[]>();
	String [] courseNo= new String[4];
	String [] classTime=new String[4];
	String [] teacherName=new String[4];
	@SuppressWarnings("rawtypes")
	JComboBox campus = null;
	Object [][]courseData=new Object[13][5];
	Object [][]data=null;
	public void Init(){
		frame = new JFrame("排课模块");
		frame.setLayout(null);
		frame.setSize(1280, 960);
		frame.setBounds(100,200,960,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addButton(){
		button1 = new JButton("查询");
		button2 = new JButton("清空");
		button3 = new JButton("保存");
		button4 = new JButton("导出");
		button5 = new JButton("选课");
		button1.setBounds(40, 160, 60, 30);
		button2.setBounds(130, 160, 60, 30);
		button3.setBounds(220, 160, 60, 30);
		button4.setBounds(310, 160, 60, 30);
		button5.setBounds(400, 160, 60, 30);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.add(button5);
	}
	public void addText(){
		for(int i=0;i<4;i++){
			text[i]=new JTextField(null);
			frame.add(text[i]);	
			text[i].setVisible(true);
			text[i].setLayout(null);
		}
		text[0].setBounds(90, 20, 120, 30);
		text[1].setBounds(300, 20, 120, 30);
		text[2].setBounds(90, 60, 120, 30);
		text[3].setBounds(300, 60, 120, 30);
		//text[4].setBounds(300, 100, 120, 30);
	}
	public void addTable(){
		String []cloName={"星期一","星期二","星期三","星期四","星期五"};
		for(int i=0;i<13;i++){
			for(int j=0;j<5;j++){
				courseData[i][j]="";
			}
		}
		JTable courseTable = new JTable(courseData,cloName);
		
		JScrollPane scrollPane = new JScrollPane(courseTable);
		
		courseTable.setRowHeight(30);
		DefaultTableCellRenderer font = new DefaultTableCellRenderer();// 设置table内容居中
		font.setHorizontalAlignment(JLabel.CENTER);
		courseTable.setDefaultRenderer(Object.class, font);
		fitTableColumns(courseTable);
		JLabel []lab=new JLabel[13];
		for(int i=0;i<13;i++){
			lab[i]=new JLabel(String.valueOf(i+1));
			frame.add(lab[i]);
			lab[i].setBounds(680, 54+i*30, 20 , 10);
		}
		scrollPane.setBounds(700, 20, 200, 415);
		frame.add(scrollPane);
	}
	public void addLabel(){
		labels[0]=new JLabel("课程号");
		frame.add(labels[0]);
		labels[0].setBounds(30, 20, 50 , 20);
		labels[1]=new JLabel("上课时间");
		frame.add(labels[1]);
		labels[1].setBounds(225, 20, 60 , 20);
		labels[2]=new JLabel("教师名");
		frame.add(labels[2]);
		labels[2].setBounds(30, 65, 50 , 20);
		labels[4]=new JLabel("学分");
		frame.add(labels[4]);
		labels[4].setBounds(225, 65, 50 , 20);
		labels[5]=new JLabel("校区");
		frame.add(labels[5]);
		labels[5].setBounds(30, 105, 50 , 20);
		//labels[6]=new JLabel("课程号");
		//frame.add(labels[6]);
		//labels[6].setBounds(225, 105, 50 , 20);
		labels[3]=new JLabel("限制条件");
		frame.add(labels[3]);
		labels[3].setBounds(450, 10, 100 , 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addComboBox(){
		String str1[]={"本部","嘉定","延长","全部"};
		campus = new JComboBox(str1);
		frame.add(campus);
		campus.setVisible(true);
		campus.setBounds(90, 100, 120, 30);
		
	}
	public String getCourseNo() {
		return text[0].getText();
	}
	public String getClassTime() {
		return text[1].getText();
	}
	public String getTeacherName() {
		return text[2].getText();
	}
	public String getCredit() {
		return text[3].getText();
	}
	public void myEvent(){
		data=new Object[Schedule.courseSize()][13];
	 button1.addActionListener(new ActionListener()
     {
           public void actionPerformed(ActionEvent e)
           { 	   	
                try {
                	scrollPane.remove(table);
                	data=Schedule.testSchedule();
                	System.out.println(data[0][0]);
    				drawTable(data,Schedule.courseSize());
    			} catch (Exception e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
           } 
	  });
	 button2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<5;i++){
				text[i].setText(null);
			}
		}
	});
	 
	 button3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int j=0;
			// TODO Auto-generated method stub
			courses=new ArrayList<String[]>();
			String [][] strings = new String[20][2];
			
			for(int i=0;i<Schedule.courseSize();i++){
				if((boolean) table.getValueAt(i, 13)){
					strings[j][0] = (String) table.getValueAt(i, 0);
					strings[j][1] = (String) table.getValueAt(i, 3);
					j++;		
				}
			}
			for(int i=0;i<j;i++){
				 courses.add(strings[i]); 
			 }
			
		}
	});
	 
	 button4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Schedule.output(courses,"选课情况");
		}
	});
	 button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Schedule.output(courses,"选课情况");
				try {
					Schedule.elective(courses,courses.size());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	 
	}
	
	public void addCheckBox(){
		
		noMorningClass.setBounds(500,40,120,30);
		noNoonClass.setBounds(500,80,120,30);
		noFridayClass.setBounds(500,120,120,30);
		
		frame.add(noMorningClass);
		frame.add(noNoonClass);
		frame.add(noFridayClass);
	}
	
	public ArrayList<String[]>getSelectedCourses(){
		return courses;
	}
	
	public void drawTable(Object [][]data,int s) throws Exception{
				
		String []cloName={"课程号", "课程名", "学分", "教师号", "教师名", "上课时间", 
						"上课地点", "容量", "人数", "校区", "选课限制", "答疑时间", "答疑地点","选择"};
		//scrollPane.removeAll();
		DefaultTableModel tabDemo = (DefaultTableModel) table.getModel();
		tabDemo.setRowCount(0);
		tabDemo.setColumnIdentifiers(cloName);
		JCheckBox jc1 = new JCheckBox();
        table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jc1));
		for(int i=0;i<s;i++){
			
			 Object[] objdata = { data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],
					 data[i][5],data[i][6],data[i][7],data[i][8],data[i][9],data[i][10],
					 data[i][11],data[i][12],false};
			 tabDemo.addRow(objdata);
			}
		tabDemo.setRowCount(s);
		table.updateUI();
		table.setRowHeight(25);
		DefaultTableCellRenderer font = new DefaultTableCellRenderer();// 设置table内容居中
		font.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, font);
		fitTableColumns(table);
		System.out.println(tableWidth);
		//scrollPane = new JScrollPane();
		//scrollPane.removeAll();
		scrollPane.setViewportView(table);
		scrollPane.repaint();
		scrollPane.setBounds(20, 280, tableWidth, tableHeigth);
		frame.validate();
		System.out.println(tableHeigth);
		frame.add(scrollPane);
	}
	
	public void fitTableColumns(JTable myTable) {
		tableWidth=0;
         myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         JTableHeader header = myTable.getTableHeader();
         int rowCount = myTable.getRowCount();
         
         Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
         while(columns.hasMoreElements()) {
        	 
            TableColumn column = (TableColumn)columns.nextElement();
            
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int)header.getDefaultRenderer().getTableCellRendererComponent
            (myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for(int row = 0; row < rowCount; row++) {
                int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent
                (myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
		header.setResizingColumn(column); 
		column.setWidth(width+myTable.getIntercellSpacing().width);
		tableWidth+=width+myTable.getIntercellSpacing().width;
		}
        tableWidth+=10;
        tableHeigth=table.getRowCount()*table.getRowHeight()+25;
	}

}

@SuppressWarnings("serial")
class mTable extends JTable{
	@Override 
	public boolean isCellEditable(int rowIndex, int columnIndex) {  
	        if(columnIndex == 13) return true;  
	        return false;  
	}
	 @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        return getValueAt(0, columnIndex).getClass();
	    }
}

