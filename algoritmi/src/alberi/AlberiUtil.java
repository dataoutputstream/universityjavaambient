/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alberi;

import java.util.*;

/**
 *
 * @author Sergio
 */
public class AlberiUtil {
    public static AlberoBImpl<Long> creaAlberoB(long h){
        AlberoBImpl<Long> a = new AlberoBImpl<Long>();
        a.setVal(Math.round(Math.random()*10));
        h--;
        for (int i = 0; i<2; i++){
            long h1 = Math.round(Math.random()*h);
            if (h1>0){
                if(i==0)
                    a.setSin(creaAlberoB(h1));
                else
                    a.setDes(creaAlberoB(h1));            }
        }
        return a;
    }

    public static void stampa(AlberoB<Long> a, String pref){
        if(a==null)
            return;
        stampa(a.getSin(),pref+"----");
        System.out.println(pref+a.getVal());
        stampa(a.getDes(),pref+"++++");
    }

    public static boolean presente(AlberoB<Long> a, long x){
        if (a==null)
            return false;
        return (a.getVal()==x)||presente(a.getSin(),x)||presente(a.getDes(),x);
    }

    public static int altezza(AlberoB<Long> a){
        if(a==null)
            return 0;
        int hs = altezza(a.getSin());
        int hd = altezza(a.getDes());
        if (hs>hd)
            return 1+hs;
        return 1+hd;
    }
    

	public static List<Long> visitaAnticipata(AlberoB<Long> a){
        if (a==null)
            return new LinkedList<Long>();
        List<Long> l = new LinkedList<Long>();
        l.add(a.getVal());
        l.addAll(visitaAnticipata(a.getSin()));
        l.addAll(visitaAnticipata(a.getDes()));
        return l;
    }

    public static List<Long> visitaPosticipata(AlberoB<Long> a){
        if (a==null)
            return new LinkedList<Long>();
        List<Long> l = new LinkedList<Long>();
        l.addAll(visitaPosticipata(a.getSin()));
        l.addAll(visitaPosticipata(a.getDes()));
        l.add(a.getVal());
        return l;
    }

    public static List<Long> visitaInfissa(AlberoB<Long> a){
        if (a==null)
            return new LinkedList<Long>();
        List<Long> l = new LinkedList<Long>();

        l.addAll(visitaInfissa(a.getSin()));
        l.add(a.getVal());
        l.addAll(visitaInfissa(a.getDes()));
        return l;
    }

    public static List<Long> visitaLivelli(AlberoB<Long> a){
        if (a==null) return new LinkedList<Long>();
        List<Long> ris = new LinkedList<Long>();

        Queue<AlberoB<Long>> davisitare = new LinkedList<AlberoB<Long>>();
        davisitare.offer(a);
        while(!davisitare.isEmpty()){
            AlberoB<Long> corr = davisitare.poll();
            ris.add(corr.getVal());
            if (corr.getSin()!=null)
                davisitare.offer(corr.getSin());
            if (corr.getDes()!=null)
                davisitare.offer(corr.getDes());
        }
        return ris;
    }
    
    public static void main(String[] args){
        AlberoB<Long> x = creaAlberoB(8);
        stampa(x,"");

        System.out.println("C'è 10? "+presente(x,5));
        System.out.println("Altezza:"+altezza(x));

        List<Long> l = visitaAnticipata(x);
        System.out.println("Anticipata:  "+l);
        l = visitaInfissa(x);
        System.out.println("Infissa:     "+l);
        l = visitaPosticipata(x);
        System.out.println("Posticipata: "+l);
        l = visitaLivelli(x);
        System.out.println("Livelli:     "+l);
        
    }
}
