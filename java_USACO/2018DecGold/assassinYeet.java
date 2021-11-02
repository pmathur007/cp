import java.util.ArrayList;
import java.util.Collections;

public class assassinYeet
{
    public static void main(String[] args)
    {
        ArrayList<String> ppl = new ArrayList<>();
        ppl.add("Aaron");
        ppl.add("Aaryan");
        ppl.add("Ashwitha");
        ppl.add("Michelle");
        ppl.add("Sofia");

        Collections.shuffle(ppl);
        for (int i = 0; i < ppl.size() - 1; i++)
            System.out.println(ppl.get(i) + " --> " + ppl.get(i + 1));
        System.out.println(ppl.get(ppl.size() - 1) + " --> " + ppl.get(0));
    }
}
