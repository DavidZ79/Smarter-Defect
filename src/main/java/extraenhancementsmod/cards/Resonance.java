package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extraenhancementsmod.powers.ResonancePower;
import extraenhancementsmod.util.CardStats;

public class Resonance extends BaseCard {
    public static final String ID = makeID("Resonance");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.POWER,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Resonance() {
        super(ID, info);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ResonancePower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

