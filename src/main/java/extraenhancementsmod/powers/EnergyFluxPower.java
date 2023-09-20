package extraenhancementsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static extraenhancementsmod.BasicMod.makeID;

public class EnergyFluxPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("EnergyFluxPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EnergyFluxPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (int i = 0; i < amount; i++) {
            if (AbstractDungeon.player.hasEmptyOrb()) {
                AbstractDungeon.player.channelOrb(new Plasma());
            } else {
                break;
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new EnergyFluxPower(owner, amount);
    }
}
