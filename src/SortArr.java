import java.util.Scanner;

public class SortArr {

    public static void Sort(int[] nums) {
        int n = nums.length;

        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
          
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            heapify(nums, i, 0);
        }
    }

    private static void heapify(int[] nums, int n, int i) {
        int largest = i;        
        int left = 2 * i + 1;  
        int right = 2 * i + 2; 

        if (left < n && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < n && nums[right] > nums[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = nums[i];
            nums[i] = nums[largest];
            nums[largest] = swap;

            heapify(nums, n, largest);
        }
    }

    public static void main(String[] args) {
        Scanner v = new Scanner(System.in);

   
        System.out.print("Enter number of elements: ");
        int n = v.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            nums[i] = v.nextInt();
        }

        Sort(nums);

  
        System.out.println("Sorted array in ascending order:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        v.close();

    }
}
