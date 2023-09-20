package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extraenhancementsmod.powers.EnergyFluxPower;
import extraenhancementsmod.util.CardStats;

public class EnergyFlux extends BaseCard {
    public static final String ID = makeID("EnergyFlux");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public EnergyFlux() {
        super(ID, info);
        this.baseMagicNumber = 1; // number of plasmas to channel
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EnergyFluxPower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}

