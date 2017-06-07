package cash;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Worker implements Upgradable{
	Worker(){															// CONSTRUCTOR
		Cash.workers.add(this);
		if(Cash.amountOfWorkers<=9) zero = "0";
		this.name = "Worker " + zero + Cash.amountOfWorkers;
		this.hiredInBuilding = hiredInBuilding();
		Cash.amountOfWorkers++;
		this.level=0;
		startedProductionAt = LocalDateTime.now();
		System.out.print(name + " is hired in ");
		if(Cash.amountOfWorkers<10) System.out.print("The Factory.");
		if(Cash.amountOfWorkers>9) System.out.print("The Castle.");
	}
	
	int hiredInBuilding(){
		if(Cash.amountOfWorkers<=9)
		return 0;
		else return 1;
	}
	
	String name;
	String zero ="";
	int level;
	int hiredInBuilding;
	int[][] matrix ={{	0,	1,	2,	3, 	 4,	  5,   6,   7,   8,   9},		// level
					{	1,  2,  3,  4,   5,   6,   7,   8,   9,  10},		// production
					{  30, 45,120,220, 450, 750,1500,2500,4500,7500},		// max products
					{  60,100,240,480,1220,2260,3300,5340,7380,9420}		// priceUpgrade
	};
	LocalDateTime startedProductionAt;

	public int products() {
		int products = isHeWorking() ? matrix[1][level] * this.getSecondsWorking() : this.matrix[2][this.level];
		return products;
	}

	boolean isHeWorking(){
		return matrix[1][level] * this.getSecondsWorking() < this.matrix[2][this.level];
	}
	
	public StringBuilder showWorker(){
		
		StringBuilder string = new StringBuilder(this.name + ". Level: " + this.level + ". Production: " + this.matrix[1][this.level] + " p/min. ");
		string.append("Max stack: " + this.matrix[2][this.level] + ". " );
		if (isHeWorking()) string.append(this.getSecondsWorking() + " seconds working");
		else string.append("Stack full");
		string.append(" - " + this.products() + " products made.");
		return string;
	}
	
	public int getSecondsWorking (){
		return (int)ChronoUnit.SECONDS.between(startedProductionAt, LocalDateTime.now());
	}

	public void upgrade(Object object){
		Manager manager = Cash.managers[Cash.cB];
		if(manager.moneyInThePocket >= this.matrix[3][this.level]){						// if enough money
			manager.moneyInThePocket -= this.matrix[3][this.level];						// pay
			this.level++;																					// levelup worker
			System.out.print("You upgraded " + this.name + " to level " + this.level + " (costs:" + this.matrix[3][this.level] + " M). ");
			System.out.print("New production: " + this.matrix[1][this.level] + " p/sec. ");
		}
		else{ 																								// not enough
			System.out.println(manager.name + " doesn't have money enough yet to upgrade the worker.");
		}
	}

	public static void hire() {
		Manager manager = Cash.managers[Cash.cB];
		Worker prevWorker = Cash.workers.get(Cash.workers.size()-1);
		if(manager.moneyInThePocket >= Cash.workers.get(Cash.workers.size()-1).matrix[3][0]){	// if enough money
			manager.moneyInThePocket -= prevWorker.matrix[3][0];									// pay
			Worker worker = new Worker();																		// hire new worker
		}
		else{ 																									// saldo not enough
			System.out.println(manager.name + " doesn't have money enough yet to hire a new worker.");
		}

	}
}
