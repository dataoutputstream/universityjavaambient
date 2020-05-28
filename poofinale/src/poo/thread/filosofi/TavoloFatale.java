package poo.thread.filosofi;

public class TavoloFatale{
	private boolean forchetta[];
	public TavoloFatale( int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		forchetta=new boolean[n];
		for( int i=0; i<forchetta.length; i++ )
			forchetta[i]=true;
	}
	public synchronized void ottieniForchette( int id ){
		if( id<0 || id>=forchetta.length )
			throw new IllegalArgumentException();
		while( !forchetta[id] )
			try{ wait(); }catch( InterruptedException ie ){}
		forchetta[id]=false;
		while( !forchetta[(id+1)%forchetta.length] )
			try{ wait(); }catch( InterruptedException ie ){}		
		forchetta[(id+1)%forchetta.length]=false;
	}//ottieniForchette
	public synchronized void rilasciaForchette( int id ){
		if( id<0 || id>=forchetta.length )
			throw new IllegalArgumentException();		
		forchetta[id]=true;
		forchetta[(id+1)%forchetta.length]=true;		
		notifyAll();
	}//rilasciaForchette
}//Tavolo
