public class Planet{
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public Planet(double xP, double yP, double xV,
    double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
    }

    public Planet(Planet P) {//构造函数2，第二种构造方法
    this.xxPos = P.xxPos;
    this.yyPos = P.yyPos;
    this.xxVel = P.xxVel;
    this.yyVel = P.yyVel;
    this.mass = P.mass;
    this.imgFileName = "";
    }
    public double calcDistance(Planet P){//用于计算距离
        double r = (P.xxPos - this.xxPos)*(P.xxPos - 
        this.xxPos) + (P.yyPos - this.yyPos)*
        (P.yyPos - this.yyPos);//dx和dy的平方和
        r = Math.sqrt(r);
        return r;
    }
    public double calcForceExertedBy(Planet P){//计算引力
        double G = 6.67e-11;
        double r = this.calcDistance(P);
        double F = G * this.mass * P.mass / (r * r);
        return F;
    }
    public double calcForceExertedByX(Planet P){//计算x方向的引力
        double F = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double dx = P.xxPos - this.xxPos;
        return F * dx / r;
    }
    public double calcForceExertedByY(Planet P){//计算y方向的引力
        double F = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double dy = P.yyPos - this.yyPos;
        return F * dy / r;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){//计算x方向的合力
        double netForceX = 0;
        for (Planet P : allPlanets) {
            if (!this.equals(P)) {
                netForceX += this.calcForceExertedByX(P);
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){//计算y方向的合力
        double netForceY = 0;
        for (Planet P : allPlanets) {
            if (!this.equals(P)) {  
                netForceY += this.calcForceExertedByY(P);
            }
        }
        return netForceY;
    }
    public void update(double dt, double fx, double fy)
    {
        double ax = fx / this.mass; //计算加速度
        double ay = fy / this.mass;
        this.xxVel += ax * dt; //更新速度
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt; //更新位置
        this.yyPos += this.yyVel * dt;
    }
    public void draw() {//绘制行星
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}