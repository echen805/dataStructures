import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private ArrayList<LinkedList<String>> elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;
    
    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }
    
    // find, add, check, delete
    private LinkedList<String> getBucket (String value) {
        int bucketNumber = (int)this.hashFunc(value);
        return elems.get(bucketNumber);
    }
    
    public void add(String value) {
        LinkedList<String> bucket = getBucket(value);
        if(bucket.contains(value)){}
        else{
            bucket.push(value);
        }
    }
    
    public void find(String value){
        LinkedList<String> bucket = getBucket(value);        
        if(bucket.contains(value)){
            out.print("yes" + "\n");
        }
        else{
            out.print("no" + "\n");
        }                    
    }
    
    public void delete (String value){
        LinkedList<String> bucket = getBucket(value);
        bucket.remove(value);
    }
    
    public void check (int indexOfList){
        LinkedList<String> bucket = elems.get(indexOfList);
        if(bucket.isEmpty()) {
            out.println();
        }
        else {
            for (String cur : bucket){                                    
                out.print(cur + " ");                
            }
            out.println();
        }
    }
    
    
    
    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s); // separates the queries to everything besides "check"
        } else {
            int ind = in.nextInt();
            return new Query(type, ind); // create a key-value pair for check to find the index
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
        
    }          
    
    private void processQuery(Query query) {
//        outputBuckets(elems);
        switch (query.type) {
            case "add":                                
                add(query.s);
                break;
            case "del":
                delete(query.s);
                break;
            case "find":
                find(query.s);
                break;
            case "check":               
                check(query.ind);
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {                
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();        
        elems = new ArrayList<LinkedList<String>>(bucketCount);
                
        for (int i = 0; i < bucketCount; i++) {
            elems.add(i, new LinkedList<String>());
        }
        
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
        
        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

