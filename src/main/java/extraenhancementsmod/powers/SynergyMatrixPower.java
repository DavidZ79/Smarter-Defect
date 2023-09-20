package extraenhancementsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static extraenhancementsmod.BasicMod.makeID;

public class SynergyMatrixPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("SynergyMatrixPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public SynergyMatrixPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public AbstractOrb idToOrb(String id) {
        switch (id) {
            case "Lightning":
                return new Lightning();
            case "Frost":
                return new Frost();
            case "Dark":
                return new Dark();
            default:  // plasma
                return new Plasma();
        }
    }

    @Override
    public void atStartOfTurn() {

        // evoke rightmost orb 'amount' times
        if (amount > 1) {
            this.addToBot(new EvokeWithoutRemovingOrbAction(amount - 1));
        }
        this.addToBot(new EvokeOrbAction(1));

        // channel left most orb
        for (int j = AbstractDungeon.player.orbs.size() - 1; j >= 0; j--) { // get leftmost orb
            if (!(AbstractDungeon.player.orbs.get(j) instanceof EmptyOrbSlot)) {
                String leftID = String.copyValueOf(AbstractDungeon.player.orbs.get(j).ID.toCharArray());
                for (int k = 0; k < amount; k++) {
                    addToBot(new ChannelAction(idToOrb(leftID)));
                }
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
        return new SynergyMatrixPower(owner, amount);
    }
}
