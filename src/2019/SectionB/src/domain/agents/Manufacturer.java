package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Manufacturer extends Agent {

  private final int neededRawPlasticBatches;

  public Manufacturer(int neededRawPlasticBatches, int thinkingTimeInMillis,
      MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
    if(neededRawPlasticBatches<1){
      throw new IllegalArgumentException("Manufacturing must require at least one batch of plastic");
    }
    this.neededRawPlasticBatches = neededRawPlasticBatches;
  }

  @Override
  protected void doAction() {
    Set<RawPlastic> plasticBatches = new HashSet<>();
    while (plasticBatches.size() < neededRawPlasticBatches) {
      Optional<RawPlastic> boughtBatch = marketPlace.buyRawPlastic();
      boughtBatch.ifPresentOrElse(b -> plasticBatches.add(b), this::think);
    }
    marketPlace.sellPlasticGood(new PlasticGood(plasticBatches));
  }
}
