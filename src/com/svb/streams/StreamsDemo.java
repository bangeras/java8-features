package com.svb.streams;

import java.util.Arrays;
import java.util.List;

public class StreamsDemo {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
        StreamsDemo streamsDemo = new StreamsDemo();
        streamsDemo.forEachDemo(nums);
        streamsDemo.mapDemo(nums);
        streamsDemo.filterDemo(nums);
        streamsDemo.reduceDemo(nums);
        streamsDemo.skipAndLimit(nums);

        List<Integer> marks = Arrays.asList(35,37,38,39,41,80,90,99);
        streamsDemo.addGraceMarks(marks);
    }

    private void forEachDemo(List<Integer> nums){
        System.out.println("----- forEachDemo() -----");
        nums.stream().forEach(s -> System.out.println(s));
    }


    private void mapDemo(List<Integer> nums){
        System.out.println("----- mapDemo() -----");
        nums.stream().map(n -> 2*n)
                .forEach(n -> System.out.println(n));
    }

    private void filterDemo(List<Integer> nums){
        System.out.println("----- filterDemo() -----");
        nums.stream()
                .filter(n -> n%2==0)
                .map(n -> 2*n)
                .forEach(n -> System.out.println(n));
    }

    private void reduceDemo(List<Integer> nums) {
        System.out.println("----- reduceDemo() -----");
        int result = nums.stream()
                .map(n -> 2 * n)
                .reduce(0, (c, e) -> c + e);
        System.out.println(result);
    }

    private void addGraceMarks(List<Integer> marks) {
        System.out.println("------addGraceMarks()-----");

        marks.stream()
                .map(n -> {
                    if (n>=37 && n<40) {
                        return 40;
                    }
                    return n;
                })
                .forEach(n -> System.out.println(n));
    }

    private void skipAndLimit(List<Integer> nums) {
        System.out.println("----- skipAndLimit() -----");

        nums.stream()
                .skip(2)
                .limit(nums.size()-2+1)
                .forEach(n -> System.out.println(n));

    }
}
