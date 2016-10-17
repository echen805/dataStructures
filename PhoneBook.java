//clean up the file and find the null. 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    //private List<Contact> contacts = new ArrayList<>();
    private List<Contact> contacts;
    private Map<Integer,String> mapContact;

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    public PhoneBook() {
        this.mapContact = new HashMap<>();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            if(mapContact.containsKey(query.number)){
                mapContact.replace(query.number, query.name);
                wasFound = true;
            }
//            for (Contact contact : contacts)
//                if (contact.number == query.number) {
//                    contact.name = query.name;                    
//                    wasFound = true;
//                    break;
//                }            
            // otherwise, just add it
//            if (!wasFound)
//                contacts.add(new Contact(query.name, query.number));
            if (!wasFound)
                mapContact.put(query.number, query.name);
        } else if (query.type.equals("del")) {
//            for (Iterator<Contact> it = contacts.iterator(); it.hasNext(); )
//                if (it.next().number == query.number) {
//                    it.remove();
//                    break;
//                }
            if(mapContact.containsKey(query.number))
                mapContact.remove(query.number);
        } else {
            String response = "not found";
//            for (Contact contact: contacts)
//                if (contact.number == query.number) {
//                    response = contact.name;
//                    break;
//                }
            if(mapContact.containsKey(query.number)){
                response = mapContact.get(query.number);
            }
            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        contacts = new ArrayList<>(queryCount);
        //Map<Integer,String> mapContact = contacts.stream().collect(Collectors.toMap(Contact::GetNumber, Contact::GetName));
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());                
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
        
        public String GetName(){
            return this.name;
        }
        
        public int GetNumber(){
            return this.number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
