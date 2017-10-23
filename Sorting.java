/**
 * My hackerrank profile is mccrosson_k
 */
import java.io.*;
import java.util.*;

public class Solution {

  // variables for keeping track of comparisons
  private static int bubbleComps;
  private static int mergeComps;

  // constructor
  public Solution() {
    bubbleComps = -1;
    mergeComps = -1;
  }

  // main function
  public static void main(String[] args) {
    Scanner scan1 = new Scanner(System.in);
    String input = scan1.nextLine();

    Scanner scan2 = new Scanner(input).useDelimiter(",");
    ArrayList<Integer> numbers = new ArrayList();
    while (scan2.hasNextInt()) {
      numbers.add(scan2.nextInt());
    }

    /**
    * Bubblesort the list of numbers keeping track of comparisons, 
    * if statement checks size of list of numbers to prevent overflow.
     */
    if (numbers.size() < 100000) {
      ArrayList<Integer> bubbleList = bubbleSort(numbers);
    }


    // Merge sort the list of numbers, keeping track of comparisons
    ArrayList<Integer> mergeList = mergeSort(numbers);
    String sortedNums = "";
    while (mergeList.size() > 1) {
      sortedNums = sortedNums + mergeList.get(0) + ",";
      mergeList.remove(0);
    }
    sortedNums = sortedNums + mergeList.get(0);
    System.out.println(sortedNums);
    System.out.println(bubbleComps);
    System.out.println(mergeComps);

  }

  // bubble sort
  private static ArrayList<Integer> bubbleSort(ArrayList<Integer> nums) {
    ArrayList<Integer> array = new ArrayList<>();
    array.addAll(nums);
    bubbleComps = 0;
    while (true) {
      boolean swapped = false;
      for (int i = 0; i < array.size() - 1; i++) {
        // increase number of comparisons
        bubbleComps++;
        if (array.get(i) > array.get(i + 1)) {
          int temp = array.get(i);
          array.set(i, array.get(i + 1));
          array.set(i + 1, temp);
          swapped = true;
        }
      }
      if (!swapped) {
        return array;
      }
    }
  }

  // merge sort
  private static ArrayList<Integer> mergeSort(ArrayList<Integer> nums) {
    if (nums.size() < 2) {
      return nums;
    }

    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();

    for (int i = 0; i < nums.size() / 2; i++) {
      left.add(nums.get(i));
    }
    for (int j = nums.size() / 2; j < nums.size(); j++) {
      right.add(nums.get(j));
    }

    left = mergeSort(left);
    right = mergeSort(right);

    int m = 0;
    int n = 0;
    int p = 0;

    ArrayList<Integer> solution = new ArrayList<>();

    while (m < left.size() && n < right.size()) {
      // increase number of comparisons
      mergeComps++;
      if (left.get(m) < right.get(n)) {
        solution.add(left.get(m));
        m++;
      }
      else {
        solution.add(right.get(n));
        n++;
      }
    }

    if (m >= left.size()) {
      for (int x = n; x < right.size(); x++) {
        solution.add(right.get(x));
      }
    }
    else {
      for (int x = m; x < left.size(); x++) {
        solution.add(left.get(x));
      }
    }

    return solution;
  }
}
