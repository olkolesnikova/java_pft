package pft.sandbox;

public class Prosto{

   public static void main(String[] args){

       Point p1 = new Point();
       p1.x = 4;
       p1.y = 6;

       Point p2 = new Point();
       p2.x = 3;
       p2.y = 1;


       System.out.println("Длина отрезка равна " + distance(p1, p2));
   }

    public static double distance(Point p1, Point p2){
       return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));

    }



}