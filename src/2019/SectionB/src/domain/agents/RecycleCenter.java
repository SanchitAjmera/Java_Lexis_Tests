package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import java.util.Optional;

public class RecycleCenter extends Agent {

  private int numRecycledPlasticComponentsProcessed = 0;

  public RecycleCenter(int thinkingTimeInMillis, MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
  }

  @Override
  protected void doAction() {
    Optional<PlasticGood> trashedGood = marketPlace.collectDisposedGood();

    trashedGood.ifPresent(good -> {
      // New material batches are transformed into the same number of recycled ones
      good.getBasicMaterials().stream()
          .filter(component -> component.origin == Origin.NEW)
          .forEach(component -> marketPlace
              .sellRawPlastic(new RawPlastic(Origin.RECYCLED)));

      // Two already recycled batches are required to create a new recycled batch
      good.getBasicMaterials().stream()
          .filter(component -> component.origin == Origin.RECYCLED)
          .forEach(component -> {
            if (++numRecycledPlasticComponentsProcessed % 2 == 0) {
              marketPlace.sellRawPlastic(new RawPlastic(Origin.RECYCLED));
            }
          });

    });
  }
}
