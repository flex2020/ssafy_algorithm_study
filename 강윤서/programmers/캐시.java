class Solution {
    static class Node {
        String data;
        Node next;
        Node (String data) {
            this.data = data;
            this.next = null;
        }
    }
    static class LinkedList {
        Node head;
        Node tail;
        int size;
        int capacity;
        LinkedList(int capacity) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            this.capacity = capacity;
        }
        public boolean find(String data) {
            Node cur = this.head;
            while (cur != null) { // tail까지
                if (cur.data.equals(data)) { 
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }
        public void insert(String data) {
            Node newNode = new Node(data);
            if (this.head == null) { // 캐시가 비어 있음
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                this.tail = newNode;
            }
            this.size++;
            if (this.size > this.capacity) { // 가장 오래된 노드 삭제
                this.head = this.head.next;
                this.size--;
            }
        }
        public boolean delete(String data) {
            Node cur = this.head;
            Node prev = null;
            while (cur != null) {
                if (cur.data.equals(data)) {
                    if (prev == null) { // cur: head
                        this.head = cur.next;
                    } else if (cur.next == null) { // cur: tail
                        prev.next = null;
                        this.tail = prev;
                    } else { // cur: 중간
                        prev.next = cur.next;
                    }
                    this.size--;
                    return true;
                }
                prev = cur;
                cur = cur.next;
            }
            return false;
        }
    }
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList cache = new LinkedList(cacheSize);
        for (String city : cities) {
            String lowerCaseCity = city.toLowerCase();
            if (cache.find(lowerCaseCity)) { // 캐시 안에 존재
                answer += 1;
                cache.delete(lowerCaseCity);
                cache.insert(lowerCaseCity);
            } else { // 캐시 안에 존재 X
                answer += 5;
                cache.insert(lowerCaseCity);
            }
            
        }
        return answer;
    }
}
