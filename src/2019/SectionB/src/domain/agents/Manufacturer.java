package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;

import java.util.ArrayList;
import java.util.Optional;

public class Manufacturer extends Agent {

    private final int neededRawPlasticBatches;

    public Manufacturer(int neededRawPlasticBatches, int thinkingTimeInMillis,
                        MarketPlace marketPlace) {
        super(thinkingTimeInMillis, marketPlace);
        this.neededRawPlasticBatches = neededRawPlasticBatches;
        if (neededRawPlasticBatches < 1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected void doAction() {
        ArrayList<RawPlastic> batch = new ArrayList<>();
        while (batch.size() < neededRawPlasticBatches) {
            Optional<RawPlastic> rawPlastic = marketPlace.buyRawPlastic();
            if (rawPlastic.isPresent()) {
                batch.add(rawPlastic.get());
            } else {
                think();
            }
        }
        PlasticGood plasticGood = new PlasticGood(batch);
        marketPlace.sellPlasticGood(plasticGood);
    }
}
