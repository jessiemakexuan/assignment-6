package assignment6;

/*Understand producer-consumer problem and implement it.*/
public class ProductorCustomer {
	//Assuming the warehouse stored for up to 5 stock
	private int capacity=5;
	//num means the number of the products we store 
	private int num=0;
	
	public static void main(String[] args) throws InterruptedException {
		ProductorCustomer pc=new ProductorCustomer();
		ProduceThread pt=new ProduceThread(pc);
		ConsumeThread ct=new ConsumeThread(pc);
		pt.start();
		ct.start();
	}
	
	public synchronized void produce(){
		//if num=capacity means we don't need to produce more products,so wait
		if(num==capacity){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		num++;
		System.out.println("Produce 1, now we have "+num+" products");
		notify();
	}
	
	public synchronized void consume(){
		//if no product in the warehouse, we need to wait the productor to produce
		if(num==0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		num--;
		System.out.println("Consume 1, now we left "+num+" products");
		notify();
	}
	
}

class ProduceThread extends Thread{
	private ProductorCustomer pc;
	public ProduceThread(ProductorCustomer pc){
		this.pc=pc;
	}
	@Override
	public synchronized void run(){
		while(true){
			pc.produce();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
class ConsumeThread extends Thread{
	private ProductorCustomer pc;
	public ConsumeThread(ProductorCustomer pc){
		this.pc=pc;
	}
	@Override
	public synchronized void run(){
		while(true){
			pc.consume();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}