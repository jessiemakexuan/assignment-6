package assignment6;

/*Create two threads, let the first thread print out number 1 to 52, another thread print out A to Z. 
 * The result shows on the screen should be: 12A34B56C*/
public class PrintLetterandNum {
	public static void main(String[] args) {
		PrintNumThread pn=new PrintNumThread();
		PrintLetterThread pl=new PrintLetterThread();
		//let pn & pl get other class's object 
		pn.getLetterClass(pl);
		pl.getNumberClass(pn);
		pn.start();
		pl.start();
	}
}

class PrintNumThread extends Thread{
	private PrintLetterThread pl;
	public void getLetterClass(PrintLetterThread pl){
		this.pl=pl;
	}
	@Override
	public synchronized void run(){
		for(int count=1;count<=52;count=count+2){
			//each time print 2 number
			System.out.print(count);
			System.out.print(count+1);
			//then notify PrintLetterThread to stop wait
			synchronized(pl){
				pl.notify();
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
class PrintLetterThread extends Thread{
	private PrintNumThread pn;
	public void getNumberClass(PrintNumThread pn){
		this.pn=pn;
	}
	@Override
	public synchronized void run(){
		for(char l='A';l<='Z';l++){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print(l);
			synchronized(pn){
				pn.notify();
			}
		}
	}
}
