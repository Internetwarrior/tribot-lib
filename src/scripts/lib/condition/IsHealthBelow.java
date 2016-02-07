package scripts.lib.condition;

import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSNPC;

public class IsHealthBelow extends Condition {

	private int _health;

	public IsHealthBelow(int _health) {
		this._health = _health;
	}

	@Override
	public boolean active() {
		return Skills.getCurrentLevel(SKILLS.HITPOINTS) < this._health;
	}

}
