package cs.b07.cscb07courseproject.util;

import java.util.Comparator;

import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * A <code>Comparator</code> which compares two <code>Itinerary</code> by their
 * total cost.
 * 
 * @author Stephen
 *
 */
public class ItineraryCostComparator implements Comparator<Itinerary> {

  /**
   * Creates a new <code>ItineraryCostComparator</code>.
   */
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
