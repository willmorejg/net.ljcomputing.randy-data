package net.ljcomputing.randy.reader;

import java.util.List;
import java.util.Map;
import net.ljcomputing.randy.reader.exception.ReaderException;

/** Reader interface shared by all resource readers. */
public interface Reader {
  /**
   * Read the resource into a List of Map values.
   *
   * @return List of Map values
   * @throws ReaderException ReaderException
   */
  List<Map<String, Object>> resourceToListOfMaps() throws ReaderException;
}
