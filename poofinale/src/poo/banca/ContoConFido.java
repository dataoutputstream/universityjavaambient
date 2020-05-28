package poo.banca;
import java.io.*;
public class ContoConFido extends ContoBancario{
    private double fido=1000;
    private double scoperto=0;
    public ContoConFido(){}
    public ContoConFido( String numero ){
        super( numero );
    }
    public ContoConFido( String numero, double bilancio ){
        super( numero, bilancio );
    }
    public ContoConFido( String numero, double bilancio, double fido ){
        super( numero, bilancio );
        if( fido<=0 ) throw new IllegalArgumentException("Fido invalido.");
        this.fido=fido;
    }

    @Override
    public void deposita( double quanto ){
		if( quanto<=0 )
			throw new IllegalArgumentException("deposita: Ammontare invalido.");
		if( quanto<=scoperto ){
			scoperto -=quanto; return;
		}
		double residuo=quanto-scoperto;
		scoperto=0; super.deposita( residuo );
	}//deposita

    @Override
    public boolean preleva( double quanto ){
      if( quanto<=0 )
    	  throw new IllegalArgumentException("preleva: Ammontare invalido.");;
      if( quanto<=saldo() ){
		  super.preleva( quanto );
		  return true;
	  }
      if( quanto<=saldo()+fido-scoperto ){
		  double residuo=saldo();
		  super.preleva( residuo );
		  scoperto+=quanto-residuo;
		  return true;
	  }
  	  return false;
    }//preleva

    public double fido(){ return this.fido; }
    public void nuovoFido( double fido ){
    	if( fido<scoperto )
    		throw new IllegalArgumentException("Nuovo fido invalido.");
    	this.fido=fido;
    }//nuovoFido
    public double scoperto(){ return scoperto; }//scoperto
    public String toString(){
       return String.format( super.toString()+
              " fido=E %1.2f scoperto=E %1.2f", fido, scoperto );
    }//toString

    public void readExternal( ObjectInput in ) throws IOException{
    	super.readExternal(in);
    	fido=in.readDouble();
    	scoperto=in.readDouble();
    }//readExternal

    public void writeExternal( ObjectOutput out ) throws IOException{
    	super.writeExternal(out);
    	out.writeDouble(fido);
    	out.writeDouble(scoperto);
    }//writeExternal

}//ContoConFido

