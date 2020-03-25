import java.util.Random;
import java.util.Scanner;
import edu.princeton.cs.introcs.StdOut;

public class ArtoriaPendragon {
	public String master;
	public String name = "Artoria Pendragon";
	
	public int health = 100;
	public int[] cardsValue = {3, 4, 3, 4, 4};
	public String[] cardsType = {"Quick", "Arts", "Arts", "Buster", "Buster"};
	public int attackBonus = 0;
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
	ArtoriaPendragonSkill skill3 = new ArtoriaPendragonSkill("Radiant Path", "Each card has a 50% chance of their power being increased by 3.", 0, 4, 0, 0, 3);
	
	private void skill1Effect()	{
		this.attackBonus += 1;
	}
	private void skill2Effect()	{
		this.cardsValue[3] += 3;
		this.cardsValue[4] += 3;
	}
	private void skill3Effect()	{
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			if (random.nextInt(100) <= 50) {
				StdOut.println("Card " + i + " will be a critical hit!");
				this.cardsValue[i] += 3;
			} 
			else
				StdOut.println("Card " + i + " is not a  critical hit...");
		}
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
}
