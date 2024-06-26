/*
 * Copyright (C) 2015 by Array Systems Computing Inc. http://www.array.ca
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see http://www.gnu.org/licenses/
 */
package eu.esa.sar.io.pyrate;

import org.esa.snap.core.dataio.AbstractProductWriter;
import org.esa.snap.core.dataio.EncodeQualification;
import org.esa.snap.core.dataio.ProductWriter;
import org.esa.snap.core.datamodel.Product;
import org.esa.snap.core.util.io.SnapFileFilter;
import org.esa.snap.dataio.geotiff.GeoTiffProductWriterPlugIn;

import java.io.File;
import java.util.Locale;

/**
 * The Gamma writer
 */
public class PyRateProductWriterPlugIn extends GeoTiffProductWriterPlugIn {

    private final SnapFileFilter fileFilter = new SnapFileFilter(getFormatNames()[0], getDefaultFileExtensions(), getDescription(null));
    /**
     * Constructs a new Gamma product writer plug-in instance.
     */
    public PyRateProductWriterPlugIn() {
    }

    @Override
    public EncodeQualification getEncodeQualification(Product product) {
        return new EncodeQualification(EncodeQualification.Preservation.FULL);
    }

    /**
     * Returns a string array containing the single entry.
     */
    public String[] getFormatNames() {
        return new String[]{"PyRate export"};
    }

    /**
     * Gets the default file extensions associated with each of the format names returned by the <code>{@link
     * #getFormatNames}</code> method. <p>The string array returned shall always have the same lenhth as the array
     * returned by the <code>{@link #getFormatNames}</code> method. <p>The extensions returned in the string array shall
     * always include a leading colon ('.') character, e.g. <code>".hdf"</code>
     *
     * @return the default file extensions for this product I/O plug-in, never <code>null</code>
     */
    public String[] getDefaultFileExtensions() {
        return new String[]{".conf"};
    }

    /**
     * Returns an array containing the classes that represent valid output types for this product writer.
     * <p>
     * <p> Intances of the classes returned in this array are valid objects for the <code>writeProductNodes</code>
     * method of the <code>AbstractProductWriter</code> interface (the method will not throw an
     * <code>InvalidArgumentException</code> in this case).
     *
     * @return an array containing valid output types, never <code>null</code>
     * @see AbstractProductWriter#writeProductNodes
     */
    public Class[] getOutputTypes() {
        return new Class[]{String.class, File.class};
    }

    /**
     * Gets a short description of this plug-in. If the given locale is set to <code>null</code> the default locale is
     * used.
     * <p>
     * <p> In a GUI, the description returned could be used as tool-tip text.
     *
     * @param locale the locale name for the given decription string, if <code>null</code> the default locale is used
     * @return a textual description of this product reader/writer
     */
    public String getDescription(Locale locale) {
        return "Gamma product writer";
    }

    /**
     * Creates an instance of the actual ENVI product writer class.
     *
     * @return a new instance of the <code>EnviProductWriter</code> class
     */
    public ProductWriter createWriterInstance() {
        return (ProductWriter) new PyRateProductWriter(this);
    }

    public SnapFileFilter getProductFileFilter() {
        return fileFilter;
    }
}
