package Tree;

import Node.Node;
import Project.Song;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(String key, Song song) {
        root = insertRec(root, key, song);
    }

    private Node insertRec(Node root, String key, Song song) {
        if (root == null) {
            return new Node(key, song);
        }

        if (key.compareTo(root.getData()) < 0) {
            root.setLeft(insertRec(root.getLeft(), key, song));
        } else if (key.compareTo(root.getData()) > 0) {
            root.setRight(insertRec(root.getRight(), key, song));
        }

        return root;
    }

    public Song search(String key) {
        Node result = searchRec(root, key);
        return (result != null) ? result.getSong() : null;
    }

    private Node searchRec(Node root, String key) {
        if (root == null || root.getData().equals(key)) {
            return root;
        }

        if (key.compareTo(root.getData()) < 0) {
            return searchRec(root.getLeft(), key);
        } else {
            return searchRec(root.getRight(), key);
        }
    }
}
