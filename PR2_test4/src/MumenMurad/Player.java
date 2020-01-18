package MumenMurad;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

import MumenMurad.VehicleCard.Category;


public class Player implements Comparable<Player> {
	private String name;
	private Queue<VehicleCard> deck = new ArrayDeque();

	public Player(final String name) {
// throw IllegalArgumentException if name is null or empty
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name is null or empty");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addCards(final Collection<VehicleCard> cards) {
		// add cards to end
		deck.addAll(cards);
	}

	public void addCard(final VehicleCard card) {
		// add card to end
		deck.add(card);
	}

	public void clearDeck() {
		deck.clear();
	}

	private VehicleCard peekNextCard() {
		/* peek next card */
		return deck.peek();
	}

	public VehicleCard playNextCard() {
		/* poll next card from deck */
		return deck.poll();
	}

	public int compareTo(final Player other) {
// compare by name[case insensitive]
		return this.name.compareToIgnoreCase(other.name);
	}

	// fragen
	@Override
	public int hashCode() {
//		hash(name[case insensitive])
		return Objects.hash(name.toLowerCase());
	}

	// fragen
	@Override
	public boolean equals(Object obj) {
//		auto generate but cmp name case insensitive 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}

	public Queue<VehicleCard> getcards() {
		return deck;
	}

	public boolean challengePlayer(Player p) {
		if (p == null || p == this) {
			throw new IllegalArgumentException("p is null or p is this");
		}
		ArrayList<VehicleCard> thislist = new ArrayList<VehicleCard>();
		ArrayList<VehicleCard> plist = new ArrayList<VehicleCard>();
		VehicleCard v1;
		VehicleCard v2;
		if (this.deck.isEmpty() || p.deck.isEmpty()) {

			return false;
		}
		do {
			if (this.deck.isEmpty() || p.deck.isEmpty()) {
				this.addCards(thislist);
				p.addCards(plist);
				return false;
			}
			v1 = this.playNextCard();
			v2 = p.playNextCard();
			thislist.add(v1);
			plist.add(v2);
			if (v1.totalBonus() > v2.totalBonus()) {
				this.addCards(thislist);
				this.addCards(plist);
				return true;
			}
			if (v1.totalBonus() < v2.totalBonus()) {
				p.addCards(thislist);
				p.addCards(plist);
				return false;
			}

		} while (v1.totalBonus() == v2.totalBonus());

		return true;
	}

	public int getBouns() {
		int sum = 0;
		for (VehicleCard v : deck) {
			sum += v.totalBonus();
		}
		return sum;
	}

	public static Comparator<Player> compareByBonus() {
		return (p1, p2) -> p1.getBouns() - p2.getBouns();
	}

	public static Comparator<Player> compareByDeckSize() {
		return (p1, p2) -> p1.deck.size() - p2.deck.size();
	}

	@Override
	public String toString() {
		String cout = this.name + "(" + this.getBouns() + "):";
		for (VehicleCard vehicleCard : deck) {
			cout += "\n" + "- " + vehicleCard.toString();
		}

		return cout;
	}

	public static VehicleCard createkarte(String name, double a, double b, double c, double d, double e, double f) {
		Map<VehicleCard.Category, Double> cc = new LinkedHashMap<>();
		cc.put(Category.PRICE_EUR, a);
		cc.put(Category.CYLINDER_CAPACITY_CM3, b);
		cc.put(Category.ENGINE_POWER_HP, c);
		cc.put(Category.ACCELERATION_SEC, d);
		cc.put(Category.VELOCITY_KMH, e);
		cc.put(Category.CONSUMPTION_L, f);
		VehicleCard karte = new VehicleCard(name, cc);
		return karte;
	}

	public static FoilVehicleCard createkarte(String name, double a, double b, double c, double d, double e, double f,
			List<Category> slist) {
		Map<VehicleCard.Category, Double> cc = new LinkedHashMap<>();
		cc.put(Category.PRICE_EUR, a);
		cc.put(Category.CYLINDER_CAPACITY_CM3, b);
		cc.put(Category.ENGINE_POWER_HP, c);
		cc.put(Category.ACCELERATION_SEC, d);
		cc.put(Category.VELOCITY_KMH, e);
		cc.put(Category.CONSUMPTION_L, f);
		FoilVehicleCard karte1 = new FoilVehicleCard(name, cc, slist);
		return karte1;
	}

}