package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import extraenhancementsmod.util.CardStats;

public class OrbAmplifier extends BaseCard {
    public static final String ID = makeID("OrbAmplifier");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public OrbAmplifier() {
        super(ID, info);
    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        int orbs = 0;
        for(int i = 0; i < p.orbs.size(); ++i) {
            if (!(p.orbs.get(i) instanceof EmptyOrbSlot)) {
                orbs++;
            }
        }
        this.addToBot(new IncreaseMaxOrbAction(orbs));
        if (this.upgraded) {
            this.addToBot(new IncreaseMaxOrbAction(2));
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


