module HA(sout,cout,a,b);
	output sout,cout;
	input a,b;
	assign sout=a^b;
	assign cout=(a&b);
endmodule

module FA(sout,cout,a,b,cin);
	output sout,cout;
	input a,b,cin;
	assign sout=(a^b^cin);
	assign cout=((a&b)|(a&cin)|(b&cin));
endmodule

module gray_cell(a, b, ci, si, co, so);
	input a, b, ci, si;
	output co, so;
	FA fa(so, co, ci, ~(a&b), si);
endmodule	 

module white_cell(a, b, ci, si, co, so);
	input a, b, ci, si;
	output co, so;
	FA fa(so, co, ci, (a&b), si);
endmodule

module multiple4bits(a, b, p,cout);
	input [3:0]a;
	input [3:0]b;
	output [7:0]p;
	output cout;
	wire w1, w2, w3, w4, w5, w6, w7, w8, w9;
	wire w10, w11, w12, w13, w14, w15, w16, w17;
	wire w18, w19, w20, w21, w22, w23, w24, w25;
	wire w26, w27, w28, w29, w30, w31;
	//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
	gray_cell g0_0(a[3],b[0],0,0,w1,w2);
	white_cell w0_1(a[2],b[0],0,0,w3,w4);
	white_cell w0_2(a[1],b[0],0,0,w5,w6);
	white_cell w0_3(a[0],b[0],0,0,w7,p[0]);
	//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
	gray_cell g1_0(a[3],b[1],0,w1,w8,w9);
	white_cell w1_1(a[2],b[1],w2,w3,w10,w11);
	white_cell w1_2(a[1],b[1],w4,w5,w12,w13);
	white_cell w1_3(a[0],b[1],w6,w7,w14,p[1]);
	//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
	gray_cell g2_0(a[3],b[2],0,w8,w15,w16);
	white_cell w2_1(a[2],b[2],w9,w10,w17,w18);
	white_cell w2_2(a[1],b[2],w11,w12,w19,w20);
	white_cell w2_3(a[0],b[2],w13,w14,w21,p[2]);
	//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
	white_cell w3_0(a[3],b[3],0,w15,w22,w23);
	gray_cell g3_1(a[2],b[3],w16,w17,w24,w25);
	gray_cell g3_2(a[1],b[3],w18,w19,w26,w27);
	gray_cell g3_3(a[0],b[3],w20,w21,w28,p[3]);
	//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
	FA f3(p[4],w29,w27,w28,1'b1);
	FA f2(p[5],w30,w25,w26,w29);
	FA f1(p[6],w31,w23,w24,w30);
	FA f0(p[7],cout,1'b1,w22,w31);
endmodule

module tb;
	reg [3:0] inp1, inp2;
	reg carryin;
	wire carryout;
	wire [7:0] ans;
	
	initial
		begin
			inp1 = 0;
			inp2 = 0;
			carryin = 0;
		end
	
	always
		begin
			#10 inp1 = 1010;
			#10 inp2 = 1010;
			#10 inp1 = 1001;
			#10 inp2 = 0010;
			#10 inp1 = 0110;
			#10 inp2 = 1000;
			#10 inp1 = 0110;
			inp2 = 20;
		end
	
	multiply4bits mul4(inp1,inp2,ans,carryout);
	
endmodule




