package poo.banca;

public class Banca{
	//si assume un array di cliente non scalabile
	private ContoBancario []clientela;
	private int size=0;
	
	public Banca(){ this(50); }
	public Banca( int capacita ){
		if( capacita<=0 )
			throw new IllegalArgumentException();
		clientela=new ContoBancario[capacita];
	}
    public int size(){
    	return size;
    }
    public void svuota(){
    	for( int i=0; i<size; ++i ) 
    		clientela[i]=null;
    	size=0;
    }//svuota
    public void aggiungiConto( ContoBancario cb ){
    	if( size==clientela.length )
    		throw new RuntimeException("Overflow clientela!");
    	clientela[size]=cb;
    	size++;
    }//aggiungiConto
    public void rimuoviConto( ContoBancario cb ){
    	int i=indexOf( cb );
    	rimuoviConto( i );
    }//rimuoviConto
    public void rimuoviConto( int i ){
    	if( i<0 || i>=size ) return;
    	if( i!=-1 )
    	for( int j=i+1; j<size; ++j )
    		clientela[j-1]=clientela[j];
    	size--; 	
    }//rimuoviConto
    public int indexOf( ContoBancario cb ){
    	for( int i=0; i<size; ++i )
    		if( clientela[i].equals(cb) ) return i;
    	return -1;
    }
    public ContoBancario getConto( int i ){
    	if( i<0 || i>=size )
    		throw new IndexOutOfBoundsException();
    	return clientela[i]; //TODO
    }
    public String toString(){
    	String s="";
    	for( int i=0; i<size; ++i )
    		s+=clientela[i]+"\n";
    	return s;
    }//toString
}//Banca
