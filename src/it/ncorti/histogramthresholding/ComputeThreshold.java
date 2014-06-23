package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Execute;

/**
 * Metodo per il calcolo della matrice aggiornata in base al valore di soglia
 * 
 * @author nicola
 *
 */
public class ComputeThreshold implements Execute<Matrix, Matrix> {

	@Override
	public Matrix execute(Matrix m) throws Exception {
		
		//long time = System.currentTimeMillis();
		m.computeUpdateMatrix();
		
		//System.out.println("Submatrix Update over in: " + (System.currentTimeMillis() - time));

		return m;
	}

}
