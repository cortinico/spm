Histogram Thresholding
===

Histogram Thresholding è un software che permette di effettuare il calcolo della matrice di [sogliatura](http://it.wikipedia.org/wiki/Sogliatura) partendo da uno stream di matrici. Il software in particolare permette di effettuare il calcolo in modo parallelo, utilizzando svariati modelli in modo da poter fare una valutazione delle performance.

Il progetto fa parte del corso di Sistemi Distribuiti: Paradigmi e Modelli, della Laurea Magistrale in Informatica ([Pagina del corso](http://didawiki.cli.di.unipi.it/doku.php/magistraleinformaticanetworking/spm/start)).

Il progetto è stato realizzato in Java, utilizzando il framework parallelo [Skandium](https://github.com/mleyton/Skandium).

![Lenna](../../raw/master/doc/tex/lenna_readme.png)

## Documentazione

E' possibile apprendere tutti i dettagli del progetto leggendo la [Relazione del Progetto](../../raw/master/doc/tex/relazione.pdf) oppure consultanto la documentazione in formato [JavaDoc](http://cortinico.github.io/spm)

## Avvio della simulazione

Per avviare la simulazione seguire i passi seguenti

* Clonare il repository
```bash
git clone git@github.com:cortinico/spm.git
```
* Compilare il software
```bash
cd spm/
ant build
```
* Avviare la simulazione
```
ant <mode> [ -Dthread=numt ] [ -Dstream=numstr ] [ -Drow=nrow ] [ -Dcol=ncol ]
[ -Dthre=threshold ] [ -Dheap=heapsize ]
```

I parametri per l'avvio sono i seguenti:

| Parametro | Utilizzo |
| ---       | ---      |
|  -Dthread=  | Permette di impostare il numero di thread con cui invocare il software. Questo numero rappresenta di fatto il grado di parallelismo con cui viene fatto eseguire il software. Tale parametro non viene preso in considerazione nel caso di esecuzioni sequenziali. Il valore di default di questo parametro `e 4 per le computazioni parallele e 1 per le computazioni sequenziali.|
|  -Dstream=  | Permette di impostare la dimensione dello stream di input ovvero quanti oggetti di tipo matrice devono essere generati e calcolati. Il valore di default per questo parametro `e 1. |
|  -Drow=  | Permette di impostare la dimensione per righe delle matrici date in input. Il valore di default per questo parametro `e 5000.|
|  -Dcol=  | Permette di impostare la dimensione per colonne delle matrici date in input. Il valore di default per questo parametro `e 5000.|
|  -Dthre=  | Permette di impostare la percentuale di soglia su cui effettuare il calcolo. Il valore deve essere un numero reale compreso fra 0 e 1 e puo' essere espresso con il punto (ad esempio 0.45 per indicare il 45%). Il valore di default per questo parametro `e 0.50 |
|  -Dheap=  | Permette di impostare il valore massimo della memoria utilizzabile dalla JVM, in modo da permettere il calcolo di matrici piu' grandi. Il valore di default di questo campo `e 3072m (3 Gb).|

Le modalità disponibili sono invece:

| Modalità | Descrizione |
| ---       | ---      |
| **Sequential** | Il calcolo viene effettuato in sequenziale, utilizzando un algoritmo a complessità lineare |
| **SequentialQuad** | Il calcolo viene effettuato in sequenziale, utilizzando un algoritmo a complessità quadratica |
| **Parallel** | Il calcolo viene effettuato in parallelo tramite il modello *Pipeline*, utilizzando un algoritmo a complessità lineare|
| **ParallelFarm** | Il calcolo viene effettuato in parallelo tramite il modello *Farm*, utilizzando un algoritmo a complessità lineare|
| **ParallelQuad** | Il calcolo viene effettuato in parallelo tramite il modello *Map*, utilizzando un algoritmo a complessità quadratica|











