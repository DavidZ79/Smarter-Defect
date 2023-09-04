package extraenhancementsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static extraenhancementsmod.BasicMod.makeID;

public class ResonancePower extends BasePower implements CloneablePowerInterface {

    public static final String POWER_ID = makeID("ResonancePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ResonancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onEvokeOrb(AbstractOrb orb) { // see juggernaut and barrage
        int orbCount = 0; // how many orbs including the one evoked
        for (int i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
            if (!(AbstractDungeon.player.orbs.get(i) instanceof EmptyOrbSlot) ) {
                orbCount += 1;
            }
        }

        int otherOrbs = orbCount - 1;
        for (int hits = 0; hits < otherOrbs; hits++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(this.owner,
                    amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new ResonancePower(owner, amount);
    }
}

