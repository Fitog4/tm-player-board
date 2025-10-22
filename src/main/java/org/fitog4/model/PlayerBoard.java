package org.fitog4.model;

import jakarta.persistence.*;

@Entity
public class PlayerBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerboard_seq_gen")
    @SequenceGenerator(name = "playerboard_seq_gen", sequenceName = "playerboard_seq", allocationSize = 1)
    private Long id;

    private int terraformRating = 20;

    private int megaCreditsAmount;
    private int steelAmount;
    private int titaniumAmount;
    private int plantsAmount;
    private int energyAmount;
    private int heatAmount;

    private int megaCreditsProduction;
    private int steelProduction;
    private int titaniumProduction;
    private int plantsProduction;
    private int energyProduction;
    private int heatProduction;

    private int megaCreditsPerUnitOfSteel = 2;
    private int megaCreditsPerUnitOfTitanium = 3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTerraformRating() {
        return terraformRating;
    }

    public void setTerraformRating(int terraformRating) {
        this.terraformRating = terraformRating;
    }

    public int getMegaCreditsAmount() {
        return megaCreditsAmount;
    }

    public void setMegaCreditsAmount(int megaCreditsAmount) {
        this.megaCreditsAmount = megaCreditsAmount;
    }

    public int getSteelAmount() {
        return steelAmount;
    }

    public void setSteelAmount(int steelAmount) {
        this.steelAmount = steelAmount;
    }

    public int getTitaniumAmount() {
        return titaniumAmount;
    }

    public void setTitaniumAmount(int titaniumAmount) {
        this.titaniumAmount = titaniumAmount;
    }

    public int getPlantsAmount() {
        return plantsAmount;
    }

    public void setPlantsAmount(int plantsAmount) {
        this.plantsAmount = plantsAmount;
    }

    public int getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }

    public int getHeatAmount() {
        return heatAmount;
    }

    public void setHeatAmount(int heatAmount) {
        this.heatAmount = heatAmount;
    }

    public int getMegaCreditsProduction() {
        return megaCreditsProduction;
    }

    public void setMegaCreditsProduction(int megaCreditsProduction) {
        this.megaCreditsProduction = megaCreditsProduction;
    }

    public int getSteelProduction() {
        return steelProduction;
    }

    public void setSteelProduction(int steelProduction) {
        this.steelProduction = steelProduction;
    }

    public int getTitaniumProduction() {
        return titaniumProduction;
    }

    public void setTitaniumProduction(int titaniumProduction) {
        this.titaniumProduction = titaniumProduction;
    }

    public int getPlantsProduction() {
        return plantsProduction;
    }

    public void setPlantsProduction(int plantsProduction) {
        this.plantsProduction = plantsProduction;
    }

    public int getEnergyProduction() {
        return energyProduction;
    }

    public void setEnergyProduction(int energyProduction) {
        this.energyProduction = energyProduction;
    }

    public int getHeatProduction() {
        return heatProduction;
    }

    public void setHeatProduction(int heatProduction) {
        this.heatProduction = heatProduction;
    }

    public int getMegaCreditsPerUnitOfSteel() {
        return megaCreditsPerUnitOfSteel;
    }

    public void setMegaCreditsPerUnitOfSteel(int megaCreditsPerUnitOfSteel) {
        this.megaCreditsPerUnitOfSteel = megaCreditsPerUnitOfSteel;
    }

    public int getMegaCreditsPerUnitOfTitanium() {
        return megaCreditsPerUnitOfTitanium;
    }

    public void setMegaCreditsPerUnitOfTitanium(int megaCreditsPerUnitOfTitanium) {
        this.megaCreditsPerUnitOfTitanium = megaCreditsPerUnitOfTitanium;
    }
}
