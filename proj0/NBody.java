public class NBody{

	/* finds radius of universe from text*/
	public static double readRadius(String path){
		In loc = new In(path);
		int N = loc.readInt();
		double r = loc.readDouble();
		return r;
	}
	/* reads planets from file into a list of planet objects **/
	public static Planet[] readPlanets(String path){
		In loc = new In(path);
		int N = loc.readInt();
		Planet[] planets = new Planet[N];
		double r = loc.readDouble();
		for(int i=0;i<N;i+=1){
			double xpos = loc.readDouble();
			double ypos = loc.readDouble();
			double xvel = loc.readDouble();
			double yvel = loc.readDouble();
			double m = loc.readDouble();
			String im = loc.readString();
			Planet p = new Planet(xpos,ypos,xvel,yvel,m,im);
			planets[i] = p;
		}
		return planets;
	}

	public static void main(String[] args){
		/*In loc = new In("data/Planets.txt");
		int N = loc.readInt();*/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();
		for(int t = 0;t<T;t+=dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length;i+=1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < planets.length;i+=1){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i<planets.length;i+=1){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	} 
}