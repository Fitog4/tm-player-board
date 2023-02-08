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
}
