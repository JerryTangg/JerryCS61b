public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
                this.xxPos = xP;
                this.yyPos = yP;
                this.xxVel = xV;
                this.yyVel = yV;
                this.mass = m;
                this.imgFileName = img;
            }
    
    public Planet() {
        this.xxPos = 0;
        this.yyPos = 0;
        this.xxVel = 0;
        this.yyVel = 0;
        this.mass = 0;
        this.imgFileName = "";
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double G = 6.67e-11;
        double f = G * this.mass * p.mass / (r * r);
        return f;
    }

    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double fx = f * (p.xxPos - this.xxPos) / r;
        return fx;
    }

    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double fy = f * (p.yyPos - this.yyPos) / r;
        return fy;
    }

    public double calcForceExertedByX(Planet[] AllPlanets) {
        double Netfx = 0;
        for (int i = 0; i < AllPlanets.length; i++) {
            if (AllPlanets[i].equals(this)){
                continue;
            }else{
                Netfx += this.calcForceExertedByX((AllPlanets[i]));
            }
        }
        return Netfx;
    }

    public double calcForceExertedByY(Planet[] AllPlanets) {
        double Netfy = 0;
        for (int i = 0; i < AllPlanets.length; i++) {
            if (AllPlanets[i].equals(this)){
                continue;
            }else{
                Netfy += this.calcForceExertedByY((AllPlanets[i]));
            }
        }
        return Netfy;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    
}