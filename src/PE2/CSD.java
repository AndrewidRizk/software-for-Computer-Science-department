package PE2;


import java.util.*;
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Andro Rizk
Student Number: 218720250
Course Section: B
*/


/**
 * CSD represent the Computer Science (CS) department, 
 * t CSD has the following restrictions:
 * --> Number of Chairpersons: 1
 * --> Number of Program Directors: 3
 * --> Number of Faculty assigned to a Program Director: Up to a maximum of 25
 * --> Number of Faculty members: Up to a Maximum of 70
 * --> Number of Grad students: Up to a Maximum of 150
 * --> Number of Undergrad Students: Up to a Maximum of 500
 * --> Number of Grad students assigned to a Faculty as TAs: Up to a maximum of 5
 * --> Number of Undergrad students assigned to a Faculty for advising: Up to a maximum of 8
 * All of them are stored in an arrayList
 * @author andro
 *
 */
public class CSD
{
    private static final int MAX_NUMBER_OF_PROGRAM_DIRECTORS = 3;
    private static final int MAX_NUMBER_OF_FACULTIES_IN_PROGRAM_DIRECTORS = 25;
    private static final int MAX_NUMBER_OF_FACULTY = 70;
    private static final int MAX_NUMBER_OF_GRAD_STUDENTS = 150;
    private static final int MAX_NUMBER_OF_UNDERGRAD_STUDENTS = 500;
	private ChairPerson ChairPerson;
	private ArrayList<Faculty> faculty;
	private int NumOfFaculty;
	private ArrayList<UGrad> UGradStudents;
	private int NumOfUGradStudents;
	private int FaucultyIndex;
	private ArrayList<Grad> GradStudents;
	private int NumOfGradStudents;
	private int FaucultyIndexForTAS;
	private ArrayList<ProgramDirector> ProgramDirector;
	private int numberOfProgramDirector;
	
	/**
	 * A constructor for the department with one ChairPeron, and initiate everything to a default values 
	 * @param ChairPerson
	 */
	CSD(ChairPerson ChairPerson) 
	{
		this.ChairPerson = ChairPerson;
		this.faculty = new ArrayList<Faculty>();
		this.NumOfFaculty = 0;
		this.UGradStudents = new ArrayList<UGrad>();
		this.NumOfUGradStudents = 0;
		this.FaucultyIndex = 0;
		this.GradStudents = new ArrayList<Grad>();
		this.NumOfGradStudents = 0;
		this.FaucultyIndexForTAS = 0;
		this.ProgramDirector = new ArrayList<ProgramDirector>();
		this.numberOfProgramDirector = 0;
		
	}
	
	/**
	 * a getter for ChairPerson  
	 * @return ChairPerson
	 */
	public ChairPerson getChairPerson()
	{
		return this.ChairPerson;
	}

	
	/**
	 * this is a getter for the Array list faculty
	 * @return an arrayList of all the faculty
	 */
	public ArrayList<Faculty> getFaculty() {
		return this.faculty;
	}
	
	
	/**
	 * this is a getter for the Array list that contains the Ugrad students
	 * @return an arrayList of all the UGradStudents
	 */
	
	public ArrayList<UGrad> getUGradStudents() {
		return UGradStudents;
	}

	public void addUGradStudents(UGrad uGradStudent) {
		boolean included = false;
		for(UGrad stu: this.UGradStudents)
			if(stu.compareTo(uGradStudent) == 0)
				included = true;
		if(!included)
		{
			this.UGradStudents.add(uGradStudent);
			this.NumOfUGradStudents = NumOfUGradStudents + 1;
		}
	}
	/**
	 * a getter for the number of Number Of UGrad Students in the department 
	 * @return NumOfUGradStudents
	 */
	public int getNumOfUGradStudents() {
		return NumOfUGradStudents;
	}
	
	
	/**
	 * this is method is like a setter for the field attribute facility, that add faculty to the department 
	 * when the University hires a new faculty member
	 * --> You need to add the faculty information to the university faculty records.
	 * --> The newly hired faculty is assigned to the designated Program Director
	 * according to the specialty of faculty member, and then the hired faculty
	 * will be added to the corresponding Program Director’s record 
	 * responsible of the same program.
	 * --> The university cannot hire the same faculty twice. Hence, there are no
	 * duplicates.

	 * @param Faculty and sets it at the end of the arrayList
	 * @throws NoSpaceException if there is No space for new faculty
	 */
	public void HireFaculty(Faculty faculty)  throws NoSpaceException
	{
		if (this.faculty.size() == MAX_NUMBER_OF_FACULTY) {
            throw new NoSpaceException("No space for new faculty");
        }
		boolean included = false;
		for(Faculty fac: this.faculty)
		{
			if(faculty.compareTo(fac) == 0)
				included = true;
		}
		if(!included)
		{
			this.faculty.add(faculty);
			this.NumOfFaculty = NumOfFaculty + 1;
			
			if(this.ProgramDirector.size() > 0)
			{
			for(ProgramDirector pd: this.ProgramDirector)
			{
				if (pd.getFaculty().size() == MAX_NUMBER_OF_FACULTIES_IN_PROGRAM_DIRECTORS) {
		            throw new NoSpaceException("No space for new faculty in Program Directory");
		        }
				if(faculty.getProgram().equals(pd.getProgram()))
				{
					pd.addFaculty(faculty);
					break;
				}
			}
			}
		}
	}

	/**
	 *  when a faculty is retired from the university
	 *  -You need to remove the faculty member’s information from the 
	 *  university faculty records.
	 *  -The designated Program Director will assign the students of the 
	 *  retired faculty to the next available faculty member (or members).
	 *  -Reassign the TAs for the next available Faculty (or Faculties).
	 *  -When reassigning advisees and TAs, there can be a scenario where all the 
	 *  faculty members in the university have reached their quota of advisees and
	 *  cannot handle the remaining students, or no TAs are needed. At this point, 
	 *  ignore these specific cases and implement this operation, assuming these 
	 *  steps can be achieved successfully.
	 * @param Faculty and sets it at the end of the arrayList
	 * @throws NoSpecialtyException: if no faculty in the department has the same specialty as the retired faculty
	 */

	public void RetireFaculty(Faculty faculty)  throws  NoSpecialtyException  {
		// remove the faculty member’s information from the university faculty records.
				this.faculty.remove(faculty);
		
		
		 boolean includesSpeciality = false;
	        for (Faculty fac : this.faculty) {
	            if (fac.getProgram().equals(faculty.getProgram())) {
	            	includesSpeciality = true;
	                break;
	            }
	        }
	        if (!includesSpeciality) {
	            throw new NoSpecialtyException();
	        }
		
		
		
		
		// moving all its UGrad students to the next available faculty
		for (UGrad ugrad : this.UGradStudents)
		{
            if (ugrad.getAdvisor().equals(faculty))
            {
                for (Faculty fac : this.faculty)
                {
                    if (fac.getAdvisingUgrads().size() <= 7)
                    {
                    	fac.addAdvisingUgrads(ugrad);
                        ugrad.setAdvisor(fac);
                        break;
                    }
                }
            }
        }
		
		// moving all its TAs students to the next available faculty
		
		for (Grad grad : this.GradStudents) 
		{
            if (grad.getAdvisor().equals(faculty))
            {
                for (Faculty fac : this.faculty) 
                {
                    if (fac.getTAs().size() <= 4)
                    {
                    	fac.addTAs(grad);
                        grad.setAdvisor(fac);
                        break;
                    }
                }
            }
        }
		
	}
	
	/**
	 * a getter for the number of faculty in the department 
	 * @return ChairPerson
	 */
	public int getNumOfFaculty() {
		return NumOfFaculty;
	}
   

	
	/**
	 * this is a setter for the field attribute numOfFaculty that count the number of the Faculties in the department 
	 * @param numOfFaculty
	 */
	public void setNumOfFaculty(int numOfFaculty) {
		NumOfFaculty = numOfFaculty;
	}
	
	/**
	 * a getter for the number of faculty in the department 
	 * @return ChairPerson
	 */
	public int getNumOfGradStudents() {
		return NumOfGradStudents;
	}
   
	
	/**
	 * this is a setter for the field attribute numOfFaculty that count the number of the Faculties in the department 
	 * @param numOfFaculty
	 */
	public void setNumOfGradStudents(int NumOfGradStudents) {
		this.NumOfGradStudents = NumOfGradStudents;
	}
	
	/**
	 * a getter for the TAs in the department 
	 * @return ChairPerson
	 */
	public ArrayList<Grad> getGradStudents() {
		return this.GradStudents;
	}
	
	
	/**
	 * this method is used  when the department admits a new undergraduate student
	 * -add the student information to the department student record
	 * -assign a faculty to every newly admitted undergrad student
	 * -add the student to the corresponding faculty record
	 * -The undergrad students are assigned to faculty on a first-come-first-serve basis.
	 * -The department should not admit the same student twice (i.e., no duplicate student in the university -student record)
	 * @author andro
	 * @parameter student --> UGrad
	 * @throws NoSpaceException --> No space for new student
	 */
	public void AdmitStudent(UGrad studnet) throws NoSpaceException
	{
		
		if (UGradStudents.size() == MAX_NUMBER_OF_UNDERGRAD_STUDENTS) {
            throw new NoSpaceException("No space for new student");
		}
        else
        {
			addUGradStudents(studnet);
			if(NumOfUGradStudents % 8 == 1)
			{
				this.FaucultyIndex =  NumOfUGradStudents/8;
			}
			this.faculty.get(FaucultyIndex).addAdvisingUgrads(studnet);
			studnet.setAdvisor(this.faculty.get(FaucultyIndex));
         }
	}
	
	/**
	 * this method clear/remove the student’s information from the university students’ record after graduation
	 * removes student from the faculty and decrease the number of uGrad students in bothFaculty and CS department
	 * @param studnet
	 */
	public boolean AlumnusUGrad(UGrad student)
	{
		for(Faculty fas: this.faculty)
		{
			if(fas.getAdvisingUgrads().contains(student))
			{
				fas.getAdvisingUgrads().remove(student);
				fas.setNumOfAdvisingUGrads(fas.getNumOfAdvisingUGrads()-1);
				break;
			}
		}
		this.NumOfUGradStudents = NumOfUGradStudents - 1 ;
		return this.UGradStudents.remove(student);
	}


	
	
	/**
	 * this is method is  when the department admits a new graduate student
	 * -store the grad student information in the department grad record
	 * -assign the grad student as a TA to a faculty who has not exceeded the TAs limit yet
	 * -The department cannot admit the same grad student twice. Hence, there is no duplicate
	 * -The TAs are assigned to faculty on a first-come-first-serve basis.
	 * @param Student and sets it at the end of the arrayList
	 * @threw NoSpaceException: No space for a new TA
	 */

	public void HireTA(Grad Student)  throws NoSpaceException
	{
		 if (GradStudents.size() == MAX_NUMBER_OF_GRAD_STUDENTS)
		 {
	            throw new NoSpaceException("No space for a new TA");
		 }
	     else
	     {
			boolean included = false;
			for(Grad stu: this.GradStudents)
				if(Student.compareTo(stu) == 0)
					included = true;
			if(!included)
			{
				this.GradStudents.add(Student);
				this.NumOfGradStudents = NumOfGradStudents + 1;
				if(NumOfGradStudents % 5 == 1)
				{
					this.FaucultyIndexForTAS =  NumOfGradStudents/5;
				}
				this.faculty.get(FaucultyIndexForTAS).addTAs(Student);
				Student.setAdvisor(this.faculty.get(FaucultyIndexForTAS));
			}
			
		}
	}
	
	
	
	
	
	
	/**
	 * this method clear/remove the student’s information from the university students’ record
	 * removes student from the faculty and decrease the number of uGrad students in bothFaculty and CS department
	 * @param studnet
	 * @throws NoTAException: f a TA is graduating from the department, but no other TA is 
	 * available under supervision/work with a particular faculty, you need to 
	 * handle this situation by using exceptions.
	 */
	public void AlumnusGrad(Grad student) throws  NoTAException
	{
		for(Faculty fas: this.faculty)
		{
			if(fas.getTAs().contains(student))
			{
				fas.getTAs().remove(student);
				fas.setNumOfTAs(fas.getNumOfTAs()-1);
				break;
			}
		}
		this.NumOfGradStudents = NumOfGradStudents - 1 ;
		this.GradStudents.remove(student);
		

		if (student.getAdvisor().getTAs().size() == 0) {
            throw new NoTAException();
        }
		
	}
	
	/**
	 * this method sort and
	 * @return: GradStudents --> all the Grad students information stored in the department record as a list of Grad Students.
	 */
	public ArrayList<Grad> ExtractAllGradDetails()
	{
		Collections.sort(this.GradStudents);         
	    return this.GradStudents;  
	}
	
	
	 /**
	  * this method  extract and return all the undergraduate 
	  * students information stored in the university students’ record as a sorted list of 
	  * students according to the student's full name.
	  * a. The student’s full name is defined as a concatenation of first name and last 
	  * name as follows: if a student's first name is "John" and the last name is 
	  * "Smith", the student's full name is "John, Smith".
	  * @return UGradStudents -->  sorted according to the student's full name.
	  */
	public ArrayList<UGrad> ExtractAllUGradDetails()
	{
		Collections.sort(this.UGradStudents);         
	    return this.UGradStudents;  
	}
	
	
	/**
	  * this method  extract and return all the information of faculty 
	  * members stored in the university faculty records as a sorted list of faculty according to 
	  * the faculty’s full name.  The faculty’s full name is defined as a concatenation of first name and last name 
	  * as follows: if a faculty’s first name is "Sara" and the last name is "Adams", the 
	  * faculty's full name is "Sara, Adams"
	  * @return faculty -->  sorted according to the student's full name.
	  */
	public ArrayList<Faculty> ExtractAllFacultyDetails()
	{
		Collections.sort(this.faculty);         
	    return this.faculty;  
	}
	
	/**
	  * this method extract and return all the faculty
	  * information belonging to a particular program as a sorted list of faculty 
	  * members according to the faculty's full name.
	  * a. The faculty’s full name is defined as a concatenation of first name and last name 
	  * as follows: if a faculty’s first name is "Sara" and the last name is "Adams", the 
	  * faculty's full name is "Sara, Adams".
	  * @param program --> String represent the program of the faculty
	  * @return Faculty -->  sorted list according to the student's full name, And the given program 
	  */
	public ArrayList<Faculty> ExtractFacultyDetails(String program)
	{
		Collections.sort(this.faculty);   
		
		ArrayList<Faculty> programFaculties = new ArrayList<Faculty>();
       for (Faculty fac : this.faculty)
       {
           if (fac.getProgram().equals(program)) {
           	programFaculties.add(fac);
           }
		
       }
       return programFaculties;  
	}
	
	
	/**
	  *  this method extract and return all the students information 
	  *  that are advisees of a particular faculty as a sorted list of students according to the
	  *  student’s full name.
	  *  a. The student’s full name is defined as a concatenation of first name and last 
	  *  name as follows: if a student's first name is "John" and the last name is 
	  *  "Smith", the student's full name is "John, Smith".
	  *  @param fac --> Faculty represent the Faculty of the students
	  *  @return FacultiesUGrad --> a list of all the students in the given faculty
	  */
	public ArrayList<UGrad> ExtractAdviseesDetails(Faculty fac)
	{
		Collections.sort(this.UGradStudents);   
		ArrayList<UGrad> FacultiesUGrad = new ArrayList<UGrad>();
		
      for (UGrad Ugrad : this.UGradStudents)
      {
          if (Ugrad.getAdvisor().equals(fac)) {
        	  FacultiesUGrad.add(Ugrad);
          }
		
      }
      return FacultiesUGrad;  
	}
	
	
	/**
	  *  this method extract and return all the Grad student information 
	  *  assigned to a particular faculty as a list of TAs.
	  *  @param fac --> Faculty represent the Faculty of the students
	  *  @return FacultiesUGrad --> a list of all TA in the given faculty
	  */
	public ArrayList<Grad> ExtractTAsDetails(Faculty fac)
	{
		Collections.sort(this.GradStudents);   
		ArrayList<Grad> FacultiesGrad = new ArrayList<Grad>();
		
     for (Grad grad : this.GradStudents)
     {
         if (grad.getAdvisor().equals(fac)) {
       	  FacultiesGrad.add(grad);
         }
		
     }
     return FacultiesGrad;  
	}

	
	/**
	 * this method is a getter for the arrayList ProgramDirector
	 * @return an arrayList of ProgramDirector
	 */
	public ArrayList<ProgramDirector> getProgramDirector() {
		return ProgramDirector;
	}

	
	/**
	 * this method add the given program directory to the CS department
	 * @param programDirector: the given program director
	 * @throws NoSpaceException: No more than 3 program directors
	 */
	public void addProgramDirector(ProgramDirector programDirector) throws NoSpaceException 
	{
		if (this.ProgramDirector.size() == MAX_NUMBER_OF_PROGRAM_DIRECTORS) {
            throw new NoSpaceException("No more than 3 program directors");
        }
		
		boolean included = false;
		for(ProgramDirector PD: this.ProgramDirector)
			if(PD.equals(programDirector) || PD.getProgram().equals(programDirector.getProgram()))
				included = true;
		if(!included)
		{
		this.ProgramDirector.add(programDirector);
		this.numberOfProgramDirector = this.numberOfProgramDirector + 1;
		}
	}
}







/**
 * This class implement person, which is the mother class of all the classes in the CSD
 * Have instance variable: First name, Last name, Age, Gender, and Address
 * @author andro
 *
 */
abstract class person
{
	private String FirstName;
	private String LastName;
	private int age;
	private String Gender;
	private String Address;
	
	/**
	 * Constructor for person
	 *  @param FirstName -->> represent the first name 
	 * @param LastName  -->> represent the last name 
	 * @param age  -->> represent the age
	 * @param Gender  -->> represent the gender male or female
	 * @param Address -->> represent the address
	 */
	person(String FirstName, String LastName, int age , String Gender,  String Address)
	{
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.age = age;
		this.Gender = Gender;
		this.Address = Address;
	}
	
	/**
	 * getter for attribute First name
	 * @return FirstName
	 */
	
	public String getFirstName()
	{
		return this.FirstName;
	}
	
	/**
	 * getter for attribute last name
	 * @return LastName
	 */
	
	public String getLastName()
	{
		return this.LastName;
	}
	
	/**
	 * getter for attribute age
	 * @return age
	 */
	
	public int getAge()
	{
		return this.age;
	}
	
	/**
	 * getter for attribute Gender
	 * @return Gender
	 */
	
	public String getGender()
	{
		return this.Gender;
	}
	
	/**
	 * getter for attribute Address
	 * @return Address
	 */
	
	public String getAddress()
	{
		return this.Address;
	}
	
	/**
	 * setter for FirstName
	 * @param name
	 * sets FirstName to name
	 */
	
	public void setFirstName(String name)
	{
		this.FirstName = name;
	}
	
	/**
	 * setter for LastName
	 * @param name
	 * sets LastName to name
	 */
	
	public void setLastName(String name)
	{
		this.LastName = name;
	}
	
	/**
	 * setter for age
	 * @param age
	 * sets LastName to age
	 */
	
	public void setLastName(int age)
	{
		this.age = age;
	}
	
	/**
	 * setter for Gender
	 * @param Gender
	 * sets LastName to Gender
	 */
	
	public void setGenderName(String Gender)
	{
		this.Gender = Gender;
	}
	
	/**
	 * setter for Address
	 * @param Address
	 * sets LastName to Address
	 */
	
	
	public void setAddressName(String Address)
	{
		this.Address = Address;
	}
	
	/**
	 * @Override toString for class person
	 * will print out the String representation for class person
	 */
	@Override 
	public String toString()
	{
		String StrPerson = "[" + this.FirstName + ", " + this.LastName+", " + this.age + ", "+ this.Gender + ", " + this.Address + "]";
		return StrPerson;
	}
	
	/**
	 * this method return the full name of the person
	 * if the persons first name is "Sara" and the last name is "Adams", the persons full name is "Sara Adams"
	 * @return
	 */
	public String getFullName()
	{
	    return getFirstName() + ", " + getLastName();
	}
	
	
	

}



/**
 * This class implement Academics, which is the mother class for administrator and facility
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. Moreover Employee ID, and Salary
 * 
 * @author andro
 *
 */

abstract class Academics extends person
{
	private String Program;
	private static int ID = 99;
	private double Salary;
	private int EmployeeId;
	
	/**
	 * 
	 * @param FirstName --> call person -->> represent the first name 
	 * @param LastName --> call person -->> represent the last name 
	 * @param age --> call person -->> represent the age
	 * @param Gender --> call person -->> represent the gender male or female
	 * @param Address --> call person -->> represent the address
	 * The CSD assigns a unique employee ID to each Academic, faculty, or administrator.
	 *  Employee IDs start at 100. --> static
	 */
	
	Academics(String FirstName, String LastName, int age, String Gender, String Address) {
		super(FirstName, LastName, age, Gender, Address);
		ID = ID + 1; 
		this.EmployeeId = ID;
		this.Salary = 0;
	}
	
	/**
	 * getter for EmployeeId
	 * @return EmployeeId --> static
	 */
	
	public int getEmployeeID()
	{
		return this.EmployeeId;
	}
	
	
	/**
	 * getter for Salary
	 * @return Salary
	 */
	
	public double getSalary()
	{
		return this.Salary;
	}
	
	
	/**
	 * setter for Salary
	 * @param Salary
	 * sets Salary to Salary
	 */
	
	public void setSalary(double Salary)
	{
		this.Salary = Salary;
	}
	
	
	
	/**
	 * setter for Program
	 * @param Salary
	 * sets Salary to Program
	 */
	
	public void setProgram(String Program)
	{
		this.Program = Program;
	}
	
	
	
	/**
	 * getter for Program
	 * @return Program
	 */
	
	public String getProgram()
	{
		return this.Program;
	}
	

	/**
	 * @Override toString for class Academics
	 * will print out the String representation for class Academics
	 */
	@Override 
	public String toString()
	{
		String StrAcademics = "[["+this.getEmployeeID() +", "+ this.getSalary() + super.toString() +"]]";
		return StrAcademics;
	
	
	}
}



/**
 * This class implement Student, which is the mother class for UGrad and Grade
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. Moreover SutentId
 * @author andro
 *
 */
abstract class Student extends person  implements Comparable<Student>
{
	private static int ID = 999;
	private int StudentId;
	/**
	 * 
	 * @param FirstName --> call person -->> represent the first name 
	 * @param LastName --> call person -->> represent the last name 
	 * @param age --> call person -->> represent the age
	 * @param Gender --> call person -->> represent the gender male or female
	 * @param Address --> call person -->> represent the address
	 * The university assigns a unique Student ID to each student starting from 1000.  --> static
	 */
	Student(String FirstName, String LastName, int age, String Gender, String Address) {
		super(FirstName, LastName, age, Gender, Address);
		ID = ID + 1;
		StudentId = ID;
	}
	
	/**
	 * getter for SutendId
	 * @return SutendId --> static
	 */
	
	public int getStudentId()
	{
		return this.StudentId; 
	}

	
	/**
	 * CompareTo implement the interface Comparable, it compare two Students according to name
	 * @param other --> Student
	 * @return 0 --> if the two compared students are the same person
	 * 		   1 --> if the Student full name is Alphabetical more than the other string
	 * 		  -1 --> if the Student full name is Alphabetical less than the other string 
	 */
	@Override
	public int compareTo(Student other) {
		if (this.getFirstName().equals(other.getFirstName())
				&& this.getLastName().equals(other.getLastName()) 
				&& this.getAge() == other.getAge()
				&& this.getGender() == other.getGender()
				&& this.getAddress() == other.getAddress()
				&& this.getStudentId() == other.getStudentId())
		return 0;
		if(this.getFullName().compareTo( other.getFullName()) < 1)
		{
			return -1;
		}
		else		 
			return 1;
		
	}
	
	
	/**
	 * @Override toString for class Student
	 * will print out the String representation for class Student
	 */
	@Override 
	public String toString()
	{
		String StrStudent = "["+ this.getStudentId() +"["+ super.toString() +"]]";
		return StrStudent;
	
	
	}
}




/**
 * Administrators are 2 types Chairperson and Program Director.
 * Administrator is the mother class for ChairPerson and ProgramDirector.
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. 
 * @author andro
 *
 */
abstract class Administrator extends Academics
{
	
/**
 * @param FirstName --> call person -->> represent the first name 
 * @param LastName --> call person -->> represent the last name 
 * @param age --> call person -->> represent the age
 * @param Gender --> call person -->> represent the gender male or female
 * @param Address --> call person -->> represent the address
 */
	Administrator(String FirstName, String LastName, int age, String Gender, String Address) {
		super(FirstName, LastName, age, Gender, Address);
		
	}
	
}



/**
 * this class is the first type of Administrators 
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. 
 * @author andro
 *
 */
class ChairPerson extends Administrator
{
/**
  * @param FirstName --> call person -->> represent the first name 
  * @param LastName --> call person -->> represent the last name 
  * @param age --> call person -->> represent the age
  * @param Gender --> call person -->> represent the gender male or female
  * @param Address --> call person -->> represent the address
 */
	ChairPerson(String FirstName, String LastName, int age, String Gender, String Address) {
		super(FirstName, LastName, age, Gender, Address);
		
	}
	
	
	/**
	 * @Override toString for class ChairPerson
	 * will print out the String representation for class chairperson
	 */
	@Override 
	public String toString()
	{
		String StrChairPerson = "Chair Person [" + super.toString()+"]";
		return StrChairPerson;
	
	
	}
	
}





/**
 * this class is the second type of Administrators
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. 
 * Software Engineering, and Digital Technology
 * has the field attributes:
 * --> Faculty: represent the faculties under the programDirector
 * --> NumberOfFaculties: the number of faculties under the programDirector
 * @author andro
 *
 */
class ProgramDirector extends Administrator
{
	private ArrayList<Faculty> Faculty;
	private int NumberOfFaculties;
/**
 * 
 * @param FirstName --> call person -->> represent the first name 
 * @param LastName --> call person -->> represent the last name 
 * @param age --> call person -->> represent the age
 * @param Gender --> call person -->> represent the gender male or female
 * @param Address --> call person -->> represent the address
 */
	ProgramDirector(String FirstName, String LastName, int age, String Gender, String Address)
	{
		super(FirstName, LastName, age, Gender, Address);
		this.Faculty = new  ArrayList<Faculty>();
		this.setNumberOfFaculties(0);
	}
	/**
	 * this is a getter for the field attribute facility
	 * @return
	 */
	public ArrayList<Faculty> getFaculty()
	{
		return Faculty;
	}
	/**
	 * this method add a new Faculty to the ProgramDirector
	 * @param faculty
	 */
	public void addFaculty(Faculty faculty)
	{
		boolean included = false;
		for(Faculty fas: this.Faculty)
		{
			if(faculty.compareTo(fas) == 0)
				included = true;
		}
		if(!included)
		{
		this.Faculty.add(faculty);
		this.NumberOfFaculties = NumberOfFaculties + 1;
		}
	}
	/**
	 * this is a getter for the NumberOfFaculties in the ProgramDirector
	 * @return NumberOfFaculties --> Number Of Faculties
	 */
	public int getNumberOfFaculties() {
		return NumberOfFaculties;
	}
	/**
	 * this is a setter for the NumberOfFaculties in the ProgramDirector
	 * @param numberOfFaculties --> Number Of Faculties
	 */
	public void setNumberOfFaculties(int numberOfFaculties) {
		this.NumberOfFaculties = numberOfFaculties;
	}
	
	
	/**
	 * this objects checks if the two ProgramDirectors are the same person or not 
	 * @param other --> ProgramDirector
	 * @return true if they are the same person, and false other wise.
	 */
	public boolean equals(ProgramDirector other)
	{
		if (this.getFirstName().equals(other.getFirstName())
				&& this.getLastName().equals(other.getLastName()) 
				&& this.getAge() == other.getAge()
				&& this.getGender() == other.getGender()
				&& this.getAddress() == other.getAddress())
		return true;
		else
		return false;
	}
	
}





/**
 * this class extends Academics and implements comparable,  can have several undergrad Students assigned at the same time.
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. 
 * -program- which represent which program the Faculty belongs too.
 * -AdvisingUgrads- represent the list of under grads students that facility is assigned to advise 
 * -NumOfUGradStudents- represent the number of under grad students assigned to the faculty
 * -TAs-  TAs in the faculty
 * -NUmberOfTAs- the number of TAs in the faculty
 * @author andro
 *
 */
class Faculty extends Academics implements Comparable<Faculty>
{
	
	private ArrayList<UGrad> AdvisingUgrads;
	private int NumOfAdvisingUGrads;
	private ArrayList<Grad> TAs;
	private int NumOfTAs;
	
	
/**
 * 
 * @param FirstName --> call person -->> represent the first name 
 * @param LastName --> call person -->> represent the last name 
 * @param age --> call person -->> represent the age
 * @param Gender --> call person -->> represent the gender male or female
 * @param Address --> call person -->> represent the address
 */
	Faculty(String FirstName, String LastName, int age, String Gender, String Address) {
		super(FirstName, LastName, age, Gender, Address);
		AdvisingUgrads = new ArrayList<UGrad>();
		this.TAs = new ArrayList<Grad>();
		this.NumOfTAs = 0;
		
	}
	

	

	/**
	 * getter for  AdvisingUgrads
	 * @return  AdvisingUgrads
	 */

	public ArrayList<UGrad> getAdvisingUgrads() {
		return this.AdvisingUgrads;
	}

	/**
	 * this method adds a new student of the lest of the advising Under grad of the Faculty
	 * @param student
	 */

	public void addAdvisingUgrads(UGrad student) {
		boolean included = false;
		for(UGrad stu: this.AdvisingUgrads)
			if(stu.compareTo(student) == 0)
				included = true;
		if(!included)
		{
		AdvisingUgrads.add(student);
		NumOfAdvisingUGrads ++;
		}
	}
	
	/**
	 * a getter for NumOfUGradStudents
	 * this method return the number of under grad students assigned to the Faculty
	 * @return NumOfUGradStudents
	 */
	public int getNumOfAdvisingUGrads() {
		return NumOfAdvisingUGrads;
	}

	/**
	 * a setter for numOfUGradStudents
	 * this method sets the value of numOfUGradStudents to the given numOfUGradStudents
	 * @param numOfUGradStudents
	 */

	public void setNumOfAdvisingUGrads(int NumOfAdvisingUGrads) {
		this.NumOfAdvisingUGrads = NumOfAdvisingUGrads;
	}
	
	
	/**
	 * this is a getter for the arrayList for the TAs in the Faculty 
	 * @return TAs --> arrayList
	 */
	
	public ArrayList<Grad> getTAs() {
		return this.TAs;
	}

	/**
	 * this method add a new TA to the end of the ArrayList
	 * also add one to the number of TAs
	 * @param tA
	 */

	public void addTAs(Grad tA) {
		this.TAs.add(tA);
		NumOfTAs = NumOfTAs +1;
	}

	/**
	 * a setter for NumberOfTAs
	 * @param NumberOfTAs
	 */
	public void setNumOfTAs(int NumberOfTAs)
	{
		this.NumOfTAs=NumberOfTAs;
	}
	
	/**
	 * a getter for NumberOfTAs
	 * @return NumberOfTAs
	 */
	public int getNumOfTAs()
	{
		return this.NumOfTAs;
	}
	/**
	 * @Override toString for class Faculty
	 * will print out the String representation for class Faculty
	 */
	@Override 
	public String toString()
	{
		String StrFaculty = "Faculty "+this.getProgram()  + super.toString() ;
		return StrFaculty;
	}

	/**
	 * CompareTo implement the interface Comparable, it compare two Faculties according to name
	 * @param other --> Faculty
	 * @return 0 --> if the two compared Faculties are the same person
	 * 		   1 --> if the Faculty full name is Alphabetical more than the other string
	 * 	      -1 --> if the Faculty full name is Alphabetical less than the other string
	 */

	@Override
	public int compareTo(Faculty other) {
		if (this.getFirstName().equals(other.getFirstName())
				&& this.getLastName().equals(other.getLastName()) 
				&& this.getAge() == other.getAge()
				&& this.getGender() == other.getGender()
				&& this.getAddress() == other.getAddress()
				&& this.getEmployeeID() == other.getEmployeeID()
				&& this.getProgram() == other.getProgram()
				&& this.getSalary() == other.getSalary() )
					return 0;
		if(this.getFullName().compareTo( other.getFullName()) < 1)
		{
			return -1;
		}
		else		 
			return 1;
	}
	
}




/**
 * this class extends Student, can have several undergrad Students assigned at the same time.
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. 
 * -Advisor: an Undergrad Student has a designated -Faculty- assigned as an academic advisor.
 * 
 * @author andro
 *
 */
class UGrad  extends Student
{
	
	private Faculty Advisor;
/**
 * 
 * @param FirstName --> call person -->> represent the first name 
 * @param LastName --> call person -->> represent the last name 
 * @param age --> call person -->> represent the age
 * @param Gender --> call person -->> represent the gender male or female
 * @param Address --> call person -->> represent the address
 */
	UGrad(String FirstName, String LastName, int age, String Gender, String Address)
	{
		super(FirstName, LastName, age, Gender, Address);
		
	}
	
	
	
	/**
	 * a getter for the academic advisor assigned to the under grad student
	 * @return Advisor
	 */
	public Faculty getAdvisor()
	{
	return this.Advisor;
	}

	
	/**
	 * sets the academic advisor to the given Advisor
	 * @param Advisor
	 */
	public void setAdvisor(Faculty Advisor) {
		this.Advisor = Advisor;
	}
	
	
	/**
	 * @Override toString for class Faculty
	 * will print out the String representation for class Faculty
	 */
	@Override 
	public String toString()
	{
		String StrFaculty = "Undergraduate " + super.toString() ;
		return StrFaculty;
	
	}
	
}




/**
 * this class extends Student, can have several undergrad Students assigned at the same time.
 * An Undergrad Student has a designated Faculty assigned as an academic advisor.
 * Have instance variable: First Name, Last name, Age, Gender, Address --> from person. 
 * @author andro
 *
 */
class Grad extends Student
{
	private Faculty Advisor;
	
/**
 * 
 * @param FirstName --> call person -->> represent the first name 
 * @param LastName --> call person -->> represent the last name 
 * @param age --> call person -->> represent the age
 * @param Gender --> call person -->> represent the gender male or female
 * @param Address --> call person -->> represent the address
 */
	Grad(String FirstName, String LastName, int age, String Gender, String Address)
	{
		super(FirstName, LastName, age, Gender, Address);
		
	}
	
	/**
	 * this method is a getter for the advisor assigned to the TA/Grad
	 * @return Advisor
	 */
	public Faculty getAdvisor()
	{
	return this.Advisor;
	}

	/**
	 * this method is a setter for the Faculty advisor
	 * @param Advisor
	 */
	public void setAdvisor(Faculty Advisor)
	{
		this.Advisor = Advisor;
	}
	
	/**
	 * @Override toString for class Faculty
	 * will print out the String representation for class Faculty
	 */
	@Override 
	public String toString()
	{
		String StrFaculty = "Graduate " + super.toString() ;
		return StrFaculty;
	
	}

}





/**
 * This is a user defined exception used
 * if the university reaches the maximum quota for students (i.e., you 
 * cannot admit any new student), Also used with --> Program Directs, and Faculty
 * @author andro
 *
 */
	
class NoSpaceException extends Exception
{
	/**
	 * if the university reaches the maximum quota for students (i.e., you 
	 * cannot admit any new student), Also used with --> Program Directs, and Faculty
	 * 
	 */
	
	public NoSpaceException()
	{
		super();
	}
	/**
	 * if the university reaches the maximum quota for students (i.e., you 
	 * cannot admit any new student), Also used with --> Program Directs, and Faculty
	 * @param message: write an Exception message
	 */
	public NoSpaceException(String message)
	{
		super(message);
	}
}

/**
 * This is a user defined exception used
 * if a faculty member is retiring from the university, but no other faculty 
 * is available with the same specialty of the program
 * @author andro
 *
 */

class NoSpecialtyException extends Exception
{
	/**
	 * if a faculty member is retiring from the university, but no other faculty 
	 * is available with the same specialty of the program 
		 */
		public NoSpecialtyException()
	{
		super();
	}
	/**
	 * if a faculty member is retiring from the university, but no other faculty 
	 * is available with the same specialty of the program  
	 * @param message: write an Exception message
	 */
	public NoSpecialtyException(String message)
	{
		super(message);
	}
}

/**
 * This is a user defined exception used
 * if a TA is graduating from the department, but no other TA is 
 * available under supervision/work with a particular faculty, you need to 
 * handle this situation by using exceptions.
 * @author andro
 *
 */
class NoTAException extends Exception
{
	/**
	 * if a TA is graduating from the department, but no other TA is 
	 * available under supervision/work with a particular faculty, you need to 
	 * handle this situation by using exceptions.
	 */
	public NoTAException()
	{
		super("No TA Available");
	}
	/**
	 * if a TA is graduating from the department, but no other TA is 
	 * available under supervision/work with a particular faculty, you need to 
	 * handle this situation by using exceptions.
	 * @param message:  write an Exception message
	 */
	public NoTAException(String message)
	{
		super(message);
	}
}