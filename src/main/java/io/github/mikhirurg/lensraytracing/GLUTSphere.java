package io.github.mikhirurg.lensraytracing;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLUTSphere extends GLUTObject {

    private final double radius;

    public GLUTSphere(Vector3D shift, Vector3D scale, double radius, Color color) {
        super(shift, scale, color);
        this.radius = radius;
    }

    @Override
    public void draw(GL2 gl2, GLUT glut) {
        super.draw(gl2, glut);
        gl2.glTranslatef(
                (float) getShift().getX(),
                (float) getShift().getY(),
                (float) getShift().getZ()
        );
        gl2.glColor4f(
                getColor().getRed() / 255f,
                getColor().getGreen() / 255f,
                getColor().getBlue() / 255f,
                getColor().getAlpha() / 255f
        );
        glut.glutSolidSphere(radius, 20, 20);
        gl2.glTranslatef(
                (float) -getShift().getX(),
                (float) -getShift().getY(),
                (float) -getShift().getZ()
        );
    }
}
