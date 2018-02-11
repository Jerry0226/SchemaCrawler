/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2018, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/
package schemacrawler.tools.executable;


import java.util.Arrays;
import java.util.Collection;

import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.options.OutputOptions;

public interface CommandProvider
{

  @Deprecated
  Executable configureNewExecutable(SchemaCrawlerOptions schemaCrawlerOptions,
                                    OutputOptions outputOptions)
    throws SchemaCrawlerException;

  default Executable configureNewExecutable(final String command,
                                            final SchemaCrawlerOptions schemaCrawlerOptions,
                                            final OutputOptions outputOptions)
    throws SchemaCrawlerException
  {
    return configureNewExecutable(getCommand(),
                                  schemaCrawlerOptions,
                                  outputOptions);
  }

  @Deprecated
  String getCommand();

  String getHelpAdditionalText();

  String getHelpResource();

  default Collection<String> getSupportedCommands()
  {
    return Arrays.asList(getCommand());
  }

  default boolean supportsCommand(final String command,
                                  final SchemaCrawlerOptions schemaCrawlerOptions,
                                  final OutputOptions outputOptions)
  {
    return getCommand().equals(command);
  }

}
