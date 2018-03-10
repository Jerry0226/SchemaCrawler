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
package schemacrawler.tools.integration.graph;


import static sf.util.Utility.isBlank;

import java.util.List;
import java.util.logging.Level;

import schemacrawler.tools.options.OutputFormat;
import schemacrawler.tools.options.OutputFormatState;
import sf.util.SchemaCrawlerLogger;
import sf.util.StringFormat;

public enum GraphOutputFormat
  implements
  OutputFormat
{

 htmlx("SchemaCrawler graph embedded in HTML5"),
 scdot("SchemaCrawler generated format"),
 //
 bmp("Windows Bitmap Format"),
 canon("DOT"),
 dot("DOT"),
 gv("DOT"),
 xdot("DOT", "xdot1.2", "xdot1.4"),
 cgimage("CGImage bitmap format"),
 cmap("Client-side imagemap (deprecated)"),
 eps("Encapsulated PostScript"),
 exr("OpenEXR"),
 fig("FIG"),
 gd("GD/GD2 formats", "gd2"),
 gif("GIF"),
 gtk("GTK canvas"),
 ico("Icon Image File Format"),
 imap("Server-side and client-side imagemaps", "cmapx", "imap_np", "cmapx_np"),
 jp2("JPEG 2000"),
 jpg("JPEG", "jpeg", "jpe"),
 pct("PICT", "pict"),
 pdf("Portable Document Format (PDF)"),
 pic("Kernighan's PIC graphics language"),
 plain("Simple text format", "plain-ext"),
 png("Portable Network Graphics format"),
 pov("POV-Ray markup language (prototype)"),
 ps("PostScript"),
 ps2("PostScript for PDF"),
 psd("PSD"),
 sgi("SGI"),
 svg("Scalable Vector Graphics"),
 svgz("Scalable Vector Graphics"),
 tga("Truevision TGA"),
 tiff("TIFF (Tag Image File Format)", "tif"),
 tk("TK graphics"),
 vml("Vector Markup Language (VML)"),
 vmlz("Vector Markup Language (VML)"),
 vrml("VRML"),
 wbmp("Wireless BitMap format"),
 webp("Image format for the Web"),
 xlib("Xlib canvas", "x11"),;

  private static final SchemaCrawlerLogger LOGGER = SchemaCrawlerLogger
    .getLogger(GraphOutputFormat.class.getName());

  /**
   * Gets the value from the format.
   *
   * @param format
   *        Graph output format.
   * @return GraphOutputFormat
   */
  public static GraphOutputFormat fromFormat(final String format)
  {
    final GraphOutputFormat outputFormat = fromFormatOrNull(format);
    if (outputFormat == null)
    {
      LOGGER
        .log(Level.CONFIG,
             new StringFormat("Unknown format <%s>, using default", format));
      return png;
    }
    else
    {
      return outputFormat;
    }
  }

  /**
   * Checks if the value of the format is supported.
   *
   * @return True if the format is a graph output format
   */
  public static boolean isSupportedFormat(final String format)
  {
    return fromFormatOrNull(format) != null;
  }

  private static GraphOutputFormat fromFormatOrNull(final String format)
  {
    if (isBlank(format))
    {
      return null;
    }
    for (final GraphOutputFormat outputFormat: GraphOutputFormat.values())
    {
      if (outputFormat.outputFormatState.isSupportedFormat(format))
      {
        return outputFormat;
      }
    }
    return null;
  }

  private final OutputFormatState outputFormatState;

  private GraphOutputFormat(final String description)
  {
    outputFormatState = new OutputFormatState(name(), description);
  }

  private GraphOutputFormat(final String description,
                            final String... additionalFormatSpecifiers)
  {
    outputFormatState = new OutputFormatState(name(),
                                              description,
                                              additionalFormatSpecifiers);
  }

  @Override
  public String getDescription()
  {
    return outputFormatState.getDescription();
  }

  @Override
  public String getFormat()
  {
    return outputFormatState.getFormat();
  }

  @Override
  public List<String> getFormats()
  {
    return outputFormatState.getFormats();
  }

  @Override
  public String toString()
  {
    return outputFormatState.toString();
  }

}
