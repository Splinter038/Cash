package cash;

import java.time.LocalDateTime;

import javax.security.auth.PrivateCredentialPermission;

class Manager implements Cashable {
	String name;
	float moneyInThePocket = 660.0f;

	Manager(String name) { // CONSTRUCTOR
		this.name = name;
	}

	public void cash(Building buildings, Manager managers) {
		int currentAmountOfProducts = 0;
		for (int i = buildings.minIndexWorker(); i <= buildings.maxIndexWorker(); i++) {
			Worker worker = Cash.workers.get(i);
			currentAmountOfProducts += worker.products();
			Cash.workers.get(i).startedProductionAt = LocalDateTime.now();
		}
		moneyInThePocket += (float) (currentAmountOfProducts * buildings.getPriceProducts(buildings.level));
		if (currentAmountOfProducts == 0) {
			System.out.print(managers.name + " got no products. He still has ");
		} else
			System.out.print(managers.name + " got " + currentAmountOfProducts + " products, and sold them for "
					+ String.format("%,.2f", buildings.getPriceProducts(buildings.level)) + " M a piece. Now he has ");
		if (moneyInThePocket == 0)
			System.out.print("no money in his pocket.");
		else
			System.out.print(String.format("%,.2f", moneyInThePocket) + " M in his pocket.");
	}

	public void show(Building buildings, Manager managers) {
		int subtotalProducts = 0;
		System.out.println(managers.name + ", manager of " + buildings.name + ". "
				+ String.format("%,.2f", moneyInThePocket) + " M in the pocket.");
		System.out.println("Workers_________Status__________Products________Max.Products____Worker Upgrade Price");
		for (int i = buildings.minIndexWorker(); i <= buildings.maxIndexWorker(); i++) {
			Worker worker = Cash.workers.get(i);
			System.out.print(worker.name + "\t");
			if (worker.isHeWorking())
				System.out.print("Working..\t");
			else
				System.out.print("Stack full\t");
			System.out.println(worker.products() + "\t\t" + worker.matrix[2][worker.level] + "\t\t"
					+ worker.matrix[3][worker.level]);
			subtotalProducts += worker.products();
		}
		System.out.println("Total products: \t\t" + subtotalProducts);
	}

}