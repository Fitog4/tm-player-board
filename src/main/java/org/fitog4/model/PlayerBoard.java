package org.fitog4.model;

public class PlayerBoard {

  private int terraformRating;
  private Resource megaCredits;
  private Resource steel;
  private Resource titanium;
  private Resource plants;
  private Resource energy;
  private Resource heat;

  public int getTerraformRating() {
    return terraformRating;
  }

  public void setTerraformRating(int terraformRating) {
    this.terraformRating = terraformRating;
  }

  public Resource getMegaCredits() {
    return megaCredits;
  }

  public void setMegaCredits(Resource megaCredits) {
    this.megaCredits = megaCredits;
  }

  public Resource getSteel() {
    return steel;
  }

  public void setSteel(Resource steel) {
    this.steel = steel;
  }

  public Resource getTitanium() {
    return titanium;
  }

  public void setTitanium(Resource titanium) {
    this.titanium = titanium;
  }

  public Resource getPlants() {
    return plants;
  }

  public void setPlants(Resource plants) {
    this.plants = plants;
  }

  public Resource getEnergy() {
    return energy;
  }

  public void setEnergy(Resource energy) {
    this.energy = energy;
  }

  public Resource getHeat() {
    return heat;
  }

  public void setHeat(Resource heat) {
    this.heat = heat;
  }
}
