package ru.nsu.fit.dskvl.gfx.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import ru.nsu.fit.dskvl.gfx.models.Spline;
import ru.nsu.fit.dskvl.gfx.models.Vec4;

public class OutlineEditorPoint extends JComponent {
    private Vec4 modelCoordinates;
    private final OutlineEditor parent;
    private final double relativeDiameter = 0.03;
    private boolean isActive = false;
    private final int index;

    public OutlineEditorPoint(Vec4 coordinates, OutlineEditor parent, 
				CoordinatesListener listener, Spline spline, int index) {
        this.modelCoordinates = coordinates;
        this.parent = parent;
        this.index = index;
        setSize(getWidth(), getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
						RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(isActive ? Color.YELLOW : Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval(1, 1, getWidth()-2, getHeight()-2);
    }

    @Override public int getX() {
        return (int) (
						modelCoordinates.x()/parent.getScale()*parent.getWidth() 
						 + parent.getWidth()/2 
						 - getWidth()/2
				);
    }
    @Override public int getY() {
				var p = parent;
        return (int) (
						modelCoordinates.y()/p.getScale()*p.getAspectRatio()*p.getHeight() 
						+ parent.getHeight()/2
						- getHeight()/2
				);
    }
    @Override public int getWidth() { 
				return (int) (relativeDiameter*parent.getWidth()); 
		}
    @Override public int getHeight() {
        return (int) (relativeDiameter*parent.getWidth());
    }
    @Override public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }
    @Override public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    @Override public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    public void     setActive(boolean status) 		{ this.isActive = status; }
    public boolean  isActive()                		{ return isActive; }
    public Vec4  		getModelCoordinates()     	 	{ return modelCoordinates; }
    public void     setModelCoordinates(Vec4 val) { modelCoordinates = val; }
    public int      getIndex()                		{ return index; }
}
