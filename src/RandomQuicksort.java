
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
	
	public int getSize(){
		return array.length;
	}
	
	public int[] getArray(){
		return array;
	}

	public void sort() {
		quicksort(0, array.length - 1);
	}

	private void quicksort(int start, int end) {
		if (start < end) {
			int middle = partition(start, end);
			quicksort(start, middle - 1);
			quicksort(middle + 1, end);
		}
	}

	private int partition(int start, int end) {

		int pivot = array[start];

		while (start < end) {

			while (array[start] < pivot)
				start++;

			while (array[end] > pivot)
				end--;

			if (array[start] == array[end])
				end--;

			else if (start < end)
				swapValues(start, end);

		}

		return start;
	}

	private void swapValues(int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		updater.update(array);

	}

}
