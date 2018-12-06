package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Constants;
import raytracer.math.Vector3;

/**
 * Uma esfera, que tem uma posição (herdada de Object) e um raio.
 *
 */
public class Sphere extends Object {

    public Vector3 center;
    public double radius;

    /**
     * *
     * Retorna um objeto do tipo RayResponse com informações sobre uma possível
     * interseção do raio com este objeto (veja o arquivo ray.h com a declaração
     * da struct RayResponse).
     *
     * O objeto response deve preencher os seguintes valores: -
     * response.intersected, true/false indicando se houve interseção ou não -
     * response.intersectionT, o valor T do raio (semi-reta) da primeira
     * interseção, caso haja mais que 1 interseção - response.intersectionPoint,
     * contendo o ponto (x,y,z) de interseção - ray.intersectionNormal, contendo
     * o vetor normal do objeto no ponto de interseção. A normal deve ser
     * *normalizada* (norma = 1)
     *
     *
     * @param ray
     * @return
     */
    @Override
    public RayResponse intersectsWith(Ray ray) {
        Vector3 u = ray.u.normalized();
        Vector3 p = center.diff(ray.P);
        double a = 1;
        double b = (-2) * u.dotProduct(p);
        double c = p.dotProduct(p) - Math.pow(radius, 2);
        double delta = Math.pow(b, 2) - 4 * a * c;
        double root1, root2;
        boolean hitFromInside = false;

        RayResponse response = new RayResponse();

        if (delta < -Constants.TINY) {
            return response;
        } else if (delta > Constants.TINY) {
            // duas interseções
            root1 = (-b - Math.sqrt(delta)) / 2 * a;
            root2 = (-b + Math.sqrt(delta)) / 2 * a;
            if (root1 > Constants.TINY) {
                response.intersected = true;
                response.intersectionT = root1;
            } else if (root2 > Constants.TINY) {
                response.intersected = true;
                response.intersectionT = root2;
                hitFromInside = true;
            } else {
                return response;
            }
        } else {
            // uma interseção
            root1 = (-b + Math.sqrt(delta)) / 2 * a;
            if (root1 > Constants.TINY) {
                response.intersected = true;
                response.intersectionT = root1;
            } else {
                return response;
            }
        }

        response.intersectionPoint = new Vector3(u.mult(response.intersectionT).add(ray.P));
        response.intersectionNormal = response.intersectionPoint.diff(center).normalized();
        if (hitFromInside) {
            response.intersectionNormal = new Vector3((response.intersectionNormal.mult(-1)));
        }
        return response;
    }

    @Override
    public Vector3 getCenter() {
        return center;
    }

    @Override
    public String getGeometryName() {
        return "sphere";
    }
}
