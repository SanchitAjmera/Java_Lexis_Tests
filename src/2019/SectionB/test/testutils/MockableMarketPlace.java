package testutils;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import java.util.Optional;

public interface MockableMarketPlace extends MarketPlace {

  default void sellRawPlastic(RawPlastic plasticItem) {
    throw new RuntimeException("Method not supported for this test");
  }

  default Optional<RawPlastic> buyRawPlastic() {
    throw new RuntimeException("Method not supported for this test");
  }

  default void sellPlasticGood(PlasticGood good) {
    throw new RuntimeException("Method not supported for this test");
  }

  default Optional<PlasticGood> buyPlasticGood() {
    throw new RuntimeException("Method not supported for this test");
  }

  default void disposePlasticGood(PlasticGood good) {
    throw new RuntimeException("Method not supported for this test");
  }

  default Optional<PlasticGood> collectDisposedGood() {
    throw new RuntimeException("Method not supported for this test");
  }
}
