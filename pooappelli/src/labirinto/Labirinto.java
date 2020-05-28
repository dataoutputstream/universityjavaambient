package labirinto;

import java.util.*;
import java.io.*;

public class Labirinto{
    private int[][] mappa;
    private boolean trovataSoluzione = false;



    public boolean caricaFile(File f) throws IOException{
        if(!f.exists())
            return false;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea = br.readLine(); 
        if(linea == null || !linea.matches("[\\+\\-]?\\d+"))
            return false;
        int n = Integer.parseInt(linea);
        if(n < 1 || n > 9999)
            return false;
        mappa = new int[n+1][4];
        int cont = 0;
        for(linea = br.readLine(); linea != null; linea = br.readLine(), cont++){
            if(!linea.matches("\\d+([ \\-\\$]\\d+){4}"))
                return false;
            StringTokenizer st = new StringTokenizer(linea, " -$");
            n = Integer.parseInt(st.nextToken());
            if(n<1 || n>mappa.length)
                return false;
            for(int i = 0; i < 4; i++){
                int m = Integer.parseInt(st.nextToken());
                if(m<0 || m>mappa.length && m != 9999 || m==n)
                    return false;
                mappa[n][i] = m;
            }
        }
        return cont == mappa.length-1;
    }

    public boolean risolvi(int start){
        trovataSoluzione = false;
        LinkedList<Integer> percorso = new LinkedList<Integer>();
        percorso.add(start);
        trovaPercorso(start, percorso);
        return trovataSoluzione;
    }

    private void trovaPercorso(int stanza, LinkedList<Integer> percorso){
        for(int i : mappa[stanza]){
            if(i != 0 && !percorso.contains(i)){
                percorso.addLast(i);
                if(i == 9999){
                    System.out.println(percorso);
                    trovataSoluzione = true;
                }
                else
                    trovaPercorso(i, percorso);
                percorso.removeLast();
            }
        }
    }

    public static void main(String... args) throws IOException{
    	Labirinto l = new Labirinto();
        Scanner sc = new Scanner(System.in);
        if(l.caricaFile(new File(sc.nextLine()))){
            int i = sc.nextInt();
            if(!l.risolvi(i))
                System.out.println("nessuna soluzione");
        }
        else
            System.out.println("file non valido");
    }
}
