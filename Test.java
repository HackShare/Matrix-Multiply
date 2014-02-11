//package edu.cooper.ece465;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test 
{
	public static void main(String [] args) throws Exception
	{
		// Jobs send data to slaves that return Results
		BlockingQueue<Job> jobQueue = new LinkedBlockingQueue<Job>();
		BlockingQueue<Result> resultQueue = new LinkedBlockingQueue<Result>();
		
		// One master
		Master M = new Master(jobQueue, resultQueue);
		
		// Ten slaves
		for(int i = 0; i < 10; i++)
		{
			Slave S = new Slave(jobQueue, resultQueue);
			S.start();
		}
		
		// 10x10 matrices with 10 splits so each slave gets 1 row
		Matrix A = new Matrix(10, 10);
		Matrix B = new Matrix(10, 10);
		
		for(int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				A.setNum(i, j, 2*i+j);
				B.setNum(i, j, i+3*j);
			}
		}
		
		A.print();
		B.print();
		
		// The command with 10 splits
		Matrix C = M.multiply(A, B, 10);
		
		C.print();
	}
}