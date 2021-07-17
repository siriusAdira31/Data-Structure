	import java.util.*;

	class TrieNode{

		public static int ALPHABET_COUNT = 26;
		boolean endOfWord;
		TrieNode[] children;

		public TrieNode(){
			this.children = new TrieNode[ALPHABET_COUNT];
			this.endOfWord = false;
		}

		public boolean getEndOfWord(){
			return this.endOfWord;
		}

		public void setEndOfWord(){
			this.endOfWord = true;
		}

		public void disableEndOfWord(){
			this.endOfWord = false;
		}
	}


	public class Trie{
		private TrieNode root;

		public Trie(){
			this.root = new TrieNode();
		}

		public int getAlphabetIndex(char ch){
			return ch - 'a';
		}

		/**************

			Insert a word in Trie
			Case 1: If the word has no common subsequence i.e. No Common Prefix
			Case 2: If the word has a common subsequence i.e. Common Prefix Found
			Case 3: If the word is present i.e. Common Prefix Found
			
			Time Complexity - O(input_word_length)	

		*************/

		public void insertKey(String key){
			if(key == null){
				System.out.println(" INVALID KEY VALUE - " + key);
				return;
			}

			key = key.toLowerCase();
			TrieNode current = this.root;
			int index = 0;
			for(int i=0;i<key.length();i++){
				index = getAlphabetIndex(key.charAt(i));
				if(current.children[index] == null){
					current.children[index] = new TrieNode();
				}

				current = current.children[index];
			}

			current.setEndOfWord();
		}


		/*******************
		Search  a word in Trie
		Case 1: Word is not present in Trie
		Case 2: Path found but isEndWord() is not set for the last character
		Case 3: Word is found and isEndWord is set for last node of the path
		
		Time Complexity - O(input_word_length)

		********************/

		public boolean searchKey(String key){
			if(root == null){
				System.out.println(" INVALID TRIE " + root);
				return false;
			}

			if(key == null){
				System.out.println(" INVALID KEY VALUE - " + key);
				return false;
			}

			key = key.toLowerCase();
			int index = 0;

			TrieNode current = root;

			for(int i=0;i<key.length();i++){
				index = getAlphabetIndex(key.charAt(i));

				if(current.children[index] == null)
					return false;

				current = current.children[index];
			}

			return current.getEndOfWord();
		}


		public boolean hasNoChildren(TrieNode current){
			for(int i=0;i<current.children.length;i++){
				if(current.children[i] != null)
					return false;
			}

			return true;
		}

		
		/*******************

			Deletion of word in Trie : 
			Case 1: If the word to be deleted has no common subsequence
			Case 2: If the word to be deleted is a prefix of some other word
			Case 3: If the word to be deleted has a common prefix
			
			Time Complexity - O(input_word_length)

		*********************/

		public boolean deleteKeyFromTrie(String key,TrieNode current, int length, int level){
			boolean deleteSelf = false;

			if(length == level){
				if(hasNoChildren(current)){
					current = null;
					deleteSelf = true;
				}else{
					current.disableEndOfWord();
					deleteSelf = false;
				}

			}else{
				TrieNode child = current.children[getAlphabetIndex(key.charAt(level))];

				boolean childDeleted = deleteKeyFromTrie(key,child,length,level+1);

				if(childDeleted){
					// Child is deleted , make children pointer = NULL
					current.children[getAlphabetIndex(key.charAt(level))] = null;


					// Case 2. If current has enabled EndOfWord i.e. key is part of another key
					if(current.getEndOfWord()){
						deleteSelf = false;
					}else if(!hasNoChildren(current)){
						// Case 3. If current has more children , then cannot delete current.
						deleteSelf = false;
					}else{
						// delete current which don't have case 2 & 3 constraint.
						current = null;
						deleteSelf = true;
					}
				}else{
					deleteSelf = false;
				}
			}

			return deleteSelf;
		}

		public void deleteKey(String key){
			if(root == null){
				System.out.println(" INVALID TRIE " + root);
				return;
			}

			if(key == null){
				System.out.println(" INVALID KEY VALUE - " + key);
				return;
			}

			deleteKeyFromTrie(key, root, key.length(),0);
		}


		public static void main(String[] args){
			// Input keys (use only 'a' through 'z' and lower case)
		    String keys[] = {"the", "a", "there", "answer", "any",
		                     "by", "bye", "their","abc"};
		    String output[] = {"Not present in trie", "Present in trie"};
		    Trie t = new Trie();
		    
		    System.out.println("Keys to insert: "+ Arrays.toString(keys) + "\n");
		        
		    // Construct trie       
		    int i;
		    for (i = 0; i < keys.length ; i++)
		    {
		      t.insertKey(keys[i]);
		      System.out.println("\""+ keys[i]+ "\"" + "Inserted.");
		    }	

		    // Search for different keys
		   	// Search for different keys and delete if found
		    if(t.searchKey("the") == true)
		    {
		      System.out.println("the --- " + output[1]);
		      t.deleteKey("the");
		      System.out.println("Deleted key \"the\"");
		    }
		    else System.out.println("the --- " + output[0]);
		         
		    if(t.searchKey("these") == true)
		    {
		      System.out.println("these --- " + output[1]);
		      t.deleteKey("these");
		      System.out.println("Deleted key \"these\"");
		    }
		    else System.out.println("these --- " + output[0]);
		         
		    if(t.searchKey("abc") == true)
		    {
		      System.out.println("abc --- " + output[1]);
		       t.deleteKey("abc");
		      System.out.println("Deleted key \"abc\""); 
		    }
		    else System.out.println("abc --- " + output[0]);
		         
		    // check if the string has deleted correctly or still present
		    if(t.searchKey("abc") == true)
		      System.out.println("abc --- " + output[1]);
		    else System.out.println("abc --- " + output[0]);

		    
		}
	}