package org.fitog4.view;

import org.fitog4.controller.ResourceChangeDTO;

import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ShellResourceChangeRegexHelper {

  ResourceChangeDTO parseAsResourceChange(String resourceChangeString) {
    ResourceChangeDTO resourceChangeDTO = new ResourceChangeDTO();

    for (ResourceLetter resourceLetter : ResourceLetter.values()) {
      int change = getChangeForResource(resourceChangeString, resourceLetter);
      resourceLetter.getResourceChangeSetter().accept(resourceChangeDTO, change);
    }
    return resourceChangeDTO;
  }

  private int getChangeForResource(String resourceChangeString, ResourceLetter resourceLetter) {
    Matcher matcher = Pattern.compile("([+-]\\d?\\d)" + resourceLetter, Pattern.CASE_INSENSITIVE)
        .matcher(resourceChangeString);
    if (matcher.find()) {
      return Integer.parseInt(matcher.group(1));
    } else {
      return 0;
    }
  }

  private enum ResourceLetter {
    M(ResourceChangeDTO::setDeltaMegaCredits),
    S(ResourceChangeDTO::setDeltaSteel),
    T(ResourceChangeDTO::setDeltaTitanium),
    P(ResourceChangeDTO::setDeltaPlants),
    E(ResourceChangeDTO::setDeltaEnergy),
    H(ResourceChangeDTO::setDeltaHeat);

    private final BiConsumer<ResourceChangeDTO, Integer> setter;

    ResourceLetter(BiConsumer<ResourceChangeDTO, Integer> setter) {
      this.setter = setter;
    }

    public BiConsumer<ResourceChangeDTO, Integer> getResourceChangeSetter() {
      return setter;
    }
  }
}
