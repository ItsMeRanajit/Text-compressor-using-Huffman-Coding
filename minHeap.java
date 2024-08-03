package textEndcoderDecoder;

import java.util.ArrayList;

public class minHeap<T extends Comparable<T>> {

    private ArrayList<T> list = new ArrayList<>();

    public int size() {
        return this.list.size();
    }

    public void create(T[] arr) throws Exception {
        for (T i : arr)
            insert(i);
        System.out.println(list);
        removeHeap();
        System.out.println(list);
        heapSort();
    }

    public void insert(T num) {
        list.add(num);
        int idx = list.size() - 1;
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (list.get(parent).compareTo(list.get(idx)) > 0) {
                swap(parent, idx);
                idx = parent;
            } else
                return;

        }
    }

    public T removeHeap() throws Exception {
        if (list.isEmpty())
            throw new Exception("cannot perform");
        T first = list.get(0);
        T last = list.remove(list.size() - 1);
        if (!list.isEmpty()) {
            list.set(0, last);
            int idx = 0;
            while (idx < list.size()) {
                int left = idx * 2 + 1;
                int right = idx * 2 + 2;
                int smaller = idx;
                if (left < list.size() && list.get(left).compareTo(list.get(idx)) < 0)
                    smaller = left;
                if (right < list.size() && list.get(right).compareTo(list.get(smaller)) < 0)
                    smaller = right;
                if (idx < list.size() && idx != smaller) {
                    swap(idx, smaller);
                    idx = smaller;
                } else
                    break;
            }
        }
        return first;
    }

    private void heapSort() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("cannot perform heapsort");
        }
        ArrayList<T> arr = new ArrayList<>();
        while (!list.isEmpty()) {
            arr.add(removeHeap());
        }
        System.out.println(arr);
    }

    private void swap(int firstidx, int secondidx) {
        T temp = list.get(firstidx);
        list.set(firstidx, list.get(secondidx));
        list.set(secondidx, temp);
        return;
    }
}
