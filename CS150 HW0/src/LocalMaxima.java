public class LocalMaxima {

    public static int[] localMaxima(int[] arr) {
        // If array is too small, no local maxima possible
        if (arr == null || arr.length < 3) {
            return new int[0]; // return empty array
        }

        // Step 1: Count how many local maxima we have
        int count = 0;

        // Check each element (except first and last)
        for (int i = 1; i < arr.length - 1; i++) {
            // Is it bigger than both neighbors?
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                count++;
            }
        }

        // If no local maxima found, return empty array
        if (count == 0) {
            return new int[0];
        }

        // Step 2: Create array to store the local maxima
        int[] result = new int[count];
        int index = 0;

        // Check each element again and save the local maxima
        for (int i = 1; i < arr.length - 1; i++) {
            // Is it bigger than both neighbors?
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                result[index] = arr[i];
                index++;
            }
        }

        return result;
    }

    // Helper to print arrays
    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        // Test case from example
        int[] test1 = {2, 7, 5, 1, 3, 3, 9};
        System.out.println("Array:");
        printArray(test1);
        System.out.println("Local maxima:");
        printArray(localMaxima(test1));

        System.out.println();

        // Test with no local maxima
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Array:");
        printArray(test2);
        System.out.println("Local maxima:");
        printArray(localMaxima(test2));

        System.out.println();

        // Test with all local maxima
        int[] test3 = {1, 5, 2, 8, 3};
        System.out.println("Array:");
        printArray(test3);
        System.out.println("Local maxima:");
        printArray(localMaxima(test3));
    }
}
