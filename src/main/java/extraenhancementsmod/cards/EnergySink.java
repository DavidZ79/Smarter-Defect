package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.defect.RedoAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.powers.FocusPower;
import extraenhancementsmod.actions.CircuitOverloadAction;
import extraenhancementsmod.util.CardStats;

public class EnergySink extends BaseCard {
    public static final String ID = makeID("EnergySink");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public EnergySink() {
        super(ID, info);
        this.showEvokeValue = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // This action will remove all Plasma orbs and shift other orbs
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < p.orbs.size(); i++) {
                    if (p.orbs.get(i) instanceof Plasma) {
                        p.orbs.remove(i);
                        i--;
                        for (int j = i; j > 0; j--) {
                            p.orbs.set(j, p.orbs.get(j - 1));
                        }
                        p.orbs.add(new EmptyOrbSlot());
                    }
                }
                //p.evokeOrb();  // To visually update the orbs
                this.isDone = true;
            }
        });
        this.addToBot(new IncreaseMaxOrbAction(1));
        this.addToBot(new DecreaseMaxOrbAction(1));


//        if (this.upgraded) {
//            addToBot(new ApplyPowerAction(p, p, new FocusPower(p, 2 * orbsRemoved)));
//        }
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgraded = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}



