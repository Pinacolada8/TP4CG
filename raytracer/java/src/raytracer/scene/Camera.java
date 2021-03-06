package raytracer.scene;

import raytracer.math.Vector3;

public class Camera {

    public Vector3 eye;
    public Vector3 target;
    public Vector3 up;
    private double fovy;

    // base ortonormal da câmera (sistema de coords.)
    public Vector3 cameraBaseX;
    public Vector3 cameraBaseY;
    public Vector3 cameraBaseZ;

    /**
     * 
     * MÉTODO calcula a base ortonormal da câmera considerando: 1. Vetor eye
     * (posição do olho) 2. Vetor target (pra onde está olhando) 3. Vetor up
     * (vetor "pra cima")
     */
    public void calculateBase() {
        Vector3 targetVector = target.diff(eye).normalized().mult(-1);

        cameraBaseZ = new Vector3(targetVector);
        cameraBaseX = new Vector3(up.cross(cameraBaseZ)).normalized();
        cameraBaseY = new Vector3(cameraBaseZ.cross(cameraBaseX));
    }
    
    public void setFovy(double inDegrees) {
        fovy = inDegrees * Math.PI / 180;
    }
    
    public double getFovy() {
        return fovy;
    }
}
