package fr.il_totore.rp.util;

import com.badlogic.gdx.math.Vector3;

import java.util.function.Consumer;

public class Physics implements Consumer<CompositeVelocity> {

    private Vector3 gravity;
    private Vector3 inertness;

    public Physics(Vector3 gravity, Vector3 inertness){
        this.gravity = gravity;
        this.inertness = inertness;
    }

    @Override
    public void accept(CompositeVelocity velocity) {
        velocity.forEach(vector -> vector.scl(inertness));
        //TODO Gravity
    }

    public Vector3 getGravity() {
        return gravity;
    }

    public void setGravity(Vector3 inertness) {
        this.inertness.set(inertness);
    }

    public Vector3 getInertness() {
        return gravity;
    }

    public void setInertness(Vector3 inertness) {
        this.inertness.set(inertness);
    }

    public static Physics noMotion(){
        return new Physics(new Vector3(), new Vector3(1, 1, 1));
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private Vector3 gravity = new Vector3();
        private Vector3 maxGravity = new Vector3();
        private Vector3 inertness = new Vector3(1, 1, 1);

        private Builder(){}

        public Builder gravity(Vector3 gravity){
            this.gravity.set(gravity);
            return this;
        }

        public Builder gravityX(float x){
            gravity.x = x;
            return this;
        }

        public Builder gravityY(float y){
            gravity.y = y;
            return this;
        }

        public Builder gravityZ(float z){
            gravity.z = z;
            return this;
        }

        public Builder inertness(Vector3 inertness){
            this.inertness.set(inertness);
            return this;
        }

        public Builder inertness(float inertness){
            this.inertness.set(inertness, inertness, inertness);
            return this;
        }

        public Builder inertnessX(float x){
            inertness.x = x;
            return this;
        }

        public Builder inertnessY(float y){
            inertness.y = y;
            return this;
        }

        public Builder inertnessZ(float z){
            inertness.z = z;
            return this;
        }

        public Physics build(){
            return new Physics(gravity, inertness);
        }
    }
}
