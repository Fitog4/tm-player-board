package org.fitog4.controller;

import org.fitog4.model.PlayerBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    playerBoard.setMegaCreditsAmount(playerBoard.getMegaCreditsAmount() + playerBoard.getMegaCreditsProduction() + playerBoard.getTerraformRating());
    playerBoard.setSteelAmount(playerBoard.getSteelAmount() + playerBoard.getSteelProduction());
    playerBoard.setTitaniumAmount(playerBoard.getTitaniumAmount() + playerBoard.getTitaniumProduction());
    playerBoard.setPlantsAmount(playerBoard.getPlantsAmount() + playerBoard.getPlantsProduction());
    playerBoard.setHeatAmount(playerBoard.getHeatAmount() + playerBoard.getHeatProduction() + playerBoard.getEnergyAmount());
    playerBoard.setEnergyAmount(playerBoard.getEnergyProduction());
  }

  public ResourceSimulationDTO simulateAllocation(List<CardDTO> allocation, PlayerBoard playerBoard) {
    int numberOfNewCards = (int) allocation.stream().filter(CardDTO::isNewCard).count();

    ResourceSimulationDTO simulationDTO = new ResourceSimulationDTO(
        playerBoard.getMegaCreditsAmount() - 3 * numberOfNewCards,
        playerBoard.getSteelAmount(),
        playerBoard.getTitaniumAmount());

    ArrayList<CardDTO> accountedForCards = new ArrayList<>();

    processCardsPayableInResource(accountedForCards,
        allocation,
        simulationDTO,
        ResourceSimulationDTO::getTitaniumAmount,
        ResourceSimulationDTO::setTitaniumAmount,
        CardDTO::isTitaniumCard,
        playerBoard.getMegaCreditsPerUnitOfTitanium());

    processCardsPayableInResource(accountedForCards,
        allocation,
        simulationDTO,
        ResourceSimulationDTO::getSteelAmount,
        ResourceSimulationDTO::setSteelAmount,
        CardDTO::isSteelCard,
        playerBoard.getMegaCreditsPerUnitOfSteel());

    allocation.removeAll(accountedForCards);
    for (CardDTO remainingCard : allocation) {
      simulationDTO.setMegaCreditsAmount(simulationDTO.getMegaCreditsAmount() - remainingCard.getCost());
    }

    return simulationDTO;
  }

  // todo refactor, too many parameters
  private void processCardsPayableInResource(ArrayList<CardDTO> accountedForCards,
      List<CardDTO> allocation,
      ResourceSimulationDTO simulationDTO,
      Function<ResourceSimulationDTO, Integer> getter,
      BiConsumer<ResourceSimulationDTO, Integer> setter,
      Predicate<CardDTO> resourceCardPredicate,
      int resourceConversion) {

    List<CardDTO> resourceCards = allocation.stream().filter(card -> resourceCardPredicate.test(card) && !accountedForCards.contains(card)).collect(Collectors.toList());

    for (CardDTO card : resourceCards) {
      int costPayableInResource = card.getCost() / resourceConversion;
      int remainingCost = card.getCost() % resourceConversion;
      if (getter.apply(simulationDTO) >= costPayableInResource) {
        setter.accept(simulationDTO, getter.apply(simulationDTO) - costPayableInResource);
      } else {
        remainingCost += resourceConversion * (costPayableInResource - getter.apply(simulationDTO));
        setter.accept(simulationDTO, 0);
      }
      simulationDTO.setMegaCreditsAmount(simulationDTO.getMegaCreditsAmount() - remainingCost);
      accountedForCards.add(card);
    }
  }
}
