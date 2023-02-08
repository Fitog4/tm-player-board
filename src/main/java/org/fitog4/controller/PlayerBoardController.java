package org.fitog4.controller;

import org.fitog4.model.PlayerBoard;

public class PlayerBoardController {

  public void applyResourceAmountChange(ResourceChangeDTO resourceChangeDTO, PlayerBoard playerBoard) {
    playerBoard.setMegaCreditsAmount(playerBoard.getMegaCreditsAmount() + resourceChangeDTO.getDeltaMegaCredits());
    playerBoard.setSteelAmount(playerBoard.getSteelAmount() + resourceChangeDTO.getDeltaSteel());
    playerBoard.setTitaniumAmount(playerBoard.getTitaniumAmount() + resourceChangeDTO.getDeltaTitanium());
    playerBoard.setPlantsAmount(playerBoard.getPlantsAmount() + resourceChangeDTO.getDeltaPlants());
    playerBoard.setEnergyAmount(playerBoard.getEnergyAmount() + resourceChangeDTO.getDeltaEnergy());
    playerBoard.setHeatAmount(playerBoard.getHeatAmount() + resourceChangeDTO.getDeltaHeat());
  }

  public void applyResourceProductionChange(ResourceChangeDTO resourceChangeDTO, PlayerBoard playerBoard) {
    playerBoard.setMegaCreditsProduction(playerBoard.getMegaCreditsProduction() + resourceChangeDTO.getDeltaMegaCredits());
    playerBoard.setSteelProduction(playerBoard.getSteelProduction() + resourceChangeDTO.getDeltaSteel());
    playerBoard.setTitaniumProduction(playerBoard.getTitaniumProduction() + resourceChangeDTO.getDeltaTitanium());
    playerBoard.setPlantsProduction(playerBoard.getPlantsProduction() + resourceChangeDTO.getDeltaPlants());
    playerBoard.setEnergyProduction(playerBoard.getEnergyProduction() + resourceChangeDTO.getDeltaEnergy());
    playerBoard.setHeatProduction(playerBoard.getHeatProduction() + resourceChangeDTO.getDeltaHeat());
  }

  public void processProduction(PlayerBoard playerBoard) {
    playerBoard.setMegaCreditsAmount(playerBoard.getMegaCreditsAmount() + playerBoard.getMegaCreditsProduction());
    playerBoard.setSteelAmount(playerBoard.getSteelAmount() + playerBoard.getSteelProduction());
    playerBoard.setTitaniumAmount(playerBoard.getTitaniumAmount() + playerBoard.getTitaniumProduction());
    playerBoard.setPlantsAmount(playerBoard.getPlantsAmount() + playerBoard.getPlantsProduction());
    playerBoard.setEnergyAmount(playerBoard.getEnergyAmount() + playerBoard.getEnergyProduction());
    playerBoard.setHeatAmount(playerBoard.getHeatAmount() + playerBoard.getHeatProduction());
  }
}
