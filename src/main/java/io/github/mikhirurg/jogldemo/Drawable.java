package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public interface Drawable {
    void draw(GL2 gl2, GLUT glut);

    void setShift(Vector3D shift);

    void setScale(Vector3D scale);

    void setColor(Color color);

    Vector3D getShift();

    Vector3D getScale();

    Color getColor();

    Cortege<Double, Vector3D, Vector3D> intersect(Ray ray, boolean near);

    void setIntersectable(boolean intersectable);

    boolean getIntersetable();
}
