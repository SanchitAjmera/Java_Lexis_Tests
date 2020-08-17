package domain;

import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import java.util.Optional;

public interface MarketPlace {

  void sellRawPlastic(RawPlastic plasticItem);

  Optional<RawPlastic> buyRawPlastic();

  void sellPlasticGood(PlasticGood good);

  Optional<PlasticGood> buyPlasticGood();

  void disposePlasticGood(PlasticGood good);

  Optional<PlasticGood> collectDisposedGood();
}
