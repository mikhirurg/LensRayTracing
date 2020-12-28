import com.jogamp.opengl.util.Animator;
import io.github.mikhirurg.jogldemo.*;

import javax.swing.*;
import java.awt.*;

public class JOGLDemo {

    public static void main(String[] args) {

        PhysicsGLPanel physicsGLPanel = new PhysicsGLPanel(600, 600, new Color(212, 232, 221));

        //GLCube glCube = new GLCube(new Vector3D(0, 0, 0), new Vector3D(0.1, 0.1, 0.1), new Color(0, 0, 255, 100));
        //GLTrianglePyramid glTrianglePyramid = new GLTrianglePyramid(new Vector3D(-0.2, 0.2, 0), new Vector3D(0.1, 0.3, 0.1), Color.RED);
        //GLUTSphere sphere = new GLUTSphere(Vector3D.ZERO, Vector3D.ONE.mul(0.1), 0.1, Color.ORANGE);
        GLUTCylinder cylinder = new GLUTCylinder(Vector3D.ZERO, Vector3D.ONE.mul(0.1), new Color(52, 146, 235, 100), 0.2, 0.2);
        GLCylinderLens cylinderLens = new GLCylinderLens(0.2, 0.2, 0.1);
        //GLCylinderLens cylinderLens2 = new GLCylinderLens(0.2, 0.2, 0.2);


        Ray ray = new Ray(
          Vector3D.of(0, 0, 0.4),
          Vector3D.of(0.12, 0, -1),
          Color.BLACK
        );

        Ray ray2 = new Ray(
                Vector3D.of(0, 0, 0.4),
                Vector3D.of(0, 0, -1),
                Color.BLACK
        );

        Ray ray3 = new Ray(
                Vector3D.of(0, 0, 0.4),
                Vector3D.of(-0.18, 0, -1),
                Color.BLACK
        );


        /*physicsGLPanel.addObject(glTrianglePyramid);
        physicsGLPanel.addObject(sphere);*/
        //physicsGLPanel.addObject(cylinder);
        //physicsGLPanel.addObject(glCube);
        cylinderLens.setColor(new Color(52, 146, 235, 100));
        //cylinderLens2.setColor(new Color(52, 146, 235, 100));
        physicsGLPanel.addObject(cylinderLens);
        //physicsGLPanel.addObject(cylinderLens2);
        physicsGLPanel.addRay(ray);
        //physicsGLPanel.addRay(ray2);
        //physicsGLPanel.addRay(ray3);

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
