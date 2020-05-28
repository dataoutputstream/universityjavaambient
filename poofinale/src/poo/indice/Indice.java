package poo.indice;

interface Indice extends Iterable<Parola>{
	int size();
	int occorrenze( String ortografia );
	void add( String ortografia, int numeroRiga );
}//Indice
