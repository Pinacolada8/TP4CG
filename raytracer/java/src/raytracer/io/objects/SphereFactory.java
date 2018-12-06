package raytracer.io.objects;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.objects.Object;
import raytracer.scene.objects.Sphere;

/**
 *
 */
public class SphereFactory implements ObjectFactory {

    @Override
    public Object setupObject(Scanner scanner) {
        Sphere sphere = new Sphere();
        sphere.center = new Vector3(
                scanner.nextDouble(),
                scanner.nextDouble(),
                scanner.nextDouble()
        );
        sphere.radius = scanner.nextDouble();
        return sphere;
    }

}
