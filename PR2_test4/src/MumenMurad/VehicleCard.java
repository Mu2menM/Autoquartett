package MumenMurad;

import java.util.Map;

public class VehicleCard implements Comparable<VehicleCard> ,RacingScore {
	public enum Category {
		// values:
		PRICE_EUR("Preis", 1), CYLINDER_CAPACITY_CM3("Hubraum", 5), ENGINE_POWER_HP("Leistung", 4),
		ACCELERATION_SEC("Beschleunigung", 3), VELOCITY_KMH("Geschwindigkeit", 2), CONSUMPTION_L("Verbrauch", 0) {
			@Override
			public int bonus(final Double value) {
				return (int) (getFactor() + value);
			}
		};
		final private String categoryName;
		final private int factor;

		private Category(final String categoryName, final int factor) {
//		throws IllegalArgumentException if categoryName null or empty or if factor less than 0 
			if (categoryName == null || categoryName.isEmpty() || factor < 0) {
				throw new IllegalArgumentException("categoryName null or empty or if factor less than 0 ");
			}
			this.categoryName = categoryName;
			this.factor = factor;
		}

		public int bonus(final Double value) {
			// return int(factor times value)
			// must be overriden for CONSUMPTION L. returns int(value + factor).
			return (int) (factor * value);
		}

		public int getFactor() {
			return factor;
		}

		@Override
		public String toString() {
			return categoryName;
		}
	}

	private String name;
	private Map<Category, Double> categories;

	public VehicleCard(final String name, final Map<Category, Double> categories) {
// throws IllegalArgumentException if name is null or empty.
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name is null or empty");
		}
// throws IllegalArgumentException if categories is null not every Category exists in categories.
		if (categories == null) {
			throw new IllegalArgumentException("categories is null ");
		}
		if (!categories.containsKey(Category.PRICE_EUR) || !categories.containsKey(Category.CYLINDER_CAPACITY_CM3)
				|| !categories.containsKey(Category.ENGINE_POWER_HP) || !categories.containsKey(Category.CONSUMPTION_L)
				|| !categories.containsKey(Category.ACCELERATION_SEC)
				|| !categories.containsKey(Category.VELOCITY_KMH)) {
			throw new IllegalArgumentException("not every Category exists in categories");
		}
		if (categories.size() != 6) {
			throw new IllegalArgumentException("not every Category exists in categories");
		}
// throws IllegalArgumentException if categories contains any null value or values less than 0.
		for (Map.Entry<Category, Double> entry : categories.entrySet()) {
			if (entry.getValue() == null || entry.getValue() < 0) {
				throw new IllegalArgumentException("categories contains null value or values less than 0");
			}
		}
// set member variables
		this.name = name;
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public Map<Category, Double> getCategories() {
		return categories;
	}

	// getters for immutable class, no setters (!)
	@Override
	public int compareTo(final VehicleCard other) {
// compare by totalBonus
		return totalBonus() - other.totalBonus();
	}

	public int totalBonus() {
// return total Bonus of card.
		int sum = 0;
		for (Category x : getCategories().keySet()) {
			sum += x.bonus(categories.get(x));
		}
		return sum;
	}

	// fragen
	@Override
	public int hashCode() {
		// hash ”name” (hint: Objects−class)∗
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleCard other = (VehicleCard) obj;
		if (this.totalBonus() == other.totalBonus()) {
			return true;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		int i = 0;
		for (Category x : categories.keySet()) {
			str += x + "=" + getCategories().get(x);
			++i;
			if (i != categories.size()) {
				str += ",";
			}
		}
		return "−" + name + "(" + this.totalBonus() + ") −> {" + str + "}" + "CalculateScore :" + calculateScore();
	}
	public double calculateScore() {
		
		double i = 0 ;
		double b = 0 ;
		for (Category c : categories.keySet()) {
			if (c == Category.ENGINE_POWER_HP) {
				i = categories.get(c);
			}
			if (c == Category.VELOCITY_KMH) {
				b = categories.get(c);
			}
		}
		return i - (b * 9);
	}

}