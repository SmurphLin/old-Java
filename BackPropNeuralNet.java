import java.util.Arrays;
import java.util.Random;

public class BackpropNeuralNetwork
{
	private Layer[] layers;
	
	// constructor
	public BackpropNeuralNetwork(int inputSize, int hiddenSize, int outputSize)
	{
		layers = new Layer[2];
		layers[0] = new Layer(inputSize, hiddenSize);
		layers[1] = new Layer(hiddenSize, outputSize);
	}
	
	public Layer getLayer(int index)
	{
		return layers[index];
	}
	
	public double[] run(double[] input)
	{
		double[] activations = input;
		for(int i=0;i<layers.length;i++)
		{
			activations = layers[i].run(activations);
		}
		
		return activations;
	}
	
	public void train(double[] input,double[] targetOutput, double learningRate, double momentum)
	{
		double[] calculatedOutput = run(input);
		double[] error = new double[calculatedOutput.length];
		
		for(int i=0;i<error.length;i++)
		{
			error[i] = targetOutput[i] - calculatedOutput[i];
		}
		
		for(int i=layers.length-1;i>=0;i--)
		{
			error = layers[i].train(error,learningRate,momentum);
		}
		
	}
	
}
