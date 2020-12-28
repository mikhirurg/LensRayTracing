package io.github.mikhirurg.jogldemo;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    public static final Vector3D ZERO = new Vector3D(0, 0, 0);

    public static final Vector3D ONE = new Vector3D(1, 1, 1);

    public static double scalarMul(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D of(double x, double y, double z) {
        return new Vector3D(x, y, z);
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(x + other.x, y + other.y, z + other.z);
    }

    public Vector3D sub(Vector3D other) {
        return new Vector3D(x - other.x, y - other.y, z - other.z);
    }

    public Vector3D mul(Vector3D other) {
        return new Vector3D(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }

    public Vector3D getNormal() {
        return mul(1.0 / Math.sqrt(x * x + y * y + z * z));
    }

    public Vector3D mul(double k) {
        return new Vector3D(x * k, y * k, z * k);
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
