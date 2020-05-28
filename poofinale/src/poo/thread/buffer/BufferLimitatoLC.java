package poo.thread.buffer;
import poo.util.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class BufferLimitatoLC<T> extends BufferLimitato<T>{
	//Si utilizzano i concetti di Lock/Condition di java.util.concurrent.locks.*
	//che sono direttamente costruiti sul monitor nativo di Java
	//Una condition è legata ad un certo lucchetto e rappresenta
	//un distinto dormitorio
	//Rispetto al monitor nativo in cui di norma si utilizza un solo
	//dormitorio comune, qui e' possibile organizzare l'attesa
	//su piu' condition in modo che il risveglio possa essere
	//diretto ai processi di UNA condition
	//Metodi utili:
	//Costruttore della classe ReentrantLock(): per creare un oggetto Lock
	//Metodi di Lock: lock()/unlock(), newCondition() quest'ultimo che crea una condition
	//appartenente ad un certo  lucchetto
	//Metodi di Condition: await(), signal(), signalAll() che hanno
	//semantica simile ai metodi wait(), notify() e notifyAll() di Object, solo
	//che si riferiscono ad un dormitorio specifico ossia una condition
	//signal() sveglia UN processo (non si sa quale) in attesa sulla condition
	//signalAll() sveglia tutti i processi in attesa sulla condition
	//Si presti attenzione allo stile suggerito di programmazione
	private LinkedList<Thread> codaProduttori=new LinkedList<Thread>();
	private LinkedList<Thread> codaConsumatori=new LinkedList<Thread>();
	private Lock lucchetto=new ReentrantLock();
	private Condition attesaProd=lucchetto.newCondition();
	private Condition attesaCons=lucchetto.newCondition();
	public BufferLimitatoLC( int n ){
		super(n);
	}//BufferLimitatoLC
	private boolean produttoreDeveDormire(){
		if( isFull() || codaProduttori.getFirst()!=Thread.currentThread() )
			return true;
		return false;
	}//produttoreDeveDormire
	private boolean consumatoreDeveDormire(){
		if( isEmpty() || codaConsumatori.getFirst()!=Thread.currentThread() )
			return true;
		return false;
	}//consumatoreDeveDormire
	public void put( T elem ){
		lucchetto.lock();
		try{
			codaProduttori.add( Thread.currentThread() );
			while( produttoreDeveDormire() ){
				try{
					attesaProd.await();
				}catch( InterruptedException e ){}
			}
			codaProduttori.removeFirst();
			super.put(elem);
			attesaCons.signalAll();
		}finally{
			lucchetto.unlock();
		}
	}//put
	public T get(){
		lucchetto.lock();
		try{
			codaConsumatori.add( Thread.currentThread() );
			while( consumatoreDeveDormire() ){
				try{
					attesaCons.await();
				}catch( InterruptedException e ){}
			}
			codaConsumatori.removeFirst();
			T x=super.get();
			attesaProd.signalAll();
			return x;
		}finally{
			lucchetto.unlock();
		}
	}//put	
	public boolean isEmpty(){
		lucchetto.lock();
		try{
			return super.isEmpty();
		}finally{ lucchetto.unlock(); }
	}//isEmpty
	public boolean isFull(){
		lucchetto.lock();
		try{
			return super.isFull();
		}finally{ lucchetto.unlock(); }
	}//isFull
	public int size(){ 
		lucchetto.lock();
		try{
			return super.size(); 
		}finally{ lucchetto.unlock(); }
	}//size
	public void clear(){ 
		lucchetto.lock();
		try{		
			super.clear();	
		}finally{ lucchetto.unlock(); }
	}//clear	
}//BufferLimitatoLC
