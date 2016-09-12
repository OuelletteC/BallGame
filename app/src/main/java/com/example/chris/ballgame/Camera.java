package com.example.chris.ballgame;

public class Camera {

    private int worldSizeX = 3000, worldSizeY = 3000, offsetMinX = 0, offsetMinY = 0;
    private float camX, camY, offsetMaxX, offsetMaxY;

    public Camera(int worldSizeX, int worldSizeY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
    }

    public float getCamX(float playerX, float screenSizeX) {
        camX = playerX - (screenSizeX / 2);
        offsetMaxX = worldSizeX - screenSizeX;
        return camX;
        /*
        if (camX > offsetMaxX)
            return offsetMaxX;
        else if (camX < offsetMinX)
            return offsetMinX;
        else
            return camX;
            */
    }

    public float getCamY(float playerY, float screenSizeY) {
        camY = playerY - (screenSizeY / 2);
        offsetMaxY = worldSizeY - screenSizeY;
        return camY;
        /*
        if (camY > offsetMaxY)
            return offsetMaxY;
        else if (camY < offsetMinY)
            return offsetMinY;
        else
            return camY;
            */
    }

}

