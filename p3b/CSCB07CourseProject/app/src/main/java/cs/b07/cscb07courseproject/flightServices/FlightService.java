package cs.b07.cscb07courseproject.flightServices;

import java.text.ParseException;
import java.util.List;

import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * Interface that provides all different flight services.
 */
public interface FlightService {

  /**
   * Gets a list of all possible <code>Itinerary</code> from the origin city to
   * the destination city on a certain date.
   * 
   * @param origin
   *          Origin city.
   * @param destination
   *          Destination city.
   * @param departDate
   *          Day of departure.
   * @return List of <code>Itinerary</code> from origin to destination on
   *         requested date.
   * @throws ParseException
   *           if cannot parse date properly.
   */
  public List<Itinerary> getItinerary(String origin, String destination, String departDate, boolean isDirect)
      throws ParseException;

  /**
   * Sorts a list of <code>Itinerary</code> by either total travel time or total
   * cost in either increasing or decreasing order.
   * 
   * @param itinerary
   *          List of <code>Itinerary</code> which want to be sorted.
   * @param sortBy
   *          Attribute list is to be sorted by.
   * @param increasingOrder
   *          Sort in increasing order if true, otherwise decreasing order.
   * @throws InvalidSortException
   *           If sorting attribute is not a valid option.
   */
  public void sortItineraries(List<Itinerary> itinerary, String sortBy, boolean increasingOrder)
      throws InvalidSortException;
}
