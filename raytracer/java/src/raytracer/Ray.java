package raytracer;

import raytracer.math.Vector3;

public class Ray {

    // Ponto de origem do raio 
    // (atribuir usando "new": ray.p0 = new Vector3(...))
    public Vector3 P;
    // Vetor de direção do raio
    public Vector3 u;

    public Ray(Vector3 p0, Vector3 v) {
        this.P = p0;
        this.u = v;
    }
    
    public Ray() {

    }
}
