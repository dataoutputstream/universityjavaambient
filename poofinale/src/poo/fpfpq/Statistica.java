package poo.fpfpq;
import java.io.*;
public interface Statistica {
	void raccoltaInfo( File f ) throws IOException;
	String[] parolaPiuMenoVerosimile( String k );
}//Statistica
