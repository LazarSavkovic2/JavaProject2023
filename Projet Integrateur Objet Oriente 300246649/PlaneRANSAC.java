/*
 * Made by Lazar Savkovic 300246649
 * I used W3 Schools how to write to a file: https://www.w3schools.com/java/java_files_create.asp
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.lang.Math;
import java.util.ArrayList;

public class PlaneRANSAC {
    private PointCloud points = null;
    private double eps;
    public PlaneRANSAC(PointCloud pc){
        points = pc;
    }

    public void setEps(double eps){ //minimum distance to be considered inside the plane
        this.eps = eps;
    }

    public double getEps(){
        return eps;
    }

    public int getNumberOfIterations(double confidence,double percentageOfPointsOnPlane){//gets the required number of iterations
        Double numberOfIterations = new Double(Math.log(1-confidence))/(Math.log(1-Math.pow(percentageOfPointsOnPlane,3)));
        return (numberOfIterations).intValue();
    }

    public void run(int numberOfIterations,String filename){
        String originalFileName = filename;
        int[] bestScore = new int[3];
        int tempScore;
        ArrayList[] arrayOfArrayLists = new ArrayList[3];
        for(int a = 0; a<3; a++){
            arrayOfArrayLists[a] = new ArrayList<Point3D>();
        }
        Point3D temp;
        for(int b = 0; b<3; b++){
            bestScore[b] = 0;
            for(int i = 0; i<numberOfIterations; i++){
                Iterator<Point3D> it = points.iterator();//goes through all points
                ArrayList<Point3D> tempList = new ArrayList<Point3D>(); // a temporary list taht is always re initialized and only stores all the points that are in the plane
                Point3D pointOne = points.getPoint();
                Point3D pointTwo = points.getPoint();
                Point3D pointThree = points.getPoint();
                tempList.add(pointOne); 
                tempList.add(pointTwo);
                tempList.add(pointThree);
                Plane3D plane = new Plane3D(pointOne, pointTwo, pointThree);
                tempScore = 0;
                while(it.hasNext()){// while has points this makes sure all points are tested to see if they belong on the plane
                    temp = it.next();
                    if(temp != pointOne){
                        if(eps> plane.getDistance(temp)){
                            tempList.add(temp);
                            tempScore++;
                        }
                    }
                }
    
                if(bestScore[b]<tempScore){ //if the the amount of points on the plane exceeds the last recorded then it is stored here.
                    bestScore[b] = tempScore;
                    arrayOfArrayLists[b] = tempList;
                }
            }
            filename = originalFileName;
            filename = filename +(b+1)+".xyz";
            Iterator<Point3D> goThroughAllPointsInPlane = arrayOfArrayLists[b].iterator();
            Point3D point = null;
            try { // creates an empty file
                File myObj = new File(filename);
                myObj.createNewFile();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
              try { //feeds all the points into the file
                FileWriter myWriter = new FileWriter(filename);
                myWriter.write("X   Y   Z\n");
                /* First we must call next three times to input our cordinates that make up the plane */
                for(int c = 0; c<3; c++ ){
                    point = goThroughAllPointsInPlane.next();
                    myWriter.write(point.getX() + "       " + point.getY() + "       " + point.getZ() + "\n");
                }
                myWriter.write("The Three points above are the coordinates forming the plane\n");
                while(goThroughAllPointsInPlane.hasNext()){
                    point = goThroughAllPointsInPlane.next();
                    myWriter.write(point.getX() + "       " + point.getY() + "       " + point.getZ() + "\n");
                }
                myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }
        /*
         * File without points on plane
         */
        tempScore = 0;
        int index = 0;
        for(int c = 0; c<3; c++){ // checks to see the highest score and then takes the index from who had the bestscore
            if(tempScore<bestScore[c]){
                tempScore = bestScore[c];
                index = c;
            }
        }
        Iterator<Point3D> ite = points.iterator();
        boolean match = false;
        filename = originalFileName;
        filename = filename +0+".xyz";
        Point3D finalPoint = null;
        try { // creates an empty file
            File myObj = new File(filename);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        double x = 0;
        double y = 0;
        double z = 0;
        Iterator<Point3D> iteratePointsThree = arrayOfArrayLists[index].iterator();
        Point3D testPoint = null;
        try { //feeds all the points into the file
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("X   Y   Z\n");
            /* First we must call next three times to input our cordinates that make up the plane */
            /*
             * Checks to see if any points match those found on the most dominant plane, if they do they are not saved into the file
             */
            while(ite.hasNext()){
                finalPoint = ite.next();
                x = finalPoint.getX();
                y = finalPoint.getY();
                z = finalPoint.getZ();
                testPoint = null;
                while(iteratePointsThree.hasNext()){
                    testPoint = iteratePointsThree.next();
                    if(testPoint.getX() == x && testPoint.getY() == y && testPoint.getZ() == z){ // checks to see if points are the same
                        match = true;
                    }
                }
                if(!match){
                    myWriter.write(x + "       " + y + "       " + z + "\n");
                }
                iteratePointsThree = arrayOfArrayLists[index].iterator();//reinitializes iterator so all points of the dominant plane can be cycled through again
                match = false;
            }
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    } 

}
