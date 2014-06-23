package it.ncorti.histogramthresholding;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Classe principale utilizzata per effettuare calcoli di esempio.
 * La classe deve essere invocata con i seguenti parametri:
 * 
 * TestClass <seq> <sizestream> <sizerow> <sizecol> <percentage>
 * TestClass <par> <thread_numbers> <sizestream> <sizerow> <sizecol> <percentage>
 * 
 * In caso di parametri mancanti verra' eseguita una computazione con parametri di default.
 * 
 * @author Nicola
 */
public class TestClass {
		
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException{
		
		int SIZE_ROW = 5000;
		int SIZE_COL = 5000;
		int SIZE_STREAM = 5;
		int THREADS = 2;
		double PERC = 0.6;
				
		
		try {
			if (args.length > 0 && args.length <= 6){
				
				if(args[0].equals("par")){
					THREADS = Integer.parseInt(args[1]);
					SIZE_STREAM = Integer.parseInt(args[2]);
					SIZE_ROW = Integer.parseInt(args[3]);
					SIZE_COL = Integer.parseInt(args[4]);
					PERC = Double.parseDouble(args[5]);
					
					Parallel par = new Parallel(THREADS);
					par.testcompute(SIZE_STREAM, SIZE_ROW, SIZE_COL, PERC);
					par.shutdown();
				}
				if(args[0].equals("farm")){
					THREADS = Integer.parseInt(args[1]);
					SIZE_STREAM = Integer.parseInt(args[2]);
					SIZE_ROW = Integer.parseInt(args[3]);
					SIZE_COL = Integer.parseInt(args[4]);
					PERC = Double.parseDouble(args[5]);
					
					ParallelFarm parfarm = new ParallelFarm(THREADS);
					parfarm.testcompute(SIZE_STREAM, SIZE_ROW, SIZE_COL, PERC);
					parfarm.shutdown();
				}
				if(args[0].equals("seq")){
					SIZE_STREAM = Integer.parseInt(args[1]);
					SIZE_ROW = Integer.parseInt(args[2]);
					SIZE_COL = Integer.parseInt(args[3]);
					PERC = Double.parseDouble(args[4]);
					Sequential.testcompute(SIZE_STREAM, SIZE_ROW, SIZE_COL, PERC);
				}
				if(args[0].equals("quadpar")){
					THREADS = Integer.parseInt(args[1]);
					SIZE_STREAM = Integer.parseInt(args[2]);
					SIZE_ROW = Integer.parseInt(args[3]);
					SIZE_COL = Integer.parseInt(args[4]);
					PERC = Double.parseDouble(args[5]);
					
					ParallelQuad parmap = new ParallelQuad(THREADS);
					parmap.testcompute(SIZE_STREAM, SIZE_ROW, SIZE_COL, PERC);
					parmap.shutdown();
				}
				if(args[0].equals("quadseq")){
					SIZE_STREAM = Integer.parseInt(args[1]);
					SIZE_ROW = Integer.parseInt(args[2]);
					SIZE_COL = Integer.parseInt(args[3]);
					PERC = Double.parseDouble(args[4]);
					SequentialQuad.testcompute(SIZE_STREAM, SIZE_ROW, SIZE_COL, PERC);
				}
			} else {
				System.err.println("Error: Parameter missing");
				printUsage();
				System.exit(3);
			}
		} catch (Exception e) {
			System.err.println("Error: Wrong invocation");
			e.printStackTrace();
			printUsage();
			System.exit(1);
		} catch (OutOfMemoryError e){
			System.err.println("RUNNING OUT OF MEMORY!");
			System.exit(2);
		}
		
		System.exit(0);

	}
	
	private static void printUsage(){
		System.err.println("Usage: \n\t TestClass <seq> <sizestream> <sizerow> <sizecol> <percentage> ");
		System.err.println("\t TestClass <par> <thread_numbers> <sizestream> <sizerow> <sizecol> <percentage> ");
		System.err.println("\t TestClass <farm> <thread_numbers> <sizestream> <sizerow> <sizecol> <percentage> ");
		System.err.println("\t TestClass <quadseq> <sizestream> <sizerow> <sizecol> <percentage> ");
		System.err.println("\t TestClass <quadpar> <thread_numbers> <sizestream> <sizerow> <sizecol> <percentage> ");
	}
}
