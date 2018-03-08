import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
// Simple Linear Regression Program
public class LinearRegression{

	private double[] X_array;
	private double[] Y_array;
	private double x_bar;
	private double y_bar;
	private int x_len;
	private int y_len;
	private int iter;
	private int jter;
	private double beta_0;
	private double beta_1;
	private double[] SSE;
	private double sum_RSSE;
	private double sum_SSE;
	private double sum_MSE;
	private int iter_sse;
	
	// constructor
	public LinearRegression(int xx,int yy){
		x_len    = xx;
		y_len    = yy;
		x_bar 	 = 0;
		y_bar 	 = 0;
		X_array  = new double[x_len];
		Y_array  = new double[y_len];
		SSE 	 = new double[y_len];
		iter     = 0;
		jter     = 0;
		beta_0   = 0;
		beta_1   = 0;
		iter_sse = 0;
		sum_SSE  = 0;
		sum_MSE	 = 0;	
	}
	
	//input x data
	public void inputX(double d_x){
		X_array[iter] = d_x;
		iter++;
		if(iter == X_array.length){
			System.out.println("WARNING: Dataset is full");
		}
		
	}
	// input y data
	public void inputY(double d_y){
		Y_array[jter] = d_y;
		jter++;
		if(jter == Y_array.length){
			System.out.println("WARNING: Dataset is full");
		}
	}
	
	// calculate the means of x and y
	public void calculateMeans(){
		double sum = 0;
		int counter = 0;
		int i = 0;
		boolean bb = true;
		while(true){
			if(counter >= X_array.length + Y_array.length){
				System.out.println("counter = " + counter);
				break;
			}
			if(counter < X_array.length){
				sum += X_array[i];
				counter++;
				System.out.println("counter = " + counter);
				i++;
				System.out.println("i = " + i);
			}
			if(counter >= X_array.length){
				System.out.println("X_array.length + Y_array.length = "+(X_array.length+Y_array.length));
			  if(bb == true){
				x_bar = sum/X_array.length;
				i = 0;
				System.out.println("i = " + i);
				sum = 0;
				bb = false;
				System.out.println(bb);
				System.out.println(sum);
				System.out.println(i);
			  }
			  else{
				while(true){
					sum += Y_array[i];
					counter++;
					System.out.println("counter = " + counter);
					i++;
					System.out.println("i = " + i);
					if(i == Y_array.length){
						y_bar = sum/Y_array.length;
						break;
					}
				}
			  }
			}
		}
	}
	
	// return the means
	public double Means(boolean b){
		if(b == true){
			return x_bar;
		}
		else{
			return y_bar;
		}
	}
	
	// print X Y data
	public void print(){
		for(int i=0; i < X_array.length;i++){
			System.out.println("[X("+i+")|Y("+i+")] = ("+X_array[i]+","+Y_array[i]+")");
		}
	}
	
	// compute beta one
	public void compute_Beta_one(){
		double sum_xx_yy = 0;
		double sum_xx_xx = 0;
		for(int i=0;i<X_array.length;i++){
			sum_xx_yy += (X_array[i]-x_bar)*(Y_array[i]-y_bar);
			sum_xx_xx += (X_array[i]-x_bar)*(X_array[i]-x_bar);
		}
		beta_1 = sum_xx_yy/sum_xx_xx;
	}
	// compute beta 0
	public void compute_Beta_zero(){
		beta_0 = y_bar - beta_1*x_bar;
	}
	
	// print calculated betas
	public double getBeta(boolean b){
		if(b == true){
			return beta_1;
		}
		else{
			return beta_0;
		}
		
	}
	// compute SSE
	public void SumSqrError(){
		double hold;
		for(int i=0;i<Y_array.length;i++){
			hold = beta_0 + beta_1*X_array[i];
			SSE[i] = (Y_array[i]-hold)*(Y_array[i]-hold);
			sum_SSE += SSE[i];
		}
	}
	// get SSE
	public double getSSE(){
		return sum_SSE;
	}
	// print SSE
	public void printSSE(){
		for(int i=0;i<SSE.length;i++){
			System.out.println("SSE["+i+"] = "+SSE[i]);
		}
	}
	// compute RSSE
	public void computeRSSE(){
	sum_RSSE =  Math.sqrt(sum_SSE);	
	}
	
	// compute MSE(note MSE is the same as SSE since df = n-2 = 3-2 = 1)
	public void computeMSE(){
		int df = Y_array.length-2;
		sum_MSE = (double)(1/df)*sum_SSE;
	}
	// get MSE
	public double getMSE(){
		return sum_MSE;
	}
	
	// sigmoid 
	public void sigmoid(){
		//double[] arrHold = Arrays.copyOf(RSSE,RSSE.length);
		double sigmoid;
		for(int i=0;i<X_array.length;i++){
			sigmoid = 1/(1+Math.exp(-sum_RSSE));
			//arrHold[i] = sigmoid;
			if(sigmoid < 0){
				X_array[i] = X_array[i] + sigmoid;
			}
			else{
				X_array[i] = X_array[i] - sigmoid;
			}
		}
	}
	
	// updated x values
	public void printXupdate(){
		for(int i=0; i< X_array.length; i++){
		System.out.println("["+X_array[i]+"]");
		}
	}
	
	
	public static void main(String [] args) throws FileNotFoundException{
		
		LinearRegression L1 = new LinearRegression(3,3);
		L1.inputX(20);
		L1.inputX(55);
		L1.inputX(30);
		L1.inputY(5);
		L1.inputY(12);
		L1.inputY(10);
		
		for(int i=0;i<10;i++){
			L1.calculateMeans();
			System.out.println(L1.Means(true));
			System.out.println(L1.Means(false));
			L1.print();
			L1.compute_Beta_one();
			L1.compute_Beta_zero();
			L1.SumSqrError();
			System.out.println();
			System.out.println();
			System.out.println(L1.getBeta(true));
			System.out.println(L1.getBeta(false));
			System.out.println();
			L1.printSSE();
			System.out.println();
			System.out.println(L1.getSSE());
			L1.computeMSE();
			System.out.println(L1.getMSE());
			L1.sigmoid();
			L1.printXupdate();
		}
		
		
	}

}
