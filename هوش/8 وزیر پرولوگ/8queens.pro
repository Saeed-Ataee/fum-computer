queens([]).

 

queens([ Row/Col | Rest]) :-

            queens(Rest),

            member(Col, [1,2,3,4,5,6,7,8]),

            safe( Row/Col, Rest).
 

safe(Anything, []).	% تخته خالی همیشه امن است

 

safe(Row/Col, [Row1/Col1 | Rest]) :-	% چک می کند آیا در ردیف پایینی تهدیدی وجود دارد یا خیر

            Col =\= Col1, 	% ستون مشابه

            Col1 - Col =\= Row1 - Row,	% چک کردن قطرها

            Col1 - Col =\= Row - Row1,

            safe(Row/Col, Rest).	% ردیف بعدی تهدیدی ندارد. بقیه جدول بررسی شود

 

member(X, [X | Tail]).

 

member(X, [Head | Tail]) :-

            member(X, Tail).

 

board([1/C1, 2/C2, 3/C3, 4/C4, 5/C5, 6/C6, 7/C7, 8/C8]).
