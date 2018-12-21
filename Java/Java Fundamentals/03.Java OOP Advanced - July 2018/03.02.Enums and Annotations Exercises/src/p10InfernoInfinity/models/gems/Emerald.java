package p10InfernoInfinity.models.gems;

import p10InfernoInfinity.enums.GemType;
import p10InfernoInfinity.interfaces.Gem;

public class Emerald extends BaseGem implements Gem {

    public Emerald() {
        super(GemType.EMERALD.getStrength(), GemType.EMERALD.getAgility(), GemType.EMERALD.getVitality());
    }
}
