
`timescale 1 ns / 1 ps

module Universal_Shift_Register (opcode , data , out , clear , clk , input_shift_right , input_shift_left);
	input [3:0] data ;
	input [1:0] opcode;
	input clk , clear , input_shift_right , input_shift_left;
	output [3:0] out;
	wire w1 ,w2 ,w3 ,w5 ,w6 ,w7 ,w8 ,w9; //  ,w10  ,w4
	
	
	mux4 m1 (opcode, w5 , w3 , input_shift_left , data[0],  w9 );
	mux4 m2 (opcode, w3 , w2 , w5 , data[1],  w8 );
	mux4 m3 (opcode, w2 , w1 , w3 , data[2],  w7 ); // w10
	mux4 m4 (opcode, w1 , input_shift_right , w2 , data[3],  w6 );   //w4
	
	D_flip_flop D1 (w9  ,clk , clear ,  w5 );
	D_flip_flop D2 (w8  ,clk , clear ,  w3 );
	D_flip_flop D3 (w7  ,clk , clear ,  w2 );
	D_flip_flop D4 (w6  ,clk , clear ,  w1 );
	
	assign 	out[0] = w5 ;
	assign 	out[1] = w3 ;
	assign 	out[2] = w2 ;
	assign 	out[3] = w1 ;
	
endmodule

module Universal_Shift_Register_tb;
		
	reg [3:0] data ;
	reg [1:0] opcode;
	reg clk , clear , input_shift_right , input_shift_left;
	wire [3:0] out;
	
	Universal_Shift_Register tb (opcode , data , out , clear , clk , input_shift_right , input_shift_left);
	
	initial
		begin
			
			data <= 1100;
			opcode <= 11;
			clear <= 0;
			input_shift_right <= 0;
			input_shift_left <= 0;
			clk <= 0;
		end
	always #1 clk=~clk;
	
	initial
		begin
			#10;
			$display("data: %b, opcode %b. Out %b . ", data, opcode, out);
			#10;
			opcode <= 00;
			#10;
			$display("data: %b, opcode %b. Out %b.", data, opcode, out);
			#2;
			opcode <= 10;
			#2;
			$display("data: %b, opcode %b. Out %b.", data, opcode, out);
			
		end
endmodule















