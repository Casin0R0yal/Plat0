package Monopoly;

import java.util.Random;

public class dice {

	public static int roll() {
		Random rand = new Random();
		int face1 = 1+rand.nextInt(6);
		int face2 = 1+rand.nextInt(6);
		if (face1 == face2)
			return -face1-face2;
		return face1+face2;
	}
}
