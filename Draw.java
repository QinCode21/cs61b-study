public class Draw {
    public static void draw(){
        StdDraw.setScale(-100, 100);

        StdDraw.picture(0,0,"images/starfield.jpg");
    }
    public static void main(String[] args) {
        draw();
        StdDraw.show();
    }
}
