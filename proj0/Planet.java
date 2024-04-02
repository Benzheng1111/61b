public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G=6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		return G*mass*p.mass/calcDistance(p)/calcDistance(p);
	}

	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
	}

	public boolean equals(Planet p){
		if(xxPos==p.xxPos&&yyPos==p.yyPos){
			return true;
		}
		else{
			return false;
		}
	}

	public double calcNetForceExertedByX(Planet[] allPlants){
		double f=0;
		int i;
		for(i=0;i<allPlants.length;i+=1){
			if(equals(allPlants[i])){
				continue;
			}
			else{
				f+=calcForceExertedByX(allPlants[i]);
			}
		}
		return f;
	}

	public double calcNetForceExertedByY(Planet[] allPlants){
		double f=0;
		int i;
		for(i=0;i<allPlants.length;i+=1){
			if(equals(allPlants[i])){
				continue;
			}
			else{
				f+=calcForceExertedByY(allPlants[i]);
			}
		}
		return f;
	}

	public void update(double dt, double fX, double fY){
		xxVel=xxVel+dt*fX/mass;
		yyVel=yyVel+dt*fY/mass;
		xxPos=xxPos+xxVel*dt;
		yyPos=yyPos+yyVel*dt;
	}
}