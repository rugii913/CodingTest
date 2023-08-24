package programmers.lv0;

// lv0 안전지대
public class Lessons120866 {

    // 풀이 1 - board의 모든 위치를 조사해서 주변에 1이 있으면 2로 바꾸기 
    // ** 2차원 배열 사각 격자로 생각할 때 y축 먼저 접근함에 유의할 것
    public int solution1(int[][] board) {
        int n = board.length;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                investigate(y, x, board);
            }
        }

        int numOfZero = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (board[y][x] == 0) {
                    numOfZero++;
                }
            }
        }
        return numOfZero;
    }
    private void investigate(int y, int x, int[][] board) {
        int n = board.length;
        if (board[y][x] == 1) { // 이 부분 없으면 실패 -
            return;
        }

        for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, n - 1); j++) {
            for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, n - 1); i++) {
                if (board[j][i] == 1) {
                    board[y][x] = 2;
                }
            }
        }
    }
    private void investigatex(int y, int x, int[][] board) {
        // 실패하는 코드 - 테스트 1에서 실패
        // 원래 1이었던 위치 주변에 다른 1이 있으면 2로 바꿔버린다. - 특히 y + 1, x -1 위치에 다른 1이 있는 경우 문제가 된다.
        // 0 0 0     2 2 2
        // 0 1 0 >>> 2 2 0 이렇게 되어버림
        // 1 0 0     1 2 0
        int n = board.length;

        for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, n - 1); j++) {
            for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, n - 1); i++) {
                if (i == x && j == y) {
                    continue;
                }
                if (board[j][i] == 1) {
                    board[y][x] = 2;
                }
            }
        }
    }

    // 풀이 2 - 지뢰의 위치를 먼저 얻고, 각 지뢰 주변의 값을 2로 바꾸기
    public int solution2(int[][] board) {
        int n = board.length;

        // 지뢰 위치 x, y 값을 int[2]로 저장 - [x, y]
        int numOfMines = 0;
        int[][] tmpCoordinatesOfMines = new int[n * n][2];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (board[y][x] == 1) {
                    tmpCoordinatesOfMines[numOfMines][0] = x;
                    tmpCoordinatesOfMines[numOfMines][1] = y;
                    numOfMines++;
                }
            }
        }

        // 지뢰 위치를 지뢰 개수에 맞춰서 배열 복사
        int[][] coordinatesOfMines = new int[numOfMines][2];
        System.arraycopy(tmpCoordinatesOfMines, 0, coordinatesOfMines, 0, numOfMines);

        // 각 지뢰마다 주변 값들 중 0인 곳만 2로 바꿔줌
        for (int[] mineCoordinates : coordinatesOfMines) {
            for (int y = Math.max(mineCoordinates[1] - 1, 0); y <= Math.min(mineCoordinates[1] + 1, n - 1); y++) {
                for (int x = Math.max(mineCoordinates[0] - 1, 0); x <= Math.min(mineCoordinates[0] + 1, n - 1); x++) {
                    if (board[y][x] == 0) {
                        board[y][x] = 2;
                    }
                }
            }
        }

        // board 전체 탐색해서 0인 개수 합 가져오기
        int numOfZero = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    numOfZero++;
                }
            }
        }
        return numOfZero;
    }

    // 풀이 3(다른 풀이 참고) - 둘러싸는 액자 배열 사용
    public int solution3(int[][] board) {
        int answer = 0;

        int length = board.length; //길이
        int[][] temp = new int[length + 2][length + 2];
        // 상하좌우로 1씩 늘린 배열 생성

        // 액자에 board 이식
        for (int y = 1; y < length + 1; y++) {
            for (int x = 1; x < length + 1; x++) {
                temp[y][x] = board[y - 1][x - 1];
            }
        }
        /*
                00000
        110     01100
        111 ->  01110 이런 모양새로 만든 것
        010     00100
                00000
         */

        // 위험지대 찾기 *4중첩 for문... - 제한 사항도 있고 걸러지는 부분도 있어서 그렇게 느리진 않지만 가독성이...
        for (int y = 1; y < length + 1; y++) {
            for (int x = 1; x < length + 1; x++) {
                if (temp[y][x] == 1) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        for (int i = x - 1; i <= x + 1; i++) {
                            if (temp[j][i] != 1) {
                                temp[j][i] = 2;
                            }
                        }
                    }
                }
            }
        }

        // 안전지대 카운트
        for (int i = 1; i < length + 1; i++) {
            for (int j = 1; j < length + 1; j++) {
                if (temp[i][j] == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    // 풀이 4(다른 풀이 참고) - dx, dy 사용
    public int solution4(int[][] board) {
        // 순서대로 좌, 좌하, 하, 우하, 우, 우상, 상, 좌상
        final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        int safe = 0;
        int danger = 0;

        // 마크 작업과 개수 파악을 위한 순회를 한 번에
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] == 1) {
                    danger++;
                    for (int k = 0; k < 8; k++) { // dx, dy 배열 순회
                        // dx, dy를 한 번에 가져오므로 2중 for문을 1개의 for문으로 바꿀 수 있다.
                        if (y + dy[k] >= 0 && y + dy[k] < board.length && x + dx[k] >= 0 && x + dx[k] < board.length && board[y + dy[k]][x + dx[k]] == 0) {
                            // 이 부분을 max min으로 바꾸고 싶은데, 좋은 방법이 떠오르지 않는다.
                            board[y + dy[k]][x + dx[k]] = 2; // 위 조건에 "board[y + dy[k]][x + dx[k]] == 0"로 거르고 있으므로, 마킹 해놓는 작업은 필요하다.
                            danger++;
                        }
                    }
                }
            }
        }

        safe = board.length * board.length - danger;

        return safe;
    }
}
