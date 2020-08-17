package domain.agents;

import static org.junit.Assert.assertEquals;

import domain.MarketPlace;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import org.junit.Test;
import testutils.MockableMarketPlace;

public class ChemicalPlantTest {

  @Test
  public void chemicalPlantsSellNewRawPlastic() {
    final MarketPlace marketPlace = new MockableMarketPlace() {
      @Override
      public void sellRawPlastic(RawPlastic plasticItem) {
        assertEquals(Origin.NEW, plasticItem.origin);
      }
    };

    final ChemicalPlant chemicalPlant = new ChemicalPlant(1, marketPlace);
    chemicalPlant.doAction();
  }

}