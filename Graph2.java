import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
class Graph2 {
   public static void main(String[] args){
		int t;
		Scanner scanner = new Scanner(System.in);
		t = scanner.nextInt();
		for(int i=0;i<t;i++) {
			int n,l;
			ArrayList<Vertex> adjList = new ArrayList<Vertex>();
			
			HashMap<String,Integer> map = new HashMap<>();
			HashMap<String,Landmark> map2= new HashMap<>();
			int v=0;
		n= scanner.nextInt();
		l=scanner.nextInt();
		for(int j=0;j<n;j++) {
			String from =  scanner.next();
			String to = scanner.next();
			String type = scanner.next();
			String name = scanner.next();
			int length = scanner.nextInt();
			int speed = scanner.nextInt();
			int c;
			Edge m = null,m2=null;
			if(type.equalsIgnoreCase("motorway"))
				c=0;
			else if(type.equalsIgnoreCase("pedestrianRoad"))
				c=1;
			else if(type.equalsIgnoreCase("cyclistRoad"))
				c=2;
			else if(type.equalsIgnoreCase("swamps"))
				c=3;
			else
				c=4;
			switch (c) {
			case 0:
				int toll = scanner.nextInt();
				int lane = scanner.nextInt();
				int maintainance = scanner.nextInt();
				int occupancy = scanner.nextInt();
				m = new Motorway(name,length,speed,from,to,toll,lane,maintainance,occupancy);
				
				m2 = new Motorway(name,length,speed,to,from,toll,lane,maintainance,occupancy);
			//	System.out.println(m.toString());
				break;
			case 1:
				int width= scanner.nextInt();
				int scenicValue= scanner.nextInt();
				int occupancy1= scanner.nextInt();
				m = new PedestrianRoad(name,length,speed,from,to,width,scenicValue,occupancy1);
				m2 = new PedestrianRoad(name,length,speed,to,from,width,scenicValue,occupancy1);
			//	System.out.println(p.toString());
				break;
			case 2:
				int curvature = scanner.nextInt();
				m = new CyclistRoad(name,length,speed,from,to,curvature);
				m2 = new CyclistRoad(name,length,speed,to,from,curvature);
			//	System.out.println(cr.toString());
				break;
			case 3:
				int difficulty = scanner.nextInt();
				m = new Swamps(name,length,speed,from,to,difficulty);
				m2 = new Swamps(name,length,speed,to,from,difficulty);
			//	System.out.println(s.toString());
				break;
			case 4:
				int width1 = scanner.nextInt();
				int tidalLevel = scanner.nextInt();
				int depth = scanner.nextInt();
				m = new Lakes(name,length,speed,from,to,width1,tidalLevel,depth);
				m2 = new Lakes(name,length,speed,to,from,width1,tidalLevel,depth);
			//	System.out.println(l.toString());
				break;
			}
			if(map.containsKey(from)) {
				Vertex k =	adjList.get(map.get(from));
				k.edges.add(m);
				}
				else {
					 map.put(from,v);
					v++;
					adjList.add(new Vertex(from));
					Vertex k =	adjList.get(map.get(from));
					k.edges.add(m);
					
				}
			if(map.containsKey(to)) {
				Vertex k =	adjList.get(map.get(to));
				k.edges.add(m2);
				}
				else {
					 map.put(to,v);
					v++;
					adjList.add(new Vertex(to));
					Vertex k =	adjList.get(map.get(to));
					k.edges.add(m2);
					
				}
			
		}
		for(int j=0;j<l;j++) {
			String from = scanner.next();
			String to = scanner.next();
			String id= scanner.next();
			String name=scanner.next();
			String location=scanner.next();
			String type=scanner.next();
			int c;
			if(type.equalsIgnoreCase("trafficLight"))
				c=0;
			else if(type.equalsIgnoreCase("bench"))
				c=1;
			else if(type.equalsIgnoreCase("shop"))
				c=2;
			else 
				c=3;
			Landmark y = null;
			switch (c) {
			case 0:
				String avgTimeRed = scanner.next();
				y =  new TrafficLight(id,name,location,from,to,avgTimeRed);
				break;
			case 1:
				String carryingCapacity = scanner.next();
				String donatedBy = scanner.next();
				y =  new Bench(id,name,location,from,to,carryingCapacity,donatedBy);
				break;
			case 2:
				String openingTime =  scanner.next();
				String sType = scanner.next();
				String rating = scanner.next();
				String expenseLevel = scanner.next();
				y =  new Shop(id,name,location,from,to,openingTime,sType,rating,expenseLevel);
				break;
			case 3:
				String costPerUse = scanner.next();
				String cleanlinessLevel = scanner.next();
				y =  new Washroom(id,name,location,from,to,costPerUse,cleanlinessLevel);
				break;
			
			}
			map2.put(id,y );

		}
		int q = scanner.nextInt();
		
		for(int k=0;k<q;k++) {
			ArrayList<Landmark> lmarks = new ArrayList<Landmark>();
			String query = scanner.next();
			if(map2.containsKey(query)) {
				Landmark z = map2.get(query);
				System.out.println(z.toString());
				int u = map.get(z.from);
				adjList.get(u).find(z.to);
				for(HashMap.Entry<String, Landmark> entry : map2.entrySet()) {
					Landmark g = entry.getValue();
					if((g.from.equals(z.from))&&(g.to.equals(z.to))){
						lmarks.add(g);
					}
				}
			}
			Collections.sort(lmarks);
			for (int b=0;b<lmarks.size();b++) {
				 Landmark e = lmarks.get(b);
				 System.out.println(e.toString());
			 }
			
		}
//		Collections.sort(adjList);
//		for(int p=0;p<adjList.size();p++) {
//			Vertex x = adjList.get(p);
//			x.print();
	}

	}
}
class Landmark implements Comparable<Landmark>{
	@Override
	public String toString() {
		return "Landmark []";
	}
	String from;
	String to;
	String id;
	String name;
	String location;
	ArrayList<String> roads;
	Landmark(String id,String name,String location,String from,String to){
		this.id=id;
		this.name=name;
		this.location=location;
		this.from=from;
		this.to=to;
		roads = new ArrayList<String>();
	}
	@Override
	public int compareTo(Landmark o) {
		
		return this.id.compareTo(o.id);
	}
}
class TrafficLight extends Landmark{
	@Override
	public String toString() {
		return id + " "+ name +" "+ location + " " + avgTimeRed;
	}
	String avgTimeRed;
	TrafficLight(String id, String name, String location,String from,String to,String avgTimeRed) {
		super(id, name, location, from, to);
		this.avgTimeRed=avgTimeRed;
	}
	
}
class Bench extends Landmark{
	@Override
	public String toString() {
		return id + " "+ name +" "+ location + " " + carryingCapacity + " " + donatedBy;
	}
	String carryingCapacity;
	String donatedBy;
	Bench(String id, String name, String location,String from,String to,String carryingCapacity,String donatedBy) {
		super(id, name, location, from, to);
		this.carryingCapacity=carryingCapacity;
		this.donatedBy=donatedBy;
	}
	
}
class Shop extends Landmark{
	@Override
	public String toString() {
		return id + " "+ name +" "+ location + " " + openingTime + " "+ type + " " + rating + " " + expenseLevel;
	}

	String openingTime;
	String type;
	String rating;
	String expenseLevel;
	
	Shop(String id, String name, String location,String from,String to,String openingTime,String type,String rating,String expenseLevel) {
		super(id, name, location, from, to);
		this.openingTime=openingTime;
		this.type=type;
		this.rating=rating;
		this.expenseLevel=expenseLevel;
		
	}
	
}
class Washroom extends Landmark{
	@Override
	public String toString() {
		return id + " "+ name +" "+ location + " " + costPerUse + " " + cleanlinessLevel;
	}
	String costPerUse;
	String cleanlinessLevel;
	Washroom(String id, String name, String location,String from,String to, String costPerUse,String cleanlinessLevel) {
		super(id, name, location, from, to);
		this.costPerUse=costPerUse;
		this.cleanlinessLevel=cleanlinessLevel;
		
	}
	
}
class Edge implements Comparable<Edge>{
	String name;
	int length;
	int maximumSpeed;
	String from;
	String to;
	private double cost;
	
	Edge(String name, int length,int maximumSpeed,String from,String to ){
		this.name=name;
		this.length=length;
		this.from=from;
		this.to=to;
		this.maximumSpeed=maximumSpeed;
		cost=0;
	}


	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


	@Override
	public int compareTo(Edge o) {
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
	int tollprice;
	int lanes;
	int maintainance;
	int occupancy;
	Motorway(String name, int length, int maximumSpeed, String from, String to,int tollprice,int lanes,int maintainance,int occupancy) {
		super(name, length, maximumSpeed, from, to);
		this.tollprice=tollprice;
		this.lanes=lanes;
		this.maintainance=maintainance;
		this.occupancy=occupancy;
		double d = computeCost();
		setCost(d);
	}
	private double computeCost() {
		double a,b,c;
		a= MAX_OCC*lanes;
		b=occupancy/a;
		c= 1-b;
		double d = maximumSpeed*c;
		double ans = length/d;
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
//		System.out.println(d);
//		System.out.println(ans);
		return ans;
	}

}
class PedestrianRoad extends Edge{

	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + width + " " + scenicValue + " " + occupancy;
	}
	static final int MAX_OCC=1500;
	int width;
	int scenicValue;
	int occupancy;
	PedestrianRoad(String name, int length, int maximumSpeed, String from, String to,int width,int scenicValue,int occupancy) {
		super(name, length, maximumSpeed, from, to);
		this.width=width;
		this.scenicValue=scenicValue;
		this.occupancy=occupancy;
		double d = computeCost();
		setCost(d);
	}
	private double computeCost() {
		double a,b,c;
		a = (double)occupancy/MAX_OCC;
		b= 1-a;
		c= maximumSpeed*b;
		double ans = length/c;
		return ans;
	}
	
}
class CyclistRoad extends Edge{
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + curvature;
	}

	int curvature;
	
	CyclistRoad(String name, int length, int maximumSpeed, String from, String to,int curvature) {
		super(name, length, maximumSpeed, from, to);
		this.curvature=curvature;
		double d = computeCost();
		setCost(d);
		
	}

	private double computeCost() {
		double a,b;
		a =(double) maximumSpeed/curvature;
		b=length/a;
		return b;
	}
	
}
class Swamps extends Edge{
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + difficulty;
	}

	int difficulty;
	
	Swamps(String name, int length, int maximumSpeed, String from, String to,int difficulty) {
		super(name, length, maximumSpeed, from, to);
		this.difficulty=difficulty;
		double d = computeCost();
		setCost(d);
	}

	private double computeCost() {
		double a = difficulty*difficulty;
		double b = maximumSpeed/a;
		
		return length/b;
	}
}
class Lakes extends Edge{
	@Override
	public String toString() {
		return from + " "+ to +" "+ name + " "+ length + " "+ maximumSpeed + " " + width +" "+ tidalLevel + " " + depth;
	}
	int width;
	int tidalLevel;
	int depth;
	Lakes(String name, int length, int maximumSpeed, String from, String to,int width,int tidalLevel,int depth) {
		super(name, length, maximumSpeed, from, to);
		this.width = width;
		this.tidalLevel=tidalLevel;
		this.depth=depth;
		double d = computeCost();
		setCost(d);
	}
	private double computeCost() {
		
		return (double) length/maximumSpeed;
	}
	
}
class Vertex implements Comparable<Vertex>{
	String name;
	LinkedList<Edge> edges;
	Vertex(String name){
		this.name=name;
		edges = new LinkedList<Edge>();
	}
	 void print(){
		 Collections.sort(edges);
		 for (int i=0;i<edges.size();i++) {
			 Edge e = edges.get(i);
			 System.out.println(e.toString());
		 }
	 }
	@Override
	public int compareTo(Vertex o) {
		return this.name.compareTo(o.name);
	}
	public void find(String to) {
		
		 Collections.sort(edges);
		for(Edge w:edges) {
			if(w.to.equals(to)) {
				System.out.println(w.toString());
				
			}
		}
	}
}
//class Road{
//	String name;
//	String from;
//	String to;
//	LinkedList<Landmark> landmarks;
//	Road(String name,String from,String to){
//		this.name= name;
//		this.from=from;
//		this.to=to;
//		landmarks = new LinkedList<Landmark>();
//	}
//}
