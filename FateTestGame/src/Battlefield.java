import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class Battlefield {
	private static int calculatedDamage = 0;
	private static int round = 1;
	
	private static ArtoriaPendragon player1 = new ArtoriaPendragon("Player 1");
	private static ArtoriaPendragon player2 = new ArtoriaPendragon("Player 2");
	public static Scanner myObj = new Scanner(System.in);
	
	public static void battle(ArtoriaPendragon Servant1, ArtoriaPendragon Servant2) {
		while ((Servant1.health > 0) && (Servant2.health > 0)) {
			StdOut.println("Round: " + round++);
			
			Servant1.resetBonus();
			Servant2.resetBonus();
			StdOut.println("Crit Stars 1: " + Servant1.critStars);
			StdOut.println("Crit Stars 2: " + Servant2.critStars);
			StdOut.println("NP Gauge 1: " + Servant1.npGauge);
			StdOut.println("NP Gauge 2: " + Servant2.npGauge);
			StdOut.println();
			
			StdOut.println(Servant1.master + "'s " + Servant1.name);
			damageEnemy(Servant1);
			resultOfAttack(Servant1, Servant2);
			StdOut.println();
			
			StdOut.println(Servant2.master + "'s " + Servant2.name);
			damageEnemy(Servant2);
			resultOfAttack(Servant2, Servant1);
			StdOut.println();
			
			StdOut.println("-----------");
			StdOut.println();
		}
	}

	public static void damageEnemy(ArtoriaPendragon attacker) {
		int[] deck = createRandomArray(5);
		int hitDamage = 0;
		calculatedDamage = 0;
		
		for (int i = 0; i<3; i++) 
			StdOut.println(attacker.cardsValue[deck[i]] + ": " + attacker.cardsType[deck[i]]);
		
		attacker.useSkillChoice(attacker.skill1);
		attacker.useSkillChoice(attacker.skill2);
		attacker.useSkillChoice(attacker.skill3);
		NoblePhantasmUse(attacker, deck);
		
		for (int i = 0; i<3; i++) {
			if (critDamage(attacker.critStars) == true)
				hitDamage = attacker.cardsValue[deck[i]] * 2;
			else
				hitDamage = attacker.cardsValue[deck[i]];
			
			attacker.critStarsNextTurn += attacker.cardsCritGain[deck[i]];
			attacker.npGauge += attacker.cardsNPGain[deck[i]];			
			
			StdOut.println(hitDamage + ": " + attacker.cardsType[deck[i]]);
			calculatedDamage += hitDamage;
		}
		
		StdOut.println("Applying bonuses to damage: " + attacker.attackBonus);
		calculatedDamage += attacker.attackBonus;
	}
	
	public static int[] createRandomArray(int sizeOfArray){
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
		
	public static boolean critDamage(int critStarsInput) {
		Random random = new Random();
		if (random.nextInt(50) <= critStarsInput) {
			StdOut.println("Critical Hit");
			return true;
		}
		else
			StdOut.println("Critical Miss");
			return false;	
	}
	
	public static void resultOfAttack(ArtoriaPendragon attacker, ArtoriaPendragon defender) {
		StdOut.println(attacker.master + "'s " + attacker.name + " deals " + calculatedDamage + " damage.");
		defender.health -= calculatedDamage;
		StdOut.println(defender.master + "'s " + defender.name + " has " + defender.health + " health left.");
		StdOut.println();
	}
	
	public static boolean NoblePhantasmCheck(ArtoriaPendragon attacker, int[] deckInput) {
		if (attacker.npGauge > 50) {
			StdOut.println("Noble Phantasm ready.");
			StdOut.println("Use NP?");
			String ArtoriaNPUse = myObj.next().toLowerCase();
			if (ArtoriaNPUse.contentEquals("Yes".toLowerCase())){
				StdOut.println("Select card (1, 2, 3) to replace");
				int ArtoriaNPCardReplace = myObj.nextInt(); // need to while / catch any values not 1, 2, or 3.
				deckInput[ArtoriaNPCardReplace] = 5;
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public static void NoblePhantasmUse(ArtoriaPendragon attacker, int[] deckInput) {
		if (NoblePhantasmCheck(attacker, deckInput)) {
			StdOut.println("NP Applied");
			calculatedDamage += attacker.NoblePhantasm();
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
