package it.ncorti.histogramthresholding;

/**
 * Classe per l'esecuzione di calcoli sequenziali.
 * 
 * I calcoli vengono effettuati tramite un algoritmo che risulta avere un costo O(n'4)
 * per cui risulta particolarmente inefficente.
 * 
 * La classe offre solamente metodi statici e non puo' essere istanziata.
 * 
 * @author Nicola
 *
 */
public class SequentialQuad {
	
	private SequentialQuad(){
	}

	/**
	 * Calcola l'Histogram thresholding di una matrice in sequenziale
	 * 
	 * @param m Matrice da calcolare
	 * @return Matrice calcolata
	 */
		
	public static Matrix compute(Matrix m){
		
		return m.compute();
		
	}
	
	/**
	 * Esegue una computazione di test su uno stream di matrici che vengono generate casualmente a runtime
	 * 
	 * @param stream Dimensione dello stream di matrici
	 * @param sizeRow Numero di righe delle matrici
	 * @param sizeCol Numero di colonne delle matrici
	 * @param perc Percentuale di soglia delle matrici
	 */
	public static void testcompute(int stream, int sizeRow, int sizeCol, double perc){

		
		long time = System.currentTimeMillis();
		// long begintime = System.currentTimeMillis();
		
		System.out.println("#############################");
		System.out.println("Sequential Quadratic Computation - Streamsize: " + stream + " Matrixsize: " + sizeRow + " x " + sizeCol + " Percentage: " + perc);
		
		Matrix[] initmat = new Matrix[stream];
		for(int i = 0; i < stream; i++){
			initmat[i] = new Matrix(sizeRow, sizeCol, perc);
		}
		
		// System.out.println("Generation of matrix completed in: " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();	
		
		for(int i = 0; i < stream; i++){
			
			compute(initmat[i]);
			
			// System.out.println("Computation of matrix #" + i + " completed in: " + (System.currentTimeMillis() - time));
			// time = System.currentTimeMillis();	
		}
		
		
		System.out.println("Computation over in: " + (System.currentTimeMillis() - time));
	}
}
