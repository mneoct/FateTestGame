import java.util.Scanner;
import edu.princeton.cs.introcs.StdOut;

public class ArtoriaPendragon {
	public String master;
	public String name = "Artoria Pendragon";
	
	public int health = 100;
	public int[] cardsValue = {3, 4, 3, 4, 4, 0};
	public String[] cardsType = {"Quick", "Arts", "Arts", "Buster", "Buster", "Null"};
	public int[] cardsCritGain = {8, 0, 0, 1, 1, 0};
	public int[] cardsNPGain = {1, 3, 3, 0, 0, 0};
	
	public int attackBonus = 0;
	public int npGauge = 0;
	public int critStars = 0;
	public int critStarsNextTurn = 0;
	
	public static Scanner myObj = new Scanner(System.in);

	public ArtoriaPendragon(String master) {
		this.master = master;		
	}
	public void resetBonus()	{
		this.attackBonus = 0;
		this.cardsValue[0] = 3;
		this.cardsValue[1] = 4;
		this.cardsValue[2] = 3;
		this.cardsValue[3] = 4;
		this.cardsValue[4] = 4;
		this.critStars = this.critStarsNextTurn;
		this.critStarsNextTurn = 0;
	}
	
	public class ArtoriaPendragonSkill{
		public String name;
		public String description;
		public int coolDownCurrent;
		public int coolDown;
		public int uptimeCurrent;
		public int uptime;
		public int index;
		
		public ArtoriaPendragonSkill(String nameInput, String desc, int coolCur, int cool, int upCur, int up, int indexSkill) {
			name = nameInput;
			description = desc;
			coolDownCurrent = coolCur;
			coolDown = cool;
			uptimeCurrent = upCur;
			uptime = up;
			index = indexSkill;
		}
	}
	
	ArtoriaPendragonSkill skill1 = new ArtoriaPendragonSkill("Charisma", "Increase all allies power by 1 for 3 rounds.", 0, 4, 0, 2, 1);
	ArtoriaPendragonSkill skill2 = new ArtoriaPendragonSkill("Mana Burst", "Increase own Buster card power by 3 for one round.", 0, 4, 0, 0, 2);
	ArtoriaPendragonSkill skill3 = new ArtoriaPendragonSkill("Radiant Path", "Gain 15 Critical Stars, and NP gauge increased by 30.", 0, 4, 0, 0, 3);
	
	private void skill1Effect()	{
		this.attackBonus += 1;
	}
	private void skill2Effect()	{
		this.cardsValue[3] += 3;
		this.cardsValue[4] += 3;
	}

	private void skill3Effect()	{
		critStars += 15;
		npGauge += 30;
	}
	
	public void useSkillChoice(ArtoriaPendragonSkill skill) {
		if (skill.uptimeCurrent > 0) {
			useSkill(skill.index);
			skill.uptimeCurrent -= 1;
		}
		
		if (skill.coolDownCurrent <= 0) {
			StdOut.println("Use " + skill.name + "?");
			StdOut.println(skill.description);
			String ArtoriaSkillUse = myObj.next().toLowerCase();
			if (ArtoriaSkillUse.contentEquals("Yes".toLowerCase())){
				useSkill(skill.index);
				skill.coolDownCurrent = skill.coolDown;
				skill.uptimeCurrent = skill.uptime;
			}
		}
		else {
			skill.coolDownCurrent -= 1;
		}
	}
	private void useSkill(int choice) {
		if (choice == 1)
			skill1Effect();
		else if (choice == 2)
			skill2Effect();
		else if (choice == 3)
			skill3Effect();
		else
			StdOut.println("Something has gone wrong.");
	}

// NoblePhantasm return the value of damage they deal; can be 0, those have other effects.
	public int NoblePhantasm() {
		StdOut.println("Sheathed in the breath of stars. \n"
				+ "A torrent of shining life. \n"
				+ "Hail! Excalibur!! ");
		this.npGauge = 10;
		return 12;
	}
	
	public int NoblePhantasm2() {
		this.npGauge = 10;
		this.health += 25;
		return 0;
	}

}
