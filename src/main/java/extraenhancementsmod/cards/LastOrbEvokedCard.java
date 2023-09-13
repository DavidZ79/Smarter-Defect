package extraenhancementsmod.cards;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import extraenhancementsmod.util.CardStats;

public abstract class LastOrbEvokedCard extends BaseCard { // base card extension for Orbital Resurgence
    protected String lastEvoked;

    public LastOrbEvokedCard(String ID, CardStats info) {
        super(ID, info);
        this.lastEvoked = null;
    }
}
