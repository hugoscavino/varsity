package lab18;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Lab18AMain {
    public static void main(String[] args) {
        Lab18AMain main = new Lab18AMain();
        Class<?> c = null;
        try {
            c = Class.forName("Employee");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        main.classFun(c);
    }

    public void classFun(Class<?> c) {
        try {
            System.out.println("Class Name: " + c.getCanonicalName());

            Field[] fields = c.getDeclaredFields();
            System.out.println("\nFields:");
            for (Field field : fields) {
                System.out.println(" - " + field);
            }

            Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
            System.out.println("\nDeclared Constructors:");
            for (Constructor<?> constructor : declaredConstructors) {
                System.out.println(" - " + constructor);
            }

            Constructor<?>[] constructors = c.getConstructors();
            System.out.println("\nPublic Constructors:");
            for (Constructor<?> constructor : constructors) {
                System.out.println(" - " + constructor);
            }
            Method[] declaredMethods = c.getDeclaredMethods();
            System.out.println("\nDeclared Methods:");
            int count = 1;
            for (Method method : declaredMethods) {
                System.out.println(" " + (count++) + ". " + method);
            }

            Method[] methods = c.getMethods();
            System.out.println("\nPublic Methods:");
            count = 1;
            for (Method method : methods) {
                System.out.println(" " + (count++) + ". " + method);
            }
            Object employeeInstance = constructors[0].newInstance();
            System.out.println("\nInstance Created: " + employeeInstance);

            System.out.println("Is Enum: " + c.isEnum());
            System.out.println("Is Interface: " + c.isInterface());
            System.out.println("toString(): " + employeeInstance.toString());

            Method setSalary = find(methods, "setSalary");
            setSalary.invoke(employeeInstance, 1000.0);

            Method getSalary = find(methods, "getSalary");
            System.out.println("Salary: " + getSalary.invoke(employeeInstance));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static Method find(Method[] methods, String what) { for (Method m: methods) {
        if (m.toString().contains(what)) {
            return m; }
    }
        return null;
    }
}
