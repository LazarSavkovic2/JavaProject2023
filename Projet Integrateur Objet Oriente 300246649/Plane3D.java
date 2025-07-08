/*
 * Made by Lazar Savkovic 300246649
 * no other code was used from anywhere
 * However I did have to search up how to get the plane equation from: https://keisan.casio.com/exec/system/1223596129
 */

import java.lang.Math;

public class Plane3D {
    private double a;
    private double b;
    private double c;
    private double d;

    public Plane3D(Point3D p1, Point3D p2, Point3D p3){ // calculates variables in plane equation
        a = (p2.getY() - p1.getY())*(p3.getZ()-p1.getZ())-(p3.getY()-p1.getY())*(p2.getZ() - p1.getZ());//value of A
        b = (p2.getZ() - p1.getZ())*(p3.getX()-p1.getX())-(p3.getZ()-p1.getZ())*(p2.getX() - p1.getX());//value of B
        c = (p2.getX() - p1.getX())*(p3.getY()-p1.getY())-(p3.getX()-p1.getX())*(p2.getY() - p1.getY());//value of C
        d = -(a*p1.getX()+b*p1.getY()+c*p1.getZ());
    }

    public Plane3D(double a, double b, double c, double d){ // initialize based off provided
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getDistance(Point3D pt){ // gets distance using calculated plane variables
        return (Math.abs(a*pt.getX())+b*pt.getY()+c*pt.getZ()+d)/(Math.sqrt(Math.pow(a, 2))+Math.sqrt(Math.pow(b, 2))+Math.sqrt(Math.pow(c, 2)));
    }
    
}
