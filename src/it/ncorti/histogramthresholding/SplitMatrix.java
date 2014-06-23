package it.ncorti.histogramthresholding;
import cl.niclabs.skandium.muscles.Split;

/**
 * Classe per la divisione della matrice in sottomatrici per righe 
 * Questa classe implementa l'interfaccia Split ed e' pensata per funzionare all'interno di uno
 * skeleton di tipo Map
 * 
 * @author Nicola
 */
public class SplitMatrix implements Split<Matrix, Matrix> {

	// Numero delle sottomatrici da realizzare
	private int num;
	
	/**
	 * Costrutture principale
	 * @param num Numero delle sottomatrici desiderate
	 */
	public SplitMatrix(int num){
		this.num = num;
	}
	
	/**
	 * Metodo per dividere una matrice p in sottomatrici.
	 * Ritorna una array di sottomatrici
	 * 
	 * @param p Matrice da dividere
	 * @return Array di sottomatrici
	 */
	@Override
	public Matrix[] split(Matrix p) throws Exception {
		
		Matrix[] res = new Matrix[num];
		int rowsize = p.sizeRow/num;
		
//		long time = System.currentTimeMillis();
		
		
		int i;
		for (i = 0; i<num-1; i++){
			res[i] = p.subMatrix((i*rowsize), 0, (i+1)*rowsize, p.sizeCol);
		}
		res[num-1] = p.subMatrix((i*rowsize), 0, p.sizeRow, p.sizeRow);
				
//		System.out.println("Split over in: " + (System.currentTimeMillis() - time));
		return res;
	}

}

/*
 Divisione per colonne
 
  		Matrix[] res = new Matrix[num];
		int colsize = p.sizeCol/num;
		
		int i;
		for (i = 0; i<num-1; i++){
			res[i] = p.subMatrix(0, (i*colsize), p.sizeRow, (i+1)*colsize);
		}
		res[num-1] = p.subMatrix(0, (i*colsize), p.sizeRow, p.sizeCol); 
 */