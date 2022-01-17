package net.ljcomputing.randy.reader.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.ljcomputing.randy.reader.Reader;
import net.ljcomputing.randy.reader.exception.ReaderException;

/** XML input stream reader implementation. */
public class JdbcReader extends AbstractReader implements Reader {
  /** The SQL of the query to execute. */
  private final transient String query;

  /** The bind parameters used in the query to execute. */
  private transient List<Object> parameters;

  /**
   * Constructor.
   *
   * @param resourceDefinition the definition of the resource
   * @param query the query used to populate data store
   */
  public JdbcReader(final String resourceDefinition, final String query) {
    this(resourceDefinition, query, new ArrayList<>());
  }

  /**
   * Constructor.
   *
   * @param resourceDefinition the definition of the resource
   * @param query the query used to populate data store
   * @param parameters bind parameters used in query
   */
  public JdbcReader(
      final String resourceDefinition, final String query, final List<Object> parameters) {
    super(resourceDefinition);
    this.query = query;
    this.parameters = parameters;
  }

  /**
   * Read the resource into a List of Map values.
   *
   * @return List of Map values
   * @throws ReaderException ReaderException
   */
  @Override
  public List<Map<String, Object>> resourceToListOfMaps() throws ReaderException {
    try (final Connection connection = DriverManager.getConnection(resourceDefinition);
        final PreparedStatement statement = connection.prepareStatement(query)) {

      if (!parameters.isEmpty()) {
        for (int i = 0; i < parameters.size(); i++) {
          statement.setObject(i + 1, parameters.get(i));
        }
      }

      try (final ResultSet resultSet = statement.executeQuery()) {
        return resultSetToList(resultSet);
      }
    } catch (Exception e) {
      throw new ReaderException("Failed to retrieve resource into list of maps", e);
    }
  }

  /**
   * Read the given InputStream and return a List of Maps.
   *
   * @param resultSet the result set of the query
   * @return a List of Map values
   * @throws ReaderException ReaderException
   */
  protected List<Map<String, Object>> resultSetToList(final ResultSet resultSet)
      throws ReaderException {
    try {
      final List<Map<String, Object>> list = new LinkedList<>();
      final Set<String> columns = getColumns(resultSet);

      while (resultSet.next()) {
        final Map<String, Object> valueMap = new HashMap<>();
        final Map<String, Object> map = new HashMap<>();

        for (final String column : columns) {
          map.put(column, resultSet.getObject(column));
        }

        valueMap.put("value", map);
        list.add(valueMap);
      }

      return list;
    } catch (Exception e) {
      throw new ReaderException("Failed to convert JSON to List: ", e);
    }
  }

  /**
   * Get the columns selected from the ResultSetMetaData.
   *
   * @param resultSet the ResultSet
   * @return a Set of column names
   * @throws SQLException SQLException
   */
  private Set<String> getColumns(final ResultSet resultSet) throws SQLException {
    final Set<String> columns = new HashSet<>();
    final ResultSetMetaData rsmd = resultSet.getMetaData();
    final int columnCount = rsmd.getColumnCount();

    for (int i = 1; i <= columnCount; i++) {
      columns.add(rsmd.getColumnName(i));
    }

    return columns;
  }
}
