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
public class ItineraryTimeComparator implements Comparator<Itinerary> {

  public ItineraryTimeComparator() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public int compare(Itinerary itin1, Itinerary itin2) {
    return itin1.getTotalTime().compareTo(itin2.getTotalTime());
  }

}
