package MumenMurad;
import java.util.*;

public class racingcomparator implements Comparator<RacingScore>{

	@Override
	public int compare(RacingScore o1, RacingScore o2) {
		if (o1.calculateScore() > o2.calculateScore()) {
			return 1;
		}
		if (o1.calculateScore() < o2.calculateScore()) {
			return -1;
		}
		return 0;
	}
	
}