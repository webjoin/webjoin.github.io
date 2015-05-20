package com.viewt.container;

import java.util.List;
import java.util.Random;

public class ListFeatures {
	
	/**
	 * java ±à³ÌË¼Ïë  11.5 List
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random(47);
		System.out.println(rand.nextInt());
		List<Pet> pets = Pets.arrayList(7);
		System.out.println("1: "+pets);
		Hamster h = new Hamster();
		pets.add(h);			//automatically resizes 
		System.out.println("2: "+pets);
		System.out.println("3: "+pets.contains(h));
		pets.remove(h); //remove by object
		
		Pet p = pets.get(2);
		
		System.out.println("4: "+p+" "+pets.indexOf(h));
		
//		Pet 
		
	}
	
	
	
	

}
class Indivdual {
	public Indivdual(){}
	public Indivdual(String name){}
}
class Pet extends Indivdual {
	public Pet(String name){ super(name);}
	public Pet(){ super();}
} 
class Pets{
	public static List<Pet> arrayList(int size){
		return null ;
	}
}
class Hamster extends Pet{
	
}
