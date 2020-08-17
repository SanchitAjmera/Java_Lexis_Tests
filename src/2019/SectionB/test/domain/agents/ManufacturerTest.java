package domain.agents;

import static org.junit.Assert.assertEquals;


import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;
import testutils.MockableMarketPlace;

public class ManufacturerTest {

  @Test(expected = IllegalArgumentException.class)
  public void manufacturersCannotNeedLessThanOnePlasticBatch() {
    Manufacturer manufacturer = new Manufacturer(0, 1,
        new MockableMarketPlace() {
        });
  }

  @Test
  public void manufacturersAssembleRequiredBatchesOfPlasticIntoAPlasticGood() {
    final int numberOfNeededBatches = 5;

    final MarketPlace marketPlace = new MockableMarketPlace() {
      Set<RawPlastic> boughtPlasticBatches = new HashSet<>();

      @Override
      public Optional<RawPlastic> buyRawPlastic() {
        RawPlastic rawPlastic = new RawPlastic(Origin.RECYCLED);
        boughtPlasticBatches.add(rawPlastic);
        return Optional.of(rawPlastic);
      }


      @Override
      public void sellPlasticGood(PlasticGood good) {
        Set<RawPlastic> goodComponents = new HashSet<>(good.getBasicMaterials());
        assertEquals(boughtPlasticBatches, goodComponents);
      }

    };

    Manufacturer manufacturer = new Manufacturer(numberOfNeededBatches, 1, marketPlace);
    manufacturer.doAction();
  }

  @Test
  public void manufacturersTryToBuyUntilAllNeededMaterialsAreAvailable() {
    final int numberOfNeededBatches = 5;

    final MarketPlace marketPlace = new MockableMarketPlace() {
      Set<RawPlastic> boughtPlasticBatches = new HashSet<>();
      int buyAttempts = 0;

      @Override
      public Optional<RawPlastic> buyRawPlastic() {
        buyAttempts++;
        if(buyAttempts<=numberOfNeededBatches){
          return Optional.empty();
        }

        RawPlastic rawPlastic = new RawPlastic(Origin.RECYCLED);
        boughtPlasticBatches.add(rawPlastic);
        return Optional.of(rawPlastic);
      }


      @Override
      public void sellPlasticGood(PlasticGood good) {
        Set<RawPlastic> goodComponents = new HashSet<>(good.getBasicMaterials());
        assertEquals(boughtPlasticBatches, goodComponents);
        assertEquals(buyAttempts, 2*numberOfNeededBatches);
      }

    };

    Manufacturer manufacturer = new Manufacturer(numberOfNeededBatches, 1, marketPlace);
    manufacturer.doAction();
  }

}