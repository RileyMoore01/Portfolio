/*
    NAME: Student
    PURPOSE: Calculate the best travel plan pass
    INVARIANT: return best plan
*/
class Student extends Customer {
    
    //Instance field for school name
    private String schoolName;

    //Conditional values for weekly and monthly pass
    private static final double monthPass = 85;
    private static final double weekPass = 42.5;

    //Rates for Student
    private static final double weekDayStudent = 2.00;
    private static final double weekEndStudent = 1.20;

    Student () {
        super();
        setSchoolName(schoolName);
    }

    /*
        NAME: getStudentTripInfo
        Parameters: weekEnd, weekDay to track number of trips, and month to track number of weeks in that month
        PURPOSE: Calculate the best pass for the customers travel plan
        PRECONDITION: Customer orders must be read in
        POSTCONDITION: The best pass plan for the customer is returned
    */
    public String getStudentTripInfo (double weekDay, double weekEnd, double month) {
        
        double total = 0;   //Storing the final amount trips will cost in a month
        String ecoPlanS = null;     //Storing the best economical plan

    //Algorthim to cacluate the custoemrs total

        //Accounting for the $10 max per day on weekdays
        if(weekDay > 5){
            weekDay = 10 * 5;
        } 
        
        else {
            weekDay = weekDay * weekDayStudent * 5;
        } 


        //Accounting for the $10 max per day on weekends
        if(weekEnd > 9){
            weekEnd = 10 * 2;
        } 
        
        else {
            weekEnd = weekEnd * weekEndStudent * 2;
        }

        //Finding the students total
        total = weekEnd + weekDay;
        total *= month;

    //Seeing what plan is best for the customer

        //If total is greater than month pass, month pass would be useful
        if(total >= monthPass){
            ecoPlanS = "Monthly pass for $85.";
        } 

        //If total is greater than week pass buy the week pass
        else if (total >= weekPass) {               
            ecoPlanS = "Weekly pass for $42.5.";
        }  

        //If total is less than week pass use PAYG card
        else if (total < weekPass) {
            ecoPlanS = "PAYG Card.";
        }

        //Otherwise custoemr not found
        else {
            System.out.println("***Error: " + getFirstName() + getPhoneNumber() + " has no matching customer record");
        }
        
        return ecoPlanS;
    }
    
    public String getSchoolName(){
        return schoolName;
    }

    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }
}
