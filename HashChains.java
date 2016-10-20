/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashchains;

/**
 *
 * @author Ed
 */
import java.io.*;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // for hash function
    private long bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    private Map<Long,AbstractQueue> mapTest;
    
    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    public HashChains() {
        this.mapTest = new HashMap<>();
    }

    private long hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s); // separates the queries to everything besides "check"
        } else {
            long ind = in.nextLong();
            return new Query(type, ind); // create a key-value pair where ind is the index of the list
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
//                if (!elems.contains(query.s))
//                    elems.add(0, query.s); // Zero is just a place holder because the values are searched via query.ind
                if(!mapTest.containsValue(query.s)){  
                    Queue temp = new AbstractQueue();
                    mapTest.put(long(0),new Query(query.ind,query.s));
                }
                break;
            case "del":
//                if (elems.contains(query.s))
//                    elems.remove(query.s);
                if (mapTest.containsValue(query.s))
                    mapTest.remove(query.s);
                break;
            case "find":
//                writeSearchResult(elems.contains(query.s));
                writeSearchResult(mapTest.containsValue(query.s)); // if it contains value query.s, output yes otherwise no
                break;
            case "check":
//                for (String cur : elems)
//                    if (hashFunc(cur) == query.ind)
//                        out.print(cur + " ");
                if(mapTest.containsKey(query.ind))                                        
                    out.print(mapTest.get(query.ind));
                else                    
                    out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextLong();
        long queryCount = in.nextLong();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        long ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, long ind) {
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
    }
}

