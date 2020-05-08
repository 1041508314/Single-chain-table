/**
 * @program: Single chain table
 * @description
 * @author: LiuXinYu
 * @create: 2020-05-02 09:44
 **/
public class TestDemo {

    //将两个单链表交在一起
    public static void createCut(Node headA , Node headB){
        headA.next = headB.next.next;
    }

    //求两个单链表相交的点
    //1.求两个单链表的长度
    //2.计算两个单链表的长度的差
    //3.让长的单链表 先走差值步
    //两个链表 所以不能放在一个链表的方法中
    public  static Node getIntersectionNode(Node headA , Node headB){
        int lenA = 0;
        int lenB = 0;
        Node pl = headA;
        Node ps = headB;
        while(pl != null){
            lenA++;
            pl = pl.next;
        }
        while (ps != null){
            lenB++;
            ps = ps.next;
        }
        pl = headA;
        ps = headB;
        int len = lenA - lenB;
        if(len < 0){
            pl = headB;
            ps = headA;
            len = lenB - lenA;
        }
        //一定是pl指向的是最长的单链表
        for (int i = 0; i < len; i++) {
            pl = pl.next;
        }
        while(ps != pl && pl != null && ps != null){
            ps = ps.next;
            pl = pl.next;
        }
        if(pl == ps && pl != null && ps != null){
            return pl;
        }
        return null;
    }


    //将两个升序链表合并为一个新的升序链表并返回 新链表是通过拼接给党的两个链表的所有节点组成的
    public static Node mergeTwoLists(Node headA , Node headB){
        Node newHead = new Node(-1);
        Node tmp = newHead;
        while(headA != null && headB != null){
            if(headA.data < headB.data){
                tmp.next = headA;
                headA = headA.next;
                tmp = tmp.next;
            }else{
                tmp.next = headB;
                headB = headB.next;
                tmp = tmp.next;
            }
        }
        if(headA != null){
            tmp.next = headA;
        }
        if(headB != null){
            tmp.next = headB;
        }
        return newHead.next;
    }



    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(10);
        myLinkedList.addLast(21);
        myLinkedList.addLast(32);
        myLinkedList.addLast(40);
        myLinkedList.addLast(58);
        myLinkedList.addLast(66);
        myLinkedList.addLast(76);
        myLinkedList.addLast(89);
        myLinkedList.display();
////        boolean flg = myLinkedList.contains(11);
////        System.out.println(flg);
////        System.out.println(myLinkedList.contains(11));
////        System.out.println(myLinkedList.size());
////        myLinkedList.addIndex(2,10);
////        myLinkedList.display();
//        myLinkedList.removeAllKey(14);
//        myLinkedList.display();
//        myLinkedList.display();
//        Node ret = myLinkedList.reverseList();
//        myLinkedList.display2(ret);
//        System.out.println(myLinkedList.middleNode().data);
//        System.out.println(myLinkedList.FindKthToTail(4).data);
//        Node ret = myLinkedList.partition(5);
//
        MyLinkedList myLinkedList2 = new MyLinkedList();
        myLinkedList2.addLast(9);
        myLinkedList2.addLast(23);
        myLinkedList2.addLast(33);
        myLinkedList2.addLast(42);
        myLinkedList2.addLast(51);
        myLinkedList2.addLast(64);
        myLinkedList2.addLast(75);
        myLinkedList2.addLast(81);
        myLinkedList2.display();
//        createCut(myLinkedList.head , myLinkedList2.head);
//        Node ret = getIntersectionNode(myLinkedList.head , myLinkedList2.head);
//        System.out.println(ret.data);
        Node ret = mergeTwoLists(myLinkedList.head , myLinkedList2.head);
        myLinkedList.display2(ret);
    }

}
