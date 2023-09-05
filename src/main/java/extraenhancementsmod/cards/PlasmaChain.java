package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import extraenhancementsmod.util.CardStats;

public class PlasmaChain extends BaseCard {
    public static final String ID = makeID("PlasmaChain");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2
    );

    public PlasmaChain() {
        super(ID, info);
        setMagic(2, 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // see barrage
        int plasmaCount = 0;
        for(int i = 0; i < p.orbs.size(); ++i) {
            if (p.orbs.get(i) instanceof Plasma) {
                plasmaCount++;
            }
        }
        this.addToBot(new GainEnergyAction(magicNumber * plasmaCount));
        for(int j = 0; j < plasmaCount; ++j) {
            this.addToBot(new ChannelAction(new Lightning()));
        }
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



