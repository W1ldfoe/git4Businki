import javax.swing.JOptionPane;

public class ModulCherniakh {
    public static void main(String[] args) {

        String values1;
        values1 = JOptionPane.showInputDialog("Enter all element of FIRST Array");

        int count1 = 0;
        for (int i = 0; i < values1.length(); i++) {
            if (Character.isWhitespace(values1.charAt(i))) count1++;
        }

        int[] array1 = new int[++count1];

        for (int i = 0; i < count1; i++) {
            array1[i] = Integer.parseInt(values1.split(" ", 5)[i]);
        }
        String values2;
        values2 = JOptionPane.showInputDialog("Enter all element of SECOND Array");

        int count2 = 0;
        for (int i = 0; i < values2.length(); i++) {
            if (Character.isWhitespace(values2.charAt(i))) count2++;
        }

        int[] array2 = new int[++count2];

        for (int i = 0; i < count2; i++) {
            array2[i] = Integer.parseInt(values2.split(" ",5)[i]);
        }


        JOptionPane.showMessageDialog(null, "The sum of FIRST Array is: "
                + getTotal(array1) + "\nThe sum of SECOND Array is: " + getTotal(array2) +"\nThe max sum is :"
                + getMax(array1, array2));
    }

    public static double getTotal(int[] array) {
        double total = 0.0;
        for (int index = 0; index < array.length; index++)
            total += array[index];

        return total;
    }
    public static double getMax(int[] array1, int[] array2) {
         double max_sum = 0.0;
         double total1 = 0.0;
        for (int index1 = 0; index1 < array1.length; index1++) {
            total1 += array1[index1];
        }
        double total2 = 0.0;
        for (int index2 = 0; index2 < array2.length; index2++) {
            total2 += array2[index2];
        }
        if(total1>total2){
            max_sum = total1;
        }else{
            max_sum = total2;
        }
        if(total1==total2){
            JOptionPane.showMessageDialog(null, "The max sum is: ");
        }
        return max_sum;
    }
}
