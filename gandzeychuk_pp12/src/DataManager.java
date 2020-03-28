import java.util.ArrayList;

public class DataManager {
    public static class Element {
        public String name;
        public int value;

        public Element(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    public ArrayList<Element> elements = new ArrayList<Element>();

    public ArrayList<Element> getNormalized(int height) {
        int max = -1;
        for (Element e : elements)
            if (e.value > max)
                max = e.value;

        ArrayList<Element> result = new ArrayList<Element>();
        for (Element e : elements)
            result.add(new Element(e.name, e.value * height / max));

        return result;
    }
}
