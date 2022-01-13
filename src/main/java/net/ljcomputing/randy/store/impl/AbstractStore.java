package net.ljcomputing.randy.store.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.ljcomputing.randy.model.Model;
import net.ljcomputing.randy.random.RandomGenerator;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.reader.exception.ReaderException;
import net.ljcomputing.randy.store.Store;
import net.ljcomputing.randy.store.exception.StoreException;

/** Abstract data store implementation. */
public abstract class AbstractStore<M extends Model> implements Store<M> {
  /** The store's data reader. */
  protected final transient Reader reader;

  /** The data store. */
  protected transient List<M> store = new LinkedList<>();

  /**
   * Constructor.
   *
   * @param reader the data store reader
   * @throws StoreException StoreException
   */
  protected AbstractStore(final Reader reader) throws StoreException {
    this.reader = reader;

    try {
      for (final Map<String, Object> map : reader.resourceToListOfMaps()) {
        store.add(mapToModel(map));
      }
    } catch (StoreException | ReaderException e) {
      throw new StoreException("Exception encountered while loading store", e);
    }
  }

  /**
   * Map the given Map to the inferred Model.
   *
   * @param map the map of values
   * @return the Model populated from the Map values
   * @throws StoreException StoreException
   */
  public M mapToModel(final Map<String, Object> map, final Class<M> klass) throws StoreException {
    try {
      final M model = klass.getDeclaredConstructor().newInstance();

      if (map.get("id") != null) {
        final Object id = map.get("id");
        model.setId(Long.parseLong(id.toString()));
      }

      return model;
    } catch (InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchMethodException
        | SecurityException e) {
      throw new StoreException("Failed to map to model.", e);
    }
  }

  /**
   * The total records in the data store.
   *
   * @return the size of the data store
   */
  public int size() {
    return store.size();
  }

  /**
   * Retrieve the data from the store by the given index.
   *
   * @param index the idex of the data record to retrieve
   * @return return M the data in the store with the given index
   */
  public M retrieve(int index) {
    if (index >= 0 && index < size()) {
      return store.get(index);
    }

    return null;
  }

  /**
   * Retrieve the data from the store by a random index.
   *
   * @return M the data in the store with a random index
   */
  public M retrieve() {
    final int rand = RandomGenerator.getRandomInt(size());
    return retrieve(rand);
  }
}
