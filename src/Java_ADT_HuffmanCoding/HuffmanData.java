// Class to represent a Data in the Huffman binary tree

public class HuffmanData implements Comparable<HuffmanData>{

	private int frequency;
	private char symbol;
	
	public HuffmanData(int freq, char symb) {
		this.frequency = freq;
		this.symbol = symb;	
	}
	
	public HuffmanData(int freq){
		this.frequency = freq;
		this.symbol = '\u0000';
	}
	
	public int getFrequency(){
		return frequency;
	}

	public char getSymbol(){
		return symbol;
	}
	
	public int compareTo(HuffmanData entry){
		return frequency - entry.getFrequency();
	}
}
