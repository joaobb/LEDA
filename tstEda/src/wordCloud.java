import java.util.HashMap;
import java.util.Scanner;

class wordCloud {
    public static void wordCount(String[] ent) {
        HashMap<String, Integer> hs = new HashMap();
        for (String elem : ent) {
            if (!hs.containsKey(elem)) {
                hs.put(elem, 1);
            } else {
                hs.put(elem, hs.get(elem) + 1);
            }
        }

        for (String key : hs.keySet()) {
            System.out.println(key + System.lineSeparator() + hs.get(key));
        }
        System.out.println("fim");
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        wordCount(sc.nextLine().split(" "));
    }
}
