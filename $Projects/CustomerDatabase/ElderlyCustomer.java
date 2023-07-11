/*
    NAME: Elderly
    PURPOSE: Calculate the best travel plan pass
    INVARIANT: return best plan
*/

class Elderly extends Customer {
    
    private String birthYear;       //Storing when the customer was born

    //Conditional values for weekly and monthly pass
    private static final double monthPass = 80.0;
    private static final double weekPass = 40.0;

    //Rates for the Elderly
    private static final double weekDayElderly = 1.80;
    private static final double weekEndElderly = 1.00;
    
    Elderly () {
        super();
        setBirthYear(birthYear);
    }

    /*
        NAME: getElderlyTripInfo
        Parameters: weekEnd, weekDay to track number of trips, and month to track number of weeks in that month
        PURPOSE: Calculate the best pass for the customers travel plan
        PRECONDITION: Customer orders must be read in
        POSTCONDITION: The best pass plan for the customer is returned
    */
    public String getElderlyTripInfo (double weekEnd, double weekDay, double month) {
    
        String ecoPlanE = null;         //Final economical plan for customer
        double total = 0.0;             //Final total of customers trip costs

    //Algorithm to calculate customers total cost

        //Accounting for the $10 max per day on weekdays
        if(weekDay > 10){
            weekDay = 10 * 5;
        } 
        else {
            weekDay = weekDay * weekDayElderly * 5;
        }


        //Accounting for the $10 max per day on weekends
        if(weekEnd > 8){
            weekEnd = 10 * 2;
        } 
        else {
            weekEnd = weekEnd * weekEndElderly * 2;
        }

        //Finding the customers total
        total = weekEnd + weekDay;
        total *= month;

    //Seeing what plan is best for the customer

        //If total is greater than month pass, month pass would be useful
        if(total >= monthPass){
            ecoPlanE = "Monthly pass for $80.";
        }

        //If total is greater than week pass buy the week pass
        else if (total >= weekPass) {
            ecoPlanE = "Weekly pass for $40.";
        }

        //If total is less than week pass use PAYG card
        else if (total < weekPass) {
            ecoPlanE = "PAYG Card.";
        }

        //Otherwise custoemr not found
        else {
            System.out.println("***Error: " + getFirstName() + getPhoneNumber() + " has no matching customer record");
        }

        return ecoPlanE;
    }

    //Mutator and Accessor Methods for birthYear
    public String getBirthYear(){
        return birthYear;
    }

    public void setBirthYear(String birthYear){
        this.birthYear = birthYear;
    }
}
