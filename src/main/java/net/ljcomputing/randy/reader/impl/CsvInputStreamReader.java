package net.ljcomputing.randy.reader.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.ljcomputing.randy.factory.ResourceFactory;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.reader.exception.ReaderException;

/** CSV input stream reader implementation. */
public class CsvInputStreamReader extends AbstractReader implements Reader {
  /**
   * Constructor.
   *
   * @param resourceDefinition the definition of the resource
   */
  public CsvInputStreamReader(final String resourceDefinition) {
    super(resourceDefinition);
  }

  /**
   * Read the resource into a List of Map values.
   *
   * @return List of Map values
   * @throws ReaderException ReaderException
   */
  @Override
  public List<Map<String, Object>> resourceToListOfMaps() throws ReaderException {
    try (final InputStream storeFile =
        ResourceFactory.getByScheme(getResourceUri().getScheme())
            .getInputStream(getResourceUri())) {
      return csvToList(storeFile);
    } catch (Exception e) {
      throw new ReaderException("Failed to retrieve resource into list of maps", e);
    }
  }

  /**
   * Read the given InputStream and return a List of Maps.
   *
   * @param storeFile the store file InputStream
   * @return a List of Map values
   * @throws ReaderException ReaderException
   */
  protected List<Map<String, Object>> csvToList(final InputStream storeFile)
      throws ReaderException {
    final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    final CsvSchema csvSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',');

    try (final MappingIterator<Map<String, Object>> it =
        new CsvMapper().readerFor(Map.class).with(csvSchema).readValues(storeFile)) {
      int id = -1;
      while (it.hasNext()) {
        final Map<String, Object> map = new HashMap<>();
        map.put("id", Integer.toString(++id));
        map.put("value", it.next());
        list.add(map);
      }
    } catch (Exception e) {
      throw new ReaderException("Failed to convert CSV to List: ", e);
    }

    return list;
  }
}
