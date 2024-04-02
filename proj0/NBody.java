public class NBody{
	public static double readRadius(String data){
		In in =new In(data);
		int N=in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String data){
		In in =new In(data);
		int N=in.readInt();
		double r=in.readDouble();
		int i;
		Planet []p=new Planet[N];
		for(i=0;i<N;i+=1){
			p[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return p;
	}

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
		Planet []p=readPlanets(filename);

		StdDraw.setScale(-radius,radius);
		StdDraw.clear();

		int i;

		StdDraw.enableDoubleBuffering();

		double t;
		double[] xForces=new double[p.length];
		double[] yForces=new double[p.length];
		for(t=0;t<T;t+=dt){
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(i=0;i<p.length;i+=1){
				xForces[i]=p[i].calcNetForceExertedByX(p);
				yForces[i]=p[i].calcNetForceExertedByY(p);
			}
			for(i=0;i<p.length;i+=1){
				p[i].update(dt,xForces[i],yForces[i]);
			}
			for(i=0;i<p.length;i+=1){
				StdDraw.picture(p[i].xxPos,p[i].yyPos,"images/"+p[i].imgFileName);
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", radius);
		for (i = 0; i < p.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            p[i].xxPos, p[i].yyPos, p[i].xxVel,
            p[i].yyVel, p[i].mass, p[i].imgFileName);   
		}
	}
}