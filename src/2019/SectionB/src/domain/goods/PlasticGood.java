package domain.goods;

import java.util.Collection;
import java.util.Collections;

public class PlasticGood extends ExchangeableGood {

  private final Collection<RawPlastic> basicMaterials;

  public PlasticGood(Collection<RawPlastic> basicMaterials) {
    this.basicMaterials = Collections.unmodifiableCollection(basicMaterials);
  }

  public Collection<RawPlastic> getBasicMaterials() {
    return basicMaterials;
  }

  @Override
  public String toString() {
    return "PlasticGood [" + id + ", " + basicMaterials + "]";
  }
}
