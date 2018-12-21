package p10InfernoInfinity.models.gems;

import p10InfernoInfinity.enums.GemType;
import p10InfernoInfinity.interfaces.Gem;

public class Ruby extends BaseGem implements Gem {

    public Ruby() {
        super(GemType.RUBY.getStrength(), GemType.RUBY.getAgility(), GemType.RUBY.getVitality());
    }
}
