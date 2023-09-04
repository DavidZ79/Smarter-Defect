package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import extraenhancementsmod.util.CardStats;

public class QuantumLeech extends BaseCard {
    public static final String ID = makeID("QuantumLeech");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    public QuantumLeech() {
        super(ID, info);
        setDamage(15, 20);
        this.baseMagicNumber = 5; // amount healed per dark orb
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // see blizzard
        int darkCount = 0;
        for (AbstractOrb orb : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (orb instanceof Dark) {
                ++darkCount;
            }
        }
        //this.calculateCardDamage((AbstractMonster)null);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SHIELD));
        addToBot(new HealAction(p, p, magicNumber * darkCount));
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            this.upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

