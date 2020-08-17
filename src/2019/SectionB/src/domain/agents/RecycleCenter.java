package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import utils.Queue;
import utils.SafeQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class RecycleCenter extends Agent {

    private int newPlastics = 0;
    private int recycledPlastics = 0;

    public RecycleCenter(int thinkingTimeInMillis, MarketPlace marketPlace) {
        super(thinkingTimeInMillis, marketPlace);
    }

    @Override
    protected void doAction() {
        Optional<PlasticGood> disposedGood = marketPlace.collectDisposedGood();
        if (disposedGood.isPresent()) {
            Collection<RawPlastic> materials =
                    disposedGood.get().getBasicMaterials();
            for (RawPlastic rawPlastic : materials) {
                if (rawPlastic.origin == RawPlastic.Origin.NEW) {
                    newPlastics += 1;
                } else {
                    recycledPlastics += 1;
                }
            }
        }

        while (newPlastics > 0) {
            marketPlace.sellRawPlastic(new RawPlastic(RawPlastic.Origin.RECYCLED));
            newPlastics -= 1;
        }
        while (recycledPlastics > 1) {
            marketPlace.sellRawPlastic(new RawPlastic(RawPlastic.Origin.RECYCLED));
            recycledPlastics -= 2;
        }
    }
}
