/**
 * 
 */
package n4;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class Numero
{
	private int valore;

	public Numero(int valore)
	{
		this.valore = valore;
	}

	public Numero()
	{
		this(0);
	}

	public void setValore(int nuovoValore)
	{
		valore = nuovoValore;
	}

	public String toString()
	{
		return "" + valore;
	}

	public void incrementa()
	{
		valore++;
	}
}
