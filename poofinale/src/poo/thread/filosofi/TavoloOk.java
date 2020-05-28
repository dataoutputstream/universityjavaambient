package poo.thread.filosofi;

public class TavoloOk {
	private boolean forchetta[];
	private Object lock=new Object();
	public TavoloOk( int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		forchetta=new boolean[n];
		for( int i=0; i<forchetta.length; i++ )
			forchetta[i]=true;
	}
	public void ottieniForchette( int id ){
		if( id<0 || id>=forchetta.length )
			throw new IllegalArgumentException();
		synchronized( lock ){
			while( !forchetta[id] || ! forchetta[(id+1)%forchetta.length] )
				try{
					lock.wait();
				}catch( InterruptedException ie ){}
			forchetta[id]=false;
			forchetta[(id+1)%forchetta.length]=false;
		}
	}//ottieniForchette
	public void rilasciaForchette( int id ){
		if( id<0 || id>=forchetta.length )
			throw new IllegalArgumentException();		
		synchronized( lock ){
			forchetta[id]=true;
			forchetta[(id+1)%forchetta.length]=true;		
			lock.notifyAll();
		}
	}//rilasciaForchette
}//Tavolo
