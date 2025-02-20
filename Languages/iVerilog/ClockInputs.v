/*
Riley Moore
ECE 2372-005
Project 2
*/
module top(A, B, Add, Load, CLK, Clrn);

//Data Inputs
input [7:0] A;

output reg[7:0] B; //Output from Register/ Input to F.A.

//Control Inputs
input CLK;
input Load;
input Add;
input Clrn;

always@(posedge CLK) begin
    
    //If add = 1 : A + B
    if(Add) begin
        B <= B + A;
    end

    //If Load = 1 : B = A
    else if(Load)begin
        B <= A;
    end

    //If ClrN = 0 : B = 0
    else if(Clrn == 0) begin
        B <= 0;

    end
    else begin
        B <= A;
    end
end 

endmodule
