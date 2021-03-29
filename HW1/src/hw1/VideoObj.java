package hw1;

// TODO: complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable<VideoObj> {
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   */
  VideoObj(String title, int year, String director) {
    // TODO
	  
	  if (title == null || director == null)
	  	{ throw new IllegalArgumentException(); }
	  if ((title.trim().equals("")) || (director.trim().equals("")))
	  	{ throw new IllegalArgumentException(); }
	  if (year <= 1800 || year >= 5000)
	  	{ throw new IllegalArgumentException(); }
	  
	  _title = title.trim();
	  _year = year;
	  _director = director.trim();
     
  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    // TODO
    return _director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    // TODO
    return _title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    // TODO
    return _year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
    // TODO
	  if (thatObject == null)
      	{ return false; }
	  if (!(this.getClass().equals(thatObject.getClass())))
	    { return false; }
	  VideoObj that = (VideoObj) thatObject;
	  
	  if (!(_director.equals(that.director())))
	  	{ return false; }
	  if (_year != that.year())
	  	{ return false; }
	  if (!(_title.equals(that.title())))
	  	{ return false; }
	  
	  return true;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    // TODO
	  
	  int hash = 17;
	  
	  hash = hash * 37 + _title.hashCode();
	  hash = hash * 37 + _year;
	  hash = hash * 37 + _director.hashCode();
	  
	  return hash;
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param that the VideoObj to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater than that object.
   */
  public int compareTo(VideoObj that) {
    // TODO
	  
	  if (_title.compareTo(that.title()) == 0)
	  {
		  if (_year == that.year())
		  {
			  return _director.compareTo(that.director());
		  }
		  else
		  {
			  return _year - that.year();
		  }
	  }
	  else
	  {
		  return _title.compareTo(that.title());
	  }
	  
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    // TODO
    return _title + " (" + _year + ") : " + _director;
  }
}
