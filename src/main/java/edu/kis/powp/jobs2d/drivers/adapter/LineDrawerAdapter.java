package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import javax.sound.sampled.Line;
import java.util.HashMap;
import java.util.Map;

/**
 * driver adapter to drawer with several bugs.
 */
public class LineDrawerAdapter implements Job2dDriver {

    public enum LineType{
        BASIC,
        DOTTED,
        SPECIAL
    }

    private int startX = 0, startY = 0;
    DrawPanelController drawPanelController;

    private LineType lineType = LineType.SPECIAL;

    public LineDrawerAdapter(LineType lineType) {
        drawPanelController = DrawerFeature.getDrawerController();
        this.lineType = lineType;
    }

    private ILine getCurrentLine(){

        switch(lineType){

            case BASIC:
                return LineFactory.getBasicLine();
            case DOTTED:
                return LineFactory.getDottedLine();
            case SPECIAL:
                return LineFactory.getSpecialLine();
        }

        return LineFactory.getBasicLine();
    }

    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void operateTo(int x, int y) {
        ILine line = getCurrentLine();
        line.setStartCoordinates(this.startX, this.startY);
        line.setEndCoordinates(x, y);

        setPosition(x, y);

        drawPanelController.drawLine(line);
    }

    @Override
    public String toString() {
        return "LineDrawerAdapter";
    }
}