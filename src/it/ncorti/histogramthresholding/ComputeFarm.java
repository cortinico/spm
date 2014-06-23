package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Execute;

/**
 * Metodo per il calcolo dell'istogramma su una singola sottomatrice
 * Da utilizzare come singolo worker all'interno di una farm
 * 
 * @author Nicola
 */
public class ComputeFarm implements Execute<Matrix, Matrix> {

	@Override
	public Matrix execute(Matrix m) throws Exception {
		//long begin = System.currentTimeMillis();
		//long time = System.currentTimeMillis();
		
		m.computeHistogram();
		//System.out.println("FARM WORKER - HISTO - " + (System.currentTimeMillis() - time));
		//time = System.currentTimeMillis();
		
		m.computeThreshold();
		//System.out.println("FARM WORKER - THRES - " + (System.currentTimeMillis() - time));
		//time = System.currentTimeMillis();
		m.computeUpdateMatrix();
		//System.out.println("FARM WORKER - UPDAT - " + (System.currentTimeMillis() - time));
		//System.out.println("FARM WORKER - TOTAL - " + (System.currentTimeMillis() - begin));
		return m;
	}
}
