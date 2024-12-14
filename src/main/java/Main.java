import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine().trim()); // Read the number of test cases

        while (t-- > 0) {
            char[] k = br.readLine().trim().toCharArray();
            bw.write(findNextPalindrome(k) + "\n");  // Find the next palindrome for each k input
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static String findNextPalindrome(char[] k) {
        int n = k.length;
        char[] result = new char[n];

        // Step 1: Mirror the left half onto the right half
        for (int i = 0; i < (n + 1) / 2; i++) {
            result[i] = k[i];
            result[n - 1 - i] = k[i];
        }

        // Step 2: Check if the result is greater than the input
        if (compare(result, k) > 0) {
            return new String(result);
        }

        // Step 3: Increment the mirrored part and propagate carry if needed
        int mid = (n - 1) / 2;
        while (mid >= 0 && result[mid] == '9') {
            result[mid] = '0';
            result[n - 1 - mid] = '0';
            mid--;
        }

        if (mid < 0) {
            // Case where all digits were 9, e.g., 999 -> 1001
            result = new char[n + 1];
            result[0] = '1';
            result[n] = '1';
            for (int i = 1; i < n; i++) {
                result[i] = '0';
            }
        } else {
            result[mid]++;
            result[n - 1 - mid] = result[mid];
        }

        return new String(result);
    }

    private static int compare(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        return 0;
    }
}
