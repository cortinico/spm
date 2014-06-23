package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Merge;

/**
 * Classe per la fusione di istogrammi calcolati dalle sottomatrici in un singolo istogramma
 * per permettere il calcolo del valore di soglia
 * 
 * Questa classe implementa l'interfaccia Merge ed e' pensata per funzionare all'interno di uno
 * skeleton di tipo Map
 * 
 * @author Nicola
 *
 */
public class MergeHistoMatrix implements Merge<Matrix, Matrix> {

	/**
	 * Costrutture principale
	 */
	public MergeHistoMatrix(){
	}
	
	/**
	 * Metodo che realizza la fusione degli istogrammi sopra l'istogramma della prima matrice.
	 * @param param Array di sottomatrici
	 * @return Matrice con istogramma calcolato
	 */
	@Override
	public Matrix merge(Matrix[] param) throws Exception {		
		
		//long time = System.currentTimeMillis();
		
		for(int i = 1; i < param.length; i++){
			for(int j = 0; j < Matrix.PIXEL_MAX_VALUE; j++){
				param[0].histo[j] += param[i].histo[j];
			}
		}
		
		//System.out.println("Histogram Merge computed in " + (System.currentTimeMillis() - time));
		//time = System.currentTimeMillis();
		
		param[0].beginCol = 0;
		param[0].beginRow = 0;
		param[0].endRow = param[0].sizeRow;
		param[0].endCol = param[0].sizeCol;
		param[0].computeThreshold();
		
		//System.out.println("Threshold computed in " + (System.currentTimeMillis() - time));
		
		return param[0];
	}
}
