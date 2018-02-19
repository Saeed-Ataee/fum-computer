
`timescale 1 ns / 1 ps
module D_flip_flop (data  ,clk    , reset ,  q );
	input data, clk, reset ; 
	output reg q;
	
	
	always @ ( posedge clk or negedge reset)
		if (reset == 1'b1) 
			begin
				q <= 1'b0;
			end  
		else
			begin
				q <= data;
			end
	
	
endmodule
