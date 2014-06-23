package it.ncorti.histogramthresholding;
/**
 * Classe per rappresentare una generica matrice di dimensione n*m.
 * La matrice offre inoltre i metodi per calcolare l'istogramma di un'immagine
 * 
 * @author Nicola
 */
public class Matrix {

	/** Matrice di interi */
	public int[][] mat;
	/** Numero righe */
	public int sizeRow;
	/** Numero colonne */
	public int sizeCol;
	
	// PARAMETRI SOTTOMATRICE
	/** Riga di inizio */
	public int beginRow = 0;
	/** Colonna di inizio */
	public int beginCol = 0;
	/** Riga di fine */
	public int endRow;
	/** Colonna di fine */
	public int endCol;
	
	/** Percentuale x Numero di pixel dell'immagine */
	public int pixperc = 0;
	/** Histogramma dell'immagine */
	public int[] histo = new int[PIXEL_MAX_VALUE];
	// Valore di soglia dell'immagine //
	private int threshold = 0;
	
	
	
	/** Dimensione massima dei pixel */
	public final static int PIXEL_MAX_VALUE = 255;
	
	
	////////////////////////////////////
	//		COSTRUTTORI
	////////////////////////////////////
	
	/**
	 * Costruttore standard per generare un oggetto di tipo Matrix, con sottomatrice e valore di soglia
	 * 
	 * @param mat Matrice di partenza
	 * @param sizeRow Numero di righe
	 * @param sizeCol Numero di colonne
	 * @param bRow Indice di partenza della riga
	 * @param bCol Indice di partenza della colonna
	 * @param eRow Indice di fine della riga
	 * @param eCol Indice di fine della colonna
	 * @param perc Percentuale di soglia
	 */
	public Matrix(int[][] mat, int sizeRow, int sizeCol, int bRow, int bCol, int eRow, int eCol, double perc){
		this.mat = mat;
		this.sizeRow = sizeRow;
		this.sizeCol = sizeCol;
		this.beginCol = bCol;
		this.beginRow = bRow;
		this.endCol = eCol;
		this.endRow = eRow;
		this.pixperc = (int) ((sizeRow*sizeCol)*perc);
		for(int i = 0; i<Matrix.PIXEL_MAX_VALUE; i++){
			this.histo[i] = 0;
		}
	}
	
	
	/**
	 * Costruttore per generare una nuova matrice m*n, senza sottomatrice e con
	 * valore di soglia
	 *  
	 * @param mat Matrice di partenza
	 * @param sizeRow Numero di righe
	 * @param sizeCol Numero di colonne
	 * @param perc Percentuale di soglia
	 */
	public Matrix(int[][] mat, int sizeRow, int sizeCol, double perc){
		this(mat,sizeRow,sizeCol, 0,0,sizeRow,sizeCol,perc);
	}
		
	
	/**
	 * Costruttore per generare una nuova matrice quadrata, senza sottomatrice e con
	 * valore di soglia
	 * 
	 * @param mat Matrice di partenza
	 * @param size Dimensione della matrice
	 * @param perc Percentuale di soglia
	 */
	public Matrix(int[][] mat, int size, double perc){
		this(mat,size,size, 0,0,size,size,perc);
	}
	
	
	/**
	 * Costruttore per generare una nuova matrice m*n con numeri casuali nel range [0, PIXEL_MAX_VALUE]
	 * che permette di indicare un valore di soglia
	 * 
	 * @param sizeRow Numero di righe
	 * @param sizeCol Numero di colonne
	 * @param perc Percentuale di soglia
	 */
	public Matrix(int sizeRow, int sizeCol, double perc){
		
		int[][] rmat = new int[sizeRow][sizeCol];
		for(int i = 0; i<sizeRow; i++){
			for(int j = 0; j<sizeCol; j++){
				rmat[i][j] = (int) (Math.random() * PIXEL_MAX_VALUE);
			}
		}
		
		this.mat = rmat;
		this.sizeRow = sizeRow;
		this.sizeCol = sizeCol;
		this.beginCol = 0;
		this.beginRow = 0;
		this.endCol = sizeRow;
		this.endRow = sizeCol;
		this.pixperc = (int) ((sizeRow*sizeCol)*perc);
		
		for(int i = 0; i<Matrix.PIXEL_MAX_VALUE; i++){
			this.histo[i] = 0;
		}
	}

	/**
	 * Costruttore per generare una nuova matrice quadrata con numeri casuali nel range [0, PIXEL_MAX_VALUE]
	 * che permette di indicare un valore di soglia
	 * 
	 * @param size Dimensione della matrice
	 * @param perc Percentuale di soglia
	 */
	public Matrix(int size, double perc){
		this(size,size,perc);
	}
	
	/**
	 * Funzione per popolare l'histogramma con i dati relativi alle occorrenze di ogni
	 * singolo numero nella matrice.
	 */
	public void computeHistogram(){
		for(int i = beginRow; i<endRow; i++){
			for(int j = beginCol; j<endCol; j++){
				histo[mat[i][j]]++;
			}
		}
		
	}
	
	/**
	 * Funzione per il calcolo del valore di soglia della matrice.
	 * 
	 * Partendo dalla percentuale indicata, si calcola il numero di pixel relativo alla percentuale
	 * (calcolo effettuato nei costruttori)
	 * 
	 * Si procede a scandire l'istogramma, sommando ad ogni iterazione il numero di occorenze trovate per
	 * ogni elemento e si procede fino a quando non si e' raggiunto il valore di soglia 
	 */
	public void computeThreshold(){
		
		// Indice per memorizzare l'elemento attuale
		int result = 0;
		// Contatore dove si accumulano il numero delle occorrenze
		int counter = 0;
		
		
		while (result < Matrix.PIXEL_MAX_VALUE && counter < pixperc){
			counter+=histo[result];
			result++;		
		}
		this.threshold = result;
		
	}
	
	
	/**
	 * Funzione per il calcolo della matrice risultante formata da 0/1 in base al fatto che il
	 * valore sia minore o maggiore della soglia
	 * 
	 */
	public void computeUpdateMatrix(){
		for(int i = beginRow; i<endRow; i++){
			for(int j = beginCol; j<endCol; j++){
				mat[i][j] = ((mat[i][j] < threshold) ? 1 : 0 );
			}
		}
	}
	
	/**
	 * Funzione per la generazione di una sottomatrice.
	 * Vengono copiati anche il valore pixel*percentuale e il valore di soglia
	 * 
	 * @param beginRow Riga di inizio
	 * @param beginCol Colonna di inizio
	 * @param endRow Riga di fine
	 * @param endCol Colonna di fine
	 * @return Sottomatrice
	 */
	public Matrix subMatrix(int beginRow, int beginCol, int endRow, int endCol){
		
		Matrix newmat = new Matrix(mat, sizeRow, sizeCol, beginRow, beginCol, endRow, endCol, 0);
		newmat.pixperc = this.pixperc;
		newmat.threshold = this.threshold;
		return newmat;
	}

	/** Vecchia funzione di calcolo che risultava O(n'4) e viene quindi utilizzata
	 *  solo per valutare il miglioramento delle performance del nuovo algoritmo sequenziale
	 */
	public Matrix compute(){
		

		int[][] histomat = new int[sizeRow][sizeCol];
		
		int count, k, l;
		
		for(int i = beginRow; i<endRow; i++){
			for(int j = beginCol; j<endCol; j++){
			
				count = 0;
				k = 0;
				while(k<sizeRow && count < pixperc){
					l = 0;
					while(l<sizeCol && count < pixperc){
						if (mat[k][l] > mat[i][j]) count++;
						l++;
					}
					k++;
				}
				histomat[i][j] = ((count < pixperc) ? 0 : 1);
			}
		}
		return new Matrix(histomat, sizeRow, sizeCol, beginRow, beginCol, endRow, endCol, pixperc);
	}
	
	
	/**
	 * Funzione per la stampa della matrice su standard output.
	 */
	public void print(){
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		String ret = new String();
		ret = "Printing matrix rows= " + sizeRow + " cols= " + sizeCol + " \n";
		ret = ret + "Submatrix beginRow= " + beginRow + " beginCol= " + beginCol + " endRow= " + endRow + " endCol= " + endCol + " \n"; 
		for(int i = beginRow; i<endRow; i++){
			for(int j = beginCol; j<endCol; j++){
				ret = ret + mat[i][j] + " ";
			}
			ret = ret + "\n";
		}
		ret = ret + "Percentage * Numpixel = " + pixperc + "\n";
		return ret;
	}
}
