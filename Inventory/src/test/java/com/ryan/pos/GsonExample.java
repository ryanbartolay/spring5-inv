package com.ryan.pos;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Brand;
import com.google.gson.Gson;

public class GsonExample {

	static Gson gson = new Gson();
	
    public static void main(String[] args) {

        Staff staff = createDummyObject();

        //1. Convert object to JSON string
        String json = gson.toJson(staff);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("D:\\staff.json")) {

            gson.toJson(staff, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Staff createDummyObject() {

        Staff staff = new GsonExample().new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition("Founder");
        staff.setSalary(new BigDecimal("10000"));

        List<Brand> skills = new ArrayList<>();
        
        Brand obj = new Brand();
        obj.setName("Ryan");
        obj.setNameArabic("hehehe");
//        obj.put("name", "Ryan");
//        obj.put("company", "Gotech Solutions");
        
        Brand obj2 = new Brand();
        obj2.setName("Ryan2");
        obj2.setNameArabic("hehehe2");
//        obj2.put("name", "Ryan2");
//        obj2.put("company", "Gotech Solutions2");
        
        skills.add(obj);
        skills.add(obj2);
        
        
        List<String> ss = new ArrayList<>();
        ss.add("java");
        ss.add("python");
        ss.add("shell");
        
        
        
        staff.setSkillString(ss);
        staff.setSkills(skills);

        return staff;

    }
    class Staff {

    	private String name;
    	private int age;
    	private String position;
    	private BigDecimal salary;
    	private List<Brand> skills;
    	private List<String> skillString;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public BigDecimal getSalary() {
			return salary;
		}
		public void setSalary(BigDecimal salary) {
			this.salary = salary;
		}
		public List<Brand> getSkills() {
			return skills;
		}
		public void setSkills(List<Brand> skills) {
			this.skills = skills;
		}
		public List<String> getSkillString() {
			return skillString;
		}
		public void setSkillString(List<String> skillString) {
			this.skillString = skillString;
		}
    }

}
