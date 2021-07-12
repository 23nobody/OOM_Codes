import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
class Graph3 {
   public static void main(String[] args){
		int t;
		Scanner scanner = new Scanner(System.in);
		t = scanner.nextInt();
		for(int i=0;i<t;i++) {
			int n;
			ArrayList<Edge> list = new ArrayList<>();
			int v=0;
		n= scanner.nextInt();
		for(int j=0;j<n;j++) {
			String from =  scanner.next();
			String to = scanner.next();
			String type = scanner.next();
			String name = scanner.next();
			long length = scanner.nextLong();
			long speed = scanner.nextLong();
			int c=9;
			Edge m = null,m2=null;
			if(type.equalsIgnoreCase("motorway"))
				c=0;
			else if(type.equalsIgnoreCase("pedestrianRoad"))
				c=1;
			else if(type.equalsIgnoreCase("cyclistRoad"))
				c=2;
			else if(type.equalsIgnoreCase("swamps"))
				c=3;
			else if(type.equalsIgnoreCase("lakes"))
				c=4;
			switch (c) {
			case 0:
				long toll = scanner.nextLong();
				long lane = scanner.nextLong();
				long maintainance = scanner.nextLong();
				long occupancy = scanner.nextLong();
				m = new Motorway(name,length,speed,from,to,toll,lane,maintainance,occupancy,type);
				
				m2 = new Motorway(name,length,speed,to,from,toll,lane,maintainance,occupancy,type);
			//	System.out.println(m.toString());
				break;
			case 1:
				long width= scanner.nextLong();
				long scenicValue= scanner.nextLong();
				long occupancy1= scanner.nextLong();
				m = new PedestrianRoad(name,length,speed,from,to,width,scenicValue,occupancy1,type);
				m2 = new PedestrianRoad(name,length,speed,to,from,width,scenicValue,occupancy1,type);
			//	System.out.println(p.toString());
				break;
			case 2:
				long curvature = scanner.nextLong();
				m = new CyclistRoad(name,length,speed,from,to,curvature,type);
				m2 = new CyclistRoad(name,length,speed,to,from,curvature,type);
			//	System.out.println(cr.toString());
				break;
			case 3:
				long difficulty = scanner.nextLong();
				m = new Swamps(name,length,speed,from,to,difficulty,type);
				m2 = new Swamps(name,length,speed,to,from,difficulty,type);
			//	System.out.println(s.toString());
				break;
			case 4:
				long width1 = scanner.nextLong();
				long tidalLevel = scanner.nextLong();
				long depth = scanner.nextLong();
				m = new Lakes(name,length,speed,from,to,width1,tidalLevel,depth,type);
				m2 = new Lakes(name,length,speed,to,from,width1,tidalLevel,depth,type);
			//	System.out.println(l.toString());
				break;
				default: 
				    scanner.nextLine();
			}
		//	m.setType(type);
		//	m2.setType(type);
			list.add(m);
			list.add(m2);
		}
		int q = scanner.nextInt();
		for(int z = 0 ; z<q;z++) {
			String Tname = scanner.next();
			double mts= scanner.nextDouble();
			String ttype = scanner.next();
			int h =0 ;
			Traveler m = new Traveler(Tname,mts,ttype) ;
			ArrayList<Edge> canTravel = new ArrayList<Edge>();
			if(ttype.equalsIgnoreCase("motorist")) {
				m = new Motorist(Tname,mts,ttype);
			}
				
			else if(ttype.equalsIgnoreCase("swimmer")) {
				m = new Swimmer(Tname,mts,ttype);
			}
			else if(ttype.equalsIgnoreCase("cyclist")) {
				m = new Cyclist(Tname,mts,ttype);
			}
			else if(ttype.equalsIgnoreCase("oldWalker")) {
				m = new OldWalker(Tname,mts,ttype);
			}
			else if(ttype.equalsIgnoreCase("newWalker")) {
				m = new NewWalker(Tname,mts,ttype);
			}
			
			for(int b = 0; b< list.size();b++) {
				Edge g = list.get(b);
				String s = g.type;
				int weight = m.getWeight(s);
				if(weight > 0) {
					double a,e,c;
					a = g.veff;
					e = g.length*weight;
					c = m.mts;
					double cost  = (double)e/(Math.min(a, c));
					g.setCost(cost);
				//	System.out.println(g.getCost());
					canTravel.add(g);
				}
			}
			Collections.sort(canTravel);
			for (int p=0;p<canTravel.size();p++) {
				Edge x = canTravel.get(p);
				System.out.println(x.toString());
			}
		}
//		Collections.sort(adjList);
//		for(int p=0;p<adjList.size();p++) {
//			Vertex x = adjList.get(p);
//			x.print();
//		}
 
	}
 
	}
}
class Traveler{
	double cost;
	int weight;
	double mts;
	String type;
	String name;
	Traveler(String name,double mts2, String type){
		this.name= name;
		this.weight = 0;
		this.cost = 0;
		this.mts = mts2;
		this.type=type;
	}
	int getWeight(String xtype) {
		return -3;
	}
}
class Motorist extends Traveler{
 
	Motorist(String name, double mts, String type) {
		super(name, mts, type);	
	}
	@Override
	int getWeight(String type) {
		if(type.equalsIgnoreCase("motorway")) {
			return 1;
		}
		else
			return -1;
	}
}
class Swimmer extends Traveler{
 
	Swimmer(String name, double mts, String type) {
		super(name, mts, type);
		// TODO Auto-generated constructor stub
	}
	@Override
	int getWeight(String type) {
		if(type.equalsIgnoreCase("motorway")) {
			return 3;
		}else if(type.equalsIgnoreCase("pedestrianRoad")) {
			return 2;
		}else if(type.equalsIgnoreCase("cyclistRoad")) {
			return 2;
		}else if(type.equalsIgnoreCase("swamps")) {
			return 3;
		}else if(type.equalsIgnoreCase("lakes")) {
			return 1;
		}
		else
			return -1;
	}
}
class Cyclist extends Traveler{
 
	Cyclist(String name, double mts, String type) {
		super(name, mts, type);
		// TODO Auto-generated constructor stub
	}
	@Override
	int getWeight(String type) {
		if(type.equalsIgnoreCase("motorway")) {
			return 2;
		}
		else if(type.equalsIgnoreCase("pedestrianRoad")) {
			return 3;
		}else if(type.equalsIgnoreCase("cyclistRoad")) {
			return 1;
		}
		else
			return -1;
	}
	
}
class OldWalker extends Traveler{
 
	OldWalker(String name, double mts, String type) {
		super(name, mts, type);
		// TODO Auto-generated constructor stub
	}
	@Override
	int getWeight(String type) {
		if(type.equalsIgnoreCase("motorway")) {
			return 5;
		}
		else if(type.equalsIgnoreCase("pedestrianRoad")) {
			return 1;
		}
		else
			return -1;
	}
	
}
class NewWalker extends Traveler{
 
	NewWalker(String name, double mts, String type) {
		super(name, mts, type);
		// TODO Auto-generated constructor stub
	}
	@Override
	int getWeight(String type) {
		if(type.equalsIgnoreCase("motorway")) {
			return 3;
		}
		else if(type.equalsIgnoreCase("pedestrianRoad")) {
			return 1;
		}else if(type.equalsIgnoreCase("cyclistRoad")) {
			return 2;
		}else if(type.equalsIgnoreCase("swamps")) {
			return 4;
		}
		else
			return -1;
	}
}
class Edge implements Comparable<Edge>{
	String name;
	long length;
	long maximumSpeed;
	double veff;
	String from;
	String to;
	String type; 
	private double cost;
	void  setType(String type) {
		this.type = type;
	}
	Edge(String name, long length2,long maximumSpeed,String from,String to, String type ){
		this.name=name;
		this.length=length2;
		this.from=from;
		this.to=to;
		this.maximumSpeed=maximumSpeed;
		this.type = type;
		cost=0;
		veff=0;
	}
 
 
	public double getCost() {
		return cost;
	}
 
	public void setCost(double cost) {
		this.cost = cost;
	}
 
 
	@Override
	public int compareTo(Edge o) {
		if(this.from.compareTo(o.from)!=0)
			return this.from.compareTo(o.from);
		
	if((Math.abs(this.getCost()-o.getCost()))<0.0001 ){
		    return (this.name.compareTo(o.name));
		}
		if(this.getCost()<o.getCost())
		return -1;
		else 
		return 1;
	}
	
	
}
class Motorway extends Edge{
	
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + tollprice + " " + lanes + " "+ maintainance + " " + occupancy;
	}
	static final int MAX_OCC=100;
	long tollprice;
	long lanes;
	long maintainance;
	long occupancy;
	Motorway(String name, long length, long maximumSpeed, String from, String to,long toll,long lane,long maintainance2,long occupancy2,String type) {
		super(name, length, maximumSpeed, from, to, type);
		this.tollprice=toll;
		this.lanes=lane;
		this.maintainance=maintainance2;
		this.occupancy=occupancy2;
		veff = setVeff();
	}
	private double setVeff() {
		double a = lanes*MAX_OCC;
		double b = (double)occupancy/a;
		double c = 1-b;
		double d = maximumSpeed*c;
		return d;
	}
	
 
}
class PedestrianRoad extends Edge{
 
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + width + " " + scenicValue + " " + occupancy;
	}
	static final int MAX_OCC=1500;
	long width;
	long scenicValue;
	long occupancy;
	PedestrianRoad(String name, long length, long maximumSpeed, String from, String to,long width2,long scenicValue2,long occupancy1,String type) {
		super(name, length, maximumSpeed, from, to, type);
		this.width=width2;
		this.scenicValue=scenicValue2;
		this.occupancy=occupancy1;
		veff = setVeff();
	}
	private double setVeff() {
		double a = (double)occupancy/MAX_OCC;
		double b = 1-a;
		double c = maximumSpeed*b;
		return c;
	}
	
	
}
class CyclistRoad extends Edge{
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + curvature;
	}
 
	long curvature;
	
	CyclistRoad(String name, long length, long maximumSpeed, String from, String to,long curvature2,String type) {
		super(name, length, maximumSpeed, from, to, type);
		this.curvature=curvature2;
		veff = setVeff();
		
	}
 
	private double setVeff() {
		double a = (double)maximumSpeed/curvature;
		return a;
	}
 
	
	
}
class Swamps extends Edge{
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + difficulty;
	}
 
	long difficulty;
	
	Swamps(String name, long length, long maximumSpeed, String from, String to,long difficulty2,String type) {
		super(name, length, maximumSpeed, from, to, type);
		this.difficulty=difficulty2;
		veff = setVeff();
	}
 
	private double setVeff() {
	double a = difficulty*difficulty;
	double b = (double)maximumSpeed/a;
	return b;
	}
 
	
}
class Lakes extends Edge{
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + width +" "+ tidalLevel + " " + depth;
	}
	long width;
	long tidalLevel;
	long depth;
	Lakes(String name, long length, long maximumSpeed, String from, String to,long width1,long tidalLevel2,long depth2,String type) {
		super(name, length, maximumSpeed, from, to, type);
		this.width = width1;
		this.tidalLevel=tidalLevel2;
		this.depth=depth2;
		veff = setVeff();
	}
	private double setVeff() {
	double a = (double) maximumSpeed;
		return a;
	}
	
	
}

 