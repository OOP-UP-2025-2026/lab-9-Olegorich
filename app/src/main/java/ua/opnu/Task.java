package ua.opnu;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        int loops;
        if (list.size() % 2 == 1) {
            loops = list.size() - 1;
        } else {
            loops = list.size();
        }

        int[] indexes = new int[loops];
        int counter = 0;

        for (int i = 1; i < loops; i = i + 2) {
            if (list.get(i - 1).length() <= list.get(i).length())
                indexes[counter] = i - 1 - counter;
            if (list.get(i - 1).length() > list.get(i).length())
                indexes[counter] = i - counter;
            counter++;
        }

        for (int i = 0; i < indexes.length / 2; i++)
            list.remove(indexes[i]);
    }

    public void stutter(List<String> list) {
        int loops = list.size();
        for (int i = 0; i < loops; i++)
            list.add(i * 2, list.get(i * 2));
    }

    public void switchPairs(List<String> list) {
        int loops;
        if (list.size() % 2 == 1) {
            loops = list.size() - 1;
        } else {
            loops = list.size();
        }

        String helper;
        for (int i = 1; i < loops; i = i + 2) {
            helper = list.get(i - 1);
            list.set(i - 1, list.get(i));
            list.set(i, helper);
        }
    }

    public void removeDuplicates(List<String> list) {
        int loops = list.size();

        for (int i = 0; i < loops; i++) {
            for (int j = 0; j < loops; j++) {
                if (j == i) continue;

                if (Objects.equals(list.get(i), list.get(j))) {
                    list.remove(j);
                    j--;
                    loops--;
                }
            }
        }
    }

    public void markLength4(List<String> list) {
        int loops = list.size();

        for (int i = 0; i < loops; i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++;
                loops++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        boolean checker = true;
        int loops;
        ArrayDeque<Integer> arrdeq = new ArrayDeque<>();

        loops = queue.size();
        for (int i = 0; i < loops; i++)
            arrdeq.add(queue.poll());

        loops = arrdeq.size() / 2;
        for (int i = 0; i < loops; i++) {
            if (!arrdeq.getFirst().equals(arrdeq.pollLast()))
                checker = false;
            queue.add(arrdeq.pollFirst());
        }

        loops = queue.size();
        for (int i = 0; i < loops; i++) {
            arrdeq.offerFirst(queue.peek());
            arrdeq.offerLast(queue.poll());
        }

        loops = arrdeq.size();
        for (int i = 0; i < loops; i ++)
            queue.add(arrdeq.pollFirst());

        return checker;
    }

    public void reorder(Queue<Integer> queue) {
        ArrayDeque<Integer> arrdeq = new ArrayDeque<>();
        int loops = queue.size();

        for (int i = 0; i < loops; i++) {
            if (queue.peek() > 0) {
                arrdeq.addLast(queue.poll());
            } else {
                arrdeq.addFirst(queue.poll());
            }
        }

        for (int i = 0; i < loops; i++)
            queue.add(arrdeq.pollFirst());
    }

    public void rearrange(Queue<Integer> queue) {
        ArrayDeque<Integer> arrdeq = new ArrayDeque<>();

        for (int i = 0; i < queue.size(); i++) {
            if (queue.peek() % 2 == 0) {
                queue.add(queue.remove());
            } else {
                arrdeq.addLast(queue.remove());
                i--;
            }
        }

        while (!arrdeq.isEmpty()) {
            queue.add(arrdeq.removeFirst());
        }
    }

    public int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) max = s.length();
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        set.removeIf(s -> s.length() % 2 == 0);
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        int[] nums = new int[Math.max(list1.size(), list2.size())];
        int reps = 0;
        int counter = 0;

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (Objects.equals(list1.get(i), list2.get(j))) {
                    for (int k = 0; k < nums.length; k++){
                        if (list1.get(i) == nums[k]) {
                            reps--;
                            break;
                        }
                    }
                    nums[counter] = list1.get(i);
                    counter++;
                    reps++;
                }
            }
        }

        return reps;
    }

    public boolean isUnique(Map<String, String> map) {

        for (Map.Entry<String, String> value1 : map.entrySet())
            for (Map.Entry<String, String> value2 : map.entrySet())
                if (Objects.equals(value1.getValue(), value2.getValue()) &&
                        !Objects.equals(value1.getKey(), value2.getKey()))
                    return false;

        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> output = new HashMap<>();

        for (Map.Entry<String, Integer> pair1 : map1.entrySet())
            for (Map.Entry<String, Integer> pair2 : map2.entrySet())
                if (Objects.equals(pair1.getValue(), pair2.getValue()) &&
                        Objects.equals(pair1.getKey(), pair2.getKey()))
                    output.put(pair1.getKey(), pair1.getValue());

        return output;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> output = new HashMap<>();

        for (Map.Entry<Integer, String> pair : map.entrySet())
            output.put(pair.getValue(), pair.getKey());

        return output;
    }

    public int rarest(Map<String, Integer> map) {
        int counter1 = 0, counter2 = 0;
        int[] arr = new int[map.size()];

        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            arr[counter1] = pair.getValue();
            counter1++;
        }

        int value = arr[0];
        counter1 = 0;

        for (int i = 0; i < arr.length; i++) {
            if (counter1 <= counter2) {
                counter2 = counter1;
                if (value > arr[i])
                    value = arr[i];
            }

            counter1 = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j])
                    counter1++;
            }
            if (i == 0)
                counter2 = counter1;
        }

        return value;
    }

    public int maxOccurrences(List<Integer> list) {
        int counter1 = 0, counter2 = 0;

        for (int i = 0; i < list.size(); i++) {
            for (Integer integer : list)
                if (Objects.equals(list.get(i), integer))
                    counter1++;

            if (counter1 > counter2) {
                counter2 = counter1;
            }
            counter1 = 0;
        }

        return counter2;
    }

}