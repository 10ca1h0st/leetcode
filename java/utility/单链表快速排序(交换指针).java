/**
 * 快排(改变链接)
 * @param head
 * @return
 */
ListNode* sortList(ListNode* head) {
    ListNode dummyHead(0);
    dummyHead.next=head;
    quickSort(&dummyHead, head, NULL);
    return dummyHead.next;
}
void quickSort(ListNode* dummyHead, ListNode* head, ListNode* tail) {
    if(head!=tail) {
        ListNode *pivot = Partation(dummyHead, head);
        quickSort(dummyHead, dummyHead->next, pivot);
        quickSort(pivot, pivot->next, tail);
    }
};

ListNode* Partation(ListNode* dummyHead, ListNode* head) {
    int pivot = head->val; // 选第一个节点为枢椎
    ListNode nodeL(0), nodeR(0);
    ListNode* pleft = &nodeL,* pright = &nodeR,* p = head->next;
    while (p) {
        if (p->val < pivot) {
            pleft->next = p;
            pleft = p;
        } else {
            pright->next = p;
            pright = p;
        }
        p=p->next;
    }
    // 大于枢椎的链表连接尾部NULL
    pright->next = NULL;
    // 小于枢椎的链表连接head
    pleft->next = head;
    // head链接大于枢椎的链表第一个节点
    head->next = nodeR.next;
    // 更新实际返回链表的头节点指向
    dummyHead->next = nodeL.next; // 链表头节点
    return head;
};