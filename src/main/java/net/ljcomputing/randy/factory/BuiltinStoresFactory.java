package net.ljcomputing.randy.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import net.ljcomputing.randy.model.Model;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.reader.impl.JsonInputStreamReader;
import net.ljcomputing.randy.store.Store;
import net.ljcomputing.randy.store.exception.StoreException;
import net.ljcomputing.randy.store.impl.GenericStringStoreImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Factory used to load builtin data stores. */
public class BuiltinStoresFactory {
  /** The logger. */
  private static final Logger logger = LoggerFactory.getLogger(BuiltinStoresFactory.class);

  /** Properties used by the factory. */
  private static final Properties properies = new Properties();

  /** Properties file containing configuration properties. */
  private static final String propertiesFile = "/builtinStores.properties";

  /** Set of all available builtin stores. */
  private static final Set<String> storeNames = new TreeSet<>();

  /** Map of builtin stores available. */
  private static final Map<String, Store<? extends Model>> stores = new HashMap<>();

  /** Constructor. */
  public BuiltinStoresFactory() {
    try (InputStream inputStream = BuiltinStoresFactory.class.getResourceAsStream(propertiesFile)) {
      properies.load(inputStream);

      final String[] storesProp = properies.getProperty("stores").trim().split(",");
      storeNames.addAll(Arrays.asList(storesProp));
    } catch (IOException e) {
      logger.error("Cannot read properties file {}:", propertiesFile, e);
    }
  }

  /**
   * Load the given store with data.
   *
   * @param storeName the name of the store to load with data
   * @throws StoreException StoreException
   */
  private static void load(final String storeName) throws StoreException {
    final String definition = String.format("jar:/stores/%s.json", storeName);
    final Reader reader = new JsonInputStreamReader(definition);

    try {
      stores.put(storeName, new GenericStringStoreImpl(reader));
    } catch (StoreException e) {
      throw e;
    }
  }

  /**
   * Get the available builtin store names as a Set.
   *
   * @return the Set of available builtin store names
   */
  public Set<String> getStoreNames() {
    return Collections.unmodifiableSet(storeNames);
  }

  /**
   * Get the given store.
   *
   * @param storeName the name of the store to retrieve
   * @return the store
   * @throws StoreException StoreException
   */
  public Store<? extends Model> getStore(final String storeName) throws StoreException {
    if (!storeNames.contains(storeName)) {
      return null;
    } else {
      if (stores.get(storeName) == null) {
        try {
          load(storeName);
        } catch (StoreException e) {
          throw e;
        }
      }

      return (Store<? extends Model>) stores.get(storeName);
    }
  }
}
