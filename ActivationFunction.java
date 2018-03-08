import java.util.Arrays;
import java.util.Random;

public class ActivationFunction
{
	// Sigmoid funciton
	public static double sigmoid(double x)
	{
		return (double) (1/ (1+Math.exp(-x)));
	}
	// derivative of sigmoid funciton
	public static double dSigmoid(double x){
		return x*(1-x);
	}
	
}
