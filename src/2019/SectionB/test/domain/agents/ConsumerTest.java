package domain.agents;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import testutils.MockableMarketPlace;

public class ConsumerTest {

  @Test
  public void consumerBuysAGoodWhenAvailableAndTrashIt() {
    final boolean goodDisposed[] = new boolean[1];

    final MarketPlace marketPlace = new MockableMarketPlace() {
      PlasticGood mockedGood;

      @Override
      public Optional<PlasticGood> buyPlasticGood() {
        assertNull(mockedGood);
        mockedGood = new PlasticGood(Collections.singleton(new RawPlastic(Origin.NEW)));
        return Optional.of(mockedGood);
      }

      @Override
      public void disposePlasticGood(PlasticGood good) {
        assertEquals(mockedGood, good);
        goodDisposed[0] = true;
      }
    };

    final Consumer consumer = new Consumer(1, marketPlace);
    consumer.doAction();
    assertTrue(goodDisposed[0]);
  }

  @Test
  public void ifNoGoodIsAvailableConsumerDoesNothing() {
    final MarketPlace marketPlace = new MockableMarketPlace() {
      @Override
      public Optional<PlasticGood> buyPlasticGood() {
        return Optional.empty();
      }
    };

    final Consumer consumer = new Consumer(1, marketPlace);
    consumer.doAction();
  }
}