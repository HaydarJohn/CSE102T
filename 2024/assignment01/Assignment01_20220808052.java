
public class Assignment01_20220808052
{

}


class Course
{
    private String departmentCode;
    private int courseNumber;
    private String title;
    private String description;
    private int AKTS;
    
    
    public Course(String departmentCode, int courseNumber , String title, String description,int akts)
    {
        setDepartmentCode(departmentCode);
        setCourseNumber(courseNumber);
        setTitle(title);
        setDescription(description);
        setAKTS(akts);
    }

    // Getters 

    public String getDepartmentCode() 
    {
        return departmentCode;
    }
    public int getCourseNumber() 
    {
        return courseNumber;
    }
    public String getTitle() 
    {
        return title;
    }
    public String getDescription() 
    {
        return description;
    }
    public int getAKTS() 
    {
        return AKTS;
    }

    // Setters

    public void setDepartmentCode(String departmentCode) 
    {
        if(departmentCode.length() == 3 || departmentCode.length() == 4)
            this.departmentCode = departmentCode;
        //else
        //TODO: it shoud be four or five letter str
    }
    public void setCourseNumber(int courseNumber) 
    {
        this.courseNumber = courseNumber;
        //TODO: must be range of 100 - 999 or 5000 - 5999 or 7000 - 7999
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }
    public void setAKTS(int aKTS) 
    {
        AKTS = aKTS;
        //TODO: must be positive
    }


    // Other methods

    public String courseCode()
    {
        return getDepartmentCode() + getCourseNumber();
    }

    @Override
    public String toString() 
    {
        return courseCode() + " - " +getTitle() + " " + getAKTS();
    }
}

class Person
{
    private String name;
    private String email;
    private long ID;
    private String  departmentCode;

    public Person(String name, String email,long ID,String departmentCode)
    {
        setName(name);
        setEmail(email);
        setID(ID);
        setDepartmentCode(departmentCode);
    }

    // Getters
    public String getName() 
    {
        return name;
    }
    public String getEmail() 
    {
        return email;
    }
    public long getID() 
    {
        return ID;
    }
    public String getDepartmentCode() 
    {
        return departmentCode;
    }

    // Setters

    public void setName(String name) 
    {
        this.name = name;
    }
    public void setEmail(String email) 
    {
        this.email = email;
        // Must be of the form {username}@{universityname}.{domain}
    }
    public void setID(long iD) 
    {
        ID = iD;
    }
    public void setDepartmentCode(String departmentCode) 
    {
        this.departmentCode = departmentCode;
        // Must be 3 or 4 charachters
    }

    // Other methods

    @Override
    public String toString() 
    {
        return getName() + " " + getID() + " - " + getEmail();    
    }
}


class Teacher extends Person
{
    private int rank;

    public Teacher(String name, String email,long ID,String departmentCode,int rank)
    {
        super(name, email, ID, departmentCode);
        

    }

    // Getters

    public String getTitle()
    {
        switch (this.rank) {
            case 1:
            {
                return "Lecturer";
            }

            case 2:
            {
                return "Assistant Professor";
            }

            case 3:
            {
                return "Associate Professor";
            }

            case 4:
            {
                return "Professor";
            }

            default:
            {
                return "";
                // Error
            }
        }
    }

    // Setters

    public void setRank(int rank) 
    {
        this.rank = rank;
        // Must be between 1 and 4
    }

    // Other methods

    public void promote()
    {
        setRank(rank + 1);
    }

    public void demote()
    {
        setRank(rank - 1);
    }

    @Override
    public String toString() {
        return getTitle() + " " + super.toString();
    }

}

class Student extends Person
{
    private int AKTS;

    public Student(String name, String email,long ID,String departmentCode)
    {
        super(name, email, ID, departmentCode);
        setAKTS(0);
    }

    // Getters

    public int getAKTS() 
    {
        return AKTS;
    }

    // Setters

    public void setAKTS(int AKTS) 
    {
        this.AKTS = AKTS;
        // Must be positive
    }

    // Other methods

    public void passCourse(Course course)
    {
        setAKTS(getAKTS() + course.getAKTS());
    }

    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return super.toString();
    }
}

class GradStudent extends Student
{
    private int rank;
    private String thesisTopic;

    public GradStudent(String name, String email,long ID,String departmentCode
    ,int rank,String thesisTopic)
    {
        super(name, email, ID, departmentCode);
        setThesisTopic(thesisTopic);
        setRank(rank);
    }

    // Getters

    public String getLevel()
    {
        switch (this.rank) {
            case 1:
            {
                return "Master’s Student";
            }

            case 2:
            {
                return "Doctoral Student";
            }

            case 3:
            {
                return "Doctoral Candidate";
            }

            default:
            {
                return "";
                // Error
            }
        }
    }

    public String getThesisTopic() 
    {
        return thesisTopic;
    }

    // Setters

    public void setRank(int rank) 
    {
        this.rank = rank;
        // Must be between 1 and 3
    }

    public void setThesisTopic(String thesisTopic) 
    {
        this.thesisTopic = thesisTopic;
    }

    // Other methods

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}

