import java.util.*;

public class Product {
	static HashMap<String,Integer> getProducts() {
		HashMap<String,Integer> products = new HashMap<String,Integer>();
		//usage: product name, amount in stock
		products.put("Apple\t",57);
		products.put("Can of soup",12);
		products.put("Steak\t",15);
		products.put("Oranges\t",98);
		products.put("Tofu\t",32);
		return products;
	}
	
	static HashMap<String, Integer> productsSold() {
		//TODO: more elegant implementation for adding sold values
		HashMap<String, Integer> soldProd = new HashMap<String, Integer>();
		//usage: product name, amount sold
		soldProd.put("Apple\t", 147);
		soldProd.put("Can of soup", 38);
		soldProd.put("Steak\t", 42);
		soldProd.put("Oranges\t", 198);
		soldProd.put("Tofu\t", 43);
		return soldProd; 
	}
	
	static String[] topSold(HashMap<String, Integer> soldProd) {
		String first = "", second = "", third = "";
		int max, mid, min;
		max=mid=min=-1;
		for(String i:soldProd.keySet()) {
			if(soldProd.get(i) > max) {
				min = mid;
				third = second;
				mid = max;
				second = first;
				max = soldProd.get(i);
				first = i;
			} else if(soldProd.get(i) > mid) {
				min = mid;
				third = second;
				mid = soldProd.get(i);
				second = i;
			} else if(soldProd.get(i) > min) {
				min = soldProd.get(i);
				third = i;
			}
		}
		String[] topThree = {first,second,third};
		return topThree;
	}
	//TODO: change this so we can actually change the featured products instead of
	//taking the first three. New method??
	static String[] featuredProducts(HashMap<String, Integer> products) {
		String[] featured = new String[3];
		int j=0;
		for(String i:products.keySet()) {
			if(j<3)
				featured[j++] = i; 
		}
		return featured;
	}
}
