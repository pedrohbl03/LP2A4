package threads;

public class MyThreadRunnable implements Runnable {
	private String name;
	private int time;

	public MyThreadRunnable(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i<6; i++) {
				System.out.println(name + " contador " + i);

				Thread.sleep(time);
			}
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(name + " terminou a execução");
	}
}
