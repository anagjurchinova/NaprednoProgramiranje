import java.util.Scanner;
import java.io.InputStream;

public class ArrayReader {

    public static IntegerArray readIntegerArray(InputStream input){
        Scanner in = new Scanner(input);
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i=0; i<n; i++){
            a[i] = in.nextInt();
        }
        return new IntegerArray(a);
    }
}
