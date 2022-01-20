Randy Data
==============

A Java API to generate random data.
-----------------------------------

**Goals**

Randy data provides a way to generate random data for unit testing.

**Features:**

- Several builtin data sources of random data
- The ability to generate random data from various data sources, such as: 
  - JSON
  - CSV
  - XML
  - JDBC

**Quick Start**

```java
    import net.ljcomputing.randy.factory.BuiltinStoresFactory;
    import net.ljcomputing.randy.store.GenericStringStore;

    // retrieve the builtin store factory
    final BuiltinStoresFactory factory = new BuiltinStoresFactory();
    // retrieve a store by name
    final GenericStringStore store = (GenericStringStore) factory.getStore("femaleGivenNames");
    // retrieve a random value from the store
    final String randomValue = store.retrieve().getValue();
```

**Stores**

There are two types of data stores at this time:
- GenericStringStore - a list of single string values
- GenericMapStore - a list of value maps

**Readers**

The API provides readers for various types of data sources. For example, one could use a SQL query to populatre a GenericMapStore. This allows for the flexibility to have random data from live data sources.

```java
    import java.util.LinkedList;
    import java.util.List;

    import net.ljcomputing.randy.store.GenericMapStore;
    import net.ljcomputing.randy.store.impl.GenericMapStoreImpl;

    // define a JDBC data source
    final String resourceDefinition =
        "jdbc:postgresql://localhost/osm2?user=<username>&password=<password>>";
    
    // define a SQL query to retrieve sample data
    final String query =
        "SELECT county_num \"id\", county_nam \"countyName\" FROM pa_roads.pacounty2020_01 p "
            + "WHERE county_nam like ? ORDER BY county_nam";
    
    // define the bind parameters to use in the SQL query
    final List<Object> parameters = new LinkedList<>();
    parameters.add("P%");

    // define the JDBC data source reader
    final Reader reader = new JdbcReader(resourceDefinition, query, parameters);

    // define a GenericMapStore using the JDBC reader
    final GenericMapStore store = new GenericMapStoreImpl(reader);

    // retrieve a random value from the data store
    final String result = store.retrieve().getValue().get("countyName").toString();
```

**Issue Tracking**

Please consult the JavaDoc documentation **first** before saying there's an issue. The API does have TestNG unit tests (which should also be consulted) that have passed on **my** machine, but **do** need to be modified if you intend to test on your own machine. This will be addressed at a future date.

If you encounter an issue, please enter the issue in the Issues section of the GitHub repository (https://github.com/willmorejg/net.ljcomputing.randy-data/issues).

One note - at this time, I am the only contributor to the project. I will attempt to address issues in a timely fashion, but make no guarantees on how quickly the issues will be addressed.

**License**

The Randy Data API is released under the Apache 2.0 license. Visit https://www.apache.org/licenses/LICENSE-2.0 for more details.
