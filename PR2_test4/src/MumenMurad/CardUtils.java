package MumenMurad;
import java.util.*;

public abstract class CardUtils { 
	@SuppressWarnings("unused")
	public static Set<RacingScore> allAbove(final RacingScore [] arr,final Double threshold){
		Set<RacingScore> ss1 = new TreeSet<>( new racingcomparator());
		
		if (arr != null) {
		for (RacingScore ra : arr) {
			if (ra != null) {
			if (ra.calculateScore() > threshold) {
				ss1.add(ra);
			}
			else if (threshold == null) {
				if (ra.calculateScore() > 500) {
					ss1.add(ra);
				}
			}
		}
		}
		}
		else if (arr == null) {
			return ss1;
		}
		return ss1;
	}
}