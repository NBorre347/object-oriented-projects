package hw1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// TODO: complete the methods
/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data = new HashMap<VideoObj,Record>();

  InventorySet() { }

  /**
   * Return the number of Records.
   */
  public int size() {
    // TODO
    return _data.size();
  }

  /**
   *  Return a copy of the record for a given Video; if not present, return <code>null</code>.
   */
  public Record get(VideoObj v) {
    // TODO
	  if (!(_data.containsKey(v)) || v == null)
	  	{ return null; }
	  return ((Record) _data.get(v)).copy();
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
    // TODO
	  
	  ArrayList<Record> list = new ArrayList<Record>();
	  
	  for (Map.Entry<VideoObj,Record> e : _data.entrySet())
	  {
		  list.add(e.getValue().copy());
	  }
	  
	  return list;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   * @postcondition changes the record for the video
   */
  public void addNumOwned(VideoObj video, int change) {
    // TODO  
	  
	  if (video == null || change == 0)
	  {
		  throw new IllegalArgumentException();
	  }
	  
	  if(_data.containsKey(video))
	  {
		  Record e = _data.get(video);
		  e.numOwned += change;
		  if (e.numOwned <= 0)
		  	{ _data.remove(video); }
	  }
	  else
	  {
		  if (change > 0)
		  	{ _data.put(video, new Record(video, change, 0, 0)); }
	  }
	  
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   */
  public void checkOut(VideoObj video) {
    // TODO
	  
	  if (video == null || !(_data.containsKey(video)))
	  	{ throw new IllegalArgumentException(); }
	  
	  Record e = _data.get(video);
	  if (e.numOut < e.numOwned)
	  {
		  e.numOut += 1;
		  e.numRentals += 1;
	  }
	  else
	  {
		  throw new IllegalArgumentException();
	  }
	  
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
    // TODO
	  
	  if (video == null || !(_data.containsKey(video)))
	  	{ throw new IllegalArgumentException(); }
	  
	  Record e = _data.get(video);
	  if (e.numOut > 0)
	  {
		  e.numOut -= 1;
	  }
	  else
	  {
		  throw new IllegalArgumentException();
	  }
	  
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
    // TODO
	  _data.clear();
  }

  /**
   * Return the contents of the inventory as a string.
   */
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
}
