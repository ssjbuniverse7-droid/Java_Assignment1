import java.util.Random;

public class PassWithAsciiGenerator {
    public static final Random RAND = new Random();
    public static void main(String[] args) {
        int passLength = RAND.nextInt(7)+10;
        StringBuilder password = new StringBuilder();

        for (int i=0;i<passLength;i++) {
            password.append(changeToChar());
        }
        System.out.println(password);
    }
    public static char changeToChar () {
        int i;
        while (true) {
            i = RAND.nextInt(256);
            if ((i>=48 && i<=57) || (i>=65 && i<=90) || (i>=97 && i<=122)) {
                break;
            }
        }
        return (char)i;
    }
}
