package poo.banca;
import java.io.*;
public class ContoBancario implements Externalizable{
    private String numero;
    protected double bilancio=0;
    public ContoBancario(){}
	public ContoBancario( String numero ){
		//pre: numero!=null
		this.numero=numero;
	}
	public ContoBancario( String numero, double bilancio ){ 
		//pre: numero!=null && bilancio>=0
		this.numero=numero; this.bilancio=bilancio;
	}
	public void deposita( double quanto ){
		if( quanto<=0 ) 
			throw new IllegalArgumentException("Ammontare invalido.");
		bilancio=bilancio+quanto; //o bilancio +=ammontare;
	}//deposita
    public boolean preleva( double quanto ){
	    if( quanto<=0 ) 
	    	throw new IllegalArgumentException("Ammontare invalido.");
		if( quanto>bilancio ) return false;
      	bilancio -= quanto;
		return true;
	}//preleva
	public double saldo(){
		return bilancio;
	}//saldo
	public String conto(){
		return numero;
	}//conto
    public String toString(){
	  return String.format( "conto=%s bilancio=E %1.2f", numero, bilancio );
    }//toString
    public boolean equals( Object o ){
      if( !(o instanceof ContoBancario) ) return false;
      if( o==this ) return true;
      ContoBancario c=(ContoBancario)o;
      return numero.equals( c.numero );
    }//equals
    
    public void readExternal( ObjectInput in ) throws IOException{	    	
    	numero=in.readUTF();
    	bilancio=in.readDouble();
    }//readExternal
    
    public void writeExternal( ObjectOutput out ) throws IOException{	
    	out.writeUTF( numero );
    	out.writeDouble( bilancio );
    }//writeExternal
    
}//ContoBancario

