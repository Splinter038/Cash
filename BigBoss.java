package cash;

public class BigBoss{
	float bBCash = 0;

	public void cash() {
		double temp = 0;
		for(Manager manager : Cash.managers){
			temp += manager.moneyInThePocket;
			manager.moneyInThePocket = 0;
		}
		bBCash += temp;
		System.out.println("Woaw, the Big Boss got " + String.format("%,.2f", temp) +" M. He now has " + String.format("%,.2f", bBCash) + " M!");
	}
}
