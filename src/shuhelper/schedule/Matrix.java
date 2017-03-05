package shuhelper.schedule;

/**
 * 矩阵类
 * @author yy
 *
 */
public class Matrix {

	private int row;

	private int col;

	int[][] data;
	/**
	 * 无参构造，这是一个形式方法，实际中不可能有0*0的空矩阵，注意避免空指针异常
	 */
	public Matrix() {
		this.row = 0;
		this.col = 0;
		this.data = new int[row][col];
	}

	/**
	 * 矩阵维度构造
	 * @param row 行
	 * @param col 列
	 */
	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.data = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				data[i][j] = 0;
			}
		}
	}

	/**
	 * 用二维数组来构造矩阵
	 * @param data 整型数组
	 */
	public Matrix(int[][] data) {
		row = data.length;
		col = data[0].length;
		this.data = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * 初始化矩阵为相同的数
	 * @param row 行数
	 * @param col 列数
	 * @param allvalue 特定整型数值
	 */
	public Matrix(int row, int col, int allvalue) {
		this.row = row;
		this.col = col;
		int[][]tmp = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[i][j] = allvalue;
			}
		}
		data = tmp;
	}

	/**
	 * 返回矩阵行数
	 * @return 行数
	 */
	public int getRowNumber() {
		return this.row;
	}

	/**
	 * 返回矩阵列数
	 * @return 列数
	 */
	public int getColoumNumber() {
		return this.col;
	}

	/**
	 * 返回特定索引的元素值
	 * @param Row 行索引
	 * @param Coloum 列索引
	 * @return 元素
	 */
	public int getElement(int Row, int Coloum) {
		return this.data[Row][Coloum];
	}

	/**
	 * 设定指定索引元素为特定值
	 * @param Row 行索引
	 * @param Coloum 列索引
	 * @param e matrix[r][c]=e
	 */
	public String setToSpecifiedValue(int r, int c, int e) {
		if (this.data[r][c] == 1) {
			return "课时冲突！";
		} else {
			this.data[r][c] = e;
			return "排课成功！";
		}
	}
	public void setValue(int r, int c, int e) {
		if (this.data[r][c] == 1) {
			this.data[r][c] = e;
		}
	}
	/**
	 * 设定指定行所有元素为特定值
	 * @param Row 行索引
	 * @param e 值
	 * @return 设定后的矩阵
	 */
	public Matrix setRowToSpecifiedValue(int Row, int e) {
		for (int j = 0; j < col; j++) {
			this.data[Row][j] = e;
		}
		return this;
	}

	/**
	 * 设定指定列所有元素为特定值
	 * @param Coloum 列索引
	 * @param e 值
	 * @return 设定后的矩阵
	 */
	public Matrix setColoumToSpecifiedValue(int Coloum, int e) {
		for (int i = 0; i < row; i++) {
			this.data[i][Coloum] = e;
		}
		return this;
	}
	/**
	 * 判断两个矩阵是否相等
	 * @param t 待比较矩阵
	 * @return 布尔矩阵
	 */
	public boolean equalTo(Matrix t) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (data[i][j] != t.data[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 打印矩阵
	 */
	public void showMatrix() {
		for (int[] a : data) {
			for (int b : a) {
				System.out.printf("%9d ", b);
			}
			System.out.println();
		}
	}
	/**
	 * 取得特定的行
	 * @param index 行索引
	 * @return 行矩阵
	 */
	public Matrix getRow(int index) {
		Matrix A = new Matrix(1, col);
		for (int i = 0; i < col; i++) {
			A.data[0][i] = data[index][i];
		}
		return A;
	}

	/**
	 * 取得特定的列
	 * @param index 列索引
	 * @return 列矩阵
	 */
	public Matrix getColoum(int index) {
		Matrix A = new Matrix(row, 1);
		for (int i = 0; i < row; i++) {
			A.data[i][0] = data[i][index];
		}
		return A;
	}
}
