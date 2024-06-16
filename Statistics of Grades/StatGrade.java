import java.util.Scanner;

public class StatGrade {
    public static void main(String[] args) {
        final String SOLID = "#######";
        final String SPACE = "       ";
        int maximum;
        int minimum;
        double average;
        int[] stats = new int[5];

        //code to get line of student scores 
        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine().trim();

        String[] integerStrings = inputLine.split("\\s+");
        int[] scores = new int[integerStrings.length];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = Integer.parseInt(integerStrings[i]);
        }

        //call to methods to find maximum mark, minimum mark and the average of all scores 
        maximum = findMaximumGrade(scores);
        minimum = findMinimumGrade(scores);
        average = findAverageGrade(scores);

        //storing the frequency of each range
        for(int mark: scores ){
            if (mark >= 0 && mark <= 20){
                stats[0]++;
            }
            else if(mark >= 21 && mark <= 40){
                stats[1]++;
            }
            else if(mark >= 41 && mark <= 60){
                stats[2]++;
            }
            else if(mark >= 61 && mark <= 80){
                stats[3]++;
            }
            else{
                stats[4]++;
            }

        }



        scanner.close();

        //getting the range with maximum frequency
        int maxFrequency = stats[0];
        for(int frequency: stats){
            if(frequency > maxFrequency){
                maxFrequency = frequency;
            }
        }

        //print bar chart
        printBarChart(maxFrequency, stats, SOLID, SPACE, maximum, minimum, average);

    }
    
    public static int findMaximumGrade(int[] scores){
        //method finds the maximum score 
        int max = scores[0];
        int length = scores.length;
        for(int i = 1; i < length; i++){
            if (scores[i] > max){
                max = scores[i];
            }
        }
        return max;

    }
    public static int findMinimumGrade(int[] scores){
        //method finds the minimum mark
        int min = scores[0];
        int length = scores.length;
        for(int i = 1; i < length; i++){
            if(scores[i] < min){
                min = scores[i];
            }
        }


        return min;
    }
    public static double findAverageGrade(int[] scores){
        //method to calculate for the average mark
        double sum = 0;
        double length = scores.length;

        for (int mark : scores) {
            sum = sum + mark;
        }
        return sum / length;
    }
    public static void printBarChart(int maxFrequency, int[] rangeFrequency, String sol, String spa, int max, int min, double avg){
        //method to print bar chart
        System.out.println("\n\nValues:\n");
        System.out.println("\nThe maximum grade is " + max);
        System.out.println("The minimum grade is " + min);
        System.out.println("The average grade is " + avg);
        System.out.println("\n\n\nGraph:\n\n\n");
        while(maxFrequency > 0){
            System.out.print(maxFrequency + " >");
            for(int i =0; i < 5; i++){
                if (maxFrequency - rangeFrequency[i] == 0){
                    System.out.print("  " + sol + " ");
                    rangeFrequency[i]--;
                }
                else{
                    System.out.print("  " + spa + " ");
                }
            }
            System.out.println(" ");

            --maxFrequency;
        }
        System.out.println("  +-----------+---------+---------+---------+---------+");
        System.out.println("  I    0-20   I  21-40  I  41-60  I  61-80  I  81-100 I");
    }
}
