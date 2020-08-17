package domain.agents;

import static org.junit.Assert.assertEquals;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;
import testutils.MockableMarketPlace;

public class RecycleCenterTest {

  @Test
  public void recycleCenterDoesNothingIfNoTrashIsAvailable() {
    final MarketPlace marketPlace = new MockableMarketPlace() {
      @Override
      public Optional<PlasticGood> collectDisposedGood() {
        return Optional.empty();
      }
    };

    RecycleCenter recycleCenter = new RecycleCenter(1, marketPlace);
    recycleCenter.doAction();
  }


  @Test
  public void oneRecycledForEachNewPlasticComponent() {

    final Set<RawPlastic> recycledPlasticComponents = new HashSet<>();

    final MarketPlace marketPlace = new MockableMarketPlace() {
      @Override
      public Optional<PlasticGood> collectDisposedGood() {

        final List<RawPlastic> components = List
            .of(new RawPlastic(Origin.NEW), new RawPlastic(Origin.NEW));
        final PlasticGood plasticGood = new PlasticGood(components);
        return Optional.of(plasticGood);
      }

      @Override
      public void sellRawPlastic(RawPlastic plasticItem) {
        assertEquals(Origin.RECYCLED, plasticItem.origin);
        recycledPlasticComponents.add(plasticItem);
      }
    };

    final RecycleCenter recycleCenter = new RecycleCenter(1, marketPlace);
    recycleCenter.doAction();
    assertEquals(2, recycledPlasticComponents.size());
  }


  @Test
  public void oneRecycledEveryTwoAlreadyRecycledPlasticComponents() {

    final Set<RawPlastic> recycledPlasticComponents = new HashSet<>();

    final MarketPlace marketPlace = new MockableMarketPlace() {
      @Override
      public Optional<PlasticGood> collectDisposedGood() {

        final List<RawPlastic> components = List
            .of(new RawPlastic(Origin.RECYCLED), new RawPlastic(Origin.RECYCLED));
        final PlasticGood plasticGood = new PlasticGood(components);
        return Optional.of(plasticGood);
      }

      @Override
      public void sellRawPlastic(RawPlastic plasticItem) {
        assertEquals(Origin.RECYCLED, plasticItem.origin);
        recycledPlasticComponents.add(plasticItem);
      }
    };

    final RecycleCenter recycleCenter = new RecycleCenter(1, marketPlace);
    recycleCenter.doAction();
    assertEquals(1, recycledPlasticComponents.size());
  }

  @Test
  public void oneRecycledEveryTwoOrOneTrashComponents() {

    final Set<RawPlastic> recycledPlasticComponents = new HashSet<>();

    final MarketPlace marketPlace = new MockableMarketPlace() {
      @Override
      public Optional<PlasticGood> collectDisposedGood() {

        final List<RawPlastic> components = List
            .of(new RawPlastic(Origin.RECYCLED), new RawPlastic(Origin.NEW),
                new RawPlastic(Origin.RECYCLED));
        final PlasticGood plasticGood = new PlasticGood(components);
        return Optional.of(plasticGood);
      }

      @Override
      public void sellRawPlastic(RawPlastic plasticItem) {
        assertEquals(Origin.RECYCLED, plasticItem.origin);
        recycledPlasticComponents.add(plasticItem);
      }
    };

    final RecycleCenter recycleCenter = new RecycleCenter(1, marketPlace);
    recycleCenter.doAction();
    assertEquals(2, recycledPlasticComponents.size());
  }

  @Test
  public void oneRecycledEveryTwoRecycledComponentsFromDifferentDisposedGoods() {

    final Set<RawPlastic> recycledPlasticComponents = new HashSet<>();

    final MarketPlace marketPlace = new MockableMarketPlace() {
      boolean firstInvocation = true;

      @Override
      public Optional<PlasticGood> collectDisposedGood() {
        final List<RawPlastic> components;
        if (firstInvocation) {
          components = List
              .of(new RawPlastic(Origin.RECYCLED), new RawPlastic(Origin.NEW),
                  new RawPlastic(Origin.RECYCLED), new RawPlastic(Origin.RECYCLED));
          firstInvocation = false;
        } else {
          components = List
              .of(new RawPlastic(Origin.RECYCLED));
        }
        final PlasticGood plasticGood = new PlasticGood(components);
        return Optional.of(plasticGood);
      }

      @Override
      public void sellRawPlastic(RawPlastic plasticItem) {
        assertEquals(Origin.RECYCLED, plasticItem.origin);
        recycledPlasticComponents.add(plasticItem);
      }
    };

    final RecycleCenter recycleCenter = new RecycleCenter(1, marketPlace);
    recycleCenter.doAction();
    recycleCenter.doAction();
    assertEquals(3, recycledPlasticComponents.size());
  }

}
