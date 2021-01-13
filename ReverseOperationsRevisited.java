import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


/**
 * Reverse Operations
 * https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=623634548182866
 */


/**
 * Node class to implement linked list.
 */
class Node {

    int data;
    Node next;

    // **** ****
    @Override
    public String toString() {
        return this.data + " ";
    }
}


/**
 * 
 */
public class ReverseOperationsRevisited {


    /**
     * Given a resulting list (head), 
     * determine the original order of the elements.
     * Will reverse all elements using a recursive approach.
     * 
     * !!! NOT PART OF THE SOLUTION !!!!
     */
    static Node reverse1(Node head) {

        // **** base case ****
        if (head == null || head.next == null)
            return head;

        // **** recursive case (set head for reversed list) ****
        Node revHead = reverse1(head.next);

        // ???? ????
        // System.out.println("reverse1 <<< newHead: " + revHead.data + " head: " + head.data);

        // **** point to the next element in the reversed linked list ****
        head.next.next = head;

        // **** tail node of reversed linked list ****
        head.next = null;

        // **** return reversed linked list head ****
        return revHead;
    }


    /**
     * Given a resulting list (head), 
     * determine the original order of the elements.
     * Will reverse all elements using a stack.
     * 
     * !!! NOT PART OF THE SOLUTION !!!!
     */
    static Node reverse2(Node head) {

        // **** sanity check(s) ****
        if (head == null || head.next == null)
            return head;

        // **** initialization ****
        Node revHead        = null;
        Node revTail        = null;
        Stack<Node> stack   = new Stack<>();

        // **** push all nodes into the stack ****
        for (Node p = head; p != null;p = p.next)
            stack.push(p);

        // ???? ????
        // System.out.println("reverse2 <<< stk: " + stk.toString());

        // **** pop the stack building the reversed linked list ****
        while (!stack.isEmpty()) {

            // **** pop next node ****
            Node node = stack.pop();

            // **** reset next reference ****
            node.next = null;

            // **** set new head ****
            if (revHead == null)
                revHead = node;
            else
                revTail.next = node;

            // **** update the reversed queue tail ****
            revTail = node;
        }

        // **** return the reversed linked list head ****
        return revHead;
    }


    /**
     * Given a resulting list (head), 
     * determine the original order of the elements.
     * Will reverse only even segments.
     */
    static Node reverse3(Node head) {

        // **** sanity check(s) ****
        if (head == null || head.next == null)
            return head;

        // **** initialization ****
        Node revHead        = null;
        Node revTail        = null;
        Stack<Node> stack   = new Stack<>();

        // **** traverse the linked list ****
        for (Node p = head; p != null; p = p.next) {

            // ???? ????
            // System.out.println("<<< p: " + p.data);

            // **** even node ****
            if (p.data % 2 == 0) {
                stack.push(p);
            } 
            
            // **** odd node ****
            else {

                // **** pop all nodes from the stack ****
                while (!stack.isEmpty()) {

                    // **** pop next node ****
                    Node node = stack.pop();

                    // **** reset next reference ****
                    node.next = null;

                    // **** set new head ****
                    if (revHead == null)
                        revHead = node;
                    else
                        revTail.next = node;

                    // **** update the reversed queue tail ****
                    revTail = node;
                }

                // **** set new head ****
                if (revHead == null)
                    revHead = p;
                else
                    revTail.next = p;

                // **** update the reversed queue tail ****
                revTail = p;
            }
        }

        // **** pop all nodes from the stack ****
        while (!stack.isEmpty()) {

            // **** pop next node ****
            Node node = stack.pop();

            // **** reset next reference ****
            node.next = null;

            // **** set new head ****
            if (revHead == null)
                revHead = node;
            else
                revTail.next = node;

            // **** update the reversed queue tail ****
            revTail = node;
        }

        // **** return head of reversed linked list ****
        return revHead;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read String[] with values for the nodes in the linked list ****
        String[] strArr = br.readLine().trim().split(", ");

        // **** closed buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<  strArr: " + Arrays.toString(strArr));
        
        // **** initialize linked list ****
        Node head = null;
        Node tail = head;

        // **** populate linked list (FILO) ****
        for (String s : strArr) {

            Node node   = new Node();
            node.data   = Integer.parseInt(s);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

        }

        // ???? ????
        System.out.print("main <<<    head: ");
        for (Node p = head; p != null; p = p.next) {
            System.out.print(p.data);
            if (p.next != null)
                System.out.print(", ");
        }
        System.out.println();

        // **** reverse the entire linked list ****
        Node revHead = reverse1(head);

        // **** display output list ****
        System.out.print("main <<< revHead: ");
        for (Node p = revHead; p != null; p = p.next) {
            System.out.print(p.data);
            if (p.next != null)
                System.out.print(", ");
        }
        System.out.println();

        // **** restore initial linked list ****
        head = reverse1(revHead);

        // ???? ????
        System.out.print("main <<<    head: ");
        for (Node p = head; p != null; p = p.next) {
            System.out.print(p.data);
            if (p.next != null)
                System.out.print(", ");
        }
        System.out.println();

        // **** reverse the linked list ****
        revHead = reverse2(head);

        // **** display output list ****
        System.out.print("main <<< revHead: ");
        for (Node p = revHead; p != null; p = p.next) {
            System.out.print(p.data);
            if (p.next != null)
                System.out.print(", ");
        }
        System.out.println();

        // **** restore initial linked list ****
        head = reverse1(revHead);

        // ???? ????
        System.out.print("main <<<    head: ");
        for (Node p = head; p != null; p = p.next) {
            System.out.print(p.data);
            if (p.next != null)
                System.out.print(", ");
        }
        System.out.println();

        // **** reverse the linked list ****
        revHead = reverse3(head);

        // **** display output list ****
        System.out.print("main <<< revHead: ");
        for (Node p = revHead; p != null; p = p.next) {
            System.out.print(p.data);
            if (p.next != null)
                System.out.print(", ");
        }
        System.out.println();
    }
}