
`timescale 1 ns / 1 ps
module mux4 (select, input_0 , input_1 , input_2 , input_3,  out );
							 
	input[1:0] select;
	input input_0 , input_1 , input_2 , input_3;
	output out;	
	reg q;
	
	always @( select or input_0 or input_1 or input_2 or input_3 )
		begin
			case( select )
				0 : q = input_0;
				1 : q = input_1;
				2 : q = input_2;
				3 : q = input_3;
			endcase
		end
	assign out = q;
endmodule
