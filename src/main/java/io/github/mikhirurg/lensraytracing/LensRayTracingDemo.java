package io.github.mikhirurg.lensraytracing;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LensRayTracingDemo extends JFrame {

    private static Vector3D lightCenter;
    private static Double radius = 600.0;
    private static Double height = 120.0;
    private static Double d = 60.0;

    private static Ray ray;
    private static Ray ray2;
    private static Ray ray3;
    private static Ray ray4;
    private static Ray ray5;

    private static GLCube glCube;
    private static GLCylinderLens cylinderLens;
    private static PhysicsGLPanel physicalPanel;

    private static JTextField vectorX;
    private static JTextField vectorY;
    private static JTextField vectorZ;
    private static JTextField lensRadius;
    private static JTextField lensHeight;
    private static JTextField lensD;

    private PhysicsGLPanel buildScene() {
        PhysicsGLPanel physicsGLPanel = new PhysicsGLPanel(700, 700, new Color(0, 0, 0));

        lightCenter = Vector3D.of(0, 0, 480);
        glCube = new GLCube(lightCenter, new Vector3D(30, 30, 30), new Color(0, 0, 255, 100));
        cylinderLens = new GLCylinderLens(radius, height, d);

        Vector3D target0 = Vector3D.of(0, 0, 0).sub(lightCenter).getNormal();
        Vector3D target1 = cylinderLens.getTop().sub(lightCenter).getNormal();
        Vector3D target2 = cylinderLens.getRight().sub(lightCenter).getNormal();
        Vector3D target3 = cylinderLens.getLeft().sub(lightCenter).getNormal();
        Vector3D target4 = cylinderLens.getBottom().sub(lightCenter).getNormal();


        glCube.setIntersectable(false);
        cylinderLens.setIntersectable(true);

        ray = new Ray(
                lightCenter,
                target0,
                Color.WHITE
        );

        ray2 = new Ray(
                lightCenter,
                target1,
                Color.CYAN
        );

        ray3 = new Ray(
                lightCenter,
                target2,
                Color.ORANGE
        );

        ray4 = new Ray(
                lightCenter,
                target3,
                Color.ORANGE
        );

        ray5 = new Ray(
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
                        vectorZ.setText(String.valueOf(lightCenter.getZ()));
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 's':
                        lightCenter.setZ(lightCenter.getZ() + step);
                        vectorZ.setText(String.valueOf(lightCenter.getZ()));
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'a':
                        lightCenter.setX(lightCenter.getX() - step);
                        vectorX.setText(String.valueOf(lightCenter.getX()));
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'd':
                        lightCenter.setX(lightCenter.getX() + step);
                        vectorX.setText(String.valueOf(lightCenter.getX()));
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'r':
                        lightCenter.setY(lightCenter.getY() + step);
                        vectorY.setText(String.valueOf(lightCenter.getY()));
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;

                    case 'f':
                        lightCenter.setY(lightCenter.getY() - step);
                        vectorY.setText(String.valueOf(lightCenter.getY()));
                        ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                        ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                        ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                        ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                        ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());
                        break;
                }
            }
        });

        return physicsGLPanel;
    }

    private void buildGui() {
        try {
            if (Application.getOS() != Application.Os.MAC) {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            }
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 10;
        physicalPanel = buildScene();
        physicalPanel.setFocusable(true);
        add(physicalPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets.right = 5;
        c.insets.bottom = 10;
        c.insets.top = 10;
        c.insets.left = 10;
        c.ipadx = 10;
        JPanel xPanel = new JPanel();
        xPanel.setLayout(new GridLayout(1, 2));


        JLabel vectorXLabel = new JLabel("X:");
        xPanel.add(vectorXLabel);

        vectorX = new JTextField(String.valueOf(lightCenter.getX()));
        xPanel.add(vectorX);

        add(xPanel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JPanel yPanel = new JPanel();
        yPanel.setLayout(new GridLayout(1, 2));

        JLabel vectorYLabel = new JLabel("Y:");
        yPanel.add(vectorYLabel);

        vectorY = new JTextField(String.valueOf(lightCenter.getY()));
        yPanel.add(vectorY);

        add(yPanel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        JPanel zPanel = new JPanel();
        zPanel.setLayout(new GridLayout(1, 2));

        JLabel vectorZLabel = new JLabel("Z:");
        zPanel.add(vectorZLabel);

        vectorZ = new JTextField(String.valueOf(lightCenter.getZ()));
        zPanel.add(vectorZ);

        add(zPanel, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        JPanel radiusPanel = new JPanel();
        radiusPanel.setLayout(new GridLayout(1, 2));

        JLabel lensRadiusLabel = new JLabel("Radius:");
        radiusPanel.add(lensRadiusLabel);

        lensRadius = new JTextField(String.valueOf(radius));
        radiusPanel.add(lensRadius);
        add(radiusPanel, c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new GridLayout(1, 2));

        JLabel lensHeightLabel = new JLabel("Height:");
        heightPanel.add(lensHeightLabel);

        lensHeight = new JTextField(String.valueOf(height));
        heightPanel.add(lensHeight);

        add(heightPanel, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        JPanel dPanel = new JPanel();
        dPanel.setLayout(new GridLayout(1, 2));

        JLabel lensDLabel = new JLabel("D:");
        dPanel.add(lensDLabel);

        lensD = new JTextField(String.valueOf(d));
        dPanel.add(lensD);
        add(dPanel, c);

        JButton apply = new JButton("Apply");
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(apply, c);
        apply.addActionListener(e -> {
            try {
                double vx = Double.parseDouble(vectorX.getText());
                double vy = Double.parseDouble(vectorY.getText());
                double vz = Double.parseDouble(vectorZ.getText());
                double lr = Double.parseDouble(lensRadius.getText());
                double lh = Double.parseDouble(lensHeight.getText());
                double ld = Double.parseDouble(lensD.getText());

                lightCenter.setX(vx);
                lightCenter.setY(vy);
                lightCenter.setZ(vz);

                cylinderLens.setRadius(lr);
                cylinderLens.setHeight(lh);
                cylinderLens.setD(ld);
                cylinderLens.refresh();

                ray.setVector(Vector3D.of(0, 0, 0).sub(lightCenter).getNormal());
                ray2.setVector(cylinderLens.getTop().sub(lightCenter).getNormal());
                ray3.setVector(cylinderLens.getRight().sub(lightCenter).getNormal());
                ray4.setVector(cylinderLens.getLeft().sub(lightCenter).getNormal());
                ray5.setVector(cylinderLens.getBottom().sub(lightCenter).getNormal());

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Wrong input!");
            }
        });

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        Border infoBorder = BorderFactory.createTitledBorder("How to use the project");
        JEditorPane  textArea = new JEditorPane("text/html", "");
        textArea.setText(Application.getProperty("app.info"));
        textArea.setEditable(false);

        JPanel infoPanel = new JPanel();
        infoPanel.add(textArea);

        infoPanel.setBorder(infoBorder);
        add(infoPanel, c);

        setResizable(false);
    }

    public LensRayTracingDemo() {
        buildGui();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final LensRayTracingDemo frame = new LensRayTracingDemo();
            frame.setTitle("Lens raytracing demo v0.1");
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            final Animator animator = new Animator(physicalPanel);
            animator.start();
        });

    }
}
