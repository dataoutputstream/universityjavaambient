/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

import java.util.*;

/**
 *
 * @author Sergio
 */
public class GrafiUtil {
    public static void main(String[] args){
        GrafoNO g = new GrafoMatriceNO(5);
        g.insArco(0, 1);
        g.insArco(0, 3);
        g.insArco(3, 4);
        g.insArco(4, 0);
/*        g.insArco(1, 0);
        g.insArco(1, 3);
        g.insArco(2, 3);
        g.insArco(3, 2);
        g.insArco(4, 2);*/

        Iterator<Arco> it = g.archi();
        while(it.hasNext()){
            Arco x = it.next();
            System.out.println("<"+x.getIn()+","+x.getOut()+">");
        }
        List<Integer> vis = visitaScandaglioRicorsiva(g,0);
        System.out.println(vis);

        boolean[] visitati = new boolean[g.n()];
        vis = visitaScandaglio(g,0, visitati);
        System.out.println(vis);

        visitati = new boolean[g.n()];
        vis = visitaVentaglio(g,0,visitati);
        System.out.println(vis);

        
        System.out.println("Aciclico:"+aciclicoNO(g));
        System.out.println("albero:"+alberoNO(g));
    }

    public static List<Integer> visitaScandaglioRicorsiva(Grafo g, int x){
        boolean[] visitati = new boolean[g.n()];
        return visitaScandaglioRicorsiva(g, x, visitati);
    }

    public static List<Integer> visitaScandaglioRicorsiva(Grafo g, int x, boolean[] visitati){
        List<Integer> ris = new LinkedList<Integer>();
        if (!visitati[x]){
            ris.add(x);
            visitati[x]=true;
            Iterator<Arco> ad = g.adiacenti(x);
            while (ad.hasNext()){
                ris.addAll(visitaScandaglioRicorsiva(g,ad.next().getOut(),visitati));
            }
        }
        return ris;
    }

    public static List<Integer> visitaVentaglio(Grafo g, int x, boolean[] visitati){
        List<Integer> ris = new LinkedList<Integer>();
        Queue<Integer> davisitare = new LinkedList<Integer>();
        davisitare.offer(x);
        while (!davisitare.isEmpty()){
            int i = davisitare.poll();
            if (!visitati[i]){
                visitati[i]= true;
                ris.add(i);
                Iterator<Arco> ad = g.adiacenti(i);
                while (ad.hasNext()){
                    davisitare.offer(ad.next().getOut());
                }
            }
        }

        return ris;
    }


    public static List<Integer> visitaScandaglio(Grafo g, int x,boolean[] visitati){
        List<Integer> ris = new LinkedList<Integer>();
        Stack<Iterator<Arco>> davisitare = new Stack<Iterator<Arco>>();
        visitati[x] = true;
        ris.add(x);
        davisitare.push(g.adiacenti(x));
        while (!davisitare.isEmpty()){
            Iterator<Arco> ad = davisitare.pop();
            if (ad.hasNext()){
                davisitare.push(ad);
                int proxnodo = ad.next().getOut();
                if(!visitati[proxnodo]){
                    visitati[proxnodo]= true;
                    ris.add(proxnodo);
                    davisitare.push(g.adiacenti(proxnodo));
                }
            }
        }

        return ris;
    }

    public static boolean aciclico(Grafo g){
        boolean[] tolti = new boolean[g.n()];
        int[] gradientrata = calcolaGradiEntrata(g);
        int x = cercaNodoGradoZero(gradientrata,tolti);
        while(x!=-1){
            tolti[x]=true;
            Iterator<Arco> ad = g.adiacenti(x);
            while(ad.hasNext()){
                Arco a = ad.next();
                gradientrata[a.getOut()]--;
            }
            x = cercaNodoGradoZero(gradientrata,tolti);
        }
        boolean ris = true;
        for(int i=0;i<tolti.length;i++)
            if(!tolti[i])
                ris=false;
        return ris;
    }

    private static int[] calcolaGradiEntrata(Grafo g) {
        int[] gradi = new int[g.n()];
        Iterator<Arco> archi = g.archi();
        while(archi.hasNext())
            gradi[archi.next().getOut()]++;
        return gradi;
    }

    private static int cercaNodoGradoZero(int[] gradientrata, boolean[] tolti) {
        for(int i=0; i<gradientrata.length;i++)
            if(!tolti[i]&&gradientrata[i]==0)
                return i;
        return -1;
    }

    @SuppressWarnings("empty-statement")
    public static int componentiConnesse(GrafoNO g){
        int comp = 0;
        boolean[] visitati = new boolean[g.n()];
        int i = 0;
        while(i<g.n()){
            comp++;
            visitaScandaglio(g,i,visitati);
            for(;i<g.n()&& visitati[i]; i++);
        }
        return comp;
    }

    public static boolean aciclicoNO(GrafoNO g){
        int comp = componentiConnesse(g);
        return (g.m()==g.n()-comp);
    }

    public static boolean connessoNO(GrafoNO g){
        boolean[] visitati = new boolean[g.n()];
        visitaScandaglio(g,0,visitati);
        for(int i =0;i<g.n(); i++)
            if(!visitati[i])
                return false;
        return true;
    }

    public static boolean alberoNO(GrafoNO g){
        return (g.m()==g.n()-1) && connessoNO(g);
    }
}
