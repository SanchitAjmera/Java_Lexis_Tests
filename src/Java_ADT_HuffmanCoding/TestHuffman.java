public class TestHuffman {

	public static void main(String args[]) {

		Huffman myHuffmanTest = new Huffman();
		// Create Huffman tree
		myHuffmanTest.setFrequencies();
		// Read in test data as an array of HuffmanData
		myHuffmanTest.setPriorityQueue();
		// Copy test data into PriorityQueue
		myHuffmanTest.createHuffmanTree();
		// Process PriorityQueue to create a HuffmanTree
		myHuffmanTest.printCode();
		//Print out the codes stored in the HuffmanTree

	}

}// end of Test class

