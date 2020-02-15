import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tree {
    Node root;
    List<Node> children;

    public Node getRoot() {
        return root;
    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(value = {"parent"})
    public static class Node {
        List<Node> children;
        String name;
        Node parent;
        Type type;
        int priority;

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", priority=" + priority +
                    '}';
        }

        public Node(String name, Node parent, Type type, int priority) {
            children = new ArrayList<>();
            this.name = name;
            this.parent = parent;
            this.type = type;
            this.priority = priority;
        }

        public Node() {

        }

        public static Builder newBuilder() {
            return (new Node()).new Builder();
        }

        public void setParent(Node node) {
            this.parent = node;
        }

        //Builder
        class Builder {

            private Builder() {
            }

            public Builder setName(String name) {
                Node.this.name = name;
                return this;
            }

            public Builder setParent(Node parent) {
                Node.this.parent = parent;
                return this;
            }

            public Builder setType(Type type) {
                Node.this.type = type;
                return this;
            }

            public Builder setPriority(int priority) {
                Node.this.priority = priority;
                return this;
            }

            public Node build() {
                Node.this.children = new ArrayList<>();
                return Node.this;
            }
        }


        public void add(Node node) {
            children.add(node);
            node.parent = this;
        }
    }


    public Tree(Node root) {
        this.root = root;
        children = new ArrayList<>();
    }


    public static Tree getFilledTree() {
        //Tataria
        Tree tree = new Tree(Node.newBuilder()
                .setName("Россия")
                .setType(Type.COUNTRY)
                .setParent(null)
                .setPriority(100)
                .build());
        Node tatarstan = Node.newBuilder()
                .setName("Татарстан")
                .setPriority(100)
                .setType(Type.REGION).build();
        Node kazan = Node.newBuilder()
                .setName("Казань")
                .setPriority(100)
                .setType(Type.CITY).build();
        Node vakhitovski = Node.newBuilder()
                .setName("Вахитовский")
                .setPriority(200)
                .setType(Type.DISTRICT)
                .build();
        Node universitetskaya = Node.newBuilder()
                .setName("Университетская")
                .setType(Type.STREET)
                .setPriority(200)
                .build();
        Node pushkina = Node.newBuilder()
                .setName("Пушкина")
                .setType(Type.STREET)
                .setPriority(100)
                .build();
        Node h18 = Node.newBuilder()
                .setName("18")
                .setPriority(200)
                .setType(Type.HOUSE).build();
        Node h32 = Node.newBuilder()
                .setName("32")
                .setType(Type.HOUSE)
                .setPriority(300).build();
        pushkina.add(h32);
        universitetskaya.add(h18);
        vakhitovski.add(pushkina);
        vakhitovski.add(universitetskaya);
        kazan.add(vakhitovski);
        tatarstan.add(kazan);
        tree.root.add(tatarstan);
        //Moscow
        Node moscow = Node.newBuilder()
                .setName("Москва")
                .setPriority(100)
                .setType(Type.REGION).build();

        Node moscowC = Node.newBuilder()
                .setName("Москва")
                .setPriority(100)
                .setType(Type.REGION).build();

        Node re = Node.newBuilder()
                .setName("Район")
                .setType(Type.DISTRICT)
                .setPriority(100)
                .build();

        Node arbat = Node.newBuilder()
                .setName("Арбат")
                .setPriority(400)
                .setType(Type.STREET).build();

        Node h20 = Node.newBuilder()
                .setName("20")
                .setType(Type.HOUSE)
                .build();

        arbat.add(h20);
        re.add(arbat);
        moscowC.add(re);
        moscow.add(moscowC);
        tree.root.add(moscow);
        //Ebanaya udmurtia
        Node udmurtia = Node.newBuilder()
                .setName("Удмуртия")
                .setPriority(100)
                .setType(Type.REGION).build();
        Node izhevsk = Node.newBuilder()
                .setName("Ижевск")
                .setType(Type.CITY)
                .setPriority(100)
                .build();
        Node pervomayski = Node.newBuilder()
                .setName("Первомайский")
                .setType(Type.DISTRICT)
                .setPriority(100)
                .build();
        Node let40pobedi = Node.newBuilder()
                .setName("40 лет победы")
                .setType(Type.STREET)
                .setPriority(200)
                .build();
        Node h140 = Node.newBuilder()
                .setName("140")
                .setType(Type.HOUSE)
                .setPriority(200)
                .build();
        let40pobedi.add(h140);
        pervomayski.add(let40pobedi);
        izhevsk.add(pervomayski);
        udmurtia.add(izhevsk);
        tree.root.add(udmurtia);
        return tree;
    }

    public Iterator<Node> iteratorDFS() {
        return new IteratorDFS();
    }

    public Iterator<Node> iteratorBFS() {
        return new IteratorBFS();
    }

    public Iterator<Node> iteratorBFSP() {
        return new IteratorBFSP();
    }

    class IteratorBFS implements Iterator<Node> {

        Node current;
        Deque<Node> deque = new ArrayDeque<>();

        public IteratorBFS() {
            this.current = root;
            deque.addAll(current.children);
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Node next() {
            Node node = deque.pop();
            if (!node.children.isEmpty()) {
                node.children.forEach(o -> deque.addLast(o));
            }
            return node;
        }
    }

    class IteratorDFS implements Iterator<Node> {

        Node current;
        Deque<Node> deque = new ArrayDeque<>();

        public IteratorDFS() {
            this.current = root;
            deque.addAll(current.children);
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Node next() {
            Node node = deque.pop();
            if (!node.children.isEmpty()) {
                node.children.forEach(o -> deque.push(o));
            }
            return node;
        }
    }

    class IteratorBFSP implements Iterator<Node> {

        Node current;
        Deque<Node> deque = new ArrayDeque<>();

        Comparator<Node> comparator = (o1, o2) -> o2.priority - o1.priority;

        public IteratorBFSP() {
            this.current = root;
            deque.addAll(current.children.stream().sorted(Comparator.comparingInt(o -> o.priority)).collect(Collectors.toList()));
            deque.push(root);
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Node next() {
            Node node = deque.pop();
            if (!node.children.isEmpty()) {
                node.children.stream().sorted(comparator).forEachOrdered(o -> deque.addLast(o));
            }
            return node;
        }
    }


}
