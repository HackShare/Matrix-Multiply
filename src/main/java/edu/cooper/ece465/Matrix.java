package edu.cooper.ece465;

public class Matrix {
	private int nRows;
	private int nColumns;
	private double[][] M;
	
	public Matrix(int r, int c)
	{
		nRows = r;
		nColumns = c;
		M = new double[r][c];
	}
	
	public int numRows()
	{
		return nRows;
	}
	
	public int numColumns()
	{
		return nColumns;
	}
	
	public double getNum(int r, int c)
	{
		return M[r][c];
	}
	
	public void setNum(int r, int c, double v)
	{
		M[r][c] = v;
	}
	
	public void print()
	{
		for(int i = 0; i < nRows; i++)
		{
			for(int j =0; j < nColumns; j++)
			{
				System.out.print(M[i][j] + " \t");
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}
}