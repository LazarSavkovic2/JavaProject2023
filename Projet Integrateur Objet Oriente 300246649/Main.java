

public class Main {
    public static void main(String[] args){
        PointCloud cloud = new PointCloud("PointCloud1.xyz"); // change file to get new result
        PlaneRANSAC ransac = new PlaneRANSAC(cloud);
        ransac.setEps(0.5);
        // gets three dominant planes for pointCloud1
        ransac.run(ransac.getNumberOfIterations(0.99, 0.90),"PointCloud1_p");

        PointCloud cloudTwo = new PointCloud("PointCloud2.xyz"); // change file to get new result
        PlaneRANSAC ransacTwo = new PlaneRANSAC(cloudTwo);
        // gets three dominant planes for pointCloud2
        ransacTwo.run(ransacTwo.getNumberOfIterations(0.99, 0.90),"PointCloud2_p");

        PointCloud cloudThree = new PointCloud("PointCloud3.xyz"); // change file to get new result
        PlaneRANSAC ransacThree = new PlaneRANSAC(cloudThree);
        // gets three dominant planes for pointCloud3
        ransacThree.run(ransacThree.getNumberOfIterations(0.99, 0.90),"PointCloud3_p");
        
        
    }
}
