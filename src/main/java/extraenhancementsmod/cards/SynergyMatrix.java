package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extraenhancementsmod.powers.PowerGridPower;
import extraenhancementsmod.powers.SynergyMatrixPower;
import extraenhancementsmod.powers.ZeroPointPower;
import extraenhancementsmod.util.CardStats;

public class SynergyMatrix extends BaseCard {
    public static final String ID = makeID("SynergyMatrix");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public SynergyMatrix() {
        super(ID, info);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SynergyMatrixPower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }
}
