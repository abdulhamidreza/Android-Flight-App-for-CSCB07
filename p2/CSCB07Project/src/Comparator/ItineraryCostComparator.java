/**
 * 
 */
package Comparator;

import java.util.Comparator;

import Itinerary.Itinerary;

/**
 * @author Stephen
 *
 */
public class ItineraryCostComparator implements Comparator<Itinerary> {
  
  public ItineraryCostComparator() {
    
  }

  @Override
  public int compare(Itinerary itin1, Itinerary itin2) {
    if (itin1.getTotalCost() > itin2.getTotalCost()) {
      return 1;
    } else if (itin1.getTotalCost() < itin2.getTotalCost()) {
      return -1;
    } else {
      return 0;
    }
  }
}
