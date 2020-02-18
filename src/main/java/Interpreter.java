import model.Tree;
import model.Type;
import parser.JsonParser;

import java.util.Iterator;

public class Interpreter {

    public static void handle(Tree tree, String query) {
        if (query.startsWith("return children")) {
            getChildren(tree, query);
        } else if (query.startsWith("add ")) {
            add(tree, query);
        } else if (query.startsWith("delete ")) {
            delete(tree, query);
        } else if (query.startsWith("save")) {
            save(tree);
        }
    }

    private static void save(Tree tree) {
        Saver.getInstance().save(tree);

    }

    private static void delete(Tree tree, String query) {
        String[] words = query.split(" ");
        Tree.Node toDelete = findByAddress(tree, words[1]);
        if (toDelete != null)
            toDelete.parent.children.remove(toDelete);
        else System.out.println("Адрес не найден");
    }

    private static void add(Tree tree, String query) {
        String[] words = query.split(" ");
        Tree.Node parent = findByAddress(tree, words[1]);
        Type type = null;
        try {
            type = Type.valueOf(words[3]);
        } catch (IllegalArgumentException e) {
//            System.out.println("Wrong type of model.Type:)");
        }
        if (type != null && parent != null)
            parent.add(Tree.Node.newBuilder()
                    .setName(words[2])
                    .setType(type)
                    .setPriority(Integer.parseInt(words[4])).build());
        else
            System.out.println("Введены неверные параметры.");

    }


    private static Tree.Node recursion(Tree.Node node, String[] address, int index) {
        if (node.children.isEmpty() || address[address.length - 1].equals(node.name)) {
            return node;
        }
        if (address.length - 1 < index) return null;
        for (Tree.Node child : node.children) {
            if (child.name.equals(address[index])) {
                return recursion(child, address, ++index);
            }
        }
        return null;
    }

    public static Tree.Node findByAddress(Tree tree, String string) {
        String[] address = string.split(",");
        Iterator<Tree.Node> iterator = tree.iteratorBFS();
        while (iterator.hasNext()) {
            Tree.Node node = iterator.next();
            if (node.name.equals(address[0])) {
                Tree.Node toReturn = recursion(node, address, 1);
                if (toReturn != null) {
                    return toReturn;
                }
            }

        }
        return null;
    }

    private static void getChildren(Tree tree, String query) {
        String[] words = query.split(" ");
        Type type = Type.valueOf(words[2]);
        String name = words[3];
        Iterator<Tree.Node> iterator = tree.iteratorDFS();
        while (iterator.hasNext()) {
            Tree.Node node = iterator.next();
            if (node.type.equals(type) && name.equals(node.name)) {
                System.out.println(node.children);
            }
        }
    }
}
