package edu.cooper.ece465;


import java.util.concurrent.BlockingQueue;


public class Slave extends Thread {
	BlockingQueue<Job> jobQueue;
	BlockingQueue<Result> resultQueue;
	
	public Slave(BlockingQueue<Job> jQ, BlockingQueue<Result> rQ)
	{
		jobQueue = jQ;
		resultQueue = rQ;
	}
	
	// Always running looking for jobs to do
	public void run()
	{
		while(true)
		{
			Job currentJob;
			try {
				currentJob = jobQueue.take();
				Matrix A = currentJob.getA();
				Matrix B = currentJob.getB();
				
				Matrix C = new Matrix(A.numRows(), B.numColumns());
				
				// Matrix multiplication
				for(int i = 0; i < A.numRows(); i++)
				{
					for(int j = 0; j < B.numColumns(); j++)
					{
						C.setNum(i, j, 0.0);
						for(int k = 0; k < A.numColumns(); k++)
						{
							C.setNum(i, j, C.getNum(i, j) + A.getNum(i, k)*B.getNum(k, j));
						}
					}
				}
				Result out = new Result(currentJob.getIndex(), C);
				resultQueue.put(out);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}