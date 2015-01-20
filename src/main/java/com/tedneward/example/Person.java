package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Age must be greater than zero.");
    }
    int old = age;
    age = value;
    
    this.pcs.firePropertyChange("age", old, value);
    propertyChangeFired = true;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    if (value == null) {
      throw new IllegalArgumentException("Needs a valid name");
    }
    String old = name;
    name = value;
    
    this.pcs.firePropertyChange("name", old, value);
    propertyChangeFired = true;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double value) {
    double old = salary;
    salary = value;
    
    this.pcs.firePropertyChange("salary", old, value);
    propertyChangeFired = true;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person otherP = (Person) other; 
      return (this.name.equals(otherP.name) && this.age == otherP.age);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]"; 
  }

  public static int count() {
    return count;
  }

  @Override
  public int compareTo(Person other) {
    return new Double(other.salary).compareTo(new Double(this.salary));
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> newardFam = new ArrayList<Person>();
    newardFam.add(new Person("Ted", 41, 250000));
    newardFam.add(new Person("Charlotte", 43, 150000));
    newardFam.add(new Person("Michael", 22, 10000));
    newardFam.add(new Person("Matthew", 15, 0));
    return newardFam;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }

  }
}
