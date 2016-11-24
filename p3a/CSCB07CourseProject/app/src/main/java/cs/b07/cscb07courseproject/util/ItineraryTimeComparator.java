package cs.b07.cscb07courseproject.util;

import java.util.Comparator;

import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * A <code>Comparator</code> which compares two <code>Itinerary</code> by their
 * total time.
 * 
 * @author Stephen
 *
 */
public class ItineraryTimeComparator implements Comparator<Itinerary> {

  /**
   * Creates a new <code>ItineraryTimeComparator</code>.
   */
  public ItineraryTimeComparator() {
  }

  @Override
  public int compare(Itinerary itin1, Itinerary itin2) {
    return itin1.getTotalTime().compareTo(itin2.getTotalTime());
  }

}
