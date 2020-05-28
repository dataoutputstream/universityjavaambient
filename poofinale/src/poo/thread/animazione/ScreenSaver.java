//
//  Esempio di finestra con animazione creata con un thread
//
//*********************************************************
package poo.thread.animazione;
import java.awt.*;
import javax.swing.*;

class FinestraRepaint extends JFrame{    

   private Pannello p=null;
   private Font f=new Font("Helvetica", Font.BOLD, 20);
   private Color col=new Color( /*red*/57, /*green*/128, /*blu*/110 );
   private int x, y;
   
   public FinestraRepaint(){
      setTitle("Finestra con Repaint");
      setSize(400,200);
      setLocation(50,200);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      add( p=new Pannello() );
      Animatore a=new Animatore();
      a.start();
   }

    class Pannello extends JPanel{ //esempio di JComponen
      public void paintComponent(Graphics g){
    	 super.paintComponent(g); //resetta background
         System.out.println("paintComponent chiamata");
         setBackground( Color.BLACK );
         g.setFont( f );
         g.setColor( col );
         g.drawString( "Swing", x, y );
      }
   }//Pannello
    
   class Animatore extends Thread{
	   
	   public void run(){
		   while( true ){
			   int nx=(int)(Math.random()*400);
			   int ny=(int)(Math.random()*200);
			   x=nx; y=ny;
			   p.repaint();
			   try{
				   Thread.sleep(1000);
			   }catch(InterruptedException e ){}
		   }
	   }
   }

}//FinestraRepaint

public class ScreenSaver{    
   public static void main( String []args ){
		  EventQueue.invokeLater( new Runnable(){
			  public void run(){
			      JFrame f=new FinestraRepaint();
			      f.setVisible(true);			  
			  }
		  });	   
   }//main
}//ScreenSaver
