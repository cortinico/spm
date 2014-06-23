package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Execute;

/**
 * Metodo per il calcolo della matrice aggiornata.
 * Calcola l'update della matrice tramite un algoritmo che calcola in O(n'4)
 * quindi risulta molto inefficente.
 * 
 * @author Nicola
 */
public class ComputeQuad implements Execute<Matrix, Matrix> {

	@Override
	public Matrix execute(Matrix m) throws Exception {
		return m.compute();
	}

}
