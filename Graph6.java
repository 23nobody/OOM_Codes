import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
class Graph6 {
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
		    try{
			String from =  scanner.next();
			String to = scanner.next();
			String type = scanner.next();
			String name = scanner.next();
			int length = scanner.nextInt();
			int speed = scanner.nextInt();
			if((name.length()>24)||(name.length()<4)||(from.length()>24)||(from.length()<4)||(to.length()>24)||(to.length()<4)||(length<0)||(length>150)||(speed<0)||(speed>150)) {
	            throw new Excep("invalid edge");		
				//System.out.println("invalid edge");
				//scanner.nextLine();
				//continue;
			}
		   
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
				try{
				    
				
			switch (c) {
			case 0:
				int toll = scanner.nextInt();
				int lane = scanner.nextInt();
				int maintainance = scanner.nextInt();
				int occupancy = scanner.nextInt();
				if(occupancy<0||(occupancy>(Motorway.MAX_OCC*lane))) {
				// 	System.out.println("invalid motorway edge");
				// 	continue;
				throw new Excep("invalid motorway edge");
				}
				m = new Motorway(name,length,speed,from,to,toll,lane,maintainance,occupancy);
				
				m2 = new Motorway(name,length,speed,to,from,toll,lane,maintainance,occupancy);
			//	System.out.println(m.toString());
				break;
			case 1:
				int width= scanner.nextInt();
				int scenicValue= scanner.nextInt();
				int occupancy1= scanner.nextInt();
				if(occupancy1<0||occupancy1>PedestrianRoad.MAX_OCC) {
				// 	System.out.println("invalid pedestrianRoad edge");
				// 	continue;
				throw new Excep("invalid pedestrianRoad edge");
				}
				m = new PedestrianRoad(name,length,speed,from,to,width,scenicValue,occupancy1);
				m2 = new PedestrianRoad(name,length,speed,to,from,width,scenicValue,occupancy1);
			//	System.out.println(p.toString());
				break;
			case 2:
				int curvature = scanner.nextInt();
				if(curvature<=0) {
				// 	System.out.println("invalid cyclistRoad edge");
				// 	continue;
				throw new Excep("invalid cyclistRoad edge");
				}
				m = new CyclistRoad(name,length,speed,from,to,curvature);
				m2 = new CyclistRoad(name,length,speed,to,from,curvature);
			//	System.out.println(cr.toString());
				break;
			case 3:
				int difficulty = scanner.nextInt();
				if(difficulty<=0||difficulty>5) {
				// 	System.out.println("invalid swamps edge");
				// 	continue;
				throw new Excep("invalid swamps edge");
				}
				m = new Swamps(name,length,speed,from,to,difficulty);
				m2 = new Swamps(name,length,speed,to,from,difficulty);
			//	System.out.println(s.toString());
				break;
			case 4:
				int width1 = scanner.nextInt();
				int tidalLevel = scanner.nextInt();
				int depth = scanner.nextInt();
				if(tidalLevel<=0||tidalLevel>15) {
				// 	System.out.println("invalid lakes edge");
				// 	continue;
				throw new Excep("invalid lakes edge");
				}
				m = new Lakes(name,length,speed,from,to,width1,tidalLevel,depth);
				m2 = new Lakes(name,length,speed,to,from,width1,tidalLevel,depth);
			//	System.out.println(l.toString());
				break;
			}
				}
				 catch(Excep ex){
		        System.out.println(ex.getMessage());
		       // scanner.nextLine();
		        continue;
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
		    catch(Excep ex){
		        System.out.println(ex.getMessage());
		        scanner.nextLine();
		        continue;
		    }
			
		}
		for(int j=0;j<l;j++) {
		    try{
			String from = scanner.next();
			String to = scanner.next();
			String id= scanner.next();
			String name=scanner.next();
			String location=scanner.next();
			String type=scanner.next();
			if((name.length()>24)||(name.length()<4)||id.length()<4||id.length()>14) {
				// System.out.println("invalid landmark");
				// scanner.nextLine();
				// continue;
				throw new Excep("invalid landmark");
			}
			if((from.length()>24)||(from.length()<4)||(to.length()>24)||(to.length()<4)) {
				// System.out.println("invalid road");
				// scanner.nextLine();
				// continue;
				throw new Excep("invalid road");
			}
			int c=0;
			if(type.equalsIgnoreCase("trafficLight"))
				c=0;
			else if(type.equalsIgnoreCase("bench"))
				c=1;
			else if(type.equalsIgnoreCase("shop"))
				c=2;
			else if(type.equalsIgnoreCase("washroom"))
				c=3;
			Landmark y = null;
			try{
			switch (c) {
			case 0:
				String avgTimeRed = scanner.next();
				if(Integer.valueOf(avgTimeRed)<=0||Integer.valueOf(avgTimeRed)>100) {
				// 	System.out.println("invalid trafficLight landmark");
				// 	continue;
				throw new Excep("invalid trafficLight landmark");
				}
				y =  new TrafficLight(id,name,location,from,to,avgTimeRed);
				break;
			case 1:
				String carryingCapacity = scanner.next();
				String donatedBy = scanner.next();
				if(Integer.valueOf(carryingCapacity)<=0||Integer.valueOf(carryingCapacity)>15) {
				// 	System.out.println("invalid bench landmark");
				// 	continue;
				throw new Excep("invalid bench landmark");
				}
				y =  new Bench(id,name,location,from,to,carryingCapacity,donatedBy);
				break;
			case 2:
				String openingTime =  scanner.next();
				String sType = scanner.next();
				String rating = scanner.next();
				String expenseLevel = scanner.next();
					if(!(sType.equalsIgnoreCase("food")||sType.equalsIgnoreCase("clothing")||sType.equalsIgnoreCase("store")||sType.equalsIgnoreCase("others"))) {
				// 	System.out.println("invalid shop landmark");
				// 	continue;
				throw new Excep("invalid shop landmark");
				}
				if(Integer.valueOf(rating)<0||Integer.valueOf(rating)>10||Integer.valueOf(expenseLevel)<0||Integer.valueOf(expenseLevel)>10) {
				// 	System.out.println("invalid shop landmark");
				// 	continue;
				throw new Excep("invalid shop landmark");
				}
				y =  new Shop(id,name,location,from,to,openingTime,sType,rating,expenseLevel);
				break;
			case 3:
				String costPerUse = scanner.next();
				String cleanlinessLevel = scanner.next();
				if(Integer.valueOf(cleanlinessLevel)<0||Integer.valueOf(cleanlinessLevel)>10) {
				// 	System.out.println("invalid washroom landmark");
				// 	continue;
				throw new Excep("invalid washroom landmark");
				}
				y =  new Washroom(id,name,location,from,to,costPerUse,cleanlinessLevel);
				break;
			
			}
			
			map2.put(id,y );
			} catch(Excep ex){
		        System.out.println(ex.getMessage());
		        //scanner.nextLine();
		        continue;
		    }
		    }
		    catch(Excep ex){
		        System.out.println(ex.getMessage());
		        scanner.nextLine();
		        continue;
		    }
		}
		int q = scanner.nextInt();
		
		for(int k=0;k<q;k++) {
			ArrayList<Landmark> lmarks = new ArrayList<Landmark>();
			try{
			String query = scanner.next();
			if(query.length()<4||query.length()>14){
			 //   System.out.println("invalid landmark");
			 //   continue;
			 throw new Excep("invalid landmark");
			}
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
			 catch(Excep ex){
		        System.out.println(ex.getMessage());
		       // scanner.nextLine();
		       continue;
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
class Excep extends Exception {
    Excep(String s){
        super(s);
    }
}
 
