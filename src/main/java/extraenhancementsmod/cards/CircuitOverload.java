package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extraenhancementsmod.actions.CircuitOverloadAction;
import extraenhancementsmod.util.CardStats;

public class CircuitOverload extends BaseCard {
    public static final String ID = makeID("CircuitOverload");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            -1
    );

    public CircuitOverload() {
        super(ID, info);
        this.showEvokeValue = true;
        this.exhaust = true;
        setDamage(7);
        setBlock(7);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // see multicast
        for (int i = 0; i < this.energyOnUse; i++) { // damage
            addToTop(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
        addToBot(new CircuitOverloadAction(p, this.energyOnUse, this.freeToPlayOnce)); // evoke
        if (this.upgraded) { // for upgraded version of card
            for (int j = 0; j < this.energyOnUse; j++) { // block
                addToBot(new GainBlockAction(p, p, block));
            }
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



