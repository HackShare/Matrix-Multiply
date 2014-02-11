package edu.cooper.ece465;

import java.util.concurrent.BlockingQueue;


public class Master {
	BlockingQueue<Job> jobQueue;
	BlockingQueue<Result> resultQueue;
	
	public Master(BlockingQueue<Job> jQ, BlockingQueue<Result> rQ)
	{
		jobQueue = jQ;
		resultQueue = rQ;
	}
	
	public Matrix multiply(Matrix A, Matrix B, int splits) throws Exception
	{
		if(A.numColumns() != B.numRows())
		{
			throw new Exception();
		}
		
		Matrix result = new Matrix(A.numRows(), B.numColumns());
		
		int rowsPerSplit = A.numRows() / splits;
		
		// Fill job queue
		for(int i = 0; i < splits; i++)
		{
			sendToSlaves(i, rowsPerSplit, A, B);
		}
		
		// Read result queue
		for(int i = 0; i < splits; i++)
		{
			receiveFromSlaves(result);
		}
		
		return result;
	}
	
	private void sendToSlaves(int i, int rps, Matrix A, Matrix B) throws InterruptedException
	{
		// numToSend and the conditional ensure no overflow
		int numToSend = A.numRows() - i*rps;
		if(numToSend > 0)
		{
			numToSend = numToSend > rps ? rps : numToSend;
			// Grabs relevant rows
			Matrix A2 = splitMatrix(A, i*rps, i*rps + numToSend - 1);
			Job J = new Job(i*rps, A2, B);
			jobQueue.put(J);
		}
	}
	
	private Matrix splitMatrix(Matrix X, int start, int end)
	{
		Matrix Y = new Matrix(end - start + 1, X.numColumns());
		for(int i = 0; i < Y.numRows(); i++)
		{
			for(int j = 0; j < Y.numColumns(); j++)
			{
				Y.setNum(i, j, X.getNum(start + i, j));
			}
		}
		return Y;
	}
	
	private void receiveFromSlaves(Matrix R) throws InterruptedException
	{
		Result res = resultQueue.take();
		int ind = res.getIndex();
		Matrix C = res.getC();
		
		// Fills the relevant part of result matrix R
		for(int i = 0; i < C.numRows(); i++)
		{
			for(int j = 0; j < C.numColumns(); j++)
			{
				R.setNum(ind + i, j, C.getNum(i, j));
			}
		}
	}
}