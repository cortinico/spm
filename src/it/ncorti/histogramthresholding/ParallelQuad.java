package it.ncorti.histogramthresholding;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.Stream;
import cl.niclabs.skandium.skeletons.Map;

/**
 * Classe per l'esecuzione di calcoli paralleli.
 * 
 * I calcoli vengono realizzati tramite un algoritmo che impiega O(n'4) per cui risulta
 * particolarmente inefficente. I calcoli vengono parallelizzati tramite una Map.
 * 
 * La classe deve essere istanziata per avviare il framework parallelo,
 * e deve essere chiusa con il metodo shutdown appena il calcolo e' terminato.
 * 
 * @author Nicola
 *
 */
public class ParallelQuad {

	/** Stream di input del framework */
	private Stream<Matrix, Matrix> stream;
	/** Oggetto skandium del framework */
	private Skandium skandium;
	/** Numero di threads con cui e' stato instanziato il framework */
	private int threads;
	
	/**
	 * Costruttore principale
	 * @param threads Numero di threads con cui far eseguire il calcolo parallelo
	 */
	public ParallelQuad(int threads){
		
		this.threads = threads;
		skandium = new Skandium(threads);
		
		// Genero il modello, formato da una singola map
		Map<Matrix, Matrix> root = new Map<Matrix, Matrix>(
				new SplitMatrix(threads),
				new ComputeQuad(),
				new MergeMatrix());
		
		stream = skandium.newStream(root);
	}
	
	/**
	 * Calcola l'Histogram thresholding di una matrice utilizzando il framework parallelo istanziato
	 * 
	 * @param m Matrice da calcolare
	 * @return Matrice calcolata
	 */
	public Matrix compute(Matrix m) throws InterruptedException, ExecutionException{
		Future<Matrix> result = stream.input(m);
		return result.get();
	}
	
	/**
	 * Metodo per la disattivazione del framework parallelo
	 */
	public void shutdown(){
		skandium.shutdown();
	}
	
	/**
	 * Esegue una computazione di test su uno stream di matrici che vengono generate casualmente a runtime
	 * 
	 * @param streamsize Dimensione dello stream di matrici
	 * @param sizeRow Numero di righe delle matrici
	 * @param sizeCol Numero di colonne delle matrici
	 * @param perc Percentuale di soglia delle matrici
	 */
	@SuppressWarnings("unchecked")
	public void testcompute(int streamsize, int sizeRow, int sizeCol, double perc) throws InterruptedException, ExecutionException{
		

		long time = System.currentTimeMillis();
		// long begintime = System.currentTimeMillis();
		
		System.out.println("#############################");
		System.out.println("Parallel Quadratic Computation - Threads: " + threads + " Streamsize: " + streamsize + " Matrixsize: " + sizeRow + " x " + sizeCol + " Percentage: " + perc);
		
		Matrix[] initmat = new Matrix[streamsize];
		for(int i = 0; i < streamsize; i++){
			initmat[i] = new Matrix(sizeRow, sizeCol, perc);
		}
		
		// System.out.println("Generation of matrix completed in: " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		
		Future<Matrix>[] results = new Future[streamsize];
		for(int i = 0; i < streamsize; i++){
			results[i] = (stream.input(initmat[i]));
		}
		
		
		for(int i = 0; i < streamsize; i++){
			results[i].get();
			// System.out.println("Computation of matrix #" + i + " completed in: " + (System.currentTimeMillis() - time));
			// time = System.currentTimeMillis();
		}
	
		System.out.println("Computation over in: " + (System.currentTimeMillis() - time));			
	}
}
