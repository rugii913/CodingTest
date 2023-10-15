package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch3-문제 009 DNA 비밀번호(문제 제목: DNA 비밀번호) // P12891
public class P12891 {
    // 첫번째 줄 입력: DNA 문자열의 길이 |S|, 비밀번호로 사용할 부분 문자열의 길이 |P| (1 <= |P| <= |S| <= 1,000,000)
    // 두번째 줄 입력: DNA 문자열
    // 세번째 줄 입력: 부분 문자열에 포함돼야 할 {'A', 'C', 'G', 'T'}의 최소 개수 ex. 1 0 0 1
    // cf. 부분 문자열이 등장하는 위치가 다르면 부분 문자열의 내용이 같더라도 다른 문자열로 취급 - 만약에 같은 문자열로 취급한다면 set에 넣고 size를 얻으면 될 것 같다. (x 시간 복잡도 때문에 불가능할 듯)

    /*
    * 슬라이딩 윈도우
    * - 길이가 P인 윈도우를 지정해서 배열 S의 시작점에 놓는다.
    * - 윈도우를 오른쪽으로 밀면서 윈도우에 잡힌 값들이 조건에 맞는지 탐색
    * */

    /*
     * 시간 복잡도 관련
     * P, S의 길이가 1,000,000(=10^6)으로 매우 크므로, O(n) 알고리즘 사용해야함 -> 슬라이딩 윈도우
     */
    
    // -> 풀이 x1, x2처럼 이중 for문, 게다가 탈출조건도 딱히 없다면 시간 초과 가능성 올라감
    //    - 나는 P의 크기가 커지면 S에서 순회할 부분도 적어지므로 가능하지 않을까 생각했는데, 아니었음
    //    - S = 10^6, P = 5 * 10^5라면 바로 시간 초과
    
    /*
    * 핵심 포인트 - 현재 상태 배열을 업데이트 할 때, 현재 슬라이딩 윈도우(부분 문자열) 배열을 순회하는 게 아니라 제거되는 문자열, 새로 들어온 문자열만 반영하여 확인
    * */

    // 풀이 x1 - 시간 초과
    public void solutionx1() {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        int P = sc.nextInt();

        String DNAString = sc.next();

        // String[] minACGTString = sc.nextLine().split("\\s");
        /*
        int minA = Integer.parseInt(minACGTString[0]);
        int minC = Integer.parseInt(minACGTString[1]);
        int minG = Integer.parseInt(minACGTString[2]);
        int minT = Integer.parseInt(minACGTString[3]);
         */
        int minA = sc.nextInt();
        int minC = sc.nextInt();
        int minG = sc.nextInt();
        int minT = sc.nextInt();

        int count = 0;
        for (int i = 0; i <= S - P; i++) {
            String substringPLength = DNAString.substring(i, i + P);

            int neededA = minA;
            int neededC = minC;
            int neededG = minG;
            int neededT = minT;

            for (int j = 0; j < P; j++) {
                switch (substringPLength.charAt(j)) {
                    case 'A':
                        neededA--;
                        break;
                    case 'C':
                        neededC--;
                        break;
                    case 'G':
                        neededG--;
                        break;
                    case 'T':
                        neededT--;
                        break;
                }
            }

            if (neededA <= 0 && neededC <= 0 && neededG <= 0 && neededT <= 0 ) {
                count++;
            }
        }

        System.out.println(count);

        sc.close();
    }

    // 풀이 x2 - 시간 초과 - BufferedReader, char[] 사용하도록 바꿔도 시간 초과함
    public void solutionx2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split("\\s");
        int S = Integer.parseInt(inputs[0]);
        int P = Integer.parseInt(inputs[1]);

        String DNAString = br.readLine();

        inputs = br.readLine().split("\\s");
        int minA = Integer.parseInt(inputs[0]);
        int minC = Integer.parseInt(inputs[1]);
        int minG = Integer.parseInt(inputs[2]);
        int minT = Integer.parseInt(inputs[3]);

        int count = 0;
        char[] DNAStringChars = DNAString.toCharArray();
        char[] substringChars = new char[P];
        for (int i = 0; i <= S - P; i++) {
            System.arraycopy(DNAStringChars, i, substringChars, 0, P);

            int neededA = minA;
            int neededC = minC;
            int neededG = minG;
            int neededT = minT;

            for (int j = 0; j < P; j++) {
                switch (substringChars[j]) {
                    case 'A':
                        neededA--;
                        break;
                    case 'C':
                        neededC--;
                        break;
                    case 'G':
                        neededG--;
                        break;
                    case 'T':
                        neededT--;
                        break;
                }
            }

            if (neededA <= 0 && neededC <= 0 && neededG <= 0 && neededT <= 0 ) {
                count++;
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 1
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split("\\s");
        int S = Integer.parseInt(inputs[0]);
        int P = Integer.parseInt(inputs[1]);

        String DNAString = br.readLine();

        inputs = br.readLine().split("\\s");
        int minA = Integer.parseInt(inputs[0]);
        int minC = Integer.parseInt(inputs[1]);
        int minG = Integer.parseInt(inputs[2]);
        int minT = Integer.parseInt(inputs[3]);

        int count = 0;
        char[] DNAStringChars = DNAString.toCharArray();

        int currentA = 0;
        int currentC = 0;
        int currentG = 0;
        int currentT = 0;
        for (int i = 0; i < P; i++) {
            switch (DNAStringChars[i]) {
                case 'A':
                    currentA++;
                    break;
                case 'C':
                    currentC++;
                    break;
                case 'G':
                    currentG++;
                    break;
                case 'T':
                    currentT++;
                    break;
            }
        }
        if (currentA >= minA && currentC >= minC && currentG >= minG && currentT >= minT) {
            count++;
        }

        int removeTargetIndex = 0;
        int addTargetIndex = P;
        while (addTargetIndex < S) {
            switch (DNAStringChars[removeTargetIndex]) {
                case 'A':
                    currentA--;
                    break;
                case 'C':
                    currentC--;
                    break;
                case 'G':
                    currentG--;
                    break;
                case 'T':
                    currentT--;
                    break;
            }

            switch (DNAStringChars[addTargetIndex]) {
                case 'A':
                    currentA++;
                    break;
                case 'C':
                    currentC++;
                    break;
                case 'G':
                    currentG++;
                    break;
                case 'T':
                    currentT++;
                    break;
            }

            if (currentA >= minA && currentC >= minC && currentG >= minG && currentT >= minT) {
                count++;
            }
            removeTargetIndex++;
            addTargetIndex++;
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 2(책) - 핵심 포인트: 슬라이딩 윈도우 + 현재 상태 배열을 업데이트 할 때, 현재 슬라이딩 윈도우(부분 문자열) 배열을 순회하는 게 아니라 제거되는 문자열, 새로 들어온 문자열만 반영하여 확인
    static int checkArr[];
    static int myArr[];
    static int checkSecret;

    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Result = 0;
        char[] A = new char[S];
        checkArr = new int[4];
        myArr = new int[4];
        checkSecret = 0;
        A = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                checkSecret++;
            }
        }

        // 초기 P 부분 문자열 처리 부분
        for (int i = 0; i < P; i++) {
            Add(A[i]);
        }
        if (checkSecret == 4) {
            Result++;
        }

        // 슬라이딩 윈도우 처리 부분
        for (int i = P; i < S; i++) {
            int j = i - P;
            Add(A[i]);
            Remove(A[j]);
            if (checkSecret == 4) {
                Result++;
            }
        }

        System.out.println(Result);
        br.close();
    }

    private static void Add(char c) { // 새로 들어온 문자를 처리하는 메서드, 이 부분을 메서드로 따로 빼기 위해 myArr, checkArr, checkSecret을 static으로 만들어준 것
        switch (c) {
            case 'A' :
                myArr[0]++;
                if (myArr[0] == checkArr[0]) {
                    checkSecret++;
                }
                break;
            case 'C' :
                myArr[1]++;
                if (myArr[1] == checkArr[1]) {
                    checkSecret++;
                }
                break;
            case 'G' :
                myArr[2]++;
                if (myArr[2] == checkArr[2]) {
                    checkSecret++;
                }
                break;
            case 'T' :
                myArr[3]++;
                if (myArr[3] == checkArr[3]) {
                    checkSecret++;
                }
                break;
        }
    }

    private static void Remove(char c) { // 제거되는 문자를 처리하는 메서드 - Add(~)와 모양은 거의 비슷
        switch (c) {
            case 'A' :
                if (myArr[0] == checkArr[0]) {
                    checkSecret--;
                }
                myArr[0]--;
                break;
            case 'C' :
                if (myArr[1] == checkArr[1]) {
                    checkSecret--;
                }
                myArr[1]--;
                break;
            case 'G' :
                if (myArr[2] == checkArr[2]) {
                    checkSecret--;
                }
                myArr[2]--;
                break;
            case 'T' :
                if (myArr[3] == checkArr[3]) {
                    checkSecret--;
                }
                myArr[3]--;
                break;
        }
    }
}
