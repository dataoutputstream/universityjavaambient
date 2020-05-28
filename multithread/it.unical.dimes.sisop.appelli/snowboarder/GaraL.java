package snowboarder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GaraL extends Gara{
	private Lock lock;
	private Condition partito;
	private LinkedList<SnowBoarder>lista=new LinkedList<SnowBoarder>();
	
	public GaraL(int n) {
		super();
		this.n=n;
		lock= new ReentrantLock();
		partito=lock.newCondition();
	}
	

	@Override
	protected void partenza(SnowBoarder s) throws InterruptedException {
		lock.lock();
		try {
			lista.add(s);
			while(!primo(s)) {
				partito.await();
			}
			lista.removeFirst();
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
		
	}

	private boolean primo(SnowBoarder s) {
		if(lista.getFirst().id==s.id) return true;
		return false;
	}


	@Override
	protected int arrivo(SnowBoarder s) throws InterruptedException {
		lock.lock();
		int i=-1;
		try {
		s.start();
		s.join();
		classifica.add(s);
		Collections.sort(classifica);
		return classifica.indexOf(s);
	}catch(Exception e) {
	}finally {
		lock.unlock();
		
	}return i;
	
}
	

	@Override
	protected boolean StampaeProssimo() throws InterruptedException {
		lock.lock();
		try {
			partito.signal();
			
		}catch(Exception e) {
			
		}finally {
		lock.unlock();	
		}
		if(classifica.size()!=n) {
			return true;
			}return false;
	}

	@Override
	protected void classificaFinale() throws InterruptedException {
		Collections.sort(classifica);
		System.out.println("Classifica della gara odierna: ");
		for(int i=0;i<n;i++) {
			System.out.println("Snowboarder: " +classifica.get(i).id+ " tempo: "+classifica.get(i).tempo+" Posto: "+(i+1));
		}
		
	}
	public static void main(String[]args) {
		GaraL g=new GaraL(100);
		AddettoDiGara ag=new AddettoDiGara(100,g);
		ag.start();
	}

}
