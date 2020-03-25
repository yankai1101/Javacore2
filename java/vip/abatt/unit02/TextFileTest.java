/**
 * Author:YANKAI_1101
 * Date:2020/3/25
 * Desc：输入/输出流
 * PrintWriter 写入
 * FileInputStream 读取
 **/
package vip.abatt.unit02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextFileTest {
    public static class Employee {
        public String name;
        public int salary;

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "{name=" + name + ", salary=" + salary + "}";
        }
    }

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("A", 10000);
        staff[1] = new Employee("B", 20000);
        staff[2] = new Employee("C", 30000);

        // 写入文件
        try (PrintWriter out = new PrintWriter("employee.dat", StandardCharsets.UTF_8)) {
            for (Employee e : staff)
                out.println(e.name + "|" + e.salary);
        }

        // 读取文件
        try (final Scanner in = new Scanner(new FileInputStream("employee.dat"), StandardCharsets.UTF_8)) {
            List employees = new ArrayList();
            while (in.hasNextLine()) {
                final String[] split = in.nextLine().split("\\|");
                employees.add(new Employee(split[0], Integer.parseInt(split[1])));
            }
            System.out.println(employees); // [{name=A, salary=10000}, {name=B, salary=20000}, {name=C, salary=30000}]
        }
    }
}
