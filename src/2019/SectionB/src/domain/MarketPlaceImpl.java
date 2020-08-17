package domain;

import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import domain.goods.RawPlastic.Origin;
import java.util.Optional;
import utils.Queue;
import utils.SafeQueue;


public class MarketPlaceImpl implements MarketPlace {

  private final boolean DEBUG_MESSAGES = true;

  private final Queue<RawPlastic> newPlasticQueue = new SafeQueue<>();
  private final Queue<RawPlastic> recycledPlasticQueue = new SafeQueue<>();
  private final Queue<PlasticGood> newPlasticGoodsQueue = new SafeQueue<>();
  private final Queue<PlasticGood> trashPlasticGoodsQueue = new SafeQueue<>();

  public void sellRawPlastic(RawPlastic plasticItem) {
    if (DEBUG_MESSAGES) {
      System.out.println("Thread: "+Thread.currentThread().getId()+" - Sell plastic: " + plasticItem);
    }

    if (plasticItem.origin == Origin.RECYCLED) {
      recycledPlasticQueue.push(plasticItem);
    } else {
      newPlasticQueue.push(plasticItem);
    }
  }

  public Optional<RawPlastic> buyRawPlastic() {
    Optional<RawPlastic> recycledPlastic = recycledPlasticQueue.pop();
    if (recycledPlastic.isPresent()) {
      return recycledPlastic;
    } else {
      return newPlasticQueue.pop();
    }
  }

  public void sellPlasticGood(PlasticGood good) {
    newPlasticGoodsQueue.push(good);
  }

  public Optional<PlasticGood> buyPlasticGood() {
    return newPlasticGoodsQueue.pop();
  }

  public void disposePlasticGood(PlasticGood good) {
    if (DEBUG_MESSAGES) {
      System.out.println("Thread: "+Thread.currentThread().getId()+" - Trash good: " + good);
    }

    trashPlasticGoodsQueue.push(good);
  }

  public Optional<PlasticGood> collectDisposedGood() {
    return trashPlasticGoodsQueue.pop();
  }
}
