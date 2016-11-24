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
    if (itin1.getTotalTime() > itin2.getTotalTime()) {
      return 1;
    }else if (itin1.getTotalTime() < itin2.getTotalTime()) {
      return -1;
    }else {
      return 0;
    }
  }

}
