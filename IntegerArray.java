import java.lang.reflect.Array;
import java.util.Arrays;

final class IntegerArray {
    private final int a[];

    public IntegerArray(int a[]){
        this.a = Arrays.copyOf(a, a.length);
    }

    public int[] getA() {
        return a;
    }

    public int length(){
        return a.length;
    }

    public int getElementAt(int i){
        return a[i];
    }

    public int sum(){
        int suma = 0;
        for (int element: a){
            suma+=element;
        }
        return suma;
    }

    public double average(){
        double suma = 0;
        for (int element: a){
            suma+=element;
        }
        return suma/(double) a.length;
    }

    public IntegerArray getSorted(){
        int nova[] = Arrays.copyOf(a, a.length);
        Arrays.sort(nova);
        return new IntegerArray(nova);
    }

    public IntegerArray concat(IntegerArray ia){
        int nova[] = new int[this.length()+ia.length()];
        System.arraycopy(this.a, 0, nova, 0, a.length);
        System.arraycopy(ia.getA(), 0, nova, a.length, ia.length());
        return new IntegerArray(nova);
    }

    @Override
    public String toString() {
        String str = new String();
        str+="[";
        for (int element: a){
            str+=element;
            str+=", ";
        }
        str+="]";
        return str;
    }
}
