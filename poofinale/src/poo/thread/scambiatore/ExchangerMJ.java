package poo.thread.scambiatore;

public class ExchangerMJ<T> implements Exchanger<T>{
	private T dato;
	private boolean partner = false, rilascio = false;
	private Object lock = new Object();
	public T exchange( T msg ){
		synchronized( lock ){
			while( rilascio )//per rientro "veloce"
				try{ lock.wait(); }
				catch(InterruptedException e){}
			T x=null;
			if( !partner ){
				dato = msg; partner=true;
				while( partner )
					try{ lock.wait(); }catch(InterruptedException e){}
				x = dato; rilascio=false;
				lock.notify();
			}
			else{
				x=dato; dato=msg; partner=false; rilascio=true;
				lock.notify();
			}
			return x;
		}//synchronized
	}//exchange
}//ExchangerMJ
