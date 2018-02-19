
module adder4(A, B, cin, cout, S);
	input[3:0] A, B;
	input cin;
	output[3:0] S;
	output cout;
	wire c1, c2, c3;
	// 4 instantiated 1-bit Full Adders
	FullAdder2 fa0(A[0], B[0], cin, c1, S[0]);
	FullAdder2 fa1(A[1], B[1], c1, c2, S[1]);
	FullAdder2 fa2(A[2], B[2], c2, c3, S[2]);
	FullAdder2 fa3(A[3], B[3], c3, cout, S[3]);
endmodule


module FullAdder2(a,b,cin,cout, sum);
	input a,b,cin;
	output sum, cout;
	
	assign sum = a ^ b ^ cin;
	assign cout = (a & b) | (a & cin) | (b & cin);
	
endmodule


module tb;
	reg [7:0] inp1, inp2;
	reg carryin;
	wire carryout;
	wire [7:0] sum;
	
	initial
		begin
			inp1 = 0;
			inp2 = 0;
			carryin = 0;
		end
	
	always
		begin
			#10 inp1 = 1;
			#10 inp2 = 0;
			#10 inp1 = 10;
			#10 inp2 = 2;
			#10 inp1 = 12;
			#10 inp2 = 0;
			#10 inp1 = 14;
			inp2 = 20;
		end
	
	adder8 add8b (inp1, inp2, carryin, carryout, sum);
	
endmodule

module mux2to1(A, B, select, cout);
	input[3:0] A, B;
	input select;
	output[3:0] cout;
	assign cout = select ? B : A; 
	
endmodule

module adder8(A, B, cin, cout, S);
	input[7:0] A;
	input[7:0] B;
	input cin;
	output[7:0] S;
	output cout;
	wire c1, c2, c3;		  
	wire [3:0]input_mux1;
	wire [3:0]input_mux2;
	reg one =1'b1;
	reg zero =1'b0;
	
	adder4 a0(A[7:4], B[7:4], zero, c1, input_mux1[3:0]); 
	adder4 a1(A[7:4], B[7:4], one, c2, input_mux2[3:0]);
  mux2to1 muxx(input_mux1[3:0],input_mux2[3:0], c3, S[7:4]);
	adder4 a2(A[3:0], B[3:0],cin,c3,S[3:0]);
	assign cout = c2&(c1|c3);
endmodule