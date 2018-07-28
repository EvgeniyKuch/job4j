package ru.job4j.generics;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> array;

    public AbstractStore(int capacity) {
        this.array = new SimpleArray<>(capacity);
    }

    @Override
    public void add(T model) {
        this.array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = searchIndex(id);
        boolean result = index != -1;
        if (result) {
            this.array.set(index, model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        int index = searchIndex(id);
        boolean result = index != -1;
        if (result) {
            this.array.delete(index);
        }
        return result;
    }

    @Override
    public T findById(String id) {
        int index = searchIndex(id);
        return index != -1 ? this.array.get(index) : null;
    }

    private int searchIndex(String id) {
        int result = -1;
        int index = -1;
        for (T base : this.array) {
            index++;
            if (base.getId().equals(id)) {
                result = index;
                break;
            }
        }
        return result;
    }
}
