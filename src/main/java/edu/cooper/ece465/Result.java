package edu.cooper.ece465;

public class Result {
	private int index;
	private Matrix C;
	
	public Result(int i, Matrix C)
	{
		this.index = i;
		this.C = C;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public Matrix getC()
	{
		return C;
	}
}