import java.util.Scanner;

public class NBody{

    public static double readRadius(String filename){
        In in = new In(filename);
        double tmp = in.readDouble(); // Skip the first integer,很关键的一步
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int numPlanets = in.readInt();
        Planet[] planets = new Planet[numPlanets];
        in.readDouble(); // Skip the radius
        for (int i = 0; i < numPlanets; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, mass, imgFileName);
        }
        return planets;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double T = scanner.nextDouble();
        double dt = scanner.nextDouble();
        String filename = scanner.next();
        Planet[] planets = readPlanets(filename);
        double Radius = readRadius(filename);
        //StdDraw.setCanvasSize(800, 800); // 设置窗口大小，可根据需要调整
        StdDraw.enableDoubleBuffering(); // 开启双缓冲，防止闪烁
        int time = 0;
        //Radius = ;
        //System.out.println("read radius: " + Radius);
        StdDraw.setScale(-Radius, Radius);

        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0;i < planets.length; i ++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0;i < planets.length;i ++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg",
                2 * Radius, 2 * Radius); // 设置背景图片和缩放比例
            for(Planet P : planets){
                P.draw();
            }
            time += dt;
            StdDraw.pause(10);
            StdDraw.show();
        }
        // 输出最终状态
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (Planet P : planets) {
            StdOut.printf("%.2e %.2e %.2e %.2e %.2e %s\n", 
                P.xxPos, P.yyPos, P.xxVel, P.yyVel, P.mass, P.imgFileName);
        }
    }
}
