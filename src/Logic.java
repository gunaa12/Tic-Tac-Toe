public class Logic {
    // attributes
    private static int[][] map;

    // getter
    public int[][] getMap() {
        return map;
    }

    // methods
    public static void setMap(int row, int col, int player) {
        map[row][col] = player;
    }

    public static void reset() {
        map = new int[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                map[row][col] = -1;
            }
        }
    }

    public static boolean isValid(int row, int col) {
        if (map[row][col] == -1) return true;
        return false; 
    }

    public static int findWinner() {
        //checking horizontals
        for (int row = 0; row < 3; row++) {
            int temp = map[row][0];
            if (temp != -1) {
                if((temp == map[row][1]) && (temp == map[row][2])) {
                    return temp;
                }
            }
		}

        //checking verticals
        for (int col = 0; col < 3; col++) {
            int temp = map[0][col];
            if (temp != -1) {
                if((temp == map[1][col]) && (temp == map[2][col])) {
                    return temp;
                }
            }
		}

        //checking diagonols
        int temp = map[1][1];
        if (temp != -1) {
            if (((map[0][0] == temp) && (map[2][2] == temp)) || ((map[0][2] == temp) && (map[2][0] == temp))) {
                return temp;
            }
        }
        

        return -1;
    }

    // default methods
    public static void print() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(map[row][col] + " | ");
            }
            System.out.println();
        }
    }
}
