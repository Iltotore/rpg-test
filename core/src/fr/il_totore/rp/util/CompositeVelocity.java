package fr.il_totore.rp.util;

import com.badlogic.gdx.math.*;

import java.util.HashMap;
import java.util.Map;

public class CompositeVelocity implements Vector<Vector3> {

    private Map<String, Vector3> velocities = new HashMap<>();

    public Vector3 getComponent(String key){
        return velocities.getOrDefault(key, Vector3.Zero.cpy());
    }

    public Vector3 getAndSetComponent(String key){
        if(velocities.containsKey(key)) return velocities.get(key);
        Vector3 vector = Vector3.Zero.cpy();
        velocities.put(key, vector);
        return vector;
    }

    public void setComponent(String key, Vector3 vector){
        velocities.put(key, vector);
    }

    public Vector3 getFinalVelocity(){
        Vector3 velocity = Vector3.Zero.cpy();
        velocities.values().forEach(velocity::add);
        return velocity;
    }

    public Vector3 set(float x, float y, float z) {
        return getFinalVelocity().set(x, y, z);
    }

    @Override
    public Vector3 set(Vector3 vector) {
        return getFinalVelocity().set(vector);
    }

    public Vector3 set(float[] values) {
        return getFinalVelocity().set(values);
    }

    public Vector3 set(Vector2 vector, float z) {
        return getFinalVelocity().set(vector, z);
    }

    public Vector3 setFromSpherical(float azimuthalAngle, float polarAngle) {
        return getFinalVelocity().setFromSpherical(azimuthalAngle, polarAngle);
    }

    @Override
    public Vector3 setToRandomDirection() {
        return getFinalVelocity().setToRandomDirection();
    }

    @Override
    public Vector3 cpy() {
        return getFinalVelocity().cpy();
    }

    @Override
    public Vector3 add(Vector3 vector) {
        return getFinalVelocity().add(vector);
    }

    public Vector3 add(float x, float y, float z) {
        return getFinalVelocity().add(x, y, z);
    }

    public Vector3 add(float values) {
        return getFinalVelocity().add(values);
    }

    @Override
    public Vector3 sub(Vector3 a_vec) {
        return getFinalVelocity().sub(a_vec);
    }

    public Vector3 sub(float x, float y, float z) {
        return getFinalVelocity().sub(x, y, z);
    }

    public Vector3 sub(float value) {
        return getFinalVelocity().sub(value);
    }

    @Override
    public Vector3 scl(float scalar) {
        return getFinalVelocity().scl(scalar);
    }

    @Override
    public Vector3 scl(Vector3 other) {
        return getFinalVelocity().scl(other);
    }

    public Vector3 scl(float vx, float vy, float vz) {
        return getFinalVelocity().scl(vx, vy, vz);
    }

    @Override
    public Vector3 mulAdd(Vector3 vec, float scalar) {
        return getFinalVelocity().mulAdd(vec, scalar);
    }

    @Override
    public Vector3 mulAdd(Vector3 vec, Vector3 mulVec) {
        return getFinalVelocity().mulAdd(vec, mulVec);
    }

    public static float len(float x, float y, float z) {
        return Vector3.len(x, y, z);
    }

    @Override
    public float len() {
        return getFinalVelocity().len();
    }

    public static float len2(float x, float y, float z) {
        return Vector3.len2(x, y, z);
    }

    @Override
    public float len2() {
        return getFinalVelocity().len2();
    }

    public boolean idt(Vector3 vector) {
        return getFinalVelocity().idt(vector);
    }

    public static float dst(float x1, float y1, float z1, float x2, float y2, float z2) {
        return Vector3.dst(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public float dst(Vector3 vector) {
        return getFinalVelocity().dst(vector);
    }

    public float dst(float x, float y, float z) {
        return getFinalVelocity().dst(x, y, z);
    }

    public static float dst2(float x1, float y1, float z1, float x2, float y2, float z2) {
        return Vector3.dst2(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public float dst2(Vector3 point) {
        return getFinalVelocity().dst2(point);
    }

    public float dst2(float x, float y, float z) {
        return getFinalVelocity().dst2(x, y, z);
    }

    @Override
    public Vector3 nor() {
        return getFinalVelocity().nor();
    }

    public static float dot(float x1, float y1, float z1, float x2, float y2, float z2) {
        return Vector3.dot(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public float dot(Vector3 vector) {
        return getFinalVelocity().dot(vector);
    }

    public float dot(float x, float y, float z) {
        return getFinalVelocity().dot(x, y, z);
    }

    public Vector3 crs(Vector3 vector) {
        return getFinalVelocity().crs(vector);
    }

    public Vector3 crs(float x, float y, float z) {
        return getFinalVelocity().crs(x, y, z);
    }

    public Vector3 mul4x3(float[] matrix) {
        return getFinalVelocity().mul4x3(matrix);
    }

    public Vector3 mul(Matrix4 matrix) {
        return getFinalVelocity().mul(matrix);
    }

    public Vector3 traMul(Matrix4 matrix) {
        return getFinalVelocity().traMul(matrix);
    }

    public Vector3 mul(Matrix3 matrix) {
        return getFinalVelocity().mul(matrix);
    }

    public Vector3 traMul(Matrix3 matrix) {
        return getFinalVelocity().traMul(matrix);
    }

    public Vector3 mul(Quaternion quat) {
        return getFinalVelocity().mul(quat);
    }

    public Vector3 prj(Matrix4 matrix) {
        return getFinalVelocity().prj(matrix);
    }

    public Vector3 rot(Matrix4 matrix) {
        return getFinalVelocity().rot(matrix);
    }

    public Vector3 unrotate(Matrix4 matrix) {
        return getFinalVelocity().unrotate(matrix);
    }

    public Vector3 untransform(Matrix4 matrix) {
        return getFinalVelocity().untransform(matrix);
    }

    public Vector3 rotate(float degrees, float axisX, float axisY, float axisZ) {
        return getFinalVelocity().rotate(degrees, axisX, axisY, axisZ);
    }

    public Vector3 rotateRad(float radians, float axisX, float axisY, float axisZ) {
        return getFinalVelocity().rotateRad(radians, axisX, axisY, axisZ);
    }

    public Vector3 rotate(Vector3 axis, float degrees) {
        return getFinalVelocity().rotate(axis, degrees);
    }

    public Vector3 rotateRad(Vector3 axis, float radians) {
        return getFinalVelocity().rotateRad(axis, radians);
    }

    @Override
    public boolean isUnit() {
        return getFinalVelocity().isUnit();
    }

    @Override
    public boolean isUnit(float margin) {
        return getFinalVelocity().isUnit(margin);
    }

    @Override
    public boolean isZero() {
        return getFinalVelocity().isZero();
    }

    @Override
    public boolean isZero(float margin) {
        return getFinalVelocity().isZero(margin);
    }

    @Override
    public boolean isOnLine(Vector3 other, float epsilon) {
        return getFinalVelocity().isOnLine(other, epsilon);
    }

    @Override
    public boolean isOnLine(Vector3 other) {
        return getFinalVelocity().isOnLine(other);
    }

    @Override
    public boolean isCollinear(Vector3 other, float epsilon) {
        return getFinalVelocity().isCollinear(other, epsilon);
    }

    @Override
    public boolean isCollinear(Vector3 other) {
        return getFinalVelocity().isCollinear(other);
    }

    @Override
    public boolean isCollinearOpposite(Vector3 other, float epsilon) {
        return getFinalVelocity().isCollinearOpposite(other, epsilon);
    }

    @Override
    public boolean isCollinearOpposite(Vector3 other) {
        return getFinalVelocity().isCollinearOpposite(other);
    }

    @Override
    public boolean isPerpendicular(Vector3 vector) {
        return getFinalVelocity().isPerpendicular(vector);
    }

    @Override
    public boolean isPerpendicular(Vector3 vector, float epsilon) {
        return getFinalVelocity().isPerpendicular(vector, epsilon);
    }

    @Override
    public boolean hasSameDirection(Vector3 vector) {
        return getFinalVelocity().hasSameDirection(vector);
    }

    @Override
    public boolean hasOppositeDirection(Vector3 vector) {
        return getFinalVelocity().hasOppositeDirection(vector);
    }

    @Override
    public Vector3 lerp(Vector3 target, float alpha) {
        return getFinalVelocity().lerp(target, alpha);
    }

    @Override
    public Vector3 interpolate(Vector3 target, float alpha, Interpolation interpolator) {
        return getFinalVelocity().interpolate(target, alpha, interpolator);
    }

    public Vector3 slerp(Vector3 target, float alpha) {
        return getFinalVelocity().slerp(target, alpha);
    }

    @Override
    public String toString() {
        return getFinalVelocity().toString();
    }

    public Vector3 fromString(String v) {
        return getFinalVelocity().fromString(v);
    }

    @Override
    public Vector3 limit(float limit) {
        return getFinalVelocity().limit(limit);
    }

    @Override
    public Vector3 limit2(float limit2) {
        return getFinalVelocity().limit2(limit2);
    }

    @Override
    public Vector3 setLength(float len) {
        return getFinalVelocity().setLength(len);
    }

    @Override
    public Vector3 setLength2(float len2) {
        return getFinalVelocity().setLength2(len2);
    }

    @Override
    public Vector3 clamp(float min, float max) {
        return getFinalVelocity().clamp(min, max);
    }

    @Override
    public int hashCode() {
        return getFinalVelocity().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getFinalVelocity().equals(obj);
    }

    @Override
    public boolean epsilonEquals(Vector3 other, float epsilon) {
        return getFinalVelocity().epsilonEquals(other, epsilon);
    }

    public boolean epsilonEquals(float x, float y, float z, float epsilon) {
        return getFinalVelocity().epsilonEquals(x, y, z, epsilon);
    }

    public boolean epsilonEquals(Vector3 other) {
        return getFinalVelocity().epsilonEquals(other);
    }

    public boolean epsilonEquals(float x, float y, float z) {
        return getFinalVelocity().epsilonEquals(x, y, z);
    }

    @Override
    public Vector3 setZero() {
        return getFinalVelocity().setZero();
    }
}
