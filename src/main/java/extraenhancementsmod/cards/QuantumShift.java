package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.RemoveAllOrbsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import extraenhancementsmod.util.CardStats;

import java.util.ArrayList;
import java.util.Collections;

public class QuantumShift extends BaseCard {
    public static final String ID = makeID("QuantumShift");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0
    );

    public QuantumShift() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

//        // This action will shuffle the orbs
//        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
//            @Override
//            public void update() {
//                if (p.orbs.size() > 1) {
//                    ArrayList<AbstractOrb> shuffledCopy = new ArrayList<AbstractOrb>(p.orbs);
//                    Collections.shuffle(shuffledCopy);
//                    this.addToTop(new RemoveAllOrbsAction());
//                    int b = 0;
//                    int e = p.orbs.size() - 1;
//                    for (int i = 0; i < p.orbs.size(); i++) { // loop through collection
//                        if (p.orbs.get(i).ID.equals("Empty")) { // if empty, add to end
//                            p.orbs.get(i).setSlot(e, p.maxOrbs);
//                            e--;
//                        } else {
//                            // else not empty, add to beginning
//                            p.orbs.get(i).setSlot(b, p.maxOrbs);
//                            b++;
//                        }
//                    }
//                }
//                this.isDone = true;
//            }
//        });

        // remove all orbs
        // list of tuples of orbs, see dark class for passive amount

        // put them back in random order

        // for upgraded version of card
        if (this.upgraded) {
            addToBot(new GainEnergyAction(1));
        }
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


