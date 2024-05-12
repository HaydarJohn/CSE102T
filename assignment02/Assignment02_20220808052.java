

public class Assignment02_20220808052
{
	public static void main(String[] args)
	{
		
	}
}

class Department
{
	private String code;
	private String name;
	private Teacher chair;

	public Department(String code,String name)
	{
		this.code = code;
		this.name = name;
	}
	public String getCode()
	{
		return code;
	}
	public String getName()
	{
		return name;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Teacher getChair()
	{
		return chair;
	}
	public void setChair(Teacher chair)
	{
		this.chair = chair;
	}

}

class Course
{
	private Department department;
	private Teacher teacher;
	private int courseNumber;
	private String title;
	private String description;
	private int AKTS;

	public Course(String departmentCode,int courseNumber,String title,String description,int AKTS,Teacher teacher)
    	{
		department.setCode(departmentCode);
        	setTitle(title);
        	setAKTS(AKTS);
        	setCourseNumber(courseNumber);
        	setDescription(description);

   	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	public int setAKTS(int akts)
	{
		AKTS = akts;
	}
	public void getAKTS()
	{
		return AKTS;
	}
	public void setCourseNumber(int courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	public int getCourseNumber()
	{
		return courseNumber;
	}
	public void setDepartmentCode(Department departmentCode)
	{
		this.department.setCode(departmentCode);
	}
	public String getDepartmentCode()
	{
		return departmen.getCode();
	}
	public String getDescription()
	{
		return desciption;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Department getDepartment()
	{
		return department;
	}
	public void setDepartment(Department department)
	{
		this.department = department;
	}
	public Teacher getTeacher()
	{
		return teacher;
	}
	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}
	public String courseCode()
    	{
        	return String.format("%s%d", getDepartmentCode(),getCourseNumber());
    	}
	@Override
    	public String toString() 
	{
		return String.format("%s%d - %s (%d)",getDepartmentCode(),getCourseNumber(),getTitle(),getAKTS());
    	}
}
