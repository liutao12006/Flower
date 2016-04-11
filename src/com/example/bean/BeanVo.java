package com.example.bean;

public class BeanVo {
  private String name;;
  private String age;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public BeanVo(String name, String age) {
	super();
	this.name = name;
	this.age = age;
}
  
}
