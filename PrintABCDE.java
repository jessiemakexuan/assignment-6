package assignment6;

/* Create five threads, let them print out “A”, “B”, “C”, “D”, “E” individually. 
 * The result shows on the screen should be: “ABCDEABCDEABCDE…...*/
public class PrintABCDE {
	public static void main(String args[]){
		PrintAThread pa=new PrintAThread();
		PrintBThread pb=new PrintBThread();
		PrintCThread pc=new PrintCThread();
		PrintDThread pd=new PrintDThread();
		PrintEThread pe=new PrintEThread();
		//give next class's object
		pa.setB(pb); 
		pb.setC(pc);
		pc.setD(pd);
		pd.setE(pe);
		pe.setA(pa);
		pa.start();
		pb.start();
		pc.start();
		pd.start();
		pe.start();
	}

}

class PrintAThread extends Thread{
	private PrintBThread PrintBThread;
	//get the object from PrintBThread
	public void setB(PrintBThread B){  
		this.PrintBThread = B;
	}
	@Override
	public synchronized void run(){
		while(true){
			System.out.print("A");
			//after print, notify PrintBThread to stop wait
			synchronized(PrintBThread){
				PrintBThread.notify();  
			}
			//then begin to wait
			try {
				wait();                 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	} 
}

class PrintBThread extends Thread{
	private PrintCThread PrintCThread;
	public void setC(PrintCThread C){
		this.PrintCThread = C;
	}
	@Override
	public synchronized void run(){
		while(true){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("B");
			synchronized(PrintCThread){
				PrintCThread.notify();
			}
		}
	} 
}

class PrintCThread extends Thread{
	private PrintDThread PrintDThread;
	public void setD(PrintDThread D){
		this.PrintDThread = D;
	}
	@Override
	public synchronized void run(){
		while(true){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("C");
			synchronized(PrintDThread){
				PrintDThread.notify();
			}
		}
	} 
}

class PrintDThread extends Thread{
	private PrintEThread PrintEThread;
	public void setE(PrintEThread E){
		this.PrintEThread = E;
	}
	@Override
	public synchronized void run(){
		while(true){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("D");
			synchronized(PrintEThread){
				PrintEThread.notify();
			}
		}
	} 
}

class PrintEThread extends Thread{
	private PrintAThread PrintAThread;
	public void setA(PrintAThread A){
		this.PrintAThread = A;
	}
	@Override
	public synchronized void run(){
		while(true){
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.print("E");
			synchronized(PrintAThread){
				PrintAThread.notify();
			}
		}
	} 
}