package personRelations;

import java.util.HashSet;
import java.util.Iterator;

public class RelationshipGraph {
	private static HashSet<Person> relationshipSet = new HashSet<Person>();
	private static Person source;
	
	/**
	 * Checks if x and y are related in given hops
	 * 
	 * @param x starting point in relationship map
	 * @param y Person we want if related to x
	 * @param hops number of hops away from x
	 * @return Returns true if given y is in relationshipSet at given hops from x
	 */
	public static boolean areRelated(Person x, Person y, int hops){
		relationshipSet.clear();
		
		if(hops == 0){
			if(x.id == y.id){
				return true;
			}
			else{
				return false;
			}
		}else{
			source = x;
			addPersonAtNode(x, hops);
			Iterator<Person> it = relationshipSet.iterator();
			while(it.hasNext()){
				if(y.id == it.next().id){
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * Adds all the related persons at given hops to relationshipSet
	 * 
	 * @param node Person in relationship map at given hops
	 * @param hops current hop value
	 */
	private static void addPersonAtNode(Person node, int hops){		
		if(hops == 0){
			if(source.id != node.id){
				relationshipSet.add(node);
			}
		}
		else{
			hops = hops - 1;
			if(node.xParent != null){
				addPersonAtNode(node.xParent, hops);
			}
			if(node.yParent != null){
				addPersonAtNode(node.yParent, hops);
			}
			
			Person[] children = node.children;
			if(children != null){
				for(Person child: children){
					addPersonAtNode(child, hops);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Person grandmom = new Person(0);
		Person grandfather = new Person(1);
		Person mom = new Person(2);
		Person dad = new Person(3);
		Person son = new Person(4);
		Person daughter = new Person(5);
		
		mom.xParent = grandmom;
		mom.yParent = grandfather;
		Person[] grandmomChild = new Person[]{mom};
		grandmom.children = grandmomChild;
		grandfather.children = grandmomChild;
						
		Person[] children = new Person[]{son, daughter};		
		mom.children = children;
		dad.children = children;
		son.xParent = mom;
		son.yParent = dad;
		daughter.xParent = mom;
		daughter.yParent = dad;
				
		System.out.println("should be true: " + RelationshipGraph.areRelated(dad, daughter, 1));
		
		System.out.println("should be true: " + RelationshipGraph.areRelated(mom, dad, 2));
				
		System.out.println("should be true: " + RelationshipGraph.areRelated(grandmom, son, 2));
		
		System.out.println("should be true: " + RelationshipGraph.areRelated(dad, grandmom, 3));
		
		System.out.println("should be false: " + RelationshipGraph.areRelated(dad, grandmom, 2));
		
		System.out.println("should be true: " + RelationshipGraph.areRelated(mom, mom, 0));
		
		System.out.println("should be true: " + RelationshipGraph.areRelated(mom, grandmom, 1));
		
		System.out.println("should be false: " + RelationshipGraph.areRelated(mom, grandmom, 0));
		
		System.out.println("should be true: " + RelationshipGraph.areRelated(son, grandfather, 2));
		
		System.out.println("should be false: " + RelationshipGraph.areRelated(son, grandfather, 3));
		
	}
}
