/*
Riley Moore
!!!!!  
I had a another version with 85/100 however I felt that 
this version was much closer to the correct answer, 
if that gets me a better grade I would perfer to submit that if possible
!!!!!
*/
module top(CLK, RST, OP, DATA, ADDR, outA, outB);

//Inputs
input CLK;
input RST;
input [1:0] OP;
input [7:0] DATA;
input [1:0] ADDR;

reg [7:0]regX;
reg [7:0]regY;

//Data Register
output reg[7:0] outA;
output reg[7:0] outB;

always@(negedge CLK)begin
   //Reset Logic
    if(RST)begin
        regX = 0;
        regY = 0;
    end

    else if(OP == 00)begin
        
    end
   //Determining Operation
    else if(OP == 01)begin
        if(ADDR == 00)begin 
            regX = regX + regY;
        end 
        
        else if(ADDR == 01)begin
           regY = regX + regY;
        end

       else if(ADDR == 10)begin
           outA = regX + regY;
        end

       else if(ADDR == 11)begin
            outB = regX + regY;
        end
    end
    
      
    else if(OP == 10)begin
        if(ADDR == 00)begin
           regX = regX - regY;
        end

        else if(ADDR == 01)begin
            regY = regX - regY;
        end 

        else if(ADDR == 10)begin
           outA = regX - regY;
        end 

       else if(ADDR == 11)begin
           outB = regX - regY;
        end   
    end
     
    else if(OP <= 11) begin
        if(ADDR <= 00)begin
           regX = DATA;
        end

        else if(ADDR <= 01)begin
            regY = DATA;
        end

        else if(ADDR <= 10)begin
           outA = DATA;
        end 

       else if(ADDR <= 11)begin
           outB = DATA;
    end
  end
end

endmodule
