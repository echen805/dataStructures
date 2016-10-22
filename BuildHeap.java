/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildheap;

/**
 *
 * @author Ed
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;    
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        int sizeOfInput = data.length-1;            
        swaps = new ArrayList<Swap>(sizeOfInput);
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

//    private void generateSwaps() {
//      swaps = new ArrayList<Swap>();
//      // The following naive implementation just sorts 
//      // the given sequence using selection sort algorithm
//      // and saves the resulting sequence of swaps.
//      // This turns the given array into a heap, 
//      // but in the worst case gives a quadratic number of swaps.
//      //
//      // TODO: replace by a more efficient implementation
//      for (int i = 0; i < data.length; ++i) {
//        for (int j = i + 1; j < data.length; ++j) {
//          if (data[i] > data[j]) {
//            swaps.add(new Swap(i, j));
//            int tmp = data[i];
//            data[i] = data[j];
//            data[j] = tmp;
//          }
//        }
//      }
//    }
    
    public void BuildHeap()
        {
            int size = data.length-1;
            
            for (int i = ((size - 1) / 2); i >= 0; i--)
            {
                if ((2 * i + 1) >= size)
                    continue;
                else
                    SiftDown(i);
            }                        
        }
    
    public void SiftDown(int i)
        {
            int maxindex = i;
            int leftChildIndex;
            int rightChildIndex;

            leftChildIndex = 2 * i + 1;
            rightChildIndex = 2 * i + 2;

            // if the left child is within the tree and the parent is higher than left child
            if (leftChildIndex <= data.length && data[leftChildIndex] <= data[maxindex])
            {
                maxindex = leftChildIndex;
            }
            // if the right child is within the tree and the parent is higher than right child
            if (rightChildIndex <= data.length && data[rightChildIndex] <= data[maxindex])
            {
                maxindex = rightChildIndex;
            }

            if (i != maxindex)
            {
                swaps.add(new Swap(i, maxindex));
                Swap(i, maxindex);
                SiftDown(maxindex);
            }

        }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        BuildHeap();
        writeResponse();
        out.close();
    }

    private void Swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
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

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

