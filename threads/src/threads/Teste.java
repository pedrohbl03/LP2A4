package threads;

public class Teste {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Thread t1 = new Thread(new MyThreadRunnable("Thread #1", 1000));
		Thread t2 = new Thread(new MyThreadRunnable("Thread #2", 1000));
		
		t1.start();
		t2.start();
		
		t1.join(10000);
		
		System.out.println("\n10000" + " milliseconds: \n");
		System.out.println("Thread atual: " + t1.getName());
		System.out.println("Is alive? " + t1.isAlive());
		System.out.println("Thread atual: " + t2.getName());
		System.out.println("Is alive? " + t2.isAlive());
	}

}
