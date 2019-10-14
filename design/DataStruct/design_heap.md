
```java
public int[] HeapSort(int[] arr) {
    if (arr==null || arr.length==0) return arr;
    return sort(arr);
}

private int[] sort(int[] arr) {
    buildMaxHeap(arr);
    for (int i=arr.length-1; i>0; i--) {
        this.swap(arr, 0, i);
        maxHeapify(arr, 0, i);
    }
    return arr;
}
private void buildMaxHeap(int[] arr) {
    int iParent = Math.floor(arr.length/2)-1;
    for (int i=iParent; i>=0; i--) {
        maxHeapify(arr, i, arr.length);
    }
}
private void maxHeapify(int[] arr, int index, int heapSize) {
    int iMax, iLeft, iRight;
    while (true) {
        iMax = index;
        iLeft = 2*index+1;
        iRight = 2*(index+1);
        if (iLeft<heapSize && arr[index]<arr[iLeft]) iMax=iLeft;
        if (iRight<heapSize && arr[index]<arr[iRight]) iMax=iRight;
        if (iMax != index) {
            swap(arr, iMax, index);
            index = iMax;
        } else {
            break;
        }
    }
}
private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}
```