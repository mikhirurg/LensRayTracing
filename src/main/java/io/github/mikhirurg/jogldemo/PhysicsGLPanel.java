package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;

import static com.jogamp.opengl.GL.*;

public class PhysicsGLPanel extends GLJPanel {

    private final List<Drawable> glObjectList;
    private final List<Ray> rays;

    public PhysicsGLPanel(int width, int height, Color background) {
        super(new GLCapabilities(GLProfile.get(GLProfile.GL2)));

        final GLU[] glu = {new GLU()};
        final int[] x = {0};
        final int[] y = {0};
        final int[] nx = {0};
        final int[] ny = {0};
        final double[] ex = {0.5};
        final double[] ey = {0.5};
        final double[] ez = {1};
        final double[] upx = {0};
        final double[] upy = {1};
        final double[] upz = {0};

        glObjectList = new LinkedList<>();
        rays = new LinkedList<>();
        setPreferredSize(new Dimension(width, height));

        addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable glAutoDrawable) {
                final GL2 gl2 = glAutoDrawable.getGL().getGL2();
                gl2.glClearColor(
                        background.getRed() / 255.0f,
                        background.getGreen() / 255.0f,
                        background.getBlue() / 255.0f,
                        background.getAlpha() / 255.0f
                );
                gl2.glEnable(GL_BLEND);
                gl2.glEnable(GL_LINE_SMOOTH);
                gl2.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                gl2.glShadeModel(GL2.GL_SMOOTH);
                gl2.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
            }

            @Override
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            @Override
            public void display(GLAutoDrawable glAutoDrawable) {
                final GL2 gl2 = glAutoDrawable.getGL().getGL2();
                final GLU glu = GLU.createGLU(gl2);
                final GLUT glut = new GLUT();

                gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
                gl2.glLoadIdentity();
                //glu.gluPerspective(60, (double) getWidth() / getHeight(), 0.1, 2);
                gl2.glOrtho(-0.5, 0.5, -0.5, 0.5, 0.1, 2);
                glu.gluLookAt(ex[0], ey[0], ez[0], 0, 0, 0, upx[0], upy[0], upz[0]);

                gl2.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
                gl2.glLoadIdentity();

                /*gl.glPolygonMode(GL_FRONT, GL_FILL);
                gl.glPolygonMode(GL_BACK, GL_LINE);*/

                for (Ray ray : rays) {
                    for (Drawable drawable : glObjectList) {
                        Cortege<Double, Vector3D, Vector3D> cortege = drawable.intersect(ray, true);
                        if (cortege != null) {
                            if (cortege.getThird() != null) {
                                Ray firstNormal = new Ray(cortege.getSecond(), cortege.getThird(), Color.red);
                                firstNormal.draw(gl2, 0.1);

                                Ray firstRefraction = ray.refract(ray.getVector(), cortege.getSecond(), cortege.getThird(), 1, 1.5);

                                Cortege<Double, Vector3D, Vector3D> newCortege = drawable.intersect(firstRefraction, false);
                                gl2.glEnable(GL2.GL_LINE_STIPPLE);
                                gl2.glLineStipple(1, (short) 0x1c47);
                                firstRefraction.draw(gl2, newCortege.getFirst());
                                gl2.glDisable(GL2.GL_LINE_STIPPLE);

                                Ray secondRefraction = firstRefraction.refract(firstRefraction.getVector(), newCortege.getSecond(), newCortege.getThird(), 1.5, 1);
                                secondRefraction.draw(gl2, 100);

                                Ray secondNormal = new Ray(newCortege.getSecond(), newCortege.getThird(), Color.red);
                                secondNormal.draw(gl2, 0.1);

                                ray.draw(gl2, cortege.getFirst());
                            }
                        }
                    }
                }

                for (Drawable drawable : glObjectList) {
                    drawable.draw(gl2, glut);
                }
            }

            @Override
            public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int w, int h) {

            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                x[0] = e.getX();
                y[0] = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                nx[0] = e.getX();
                ny[0] = e.getY();

                double dx = nx[0] - x[0];
                double a = (dx / getWidth()) * Math.PI;
                double nex = ex[0] * Math.cos(a) - ez[0] * Math.sin(a);

                double nupx = (upx[0] + ex[0]) * Math.cos(a) - (upz[0] + ez[0]) * Math.sin(a);
                upz[0] = (upx[0] + ex[0]) * Math.sin(a) + (upz[0] + ez[0]) * Math.cos(a);

                ez[0] = ex[0] * Math.sin(a) + ez[0] * Math.cos(a);
                ex[0] = nex;

                upz[0] = upz[0] - ez[0];
                upx[0] = nupx - ex[0];


                double dy = ny[0] - y[0];
                double b = -(dy / getHeight()) * Math.PI;
                double ney = ey[0] * Math.cos(b) - ez[0] * Math.sin(b);

                double nupy = (upy[0] + ey[0]) * Math.cos(b) - (upz[0] + ez[0]) * Math.sin(b);
                upz[0] = (upy[0] + ey[0]) * Math.sin(b) + (upz[0] + ez[0]) * Math.cos(b);

                ez[0] = ey[0] * Math.sin(b) + ez[0] * Math.cos(b);
                ey[0] = ney;

                upz[0] = upz[0] - ez[0];
                upy[0] = nupy - ey[0];

                x[0] = nx[0];
                y[0] = ny[0];
            }
        });
    }

    public void addObject(Drawable drawable) {
        glObjectList.add(drawable);
    }

    public void addRay(Ray ray) {
        rays.add(ray);
    }
}
