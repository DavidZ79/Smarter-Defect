package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.RedoAction;
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
import java.util.Random;

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
        int orbNum = AbstractDungeon.player.orbs.size();
        ArrayList<AbstractOrb> orbList = new ArrayList<AbstractOrb>();

        for(int i = 0; i < orbNum; ++i) { // observe orbs
            if (!(AbstractDungeon.player.orbs.get(i) instanceof EmptyOrbSlot)) {
                orbList.add(AbstractDungeon.player.orbs.get(i));
            }
        }

        this.addToTop(new RemoveAllOrbsAction());

        Random rand = new Random();
        //AbstractOrb randomValue = orbList.get(randomIndex);
        for(int i = 0; i < orbNum; ++i) { // channel orbs back in
            int randomIndex = rand.nextInt(orbList.size()); // randomly choose index
            AbstractOrb x = orbList.get(randomIndex); // get orb at index
            orbList.remove(randomIndex);  // removes the element at the random index
            this.addToBot(new ChannelAction(x)); // channel randomly selected orb
        }



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


