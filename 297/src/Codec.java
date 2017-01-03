import java.util.Collections;
import java.util.LinkedList;

public class Codec {
    private final static String N = "N";
    private final static String SPLITER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "{}";
        StringBuilder sb = new StringBuilder("{");
        buildContents(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    private void buildContents(StringBuilder sb, TreeNode node) {
        if (node == null) sb.append(N);
        else sb.append(node.val);
        sb.append(SPLITER);
        if (node == null) return;
        buildContents(sb, node.left);
        buildContents(sb, node.right);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int length = data.length();
        String content = data.substring(1, length - 1);
        if (content.isEmpty()) return null;
        String[] nodesString = content.split(SPLITER);
        LinkedList<String> queue = new LinkedList<>();
        Collections.addAll(queue, nodesString);
        return buildNode(queue);
    }

    private TreeNode buildNode(LinkedList<String> queue) {
        String elem = queue.remove();
        if (elem.equals(N)) return null;
        TreeNode node = new TreeNode(Integer.valueOf(elem));
        node.left = buildNode(queue);
        node.right = buildNode(queue);
        return node;
    }
}