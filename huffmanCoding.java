package textEndcoderDecoder;

import java.util.*;

public class huffmanCoding {
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    private class Node implements Comparable<Node> { // implements Comparable allows to use comapareTo interface which
                                                     // provides the facility to comapre objects with some specific user
                                                     // created login
        char data;
        int cost; // freq.
        Node left;
        Node right;

        public Node(char data, int cost) {
            this.data = data;
            this.cost = cost;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost; // -ve means smaller
            // obj1.compareTo(obj2); // +ve means bigger
            // obj1 is this and obj2 passed as n // 0 means equal
        }
    }

    public huffmanCoding(String feeder) throws Exception { // passing the string feeder step 1
        HashMap<Character, Integer> freqMap = new HashMap<>(); // creating the freq map step 2
        for (int i = 0; i < feeder.length(); i++) {
            char c = feeder.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1); // making freq. map of the characters
        }

        minHeap<Node> heap = new minHeap<>();
        Set<Map.Entry<Character, Integer>> freqSet = freqMap.entrySet();// creating a hashset so we can iterate through
                                                                        // the sets
        for (Map.Entry<Character, Integer> i : freqSet) {
            Node node = new Node(i.getKey(), i.getValue());// making node for each set
            heap.insert(node); // inserting each node in the heap step 3
        }
        while (heap.size() != 1) { // condition bcz we're leaving one node at the end which works as a tree
            Node first = heap.removeHeap(); // removed 2 elements step 4
            Node second = heap.removeHeap();

            Node newNode = new Node('\0', first.cost + second.cost); // combining the removed nodes naming with a null
                                                                     // step 4
            newNode.left = first;
            newNode.right = second;

            heap.insert(newNode); // inserting the combined node in the heap step 5
        }

        Node finalNode = heap.removeHeap();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();

        this.fillEncoderDecoder(finalNode, "");
    }

    private void fillEncoderDecoder(Node node, String s) { // getting the path to the leaf node of the tree.
        // each path to the leaf node gets the unique code that identifies each
        // character in the maps
        if (node == null)
            return;

        if (node.right == null && node.left == null) {
            this.encoder.put(node.data, s);
            this.decoder.put(s, node.data);
        }
        fillEncoderDecoder(node.left, s + '0');// adding 0s when it goes left step 6
        fillEncoderDecoder(node.right, s + '1');// adding 1s when it goes right step 6

        // will create unique identifies for each character
    }

    public String encode(String strSource) { // will create the compressed encoded string
        String ans = "";
        for (int i = 0; i < strSource.length(); i++) {
            ans = ans + encoder.get(strSource.charAt(i));
        }
        return ans;
        /*
         * when the huffman object is created for a perticular string itwill encoded in
         * the encoder map. and when the encoder will called itll fetch the encoded
         * data, sum all the encoded data from the map and return
         */
    }

    public String decode(String strDecode) {
        String key = "";
        String ans = "";

        for (int i = 0; i < strDecode.length(); i++) {
            key = key + strDecode.charAt(i); // keep adding the characters one by one from the decoded code
            if (decoder.containsKey(key)) { // check if the added string available on the string or not
                ans += decoder.get(key); // if it is then get the value and add it to the ans / final decoded string
                key = ""; // and reinitialize the key
            }
            /*
             * if not 1 then next, 11. avail in map? no.
             * if not 11 then next 111. is this avail in the map ?
             * yes it is then get the value and add with the answer , and reinitialize key
             * with nothing
             */
        }
        return ans;
    }

}