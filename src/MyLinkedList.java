/**
 * @program: Single chain table
 * @description
 * @author: LiuXinYu
 * @create: 2020-05-02 10:08
 **/

class Node{
    public int data;//默认值为0
    public Node next;//默认值为null

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}
public class MyLinkedList {
    public Node head;//保存单链表的头节点的引用

    //单链表的插入时 要先邦后面


    //头插法
    public void addFirst(int data) {
        Node node = new Node(data);

        if (this.head == null) {
            //第一次插入节点
            this.head = node;
            return;
        }
        node.next = this.head;
        head = node;
    }


    //尾插法
    public void addLast(int data) {
        Node node = new Node(data);

        if (this.head == null) {
            this.head = node;
            return;
        }

        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }


    //任意位置插入，第一个数据节点为0号下标
    public void addIndex(int index, int data) {//1.定义一个cur走index-1步 2.插入
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == this.size()) {
            addLast(data);
            return;
        }
        Node node = new Node(data);
        //先找到index位置的前一个节点的地址
        Node cur = searchIndex(index);
        //进行插入
        node.next = cur.next;
        cur.next = node;
    }

    private Node searchIndex(int index) {
        //1.对index进行合法性检查
        if (index < 0 || index > this.size()) {
            throw new RuntimeException("index位置不合法！");
        }
        Node cur = this.head;
        while (index - 1 != 0) {
            cur = cur.next;
            index--;
        }
        return cur;
    }


    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    private Node searchPrev(int key) {
        Node prev = this.head;
        while (prev.next != null) {
            if (prev.next.data == key) {
                return prev;
            } else {
                prev = prev.next;
            }
        }
        return null;
    }


    //删除第一次出现的关键字为key的节点
    public void remove(int key) {
        if (this.head == null) {
            return;
        }
        //删除的不是头节点
        if (this.head.data == key) {
            this.head = this.head.next;
            return;
        }
        //找到删除节点的前驱
        Node prev = searchPrev(key);
        if (prev == null) {
            System.out.println("根本没有这个节点!");
            return;
        }
        //开始删除
        Node del = prev.next;
        prev.next = del.next;
    }


    //删除所有值为key的节点
    public void removeAllKey(int key) {
        Node prev = this.head;
        Node cur = this.head.next;//代表要删除的节点
        while (cur != null) {
            if (cur.data == key) {
                prev.next = cur.next;
                cur = cur.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        if (this.head.data == key) {
            this.head = this.head.next;
        }

    }


    //得到单链表的长度
    public int size() {
        int count = 0;
        Node cur = this.head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }


    //打印单链表
    public void display() {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    //清空链表

    /**
     * 释放内存
     */
    public void clear() {
        this.head = null;
    }


    //反转链表
    public Node reverseList() {
        Node cur = this.head;
        Node prev = null;
        Node newHead = null;
        while (cur != null) {
            Node curNext = cur.next;
            if (curNext == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }

    public void display2(Node newHead) {
        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    //找链表的中间节点
    public Node middleNode() {
        Node slow = this.head;
        Node fast = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    //找出单链表倒数第k个节点
    public Node FindKthToTail(int k) {
        if (head == null) {
            return null;
        }
        if (k <= 0) {
            System.out.println("k不合法!");
            return null;
        }
        Node fast = this.head;
        Node slow = this.head;
        while (k - 1 > 0) {
            if (fast.next != null) {
                fast = fast.next;
                k--;
            } else {
                System.out.println("没有这个节点!");
                return null;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    //给定一个数值 单链表中比这个数值小的放在前面 大于等于它的放在后面 单链表中的数字顺序不变
    public Node partition(int x) {
        Node bs = null;
        Node be = null;
        Node as = null;
        Node ae = null;
        Node cur = this.head;
        while (cur != null) {
            if (cur.data < x) {
                //第一次插入
                if (bs == null) {
                    bs = cur;
                    be = cur;
                } else {
                    //不是第一次插入
                    be.next = cur;
                    be = be.next;
                }
            } else {
                //第一次插入
                if (as == null) {
                    as = cur;
                    ae = cur;
                } else {
                    //不是第一次插入
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        //1.判断bs是否为空  如果bs == null  返回as
        if (bs == null) {
            return as;
        }
        //2.如果bs不为空  需要进行拼接
        be.next = as;
        //3.如果ae不为空  ae的next需要置为空
        if (ae != null) {
            ae.next = null;
        }
        return bs;
    }

    //在一个排序的链表中 存在重复的节点 删除该表中重复的节点 重复的节点不保留 返回链表头指针
    public Node deleteDuplication() {
        Node cur = this.head;
        Node newHead = new Node(-1);
        Node tmp = newHead;
        while (cur != null) {
            if (cur.next != null && cur.data == cur.next.data) {
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                cur = cur.next;//多走一步
            } else {
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        tmp.next = null;
        return newHead.next;
    }


    //链表的回文结构
    public boolean chkPalindrome() {

        //单链表为空 肯定不是回文结构
        if (this.head == null) {
            return false;
        }

        //这是只有头节点自己  必然是回文结构
        if (this.head.next == null) {
            return true;
        }

        //1.先找到单链表的中间节点
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //2.开始反转单链表的后半部分  上边走完之后 slow肯定在中间位置
        Node cur = slow.next;
        while (cur != null) {
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }//此时slow已经是最后一个节点了

        //3.一个从头走 一个从尾走
        //head和slow相遇
        while (slow != this.head) {
            if (slow.data != this.head.data) {
                return false;
            }

            if (this.head.next == slow) {
                return true;
            }//链表中有偶数个数字 判断偶数时需要这个判断

            slow = slow.next;
            this.head = this.head.next;
        }
        return true;
    }


    //判断单链表中是否存在环
    //方法1
    public boolean hasCycle1() {
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //方法2
    public boolean hasCycle2() {
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return false;
        }
        return true;
    }


    //如果单链表有环 返回入口点的地址
    public Node detectCycle() {
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = this.head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}




