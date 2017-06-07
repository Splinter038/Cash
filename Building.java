package cash;
import java.util.ArrayList;

public class Building implements Upgradable{
	String name;
	
	int level = 0;
	int[][] matrix = 				{{	0,	1,	2,	 3,   4,   5,   6,    7,    8,    9},	// level
									{	0,550,740,1250,2450,4800,7500,12500,18000,24000}};	// priceNewLevel
	private double priceProducts[] = {2.5,3.5,5.5, 9.5,16.2,31.6,53.7, 67.4,125.8,200.0};	// price products
					
	public double getPriceProducts(int index) {
		return priceProducts[index];
	}

	Building(){																				// NO ARG CONSTRUCTOR
		this("default building","default manager");
	}
	Building(String name, String nameManager){												// CONSTRUCTOR
		this.name=name;
		System.out.println(name + " is built - the manager is: " + nameManager + ".");
	}

	public void upgrade(Object object) {
		Manager manager = Cash.managers[Cash.cB];  
		if(manager.moneyInThePocket >= this.matrix[1][this.level]){							// if enough money
			manager.moneyInThePocket -= this.matrix[1][this.level];							// pay
			this.level++;																	// levelup worker
			System.out.print("You upgraded " + this.name + " to level " + this.level + " (costs:" + String.format("%,.2f", this.matrix[1][this.level]) + " M). ");
			System.out.print("New product price: " + String.format("%,.2f", this.getPriceProducts(this.level)) + " p/sec. ");
		}
		else{ 																				// not enough
			System.out.println(manager.name + " doesn't have money enough yet to upgrade the building.");
		}
	}
	
	protected int minIndexWorker (){return Cash.cB == 0 ? 0 : 8;}
	protected int maxIndexWorker (){
		int m=0;
		if (Cash.cB == 0)	m =  Math.min(Cash.workers.size()-1, 8); 
		if (Cash.cB == 1)	m =  Math.min(Cash.workers.size()-1, 17); 
	return m;	
	}
	
	void showBuilding(){
		Building buildings 	= Cash.buildings[Cash.cB];
		Manager managers 	= Cash.managers[Cash.cB]; 
		System.out.print("\n"+buildings.name + ", level: " + buildings.level + ", manager: " + managers.name + ". ");
		System.out.println("Current product's price: " + String.format("%,.2f", buildings.getPriceProducts(buildings.level)));
		if(Cash.cB == 0 && Cash.workers.size() == 0 || Cash.cB == 1 && Cash.workers.size()<10){
			System.out.println("- no workers yet");
		}
		else{ 
			System.out.println("Workers_________Level___Status__________Products________Max.Products");
			for (int i = buildings.minIndexWorker() ; i<= buildings.maxIndexWorker() ; i++){
				Worker worker = Cash.workers.get(i);
				System.out.print(worker.name + "\t");
				System.out.print(worker.level + "\t");
				if(worker.isHeWorking())System.out.print("Working..\t");
				else System.out.print("Stack full\t");
				System.out.println(worker.products() + "\t\t" + worker.matrix[2][worker.level]);
			}
		}
	}
}
