import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
class Search {
	static void dijkstra(String source,String dest, ArrayList<Vertex> adjlist, HashMap<String, Integer> map,int v) {
		if(dest==source) {
			System.out.println("source on landmark");
			return;
		}
		double cost[] = new double[v];
		Edge parentRoad[] = new Edge[v];
		int s = map.get(source);
		//int d = map.get(dest);
		Boolean spt[] = new Boolean[v];
		for(int i=0;i<v;i++) {
			cost[i]= Double.MAX_VALUE;
			spt[i] = false;
			parentRoad[i]=null;
	
		}
		
		cost[s]=0;
		for(int i=0; i<v-1; i++) {
			double min = Double.MAX_VALUE;
			int index = -1;
			for(int j=0;j<v;j++) {
				if(cost[j]<= min && spt[j] == false ) {
					min = cost[j];
					index = j ;
				
				}
			}
			spt[index] = true;
			
			Vertex f = adjlist.get(index);
			
			for(int j=0 ; j< f.edges.size();j++) {
			    	Edge e = f.edges.get(j);
			    if(map.containsKey(e.to)) {
					
				int x = map.get(e.to);
			    
				if(!spt[x] && cost[index]!=Double.MAX_VALUE) {
				    
			if(cost[index] + e.getCost() <= cost[x]) {
				    
				 
				cost[x] = cost[index] + e.getCost();
				parentRoad[x] = e;
				}
				}
				}
			}
		}
		if(map.containsKey(dest)) {
		int g = map.get(dest);
		print(cost,g,parentRoad,map);
		
		}
		else
			System.out.println("no path exists");
		
		
	}
   private static void print(double[] cost, int dest, Edge[] parentRoad,HashMap <String , Integer> map) {
		if(cost[dest] == Double.MAX_VALUE) {
			System.out.println("no path exists");
		}
		else {
			if(parentRoad[dest]!= null) {
			Edge e =parentRoad[dest];
			if(map.containsKey(e.from)) {
			int p = map.get(e.from);	
			print(cost,p,parentRoad,map);
			System.out.println(e.toString());
			}
			}
			
		}
		
	}
    public static void main(String[] args){
		int t;
	
		Scanner scanner = new Scanner(System.in);
		t = scanner.nextInt();
		for(int i=0;i<t;i++) {
			int n,l;
			ArrayList<Edge> list = new ArrayList<>();
			HashMap<String,Landmark> map2= new HashMap<>();
			
		n= scanner.nextInt();
		l=scanner.nextInt();
		for(int j=0;j<n;j++) {
			String from =  scanner.next();
			String to = scanner.next();
			String type = scanner.next();
			String name = scanner.next();
			long length = scanner.nextLong();
			long speed = scanner.nextLong();
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
				long toll = scanner.nextLong();
				long lane = scanner.nextLong();
				long maintainance = scanner.nextLong();
				long occupancy = scanner.nextLong();
				m = new Motorway(name,length,speed,from,to,toll,lane,maintainance,occupancy,type);
				
				m2 = new Motorway(name,length,speed,to,from,toll,lane,maintainance,occupancy,type);
				break;
			case 1:
				long width= scanner.nextLong();
				long scenicValue= scanner.nextLong();
				long occupancy1= scanner.nextLong();
				m = new PedestrianRoad(name,length,speed,from,to,width,scenicValue,occupancy1,type);
				m2 = new PedestrianRoad(name,length,speed,to,from,width,scenicValue,occupancy1,type);
			
				break;
			case 2:
				long curvature = scanner.nextLong();
				m = new CyclistRoad(name,length,speed,from,to,curvature,type);
				m2 = new CyclistRoad(name,length,speed,to,from,curvature,type);
			
				break;
			case 3:
				long difficulty = scanner.nextLong();
				m = new Swamps(name,length,speed,from,to,difficulty,type);
				m2 = new Swamps(name,length,speed,to,from,difficulty,type);
			
				break;
			case 4:
				long width1 = scanner.nextLong();
				long tidalLevel = scanner.nextLong();
				long depth = scanner.nextLong();
				m = new Lakes(name,length,speed,from,to,width1,tidalLevel,depth,type);
				m2 = new Lakes(name,length,speed,to,from,width1,tidalLevel,depth,type);
			
				break;
				default: 
				    scanner.nextLine();
			}
			list.add(m);
			list.add(m2);
			
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
			map2.put(id, y );
 
		}
		int q = scanner.nextInt();
		for(int z = 0 ; z<q;z++) {
			String from = scanner.next();
			String dest = scanner.next();
			String Tname = scanner.next();
			double mts= scanner.nextDouble();
			String ttype = scanner.next();
			Traveler m = new Traveler(Tname,mts,ttype) ;
			ArrayList<Vertex> adjList = new ArrayList<Vertex>();
				HashMap<String,Integer> map = new HashMap<>();
			int v=0;
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
					if(map.containsKey(g.from)) {
					Vertex k =	adjList.get(map.get(g.from));
					k.edges.add(g);
					}
					else {
						 map.put(g.from,v);
						v++;
						adjList.add(new Vertex(g.from));
						Vertex k =	adjList.get(map.get(g.from));
						k.edges.add(g);
						
					}
				}
			}
			
			if(map2.containsKey(dest)) {
				Landmark w = map2.get(dest);
				dijkstra(from,w.from,adjList,map,v);
			}
			
		}
 
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
}
