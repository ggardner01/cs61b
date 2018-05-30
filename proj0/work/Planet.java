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

   /** public static void main(String[] args) {
       
    }*/
}