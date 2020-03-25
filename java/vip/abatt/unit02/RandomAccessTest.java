package vip.abatt.unit02;

import vip.abatt.unit02.TextFileTest.Employee;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Author:YANKAI_1101
 * Date:2020/3/25
 * Desc：随机访问文件
 * DataOutputStream写入文件
 * RandomAccessFile读取文件
 **/
public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new TextFileTest.Employee("A", 10000);
        staff[1] = new TextFileTest.Employee("B", 20000);
        staff[2] = new TextFileTest.Employee("C", 30000);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("emp.dat"))) {
            for (Employee e : staff) {
                out.writeUTF(e.name);
                out.writeInt(e.salary);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("emp.dat", "r")) {
            Employee[] employees = new Employee[staff.length];
            for (int i = 0; i < employees.length; i++) {
                final String name = in.readUTF();
                final int salary = in.readInt();
                employees[i] = new Employee(name, salary);
            }
            System.out.println(Arrays.toString(employees)); // [{name=A, salary=10000}, {name=B, salary=20000}, {name=C, salary=30000}]
        }
    }
}
