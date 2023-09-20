package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extraenhancementsmod.util.CardStats;

public class Stabilizer extends BaseCard {
    public static final String ID = makeID("Stabilizer");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );

    public Stabilizer() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // dualcast lol
        this.addToBot(new AnimateOrbAction(1));
        this.addToBot(new EvokeWithoutRemovingOrbAction(1));
        this.addToBot(new AnimateOrbAction(1));
        this.addToBot(new EvokeOrbAction(1));
        if (this.upgraded) {
            this.addToBot(new AnimateOrbAction(1));
            this.addToBot(new EvokeWithoutRemovingOrbAction(1));
            this.addToBot(new AnimateOrbAction(1));
            this.addToBot(new EvokeOrbAction(1));
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


