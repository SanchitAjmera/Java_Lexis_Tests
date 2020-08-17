package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;

import java.util.Optional;

public class Consumer extends Agent {

    public Consumer(int thinkingTimeInMillis, MarketPlace marketPlace) {
        super(thinkingTimeInMillis, marketPlace);
    }

    @Override
    protected void doAction() {
        Optional<PlasticGood> boughtGood = marketPlace.buyPlasticGood();
        if (boughtGood.isPresent()) {
            this.think();
            marketPlace.disposePlasticGood(boughtGood.get());
        }
    }
}
