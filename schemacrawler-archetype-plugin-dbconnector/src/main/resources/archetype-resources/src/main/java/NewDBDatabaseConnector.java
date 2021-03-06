#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import schemacrawler.tools.databaseconnector.DatabaseConnector;
import schemacrawler.tools.databaseconnector.DatabaseServerType;
import schemacrawler.tools.iosource.ClasspathInputResource;

public final class NewDBDatabaseConnector
  extends DatabaseConnector
{

  private static final Logger LOGGER = Logger
    .getLogger(NewDBDatabaseConnector.class.getName());

  public NewDBDatabaseConnector()
    throws IOException
  {
    super(new DatabaseServerType("newdb", "NewDB"),
          new ClasspathInputResource("/help/Connections.newdb.txt"),
          new ClasspathInputResource("/schemacrawler-newdb.config.properties"),
          (informationSchemaViewsBuilder,
              connection) -> informationSchemaViewsBuilder
                .fromResourceFolder("/newdb.information_schema"),
          url -> Pattern.matches("jdbc:newdb:.*", url));
    // SchemaCrawler will control output of log messages if you use JDK logging
    LOGGER.log(Level.INFO, "Loaded plugin for NewDB");
  }
  
}
