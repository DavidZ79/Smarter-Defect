package extraenhancementsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.FocusPower;
import static extraenhancementsmod.BasicMod.makeID;

public class ZeroPointPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("ZeroPointPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ZeroPointPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (orb.ID.equals("Frost")) {
            addToBot(new ApplyPowerAction(owner, owner, new FocusPower(owner, this.amount)));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new ZeroPointPower(owner, amount);
    }
}
