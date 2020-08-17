package domain.agents;

import domain.MarketPlace;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;

public class ChemicalPlant extends Agent {

  public ChemicalPlant(int thinkingTimeInMillis, MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
  }

  @Override
  protected void doAction() {
    marketPlace.sellRawPlastic(new RawPlastic(Origin.NEW));
  }
}
