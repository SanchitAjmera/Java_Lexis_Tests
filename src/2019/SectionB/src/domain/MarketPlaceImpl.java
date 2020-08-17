package domain;

import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import utils.SafeQueue;

import java.util.Optional;
import java.util.Queue;


public class MarketPlaceImpl implements MarketPlace {

    private final boolean DEBUG_MESSAGES = true;
    private SafeQueue<RawPlastic> newRawPlastics = new SafeQueue<>();
    private SafeQueue<RawPlastic> recycledRawPlastics = new SafeQueue<>();
    private SafeQueue<PlasticGood> plasticGoods = new SafeQueue<>();
    private SafeQueue<PlasticGood> disposedGoods = new SafeQueue<>();

    public void sellRawPlastic(RawPlastic plasticItem) {
        if (DEBUG_MESSAGES) {
            System.out
                    .println("Thread: " + Thread.currentThread().getId() + " " +
                            "- Sell plastic: " + plasticItem);
        }
        if (plasticItem.origin == RawPlastic.Origin.NEW) {
            newRawPlastics.push(plasticItem);
        } else {
            recycledRawPlastics.push(plasticItem);
        }

    }

    public Optional<RawPlastic> buyRawPlastic() {
        Optional<RawPlastic> recycled = recycledRawPlastics.pop();
        if (recycled.isPresent()) {
            return recycled;
        } else {
            return newRawPlastics.pop();
        }
    }

    public void sellPlasticGood(PlasticGood good) {
        if (DEBUG_MESSAGES) {
            System.out.println("Thread: " + Thread.currentThread().getId() +
                    " - Sell good: " + good);
        }
        plasticGoods.push(good);

    }

    public Optional<PlasticGood> buyPlasticGood() {
        return plasticGoods.pop();
    }

    public void disposePlasticGood(PlasticGood good) {
        if (DEBUG_MESSAGES) {
            System.out.println("Thread: " + Thread.currentThread().getId() +
                    " - Dispose good: " + good);
        }
        disposedGoods.push(good);
    }

    public Optional<PlasticGood> collectDisposedGood() {
        return disposedGoods.pop();
    }
}
