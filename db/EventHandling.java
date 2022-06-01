package tmvf.wobbler;

import android.view.MotionEvent;

public class EventHandling {
    OpenGLRenderer openGLRenderer;

    public EventHandling(MainActivity mainActivity, OpenGLRenderer openGLRenderer) {
        this.openGLRenderer = openGLRenderer;
    }

    public void onTouchEvent(MotionEvent event, MainActivity mainActivity) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            if (!openGLRenderer.selectShape) {
                if (event.getX() < ProjectionMatrix.width / 2) {
                    if (event.getY() < ProjectionMatrix.height / 3) {
                        openGLRenderer.indexRandom = 0;
                        openGLRenderer.selectShape();
                        openGLRenderer.selectShape = true;
                    } else if (event.getY() > ProjectionMatrix.height / 3 * 2) {
                        openGLRenderer.indexRandom = 3;
                        openGLRenderer.selectShape();
                        openGLRenderer.selectShape = true;
                    }
                } else {
                    if (event.getY() < ProjectionMatrix.height / 3) {
                        openGLRenderer.indexRandom = 1;
                        openGLRenderer.selectShape();
                        openGLRenderer.selectShape = true;
                    } else if (event.getY() > ProjectionMatrix.height / 3 * 2) {
                        openGLRenderer.indexRandom = 2;
                        openGLRenderer.selectShape();
                        openGLRenderer.selectShape = true;
                    }
                }
            } else {
                if (event.getX() > ProjectionMatrix.width / 7 * 3 &&
                        event.getX() < ProjectionMatrix.width / 7 * 4 &&
                        event.getY() < ProjectionMatrix.width / 7 &&
                        !openGLRenderer.addShape) {
                    openGLRenderer.spinning = !openGLRenderer.spinning;
                } else if (event.getX() > ProjectionMatrix.width / 2 * (1 - 60 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (1 + 60 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 135f / 400 * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 15f / 400 * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.defaultValue();
                    openGLRenderer.dx = 0;
                    openGLRenderer.dy = 0;
                    CameraMatrix.clearRotateCurrentShape();
                    openGLRenderer.selectShape = false;
                } else if (event.getX() > ProjectionMatrix.width / 2 * (1 - 60 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (1 + 60 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 285f / 400 * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 175f / 400 * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.dx = 0;
                    openGLRenderer.dy = 0;
                    openGLRenderer.addShape = true;
                } else if (event.getX() > ProjectionMatrix.width / 2 * (40 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (140 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 190 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 110 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.moveXM();
                } else if (event.getX() > ProjectionMatrix.width / 2 * (220 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (320 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 190 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 110 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.moveXP();
                } else if (event.getX() > ProjectionMatrix.width / 2 * (140 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (220 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 290 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 190 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.moveYP();
                } else if (event.getX() > ProjectionMatrix.width / 2 * (140 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (220 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 110 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 10 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.moveYM();
                } else if (event.getX() > ProjectionMatrix.width / 2 * (690 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (790 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 185 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 85 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.rotateX();
                } else if (event.getX() > ProjectionMatrix.width / 2 * (590 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (690 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 290 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 190 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.rotateY();
                } else if (event.getX() > ProjectionMatrix.width / 2 * (530 / 400f) &&
                        event.getX() < ProjectionMatrix.width / 2 * (630 / 400f) &&
                        event.getY() > (1 + -1 * (-1 + 140 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2 &&
                        event.getY() < (1 + -1 * (-1 + 30 / 400f * ProjectionMatrix.width /
                                ProjectionMatrix.height)) * ProjectionMatrix.height / 2) {
                    openGLRenderer.rotateZ();
                }
            }
        }
    }
}
