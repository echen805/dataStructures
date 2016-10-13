using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HeapBuild
{
    public class Program
    {
        public static int[] data;
        public static List<Swap> swaps;

        public class Swap
        {
            public int index1;
            public int index2;

            public Swap(int node1, int node2)
            {
                index1 = node1;
                index2 = node2;
            }
        }

        

        
        

        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            data = new int[Int32.Parse(input)];

            string stringInput = Console.ReadLine();
            string[] strArray = stringInput.Split(' ');
            strArray = strArray.Where(s => !String.IsNullOrEmpty(s)).ToArray(); // removes any nulls or empty indexes
            data = Array.ConvertAll<string, int>(strArray, int.Parse);
            

            BuildHeap(data);
            
            Console.ReadKey();
        }

        public static void BuildHeap(int[] array)
        {
            int size = array.Count()-1;
            swaps = new List<Swap>();

            for (int i = (size-1/2); i>=0; i--)
            {
                if ((2 * i + 1) >= size)
                    continue;
                else
                    SiftDown(i);
            }

            
            Console.WriteLine(swaps.Count);
            foreach(Swap val in swaps)
            {
                Console.WriteLine(val.index1 + " " + val.index2);
            }
        }        

        public static void SiftDown(int i)
        {            
            int maxindex = i;
            int l;
            int r;

            l = 2 * i +1;
            r = 2 * i + 2;
            
            // if the left child is within the tree and the parent is higher than left child
            if (l <= data.Count() && data[l] <= data[maxindex])
            {
                maxindex = l;
            }                       
            // if the right child is within the tree and the parent is higher than right child
            if (r <= data.Count() && data[r] <= data[maxindex])
            {
                maxindex = r;
            }

            if(i != maxindex)
            {
                swaps.Add(new Swap(i, maxindex));
                swapNumbers(ref data[i], ref data[maxindex]);
                SiftDown(maxindex);
            }
            
        }

        public static void swapNumbers(ref int a, ref int b)
        {
            int temp = b;
            b = a;
            a = temp;
        }

    }
}
