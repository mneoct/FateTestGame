import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class Battlefield {
	private static int calculatedDamage = 0;
	private static int round = 1;
	
	private static ArtoriaPendragon player1 = new ArtoriaPendragon("Player 1");
	private static ArtoriaPendragon player2 = new ArtoriaPendragon("Player 2");
	public static Scanner myObj = new Scanner(System.in);
	
	public static int[] CreateRandomArray(int sizeOfArray){
		Random randomObject = new Random();
		int[] randomizedArray = new int[sizeOfArray];  

		for (int i = 0; i<randomizedArray.length; i++) {
		    randomizedArray[i] = i;
		}

		for (int i=0; i<randomizedArray.length; i++) {
		    int randomPosition = randomObject.nextInt(randomizedArray.length);
		    int temp = randomizedArray[i];
		    randomizedArray[i] = randomizedArray[randomPosition];
		    randomizedArray[randomPosition] = temp;
		}
		return randomizedArray;
	}
	
	public static void damageEnemy(ArtoriaPendragon attacker) {
		int[] deck = CreateRandomArray(5);
		calculatedDamage = 0;
		for (int i = 0; i<3; i++) {
			StdOut.println(attacker.cardsValue[deck[i]] + ": " + attacker.cardsType[deck[i]]);
			calculatedDamage += attacker.cardsValue[deck[i]];
		}
		StdOut.println("Applying bonuses to damage: " + attacker.attackBonus);
		calculatedDamage += attacker.attackBonus;
	}
	
	public static void resultOfAttack(ArtoriaPendragon attacker, ArtoriaPendragon defender) {
		StdOut.println(attacker.master + "'s " + attacker.name + " deals " + calculatedDamage + " damage.");
		defender.health -= calculatedDamage;
		StdOut.println(defender.master + "'s " + defender.name + " has " + defender.health + " health left.");
		StdOut.println();
	}
	
	public static void battle(ArtoriaPendragon Servant1, ArtoriaPendragon Servant2) {
		while ((Servant1.health > 0) && (Servant2.health > 0)) {
			StdOut.println("Round: " + round++);
			
			Servant1.resetBonus();
			Servant2.resetBonus();
			
			StdOut.println(Servant1.master + "'s " + Servant1.name);
			Servant1.useSkillChoice(Servant1.skill1);
			damageEnemy(Servant1);
			resultOfAttack(Servant1, Servant2);
			
			StdOut.println(Servant2.master + "'s " + Servant2.name);
			Servant2.useSkillChoice(Servant2.skill3);
			damageEnemy(Servant2);
			resultOfAttack(Servant2, Servant1);
			
			StdOut.println();
			StdOut.println("-----------");
			StdOut.println();
		}
	}

	public static void main(String[] args){
		battle(player1,player2);
		if (player1.health > player2.health)
			StdOut.println(player1.master + " has emerged victorious!");
		else
			StdOut.println(player2.master + " has emerged victorious!");
	}
}
