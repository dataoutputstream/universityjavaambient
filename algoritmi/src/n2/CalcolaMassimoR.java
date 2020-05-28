/**
 * 
 */
package n2;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
// Sia assegnato un vettore A di interi di dimensione N. Scrivere una funzione
// ricorsiva che calcoli il massimo valore degli elementi di A.
class CalcolaMassimoR
{

	/**
	 * @param args
	 */
	static int massimo(int vettore[], int i)
	{
		//sono sull'ultimo elemento condizione di uscita
		if (i + 1 == vettore.length)
			//ritorno l'elemeno ulrtimo
			return vettore[i];
		//il massimo è l'elemento che viene dopo (supposizione) 
		//in questo modo gestisco tutti gi elementi
		int max = massimo(vettore, i + 1);
		
		//al ritorno dalla chiamata ricorsiva verifico che max sia minore dell'elemento attuale alla posizione 
		//della chiamata ricorsiva relativa se cosi non è allora l'elemento maggiore è qullo in posizione i
		//lo rimando indietro achiamante che farà lo stesso procedimento
		if (max < vettore[i])
			max = vettore[i];
		return max;
	}// massimo

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int vettore[] = { 1, 20, 300, 4, 6, 10, 2, 20, 1, 3, 1 };
		System.out.println(massimo(vettore, 0));
	}

}
