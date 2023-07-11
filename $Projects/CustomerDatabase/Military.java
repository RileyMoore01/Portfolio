/*
    NAME: Military
    PURPOSE: Calculate the best travel plan pass
    INVARIANT: return best plan
*/
class Military extends Customer {
    //Branch of military the customer is in
    private String militaryBranch = null;;

    //Conditional values for weekly and monthly pass
    private static final double monthPass = 90.0;
    private static final double weekPass = 45.0;

    //Rates for Military customer
    private static final double weekDayMilitary = 2.30;
    private static final double weekEndMilitary = 1.30;

    
    //Subclass constrcutor
    Military () {
        super();
        setMilitaryBranch(militaryBranch);
    }

        /*
        NAME: getMilitaryTripInfo
        Parameters: weekEnd, weekDay to track number of trips, and month to track number of weeks in that month
        PURPOSE: Calculate the best pass for the customers travel plan
        PRECONDITION: Customer orders must be read in
        POSTCONDITION: The best pass plan for the customer is returned
    */
    public String getMilitaryTripInfo (double weekEnd, double weekDay, double month) {
        
        String ecoPlanM = null;         //Final economical plan for customer
        double total = 0.0;             //Final total of customers trip costs

    //Algorithm to calculate customers total cost
        //Accounting for the $10 max per day on weekdays
        if(weekDay > 5){
            weekDay = 10 * 5;
        } 
        else {
            weekDay = weekDay * weekDayMilitary * 5;
        }


        //Accounting for the $10 max per day on weekends
        if(weekEnd > 8){
            weekEnd = 10 * 2;
        } 
        else {
            weekEnd = weekEnd * weekEndMilitary * 2;
        }

        //Finding the customers total
        total = weekEnd + weekDay;
        total *= month;

    //Seeing what plan is best for the customer

        //If total is greater than month pass, month pass would be useful
        if(total >= monthPass){
            ecoPlanM = "Monthly pass for $90.";
        }

        //If total is greater than week pass buy the week pass
        else if (total >= weekPass) {
            ecoPlanM = "Weekly pass for $45.";
        }

        //If total is less than week pass use PAYG card
        else if (total < weekPass) {
            ecoPlanM = "PAYG Card.";
        }

        //Otherwise custoemr not found
        else {
            System.out.println("***Error: " + getFirstName() + getPhoneNumber() + " has no matching customer record");
        }


        return ecoPlanM;
    }
    
    public String getMilitaryBranch(){
        return militaryBranch;
    }

    public void setMilitaryBranch(String militaryBranch){
        this.militaryBranch = militaryBranch;
    }
}
