package personRelations;

/**
 * Represents a person
 * 
 *
 */
public class Person {

	int id;
	Person xParent;
	Person yParent;
	Person[] children;
	
	public Person getxParent() {
		return xParent;
	}

	public void setxParent(Person xParent) {
		this.xParent = xParent;
	}

	public Person getyParent() {
		return yParent;
	}

	public void setyParent(Person yParent) {
		this.yParent = yParent;
	}

	public Person(int id){
		this.id = id;
	}

}