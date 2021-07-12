import java.util.ArrayList;
import java.util.Scanner;
class LabTest {
 
	public static void main(String[] args) {
	
		int t;
		Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        for(int i=0;i<t;i++) {
        	int m,n,num;
        	m= scanner.nextInt();
        	n= scanner.nextInt();
        	num = scanner.nextInt();
        //	ArrayList<Row> room = new ArrayList<Row>();
        	Row room[] = new Row[m];	
        	for(int j=0;j< m ; j++) {
        		room[j] = new Row(n);
        	}
        	for(int j=0;j<num;j++) {
        		String roll = scanner.next();
        		String name = scanner.next();
        		String gender = scanner.next();
        		String hostel = scanner.next();
        		int intelligence = scanner.nextInt();
        		String type = scanner.next();
        		int mostIntelligent =	Integer.MIN_VALUE;
        		int miIndex = 0;
        		int c;
                Student s = null;
                int x=0,y=0;
                if(type.equalsIgnoreCase("Greedy"))
                    c=0;
                else if(type.equalsIgnoreCase("GreedyGenderConscious"))
                    c=1;
                else if(type.equalsIgnoreCase("GreedyOppositeGenderConscious"))
                    c=2;
                else if(type.equalsIgnoreCase("MixedStrategy"))
                    c=3;
                else if(type.equalsIgnoreCase("GreedyHostelMate"))
                    c=4;
                else 
                	c = 5;
//                for(int k=0;room.get(k).students.size() == n && k<m;k++) {
//                	x++;
//                }
               
//                for(int l = 0 ; l <room.get(x).students.size()&& l<n; l++) {
//         		   if(room.get(x).students.get(l).intelligence >= mostIntelligent) {
//         			   mostIntelligent = room.get(x).students.get(l).intelligence;
//         			   miIndex = l;
//         		   }
//         	   }
               // System.out.println(x);
                Boolean added = false;
                switch (c) {
                case 0:
                   s = new Student(roll, name, gender, hostel, intelligence, type);
                   for(int l = 0 ; l < m; l++  ) {
                   	int p=room[l].students.size();
                   	if(p>0) {
                   	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n) {
                   		mostIntelligent = room[l].students.get(p-1).intelligence;
             			   miIndex = l;
                   	}
                   	}
                   	else {
                   		if(mostIntelligent < 0) {
                   			mostIntelligent = 0;
                   			miIndex = l;
                   		}
                   	}
                   	}
                   room[miIndex].students.add(s);
                  // System.out.println("abcd");
                    break;
                case 1:
                	 s = new Student(roll, name, gender, hostel, intelligence, type);
                	// Boolean added = false;
                	 for(int l = 0 ; l < m; l++  ) {
                       	int p=room[l].students.size();
                       	if(p>0) {
                       	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n && room[l].students.get(p-1).gender.equalsIgnoreCase(gender) ) {
                       		mostIntelligent = room[l].students.get(p-1).intelligence;
                 			   miIndex = l;
                       	}
                       	}
                       
                       	}
                       if(mostIntelligent != Integer.MIN_VALUE) {
                     	  room[miIndex].students.add(s);
                     	  added = true;
                       }
                 if(added)
                 	break;
                 	 for(int l = 0 ; l < m; l++  ) {
                      	int p=room[l].students.size();
                      	if(p>0) {
                      	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n) {
                      		mostIntelligent = room[l].students.get(p-1).intelligence;
                			   miIndex = l;
                      	}
                      	}
                      	else {
                      		if(mostIntelligent < 0) {
                      			mostIntelligent = 0;
                      			miIndex = l;
                      		}
                      	}
                 	 }
                 	  room[miIndex].students.add(s);
                    break;
                case 2:
                	 s = new Student(roll, name, gender, hostel, intelligence, type);
                //	 Boolean added = false;
                	 for(int l = 0 ; l < m; l++  ) {
                       	int p=room[l].students.size();
                       	if(p>0) {
                       	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n && room[l].students.get(p-1).gender.equalsIgnoreCase(gender) && !room[l].students.get(p-1).type.equalsIgnoreCase("GreedyGenderConscious") ) {
                       		mostIntelligent = room[l].students.get(p-1).intelligence;
                 			   miIndex = l;
                       	}
                       	}
                       
                       	}
                       if(mostIntelligent != Integer.MIN_VALUE) {
                     	  room[miIndex].students.add(s);
                     	  added = true;
                       }
                 if(added)
                 	break;
                 	 for(int l = 0 ; l < m; l++  ) {
                      	int p=room[l].students.size();
                      	if(p>0) {
                      	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n) {
                      		mostIntelligent = room[l].students.get(p-1).intelligence;
                			   miIndex = l;
                      	}
                      	}
                      	else {
                      		if(mostIntelligent < 0) {
                      			mostIntelligent = 0;
                      			miIndex = l;
                      		}
                      	}
                 	 }
                 	  room[miIndex].students.add(s);
                    break;
                case 3:
                	 s = new Student(roll, name, gender, hostel, intelligence, type);
                	 for(int l = 0 ; l < m; l++  ) {
                        	int p=room[l].students.size();
                        	if(p>0) {
                        		int w1=scanner.nextInt(),w2=scanner.nextInt(),w3=scanner.nextInt();
                        		int h=0, g=0;
                        		if( room[l].students.get(p-1).hostel.equalsIgnoreCase(hostel)) {
                        			h=1;
                        		}
                        		if( room[l].students.get(p-1).gender.equalsIgnoreCase(gender)) {
                        			g=1;
                        		}
                        		
                        	if(w1*(room[l].students.get(p-1).intelligence)+w2*h + w3*g > mostIntelligent && p!=n) {
                        		
                        		mostIntelligent =w1*(room[l].students.get(p-1).intelligence)+w2*h + w3*g;
                  			   miIndex = l;
                        	}
                        	}
                        	else {
                        		if(mostIntelligent < 0) {
                        			mostIntelligent = 0;
                        			miIndex = l;
                        		}
                        	}
                        	}
                        room[miIndex].students.add(s);
                    break;
                case 4:
                	 s = new Student(roll, name, gender, hostel, intelligence, type);
                	 for(int l = 0 ; l < m; l++  ) {
                       	int p=room[l].students.size();
                       	if(p>0) {
                       	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n && room[l].students.get(p-1).hostel.equalsIgnoreCase(hostel) ) {
                       		mostIntelligent = room[l].students.get(p-1).intelligence;
                 			   miIndex = l;
                       	}
                       	}
                       
                       	}
                       if(mostIntelligent != Integer.MIN_VALUE) {
                     	  room[miIndex].students.add(s);
                     	  added = true;
                       }
                 if(added)
                 	break;
                 	 for(int l = 0 ; l < m; l++  ) {
                      	int p=room[l].students.size();
                      	if(p>0) {
                      	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n) {
                      		mostIntelligent = room[l].students.get(p-1).intelligence;
                			   miIndex = l;
                      	}
                      	}
                      	else {
                      		if(mostIntelligent < 0) {
                      			mostIntelligent = 0;
                      			miIndex = l;
                      		}
                      	}
                      	}
                 	  room[miIndex].students.add(s);
                    break;
                case 5:
                	 s = new Student(roll, name, gender, hostel, intelligence, type);
                	 int f = scanner.nextInt();
                	 ArrayList<String> friends = new ArrayList<String>();
                	 
                	 for(int l=0;l<f;l++) {
                	friends.add(scanner.next());	
                	}
                 	// Boolean added = false;
                 	 for(int l = 0 ; l < m; l++  ) {
                        	int p=room[l].students.size();
                        	if(p>0) {
                        	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n && friends.contains(room[l].students.get(p-1).roll) ) {
                        		mostIntelligent = room[l].students.get(p-1).intelligence;
                  			   miIndex = l;
                        	}
                        	}
                        
                        	}
                        if(mostIntelligent != Integer.MIN_VALUE) {
                      	  room[miIndex].students.add(s);
                      	  added = true;
                        }
                  if(added)
                  	break;
                  	 for(int l = 0 ; l < m; l++  ) {
                       	int p=room[l].students.size();
                       	if(p>0) {
                       	if(room[l].students.get(p-1).intelligence > mostIntelligent && p!=n) {
                       		mostIntelligent = room[l].students.get(p-1).intelligence;
                 			   miIndex = l;
                       	}
                       	}
                       	else {
                       		if(mostIntelligent < 0) {
                       			mostIntelligent = 0;
                       			miIndex = l;
                       		}
                       	}
                  	 }
                  	  room[miIndex].students.add(s);
                	 break;
                }
        	}
        	for(int j=0; j< m ;j++) {
        		for(int k=0;k< room[j].students.size();k++) {
        			Student r = room[j].students.get(k);
        		System.out.println(r.toString());
        		}        	}
        	}
       
        }
 
	}
class Row{
	int n;
	Row(int n){
		this.n = n;
		students   = new ArrayList<Student>();
	}
	ArrayList<Student> students ; 
}
class Student{
	@Override
	public String toString() {
		return  roll + " " + name + " " + gender + " " + hostel + " " + intelligence;
	}
	String name;
	String roll;
	String gender;
	String type;
	int intelligence;
	String hostel;
	Student(String roll,String name, String gender, String hostel, int intelligence, String type) {
		this.gender = gender;
		this.hostel = hostel;
		this.intelligence = intelligence;
		this.roll = roll;
		this.name = name;
		this.type = type;
		
	}
	
}
 
