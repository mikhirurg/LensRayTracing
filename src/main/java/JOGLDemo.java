import com.jogamp.opengl.util.Animator;
import io.github.mikhirurg.jogldemo.*;

import javax.swing.*;
import java.awt.*;

public class JOGLDemo {

    public static void main(String[] args) {

        PhysicsGLPanel physicsGLPanel = new PhysicsGLPanel(600, 600, new Color(212, 232, 221));

        Vector3D lightCenter = Vector3D.of(0, 0, 0.8);
        GLCube glCube = new GLCube(lightCenter, new Vector3D(0.05, 0.05, 0.05), new Color(0, 0, 255, 100));
        GLCylinderLens cylinderLens = new GLCylinderLens(1, 0.2, 0.1);

        Vector3D target0 = Vector3D.of(0, 0, 0).sub(lightCenter).getNormal();
        Vector3D target1 = cylinderLens.getTop().sub(lightCenter).getNormal();
        Vector3D target2 = cylinderLens.getRight().sub(lightCenter).getNormal();
        /*Vector3D target3 = Vector3D.of(0, 0, 0).sub(lightCenter).getNormal();
        Vector3D target4 = Vector3D.of(0, 0, 0).sub(lightCenter).getNormal();
        Vector3D target5 = Vector3D.of(0, 0, 0).sub(lightCenter).getNormal();*/


        glCube.setIntersectable(false);

        Ray ray = new Ray(
                lightCenter,
                target0,
                Color.BLACK
        );

        Ray ray2 = new Ray(
                lightCenter,
                target1,
                Color.BLACK
        );

        Ray ray3 = new Ray(
                lightCenter,
                target2,
                Color.BLACK
        );

        cylinderLens.setColor(new Color(52, 146, 235, 100));
        physicsGLPanel.addObject(cylinderLens);
        physicsGLPanel.addObject(glCube);
       // physicsGLPanel.addRay(ray);
        //physicsGLPanel.addRay(ray2);
        physicsGLPanel.addRay(ray3);

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
