package org.fitog4.controller;

public class ResourceChangeDTO {

  private int deltaMegaCredits;
  private int deltaSteel;
  private int deltaTitanium;
  private int deltaPlants;
  private int deltaEnergy;
  private int deltaHeat;

  int getDeltaMegaCredits() {
    return deltaMegaCredits;
  }

  public void setDeltaMegaCredits(int deltaMegaCredits) {
    this.deltaMegaCredits = deltaMegaCredits;
  }

  int getDeltaSteel() {
    return deltaSteel;
  }

  public void setDeltaSteel(int deltaSteel) {
    this.deltaSteel = deltaSteel;
  }

  int getDeltaTitanium() {
    return deltaTitanium;
  }

  public void setDeltaTitanium(int deltaTitanium) {
    this.deltaTitanium = deltaTitanium;
  }

  int getDeltaPlants() {
    return deltaPlants;
  }

  public void setDeltaPlants(int deltaPlants) {
    this.deltaPlants = deltaPlants;
  }

  int getDeltaEnergy() {
    return deltaEnergy;
  }

  public void setDeltaEnergy(int deltaEnergy) {
    this.deltaEnergy = deltaEnergy;
  }

  int getDeltaHeat() {
    return deltaHeat;
  }

  public void setDeltaHeat(int deltaHeat) {
    this.deltaHeat = deltaHeat;
  }

  @Override
  public String toString() {
    return String.format("\tDelta MegaCredits: %+d\n\tDelta Steel: %+d\n\tDelta Titanium: %+d\n\tDelta Plants: %+d\n\tDelta Energy: %+d\n\tDelta Heat: %+d",
        deltaMegaCredits, deltaSteel, deltaTitanium, deltaPlants, deltaEnergy, deltaHeat);
  }
}
