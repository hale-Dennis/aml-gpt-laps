package org.sorting.controllers;

import org.sorting.services.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class SortingController {

    @Autowired
    private SortingService sortingService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/sort")
    public String sortArrayPost(@RequestParam("array") String array, @RequestParam("algorithm") String algorithm, Model model) {
        return sortArray(array, algorithm, model);
    }

    @GetMapping("/sort")
    public String sortArrayGet(@RequestParam("array") String array, @RequestParam("algorithm") String algorithm, Model model) {
        return sortArray(array, algorithm, model);
    }

    private String sortArray(String array, String algorithm, Model model) {
        String[] stringArray = array.split(",");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        int[] originalArray = Arrays.copyOf(intArray, intArray.length);
        long startTime = System.nanoTime();

        switch (algorithm) {
            case "quick":
                sortingService.quickSort(intArray);
                break;
            case "merge":
                sortingService.mergeSort(intArray);
                break;
            case "bucket":
                sortingService.bucketSort(intArray);
                break;
            case "radix":
                sortingService.radixSort(intArray);
                break;
            case "heap":
                sortingService.heapSort(intArray);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        String timeComplexity = "";

        switch (algorithm){
            case "quick":
                timeComplexity = " O(n*log(n))";
                break;
            case "merge":
                timeComplexity = " O(n log n)";
                break;
            case "bucket":
                timeComplexity = "O(n + k)";
                break;
            case "radix":
                timeComplexity = " O(nk)";
                break;
            case "heap":
                timeComplexity = "O(n log n)";
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }

        model.addAttribute("sortedArray", Arrays.toString(intArray));
        model.addAttribute("originalArray", array);
        model.addAttribute("executionTime", duration);
        model.addAttribute("algorithm", algorithm);
        model.addAttribute("timeComplexity", timeComplexity);
        return "result";
    }
}
