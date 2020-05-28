package poo.thread.buffer.priorita;
import java.util.*;
public class MailboxPrioritaria<T> {

	private static class Processo implements Comparable<Processo>{
		Thread thread;
		int id;
		public Processo( Thread t, int id ){ this.thread=t; this.id=id; }
		public int compareTo( Processo p ){
			return p.id-this.id;
		}
	}//Processo

	private PriorityQueue<Processo> listaProd=new PriorityQueue<Processo>();
	private PriorityQueue<Processo> listaCons=new PriorityQueue<Processo>();
	private T[] buffer;
	private int in=0, out=0, size=0;
	
	@SuppressWarnings("unchecked")
	public MailboxPrioritaria( int n ){ 
		if( n<=0 ) throw new IllegalArgumentException();
		buffer=(T[]) new Object[n];
	}
	
	private boolean produttoreDeveDormire(){
		if( size==buffer.length || listaProd.peek().thread!=Thread.currentThread() )
			return true;
		return false;
	}//produttoreDeveDormire
	private boolean consumatoreDeveDormire(){
		if( size==0 || listaCons.peek().thread!=Thread.currentThread() )
			return true;
		return false;
	}//consumatoreDeveDormire	
	
	public synchronized void put( int id, T msg ){
		listaProd.add( new Processo( Thread.currentThread(), id ) );
		while( produttoreDeveDormire() )
			try{ wait(); }catch( InterruptedException e ){}
		listaProd.poll();
		buffer[in]=msg;
		in=(in+1)%buffer.length; size++;
		notifyAll();
	}//put
	
	public synchronized T get( int id ){
		listaCons.add( new Processo( Thread.currentThread(), id ) );
		while( consumatoreDeveDormire() )
			try{ wait(); }catch( InterruptedException e ){}
		listaCons.poll();
		T msg=buffer[out];
		out=(out+1)%buffer.length; size--;
		notifyAll();
		return msg;
	}//get
		
}//MailboxPrioritaria
