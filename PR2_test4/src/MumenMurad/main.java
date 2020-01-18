package MumenMurad;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import MumenMurad.VehicleCard.Category;

public class main {
	public static void main(String[] args) {
		VehicleCard opel = Player.createkarte("Opel Astra", 16600., 2720., 204,8.7 , 230.,8.4);
		System.out.println(opel.toString());
		try {
		VehicleCard audi = Player.createkarte("Audi TT RS Roadster", 43650.,2480., 170., -1, 180., -99);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Fehler bei Audi");
		}
		VehicleCard porsche = Player.createkarte("Porsche 911GT", 3900000,31630., 544., 3.7, 310.,29.);
		System.out.println(porsche.toString());
		
		VehicleCard peugeot = Player.createkarte("Peugeot 607", 46950.,2650., 195., 8.8, 220.,8.6);
		System.out.println(peugeot.toString());
		
		//foilkarte
		List<Category> l1 = new LinkedList<>();
		l1.add(Category.PRICE_EUR);
		l1.add(Category.ENGINE_POWER_HP);
		l1.add(Category.VELOCITY_KMH);
		FoilVehicleCard tesla = Player.createkarte("Model S LRP", 90800.,0, 460., 3.4, 261.,0,l1);
		System.out.println(tesla.toString());
		
		Player tom = new Player ("Tom");
		tom.addCard(peugeot);
		tom.addCard(tesla);
		System.out.println(tom.toString());
		
		Player anna = new Player("Anna");
		anna.addCard(tesla);
		anna.addCard(porsche);
		System.out.println(anna.toString());
		
		System.out.println(tom.challengePlayer(anna));
		
		Player dankwart = new Player ("Dankwart");
		dankwart.addCard(porsche);
		dankwart.addCard(opel);
		System.out.println(dankwart.toString());
		
		System.out.println(anna.challengePlayer(dankwart));
		
		Set<Player> tre1 = new TreeSet<>(Player.compareByBonus());
		tre1.add(tom);
		tre1.add(anna);
		tre1.add(dankwart);
		
		System.out.println("------------>TreeSet");
		for (Player p : tre1) {
			System.out.println(p.toString());
		}
		
		
		System.out.println("------------------------------------Aufgabe 3 ");
		
		//Aufgabe 3
		VehicleCard neu = Player.createkarte("Mazda", 12d, 4., 3d, 2d, 1., 12d);
		RacingScore [] racing = new RacingScore[4];
		racing[0] = opel;
		racing[1] = porsche;
		racing[2] = peugeot;
		racing[3] = neu;
		
		
		
		Set<RacingScore> rr1 = CardUtils.allAbove(racing, -3000.);
		for (RacingScore ss1 : rr1) {
			System.out.println(ss1);
		}
		
	}
	
	
}