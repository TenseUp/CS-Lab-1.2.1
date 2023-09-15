import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {

    private T[] array;
    private int size = 0;

    public DynamicArray(Class<T> type) {
        array = (T[]) Array.newInstance(type, 10);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        if (size == array.length) {
            grow();
        }
        array[size++] = element;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        if (size == array.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;
        size++;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        array[index] = element;
    }

    public T get(int index) {
        return array[index];
    }

    public T remove(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }

        T removed = array[i];

        for (int z = i; z < size - 1; z++) {
            array[z] = array[z + 1];
        }

        array[size - 1] = null;
        size--;

        return removed;
    }

    public T remove(T x) {
        int i = indexOf(x);
        if (i == -1) {
            return null;
        }

        return remove(i);
    }

    public T removeAll(T x) {
        T rem = null;

        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                remove(i);
                rem = x;

                i--;
            }
        }

        return rem;
    }

    public boolean contains(T x) {
        return indexOf(x) != -1;
    }

    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    private int indexOf(T x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length * 2);
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}