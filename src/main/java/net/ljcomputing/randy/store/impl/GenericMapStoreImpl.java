package net.ljcomputing.randy.store.impl;

import java.util.Map;
import net.ljcomputing.randy.model.impl.GenericMap;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.store.GenericMapStore;
import net.ljcomputing.randy.store.exception.StoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Generic map data store implementation. */
public class GenericMapStoreImpl extends AbstractStore<GenericMap> implements GenericMapStore {
  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(GenericMapStoreImpl.class);

  /**
   * Constructor.
   *
   * @param reader the data store loader
   * @throws StoreException StoreException
   */
  public GenericMapStoreImpl(final Reader reader) throws StoreException {
    super(reader);
  }

  /**
   * Map the given Map to the inferred Model.
   *
   * @param map the map of values
   * @return the Model populated from the Map values
   * @throws StoreException StoreException
   */
  public GenericMap mapToModel(final Map<String, Object> map) throws StoreException {
    try {
      final GenericMap model = super.mapToModel(map, GenericMap.class);
      model.setValue((Map<String, Object>) map.get("value"));
      return model;
    } catch (StoreException e) {
      logger.error("Failed to map to model: ", e);
      throw e;
    }
  }
}
