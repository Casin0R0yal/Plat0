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
	public static int roll(player p) {
		Random rand = new Random();
		int face1 = 1+rand.nextInt(6);
		int face2 = 1+rand.nextInt(6);
		if (face1 == face2){
			p.addDouble();
			System.out.println(p.getName() + " rolled doubles for the "+p.getNbDouble()+" time.");
			if (p.getNbDouble() == 3) {p.GoToJail();}
			else {System.out.println("You roll again.");p.addPlay();}
		}
		else {p.resetDouble();}
		return face1+face2;
	}
}
