import models.Tree;
import models.Type;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Tree tree = XMLSaver.getInstance().read();
        tree.normalize();
//        model.Tree tree = model.Tree.getFilledTree();
//        Saver.getInstance().save(tree);
        Interpreter.handle(tree, "add Казань,Вахитовский,Пушкина 29A HOUSE 100");
        Iterator<Tree.Node> interpreter = tree.iteratorDFS();
        Interpreter.handle(tree, "delete Удмуртия,Ижевск");
        while (interpreter.hasNext()) {
            System.out.println(interpreter.next());
        }
//        System.out.println(Interpreter.findByAddress(tree, "Удмуртия,Ижевск,Первомайский"));
        System.out.println(Type.valueOf("HOUSE"));
    }
}
