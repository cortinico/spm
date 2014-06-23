package it.ncorti.histogramthresholding;

/**
 * Classe per l'esecuzione di calcoli sequenziali.
 * La classe offre solamente metodi statici e non puo' essere istanziata.
 * 
 * @author Nicola
 *
 */
public class Sequential {
	
	private Sequential(){
	}

	/**
	 * Calcola l'Histogram thresholding di una matrice in sequenziale
	 * 
	 * @param m Matrice da calcolare
	 * @return Matrice calcolata
	 */
	public static Matrix compute(Matrix m){
		//long begin = System.currentTimeMillis();
		//long time = System.currentTimeMillis();
		
		m.computeHistogram();
		
		//System.out.println("SEQ WORKER - HISTO - " + (System.currentTimeMillis() - time));
		//time = System.currentTimeMillis();
		
		m.computeThreshold();
		
		//System.out.println("SEQ WORKER - THRES - " + (System.currentTimeMillis() - time));
		//time = System.currentTimeMillis();
		
		m.computeUpdateMatrix();
		
		//System.out.println("SEQ WORKER - UPDAT - " + (System.currentTimeMillis() - time));
		//System.out.println("SEQ WORKER - TOTAL - " + (System.currentTimeMillis() - begin));
		return m;
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
		System.out.println("Sequential Linear Computation - Streamsize: " + stream + " Matrixsize: " + sizeRow + " x " + sizeCol + " Percentage: " + perc);
		
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
