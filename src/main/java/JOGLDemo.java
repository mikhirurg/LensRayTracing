import com.jogamp.opengl.util.Animator;
import io.github.mikhirurg.jogldemo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JOGLDemo {

    public static void main(String[] args) {

        PhysicsGLPanel physicsGLPanel = new PhysicsGLPanel(600, 600, new Color(0, 0, 0));

        final Vector3D lightCenter = Vector3D.of(0, 0, 480);
        GLCube glCube = new GLCube(lightCenter, new Vector3D(30, 30, 30), new Color(0, 0, 255, 100));
        GLCylinderLens cylinderLens = new GLCylinderLens(600, 120, 60);

        Vector3D target0 = Vector3D.of(0, 0, 0).sub(lightCenter).getNormal();
        Vector3D target1 = cylinderLens.getTop().sub(lightCenter).getNormal();
        Vector3D target2 = cylinderLens.getRight().sub(lightCenter).getNormal();
        Vector3D target3 = cylinderLens.getLeft().sub(lightCenter).getNormal();
        Vector3D target4 = cylinderLens.getBottom().sub(lightCenter).getNormal();


        glCube.setIntersectable(false);
        cylinderLens.setIntersectable(true);

        Ray ray = new Ray(
                lightCenter,
                target0,
                Color.WHITE
        );

        Ray ray2 = new Ray(
                lightCenter,
                target1,
                Color.CYAN
        );

        Ray ray3 = new Ray(
                lightCenter,
                target2,
                Color.ORANGE
        );

        Ray ray4 = new Ray(
                lightCenter,
                target3,
                Color.ORANGE
        );

        Ray ray5 = new Ray(
                lightCenter,
                target4,
                Color.CYAN
        );

        cylinderLens.setColor(new Color(52, 146, 235, 100));
        physicsGLPanel.addObject(cylinderLens);
        physicsGLPanel.addObject(glCube);
        physicsGLPanel.addRay(ray);
        physicsGLPanel.addRay(ray2);
        physicsGLPanel.addRay(ray3);
        physicsGLPanel.addRay(ray4);
        physicsGLPanel.addRay(ray5);

        double step = 5;

        physicsGLPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        lightCenter.setZ(lightCenter.getZ() - step);
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 's':
                        lightCenter.setZ(lightCenter.getZ() + step);
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'a':
                        lightCenter.setX(lightCenter.getX() - step);
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'd':
                        lightCenter.setX(lightCenter.getX() + step);
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'r':
                        lightCenter.setY(lightCenter.getY() + step);
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'f':
                        lightCenter.setY(lightCenter.getY() - step);
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;
                }
            }
        });


        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame("JOGL demo");

            frame.add(physicsGLPanel);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);

            final Animator animator = new Animator(physicsGLPanel);
            animator.start();
        });

    }
}
