import java.util.Arrays;
import java.util.Random;


public class App
{
	public static void main(String[] args)
	{
		double [][] trainingData = new double[][]
		{
			new double[] {0,0},
			new double[] {0,1},
			new double[] {1,0},
			new double[] {1,1}
		};
		
		double[][] trainingResults = new double[][]
		{
			new double[] {0},
			new double[] {0},
			new double[] {0},
			new double[] {1}
		};
		
		BackpropNeuralNetwork backpropNeuralNetwork = new BackpropNeuralNetwork(2,3,1);
		for(int iter = 0; iter < NeuralNetConstants.ITERATIONS; iter++)
		{
			for(int i=0;i<trainingResults.length;i++)
			{
				backpropNeuralNetwork.train(trainingData[i],trainingResults[i],NeuralNetConstants.LEARNING_RATE,NeuralNetConstants.MOMENTUM);
			}
		}
		System.out.println();
		for(int i=0;i<trainingResults.length;i++)
		{
			double[] t = trainingData[i];
			System.out.println("Num of iterations: " + i+1);
			System.out.printf("%.1f, %.1f --> %.3f\n",t[0],t[1], backpropNeuralNetwork.run(t)[0]);
		}
	}
}
