package InterviewProblems.Q3Q4;

import java.util.*;

public class Q3Q4 {
    public static void main(String[] args) {
        List<String> fullTimeEmployees = Arrays.asList("A", "B", "C");
        List<String> partTimeEmployees = Arrays.asList("D", "E", "F", "G", "H");
        Map<String, Integer> fullTimeCount = new HashMap<>();
        Map<String, Integer> partTimeCount = new HashMap<>();
        for (String s : fullTimeEmployees) {
            fullTimeCount.put(s, 0);
        }
        for (String s : partTimeEmployees) {
            partTimeCount.put(s, 0);
        }
        List<Map<String, List<String>>> schedule = new ArrayList<>();
        for (int day = 1; day <= 20; day++) {
            Map<String, List<String>> dailySchedule = new HashMap<>();
            dailySchedule.put("morningShift", assignShift(fullTimeEmployees, partTimeEmployees, fullTimeCount, partTimeCount, new ArrayList<>()));
            dailySchedule.put("eveningShift", assignShift(fullTimeEmployees, partTimeEmployees, fullTimeCount, partTimeCount, dailySchedule.get("morningShift")));
            schedule.add(dailySchedule);
        }

        // Print schedule
        for (int i = 0; i < schedule.size(); i++) {
            System.out.println("Day " + (i + 1) + ": " + schedule.get(i));
        }
        // Print the number of shifts each full-time employee has taken
        System.out.println("Full-time employee shifts:");
        for (Map.Entry<String, Integer> entry : fullTimeCount.entrySet()) {
            System.out.println("Employee " + entry.getKey() + ": " + entry.getValue() + " shifts");
        }

        // Print the number of shifts each part-time employee has taken
        System.out.println("Part-time employee shifts:");
        for (Map.Entry<String, Integer> entry : partTimeCount.entrySet()) {
            System.out.println("Employee " + entry.getKey() + ": " + entry.getValue() + " shifts");
        }

    }

    /**
     * 指派員工輪班
     * 將第三題，第四題進行合併，將低於minimum工作日的人，列於優先排班人選
     * @param fullTime 正職員工
     * @param partTime PT
     * @param fullTimeCount 正職員工: 工作日數
     * @param partTimeCount PT: 工作日數
     * @param exclude 當日已輪班過
     * @return 該次輪班
     */
    public static List<String> assignShift(List<String> fullTime, List<String> partTime, Map<String, Integer> fullTimeCount, Map<String, Integer> partTimeCount, List<String> exclude) {
        List<String> shift = new ArrayList<>();
        List<String> availableFullTime = new ArrayList<>(fullTime);
        List<String> availablePartTime = new ArrayList<>(partTime);
        availableFullTime.removeAll(exclude);
        availablePartTime.removeAll(exclude);

        // Prioritize full-time employees who haven't worked 15 days yet
        List<String> priorityFullTime = new ArrayList<>();
        //Q4 code
//        for (String e : availableFullTime) {
//            if (fullTimeCount.get(e) < 15) {
//                priorityFullTime.add(e);
//            }
//        }

        // Prioritize part-time employees who haven't worked 2 days yet
        List<String> priorityPartTime = new ArrayList<>();
        //Q4 code
//        for (String e : availablePartTime) {
//            if (partTimeCount.get(e) < 2) {
//                priorityPartTime.add(e);
//            }
//        }

        for (int i = 0; i < 2; i++) {
            List<String> allAvailable = new ArrayList<>();
            if (!priorityFullTime.isEmpty() || !priorityPartTime.isEmpty()) {
                allAvailable.addAll(priorityFullTime);
                allAvailable.addAll(priorityPartTime);
            } else {
                allAvailable.addAll(availableFullTime);
                availablePartTime.removeIf(e -> partTimeCount.get(e)>=10);
                allAvailable.addAll(availablePartTime);
            }

            Collections.shuffle(allAvailable);
            String selected = allAvailable.get(0);

            if (fullTime.contains(selected)) {
                fullTimeCount.put(selected, fullTimeCount.get(selected) + 1);
            } else if (partTime.contains(selected)) {
                partTimeCount.put(selected, partTimeCount.get(selected) + 1);
            }

            shift.add(selected);
            availableFullTime.remove(selected);
            availablePartTime.remove(selected);
            priorityFullTime.remove(selected);
            priorityPartTime.remove(selected);
        }

        return shift;
    }

}
