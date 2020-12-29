package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLCylinderLens extends GLObject {

    private double radius;
    private double height;
    private double d;
    private static final int n = 100;

    private static double[] genQuads(double radius, double height, double d) {
        double[] data = new double[(n + 1) * 4 * 3];

        double a = Math.acos((radius - d) / radius);
        double da = 2 * a / n;
        for (int i = 0; i < n * 4 * 3; i += 12) {
            double z = radius * Math.cos(a) - radius + d;
            double x = radius * Math.sin(a);
            double y = height / 2.0;
            data[i] = x;
            data[i + 1] = y;
            data[i + 2] = z;

            y = -height / 2.0;
            data[i + 3] = x;
            data[i + 4] = y;
            data[i + 5] = z;
            a = a - da;

            z = radius * Math.cos(a) - radius + d;
            x = radius * Math.sin(a);
            y = -height / 2.0;
            data[i + 6] = x;
            data[i + 7] = y;
            data[i + 8] = z;

            y = height / 2.0;
            data[i + 9] = x;
            data[i + 10] = y;
            data[i + 11] = z;
        }

        a = Math.acos((radius - d) / radius);

        double z = radius * Math.cos(a) - radius + d;
        double x = radius * Math.sin(a);
        double y = height / 2.0;
        data[n * 4 * 3] = x;
        data[n * 4 * 3 + 1] = y;
        data[n * 4 * 3 + 2] = z;

        y = -height / 2.0;
        data[n * 4 * 3 + 3] = x;
        data[n * 4 * 3 + 4] = y;
        data[n * 4 * 3 + 5] = z;

        a = -a;

        z = radius * Math.cos(a) - radius + d;
        x = radius * Math.sin(a);
        y = -height / 2.0;
        data[n * 4 * 3 + 6] = x;
        data[n * 4 * 3 + 7] = y;
        data[n * 4 * 3 + 8] = z;

        y = height / 2.0;
        data[n * 4 * 3 + 9] = x;
        data[n * 4 * 3 + 10] = y;
        data[n * 4 * 3 + 11] = z;

        return data;
    }

    private static double[] genNormals(double[] faceDots, double radius, double height, double d) {
        double[] data = new double[(n + 1) * 4 * 3];

        double a = Math.acos((radius - d) / radius);

        for (int i = 0; i < n * 4 * 3; i += 12) {
            double x1 = faceDots[i];
            double y1 = faceDots[i + 1];
            double z1 = faceDots[i + 2];

            double x2 = faceDots[i];
            double y2 = faceDots[i + 3];
            double z2 = faceDots[i + 4];

            double x3 = faceDots[i + 5];
            double y3 = faceDots[i + 6];
            double z3 = faceDots[i + 7];

            x1 -= x2;
            y1 -= y2;
            z1 -= z2;

            x3 -= x2;
            y3 -= y2;
            z3 -= z2;

            double nx = (y1 * z3 - y3 * z1);
            double ny = (x3 * z1 - x1 * z3);
            double nz = (x1 * y3 - x3 * y1);

            data[i] = nx;
            data[i + 1] = ny;
            data[i + 2] = nz;

            data[i + 3] = nx;
            data[i + 4] = ny;
            data[i + 5] = nz;

            data[i + 6] = nx;
            data[i + 7] = ny;
            data[i + 8] = nz;

            data[i + 9] = nx;
            data[i + 10] = ny;
            data[i + 11] = nz;
        }

        double x1 = faceDots[n * 4 * 3];
        double y1 = faceDots[n * 4 * 3 + 1];
        double z1 = faceDots[n * 4 * 3 + 2];

        double x2 = faceDots[n * 4 * 3 + 3];
        double y2 = faceDots[n * 4 * 3 + 4];
        double z2 = faceDots[n * 4 * 3 + 5];

        double x3 = faceDots[n * 4 * 3 + 6];
        double y3 = faceDots[n * 4 * 3 + 7];
        double z3 = faceDots[n * 4 * 3 + 8];

        x1 -= x2;
        y1 -= y2;
        z1 -= z2;

        x3 -= x2;
        y3 -= y2;
        z3 -= z2;

        double nx = (y1 * z3 - y3 * z1);
        double ny = (x3 * z1 - x1 * z3);
        double nz = (x1 * y3 - x3 * y1);

        data[n * 4 * 3] = nx;
        data[n * 4 * 3 + 1] = ny;
        data[n * 4 * 3 + 2] = nz;

        data[n * 4 * 3 + 3] = nx;
        data[n * 4 * 3 + 4] = ny;
        data[n * 4 * 3 + 5] = nz;

        data[n * 4 * 3 + 6] = nx;
        data[n * 4 * 3 + 7] = ny;
        data[n * 4 * 3 + 8] = nz;

        data[n * 4 * 3 + 9] = nx;
        data[n * 4 * 3 + 10] = ny;
        data[n * 4 * 3 + 11] = nz;

        return data;
    }


    private static double[] genLines(double radius, double height, double d) {
        double[] data = new double[n * 4 * 3];

        double a = Math.acos((radius - d) / radius);
        double da = 2 * a / n;
        for (int i = 0; i < n * 2 * 3; i += 6) {
            double z = radius * Math.cos(a) - radius + d;
            double x = radius * Math.sin(a);
            double y = height / 2.0;
            data[i] = x;
            data[i + 1] = y;
            data[i + 2] = z;

            a = a - da;
            z = radius * Math.cos(a) - radius + d;
            x = radius * Math.sin(a);
            data[i + 3] = x;
            data[i + 4] = y;
            data[i + 5] = z;
        }

        a = Math.acos((radius - d) / radius);
        System.out.println(a / Math.PI * 180.0);
        for (int i = n * 2 * 3; i < n * 4 * 3; i += 6) {
            double z = radius * Math.cos(a) - radius + d;
            double x = radius * Math.sin(a);
            double y = -height / 2.0;
            data[i] = x;
            data[i + 1] = y;
            data[i + 2] = z;

            a = a - da;
            z = radius * Math.cos(a) - radius + d;
            x = radius * Math.sin(a);
            data[i + 3] = x;
            data[i + 4] = y;
            data[i + 5] = z;
        }

        return data;
    }

    public void refresh() {
        setData(genQuads(radius, height, d));
        setVertices(genLines(radius, height, d));
        setNormals(genNormals(genQuads(radius, height, d), radius, height, d));
    }


    public GLCylinderLens(Vector3D shift, Vector3D scale, Color color, double radius, double height, double d) {
        super(genQuads(radius, height, d), genLines(radius, height, d), genNormals(genQuads(radius, height, d), radius, height, d), shift, scale, color, GL2.GL_QUADS);
        this.radius = radius;
        this.height = height;
        this.d = d;
    }

    public GLCylinderLens(double radius, double height, double d) {
        super(genQuads(radius, height, d), genLines(radius, height, d), genNormals(genQuads(radius, height, d), radius, height, d), GL2.GL_QUADS);
        this.radius = radius;
        this.height = height;
        this.d = d;
    }

    @Override
    public Cortege<Double, Vector3D, Vector3D> intersect(Ray ray, boolean near) {
        Vector3D intersectPoint;
        Vector3D normalVector;

        Vector3D point = ray.getPoint();
        Vector3D vector = ray.getVector();


        double A = (vector.getX() * vector.getX() + vector.getZ() * vector.getZ());
        double B = 2 * vector.getX() * point.getX() + 2 * vector.getZ() * (point.getZ() + radius - d);
        double C = point.getX() * point.getX() + Math.pow(point.getZ() + radius - d, 2) - radius * radius;

        double D = B * B - 4 * A * C;

        if (D < 0) {
            double t = 1000;
            return Cortege.of(t, null, null);
        }

        double t = (-B + Math.sqrt(D)) / (2 * A);

        if (near) {
            t = Math.min(t, (-B - Math.sqrt(D)) / (2 * A));

            intersectPoint = Vector3D.of(
                    vector.getX() * t + point.getX(),
                    vector.getY() * t + point.getY(),
                    vector.getZ() * t + point.getZ()
            );

            if (intersectPoint.getY() > height / 2.0 || intersectPoint.getY() < -height / 2.0 ||
                    intersectPoint.getZ() < 0) {
                t = 1000;
                return Cortege.of(t, null, null);
            }
            double N = Math.sqrt((4 * vector.getX() * vector.getX()) + (4 * (vector.getZ() - d + radius) * (vector.getZ() - d + radius)));

            normalVector = Vector3D.of(
                    2 * intersectPoint.getX() / N,
                    0,
                    2 * (intersectPoint.getZ() - d + radius) / N

            ).getNormal();
        } else {
            normalVector = Vector3D.of(0, 0, -1).getNormal();
            t = -point.getZ() / vector.getZ();
        }

        intersectPoint = Vector3D.of(
                vector.getX() * t + point.getX(),
                vector.getY() * t + point.getY(),
                vector.getZ() * t + point.getZ()
        );

        return Cortege.of(t, intersectPoint, normalVector);
    }

    @Override
    public void draw(GL2 gl2, GLUT glut) {
        super.draw(gl2, glut);
    }

    public Vector3D getTop() {
        return Vector3D.of(0, height / 2 - d / 8, d);
    }

    public Vector3D getRight() {
        double a = Math.acos((radius - d) / radius) - 0.01;
        double x = radius * Math.sin(a);
        double z = radius * Math.cos(a) - radius + d;

        return Vector3D.of(x, 0, z);
    }

    public Vector3D getLeft() {
        double a = -Math.acos((radius - d) / radius) + 0.01;
        double x = radius * Math.sin(a);
        double z = radius * Math.cos(a) - radius + d;

        return Vector3D.of(x, 0, z);
    }

    public Vector3D getBottom() {
        return Vector3D.of(0, -height / 2 + d / 8, d);
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

    public double getD() {
        return d;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setD(double d) {
        this.d = d;
    }
}
