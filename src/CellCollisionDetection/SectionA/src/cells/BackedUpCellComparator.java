package cells;

import java.rmi.UnexpectedException;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class BackedUpCellComparator<U> implements Comparable<BackedUpCell<U>> {

    private Comparator<U> valueComparator;

    public BackedUpCellComparator(Comparator<U> valueComparator) {
        this.valueComparator = valueComparator;
    }

    @Override
    public int compareTo(BackedUpCell<U> uBackedUpCell) {
        return 0;
    }

    public int compare(BackedUpCell<U> a, BackedUpCell<U> b) {

        BackedUpCell<U> initialA = a;
        BackedUpCell<U> initialB = b;

        if (!a.isSet() && b.isSet()) {
            return -1;
        } else if (a.isSet() && !b.isSet()) {
            return 1;
        } else if (!a.isSet() && !b.isSet()) {
            return 0;
        }

        int answer = valueComparator.compare(a.get(), b.get());
        if (answer != 0) {
            return answer;
        }
        Deque<U> newerValues = new ArrayDeque<U>();
        while (a.hasBackup() && b.hasBackup()) {
            newerValues.push(a.get());
            newerValues.push(b.get());
            a.revertToPrevious();
            b.revertToPrevious();
            answer = valueComparator.compare(a.get(), b.get());
            if (answer != 0) {
                reconstructCells(a, b, newerValues);
                return answer;
            }
        }

        if (!a.hasBackup() && b.hasBackup()) {
            return -1;
        } else if (a.hasBackup() && !b.hasBackup()) {
            return 1;
        }

        reconstructCells(a, b, newerValues);
        return 0;
    }

    public void reconstructCells(BackedUpCell<U> a, BackedUpCell<U> b,
                                 Deque<U> values) {
        while(!values.isEmpty()){
            a.set(values.pop());
            b.set(values.pop());
        }
    }
}

