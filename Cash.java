package cash;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Cash {
	static Building[] buildings = {		new Building("Factory", "Boss"),
										new Building("Castle", "MyLord")};
	static Manager[] managers   = {		new Manager("Boss"),
										new Manager("MyLord")};
	static ArrayList<Worker> workers =	new ArrayList<Worker>();
	BigBoss bigBoss = new BigBoss();

	static int cB = 0;										// current Building
	static int amountOfWorkers = 0;

	static public void main(String yumyum[]){				// PSVM
		Cash cash = new Cash();
		cash.firstRunCash();

	}

	void firstRunCash(){									//first run
		System.out.println("\n||| CASH   ||| CASH   ||| CASH   ||| CASH   ||| CASH   ||| CASH   ||| CASH   ||| by Mark");
		System.out.println("Commands:");
		System.out.println("  'hire' - hire new worker");
		System.out.println("  'upgrade 00' - upgrade Worker 00");
		System.out.println("  'Factory', 'Castle', 'Boss', or 'MyLord' - overview");
		System.out.println("  'cash' - manager takes workers' products and sells them");
		System.out.println("  'BigBoss' - gathers the managers' money");
		System.out.println("  'exit' - leave the game\n");
		Worker worker = new Worker();						//the first worker
		buildings[cB].showBuilding();
		runCash();
	}

	void runCash(){											// run game - processing Input
		boolean goThrough = true;
		while(goThrough) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if(input.startsWith("Factory") ){
				cB = 0;
				buildings[cB].showBuilding();
			}else if(input.startsWith("Castle") ){
				cB = 1;
				buildings[cB].showBuilding();
			}else if(input.startsWith("Boss")){
				cB = 0;
				managers[cB].show(buildings[cB], managers[cB]);
			}else if(input.startsWith("MyLord")){
				cB = 1;
				managers[cB].show(buildings[cB], managers[cB]);
			}else if(input.startsWith("upgrade")){
					upgrade(input);
			}else{
				switch (input){
				case ("exit"):
					System.out.print("The Big Boss left with " + String.format("%,.2f", bigBoss.bBCash) + " M in his pocket. He burnt his factor");
				if(workers.size()<10) System.out.print("y");
				else System.out.print("ies");
				System.out.print(" and he lived happily ever after.");
				goThrough = false;
				break;
				case("hire"):
					if(amountOfWorkers<=18){
						Worker.hire();
						break;
					}else System.out.println("You reached the current maximum amount of workers!");
				case ("cash"):
					managers[cB].cash(buildings[cB], managers[cB]);break;
				case ("BigBoss"):
					bigBoss.cash();
					break;
				default:{
				//	buildings[cB].showBuilding();
				}}
			}
		}

	}
	
	public void upgrade(String input) {
			try {
				String ssInput = input.substring(8);
				
				if (ssInput.equals("Factory")) {
					cB = 0;
					if(buildings[0].level<9)buildings[0].upgrade(buildings[0]);
					else System.out.println("Factory is max level already");
				}else if (ssInput.equals("Castle")){ 
					cB = 1;
					if(buildings[1].level<9)buildings[1].upgrade(buildings[1]);
					else System.out.println("Castle is max level already");
				}else {
				int inputInt0 = Integer.parseInt(ssInput);							// get worker
				 Worker worker = Cash.workers.get(inputInt0);
				worker.upgrade(worker);
				}
			} catch (NumberFormatException e) {System.out.print("That worker or building doesn't exist: e.g. type 'upgrade 12' to upgrade Worker 12.");
			} catch (StringIndexOutOfBoundsException e) {System.out.print("That worker or building doesn't exist: e.g. type 'upgrade 12' to upgrade Worker 12.");
			} catch (IndexOutOfBoundsException e) {System.out.print("That worker or building doesn't exist: e.g. type 'upgrade 12' to upgrade Worker 12.");
			}
	}
}

interface Cashable{
	public abstract void cash(Building buildings, Manager managers);
}

interface Upgradable{
	public void upgrade(Object object);
}
