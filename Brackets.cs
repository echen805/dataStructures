using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Brackets;

namespace Brackets
{    
    public class Brackets
    {
        char type;
        int position;

        Brackets(char _type, int _position)
        {
            type = _type;
            position = _position;
        }

        public Brackets()
        {
        }

        public int GetPosition()
        {
            return this.position;
        }

        public Boolean Match(char b)
        {
            if (this.type == '[' && b == ']')
                return true;
            if (this.type == '{' && b == '}')
                return true;
            if (this.type == '(' && b == ')')
                return true;
            return false;
        }        

        static bool isBalancev5(string input)
        {
            Stack<Brackets> open_tag = new Stack<Brackets>();
            Stack<Brackets> close_tag = new Stack<Brackets>();
            char c;


            if (input.Length == 1)
            {
                Console.WriteLine(1);
                return false;
            }

            for (int i = 0; i < input.Length; i++)
            {
                c = input[i];
                Brackets open_temp = new Brackets();                

                // Processes open tags
                if (c == '(' || c == '[' || c == '{')
                {                    
                    open_temp.type = c;
                    open_temp.position = i+1;
                    open_tag.Push(open_temp);
                }

                // Processes close tags
                if (c == ')' || c == ']' || c == '}')
                {
                    if (open_tag.Count == 0)
                    {
                        Console.WriteLine(i+1);
                        return false;
                    }                  

                    if (open_tag.Peek().Match(c))
                    {
                        open_tag.Pop();
                    }
                    else
                    {
                        Console.WriteLine(i+1);
                        return false;
                    }
                }                                
            }

            if (open_tag.Count > 0)
            {
                Console.WriteLine(open_tag.Peek().position);
                return false;
            }

            return true;
        }

        // End of class


        static void Main(string[] args)
        {
            string input = Console.ReadLine();                        

            bool isSuccess = isBalancev5(input);
            if (isSuccess)
                Console.WriteLine("Success");

            Console.ReadKey();
        }

    }

}
