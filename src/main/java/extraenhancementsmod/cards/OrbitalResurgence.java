package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.defect.RedoAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.*;
import extraenhancementsmod.util.CardStats;

public class OrbitalResurgence extends LastOrbEvokedCard {
    public static final String ID = makeID("OrbitalResurgence");
    private static final CardStats info = new CardStats(
            AbstractCard.CardColor.BLUE,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public OrbitalResurgence() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
            if (!(AbstractDungeon.player.orbs.get(i) instanceof EmptyOrbSlot)) {
                this.addToBot(new RedoAction());
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeBaseCost(1);
        }
    }
}
