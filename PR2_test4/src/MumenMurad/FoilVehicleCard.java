package MumenMurad;

import java.util.List;
import java.util.Map;


public class FoilVehicleCard extends VehicleCard {
	private List<Category> specials;

	public FoilVehicleCard(final String name, final Map<Category, Double> categories, final List<Category> specials) {
// throws IllegalArgumentException if specials contains more than 3 items or is null
		super(name, categories);
		if (specials == null) {
			throw new IllegalArgumentException("specials contains more than 3 items or is null");
		}
		if (specials.size() > 3) {
			throw new IllegalArgumentException("specials contains more than 3 items or is null");
		}
// set member variables
		this.specials = specials;
	}

	@Override
	public int totalBonus() {
		int sum = 0;
// return total Bonus of card.
// Special Categories bonus points are doubled.
		for (Map.Entry<Category, Double> entry : getCategories().entrySet()) {
			if (specials.contains(entry.getKey())) {
				sum += entry.getKey().bonus((entry.getValue()) * 2);
			} else {
				sum += entry.getKey().bonus((entry.getValue()));
			}
		}
		return sum;
	}

	@Override
	public double calculateScore() {
		for (Category ca : getCategories().keySet()) {
			if (ca == Category.ENGINE_POWER_HP)
			{
				return super.calculateScore() * 2 ;
			}		
			}
		return super.calculateScore(); 
		
	}
	
	public String toString() {
		String str = "";
		int i = 0;
		for (Map.Entry<Category, Double> c : getCategories().entrySet()) {
			++i;
			if (specials.contains(c.getKey())) {
				str += "*" + c.getKey() + "*" + "=" + c.getValue();
			} else {
				str += c.getKey() + "=" + c.getValue();
			}
			if (i != getCategories().size()) {
				str += ",";
			}
		}
		return "−" + getName() + "(" + this.totalBonus() + ") −> {" + str + "} " + "CalculateScore :" +this.calculateScore() ;
	}
	
}
