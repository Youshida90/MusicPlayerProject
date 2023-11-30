package Tree;

import java.io.File;

import Node.Node;

public class BinarySearchTree {
    public Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(String fileName, File selectedFile) {
        root = insertRec(root, fileName, selectedFile);
    }

    private Node insertRec(Node root, String fileName, File selectedFile) {
    	if (root == null) {
            root = new Node(fileName , selectedFile);
            return root;
        }

        if (fileName.compareTo(root.getData()) < 0) {
            root.setLeft(insertRec(root.getLeft(), fileName, selectedFile));
        } else if (fileName.compareTo(root.getData()) > 0) {
            root.setRight(insertRec(root.getRight(), fileName, selectedFile));
        }

        return root;
    }

    public File search(String key) {
        Node result = searchRec(root, key);
        return (result != null) ? result.getFile() : null;
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
    

    public void delete(String key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, String key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.getData()) < 0) {
            root.setLeft(deleteRec(root.getLeft(), key));
        } else if (key.compareTo(root.getData()) > 0) {
            root.setRight(deleteRec(root.getRight(), key));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            root.setData(minValue(root.getRight()));
            root.setRight(deleteRec(root.getRight(), root.getData()));
        }

        return root;
    }

    private String minValue(Node root) {
        String minv = root.getData();
        while (root.getLeft() != null) {
            minv = root.getLeft().getData();
            root = root.getLeft();
        }
        return minv;
    }
}