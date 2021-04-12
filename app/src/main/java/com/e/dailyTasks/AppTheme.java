package com.e.dailyTasks;

public class AppTheme {

    private int themeResource;
    private int color;
    private int colorForCanvas;

    public AppTheme(int themeResource, int color, int colorForCanvas) {
        this.themeResource = themeResource;
        this.color = color;
        this.colorForCanvas = colorForCanvas;
    }

    public int getColorForCanvas() {
        return colorForCanvas;
    }

    public int getColor() {
        return color;
    }

    public int getThemeResource() {
        return themeResource;
    }

    public void setColorForCanvas(int colorForCanvas) {
        this.colorForCanvas = colorForCanvas;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setThemeResource(int themeResource) {
        this.themeResource = themeResource;
    }
}
