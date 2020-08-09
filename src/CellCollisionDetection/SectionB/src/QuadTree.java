/**
 * You must implement the <code>add</code> and <code>queryRegion</code>
 * methods in the
 * region-based QuadTree class given below.
 */


import java.awt.*;
import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

/**
 * A region-based quadtree implementation.
 */
public class QuadTree implements QuadTreeInterface {

    private QuadTreeNode root;
    private int nodeCapacity;

    /**
     * Default constructor.
     *
     * @param region   The axis-aligned bounding region representing the given
     *                 area that the current
     *                 quadtree covers
     * @param capacity The maximum number of objects each quadtree node can
     *                 store. If a quadtree
     *                 node's number of stored objects exceeds its capacity,
     *                 the node should be subdivided.
     */
    public QuadTree(AABB region, int capacity) {
        root = new QuadTreeNode(region);
        nodeCapacity = capacity;
    }

    /**
     * <p> Implement this method for Question 2 </p>
     * <p>
     * Adds a 2D-object with Cartesian coordinates to the tree.
     *
     * @param elem the 2D-object to add to the tree.
     */
    public void add(Object2D elem) {
        addHelper(root, elem);
    }

    /**
     * <p> Implement this method for Question 2 </p>
     *
     * @param elem the 2D-object to add to the tree.
     * @param node the root of the current subtree to visit
     */
    private QuadTreeNode addHelper(QuadTreeNode node, Object2D elem) {
        if (node.isLeaf()) {
            if (nodeCapacity > node.values.size()) {
                node.values.add(node.values.size(), elem);
                return node;
            }
            node.subdivide();
            ListInterface<Object2D> values = node.values;
            node.values = new ListArrayBased<>();
            for (int i = 0; i < values.size(); i++) {
                addHelper(node, values.get(i));
            }
            return addHelper(node, elem);
        }

        if (node.NE.region.covers(elem.getCenter())) {
           return addHelper(node.NE, elem);
        }
        else if (node.NW.region.covers(elem.getCenter())) {
            return addHelper(node.NE, elem);
        }
        else if (node.SE.region.covers(elem.getCenter())) {
            return addHelper(node.NE, elem);
        } else {
            return addHelper(node.NE, elem);
        }
    }

    /**
     * <p> Implement this method for Question 3 </p>
     * <p>
     * Given an axis-aligned bounding box region, it returns all the 2D-objects
     * in the quadtree that are within the region.
     *
     * @param region axies-aligned bounding box region
     * @return a list of 2D-objects
     */
    public ListInterface<Object2D> queryRegion(AABB region) {
        ListInterface<Object2D> bucket = new ListArrayBased<>();
        queryRegionHelper(root, region, bucket);
        return bucket;
    }

    /**
     * <p> Implement this method for Question 3 </p>
     * <p>
     * Auxiliary method that recursively goes down from the root of the tree
     * through all * the
     * children whose regions overlap with the given region. When a leaf node
     * is reached, then only
     * the 2D-objects stored at this leaf node that are covered by the given
     * region are collected.
     *
     * @param region axies-aligned bounding box region
     * @param node   the root of the current subtree to visit
     */
    private void queryRegionHelper(QuadTreeNode node, AABB region,
                                   ListInterface<Object2D> bucket) {
        if (node.isLeaf()){
            int bucketSize = bucket.size();
            ListInterface<Object2D> values = node.values;
            for (int i = 0; i < values.size(); i++){
                bucket.add(bucketSize + i, values.get(i));
            }
        }
        if (overlapping(node.NE, region)){
            queryRegionHelper(node.NE, region, bucket);
        }
        if (overlapping(node.NW, region)){
            queryRegionHelper(node.NW, region, bucket);
        }
        if (overlapping(node.SW, region)){
            queryRegionHelper(node.SW, region, bucket);
        }
        if (overlapping(node.SE, region)){
            queryRegionHelper(node.SE, region, bucket);
        }
    }

    private boolean overlapping(QuadTreeNode node, AABB region){

        ArrayList<Point2D> points = new ArrayList<Point2D>();
        points.add(region.topLeft());
        points.add(region.bottomLeft());
        points.add(region.topRight());
        points.add(region.bottomRight());
        for (Point2D point : points){
            if (node.region.covers(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a 2D-object is in the tree.
     *
     * @param elem the 2D-object to search for in the tree.
     */
    public boolean contains(Object2D elem) {
        return containsHelper(root, elem);
    }


    /**
     * @param elem the 2D-object to search for in the tree.
     */
    private boolean containsHelper(QuadTreeNode node, Object2D elem) {
        if (node.isLeaf()) {
            return node.values.contains(elem);
        } else {
            if (node.NE.region.covers(elem.getCenter())) {
                return containsHelper(node.NE, elem);
            } else if (node.NW.region.covers(elem.getCenter())) {
                return containsHelper(node.NW, elem);
            } else if (node.SE.region.covers(elem.getCenter())) {
                return containsHelper(node.SE, elem);
            } else {
                return containsHelper(node.SW, elem);
            }
        }
    }
}
