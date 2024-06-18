class ListNode {
    int data;
    ListNode next;
    public ListNode(int data){
        this.data = data;
        next = null;
    }
}



public class LinkedList{
    ListNode head;
    public void addElement(int data){
        if(head == null) {
            head = new ListNode(data);
        }
        else{
            ListNode p = head;
            while(true){
                if(p.next == null){
                    p.next = new ListNode(data);
                    break;
                }
                p = p.next;
            }
        }
    }

    public void display(){
        ListNode p = head;
        if(this.head == null){
            System.out.println("There are no elements in the list");
        }
        else if(head.next == null){
            System.out.println(head.data);
        }
        else{
            while(p != null){
                System.out.println(p.data);
                p = p.next;
            }
        }

    }

    public void insertAtEnd(int val){
        ListNode p = new ListNode(val);
        if(head == null){
            head = p;
        }
        else{
            ListNode temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = p;
        }
    }

    public void deleteAtPosition(int position){
//        if(head == null){
//            System.out.println("List is empty");
//        }
//        else{
//            if(head.next == null){
//                head = null;
//            }
//            else if(head.next.next == null){
//                head = head.next;
//            }
//            else{
//                ListNode current = head.next;
//                ListNode previous = head;
//                int counter = 1;
//                while(true){
//                    if(position == 0){
//                        head = current;
//                        break;
//                    }
//                    if(counter == position){
//                        previous.next = current.next;
//                        break;
//                    }
//                    current = current.next;
//                    previous = previous.next;
//                    counter++;
//                }
//            }
//
//        }

    }
    public void reverse(){

    }
    public void findMiddleElement(){

    }
    public void detectLoop(){

    }

    public static void main(String[] args){
        LinkedList myList = new LinkedList();

        myList.addElement(10);
//        myList.addElement(20);
//        myList.addElement(30);
//        myList.addElement(40);
//        myList.addElement(50);
//        myList.insertAtEnd(60);
        myList.deleteAtPosition(2);
        myList.display();
    }
}