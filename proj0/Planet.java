import java.lang.Math;
public class Planet {
	public double xxPos;
	public double yyPos;
	public double yyVel;
	public double xxVel;
	public double mass;
	public String imgFileName;
	/** creates planet object from inputs*/
	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	/** creates new planet instance copying old one*/
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	//** finds distance between a planet object and a given planet "p"
	public double calcDistance(Planet p){
		double dx=-(xxPos-p.xxPos);
		double dy=-(yyPos-p.yyPos);
		return Math.sqrt(dx*dx+dy*dy);
	}
	/** magnitude force between two bodies as double*/
	public double calcForceExertedBy(Planet p){
		double G = 6.67e-11;
		double r = this.calcDistance(p);
		return G*mass*p.mass/(r*r);
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos-this.xxPos;
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		return F*dx/r;
	}

	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos-this.yyPos;
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		return F*dy/r;
	}

	public double calcNetForceExertedByX(Planet[] p){
		double Fx = 0;
		for(int i=0, len=p.length; i<len;i+=1){
			if(this!=p[i]){
				Fx=Fx+this.calcForceExertedByX(p[i]);
			}
		}
		return Fx;
	}

	public double calcNetForceExertedByY(Planet[] p){
		double Fy = 0;
		for(int i=0, len =p.length; i<len;i+=1){
			if(this!=p[i]){
				Fy=Fy+this.calcForceExertedByY(p[i]);
			}
		}
		return Fy;
	}
	public void update(double dt, double fX, double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + dt*ax;
		this.yyVel = this.yyVel + dt*ay;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos=this.yyPos + this.yyVel*dt;
	}

	/* prints the planet using stddraw */
	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}

   /** public static void main(String[] args) {
       
    }*/
}