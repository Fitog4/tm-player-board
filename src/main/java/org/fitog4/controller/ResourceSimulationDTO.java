package org.fitog4.controller;

public class ResourceSimulationDTO {

  private int megaCreditsAmount;
  private int steelAmount;
  private int titaniumAmount;

  ResourceSimulationDTO(int megaCreditsAmount, int steelAmount, int titaniumAmount) {
    this.megaCreditsAmount = megaCreditsAmount;
    this.steelAmount = steelAmount;
    this.titaniumAmount = titaniumAmount;
  }

  public int getMegaCreditsAmount() {
    return megaCreditsAmount;
  }

  void setMegaCreditsAmount(int megaCreditsAmount) {
    this.megaCreditsAmount = megaCreditsAmount;
  }

  public int getSteelAmount() {
    return steelAmount;
  }

  void setSteelAmount(int steelAmount) {
    this.steelAmount = steelAmount;
  }

  public int getTitaniumAmount() {
    return titaniumAmount;
  }

  void setTitaniumAmount(int titaniumAmount) {
    this.titaniumAmount = titaniumAmount;
  }
}
