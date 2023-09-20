package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extraenhancementsmod.powers.ZeroPointPower;
import extraenhancementsmod.util.CardStats;

public class ZeroPoint extends BaseCard {
    public static final String ID = makeID("ZeroPoint");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.POWER,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public ZeroPoint() {
        super(ID, info);
        this.baseMagicNumber = 1; // the amount of focus to be gained (see storm)
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ZeroPointPower(p, this.magicNumber)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}
