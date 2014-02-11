//package edu.cooper.ece465;

public class Job {
	private int index;
	private Matrix A;
	private Matrix B;
	
	public Job(int i, Matrix A, Matrix B)
	{
		this.index = i;
		this.A = A;
		this.B = B;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public Matrix getA()
	{
		return A;
	}
	
	public Matrix getB()
	{
		return B;
	}
}