package extraenhancementsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static extraenhancementsmod.BasicMod.makeID;

public class OrbitalResurgencePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("PowerGridPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public String lastEvoked;

    public OrbitalResurgencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.lastEvoked = null;
    }

    @Override
    public void onEvokeOrb(AbstractOrb orb) {
        this.lastEvoked = orb.ID;
    }

    public String getID() {
        return lastEvoked;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new OrbitalResurgencePower(owner, amount);
    }
}
