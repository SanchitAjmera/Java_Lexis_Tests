package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MarketPlaceTest {

  MarketPlace marketPlace;

  @Before
  public void setup() {
    marketPlace = new MarketPlaceImpl();
  }

  @Test
  public void marketPlaceInitiallyEmpty() {
    assertTrue(marketPlace.buyRawPlastic().isEmpty());
    assertTrue(marketPlace.buyPlasticGood().isEmpty());
    assertTrue(marketPlace.collectDisposedGood().isEmpty());
  }

  @Test
  public void marketPlaceRawPlasticStorageImplementsFIFO() {
    assertTrue(marketPlace.buyRawPlastic().isEmpty());
    RawPlastic rawPlastic01 = new RawPlastic(Origin.NEW);
    RawPlastic rawPlastic02 = new RawPlastic(Origin.NEW);
    RawPlastic rawPlastic03 = new RawPlastic(Origin.NEW);

    marketPlace.sellRawPlastic(rawPlastic01);
    marketPlace.sellRawPlastic(rawPlastic02);
    marketPlace.sellRawPlastic(rawPlastic03);

    assertEquals(rawPlastic01, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlastic02, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlastic03, marketPlace.buyRawPlastic().get());
    assertTrue(marketPlace.buyRawPlastic().isEmpty());
  }

  @Test
  public void marketPlaceRawPlasticStorageImplementsFIFOWithPriority() {
    assertTrue(marketPlace.buyRawPlastic().isEmpty());
    RawPlastic rawPlasticNew01 = new RawPlastic(Origin.NEW);
    RawPlastic rawPlasticNew02 = new RawPlastic(Origin.NEW);
    RawPlastic rawPlasticNew03 = new RawPlastic(Origin.NEW);
    RawPlastic rawPlasticNew04 = new RawPlastic(Origin.NEW);

    RawPlastic rawPlasticRecycled01 = new RawPlastic(Origin.RECYCLED);
    RawPlastic rawPlasticRecycled02 = new RawPlastic(Origin.RECYCLED);
    RawPlastic rawPlasticRecycled03 = new RawPlastic(Origin.RECYCLED);
    RawPlastic rawPlasticRecycled04 = new RawPlastic(Origin.RECYCLED);

    marketPlace.sellRawPlastic(rawPlasticNew01);
    marketPlace.sellRawPlastic(rawPlasticNew02);
    marketPlace.sellRawPlastic(rawPlasticRecycled01);

    assertEquals(rawPlasticRecycled01, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlasticNew01, marketPlace.buyRawPlastic().get());

    marketPlace.sellRawPlastic(rawPlasticRecycled02);
    marketPlace.sellRawPlastic(rawPlasticRecycled03);
    marketPlace.sellRawPlastic(rawPlasticNew03);
    marketPlace.sellRawPlastic(rawPlasticRecycled04);
    marketPlace.sellRawPlastic(rawPlasticNew04);

    assertEquals(rawPlasticRecycled02, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlasticRecycled03, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlasticRecycled04, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlasticNew02, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlasticNew03, marketPlace.buyRawPlastic().get());
    assertEquals(rawPlasticNew04, marketPlace.buyRawPlastic().get());

    assertTrue(marketPlace.buyRawPlastic().isEmpty());
  }

  @Test
  public void marketPlacePlasticGoodsStorageImplementsFIFO() {
    final List<RawPlastic> components = List.of(new RawPlastic(Origin.NEW));

    assertTrue(marketPlace.buyPlasticGood().isEmpty());
    PlasticGood plasticGood01 = new PlasticGood(components);
    PlasticGood plasticGood02 = new PlasticGood(components);
    PlasticGood plasticGood03 = new PlasticGood(components);

    marketPlace.sellPlasticGood(plasticGood01);
    marketPlace.sellPlasticGood(plasticGood02);
    marketPlace.sellPlasticGood(plasticGood03);

    assertEquals(plasticGood01, marketPlace.buyPlasticGood().get());
    assertEquals(plasticGood02, marketPlace.buyPlasticGood().get());
    assertEquals(plasticGood03, marketPlace.buyPlasticGood().get());

    assertTrue(marketPlace.buyRawPlastic().isEmpty());
  }

  @Test
  public void marketPlaceTrashStorageImplementsFIFO() {
    final List<RawPlastic> components = List.of(new RawPlastic(Origin.NEW));

    assertTrue(marketPlace.collectDisposedGood().isEmpty());
    PlasticGood plasticGood01 = new PlasticGood(components);
    PlasticGood plasticGood02 = new PlasticGood(components);
    PlasticGood plasticGood03 = new PlasticGood(components);

    marketPlace.disposePlasticGood(plasticGood01);
    marketPlace.disposePlasticGood(plasticGood02);
    marketPlace.disposePlasticGood(plasticGood03);

    assertEquals(plasticGood01, marketPlace.collectDisposedGood().get());
    assertEquals(plasticGood02, marketPlace.collectDisposedGood().get());
    assertEquals(plasticGood03, marketPlace.collectDisposedGood().get());

    assertTrue(marketPlace.collectDisposedGood().isEmpty());
  }

}