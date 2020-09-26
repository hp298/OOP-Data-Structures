package submit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import game.FindState;
import game.Finder;
import game.Node;
import game.NodeStatus;
import game.ScramState;

/** Student solution for two methods. */
public class Pollack extends Finder {

    /** Get to the orb in as few steps as possible. <br>
     * Once you get there, you must return from the function in order to pick it up. <br>
     * If you continue to move after finding the orb rather than returning, it will not count.<br>
     * If you return from this function while not standing on top of the orb, it will count as <br>
     * a failure.
     *
     * There is no limit to how many steps you can take, but you will receive<br>
     * a score bonus multiplier for finding the orb in fewer steps.
     *
     * At every step, you know only your current tile's ID and the ID of all<br>
     * open neighbor tiles, as well as the distance to the orb at each of <br>
     * these tiles (ignoring walls and obstacles).
     *
     * In order to get information about the current state, use functions<br>
     * state.currentLoc(), state.neighbors(), and state.distanceToOrb() in FindState.<br>
     * You know you are standing on the orb when distanceToOrb() is 0.
     *
     * Use function state.moveTo(long id) in FindState to move to a neighboring<br>
     * tile by its ID. Doing this will change state to reflect your new position.
     *
     * A suggested first implementation that will always find the orb, but <br>
     * likely won't receive a large bonus multiplier, is a depth-first walk. <br>
     * Some modification is necessary to make the search better, in general. */
    @Override
    public void findOrb(FindState state) {
        // TODO 1: Get the orb
        dfswalkgreedy(state);
    }

    /** Contains all nodes that are visited in FindState. Updated every time a new node is
     * visited */
    private ArrayList<Long> v= new ArrayList<>(); // Visited tile on map
    private boolean orb= false;

    public void dfswalkgreedy(FindState u) {
        if (u.distanceToOrb() == 0) { orb= true; return; } // check if we got the orb

        long pos= u.currentLoc(); // save current position for later
        v.add(pos); // pollack!!!!!!!!!!!!!!!!!!!!!! make it a visited tile

        Heap<Long> nbrs= new Heap<>(false); // neighbors to current tile, in a HEAP

        // add the neighbors details to above HEAP!!!!!!!!!!!!!!!
        for (NodeStatus n : u.neighbors()) { nbrs.add(n.getId(), n.getDistanceToTarget()); }

        // for all the neighbors
        for (NodeStatus m : u.neighbors()) { // idk why i couldnt loop, DO NOT TOUCH!
            // Integer dist= nbrs.getVal(); // if we need the distance, i have it
            long cur_nbr= nbrs.poll(); // shortest distance to orb neighbor

            // If we havent been there before
            if (!v.contains(cur_nbr)) {
                u.moveTo(cur_nbr); // move to it
                dfswalkgreedy(u); // do it again

                if (orb) {
                    return; // exiting recur after finding orb
                } else {
                    u.moveTo(pos); // move backwards if needed
                }
            }
        }
        // if we have been to all of the neighbors
        return;
    }

    public void dfswalkworking(FindState u) {
        long x= u.currentLoc(); // pollack here
        v.add(x); // add to map of tiles pollack has been

        // go through each neighbor
        for (NodeStatus a : u.neighbors()) {
            if (!v.contains(a.getId())) { // if we havent been to this node
                // v.add(a.getId()); // save the location
                u.moveTo(a.getId()); // move to it

                if (u.distanceToOrb() == 0) // if we at the orb, nice
                    return;

                dfswalkworking(u); // otherwise, do it again

                if (u.distanceToOrb() == 0) // check again?
                    return;

                u.moveTo(x); // go back otherwise
            }
        }
    }

    /** Pres Pollack is standing at a node given by parameter state.<br>
     *
     * Get out of the cavern before the ceiling collapses, trying to collect as <br>
     * much gold as possible along the way. Your solution must ALWAYS get out <br>
     * before time runs out, and this should be prioritized above collecting gold.
     *
     * You now have access to the entire underlying graph, which can be accessed <br>
     * through parameter state. <br>
     * state.currentNode() and state.getExit() will return Node objects of interest, and <br>
     * state.allNodes() will return a collection of all nodes on the graph.
     *
     * The cavern will collapse in the number of steps given by <br>
     * state.stepsLeft(), and for each step this number is decremented by the <br>
     * weight of the edge taken. <br>
     * Use state.stepsLeft() to get the time still remaining, <br>
     * Use state.moveTo() to move to a destination node adjacent to your current node.<br>
     * Do not call state.grabGold(). Gold on a node is automatically picked up <br>
     * when the node is reached.<br>
     *
     * The method must return from this function while standing at the exit. <br>
     * Failing to do so before time runs out or returning from the wrong <br>
     * location will be considered a failed run.
     *
     * You will always have enough time to scram using the shortest path from the <br>
     * starting position to the exit, although this will not collect much gold. <br>
     * For this reason, using the shortest path method to calculate the shortest <br>
     * path to the exit is a good starting solution */
    @Override
    public void scram(ScramState state) {
        // TODO 2: scram
        scramshort(state);
    }

    public void scramshort(ScramState state) {
        if (Path.pathSum(Path.shortest(state.currentNode(), state.getExit())) >= state.stepsLeft())
            scramworking(state);
        HashSet<Node> allnodes= new HashSet<>(state.allNodes());
        HashSet<Node> goldnodes= new HashSet<>();
        for (Node n : allnodes) {
            if (n.getTile().gold() != 0)
                goldnodes.add(n);
        }
        HashMap<Integer, Node> coinpaths= new HashMap<>();
        ArrayList<Integer> distances= new ArrayList<>();
        for (Node n : goldnodes) {
            coinpaths.put(Path.pathSum(Path.shortest(state.currentNode(), n)), n);
            distances.add(Path.pathSum(Path.shortest(state.currentNode(), n)));
        }
        Collections.sort(distances);
        if (Path.pathSum(Path.shortest(state.currentNode(), coinpaths.get(distances.get(0)))) +
            Path.pathSum(Path.shortest(coinpaths.get(distances.get(0)), state.getExit())) >= state
                .stepsLeft())
            scramworking(state);
        if (state.currentNode() == state.getExit())
            return;
        move(state, coinpaths.get(distances.get(0)));
        goldnodes.remove(coinpaths.get(distances.get(0)));
        if (state.currentNode() == state.getExit())
            return;
        if (Path.pathSum(Path.shortest(state.currentNode(), coinpaths.get(distances.get(0)))) +
            Path.pathSum(Path.shortest(coinpaths.get(distances.get(0)), state.getExit())) >= state
                .stepsLeft())
            scramworking(state);
        scramshort(state);

    }

    /** Get to the exit no matter where you are on the map. Return when you reach the exit. */
    public void scramworking(ScramState state) {
        List<Node> a= Path.shortest(state.currentNode(), state.getExit());
        for (Node n : a) {
            if (state.currentNode().getNeighbors().contains(n))
                state.moveTo(n);
            if (state.currentNode() == state.getExit())
                return;
        }
    }

    /** Move from state.currentLocation() to other location on map. Return when you reach
     * location */

    public void move(ScramState state, Node location) {
        List<Node> a= Path.shortest(state.currentNode(), location);
        for (Node n : a) {
            if (state.currentNode().getNeighbors().contains(n)) {
                state.moveTo(n);
                if (state.currentNode() == location)
                    return;

            }
        }
    }

}
