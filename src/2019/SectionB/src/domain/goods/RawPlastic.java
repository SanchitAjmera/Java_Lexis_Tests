package domain.goods;

public class RawPlastic extends ExchangeableGood {
  public enum Origin {NEW, RECYCLED}

  public final Origin origin;

  public RawPlastic(Origin origin){
    this.origin = origin;
  }

  @Override
  public String toString() {
    return "RawPlastic ["+id+", "+origin+"]";
  }
}
