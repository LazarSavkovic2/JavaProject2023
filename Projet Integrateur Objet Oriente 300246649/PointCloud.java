/*
 * Made by Lazar Savkovic 300246649
 * I used W3 Schools how to read a file: https://www.w3schools.com/java/java_files_read.asp
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

public class PointCloud {
    private ArrayList<Point3D> pointList = new ArrayList<Point3D>();
    private int pointListSize = 0;
    PointCloud(String filename){
        File pointCloud = new File(filename);
        try {
            Scanner myScanner = new Scanner(pointCloud);
            myScanner.nextLine();
           
            while(myScanner.hasNext()){
                String x = myScanner.next();
                String y = myScanner.next();
                String z = myScanner.next();
                Point3D a = new Point3D(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
                pointList.add(a);
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pointListSize = pointList.size();
    }

    PointCloud(){

    }

    public void addPoint(Point3D pt){

    } 

    public Point3D getPoint(){
        int random = (int)(Math.random()* pointListSize);
        return pointList.get(random);
    }

    public void save(String filename){

    }

    Iterator<Point3D> iterator(){
        return pointList.iterator();
    }
}
