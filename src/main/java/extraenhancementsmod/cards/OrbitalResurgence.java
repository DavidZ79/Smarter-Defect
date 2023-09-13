package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.lastEvoked != null) {
            switch (this.lastEvoked.ID) {
                case "Frost":
                    this.addToBot(new ChannelAction(new Frost()));
                    break;
                case "Lightning":
                    this.addToBot(new ChannelAction(new Lightning()));
                    break;
                case "Dark":
                    this.addToBot(new ChannelAction(new Dark()));
                    break;
                case "Plasma":
                    this.addToBot(new ChannelAction(new Plasma()));
                    break;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            // Add whatever you want to happen upon upgrade here
        }
    }
}
