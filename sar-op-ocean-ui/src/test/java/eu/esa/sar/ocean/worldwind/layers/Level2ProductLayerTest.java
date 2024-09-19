/*
 * Copyright (C) 2024 by SkyWatch Space Applications Inc. http://www.skywatch.com
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
package eu.esa.sar.ocean.worldwind.layers;

import com.bc.ceres.annotation.STTM;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwindx.examples.util.DirectedPath;
import org.esa.snap.worldwind.ColorBarLegend;
import org.junit.Before;
import org.junit.Test;
import gov.nasa.worldwind.render.Renderable;
import org.esa.snap.core.datamodel.GeoPos;
import org.esa.snap.core.datamodel.Product;
import org.esa.snap.worldwind.ProductRenderablesInfo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Level2ProductLayerTest {

    private Level2ProductLayer level2ProductLayer;
    private Product mockProduct;
    private WorldWindowGLCanvas mockWwd;

    @Before
    public void setUp() {
        level2ProductLayer = new Level2ProductLayer();
        mockProduct = mock(Product.class);
        mockWwd = mock(WorldWindowGLCanvas.class);
    }

    @Test
    public void getSuitability_returnsSuitableForOCNProduct() {
        when(mockProduct.getProductType()).thenReturn("OCN");
        assertEquals(Level2ProductLayer.Suitability.INTENDED, level2ProductLayer.getSuitability(mockProduct));

        when(mockProduct.getProductType()).thenReturn("SLC");
        assertEquals(Level2ProductLayer.Suitability.UNSUITABLE, level2ProductLayer.getSuitability(mockProduct));
    }

    @Test
    public void getSuitability_returnsUnsuitableForNonOCNProduct() {
        when(mockProduct.getProductType()).thenReturn("NON_OCN");

        assertEquals(Level2ProductLayer.Suitability.UNSUITABLE, level2ProductLayer.getSuitability(mockProduct));
    }

    @Test
    public void createColorSurface_createsAnalyticSurface() {
        GeoPos geoPos1 = new GeoPos(0, 0);
        GeoPos geoPos2 = new GeoPos(1, 1);
        double[] latValues = {0, 1};
        double[] lonValues = {0, 1};
        double[] vals = {0.5, 1.0};
        ArrayList<Renderable> renderableList = new ArrayList<>();
        ProductRenderablesInfo prodRenderInfo = new ProductRenderablesInfo();

        level2ProductLayer.createColorSurface(geoPos1, geoPos2, latValues, lonValues, vals,
                2, 2, renderableList, prodRenderInfo, "comp");
    }

    @Test
    public void createColorBarLegend_createsLegendWithCorrectAttributes() {
        level2ProductLayer.createColorBarLegend(0, 10, "Test Legend", "testComp");

        ColorBarLegend legend = level2ProductLayer.theColorBarLegendHash.get("testComp");
        assertNotNull(legend);
    }

    @Test
    public void setComponentVisible_updatesVisibilityCorrectly() {
        level2ProductLayer.createColorBarLegend(0, 10, "Test Legend", "testComp");
        level2ProductLayer.setComponentVisible("testComp", mockWwd);

        assertTrue(level2ProductLayer.theColorBarLegendHash.get("testComp").isVisible());
    }

    @Test
    public void getControlPanel_initializesControlPanelCorrectly() {
        JPanel controlPanel = level2ProductLayer.getControlPanel(mockWwd);

        assertNotNull(controlPanel);
        assertEquals(6, controlPanel.getComponentCount());
    }

    @Test
    @STTM("SNAP-2521")
    public void addWaveLengthArrows_createsArrowsCorrectly() {
        double[] latValues = {0.0, 1.0};
        double[] lonValues = {0.0, 1.0};
        double[] waveLengthValues = {4000.0, 8000.0, -999.0};
        double[] waveDirValues = {45.0, 90.0};
        List<Renderable> renderableList = new ArrayList<>();

        level2ProductLayer.addWaveLengthArrows(latValues, lonValues, waveLengthValues, waveDirValues, renderableList);

        assertEquals(2, renderableList.size());
        assertTrue(renderableList.get(0) instanceof DirectedPath);
        assertTrue(renderableList.get(1) instanceof DirectedPath);
    }
}

