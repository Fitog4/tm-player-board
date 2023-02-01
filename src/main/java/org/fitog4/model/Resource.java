package org.fitog4.model;

public class Resource {

  private int amount;
  private int production;
  private Integer megaCreditsPerUnit;

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getProduction() {
    return production;
  }

  public void setProduction(int production) {
    this.production = production;
  }

  public Integer getMegaCreditsPerUnit() {
    return megaCreditsPerUnit;
  }

  public void setMegaCreditsPerUnit(Integer megaCreditsPerUnit) {
    this.megaCreditsPerUnit = megaCreditsPerUnit;
  }
}
