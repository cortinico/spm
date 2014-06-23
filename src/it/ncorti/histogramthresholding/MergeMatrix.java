package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Merge;

/**
 * Classe per la fusione di sottomatrici in una singola matrice
 * Questa classe implementa l'interfaccia Merge ed e' pensata per funzionare all'interno di uno
 * skeleton di tipo Map
 * 
 * @author Nicola
 *
 */
public class MergeMatrix implements Merge<Matrix, Matrix> {
		
	/**
	 * Costrutture principale
	 */
	public MergeMatrix(){
	}
	
	/**
	 * Metodo per il merge di un array di sottomatrici in una singola matrice
	 */
	@Override
	public Matrix merge(Matrix[] param) throws Exception {
		
		//long time = System.currentTimeMillis();
		
		
		// Le matrici vengono fuse con la prima dell'array
		for(int i = 1; i<param.length; i++){
			mergeMatrix(param[0], param[i]);
		}
		param[0].beginCol = 0;
		param[0].beginRow = 0;
		param[0].endRow = param[0].sizeRow;
		param[0].endCol = param[0].sizeCol;
		
		
		//System.out.println("Matrix Update Merging computed in " + (System.currentTimeMillis()-time));
		
		return param[0];
	}
	
	/**
	 * Metodo privato per la fusione fra due matrici
	 * @param host Matrice che ospitera' i nuovi valori
	 * @param toCopy Matrice da cui copiare i valori
	 */
	private void mergeMatrix(Matrix host, Matrix toCopy){
		for(int i = toCopy.beginRow; i<toCopy.endRow; i++){
			for(int j = toCopy.beginCol; j<toCopy.endCol; j++){
				host.mat[i][j] = toCopy.mat[i][j];
			}
		}
	}
}
