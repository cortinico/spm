package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Execute;

/**
 * Metodo per il calcolo dell'istogramma su una singola sottomatrice
 * 
 * @author Nicola
 */
public class ComputeHistogram implements Execute<Matrix, Matrix> {

	@Override
	public Matrix execute(Matrix m) throws Exception {
		
		//long time = System.currentTimeMillis();
		
		m.computeHistogram();
		
		//System.out.println("Compute Histogram over in: " + (System.currentTimeMillis() - time));
		return m;
	}

}
