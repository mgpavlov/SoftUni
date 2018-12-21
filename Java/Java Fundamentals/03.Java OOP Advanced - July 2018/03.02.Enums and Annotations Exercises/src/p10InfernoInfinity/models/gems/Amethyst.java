package p10InfernoInfinity.models.gems;

import p10InfernoInfinity.enums.GemType;

public class Amethyst extends BaseGem{

    public Amethyst() {
        super(GemType.AMETHYST.getStrength(), GemType.AMETHYST.getAgility(), GemType.AMETHYST.getVitality());
    }
}
