import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class family
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("family.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("family.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        String bessie = meta[1];
        String elsie = meta[2];

        Map<String, Cow> fam = new HashMap<>();
        fam.put(bessie, new Cow(bessie));
        fam.put(elsie, new Cow(elsie));

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            String mom = line[0];
            String child = line[1];

            if (fam.get(mom) == null)
                fam.put(mom, new Cow(mom));
            if (fam.get(child) == null)
                fam.put(child, new Cow(child));

            fam.get(mom).addChild(fam.get(child));
            fam.get(child).setMother(fam.get(mom));
        }

        Cow current = fam.get(bessie);
        String relation = "";
        int numGreats = 0;

        while(current.getMother() != null)
        {
            if (current.getMother().getChildren().contains(fam.get(elsie)))
            {
                relation = "sister";
                break;
            }
            if (current.getMother().equals(fam.get(elsie)))
            {
                relation = "mother";
                break;
            }
            else if (current.getMother().getMother() != null && current.getMother().getMother().getChildren().contains(fam.get(elsie)))
            {
                relation = "aunt";
                break;
            }
            else
            {
                numGreats++;
                current = current.getMother();
            }
        }

        String finalRelation = "";
        if (!relation.equals(""))
        {
            if (numGreats == 0)
                finalRelation = relation;
            else
            {
                for (int i = 0; i < numGreats - 1; i++)
                {
                    finalRelation += "great-";
                }
                if (relation.equals("aunt"))
                    finalRelation += "great-aunt";
                else
                    finalRelation += "grand-mother";
            }
        }

        if (finalRelation.equals(""))
        {
            Cow bessieAncestor = fam.get(bessie);
            Cow elsieAncestor = fam.get(elsie);

            while (bessieAncestor.getMother() != null)
                bessieAncestor = bessieAncestor.getMother();

            while (elsieAncestor.getMother() != null)
                elsieAncestor = elsieAncestor.getMother();

            if (bessieAncestor.equals(elsieAncestor))
                finalRelation = "cousins";
            else
                finalRelation = "NOT RELATED";
        }

        if (finalRelation.equals("NOT RELATED"))
            out.println(finalRelation);
        else out.println(elsie + " is the " + finalRelation + " of " + bessie);

        out.close();
    }
}

class Cow
{
    private String name;
    private Cow mother;
    private LinkedList<Cow> children;

    public Cow(String name)
    {
        this.name = name;
        this.mother = null;
        this.children = new LinkedList<>();
    }

    public Cow(String name, Cow mother)
    {
        this.name = name;
        this.mother = mother;
        this.children = new LinkedList<>();
    }

    public String getName()
    {
        return name;
    }

    public Cow getMother()
    {
        return mother;
    }

    public void setMother(Cow mother)
    {
        this.mother = mother;
    }

    public LinkedList<Cow> getChildren()
    {
        return children;
    }

    public void addChild(Cow child)
    {
        this.children.add(child);
    }

    @Override
    public boolean equals(Object o)
    {
        Cow cow = (Cow) o;
        return this.name.equals(cow.name);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        for (char c : this.name.toCharArray())
        {
            hash += Math.pow((int) c, 2);
        }
        return hash;
    }
}
