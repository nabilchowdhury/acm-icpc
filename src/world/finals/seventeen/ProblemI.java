package world.finals.seventeen;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Secret Chamber at Mount Rushmore
 * */
public class ProblemI {

    private static final int LENGTH = 26;
    private static boolean[][] graph;

    private static boolean bfs(int src, int dest) {
        if (src == dest) return true;

        boolean[] seen = new boolean[LENGTH];
        Queue<Integer> queue = new LinkedList<>();

        seen[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int neighbor = 0; neighbor < graph[cur].length; ++neighbor) {
                if (graph[cur][neighbor] && !seen[neighbor]) {
                    // We found a path
                    if (neighbor == dest) return true;

                    seen[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Read from stdin
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();

        // The graph is at most 26x26, we could optimize this later eg. by using an adj list instead
        graph = new boolean[LENGTH][LENGTH];
        // Fill the graph
        for (int i = 0; i < m; ++i) {
            char from = sc.next().charAt(0);
            char to = sc.next().charAt(0);
            graph[from - 'a'][to - 'a'] = true;
        }

        // Now we check each word pair
        for (int i = 0; i < n; ++i) {
            String original = sc.next();
            String translated = sc.next();

            if (original.length() != translated.length()) {
                System.out.println("no");
                continue;
            }

            int j = 0;
            for (; j < original.length(); ++j) {
                if (!bfs(original.charAt(j) - 'a', translated.charAt(j) - 'a')) {
                    System.out.println("no");
                    break;
                }
            }

            if (j == original.length()) {
                System.out.println("yes");
            }
        }
    }
}
