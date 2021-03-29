package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
	  return _director;
  }

  public String title() {
	  return _title;
  }

  public int year() {
	  return _year;
  }

  public boolean equals(Object thatObject) {
	  if (thatObject == this) { return true; }
	  if (!(thatObject instanceof VideoObj)) { return false; }
	  
	  VideoObj v = (VideoObj) thatObject;
	  
	  return _title.equals(v.title()) && _director.equals(v.director()) && _year == v.year();
  }

  public int hashCode() {
	  int hash = 17;
	  
	  hash = hash * 37 + _title.hashCode();
	  hash = hash * 37 + _year;
	  hash = hash * 37 + _director.hashCode();
	  
	  return hash;
  }

  public int compareTo(Video that) {
	  int c = _title.compareTo(that.title());
	  if (c == 0)
	  {
		  c = _director.compareTo(that.director());
		  if (c == 0)
		  {
			  c = _year - that.year();
		  }
	  }
	  return c;
  }

  public String toString() {
	  return _title + " (" + _year + ") : " + _director;
  }
}
