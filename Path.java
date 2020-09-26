
/** NetId(s): cd459, hp298
 * Name(s): Chris D'Arminio, Hermogenes Parente
 *
 *
 *
 */
package submit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import game.Edge;
import game.Node;

/** This class contains the shortest-path algorithm and other methods<br>
 * for an undirected graph. */
public class Path {

    /** Return the shortest path from node v to node end <br>
     * ---or the empty list if a path does not exist. <br>
     * Note: The empty list is a list with 0 elements ---it is not "null". */
    public static List<Node> shortest(Node v, Node end) {
        /* TODO Put your method shortest in your solution to Assignment A5
         * here. We will make our solution available for you to use after
         * the deadline for submitting A5. */
        // Contains nodes in the Frontier set F
        Heap<Node> F= new Heap<>(false);
        F.add(v, 0);
        // Contains Frontier set, F, and Settled set, S, as defined in the
        // abstract algorithm
        HashMap<Node, NodeInfo> info= new HashMap<>();

        info.put(v, new NodeInfo(0, null));
        while (F.size() != 0) {
            Node f= F.poll();

            // once at the destination, return the path
            if (f == end) { return getPath(info, f); }

            // check each edge going away from node
            for (Edge e : f.getExits()) {
                Node w= e.getOther(f);
                NodeInfo dbw= info.get(w);
                NodeInfo dbf= info.get(f);

                // add the edge length to the nodes distance from start
                int d= dbf.dist + e.length;

                // if w is far off set, add to frontier set
                if (info.get(w) == null) {
                    F.add(w, d);
                    info.put(w, new NodeInfo(d, f));

                    // otherwise, if the distance to node f + distance from f to w
                    // is less than current shortest distance to w, change it
                } else if (dbf.dist + e.length < dbw.dist) {
                    F.updatePriority(w, d);
                    dbw.dist= d;
                    dbw.bkptr= f;
                }
            }
        }
        // no path from v to end
        return new LinkedList<>();
    }

    /** An instance contains information about a node: <br>
     * the Distance of this node from the start node and <br>
     * its Backpointer: the previous node on a shortest path <br>
     * from the start node to this node (null for the start node). */
    private static class NodeInfo {
        /** shortest known distance from the start node to this one. */
        private int dist;
        /** backpointer on path (with shortest known distance) from start node to this one */
        private Node bkptr;

        /** Constructor: an instance with dist d from the start node and<br>
         * backpointer p. */
        private NodeInfo(int d, Node p) {
            dist= d;     // Distance from start node to this one.
            bkptr= p;    // Backpointer on the path (null if start node)
        }

        /** return a representation of this instance. */
        @Override
        public String toString() {
            return "dist " + dist + ", bckptr " + bkptr;
        }
    }

    /** Return the path from the start node to node end.<br>
     * Precondition: SandF contains all the necessary information about<br>
     * ............. the path. */
    public static List<Node> getPath(HashMap<Node, NodeInfo> SandF, Node end) {
        List<Node> path= new LinkedList<>();
        Node p= end;
        // invariant: All the nodes from p's successor to the end are in
        // path, in reverse order.
        while (p != null) {
            path.add(0, p);
            p= SandF.get(p).bkptr;
        }
        return path;
    }

    /** Return the sum of the weights of the edges on path pa. <br>
     * Precondition: pa contains at least 1 node. <br>
     * If 1 node, it's a path of length 0, i.e. with no edges. */
    public static int pathSum(List<Node> pa) {
        synchronized (pa) {
            Node v= null;
            int sum= 0;
            // invariant: if v is null, n is the first node of the path.<br>
            // .......... if v is not null, v is the predecessor of n on the path.
            // .......... sum = sum of weights on edges from first node to v
            for (Node n : pa) {
                if (v != null) sum= sum + v.getEdge(n).length;
                v= n;
            }
            return sum;
        }
    }

}
