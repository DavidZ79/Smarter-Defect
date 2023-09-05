package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Plasma;
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
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                //int counter = 0; // plasma orbs removed
                for (int i = 0; i < p.orbs.size(); i++) { // loop through orbs
                    if (p.orbs.get(i) instanceof Plasma) {
                        AbstractOrb plasmaOrb = p.orbs.get(i);
                        p.orbs.remove(i);
                        p.maxOrbs--;
                        //counter++;
                        p.orbs.get(i).setSlot(i, p.maxOrbs);
                        plasmaOrb.triggerEvokeAnimation();
                    }
                }
                this.isDone = true;
            }
        });
//        if (this.upgraded) {
//            for (int j = 0; j < counter; j++) {
//                addToBot(new GainEnergyAction(2));
//            }
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



