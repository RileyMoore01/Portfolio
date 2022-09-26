/*
    NAME: Regular
    PURPOSE: Calculate the best travel plan pass
    INVARIANT: return best plan
*/
class Regular extends Customer {

    //Instance variable for reward number
    public String rewardNumber = null;

    //Conditonal values for weekly & monthly pass
    private static final double weekPassRegular = 50;
    private static final double weekPassReward = 47.5;
    private static final double monthPassRegular = 100;
    private static final double monthPassReward = 95;

    //Rates for regualr customers
    private static final double weekDayRegular = 3.00;
    private static final double weekEndRegular = 2.00;

    //Rates for Regular w reward number
    private static final double weekDayReward = 2.50;
    private static final double weekEndReward = 1.50;
    
    //Defualt constuctor
    Regular () {
        super();
        setRewardNumber();
    }

    public String getRewardNumber(){
        return rewardNumber;
    }

    public void setRewardNumber(){
        this.rewardNumber = getDiscount();
    }

    /*
        NAME: getRegularTripInfo
        Parameters: weekEnd, weekDay to track number of trips, and month to track number of weeks in that month
        PURPOSE: Calculate the best pass for the customers travel plan
        PRECONDITION: Customer orders must be read in
        POSTCONDITION: The best pass plan for the customer is returned
    */
    public String getRegularTripInfo (double weekDay, double weekEnd, double month){
        double total = 0.0;     //total the customer will have to pay w/out a pass
        String ecoPlanR = null;  //String to output the best economical pass
        
        //Algorthim to cacluate the Regular custoemrs total


    //If customer does not have a rewards number
        if(getRewardNumber() == null || getRewardNumber() == "NA"){
            //Accounting for the $10 max per day on weekdays
            if(weekDay > 3){
                weekDay = 10 * 5;
            } 
            
            else {
                weekDay = weekDay * weekDayRegular * 5;
            } 

            //Accounting for the $10 max per day on weekends
            if(weekEnd > 5){
                weekEnd = 10 * 2;
            } 
            
            else {
                weekEnd = weekEnd * weekEndRegular * 2;
            }

            total = weekDay + weekEnd;  //Calculating the total cost per week
            total *= month; //Converts weekly rate to monthly rate

            //If total is greater than month pass buy the month pass
            if(total >= monthPassRegular){
                ecoPlanR = "Monthly pass for $100.";
            } 

            //If total is greater than month pass buy the month pass
            else if (total >= weekPassRegular) {
                ecoPlanR = "Weekly pass for $50.";
            }  

            //If tota is less than weekly pass just use your PAYG card
            else if (total <= weekPassReward) {
                ecoPlanR = "PAYG card";
            }

            //Otherwise print out error
            else {
                System.out.println("***Error: " + getFirstName() + getPhoneNumber() + " has no matching customer record");
            }
        } 
    //If customer does have a rewards number
        else {
            //Accounting for the $10 max per day on weekdays
            if(weekDay > 3){
                weekDay = 10 * 5;
            } 
            
            else {
                weekDay = weekDay * weekDayReward * 5;
            } 

            //Accounting for the $10 max per day on weekends
            if(weekEnd > 5){
                weekEnd = 10 * 2;
            } 
            
            else {
                weekEnd = weekEnd * weekEndReward * 2;
            }

            total = weekDay + weekEnd;
            total *= month ; 

            //If total is greater than month pass buy the month pass
            if(total >= monthPassReward){
                ecoPlanR = "Monthly pass for $95 each.";
            } 

            //If total is greater than month pass buy the month pass
            else if (total >= weekPassReward) {
                ecoPlanR = "Weekly pass for $47.5 each.";
            }

            //If tota is less than weekly pass just use your PAYG card
            else if (total <= weekPassReward) {
                ecoPlanR = "PAYG card";
            }

            //Otherwise print out error
            else {
                System.out.println("***Error: " + getFirstName() + getPhoneNumber() + " has no matching customer record");
            }
        }

        return ecoPlanR;   //Returns total per month
    }
}
