# Advance Web Sorting Algorithms Project


## Project Overview
This project is a Spring-based web application that allows users to sort an array of integers using various sorting algorithms. The user inputs the array and selects a sorting algorithm from a dropdown list. Upon submission, the sorted array is displayed along with links to sort the original array using other algorithms.

## Architecture
The project follows a standard Spring MVC architecture:
- **Controller**: Handles user requests and interactions.
- **Service**: Contains the business logic for sorting algorithms.
- **Views**: JSP files for user interaction and displaying results.

## Components

### Controller
#### SortingController
```java
package org.sorting.controllers;

import org.sorting.services.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String sortArray(@RequestParam("array") String array, @RequestParam("algorithm") String algorithm, Model model) {
        String[] stringArray = array.split(",");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        long startTime = System.nanoTime(); // Start time measurement

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

        long endTime = System.nanoTime(); // End time measurement
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        model.addAttribute("sortedArray", Arrays.toString(intArray));
        model.addAttribute("executionTime", duration); // Pass execution time to result.jsp

        // Add HATEOAS links for other sorting algorithms
        model.addAttribute("originalArray", array);

        return "result";
    }
}

# SortingService.class
package org.sorting.services;

import org.springframework.stereotype.Service;

@Service
public class SortingService {

    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public void mergeSort(int[] array) {
        // Implementation of merge sort
    }

    public void bucketSort(int[] array) {
        // Implementation of bucket sort
    }

    public void radixSort(int[] array) {
        // Implementation of radix sort
    }

    public void heapSort(int[] array) {
        // Implementation of heap sort
    }
}

## index.jsp
<!DOCTYPE html>
<html>
<head>
    <title>Sort Array</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
        }
        .form-control:focus {
            border-color: yellow;
            box-shadow: none;
        }
        select.form-control:focus {
            border-color: yellow;
            box-shadow: none;
        }
        .btn-black {
            background-color: black;
            color: white;
        }
        body {
            background-color: grey;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Enter an Array to Sort</h1>
    <form action="sort" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="array" placeholder="e.g., 1,2,3,4,5"/>
        </div>
        <div class="form-group">
            <label for="algorithm">Select Sorting Algorithm:</label>
            <select class="form-control" id="algorithm" name="algorithm">
                <option value="quick">Quick Sort</option>
                <option value="merge">Merge Sort</option>
                <option value="bucket">Bucket Sort</option>
                <option value="radix">Radix Sort</option>
                <option value="heap">Heap Sort</option>
            </select>
        </div>
        <button type="submit" class="btn btn-black">Sort</button>
    </form>
</div>
</body>
</html>

## result.jsp
<!DOCTYPE html>
<html>
<head>
    <title>Sorted Array</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f2f2f2;">
<div class="container" style="margin-top: 50px; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
    <h1>Sorted Array</h1>
    <p>${sortedArray}</p>
    <p>Execution Time: ${executionTime} milliseconds</p>
    <a href="${pageContext.request.contextPath}/AdvancedSortingWA">Sort Another Array</a>
    <br><br>
    <h3>Sort with another algorithm:</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/sort?algorithm=quick&array=${originalArray}">Quick Sort</a></li>
        <li><a href="${pageContext.request.contextPath}/sort?algorithm=merge&array=${originalArray}">Merge Sort</a></li>
        <li><a href="${pageContext.request.contextPath}/sort?algorithm=bucket&array=${originalArray}">Bucket Sort</a></li>
        <li><a href="${pageContext.request.contextPath}/sort?algorithm=radix&array=${originalArray}">Radix Sort</a></li>
        <li><a href="${pageContext.request.contextPath}/sort?algorithm=heap&array=${originalArray}">Heap Sort</a></li>
    </ul>
</div>
</body>
</html>

# dispatch-servlet.xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- Enable component scanning -->
    <context:component-scan base-package="org.sorting" />

    <!-- Enable Spring MVC annotations -->
    <mvc:annotation-driven />

    <!-- View resolver configuration -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>


# web.xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>

## Execution Flow
1. User accesses the application and is presented with the `index.jsp` page.
2. User inputs an array and selects a sorting algorithm, then submits the form.
3. The form submission triggers a POST request to the `/sort` endpoint, handled by the `SortingController`.
4. The `SortingController` processes the request, invoking the appropriate sorting algorithm from the `SortingService`.
5. The sorted array and execution time are added to the model.
6. The `result.jsp` page is displayed, showing the sorted array, execution time, and links to sort the original array using other algorithms.

## HATEOAS Compliance
- The `result.jsp` page includes links to execute different sorting algorithms on the original array, making the application HATEOAS compliant.
- The links use the original array and specify different sorting algorithms, allowing the user to easily switch between sorting methods.

## Styling
- The project uses Bootstrap for styling.
- Custom CSS is added to enhance the appearance of input fields, dropdown lists, and buttons.
- The background color of the `index.jsp` page is set to grey, with the body content in white.
- The `result.jsp` page is styled similarly, ensuring a consistent look and feel across the application.
