
public class RandomQuicksort {

	private int[] array;
	private ArrayUpdater updater;

	public RandomQuicksort(ArrayUpdater updater){
		this(20, updater);
	}
	
	public RandomQuicksort(int size, ArrayUpdater updater) {
		array = new int[size];
		this.updater = updater;
		for (int i = 0; i < size; i++)
			array[i] = (int) (Math.random() * size) + 1;
	}
	
	public int[] getArray(){
		return array;
	}

	public void sort() {
		quicksort(0, array.length - 1);
	}

	private void quicksort(int start, int end) {
		if (start < end) {
			int middle = partition(start, end);  // Partitions the array and then finds the where the pivot ended up
			quicksort(start, middle - 1);		// Sorts the sides around it
			quicksort(middle + 1, end);
		}
	}

	private int partition(int start, int end) {

		int pivot = array[start];			// Selects the first number as the pivot (it can be anything)

		while (start < end) {				// We don't want overlap
			
			while (array[start] < pivot) 	// Moves the left pointer to a point where the number not suppose to be on that side, ie bigger than the pivot
				start++;

			while (array[end] > pivot)		// Moves the right pointer to a point where the number not suppose to be on that side, ie smaller than the pivot
				end--;

			if (array[start] == array[end])	// Do nothing if they are the same
				end--;

			else if (start < end)			// Swap the values at the pointers
				swapValues(start, end);

		}

		return start;
	}

	private void swapValues(int a, int b) {		
		int temp = array[a];				// Simple array swap
		array[a] = array[b];
		array[b] = temp;

		try {
			Thread.sleep(100);				// Sleeps so update isn't "instant"
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		updater.update(array);				// Ultimately Prints 

	}
	
	public interface ArrayUpdater {
		public void update(int[] array);
	}

}
