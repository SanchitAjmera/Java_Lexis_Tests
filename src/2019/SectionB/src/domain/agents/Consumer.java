package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import java.util.Optional;

public class Consumer extends Agent {

  public Consumer(int thinkingTimeInMillis, MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
  }

  @Override
  protected void doAction() {
    Optional<PlasticGood> good = marketPlace.buyPlasticGood();
    good.ifPresent(g -> {
      think();
      marketPlace.disposePlasticGood(g);
    });
  }
}
