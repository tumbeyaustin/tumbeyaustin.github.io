public static void main(String[] arg) {

    class ArrayCompression {
        public static int[][] compressRuns(int[] arr) {
            if (arr.length == 0) {
                return new int[0][0];
            }

            // count how many different runs there are
            int runs = 1;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] != arr[i + 1]) {
                    runs++;
                }
            }

            // make the result array
            int[][] result = new int[runs][2];

            // fill it in
            int val = arr[0];
            int count = 1;
            int index = 0;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == val) {
                    count++;
                } else {
                    result[index][0] = val;
                    result[index][1] = count;
                    index++;
                    val = arr[i];
                    count = 1;
                }
            }

            // add last one
            result[index][0] = val;
            result[index][1] = count;

            return result;
        }

        public static int[] decompressRuns(int[][] compressedArr) {
            if (compressedArr.length == 0) {
                return new int[0];
            }

            // figure out how long the array should be
            int size = 0;
            for (int i = 0; i < compressedArr.length; i++) {
                size = size + compressedArr[i][1];
            }

            int[] result = new int[size];
            int pos = 0;

            // go through each run and add the values
            for (int i = 0; i < compressedArr.length; i++) {
                int value = compressedArr[i][0];
                int times = compressedArr[i][1];

                for (int j = 0; j < times; j++) {
                    result[pos] = value;
                    pos++;
                }
            }

            return result;
        }

        public static void main(String[] args) {
            // test it
            int[] test = {4, 4, 4, 1, 1, 2};

            System.out.println("Original:");
            for (int i = 0; i < test.length; i++) {
                System.out.print(test[i] + " ");
            }
            System.out.println();

            int[][] compressed = compressRuns(test);

            System.out.println("Compressed:");
            for (int i = 0; i < compressed.length; i++) {
                System.out.print("{" + compressed[i][0] + "," + compressed[i][1] + "} ");
            }
            System.out.println();

            int[] decompressed = decompressRuns(compressed);

            System.out.println("Decompressed:");
            for (int i = 0; i < decompressed.length; i++) {
                System.out.print(decompressed[i] + " ");
            }
            System.out.println();
        }
    }
}