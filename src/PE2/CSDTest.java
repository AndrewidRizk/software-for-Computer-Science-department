package PE2;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CSDTest {
    @Test
    @Order(1)
    void CheckChairEmployeeID100(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        assertEquals(100, chair.getEmployeeID(), "ChairPerson getID Fail");
    }
    @Test
    @Order(2)
    void ShouldCompareDeptChairPerson(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        assertEquals(chair, csd.getChairPerson(), "CS Dept getChairPerson Fail");
    }
    @Test
    @Order(3)
    void CheckChairID102AndFacultyID103(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female",
                "Lawrence Avenue East");
        f.setProgram("Computer Science");
        assertEquals(102, chair.getEmployeeID(), "ChairPerson getID Fail");
        assertEquals(103, f.getEmployeeID(), "Faculty getID Fail");

    }
    @Test
    @Order(4)
    void CheckProgramNameCorrect(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female",
                "Lawrence Avenue East");
        f.setProgram("Computer Science");
        assertEquals("Computer Science", f.getProgram(), "CS Dept getProgram Fail");
        assertFalse(f.getProgram().equals("Computer"), "CS Dept getProgram Fail");
    }
    @Test
    @Order(5)
    void CheckToStringChair(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        chair.setSalary(12345.6);
        assertEquals("Chair Person [[[106, 12345.6[Rebert, Jack, 59, Male, Birchmount Road]]]]", chair.toString());
    }
    @Test
    @Order(6)
    void CheckToStringFaculty(){
        Faculty f = new Faculty("Rebert", "Jack", 59, "Male", "Birchmount Road");
        f.setProgram("Computer Science");
        f.setSalary(123.4);
        assertEquals("Faculty Computer Science[[107, 123.4[Rebert, Jack, 59, Male, Birchmount Road]]]", f.toString());
    }
    @Test
    @Order(7)
    void CheckToStringUgrad(){
        UGrad s = new UGrad("Rebert", "Jack", 59, "Male", "Birchmount Road") {};
        assertEquals("Undergraduate [1000[[Rebert, Jack, 59, Male, Birchmount Road]]]", s.toString());
    }
    @Test
    @Order(8)
    void CheckToStringGrad(){
        Grad s = new Grad("Rebert", "Jack", 59, "Male", "Birchmount Road") {};
        assertEquals("Graduate [1001[[Rebert, Jack, 59, Male, Birchmount Road]]]", s.toString());
    }
    @Test
    @Order(9)
    void HireFaculty(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        UGrad s = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
        }
        catch(NoSpaceException e){
        }
        assertEquals(f, csd.getFaculty().get(0), "Hire Faculty Failed");
        assertEquals(1, csd.getNumOfFaculty(), "Hire Faculty Failed");
    }
    @Test
    @Order(10)
    void HireFacultyDuplicate(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        UGrad s = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
            csd.HireFaculty(f);
        }
        catch(NoSpaceException e){
        }
        assertEquals(f, csd.getFaculty().get(0), "Hire Faculty Failed");
        assertEquals(1, csd.getNumOfFaculty(), "Hire Faculty Failed");
    }
    @Test
    @Order(11)
    void HireMultipleFaculty(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Adam", "Tom", 55, "Male", "Danforth Road");
        f2.setProgram("Computer Science");
        Grad s = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireFaculty(f1);
        }
        catch(NoSpaceException e){
        }
        assertEquals(f1, csd.getFaculty().get(0), "Hire Faculty Failed");
        assertEquals(f2, csd.getFaculty().get(1), "Hire Faculty Failed");
        assertEquals(2, csd.getNumOfFaculty(), "Hire Faculty Failed");
    }
    @Test
    @Order(12)
    void AdmitStudent(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        UGrad s = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
            csd.AdmitStudent(s);
        }
        catch(NoSpaceException e){
            fail();
        }
        assertEquals(true, f.getAdvisingUgrads().contains(s), "Admit Undergrad Failed");
        assertEquals(s, f.getAdvisingUgrads().get(0), "Admit Undergrad Failed");
        assertEquals(s.getAdvisor(), f, "Assign Advisor Undergrad Failed");
        assertEquals(1, csd.getNumOfUGradStudents(), "Increment UGrad count in Dept Failed");

    }
    @Test
    @Order(13)
    void AdmitStudentDuplicate(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        UGrad s = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
            csd.AdmitStudent(s);
            csd.AdmitStudent(s);
        }
        catch(NoSpaceException e){
            fail();
        }
        assertEquals(true, f.getAdvisingUgrads().contains(s), "Admit Undergrad Failed");
        assertEquals(s, f.getAdvisingUgrads().get(0), "Admit Undergrad Failed");
        assertEquals(s.getAdvisor(), f, "Assign Advisor Undergrad Failed");
        assertEquals(1, csd.getNumOfUGradStudents(), "Increment UGrad count in Dept Failed");
    }
    @Test
    @Order(14)
    void AdmitStudentMultiple(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        UGrad s1 = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        UGrad s2 = new UGrad("Jeremy", "Dave", 45, "Male", "Guildwood Parkway");
        UGrad s3 = new UGrad("Adam", "Tom", 55, "Male", "Danforth Road");
        UGrad s4 = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        UGrad s5 = new UGrad("George", "Hardy", 45, "Male", "Rockwood Drive");
        UGrad s6 = new UGrad("Radi", "Aman", 32, "Male", "Tawoon Road");
        UGrad s7 = new UGrad("John", "Mark", 27, "Male", "Pizza Road");
        UGrad s8 = new UGrad("Perseus", "Jackson", 21, "Male", "Candy Road");
        UGrad s9 = new UGrad("Sarah", "Walker", 15, "Female", "Qudra Road");
        UGrad s10 = new UGrad("Radi", "Mark", 35, "Male", "Some Road");

        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.AdmitStudent(s1);
            csd.AdmitStudent(s2);
            csd.AdmitStudent(s3);
            csd.AdmitStudent(s4);
            csd.AdmitStudent(s5);
            csd.AdmitStudent(s6);
            csd.AdmitStudent(s7);
            csd.AdmitStudent(s3);
            csd.AdmitStudent(s8);
            csd.AdmitStudent(s9);
            csd.AdmitStudent(s10);

        }
        catch(NoSpaceException e){
            fail();
        }
        assertTrue(f1.getAdvisingUgrads().contains(s1), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s2), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s3), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s4), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s5), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s6), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s7), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getAdvisingUgrads().contains(s8), "Assign Undergrad to Advisor Failed");
        assertFalse(f1.getAdvisingUgrads().contains(s9), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getAdvisingUgrads().contains(s9), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getAdvisingUgrads().contains(s10), "Assign Undergrad to Advisor Failed");
        assertEquals(s3.getAdvisor(), f1, "Assign Advisor to Undergrad Failed");
        assertEquals(10, csd.getNumOfUGradStudents(), "Increment UGrad count in Dept Failed");
        assertEquals(2, csd.getNumOfFaculty(), "Increment Faculty count in Dept Failed");

    }
    @Test
    @Order(15)
    void HireTA(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        Grad s = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
            csd.HireTA(s);
        }
        catch(NoSpaceException e){
            fail();
        }
        assertEquals(true, f.getTAs().contains(s), "Admit Undergrad Failed");
        assertEquals(s, f.getTAs().get(0), "Admit Undergrad Failed");
        assertEquals(s.getAdvisor(), f, "Assign Advisor Undergrad Failed");
        assertEquals(1, csd.getNumOfGradStudents(), "Increment UGrad count in Dept Failed");

    }
    @Test
    @Order(16)
    void HireTADuplicate(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        Grad s = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
            csd.HireTA(s);
            csd.HireTA(s);
        }
        catch(NoSpaceException e){
            fail();
        }
        assertEquals(true, f.getTAs().contains(s), "Admit Undergrad Failed");
        assertEquals(s, f.getTAs().get(0), "Admit Undergrad Failed");
        assertEquals(s.getAdvisor(), f, "Assign Advisor Undergrad Failed");
        assertEquals(1, csd.getNumOfGradStudents(), "Increment UGrad count in Dept Failed");
    }
    @Test
    @Order(17)
    void HireTAMultiple(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Grad s1 = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("Jeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        Grad s5 = new Grad("George", "Hardy", 45, "Male", "Rockwood Drive");
        Grad s6 = new Grad("Radi", "Aman", 32, "Male", "Tawoon Road");
        Grad s7 = new Grad("John", "Mark", 27, "Male", "Pizza Road");
        Grad s8 = new Grad("Perseus", "Jackson", 21, "Male", "Candy Road");
        Grad s9 = new Grad("Sarah", "Walker", 15, "Female", "Qudra Road");
        Grad s10 = new Grad("Radi", "Mark", 35, "Male", "Some Road");

        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireTA(s1);
            csd.HireTA(s2);
            csd.HireTA(s3);
            csd.HireTA(s4);
            csd.HireTA(s5);
            csd.HireTA(s6);
            csd.HireTA(s7);
            csd.HireTA(s3);
            csd.HireTA(s8);
            csd.HireTA(s9);
            csd.HireTA(s10);

        }
        catch(NoSpaceException e){
            fail();
        }
        assertTrue(f1.getTAs().contains(s1), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getTAs().contains(s2), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getTAs().contains(s3), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getTAs().contains(s4), "Assign Undergrad to Advisor Failed");
        assertTrue(f1.getTAs().contains(s5), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getTAs().contains(s6), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getTAs().contains(s7), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getTAs().contains(s8), "Assign Undergrad to Advisor Failed");
        assertFalse(f1.getTAs().contains(s9), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getTAs().contains(s9), "Assign Undergrad to Advisor Failed");
        assertTrue(f2.getTAs().contains(s10), "Assign Undergrad to Advisor Failed");
        assertEquals(s3.getAdvisor(), f1, "Assign Advisor to Undergrad Failed");
        assertEquals(s10.getAdvisor(), f2, "Assign Advisor to Undergrad Failed");
        assertEquals(10, csd.getNumOfGradStudents(), "Increment UGrad count in Dept Failed");
        assertEquals(2, csd.getNumOfFaculty(), "Increment Faculty count in Dept Failed");

    }
    @Test
    @Order(18)
    void GraduateUndergrad(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f.setProgram("Software Engineering");
        UGrad s = new UGrad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f);
            csd.AdmitStudent(s);
            csd.AlumnusUGrad(s);
        }
        catch(NoSpaceException e){
            fail();
        }
        assertFalse(f.getAdvisingUgrads().contains(s), "Graduate Undergrad Failed");
        assertEquals(s.getAdvisor(), f, "Remove Advisor Link Failed");
        assertEquals(0, csd.getNumOfUGradStudents(), "Decrement UGrad count in Dept Failed");
        assertEquals(0, f.getNumOfAdvisingUGrads(), "Decrement UGrad count in Faculty Failed");
    }
    @Test
    @Order(19)
    void GraduateTA(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Grad s1 = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("Jeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireTA(s1);
            csd.HireTA(s2);
            csd.HireTA(s3);
            csd.AlumnusGrad(s1);
            csd.HireTA(s4);
        }
        catch(NoTAException e){
            fail();
        }
        catch(NoSpaceException e){
            fail();
        }
        assertFalse(f1.getTAs().contains(s1), "Delete Grad Student after graduating failed");
        assertEquals(s1.getAdvisor(), f1, "Remove Grad Advisor Link failed after graduation");
        assertEquals(3, csd.getNumOfGradStudents(), "Decrement Grad count in Dept Failed");
        assertEquals(3, f1.getNumOfTAs(), "Decrement Grad count in Faculty Failed");
        Grad s5 = new Grad("George", "Hardy", 45, "Male", "Rockwood Drive");
        Grad s6 = new Grad("Radi", "Aman", 32, "Male", "Tawoon Road");
        Grad s7 = new Grad("John", "Mark", 27, "Male", "Pizza Road");
        boolean caught = false;
        try{
            csd.HireTA(s5);
            csd.HireTA(s6);
            csd.HireTA(s7);
            csd.AlumnusGrad(s7);
        }
        catch(NoTAException e){
            caught = true;
        }
        catch (NoSpaceException e){
            fail();
        }
        assertTrue(caught, "NoTAException not thrown!");
        assertFalse(f1.getTAs().contains(s1), "Delete Grad Student after graduating failed");
        assertEquals(s1.getAdvisor(), f1, "Remove Grad Advisor Link failed after graduation");
        assertEquals(5, csd.getNumOfGradStudents(), "Decrement Grad count in Dept Failed");
        assertEquals(5, f1.getNumOfTAs(), "Decrement Grad count in Faculty Failed");
    }
    @Test
    @Order(20)
    void ExtractAllGradDetails(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Grad s0 = new Grad("Zara", "Mark", 30, "Male", "Canlish Road");
        Grad s1 = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("Jeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("Ryan", "Mark", 35, "Male", "Canlish Road");
        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireTA(s0);
            csd.HireTA(s1);
            csd.HireTA(s2);
            csd.HireTA(s3);
            csd.AlumnusGrad(s1);
            csd.HireTA(s1);
        }
        catch(NoTAException e){
            fail();
        }
        catch(NoSpaceException e){
            fail();
        }
        List<Grad> lst = new ArrayList<Grad>();
        lst.add(s3);
        lst.add(s2);
        lst.add(s1);
        lst.add(s0);
        assertTrue(lst.equals(csd.ExtractAllGradDetails()), "Extract All Grad Details Failed");
    }

    @Test
    @Order(22)
    void ExtractAllFacultyDetails(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("z", "Mark", 35, "Male", "Canlish Road");
        Faculty f2 = new Faculty("x", "Dave", 45, "Male", "Guildwood Parkway");
        Faculty f3 = new Faculty("y", "Tom", 55, "Male", "Danforth Road");
        Faculty f4 = new Faculty("a", "Mark", 35, "Male", "Canlish Road");
        Faculty f5 = new Faculty("b", "Hardy", 45, "Male", "Rockwood Drive");
        Faculty f6 = new Faculty("w", "Aman", 32, "Male", "Tawoon Road");
        Faculty f7 = new Faculty("c", "Mark", 27, "Male", "Pizza Road");
        Faculty f8 = new Faculty("j", "Jackson", 21, "Male", "Candy Road");
        Faculty f9 = new Faculty("l", "Walker", 15, "Female", "Qudra Road");
        Faculty f10 = new Faculty("t", "Mark", 35, "Male", "Some Road");

        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireFaculty(f3);
            csd.HireFaculty(f4);
            csd.HireFaculty(f5);
            csd.HireFaculty(f6);
            csd.HireFaculty(f7);
            csd.HireFaculty(f3);
            csd.HireFaculty(f8);
            csd.HireFaculty(f9);
            csd.HireFaculty(f10);

        }
        catch(NoSpaceException e){
            fail();
        }

        List<Faculty> lst = new ArrayList<Faculty>();
        lst.add(f4);
        lst.add(f5);
        lst.add(f7);
        lst.add(f8);
        lst.add(f9);
        lst.add(f10);
        lst.add(f6);
        lst.add(f2);
        lst.add(f3);
        lst.add(f1);
        assertEquals(true ,lst.equals(csd.ExtractAllFacultyDetails()), "Extract All Faculty Details Failed");
    }
    @Test
    @Order(23)
    void ExtractFacultyDetails(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("z", "Mark", 35, "Male", "Canlish Road");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("x", "Dave", 45, "Male", "Guildwood Parkway");
        f2.setProgram("Computer Science");
        Faculty f3 = new Faculty("y", "Tom", 55, "Male", "Danforth Road");
        f3.setProgram("Digital Technology");
        Faculty f4 = new Faculty("a", "Mark", 35, "Male", "Canlish Road");
        f4.setProgram("Software Engineering");
        Faculty f5 = new Faculty("b", "Hardy", 45, "Male", "Rockwood Drive");
        f5.setProgram("Computer Science");
        Faculty f6 = new Faculty("w", "Aman", 32, "Male", "Tawoon Road");
        f6.setProgram("Digital Technology");
        Faculty f7 = new Faculty("c", "Mark", 27, "Male", "Pizza Road");
        f7.setProgram("Software Engineering");
        Faculty f8 = new Faculty("j", "Jackson", 21, "Male", "Candy Road");
        f8.setProgram("Computer Science");
        Faculty f9 = new Faculty("l", "Walker", 15, "Female", "Qudra Road");
        f9.setProgram("Digital Technology");
        Faculty f10 = new Faculty("t", "Mark", 35, "Male", "Some Road");
        f10.setProgram("Software Engineering");
        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireFaculty(f3);
            csd.HireFaculty(f4);
            csd.HireFaculty(f5);
            csd.HireFaculty(f6);
            csd.HireFaculty(f7);
            csd.HireFaculty(f3);
            csd.HireFaculty(f8);
            csd.HireFaculty(f9);
            csd.HireFaculty(f10);
        }
        catch(NoSpaceException e){
            fail();
        }
        List<Faculty> lstCS = new ArrayList<Faculty>();
        List<Faculty> lstDT = new ArrayList<Faculty>();
        List<Faculty> lstSE = new ArrayList<Faculty>();
        lstSE.add(f4);
        lstSE.add(f7);
        lstSE.add(f10);
        lstSE.add(f1);
        lstCS.add(f5);
        lstCS.add(f8);
        lstCS.add(f2);
        lstDT.add(f9);
        lstDT.add(f6);
        lstDT.add(f3);
        assertTrue(lstCS.equals(csd.ExtractFacultyDetails("Computer Science")), "Extract All Faculty Details for CS Failed");
        assertTrue(lstSE.equals(csd.ExtractFacultyDetails("Software Engineering")), "Extract All Faculty Details for SE Failed");
        assertTrue(lstDT.equals(csd.ExtractFacultyDetails("Digital Technology")), "Extract All Faculty Details for DT Failed");
        assertFalse(lstDT.equals(csd.ExtractFacultyDetails("Digital")), "Extract All Faculty Details for DT Failed");
    }
    @Test
    @Order(24)
    void ExtractAdviseesDetails(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        UGrad s1 = new UGrad("z", "Mark", 35, "Male", "Canlish Road");
        UGrad s2 = new UGrad("x", "Dave", 45, "Male", "Guildwood Parkway");
        UGrad s3 = new UGrad("y", "Tom", 55, "Male", "Danforth Road");
        UGrad s4 = new UGrad("a", "Mark", 35, "Male", "Canlish Road");
        UGrad s5 = new UGrad("b", "Hardy", 45, "Male", "Rockwood Drive");
        UGrad s6 = new UGrad("w", "Aman", 32, "Male", "Tawoon Road");
        UGrad s7 = new UGrad("c", "Mark", 27, "Male", "Pizza Road");
        UGrad s8 = new UGrad("j", "Jackson", 21, "Male", "Candy Road");
        UGrad s9 = new UGrad("l", "Walker", 15, "Female", "Qudra Road");
        UGrad s10 = new UGrad("t", "Mark", 35, "Male", "Some Road");

        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.AdmitStudent(s1);
            csd.AdmitStudent(s2);
            csd.AdmitStudent(s3);
            csd.AdmitStudent(s4);
            csd.AdmitStudent(s5);
            csd.AdmitStudent(s6);
            csd.AdmitStudent(s7);
            csd.AdmitStudent(s3);
            csd.AdmitStudent(s8);
            csd.AdmitStudent(s9);
            csd.AdmitStudent(s10);

        }
        catch(NoSpaceException e){
            fail();
        }

        List<UGrad> lst1 = new ArrayList<UGrad>();
        List<UGrad> lst2 = new ArrayList<UGrad>();

        lst1.add(s4);
        lst1.add(s5);
        lst1.add(s7);
        lst1.add(s8);
        lst1.add(s6);
        lst1.add(s2);
        lst1.add(s3);
        lst1.add(s1);

        lst2.add(s9);
        lst2.add(s10);
        assertTrue(lst1.equals(csd.ExtractAdviseesDetails(f1)), "Extract First Advisor Students Failed");
        assertTrue(lst2.equals(csd.ExtractAdviseesDetails(f2)), "Extract Second Advisor Students Failed");
    }
    @Test
    @Order(25)
    void ExtractTAsDetails(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Software Engineering");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Grad s1 = new Grad("aaRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("bbJeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("cc2Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("ddRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s5 = new Grad("eeGeorge", "Hardy", 45, "Male", "Rockwood Drive");
        Grad s6 = new Grad("ffRadi", "Aman", 32, "Male", "Tawoon Road");
        Grad s7 = new Grad("ggJohn", "Mark", 27, "Male", "Pizza Road");
        Grad s8 = new Grad("hhPerseus", "Jackson", 21, "Male", "Candy Road");
        Grad s9 = new Grad("iiSarah", "Walker", 15, "Female", "Qudra Road");
        Grad s10 = new Grad("jjRadi", "Mark", 35, "Male", "Some Road");

        try{
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireTA(s10);
            csd.HireTA(s9);
            csd.HireTA(s8);
            csd.HireTA(s7);
            csd.HireTA(s6);
            csd.HireTA(s5);
            csd.HireTA(s4);
            csd.HireTA(s3);
            csd.HireTA(s2);
            csd.HireTA(s1);

        }
        catch(NoSpaceException e){
            fail();
        }
        List<Grad> lst1 = new ArrayList<Grad>();
        List<Grad> lst2 = new ArrayList<Grad>();

        lst1.add(s6);
        lst1.add(s7);
        lst1.add(s8);
        lst1.add(s9);
        lst1.add(s10);

        lst2.add(s1);
        lst2.add(s2);
        lst2.add(s3);
        lst2.add(s4);
        lst2.add(s5);

        assertTrue(lst1.equals(csd.ExtractTAsDetails(f1)), "Extract All TAs of first faculty Failed");
        assertFalse(lst2.equals(csd.ExtractTAsDetails(f1)), "ExtractTA not working properly");
        assertTrue(lst2.equals(csd.ExtractTAsDetails(f2)), "Extract All TAs of first faculty Failed");
    }
    @Test
    @Order(26)
    void RetireFacultyReassignTAs(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Computer Science");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Faculty f3 = new Faculty("Radi", "Riyas", 20, "Male","Avenue East");
        f3.setProgram("Software Engineering");
        Grad s1 = new Grad("aaRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("bbJeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("cc2Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("ddRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s5 = new Grad("eeGeorge", "Hardy", 45, "Male", "Rockwood Drive");
        Grad s6 = new Grad("ffRadi", "Aman", 32, "Male", "Tawoon Road");
        Grad s7 = new Grad("ggJohn", "Mark", 27, "Male", "Pizza Road");
        Grad s8 = new Grad("hhPerseus", "Jackson", 21, "Male", "Candy Road");
        Grad s9 = new Grad("iiSarah", "Walker", 15, "Female", "Qudra Road");
        try{
            csd.addProgramDirector(p1);
            csd.addProgramDirector(p2);
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireFaculty(f3);
            csd.HireTA(s1);
            csd.HireTA(s2);
            csd.HireTA(s3);
            csd.HireTA(s4);
            csd.HireTA(s5);
            csd.HireTA(s6);
            csd.HireTA(s7);
            csd.HireTA(s8);
            csd.HireTA(s9);
            csd.RetireFaculty(f1);
        }
        catch(NoSpaceException e){
            fail();
        }
        catch(NoSpecialtyException e){
            fail();
        }
        List<Grad> lst1 = new ArrayList<Grad>();
        List<Grad> lst2 = new ArrayList<Grad>();

        lst1.add(s2);
        lst1.add(s3);
        lst1.add(s4);
        lst1.add(s5);

        lst2.add(s1);
        lst2.add(s6);
        lst2.add(s7);
        lst2.add(s8);
        lst2.add(s9);

        assertTrue(lst1.equals(csd.ExtractTAsDetails(f3)), "Reassign TAs to new Faculty Failed");
        assertFalse(lst2.equals(csd.ExtractTAsDetails(f1)), "Delete oldFaculty Links Failed");
        assertTrue(lst2.equals(csd.ExtractTAsDetails(f2)), "Reassign TAs Failed");
    }
    @Test
    @Order(27)
    void RetireFacultyReassignUndergrads(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Computer Science");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Faculty f3 = new Faculty("Radi", "Riyas", 20, "Male","Avenue East");
        f3.setProgram("Software Engineering");
        UGrad s1 = new UGrad("aaRyan", "Mark", 35, "Male", "Canlish Road");
        UGrad s2 = new UGrad("bbJeremy", "Dave", 45, "Male", "Guildwood Parkway");
        UGrad s3 = new UGrad("cc2Adam", "Tom", 55, "Male", "Danforth Road");
        UGrad s4 = new UGrad("ddRyan", "Mark", 35, "Male", "Canlish Road");
        UGrad s5 = new UGrad("eeGeorge", "Hardy", 45, "Male", "Rockwood Drive");
        UGrad s6 = new UGrad("ffRadi", "Aman", 32, "Male", "Tawoon Road");
        UGrad s7 = new UGrad("ggJohn", "Mark", 27, "Male", "Pizza Road");
        UGrad s8 = new UGrad("hhPerseus", "Jackson", 21, "Male", "Candy Road");
        UGrad s9 = new UGrad("iiSarah", "Walker", 15, "Female", "Qudra Road");
        try{
            csd.addProgramDirector(p1);
            csd.addProgramDirector(p2);
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireFaculty(f3);
            csd.AdmitStudent(s1);
            csd.AdmitStudent(s2);
            csd.AdmitStudent(s3);
            csd.AdmitStudent(s4);
            csd.AdmitStudent(s5);
            csd.AdmitStudent(s6);
            csd.AdmitStudent(s7);
            csd.AdmitStudent(s8);
            csd.AdmitStudent(s9);
            csd.RetireFaculty(f1);
        }
        catch(NoSpaceException e){
            fail();
        }
        catch(NoSpecialtyException e){
            fail();
        }
        List<UGrad> lst1 = new ArrayList<UGrad>();
        List<UGrad> lst2 = new ArrayList<UGrad>();

        lst1.add(s1);
        lst1.add(s2);
        lst1.add(s3);
        lst1.add(s4);
        lst1.add(s5);
        lst1.add(s6);
        lst1.add(s7);
        lst1.add(s9);

        lst2.add(s8);

        assertTrue(lst1.equals(csd.ExtractAdviseesDetails(f2)), "Reassign TAs to new Faculty Failed");
        assertTrue(lst2.equals(csd.ExtractAdviseesDetails(f3)), "Reassign TAs Failed");
    }
    //SPECIAL CASES:
    @Test
    @Order(28)
    void RetireFacultyNoOtherFacultyInProgramException(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Computer Science");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Faculty f3 = new Faculty("Radi", "Riyas", 20, "Male","Avenue East");
        f3.setProgram("Software Engineering");
        Grad s1 = new Grad("aaRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("bbJeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("cc2Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("ddRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s5 = new Grad("eeGeorge", "Hardy", 45, "Male", "Rockwood Drive");
        Grad s6 = new Grad("ffRadi", "Aman", 32, "Male", "Tawoon Road");
        Grad s7 = new Grad("ggJohn", "Mark", 27, "Male", "Pizza Road");
        Grad s8 = new Grad("hhPerseus", "Jackson", 21, "Male", "Candy Road");
        Grad s9 = new Grad("iiSarah", "Walker", 15, "Female", "Qudra Road");
        boolean exceptionCaught = false;
        try{
            csd.addProgramDirector(p1);
            csd.addProgramDirector(p2);
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireFaculty(f3);
            csd.HireTA(s1);
            csd.HireTA(s2);
            csd.HireTA(s3);
            csd.HireTA(s4);
            csd.HireTA(s5);
            csd.HireTA(s6);
            csd.HireTA(s7);
            csd.HireTA(s8);
            csd.HireTA(s9);
            csd.RetireFaculty(f3);
        }
        catch(NoSpaceException e){
            fail();
        }
        catch(NoSpecialtyException e){
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught, "No other prof under Progam Exception Not thrown");
    }
    @Test
    @Order(29)
    void RetireFacultyNoOtherTAException(){
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setProgram("Computer Science");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        Grad s1 = new Grad("aaRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s2 = new Grad("bbJeremy", "Dave", 45, "Male", "Guildwood Parkway");
        Grad s3 = new Grad("cc2Adam", "Tom", 55, "Male", "Danforth Road");
        Grad s4 = new Grad("ddRyan", "Mark", 35, "Male", "Canlish Road");
        Grad s5 = new Grad("eeGeorge", "Hardy", 45, "Male", "Rockwood Drive");
        Grad s6 = new Grad("ffRadi", "Aman", 32, "Male", "Tawoon Road");
        Grad s7 = new Grad("ggJohn", "Mark", 27, "Male", "Pizza Road");
        Grad s8 = new Grad("hhPerseus", "Jackson", 21, "Male", "Candy Road");
        Grad s9 = new Grad("iiSarah", "Walker", 15, "Female", "Qudra Road");
        boolean exceptionCaught = false;
        try{
            csd.addProgramDirector(p1);
            csd.addProgramDirector(p2);
            csd.HireFaculty(f1);
            csd.HireFaculty(f2);
            csd.HireTA(s1);
            csd.HireTA(s2);
            csd.HireTA(s3);
            csd.HireTA(s4);
            csd.HireTA(s5);
            csd.HireTA(s6);
            csd.AlumnusGrad(s6);
        }
        catch(NoSpaceException e){
            fail();
        }
        catch (NoTAException e){
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught, "No TA under Faculty Exception Not thrown");
    }
   
   

    @Test
    @Order(32)
    void CheckMaxTACapacity() throws NoSpaceException{
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setSalary(25000);
        f1.setProgram("Computer Science");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        f1.setSalary(35000);

        csd.HireFaculty(f1);
        csd.HireFaculty(f2);
        Random rnd = new Random();

        List<Grad> tas = new ArrayList<>();

        for (int i = 0; i < 150; i++){
            tas.add(new Grad("TestFirstName " + i + ((char) (rnd.nextInt(26) + 65)),
                    "TestLastName " + ((char) (rnd.nextInt(26) + 65)), rnd.nextInt(100), " ",
                    "TestAddress" + ((char) (rnd.nextInt(26) + 65))));
        }
        for (int i = 0 ;i < 150; i++) {
            try {
                csd.HireTA(tas.get(i));
            } catch (NoSpaceException e){
                fail();
            }
        }
        assertEquals(150, csd.ExtractAllGradDetails().size(), "Exception thrown before Max Grad capacity has been reached");
    }

    @Test
    @Order(33)
    void ThrowTANoSpaceException() throws NoSpaceException{
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));
        Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female","Lawrence Avenue East");
        f1.setSalary(25000);
        f1.setProgram("Computer Science");
        Faculty f2 = new Faculty("Sean", "Smith", 48, "Male","Avenue East");
        f2.setProgram("Computer Science");
        f1.setSalary(35000);

        csd.HireFaculty(f1);
        csd.HireFaculty(f2);
        Random rnd = new Random();

        List<Grad> tas = new ArrayList<>();

        for (int i = 0; i < 151; i++){
            tas.add(new Grad("TestFirstName " + i + ((char) (rnd.nextInt(26) + 65)),
                    "TestLastName " + ((char) (rnd.nextInt(26) + 65)), rnd.nextInt(100), " ",
                    "TestAddress" + ((char) (rnd.nextInt(26) + 65))));
        }
        int i = 0;
        for (i = 0 ;i < 150; i++) {
            try {
                csd.HireTA(tas.get(i));
            } catch (NoSpaceException e){
                fail();
            }
        }
        try {
            csd.HireTA(tas.get(i));
            fail();
        } catch (NoSpaceException e){
        }
        assertEquals(150, csd.ExtractAllGradDetails().size(), "Max Grad capacity exception not thrown");
    }

    @Test
    @Order(34)
    void CheckMaxFacultyCapacity() throws NoSpaceException{
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));

        Random rnd = new Random();

        List<Faculty> faculty = new ArrayList<>();

        for (int i = 0; i < 70; i++){
            Faculty f = new Faculty("TestFirstName " + i + ((char) (rnd.nextInt(26) + 65)),
                    "TestLastName " + ((char) (rnd.nextInt(26) + 65)), rnd.nextInt(100), " ",
                    "TestAddress" + ((char) (rnd.nextInt(26) + 65)));
            f.setSalary(25000 + rnd.nextInt(10000));
            f.setProgram("Computer Science");
            faculty.add(f);
        }
        for (int i = 0 ;i < 70; i++) {
            try {
                csd.HireFaculty(faculty.get(i));
            } catch (NoSpaceException e){
                fail();
            }
        }
        assertEquals(70, csd.ExtractAllFacultyDetails().size(), "Exception thrown before Max Faculty capacity has been reached");

    }

    @Test
    @Order(35)
    void ThrowFacultyNoSpaceException() throws NoSpaceException{
        ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
        CSD csd = new CSD(chair);
        ProgramDirector p1 = new ProgramDirector("pd1","lastName", 50, "Male", "Californnia");
        p1.setProgram(("Computer Science"));
        ProgramDirector p2 = new ProgramDirector("pd2","lastName", 50, "Male", "America");
        p2.setProgram(("Software Engineering"));

        Random rnd = new Random();

        List<Faculty> faculty = new ArrayList<>();

        for (int i = 0; i < 75; i++){
            Faculty f = new Faculty("TestFirstName " + i + ((char) (rnd.nextInt(26) + 65)),
                    "TestLastName " + ((char) (rnd.nextInt(26) + 65)), rnd.nextInt(100), " ",
                    "TestAddress" + ((char) (rnd.nextInt(26) + 65)));
            f.setSalary(25000 + rnd.nextInt(10000));
            f.setProgram("Computer Science");
            faculty.add(f);
        }
        int i = 0;
        for (i = 0 ;i < 70; i++) {
            try {
                csd.HireFaculty(faculty.get(i));
            } catch (NoSpaceException e){
                fail();
            }
        }
        for (;i < 75; i++) {
            try {
                csd.HireFaculty(faculty.get(i));
                fail();
            } catch (NoSpaceException e){
            }
        }
        assertEquals(70, csd.ExtractAllFacultyDetails().size(), "Max Faculty capacity exception not thrown");

    }
}